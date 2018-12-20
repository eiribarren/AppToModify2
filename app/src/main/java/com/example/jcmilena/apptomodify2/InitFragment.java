package com.example.jcmilena.apptomodify2;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InitFragment extends Fragment {

    String nom;


    public InitFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Llegir SharedPreferences
        nom = llegir_SharedPreferences();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_init, container, false);
        TextView salutacio = view.findViewById(R.id.init_text);
        salutacio.setText("Hola "+ nom + " has d'utilitzar la Toolbar per continuar.");


        return view;
    }


}
