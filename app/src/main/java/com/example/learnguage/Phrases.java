package com.example.learnguage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.phrases,container,false);

        final ArrayList<word> word_list = new ArrayList<word>();

        word_list.add(new word("Hello / Hi",R.drawable.ic_launcher_background,"Namaste / Namaskar"));
        word_list.add(new word("How are you? (formal)",R.drawable.ic_launcher_background,"Aap kaise hai?"));
        word_list.add(new word("How are you? (informal)",R.drawable.ic_launcher_background,"Sab theek? / kya haal hai?"));
        word_list.add(new word("I’m fine, you?",R.drawable.ic_launcher_background,"Main theek hoon. Tum?"));
        word_list.add(new word("Please",R.drawable.ic_launcher_background,"Kripya"));
        word_list.add(new word("Thank you",R.drawable.ic_launcher_background,"Dhanyavad / Shukriya"));
        word_list.add(new word("Excuse me",R.drawable.ic_launcher_background,"Suniye"));
        word_list.add(new word("Sorry",R.drawable.ic_launcher_background,"Maaf kijiye"));
        word_list.add(new word("Bye / See you",R.drawable.ic_launcher_background,"Phir milenge"));
        word_list.add(new word("See you tomorrow",R.drawable.ic_launcher_background,"Kal milenge"));
        word_list.add(new word("Yes",R.drawable.ic_launcher_background,"Haan"));
        word_list.add(new word("No",R.drawable.ic_launcher_background,"Nahi"));
        word_list.add(new word("Good",R.drawable.ic_launcher_background,"Accha"));
        word_list.add(new word("Bad",R.drawable.ic_launcher_background,"Bura"));
        word_list.add(new word("Okay",R.drawable.ic_launcher_background,"Theek hai"));

        AdapterClass adapter = new AdapterClass(getActivity(),word_list);
        adapter.number = 3;
        ListView listView = (ListView) view.findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        return view;
    }
}
