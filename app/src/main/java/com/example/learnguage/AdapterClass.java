package com.example.learnguage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class AdapterClass extends ArrayAdapter<word> {

    public int number;
    public AdapterClass(Activity context, ArrayList<word> objects) { super(context, 0, objects); }

    public View getView(int position,View convertView, ViewGroup parent){
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_view,parent,false);
        }

        word current = getItem(position);

        TextView num = (TextView) listView.findViewById(R.id.num);
        ImageView imageView = (ImageView) listView.findViewById(R.id.image_disp);
        RelativeLayout layout = (RelativeLayout) listView.findViewById(R.id.canva);
        if (number == 0){
            imageView.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);

            int x = position + 1;
            String str = "| ";
            for (int i = 2;i<=x;i++){
                str = str + "| ";
            }
            num.setText(str);
        }
        else if (number == 1 || number == 3){
            num.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);

            imageView.setImageResource(current.getImage());
        }
        else if (number == 2){
            imageView.setVisibility(View.GONE);
            num.setVisibility(View.GONE);

            MyCanvas canvas = new MyCanvas(getContext());
            canvas.getColor(current.getA(),current.getB(),current.getC());
            layout.addView(canvas);
        }

        TextView eng = (TextView) listView.findViewById(R.id.text1);
        eng.setText(current.getDefaultWord());
        
        TextView hindi = (TextView) listView.findViewById(R.id.text2);
        hindi.setText(current.getHindWord());

        return listView;
    }
}
