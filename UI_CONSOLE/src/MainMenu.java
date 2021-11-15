import java.util.Scanner;

public class MainMenu {
    public String show()
    {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("          GAME OF LIFE           \n\n");
        System.out.println("chose an option from below:");
        System.out.println("1-New State");
        System.out.println("2-View Saved State");
        System.out.println("3-Rules");
        System.out.println("4-Exit");
        return myObj.nextLine();
    }
}
