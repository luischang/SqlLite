package com.ejemplo.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lchang on 25/09/17.
 */

public class DatosSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    private String sqlCreate = "CREATE TABLE Producto (codigo INTEGER, nombre TEXT, stock INTEGER)";

    public DatosSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        Log.i("onCreate","CREA TABLA");
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente
        //      la opción de eliminar la tabla anterior y crearla de nuevo
        //      vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la
        //      tabla antigua a la nueva, por lo que este método debería
        //      ser más elaborado.

        //Se elimina la versión anterior de la tabla
        Log.i("onUpgrade","CABMIO LA BASE DE DATOS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Producto");

        //Se crea la nueva versión de la tabla
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
