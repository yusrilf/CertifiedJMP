package com.example.sekolahku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sekolahku.db.DBSource;
import com.example.sekolahku.model.Mahasiswa;
import android.util.Log;

public class InputDataActivity extends AppCompatActivity  {
    private EditText editTextNama;
    private EditText editTextNim;
    private EditText editTextTanggalLahir;
    private EditText editTextKelamin;
    private EditText editTextAlamat;
    private Button buttonSave;

    private DBSource dbSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata);

        editTextNama = findViewById(R.id.editTextNama);
        editTextNim = findViewById(R.id.editTextNim);
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir);
        editTextKelamin = findViewById(R.id.editTextKelamin);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        buttonSave = findViewById(R.id.buttonSave);

        dbSource = new DBSource(this);
        dbSource.open();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataMahasiswa();
            }
        });
    }

    private void saveDataMahasiswa() {
        String nama = editTextNama.getText().toString().trim();
        String nim = editTextNim.getText().toString().trim();
        String tanggalLahir = editTextTanggalLahir.getText().toString().trim();
        String kelamin = editTextKelamin.getText().toString().trim();
        String alamat = editTextAlamat.getText().toString().trim();

        if (nama.isEmpty() || nim.isEmpty() || tanggalLahir.isEmpty() || kelamin.isEmpty() || alamat.isEmpty()) {
            Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNama(nama);
        mahasiswa.setNim(nim);
        mahasiswa.setTanggal_lahir(tanggalLahir);
        mahasiswa.setKelamin(kelamin);
        mahasiswa.setAlamat(alamat);

        // Debugging log
        Log.d("MainActivity", "Nama: " + mahasiswa.getNama());
        Log.d("MainActivity", "NIM: " + mahasiswa.getNim());
        Log.d("MainActivity", "Tanggal Lahir: " + mahasiswa.getTanggalLahir());
        Log.d("MainActivity", "Kelamin: " + mahasiswa.getKelamin());
        Log.d("MainActivity", "Alamat: " + mahasiswa.getAlamat());


        dbSource.createMahasiswa(mahasiswa);
        Toast.makeText(this, "Data Mahasiswa disimpan", Toast.LENGTH_SHORT).show();
        clearFields();
    }


    private void clearFields() {
        editTextNama.setText("");
        editTextNim.setText("");
        editTextTanggalLahir.setText("");
        editTextKelamin.setText("");
        editTextAlamat.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbSource.close();
    }
}
