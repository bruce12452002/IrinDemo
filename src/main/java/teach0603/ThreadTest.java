package teach0603;

public class ThreadTest extends Thread { // implements Runnable{
    private static int i;

    @Override
    public void run() {
        exec();
    }

//    public void start() {
//        exec();
//    }

    private void exec() {
        for (i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i + " ");
        }
    }

    public static void main(String[] args) {
        ThreadTest tt1 = new ThreadTest();
        ThreadTest tt2 = new ThreadTest();
        tt1.setName("tt1");
        tt2.setName("tt2");
        tt1.start();
        tt2.start();
    }
}
