package com.example.noteapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class CatatanMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    CatatanDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(CatatanMenu.this, CatatanTambah.class));
            }
        });

        helper = new CatatanDB(this);
        listView = (ListView)findViewById(R.id.list_catatan);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setListView(){
        Cursor cursor = helper.allData();
        CatatanAdapter catatanAdapter = new CatatanAdapter(this, cursor, 1);
        listView.setAdapter(catatanAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long x) {
        TextView getId = (TextView)view.findViewById(R.id.listID);
        final long id = Long.parseLong(getId.getText().toString());
        Cursor cur = helper.oneData(id);
        cur.moveToFirst();

        Intent idnotes = new Intent(CatatanMenu.this, CatatanEdit.class);
        idnotes.putExtra(CatatanDB.row_id, id);
        startActivity(idnotes);
    }

    @Override
    protected void onResume(){
        super.onResume();
        setListView();
    }
}
