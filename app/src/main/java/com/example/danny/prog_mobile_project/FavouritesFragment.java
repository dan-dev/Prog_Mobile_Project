package com.example.danny.prog_mobile_project;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FavouritesFragment extends Fragment {

    View view;
    ListView listView;

    private DataBaseBuilder db;
    private JSONObject jsonObject;
    private ArrayList<String> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_favourites, container, false);

        listView = (ListView) view.findViewById(R.id.listView_favourites);

        db = new DataBaseBuilder(getActivity());
        db.getWritableDatabase();

        final ArrayList<Serie> list = db.getSeries();

        arrayList = new ArrayList<>();

        for (int i = 0; i < list.size() ; i++){
            arrayList.add("Name: " + list.get(i).getName() + " | State: " + list.get(i).getState());
        }

        listView.setAdapter(new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, arrayList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = "" + list.get(i).getId();
                Log.d("--- id", s + " name: " + list.get(i).getName());
                try {
                    jsonObject = new JSONObject(new SeriesParser().execute(s).get());

                    Bundle bundle = new Bundle();
                    bundle.putInt("id", Integer.parseInt(jsonObject.getString("id")));
                    bundle.putString("title", jsonObject.getString("name"));
                    bundle.putString("description", jsonObject.getString("summary"));
                    bundle.putString("image", jsonObject.getJSONObject("image").getString("medium"));
                    bundle.putString("status", jsonObject.getString("status"));
                    bundle.putString("score", jsonObject.getJSONObject("rating").getString("average"));
                    bundle.putString("genre", jsonObject.getJSONArray("genres").toString().replace("\",\"", ", ").replace("[\"","").replace("\"]",""));
                    SerieFragment fragment = new SerieFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}