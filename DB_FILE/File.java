package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.FileReader;
import static java.lang.String.valueOf;
import java.time.LocalDateTime;
import static java.lang.String.valueOf;

//public class DB_Filing  implements DBduties{  //u would use this// and then also use @override
public class DB_Filing {
   // @Override
    public int[] load_from_file(int Save, String document ) throws SQLException, IOException {
        int array[]=new int[1000];

        FileReader fr=new FileReader("D:\\document.txt");
        int i;
        while((i=fr.read())!=-1) {
            //System.out.print((char) i);
            array[i]=(char)i;
            array[i+1]=(char)i;
        }
        fr.close();

        return new int[0];
        //return array;
    }

    //@Override
   public void save_into_file(String Name, int Generation, ArrayList<Integer> array) throws SQLException {
        {
            int len =  array.size() - 1;
            try {
                File myObj = new File("document.txt");
                FileWriter objmyWriter = new FileWriter("document.txt", true);
                objmyWriter.write(Name);
                objmyWriter.write("\n");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                objmyWriter.write(dtf.format(now));
                objmyWriter.write("\n");
                objmyWriter.write(valueOf(Generation));
                objmyWriter.write("\n");
                for (int i =0; i< len;i++){
                        objmyWriter.write(valueOf(array.get(i)));
                        objmyWriter.write(valueOf(array.get(i+1)));
                        objmyWriter.write("\n");
                    }
                    objmyWriter.write("\n");

                objmyWriter.close();
                } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// public class DB_Filing implements DB_interface{
//
// }

