package thread.start.test;

import static util.MyLogger.log;

public class CounterRunnableMainV2 {
    public static void main(String[] args) {
        Runnable counterRunnable = new Runnable(){
            @Override
            public void run() {
                for (int i =1; i<=5; i++){
                    log("value: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread thread = new Thread(counterRunnable, "counter");
        thread.start();
    }

}
