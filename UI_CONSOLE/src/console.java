/*import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class console extends JFrame {
    public static void main(String[] args) {

        Frame frame = new Frame("GAME");

        frame.setBackground(new Color(117, 210, 247));
        frame.setSize(800,550);
        frame.setLayout(null); // Set cancel default layout
        frame.setLocationRelativeTo(null); // Set auto center
        JLabel labelM = new JLabel("John Conway’s");
        labelM.setBounds(40, 35, 200, 28);
        labelM.setFont(new Font("Mistral", Font.BOLD, 20));
        labelM.setForeground(Color.magenta);
        frame.add(labelM);

        JLabel labelN = new JLabel("GamE of LifE");
        labelN.setBounds(28, 55, 200, 32);
        labelN.setFont(new Font("Bockmann Old Style", Font.BOLD, 31));
        labelN.setForeground(Color.blue);
        frame.add(labelN);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            //            Add a window listener to the form to solve the problem of closing the form
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Panel panel = new Panel(); // First panel
        Panel panel1 =new Panel(); //second panel
        Panel panel2= new Panel(); //third panel

        panel.setBounds(0,630,6000,800); // Set panel size and position relative to form
        panel.setBackground(new Color(195, 195, 195)); //Set the background color of the panel
        frame.add(panel); // Add a panel to the form

        panel1.setBounds(0,88,6000,4);
        panel1.setBackground(new Color(6,35,132));
        frame.add(panel1);

        panel2.setBounds(0,90,6000,540);
        panel2.setBackground(new Color(73,73,73));
        frame.add(panel2);

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
        Gameboard object = new board();


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
