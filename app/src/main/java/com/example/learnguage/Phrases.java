package com.example.learnguage;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends Fragment {
    private MediaPlayer player;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                player.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                player.pause();
                player.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releasePlayer(player);
            }
        }
    };

    private void releasePlayer(MediaPlayer player) {
        if (player != null){
            player.release();
            player = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener(){

        @Override
        public void onCompletion(MediaPlayer player) {
            releasePlayer(player);
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.phrases,container,false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<word> word_list = new ArrayList<word>();

        word_list.add(new word("Hello / Hi",R.drawable.ic_launcher_background,"Namaste / Namaskar",R.raw.p1));
        word_list.add(new word("How are you? (formal)",R.drawable.ic_launcher_background,"Aap kaise hai?",R.raw.p2));
        word_list.add(new word("How are you? (informal)",R.drawable.ic_launcher_background,"Sab theek? / kya haal hai?",R.raw.p3));
        word_list.add(new word("I’m fine, you?",R.drawable.ic_launcher_background,"Main theek hoon. Tum?",R.raw.p4));
        word_list.add(new word("Please",R.drawable.ic_launcher_background,"Kripya",R.raw.p5));
        word_list.add(new word("Thank you",R.drawable.ic_launcher_background,"Dhanyavad / Shukriya",R.raw.p6));
        word_list.add(new word("Excuse me",R.drawable.ic_launcher_background,"Suniye",R.raw.p7));
        word_list.add(new word("Sorry",R.drawable.ic_launcher_background,"Maaf kijiye",R.raw.p8));
        word_list.add(new word("Bye / See you",R.drawable.ic_launcher_background,"Phir milenge",R.raw.p9));
        word_list.add(new word("See you tomorrow",R.drawable.ic_launcher_background,"Kal milenge",R.raw.p10));
        word_list.add(new word("Yes",R.drawable.ic_launcher_background,"Haan",R.raw.p11));
        word_list.add(new word("No",R.drawable.ic_launcher_background,"Nahi",R.raw.p12));
        word_list.add(new word("Good",R.drawable.ic_launcher_background,"Accha",R.raw.p13));
        word_list.add(new word("Bad",R.drawable.ic_launcher_background,"Bura",R.raw.p14));
        word_list.add(new word("Okay",R.drawable.ic_launcher_background,"Theek hai",R.raw.p15));

        AdapterClass adapter = new AdapterClass(getActivity(),word_list);
        adapter.number = 3;
        ListView listView = (ListView) view.findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                word pos = word_list.get(position);

                releasePlayer(player);

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    player = MediaPlayer.create(getContext(),pos.getAudio());
                    player.start();
                    player.setOnCompletionListener(onCompletionListener);
                }
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer(player);
    }
}
