package com.ejemplo.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtNombre;
    private TextView txtStock;

    private ListView ltvValores;

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnConsultar;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Obtenemos las referencias a los controles
        txtCodigo = (EditText)findViewById(R.id.txtCodigo);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtStock = (EditText)findViewById(R.id.txtStock);
        //txtResultado = (TextView)findViewById(R.id.txtResultado);

        btnInsertar = (Button)findViewById(R.id.btnInsertar);
        btnActualizar = (Button)findViewById(R.id.btnActualizar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);
        btnConsultar = (Button)findViewById(R.id.btnConsultar);

        ltvValores = (ListView) findViewById(R.id.listValores);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        final ProductoSQLiteHelper dsdbh =
                new ProductoSQLiteHelper(this, "DBUsuarios", null, 1);

        db = dsdbh.getWritableDatabase();


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();
                String nom = txtNombre.getText().toString();
                String stk = txtStock.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "INSERT INTO Usuarios (codigo,nombre) VALUES ('" + cod + "','" + nom + "') ";
                //db.execSQL(sql);

                //Alternativa 2: método insert()
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("codigo", cod);
                nuevoRegistro.put("nombre", nom);
                nuevoRegistro.put("stock", stk);
                db.insert("Producto", null, nuevoRegistro);

                Toast.makeText(PrincipalActivity.this, "Valor Insertado", Toast.LENGTH_LONG).show();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();
                String nom = txtNombre.getText().toString();
                String stk = txtStock.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "UPDATE Usuarios SET nombre='" + nom + "' WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método update()
                ContentValues valores = new ContentValues();
                valores.put("nombre", nom);
                valores.put("stock", stk);
                db.update("Producto", valores, "codigo=" + cod, null);

                Toast.makeText(PrincipalActivity.this, "Valor Actualizado", Toast.LENGTH_LONG).show();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "DELETE FROM Usuarios WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método delete()
                db.delete("Producto", "codigo=" + cod, null);

                Toast.makeText(PrincipalActivity.this, "Valor Eliminado", Toast.LENGTH_LONG).show();

            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Alternativa 1: método rawQuery()
                Cursor c = db.rawQuery("SELECT codigo, nombre, stock FROM Producto", null);

                //Alternativa 2: método delete()
                //String[] campos = new String[] {"codigo", "nombre"};
                //Cursor c = db.query("Usuarios", campos, null, null, null, null, null);

                //Recorremos los resultados para mostrarlos en pantalla

                List<Producto> productos = new ArrayList<>();

                //txtResultado.setText("");
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {

                        productos.add(new Producto(c.getInt(0), c.getString(1), c.getInt(2)));

                        /*String cod = c.getString(0);
                        String nom = c.getString(1);

                        txtResultado.append(" " + cod + " - " + nom + "\n");*/
                    } while(c.moveToNext());
                }

                ProductoAdapter adapter = new ProductoAdapter(PrincipalActivity.this, productos);
                ltvValores.setAdapter(adapter);
            }
        });
    }
}
