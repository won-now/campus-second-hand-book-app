package demo.com.campussecondbookrecycle.Utils;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOStreamHandler {

    public static String JsontoString(Context context, String filename){
        StringBuilder stringBuilder = new StringBuilder();
        InputStream in = null;
        InputStreamReader inReader = null;
        BufferedReader bufferedReader = null;
        try {
            in = context.getAssets().open(filename);
            inReader = new InputStreamReader(in);
            bufferedReader = new BufferedReader(inReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader != null)   bufferedReader.close();
                if(inReader != null)    inReader.close();
                if(in!= null)   in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
