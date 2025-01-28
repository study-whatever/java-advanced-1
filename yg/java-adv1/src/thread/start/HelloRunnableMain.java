package thread.start;

public class HelloRunnableMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start"); // main

        HelloRunnable helloRunnable = new HelloRunnable();
        Thread thread = new Thread(helloRunnable); // Runnable을 Thread 생성자에 전달
        thread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end"); // main
    }
}
