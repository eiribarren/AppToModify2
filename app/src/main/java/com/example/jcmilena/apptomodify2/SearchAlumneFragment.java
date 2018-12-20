package com.example.jcmilena.apptomodify2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class SearchAlumneFragment extends Fragment {

    EditText nom;
    ImageButton cercar;

    private SearchAlumneListener mListener;

    public SearchAlumneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_alumne, container, false);
        nom = view.findViewById(R.id.nom_cercar);
        cercar = view.findViewById(R.id.boto_cercar);
        cercar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SQLite", "Nom a cercar = "+ nom.getText().toString());
                mListener.cercar_nom_bbdd(nom.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchAlumneListener) {
            mListener = (SearchAlumneListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface SearchAlumneListener {
        // TODO: Update argument type and name
        void cercar_nom_bbdd(String nom);
    }
}
