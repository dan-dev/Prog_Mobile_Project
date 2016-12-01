package com.example.danny.prog_mobile_project;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {


    public ResultsFragment() {
        // Required empty public constructor
    }

    View view;
    TextView textView;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_results, container, false);

        textView = (TextView) view.findViewById(R.id.text_args);
        String str = getArguments().getString("search");
        listView = (ListView) view.findViewById(R.id.listView_results);

        try {
            //JSONObject obj = new JSONObject(new SeriesParser().execute(str).get());

            final JSONArray jsonArray = new JSONArray(new SeriesParser().execute(str).get());

            //textView.setText(jsonArray.toString());

            ArrayList<String> strings = new ArrayList<String>();

            for (int i = 0; i < jsonArray.length() ; i++){
                //Log.d("serie id: ", jsonArray.getJSONObject(i).getJSONObject("show").getString("name"));
                strings.add(jsonArray.getJSONObject(i).getJSONObject("show").getString("name"));
                //Log.d("serie: ", obj.getJSONObject("show").getString("name"));

                //strings.add(jsonArray.getJSONObject(i).getString("show"));
                //Log.d("serie id: ", jsonArray.getJSONObject(i).getString("score"));
            }

            String[] arr = new String[strings.size()];
            arr = strings.toArray(arr);

            listView.setAdapter(new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, arr));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        Bundle bundle = new Bundle();
                        int index = (int) listView.getSelectedItemId();
                        bundle.putString("title", jsonArray.getJSONObject(index).getJSONObject("show").getString("name"));
                        bundle.putString("description", jsonArray.getJSONObject(index).getJSONObject("show").getString("summary"));
                        SerieFragment fragment = new SerieFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            //listView.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, arr));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return view;
    }
}
