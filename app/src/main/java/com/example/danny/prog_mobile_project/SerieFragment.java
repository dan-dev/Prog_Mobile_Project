package com.example.danny.prog_mobile_project;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class SerieFragment extends Fragment {


    public SerieFragment() {
        // Required empty public constructor
    }
    View view;
    TextView titleText;
    TextView descripText;
    TextView genreText;
    TextView statusText;
    TextView scoreText;

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_serie, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        titleText = (TextView) view.findViewById(R.id.text_title);
        descripText = (TextView) view.findViewById(R.id.text_description);
        imageView = (ImageView) view.findViewById(R.id.image);
        genreText = (TextView) view.findViewById(R.id.text_genre);
        statusText = (TextView) view.findViewById(R.id.text_status);
        scoreText = (TextView) view.findViewById(R.id.text_score);

        String titleStr = getArguments().getString("title");
        String descripStr = getArguments().getString("description");
        String image = getArguments().getString("image");
        String genre = getArguments().getString("genre");
        String status = getArguments().getString("status");
        String score = getArguments().getString("score");

        titleText.setText(titleStr);

        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(image).getContent());
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //descripText.setText(descripStr);
        descripText.setText("Description:\n" + descripStr);
        genreText.setText("Genre: " + genre);
        statusText.setText("Status: " + status);
        scoreText.setText("Score: " + score);

        return view;
    }
}
