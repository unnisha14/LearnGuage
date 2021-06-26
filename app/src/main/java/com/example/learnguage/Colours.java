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

public class Colours extends Fragment {
    private MediaPlayer player;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS)  releasePlayer(player);
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                player.pause();
                player.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)  player.start();
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
        public void onCompletion(MediaPlayer mp) {
            releasePlayer(mp);
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.colours,container, false);

        final ArrayList<word> word_list = new ArrayList<word>();

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        word_list.add(new word("Black",0,0,0,"Kaala",R.raw.c1));
        word_list.add(new word("Red",249,17,7,"Laal",R.raw.c2));
        word_list.add(new word("Yellow",247, 249, 7,"Peela",R.raw.c3));
        word_list.add(new word("Green",49, 249, 7,"Hara",R.raw.c4));
        word_list.add(new word("Orange",249, 155, 7,"Narangi",R.raw.c5));
        word_list.add(new word("Blue",7, 168, 249,"Neela",R.raw.c6));
        word_list.add(new word("Pink",245, 117, 160 ,"Gulaabi",R.raw.c7));
        word_list.add(new word("Brown ",114, 57, 5 ,"Bhura",R.raw.c8));
        word_list.add(new word("Violet",230, 48, 238 ,"Baingani",R.raw.c9));

        AdapterClass adapter = new AdapterClass(getActivity(),word_list);
        adapter.number = 2;
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
