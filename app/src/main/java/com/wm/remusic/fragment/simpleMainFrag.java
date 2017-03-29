package com.wm.remusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wm.remusic.R;

/**
 * Created by mac-yk on 2017/3/29.
 */

public class simpleMainFrag extends Fragment {
    public simpleMainFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_main,container, false);
        return view;
    }

}
