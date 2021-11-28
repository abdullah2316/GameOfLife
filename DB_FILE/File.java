import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Date;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import static java.lang.String.valueOf;

public class DB_Filing {
    private String filename;
    private Integer filecounter;
    private ArrayList<String> FileList;
    DB_Filing()
    {
        filename = new String("State");
        filecounter = 1;
        FileList = new ArrayList<String>();
    }

    //@Override
    public void save_into_file(String Name, int Generation, ArrayList<Integer> array) throws SQLException {
        {
            String n = new String(filename + filecounter.toString() + ".txt");
            FileList.add(n);
            int len =  array.size() - 1;
            try {
                File myObj = new File(n);
                FileWriter objmyWriter = new FileWriter(n);
                objmyWriter.write(valueOf(array.size()/2));
                objmyWriter.write("\n");
                for (int i = 0; i < len ; i++)
                {
                    objmyWriter.write(valueOf(array.get(i)));
                    objmyWriter.write(valueOf(array.get(i+1)));
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
    
    public  void Load(ArrayList<StringBuilder> info, ArrayList<Integer> cells) throws FileNotFoundException {
        int total_states = FileList.size();
        cells.add(total_states);
        String n = new String();
        for (int i = 0; i < FileList.size(); i++)
        {
            n = FileList.get(i);
            File myFile = new File (n);
            Scanner scan = new Scanner(myFile);
            Integer iterations = Integer.parseInt(scan.nextLine());
            cells.add(iterations);
            int j = 0;
            while (j < iterations)
            {
                Integer number = Integer.parseInt(scan.nextLine());
                Integer col = number % 10;
                number = number / 10;
                Integer row = number;
                cells.add(row);
                cells.add(col);
                j++;
            }
            info.add(new StringBuilder(scan.nextLine()));
            info.add(new StringBuilder(scan.nextLine()));
            info.add(new StringBuilder(scan.nextLine()));
            info.add(new StringBuilder(n));
            info.add(new StringBuilder(scan.nextLine()));
        }

    }
}
