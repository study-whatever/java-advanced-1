package thread.start;

public class HelloThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start"); // main

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        helloThread.start(); //
        System.out.println(Thread.currentThread().getName() + ": start() 호출 ");
        System.out.println(Thread.currentThread().getName() + ": main() end"); // main
    }
}
