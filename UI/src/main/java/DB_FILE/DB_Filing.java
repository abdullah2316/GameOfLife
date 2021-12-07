package DB_FILE;

import BL.DBduties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class DB_Filing implements DBduties {
    private String filename;
    private Integer filecounter;
    private ArrayList<String> FileList;

    public DB_Filing() {
        filename = new String("State");
        filecounter = 1;
        FileList = new ArrayList<String>();
    }

    //@Override
    public void save(String Name, int Generation, ArrayList<Integer> array) throws SQLException {
        {
            String n = filename + filecounter.toString() + ".txt";
            FileList.add(n);
            int len = array.size() - 1;
            try {
                File myObj = new File(n);
                FileWriter objmyWriter = new FileWriter(n);
                objmyWriter.write(valueOf(array.size() / 2));
                objmyWriter.write("\n");
                for (int i = 0; i < len; i++) {
                    objmyWriter.write(valueOf(array.get(i)));
                    objmyWriter.write(" ");
                    objmyWriter.write(valueOf(array.get(i + 1)));
                    objmyWriter.write("\n");
                    i++;
                }
                objmyWriter.write(Name);
                objmyWriter.write("\n");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                java.util.Date date = new Date();
                String datetime = formatter.format(date);
                String datetoday = datetime.substring(0, datetime.indexOf(' '));
                String time = datetime.substring(datetime.indexOf(' ') + 1);
                objmyWriter.write(datetoday);
                objmyWriter.write("\n");
                objmyWriter.write(time);
                objmyWriter.write("\n");
                objmyWriter.write(valueOf(Generation));
                objmyWriter.write("\n");
                objmyWriter.close();
                filecounter++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Load(ArrayList<StringBuilder> info, ArrayList<Integer> cells) throws FileNotFoundException {
        int total_states = FileList.size();
        cells.add(total_states);
        String n;
        for (String s : FileList) {
            n = s;
            File myFile = new File(n);
            Scanner scan = new Scanner(myFile);
            int iterations = Integer.parseInt(scan.nextLine());
            cells.add(iterations);
            int j = 0;
            while (j < iterations) {
                int number = scan.nextInt();
                cells.add(number);
                number = scan.nextInt();
                cells.add(number);
                j++;
            }
            scan.nextLine();
            info.add(new StringBuilder(scan.nextLine()));
            info.add(new StringBuilder(scan.nextLine()));
            info.add(new StringBuilder(scan.nextLine()));
            info.add(new StringBuilder(scan.nextLine()));
            info.add(new StringBuilder(n));//id
            scan.close();
        }


    }

    public void deletestate(String id) {
        File myObj = new File(id);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
            FileList.remove(id);
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public ArrayList<Integer> LoadState(String id) throws SQLException, FileNotFoundException {
        ArrayList<Integer> load = new ArrayList<>();
        File myFile = new File(id);
        int size = 0;
        Scanner scan = new Scanner(myFile);
        size = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < size; i++) {
            int number = scan.nextInt();
            load.add(number);
            number = scan.nextInt();
            load.add(number);
        }
        return load;
    }

}
