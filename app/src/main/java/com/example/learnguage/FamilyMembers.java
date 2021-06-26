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

public class FamilyMembers extends Fragment {
    private MediaPlayer player;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                player.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                player.pause();
                player.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releasePlayer(player);
            }
        }
    };

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener(){

        @Override
        public void onCompletion(MediaPlayer player) {
            releasePlayer(player);
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.family,container,false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<word> word_list = new ArrayList<word>();

        word_list.add(new word("Father",R.drawable.ic_launcher_background,"Pita Ji / Papa",R.raw.father));
        word_list.add(new word("Mother",R.drawable.ic_launcher_background,"Mata Ji / Mummy",R.raw.mother));
        word_list.add(new word("Son",R.drawable.ic_launcher_background,"Beta",R.raw.son));
        word_list.add(new word("Daughter ",R.drawable.ic_launcher_background,"Beti",R.raw.daughter));
        word_list.add(new word("Paternal Grand Father",R.drawable.ic_launcher_background,"Dada Ji",R.raw.dada));
        word_list.add(new word("Paternal Grand Mother",R.drawable.ic_launcher_background,"Dadi Ji",R.raw.dadi));
        word_list.add(new word("Maternal Grand Father",R.drawable.ic_launcher_background,"Nana Ji",R.raw.nana));
        word_list.add(new word("Maternal Grand Mother",R.drawable.ic_launcher_background,"Nani Ji",R.raw.nani));
        word_list.add(new word("Younger Sister",R.drawable.ic_launcher_background,"Bahen / Choti Bahen",R.raw.ysis));
        word_list.add(new word("Elder Sister",R.drawable.ic_launcher_background,"Didi / Badi Bahen",R.raw.esis));
        word_list.add(new word("Younger Brother",R.drawable.ic_launcher_background,"Bhai / Chota Bhai",R.raw.ybro));
        word_list.add(new word("Elder Brother",R.drawable.ic_launcher_background,"Bhaiya / Bada Bhai",R.raw.ebro));

        AdapterClass adapter = new AdapterClass(getActivity(),word_list);
        adapter.number = 1;
        ListView list = (ListView) view.findViewById(R.id.list_item);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                word pos = word_list.get(position);

                releasePlayer(player);

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == 1){
                    player = MediaPlayer.create(getContext(),pos.getAudio());
                    player.start();
                    player.setOnCompletionListener(onCompletionListener);
                }
            }
        });

        return view;
    }

    private void releasePlayer(MediaPlayer player) {
        if (player != null){
            player.release();
            player = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer(player);
    }
}
