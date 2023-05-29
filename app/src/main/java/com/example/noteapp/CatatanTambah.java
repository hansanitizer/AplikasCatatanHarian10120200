package com.example.noteapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class CatatanTambah extends AppCompatActivity {

    CatatanDB helper;
    EditText TxTitle, TxDetail;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        helper = new CatatanDB(this);

        id = getIntent().getLongExtra(CatatanDB.row_id, 0);

        TxTitle = (EditText)findViewById(R.id.txJudul_Add);
        TxDetail = (EditText)findViewById(R.id.txDetail_Add);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.tambah_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.save_add:
                String title = TxTitle.getText().toString().trim();
                String detail = TxDetail.getText().toString().trim();

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDate = new SimpleDateFormat("MMM dd, yyyy");
                String created = simpleDate.format(calendar.getTime());

                ContentValues values = new ContentValues();
                values.put(CatatanDB.row_title, title);
                values.put(CatatanDB.row_note, detail);
                values.put(CatatanDB.row_created, created);

                if (title.equals("") && detail.equals("")){
                    Toast.makeText(CatatanTambah.this, "Tidak Ada yang Bisa Disimpan", Toast.LENGTH_SHORT).show();
                }else{
                    helper.insertData(values);
                    Toast.makeText(CatatanTambah.this, "Berhasil Menambah Catatan Baru", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
