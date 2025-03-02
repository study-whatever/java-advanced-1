package thread.control;

public class CheckedExceptionMain {
    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run()  {

        }
    }
}
