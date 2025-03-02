package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

// sleep 사용하기
public class JoinMainV3 {
    public static void main(String[] args) throws InterruptedException {
        log("main start");
        SumTask sumTask1 = new SumTask(1, 50);
        SumTask sumTask2 = new SumTask(51, 100);
        Thread thread1 = new Thread(sumTask1, "thread-1");
        Thread thread2 = new Thread(sumTask2, "thread-2");

        thread1.start();
        thread2.start();

        // 스레드가 종료될 때까지 대기
        log("main 스레드 종료 대기");
        thread1.join();
        thread2.join();
        log("main 스레드 종료 대기 끝");


        log("task1 result = " + sumTask1.result);
        log("task2 result = " + sumTask2.result);

        int sumAll = sumTask1.result + sumTask2.result;
        log("sumAll = " + sumAll);

        log("main end");
    }

    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;
            log("작업 완료 result = " + result);
        }
    }
}
