package thread.control;

import static util.MyLogger.log;

public class ThreadStateMain {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new MyRunnable(), "myThread");
        log(STR."my Thread.state: \{myThread.getState()}"); // NEW
        log("myThread.start()");
        myThread.start();
        Thread.sleep(1000);
        log("myThread.state3 = " + myThread.getState()); // TIME_WAITING
        Thread.sleep(3000);
        log("myThread.state5 = " + myThread.getState()); // TERMINATED

    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {

            try {
                log("start");
                log("myThread.state2 = " + Thread.currentThread().getState()); // RUNNABLE
                log("sleep() start");
                Thread.sleep(3000);
                log("sleep() end");
                log("myThread.state4 = " + Thread.currentThread().getState()); // RUNNABLE
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
