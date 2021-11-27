package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.FileReader;
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
    public void save_into_file(ArrayList<Integer> array) throws SQLException {
        int len =  array.size();
        try {
            File myObj = new File("document.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

                FileWriter objmyWriter = new FileWriter("document.txt");
                //objmyWriter.write(getcount(size));  //writes the no. of saves
                for (int i =0; i< len;i++){
                    //objmyWriter.write(valueOf(array.get(i)) + valueOf(array.get(i+1)));
                    objmyWriter.write(array.get(i) + array.get(i+1));
                    System.out.println("\r\n");
                }
                objmyWriter.close();

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

// public class DB_Filing implements DB_interface{
//
// }

