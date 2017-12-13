package com.example.huanpet.view.pet.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huanpet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class catFragment extends Fragment {


    public catFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cat, container, false);
        return view;
    }

}
