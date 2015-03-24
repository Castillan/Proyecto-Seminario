package com.example.luisgluna.vista_lista_expandible;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class adaptador extends BaseExpandableListAdapter {
    private final SparseArray<GrupoDeItems> grupos;
    public LayoutInflater inflater;
    public Activity activity;
    // Constructor
    public adaptador(Activity act, SparseArray<GrupoDeItems> grupos) {
        activity = act;
        this.grupos = grupos;
        inflater = act.getLayoutInflater();
    }
    // Nos devuelve los datos asociados a un subitem en base
    // a la posición
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return grupos.get(groupPosition).children.get(childPosition);
    }
    // Devuelve el id de un item o subitem en base a la
    // posición de item y subitem
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }
    // En base a la posición del item y de subitem nos devuelve
    // el objeto view correspondiente y el layout para los subitems
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView textvw = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.subitems_layout, null);
        }
        textvw = (TextView) convertView.findViewById(R.id.textView1);
        textvw.setText(children);
        final int groupPosition2 = groupPosition;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Esto emite una pequeña ventana de alerta con el mensaje seleccionado
                //Toast.makeText(activity, children, Toast.LENGTH_SHORT).show();
                Intent ine = new Intent();
                switch (groupPosition2){
                    case 0:
                        ine.setClass(activity,Drunk_Blocker.class);
                        switch (childPosition){
                            case 0:
                                activity.startActivity(ine);
                                break;
                            case 1:
                                activity.startActivity(ine);
                                break;
                        }
                        break;
                    case 1:
                        //ine.setClass(activity,Alarma.class);
                        switch (childPosition){
                            case 0:
                                final AlertDialog.Builder alertaSimple = new AlertDialog.Builder(activity);

                                alertaSimple.setTitle("Nueva Alarma");
                                alertaSimple.setMessage("Ingrese la hora");
                                alertaSimple.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Codigo
                                    }
                                });
                                alertaSimple.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Codigo
                                    }
                                });
                                alertaSimple.setIcon(R.drawable.relog_alerta);
                                alertaSimple.create();
                                alertaSimple.show();
                          //      activity.startActivity(ine);
                                break;
                            //asigna una imagen a un teim o subitem
                            //textvw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pescadofrito, 0, 0, 0);
                        }
                        break;
                }
            }
        });

        textvw.setText(children);

        return convertView;
    }
    // Nos devuelve la cantidad de subitems que tiene un ítem
    @Override
    public int getChildrenCount(int groupPosition) {
        return grupos.get(groupPosition).children.size();
    }
    //Los datos de un ítem especificado por groupPosition
    @Override
    public Object getGroup(int groupPosition) {
        return grupos.get(groupPosition);
    }
    //La cantidad de ítem que tenemos definidos
    @Override
    public int getGroupCount() {
        return grupos.size();
    }
    //Método que se invoca al contraer un ítem
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }
    //Método que se invoca al expandir un ítem
    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }
    //Devuelve el id de un ítem
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }
    //Obtenemos el layout para los ítems
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.items_layout, null);
        }
        GrupoDeItems grupo = (GrupoDeItems) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(grupo.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    //Nos informa si es seleccionable o no un ítem o subitem
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}