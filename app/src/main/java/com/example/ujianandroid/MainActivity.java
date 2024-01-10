package com.example.ujianandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edNamaDepan = (EditText) findViewById(R.id.edNamaDepan);
        EditText edNamaBelakang = (EditText) findViewById(R.id.edNamaBelakang);
        EditText edUmur = (EditText) findViewById(R.id.edUmur);
        Button btnSimpan = (Button) findViewById(R.id.btnSimpan);

        ArrayList<String> daftar_nama = new ArrayList<>();

        Intent intent_list = new Intent(MainActivity.this, ListActivity.class);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isian_nama_depan = edNamaDepan.getText().toString();
                String isian_nama_belakang = edNamaBelakang.getText().toString();
                String isian_umur = edUmur.getText().toString();

                if (isian_nama_depan.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Isian masih kosong", Toast.LENGTH_SHORT).show();
                } else {
                    // Menentukan status berdasarkan umur
                    String status_umur;
                    int umur = Integer.parseInt(isian_umur);
                    if (umur < 10) {
                        status_umur = "Anak";
                    } else if (umur < 20) {
                        status_umur = "Remaja";
                    } else if (umur < 40) {
                        status_umur = "Dewasa";
                    } else {
                        status_umur = "Tua";
                    }

                    String nama_lengkap = isian_nama_depan.concat(" ").concat(isian_nama_belakang);

                    // Menambahkan informasi umur, nama, dan status ke daftar_nama
                    daftar_nama.clear();
                    for (int i = 1; i <= umur ; i++) {
                        daftar_nama.add(i + " " + nama_lengkap + " " + status_umur);
                    }

                    edNamaDepan.setText("");
                    edNamaBelakang.setText("");
                    edUmur.setText("");  // Clear input umur setelah satu entri

                    // Mengirim daftar_nama ke ListActivity
                    intent_list.putStringArrayListExtra("daftar_nama", daftar_nama);
                    startActivity(intent_list);
                }
            }
        });
    }
}