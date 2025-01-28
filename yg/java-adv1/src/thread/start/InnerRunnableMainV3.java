package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV3 {
    public static void main(String[] args) {
        log("main() start");

        // 익명 클래스
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        log("Hello, Runnable");
                    }
                }
        );
        thread.start();

        log("main() end");
    }

}
