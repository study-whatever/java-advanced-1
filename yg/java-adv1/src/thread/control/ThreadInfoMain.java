package thread.control;

import thread.start.HelloRunnable;

import static util.MyLogger.log;

public class ThreadInfoMain {
    public static void main(String[] args) {
        //main 스레드
        Thread mainThread = Thread.currentThread();
        log("main thread: " + mainThread);
        log("main thread id: " + mainThread.getId());
        log("main thread name: " + mainThread.getName());
        log("main thread priority: " + mainThread.getPriority());
        log("main thread group: " + mainThread.getThreadGroup());
        log("main thread state: " + mainThread.getState());

        //myThread 스레드
        Thread myThread = new Thread(new HelloRunnable(), "myThread");
        log("main thread: " + myThread);
        log("main thread id: " + myThread.getId());
        log("main thread name: " + myThread.getName());
        log("main thread priority: " + myThread.getPriority());
        log("main thread group: " + myThread.getThreadGroup());
        log("main thread state: " + myThread.getState());
    }
}
