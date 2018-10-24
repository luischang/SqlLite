package com.ejemplo.sqllite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lchang on 25/09/17.
 */

public class ProductoAdapter extends ArrayAdapter<Producto> {

    private List<Producto> productos;

    public ProductoAdapter(Context context, List<Producto> productos){
        super(context, R.layout.item, productos);
        this.productos = productos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item, null);
        }

        TextView tvCodigo = (TextView) view.findViewById(R.id.tvCodigo);
        TextView tvNombre = (TextView) view.findViewById(R.id.tvNombre);
        TextView tvStock = (TextView) view.findViewById(R.id.tvStock);

        tvCodigo.setText(String.valueOf(productos.get(position).getCodigo()));
        tvNombre.setText(productos.get(position).getNombre());
        tvStock.setText(String.valueOf(productos.get(position).getStock()));

        return view;
    }
}
