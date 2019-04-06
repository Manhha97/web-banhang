package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertUtil {
    public static  <T> T convert(BufferedReader bufferedReader, Class<T> tClass){
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(bufferedReader);
        Gson gson = new Gson();
        T object = gson.fromJson(obj.toString(), tClass);
        return object;
    }
    public static String randomDateNow(){
        DateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        return sdf.format(new Date());
    }
    public static String createProductCode(Integer id){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        return "SP"+sdf.format(new Date())+String.format("%03d", (id+1));
    }
    public static String createUsercode(int role, int id) {
        if (role==1) {
            return "AD"+String.format("%03d", (id+1));
        } else if(role==2) {
            return "EM"+String.format("%03d", (id+1));
        }else {
            return "BO"+String.format("%03d", (id+1));
        }
    }
}
