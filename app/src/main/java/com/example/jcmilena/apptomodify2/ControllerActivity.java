package com.example.jcmilena.apptomodify2;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ControllerActivity extends AppCompatActivity implements NameDialog.NameDialogListener, SearchAlumneFragment.SearchAlumneListener, AddAlumneFragment.AddAlumneListener, LlistaAlumnesFragment.LListaAlumnesListener {


    Toolbar miToolbar;

    List<Alumne> alumneList = new ArrayList<>();
    MyBBDD_Helper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        dbhelper = new MyBBDD_Helper(this);

        miToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(miToolbar);

        DialogFragment dialogFragment = new NameDialog();
        dialogFragment.show(getSupportFragmentManager(),"NameDialog");
    }

    @Override
    public void guardar_nom_SharedPreferences(String nom) {
        //Guardar nom en SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nom", nom).commit();
        cargar_fragment(new InitFragment());
    }

    private void cargar_fragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment fragment;

        switch (item.getItemId()){

            case R.id.add_alumne:
                //fragment add alumne
                fragment = new AddAlumneFragment();
                cargar_fragment(fragment);
                return true;
            case R.id.search_alumne:
                //fragment cerca alumnes
                fragment = new SearchAlumneFragment();
                cargar_fragment(fragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }



    @Override
    public List<Alumne> carregar_alumnes() {
        return alumneList;
    }

    @Override
    public void add_alumne_BBDD(Alumne alumne) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyBBDD_Schema.EntradaBBDD.COLUMNA1, alumne.getNom());
        cv.put(MyBBDD_Schema.EntradaBBDD.COLUMNA2, alumne.getCognom());
        cv.put(MyBBDD_Schema.EntradaBBDD.COLUMNA3, alumne.getCurs());
        cv.put(MyBBDD_Schema.EntradaBBDD.COLUMNA4, alumne.getTelefon());
        db.insert(MyBBDD_Schema.EntradaBBDD.TABLE_NAME, null, cv);
    }

    @Override
    public void cercar_nom_bbdd(String nom) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        alumneList = new ArrayList<>();
        String[] columnas = { MyBBDD_Schema.EntradaBBDD.COLUMNA1,
                              MyBBDD_Schema.EntradaBBDD.COLUMNA2,
                              MyBBDD_Schema.EntradaBBDD.COLUMNA3,
                              MyBBDD_Schema.EntradaBBDD.COLUMNA4 };
        String[] args = { nom };
        Cursor cursor = db.query( MyBBDD_Schema.EntradaBBDD.TABLE_NAME, columnas, MyBBDD_Schema.EntradaBBDD.COLUMNA1 + "=?", args, null, null, null);
        while ( cursor.moveToNext() ) {
            alumneList.add( new Alumne( cursor.getString(cursor.getColumnIndex(MyBBDD_Schema.EntradaBBDD.COLUMNA1)),
                                        cursor.getString(cursor.getColumnIndex(MyBBDD_Schema.EntradaBBDD.COLUMNA2)),
                                        cursor.getString(cursor.getColumnIndex(MyBBDD_Schema.EntradaBBDD.COLUMNA3)),
                                        cursor.getString(cursor.getColumnIndex(MyBBDD_Schema.EntradaBBDD.COLUMNA4))
                                        )
                            );
        }
        cargar_fragment(new LlistaAlumnesFragment());
    }

}
