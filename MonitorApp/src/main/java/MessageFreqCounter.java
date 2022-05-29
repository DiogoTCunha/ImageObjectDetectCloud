import java.util.concurrent.atomic.AtomicInteger;

public class MessageFreqCounter {

    private final long monitoringInterval;
    private long startingInterval;
    private final int[] details;
    private final AtomicInteger eventCount = new AtomicInteger();
    private int total;

    MessageFreqCounter(long interval) {
        monitoringInterval = interval;
        startingInterval = System.currentTimeMillis() - monitoringInterval;
        details = new int[16];
    }

    public void increment(){
        checkInterval(System.currentTimeMillis());
        eventCount.incrementAndGet();
    }

    public int getCount() {
        long currentTime = System.currentTimeMillis();
        checkInterval( currentTime );

        long diff = currentTime - startingInterval - monitoringInterval;
        double partFactor = (diff * details.length / (double)monitoringInterval);
        int part = (int)(details[0] * partFactor);
        return total + eventCount.get() - part;
    }

    private void checkInterval(long time){
        if( (time - startingInterval - monitoringInterval) > monitoringInterval / details.length ) {
            synchronized( details ) {
                long detailInterval = monitoringInterval / details.length;
                while( (time - startingInterval - monitoringInterval) > detailInterval ) {
                    int currentValue = eventCount.getAndSet( 0 );
                    if( (total | currentValue) == 0 ) {
                        // for the case that the counter was not used for a long time
                        startingInterval = time - monitoringInterval;
                        return;
                    }
                    int size = details.length - 1;
                    total += currentValue - details[0];
                    System.arraycopy( details, 1, details, 0, size );
                    details[size] = currentValue;
                    startingInterval += detailInterval;
                }
            }
        }
    }
}
