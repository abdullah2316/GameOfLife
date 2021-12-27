package BL;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class DataHandler implements Data {


    @Override
    public void Load() throws SQLException, ClassNotFoundException, FileNotFoundException {

        GameLogic.getDB().Load();
        //  info = BL.Helper.Arguments_to_SB("output1");
        //cells = BL.Helper.Arguments_to_AL("output2");
    }

    @Override
    public void deletestate() throws SQLException, ClassNotFoundException {

        GameLogic.getDB().deletestate();
    }


    public void save() throws SQLException, ClassNotFoundException {
        GameLogic.getDB().save();
    }


    public void Load_A_State() throws SQLException, ClassNotFoundException, FileNotFoundException {

        GameLogic.getDB().LoadState();
        Helper.write_output_AL(Helper.O_to_Int());
    }

}
