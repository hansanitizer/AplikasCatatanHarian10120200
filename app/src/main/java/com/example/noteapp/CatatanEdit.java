package com.example.noteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class CatatanEdit extends AppCompatActivity {

    CatatanDB helper;
    EditText TxTitle, TxDetail;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        helper = new CatatanDB(this);

        id = getIntent().getLongExtra(CatatanDB.row_id, 0);

        TxTitle = (EditText)findViewById(R.id.txJudul_Edit);
        TxDetail = (EditText)findViewById(R.id.txDetail_Edit);

        getData();
    }

    private void getData() {
        Cursor cursor = helper.oneData(id);
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndex(CatatanDB.row_title));
            String detail = cursor.getString(cursor.getColumnIndex(CatatanDB.row_note));

            TxTitle.setText(title);
            TxDetail.setText(detail);
        }

    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_edit:
                String title = TxTitle.getText().toString().trim();
                String detail = TxDetail.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(CatatanDB.row_title, title);
                values.put(CatatanDB.row_note, detail);

                if (title.equals("") && detail.equals("")){
                    Toast.makeText(CatatanEdit.this, "Tidak Ada yang dapat Disimpan", Toast.LENGTH_SHORT).show();
                }else {
                    helper.updateData(values, id);
                    Toast.makeText(CatatanEdit.this, "Berhasil Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        switch (item.getItemId()) {
            case R.id.hapus_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(CatatanEdit.this);
                builder.setMessage("Hapus Catatan?");
                builder.setCancelable(true);
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        helper.deleteData(id);
                        Toast.makeText(CatatanEdit.this, "Catatan Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}