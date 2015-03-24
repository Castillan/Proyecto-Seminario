package com.example.luisgluna.vista_lista_expandible;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends Activity {
    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();
    Hilos hilo;
    Switch estado_drunk_blocker;
    Switch estado_alarma;
    GrupoDeItems grupo0 = new GrupoDeItems("Drunk Blocker");
    GrupoDeItems grupo1 = new GrupoDeItems("Alarma");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearDatos();
        estado_drunk_blocker = (Switch) findViewById(R.id.switch1);
        estado_alarma = (Switch) findViewById(R.id.switch2);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
        adaptador adapter = new adaptador(this, grupos);
        listView.setAdapter(adapter);
    }

    public void crearDatos() {
        grupo0.children.add("Tipos de Preguntas");
        grupo0.children.add("Configuración");
        grupos.append(0, grupo0);

        grupo1.children.add("Nueva Alarma");
        grupos.append(1, grupo1);
      }

     public void AgregarAlarma(String valor){
        grupo1.children.add(valor);
        grupos.append(1,grupo1);
     }

    public void LanzarDrunkBlocker(View view) throws InterruptedException {
        boolean on = estado_drunk_blocker.isChecked();
        if (on) {
            hilo = new Hilos();
            hilo.execute();
        }
    }

    public void LanzarAlarma(View view) throws InterruptedException {
       GenerarSaltos(this,Alarma.class);
    }

    public void GenerarSaltos(Activity origen, Class destino){

        Intent salto = new Intent(origen,destino);
        origen.startActivity(salto);

    }

    public void HacerUnaPausa(){
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public class Hilos extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
        boolean pasar = true;

            for (int i = 0; i < 10; i++) {

                HacerUnaPausa();
                if(!estado_drunk_blocker.isChecked()){
                    cancel(true);
                    pasar = false;
                    break;
                }

            }

            if(pasar){
                GenerarSaltos(MainActivity.this,Drunk_Blocker.class);
            }

            return true;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this,"Cuenta con 10 segundos antes del bloqueo",Toast.LENGTH_SHORT).show();
        }


    }

}
