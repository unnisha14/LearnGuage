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

public class Numbers extends Fragment {

    private MediaPlayer player;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                player.pause();
                player.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releasePlayer(player);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                player.start();
            }
        }
    };

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {

        public void onCompletion(MediaPlayer player) {
            releasePlayer(player);
        }
    };

    private void releasePlayer(MediaPlayer player) {

        if (player != null){
            player.release();
            player = null;

            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.numbers,container,false);

        final ArrayList<word> word_list = new ArrayList<word>();

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        
        word_list.add(new word("One","Eek (१)",R.raw.one));
        word_list.add(new word("Two","Do (२)",R.raw.two));
        word_list.add(new word("Three","Teen (३)",R.raw.three));
        word_list.add(new word("Four","Char (४)",R.raw.four));
        word_list.add(new word("Five","Panch (५)",R.raw.five));
        word_list.add(new word("Six","Chahh (६)",R.raw.six));
        word_list.add(new word("Seven","Saat (७)",R.raw.seven));
        word_list.add(new word("Eight","Aath (८)",R.raw.eight));
        word_list.add(new word("Nine","Nao (९)",R.raw.nine));
        word_list.add(new word("Ten","Dus (१०)",R.raw.ten));

        AdapterClass item = new AdapterClass(getActivity(),word_list);
        item.number = 0;
        ListView list = (ListView) view.findViewById(R.id.list_item);
        list.setAdapter(item);
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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

    public void onStop() {
        super.onStop();
        releasePlayer(player);
    }
}
