package com.example.learnguage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Colours extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.colours,container, false);

        final ArrayList<word> word_list = new ArrayList<word>();

        word_list.add(new word("Black",0,0,0,"Kaala"));
        word_list.add(new word("Red",249,17,7,"Laal"));
        word_list.add(new word("Yellow",247, 249, 7,"Peela"));
        word_list.add(new word("Green",49, 249, 7,"Hara"));
        word_list.add(new word("Orange",249, 155, 7,"Narangi"));
        word_list.add(new word("Blue",7, 168, 249,"Neela"));
        word_list.add(new word("Pink",245, 117, 160 ,"Gulaabi"));
        word_list.add(new word("Brown ",114, 57, 5 ,"Bhura"));
        word_list.add(new word("Violet",230, 48, 238 ,"Baingani"));

        AdapterClass adapter = new AdapterClass(getActivity(),word_list);
        adapter.number = 2;
        ListView listView = (ListView) view.findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        return view;
    }
}
