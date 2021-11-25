/*import java.io.IOException;
import java.awt.AWTException;

 public class Main {
     //public   DBdutiesImpl dbobj=new DBdutiesImpl();
     //public static void main(String[] args) {
       //  System.out.println("Hello world");
        // test obj;
         //db connction established
        // DBdutiesImpl dbobj=new DBdutiesImpl();
        // dbobj.connect();
        // String output= dbobj.load();
        // System.out.println(output +" from main");

    //public String getDboj(){
        // return dbobj.load();
    //}
    public static void main(String[] args) throws InterruptedException {
        // write your code here
        int i = 0;
        GameLogic object = new GameLogic();
        object.ConstructBoard();
        object.displayBoard();
        Thread.sleep(1000);
        clearScreen();
        while(i < 2) {

            object.updateBoard();
            System.out.println();
            object.displayBoard();
            Thread.sleep(1000);
            clearScreen();
        }
    }
     public static void clearScreen() throws InterruptedException {
         System.out.print("\033[H\033[2J");
         System.out.flush();
    }

}


 */
import java.io.BufferedReader;
        import java.io.IOException;
        import java.awt.AWTException;
        import java.io.InputStreamReader;

public class Main  {

    public static void main(String[] args) throws InterruptedException, IOException {


        int i = 0;
        GameLogic object0 = new GameLogic();
        board object = new board();


        object.displayMenu();
        object.displayBoard();

        Thread.sleep(1000);
        object.clear();

        while(true) {

            if (object0.Stop()) {
                object0.Reset();
            }
            System.out.println();
            object.displayMenu();
            object0.updateBoard();
            object.displayBoard();
            Thread.sleep(1000);
            object.clear();

        }




    }

}
