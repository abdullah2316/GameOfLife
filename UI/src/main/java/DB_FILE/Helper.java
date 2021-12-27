package DB_FILE;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class Helper {
    public static void deleteFile(String name) {
        File myObj = new File(name);
        if (!myObj.delete()) {
            System.out.println("Failed to delete the file." + myObj.getName());
        }
    }

    public static String Arguments_to_string(String File) {
        String out = "";
        Gson gson = new Gson();
        try (Reader reader = new FileReader("\\AllModules\\DataExchange\\" + File + ".json")) {
            out = gson.fromJson(reader, String.class);
            reader.close();
            deleteFile("\\AllModules\\DataExchange\\" + File + ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static <T> void write_argument(T arg, String File) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("\\AllModules\\DataExchange\\" + File + ".json")) {
            gson.toJson(arg, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> Arguments_to_AL(String File) {
        ArrayList<Integer> out = new ArrayList<>();
        Gson gson = new Gson();
        try (Reader reader = new FileReader("\\AllModules\\DataExchange\\" + File + ".json")) {
            out = gson.fromJson(reader, new TypeToken<ArrayList<Integer>>() {
            }.getType());
            reader.close();
            deleteFile("\\AllModules\\DataExchange\\" + File + ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }


    public static int Arguments_to_int(String File) {
        int out = 0;
        Gson gson = new Gson();
        try (Reader reader = new FileReader("\\AllModules\\DataExchange\\" + File + ".json")) {
            out = gson.fromJson(reader, int.class);
            reader.close();
            deleteFile("\\AllModules\\DataExchange\\" + File + ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

}
