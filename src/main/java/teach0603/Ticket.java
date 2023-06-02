package teach0603;

import java.util.concurrent.TimeUnit;

public class Ticket implements Runnable {
    private /*static*/ int ticket = 10;

    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);

                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "=>" + ticket--);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        // 不同的 Ticket 不一定可以共享票
        differentTicket();

        // 同個 Ticket 可以共享票
//        sameTicket();
    }

    private static void differentTicket() {
        // new Ticket() 3 次，所以票也是 3 倍
        // 這種寫法可將 ticket 改成 static，會發現有問題，因為有可能同一個時間點，多個執行緒去拿到 ticket 且都 > 0，但 ticket-- 時就會多了
        Thread t1 = new Thread(new Ticket());
        Thread t2 = new Thread(new Ticket());
        Thread t3 = new Thread(new Ticket());
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();
    }

    private static void sameTicket() {
        // 實際上，在 run 裡的程式碼，sleep() 會發現有問題，因為有可能同一個時間點，多個執行緒去拿到 ticket 且都 > 0，但 ticket-- 時就會多了
        Ticket r = new Ticket();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
