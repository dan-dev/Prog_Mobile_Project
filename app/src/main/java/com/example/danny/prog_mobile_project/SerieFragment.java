package com.example.danny.prog_mobile_project;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

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
    Button addButton;

    String titleStr;

    int id;
    private DataBaseBuilder db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_serie, container, false);

        db = new DataBaseBuilder(getActivity());
        db.getWritableDatabase();

        titleText = (TextView) view.findViewById(R.id.text_title);
        descripText = (TextView) view.findViewById(R.id.text_description);
        imageView = (ImageView) view.findViewById(R.id.image);
        genreText = (TextView) view.findViewById(R.id.text_genre);
        statusText = (TextView) view.findViewById(R.id.text_status);
        scoreText = (TextView) view.findViewById(R.id.text_score);
        addButton = (Button) view.findViewById(R.id.button_add);

        id = getArguments().getInt("id");
        titleStr = getArguments().getString("title");
        String descripStr = getArguments().getString("description");
        String image = getArguments().getString("image");
        String genre = getArguments().getString("genre");
        String status = getArguments().getString("status");
        String score = getArguments().getString("score");

        try {
            imageView.setImageBitmap(new ImageHandler().execute(image).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        titleText.setText(titleStr);
        descripText.setText("Description:\n" + Html.fromHtml(descripStr));
        genreText.setText("Genre: " + genre);
        statusText.setText("Status: " + status);
        scoreText.setText("Score: " + score);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateDialog();
            }
        });
        return view;
    }

    public Dialog CreateDialog(){
        final String[] str = {"Completed", "Watching", "Plan to Watch"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose an option:");
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addToDB(str[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
        return builder.create();
    }

    public void addToDB(String s){
        db.addSerie(new Serie(id, s, titleStr));
        Toast.makeText(getContext(), "Show is now marked as: " + s, Toast.LENGTH_SHORT).show();
    }
}