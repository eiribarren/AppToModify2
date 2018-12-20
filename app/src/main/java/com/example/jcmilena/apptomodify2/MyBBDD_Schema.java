package com.example.jcmilena.apptomodify2;

public class MyBBDD_Schema {

    static final String SQLCreate =
                    "CREATE TABLE "+ EntradaBBDD.TABLE_NAME + " (" +
                    "_ID INTEGER PRIMARY KEY, " +
                    EntradaBBDD.COLUMNA1 + " TEXT, " +
                    EntradaBBDD.COLUMNA2 + " TEXT, " +
                    EntradaBBDD.COLUMNA3 + " TEXT, " +
                    EntradaBBDD.COLUMNA4 + " TEXT)";

    static final String SQLUpgrade =
                    "DROP TABLE IF EXISTS "+ EntradaBBDD.TABLE_NAME;

    private MyBBDD_Schema(){};

    public class EntradaBBDD {

        static final String TABLE_NAME = "ALUMNES";
        static final String COLUMNA1 = "NOM";
        static final String COLUMNA2 = "COGNOM";
        static final String COLUMNA3 = "CURS";
        static final String COLUMNA4 = "TELEFON";

    }

}
