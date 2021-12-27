package UI_Console;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class GameScreen {
    private static int speed = 900;

    private static void clearScreen() throws InterruptedException {
        System.out.println(System.lineSeparator().repeat(100));
    }

    private static void printinitialBoard(ArrayList<Integer> cells) throws InterruptedException {
        clearScreen();
        int x = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                if (cells.get(x) == i && cells.get(x + 1) == j) {
                    System.out.print(" X ");
                    if (x + 2 < cells.size())
                        x += 2;
                } else
                    System.out.print(" o ");

            }

            System.out.println();
        }
    }

    private static void printBoard(ArrayList<Integer> cells) throws InterruptedException {
        clearScreen();
        int x = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (cells.size() == 0) {
                    System.out.print(" o ");
                } else {

                    if ((cells.get(x) % 20) == i && (cells.get(x + 1) % 20) == j) {
                        System.out.print(" X ");
                        if (x + 2 < cells.size())
                            x += 2;
                    } else
                        System.out.print(" o ");
                }

            }

            System.out.println();
        }
    }

    private static String Gamechoice() {
        System.out.println("1-run more iterations?\n2-Save pattern\n3-Back to Main menu\n4-change speed");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        return myObj.nextLine();

    }

    //set the manually drawn pattern on Board
    private static void setChoiceonBoard(ArrayList<Integer> cells) {
        for (int i = 0; i < cells.size(); i += 2) {
            Helper.write_argument(cells.get(i), "input1");
            Helper.write_argument(cells.get(i + 1), "input1");
            mainmenu.get_BL().isClicked();

        }
    }

    //take user input (manually draw pattern)
    private static void Draw() {
        boolean flag = true;
        ArrayList<Integer> cells = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Row and Column number for each cell or press -1 to stop ");
        while (flag) {

            int choice = myObj.nextInt();//row or exit
            if (choice != -1) {
                cells.add(choice);
                choice = myObj.nextInt();//column
                cells.add(choice);
            } else flag = false;
        }
        setChoiceonBoard(cells);

    }

    private static void setspeed() {
        System.out.println("Enter speed from 1 to 10");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int newspeed = myObj.nextInt();
        speed = 900 + newspeed;
    }

    public static void init(int mode) throws InterruptedException, SQLException, ClassNotFoundException {

        if (mode == 0) {
            System.out.println("1-Draw pattern manually  2-Load built in Pattern");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String choice = myObj.nextLine();
            if (Objects.equals(choice, "2")) {
                com.mygroup.ui.Helper.write_argument(20, "input1");
                com.mygroup.ui.Helper.write_argument(20, "input2");
                mainmenu.get_BL().ConstructBoard();
                printinitialBoard(Helper.O_to_Int());
            } else if (Objects.equals(choice, "1")) {
                com.mygroup.ui.Helper.write_argument(20, "input1");
                com.mygroup.ui.Helper.write_argument(20, "input2");
                mainmenu.get_BL().ConstructBoard();
                mainmenu.get_BL().Clear();
                Draw();
            }

        } else {
            mainmenu.get_BL().start();
            printBoard(UI_Console.Helper.O_to_Int());
        }
        boolean flag = true;
        int iterations = 0;
        while (flag) {
            switch (Gamechoice()) {
                case "1":
                    System.out.println("How many Iterations you want to run?");
                    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                    iterations = myObj.nextInt();
                    break;
                case "3":
                    iterations = 0;
                    flag = false;
                    break;
                case "2":
                    iterations = 0;
                    System.out.println("Enter State Name:");
                    Scanner nameobj = new Scanner(System.in);  // Create a Scanner object
                    String name = nameobj.nextLine();
                    Helper.write_argument(name, "input1");
                    mainmenu.get_BL().save();
                case "4":
                    iterations = 0;
                    setspeed();
                    break;
                default:
                    break;
            }

            for (int i = 0; i < iterations; i++) {
                sleep(1000);
                mainmenu.get_BL().updateBoard();
                mainmenu.get_BL().get_Alive();
                ArrayList<Integer> cells = Helper.O_to_Int();
                printBoard(cells);
            }
        }
    }


}
