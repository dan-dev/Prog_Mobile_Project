package com.example.danny.prog_mobile_project;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class SeriesParser extends AsyncTask<String, Void, String>{

    int id;

    @Override
    protected String doInBackground(String... strings) {
        String str = "";
        URL url = null;
        int i;
        try {
            Scanner scan;
            if (isNumber(strings[0])){
                url = new URL("http://api.tvmaze.com/shows/" + id);
            }
            else {
                url = new URL("http://api.tvmaze.com/search/shows?q=" + strings[0]);
            }
            scan = new Scanner(url.openStream());
            while (scan.hasNext()){
                str += scan.nextLine();
            }
            scan.close();
            return str;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isNumber(String string){
        try {
            id = Integer.parseInt(string);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
