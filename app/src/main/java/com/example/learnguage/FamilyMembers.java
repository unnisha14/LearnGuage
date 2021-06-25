package com.example.learnguage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembers extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.family,container,false);

        final ArrayList<word> word_list = new ArrayList<word>();

        word_list.add(new word("Father",R.drawable.ic_launcher_background,"Pita Ji / Papa"));
        word_list.add(new word("Mother",R.drawable.ic_launcher_background,"Mata Ji / Mummy"));
        word_list.add(new word("Son",R.drawable.ic_launcher_background,"Beta"));
        word_list.add(new word("Daughter ",R.drawable.ic_launcher_background,"Beti"));
        word_list.add(new word("Paternal Grand Father",R.drawable.ic_launcher_background,"Dada Ji"));
        word_list.add(new word("Paternal Grand Mother",R.drawable.ic_launcher_background,"Dadi Ji"));
        word_list.add(new word("Maternal Grand Father",R.drawable.ic_launcher_background,"Nana Ji"));
        word_list.add(new word("Maternal Grand Mother",R.drawable.ic_launcher_background,"Nani Ji"));
        word_list.add(new word("Younger Sister",R.drawable.ic_launcher_background,"Bahen / Choti Bahen"));
        word_list.add(new word("Elder Sister",R.drawable.ic_launcher_background,"Didi / Badi Bahen"));
        word_list.add(new word("Younger Brother",R.drawable.ic_launcher_background,"Bhai / Chota Bhai"));
        word_list.add(new word("Elder Brother",R.drawable.ic_launcher_background,"Bhaiya / Bada Bhai"));

        AdapterClass adapter = new AdapterClass(getActivity(),word_list);
        adapter.number = 1;
        ListView list = (ListView) view.findViewById(R.id.list_item);
        list.setAdapter(adapter);

        return view;
    }
}
