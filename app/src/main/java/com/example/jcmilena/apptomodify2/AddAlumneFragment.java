package com.example.jcmilena.apptomodify2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddAlumneFragment extends Fragment {

    EditText nom, cognom, curs, telefon;
    Button afegir;

    private AddAlumneListener mListener;

    public AddAlumneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_alumne, container, false);

        nom = view.findViewById(R.id.entrada_nom);
        cognom = view.findViewById(R.id.entrada_cognom);
        curs = view.findViewById(R.id.entrada_curs);
        telefon = view.findViewById(R.id.entrada_telefon);
        afegir = view.findViewById(R.id.boto_add_alumne);

        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumne alumne = new Alumne(nom.getText().toString(),
                        cognom.getText().toString(),
                        curs.getText().toString(),
                        telefon.getText().toString());

                mListener.add_alumne_BBDD(alumne);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddAlumneListener) {
            mListener = (AddAlumneListener) context;
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
    public interface AddAlumneListener {
        // TODO: Update argument type and name
        void add_alumne_BBDD(Alumne alumne);
    }
}
