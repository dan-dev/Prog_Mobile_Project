package com.example.danny.prog_mobile_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_serie, container, false);

        titleText = (TextView) view.findViewById(R.id.text_title);
        descripText = (TextView) view.findViewById(R.id.text_description);

        String titleStr = getArguments().getString("title");
        String descripStr = getArguments().getString("description");

        titleText.setText(titleStr);
        descripText.setText(descripStr);

        return view;
    }
}
