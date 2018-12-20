package com.example.jcmilena.apptomodify2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LlistaAlumnesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<Alumne> alumnes = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    private LListaAlumnesListener mListener;

    public LlistaAlumnesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alumnes= mListener.carregar_alumnes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_llista_alumnes, container, false);
        recyclerView = view.findViewById(R.id.recycler);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AlumnesAdapter(alumnes);
        recyclerView.setAdapter(adapter);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LListaAlumnesListener) {
            mListener = (LListaAlumnesListener) context;
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

    public interface LListaAlumnesListener {
        // TODO: Update argument type and name
        List<Alumne> carregar_alumnes();
    }

    public class AlumnesAdapter extends RecyclerView.Adapter<AlumnesAdapter.AlumnesViewHolder> {

        List<Alumne> llistatAlumnes;

        public AlumnesAdapter (List<Alumne> llista){
            llistatAlumnes = llista;
        }

        @NonNull
        @Override
        public AlumnesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.viewholder_llistat_alumnes, viewGroup, false);

            return new AlumnesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AlumnesViewHolder alumnesViewHolder, int i) {

            alumnesViewHolder.alumneinfo.setText(llistatAlumnes.get(i).getNom() +
            " "+ llistatAlumnes.get(i).getCognom());
            alumnesViewHolder.curs.setText("CURS: "+ llistatAlumnes.get(i).getCurs());
            alumnesViewHolder.telefon.setText("Telèfon: "+ llistatAlumnes.get(i).getTelefon());
        }

        @Override
        public int getItemCount() {
            return llistatAlumnes.size();
        }

        public class AlumnesViewHolder extends RecyclerView.ViewHolder{

            TextView alumneinfo, curs, telefon;

            public AlumnesViewHolder(@NonNull View itemView) {
                super(itemView);
                alumneinfo = itemView.findViewById(R.id.vw_alumne);
                curs = itemView.findViewById(R.id.vw_curs);
                telefon = itemView.findViewById(R.id.vw_telefon);
            }
        }
    }
}
