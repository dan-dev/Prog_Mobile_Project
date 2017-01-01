package com.example.danny.prog_mobile_project;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {


    public FavouritesFragment() {
        // Required empty public constructor
    }

    View view;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_favourites, container, false);

        textView = (TextView) view.findViewById(R.id.favourite);

        DataBaseBuilder db = new DataBaseBuilder(getActivity());

        /*if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                String data = cursor.getString();
            }
        }*/

        /*textView.setText(cursor.getString(cursor.getColumnIndex("serie")
                + cursor.getColumnIndex("state") + cursor.getColumnIndex("count")));*/
        return view;
    }

}
