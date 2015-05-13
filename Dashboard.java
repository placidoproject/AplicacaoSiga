package com.example.gavin.aplicacaosiga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Gavin on 05/05/2015.
 */
public class Dashboard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashborad);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,1,1,"Lista");
        return false;
    }

    public boolean onItemMenuSelected(int featureId, MenuItem item){
            int id = item.getItemId();

        switch (id){
            case 1:
                Intent intent = new Intent(this, Dashboard_List.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

}
