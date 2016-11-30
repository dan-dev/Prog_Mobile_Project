package com.example.danny.prog_mobile_project;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Danny on 30/11/2016.
 */

public class SeriesParser extends AsyncTask<String, Void, String>{

    @Override
    protected String doInBackground(String... strings) {

        String str = "";
        URL url = null;

        try {
            url = new URL("http://api.tvmaze.com/search/shows?q=" + strings[0]);
            Scanner scan = new Scanner(url.openStream());
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
        //return null;
    }
}
