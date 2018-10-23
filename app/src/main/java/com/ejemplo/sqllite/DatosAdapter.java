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
 * Created by lventura on 25/09/17.
 */

public class DatosAdapter extends ArrayAdapter<Datos> {

    private List<Datos> datos;

    public DatosAdapter(Context context, List<Datos> datos){
        super(context, R.layout.item, datos);
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item, null);
        }

        TextView lblId = (TextView) view.findViewById(R.id.lblid);
        TextView lblValor = (TextView) view.findViewById(R.id.lblValor);
        TextView lblStock = (TextView) view.findViewById(R.id.lblStock);

        lblId.setText(datos.get(position).getId());
        lblValor.setText(datos.get(position).getNombre());
        lblStock.setText(datos.get(position).getStock());

        return view;
    }
}
