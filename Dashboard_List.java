package com.example.gavin.aplicacaosiga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Dashboard_List extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashborad_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,1,1,"Em quadros");
        return false;
    }


    public boolean onItemMenuSelected(int featureId, MenuItem item){
        int id = item.getItemId();

        switch (id){
            case 1:
                Intent intent = new Intent(this, Dashboard.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}
