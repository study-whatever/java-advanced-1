package thread.start.test;

import static util.MyLogger.log;

public class MultiThreadMain {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new PrintWork("A", 1000));
        Thread thread2 = new Thread(new PrintWork("B", 500));
        thread1.start();
        thread2.start();

    }

    static class PrintWork implements Runnable {
        private String content;
        private int interval;
        public PrintWork(String content, int interval) {
            this.content = content;
            this.interval = interval;
        }

        @Override
        public void run() {
            while (true) {
                log(content);
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
