package BL;

public class test extends Thread {
    //    public static void main(String[] args) {
//        test thread = new test();
//        thread.start();
//        while (thread.isAlive()) {
//            System.out.println("Waiting...");
//        }
//        for (int i = 0; i < 10; i++)
//            System.out.println("This code is outside of the thread");
//    }
    public static void updateBoard() {
        System.out.println("board");
    }

    @Override
    public void run() {


        for (int i = 0; i < 10; i++)
            System.out.println("in thread");

    }
}
