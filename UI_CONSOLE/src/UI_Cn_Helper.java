import java.io.IOException;

public class UI_Cn_Helper {
public void start() throws InterruptedException {
    clearScreen();
    MainMenu mMenu= new MainMenu();
   String choice= mMenu.show();
}
    public static void clearScreen() throws InterruptedException {
        System.out.println(System.lineSeparator().repeat(100));
        }
    }

