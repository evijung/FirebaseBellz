package com.th.samsen.tunyaporn.firebasebellz.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.th.samsen.tunyaporn.firebasebellz.MainActivity;
import com.th.samsen.tunyaporn.firebasebellz.R;

/**
 * Created by TunyapornSamsen on 11/25/2017 AD.
 */

public class RegisterFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();

//        Create Menu Icon
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_save,menu);

        super.onCreateOptionsMenu(menu, inflater);


    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.tbrRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

//        Set Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.new_register);

//        Back Icon
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
