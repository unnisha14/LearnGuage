package com.example.learnguage;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public int getCount() {
        return 4;
    }


    public Fragment getItem(int i) {
        switch (i){
            case 0: return new Numbers();
            case 1: return new FamilyMembers();
            case 2: return new Colours();
            case 3: return new Phrases();
        };
        return null;
    }

    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return ("Numbers");
            case 1: return ("Family");
            case 2: return ("Colors");
            case 3: return ("Phrases");
        };
        return null;
    }
}
