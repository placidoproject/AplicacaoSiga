package com.example.gavin.aplicacaosiga;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.exemplo.gavin.Adapters.ApiarioAdapter;
import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.Model.ModelApiario;


public class listarApiario extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaoApiario dao = new DaoApiario(getBaseContext());
       setListAdapter(new ApiarioAdapter(getBaseContext(),dao.ListaApiario()));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
       ModelApiario Mo = (ModelApiario) l.getAdapter().getItem(position);
       startActivity(new Intent("EdicaoApiario").putExtra("codigo",Mo.getID_APIARIO()));
    }

    @Override
    public void onResume(){
        super.onResume();
        DaoApiario dao = new DaoApiario(getBaseContext());
        setListAdapter(new ApiarioAdapter(getBaseContext(), dao.ListaApiario()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar_apiario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
