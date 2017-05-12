package com.koalasdev.coffeshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TopMenuActivity extends AppCompatActivity {

    private ListView mListView;
    String[] menuCoffee = {"Robusta","Mocchachino","Kopi Luwak","Arabica","Capucchino"};
    ArrayList<String > data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_menu);
        //getaction bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListView = (ListView) findViewById(R.id.mListView);
         //make an adapter
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,menuCoffee);
        mListView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
