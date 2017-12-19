package com.example.huanpet.view.pet.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.huanpet.R;
import com.example.huanpet.view.pet.PetAddActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class dogFragment extends Fragment {


    private LinearLayout dog;
    private RecyclerView rv;
    private WaveSideBar sideBar;

    public dogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dog, container, false);
        initView(view);

        return view;

    }

    private void initView(View view) {
        dog = (LinearLayout) view.findViewById(R.id.dog);


       // sideBar = (WaveSideBar) view.findViewById(R.id.sideBar);

        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), PetAddActivity.class);


                startActivity(intent);
            }


        });



    }

}
