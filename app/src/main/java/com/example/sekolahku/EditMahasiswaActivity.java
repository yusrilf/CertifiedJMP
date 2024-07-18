package com.example.sekolahku;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sekolahku.db.DBSource;
import com.example.sekolahku.model.Mahasiswa;

public class EditMahasiswaActivity extends AppCompatActivity {

    private EditText editTextNama;
    private EditText editTextNim;
    private EditText editTextTanggalLahir;
    private EditText editTextKelamin;
    private EditText editTextAlamat;
    private Button buttonUpdate;

    private DBSource dbSource;
    private Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmahasiswa);

        editTextNama = findViewById(R.id.editTextNama);
        editTextNim = findViewById(R.id.editTextNim);
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir);
        editTextKelamin = findViewById(R.id.editTextKelamin);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        dbSource = new DBSource(this);
        dbSource.open();

        int mahasiswaId = getIntent().getIntExtra("mahasiswaId", -1);
        mahasiswa = dbSource.getMahasiswaById(mahasiswaId);

        if (mahasiswa != null) {
            editTextNama.setText(mahasiswa.getNama());
            editTextNim.setText(mahasiswa.getNim());
            editTextTanggalLahir.setText(mahasiswa.getTanggalLahir());
            editTextKelamin.setText(mahasiswa.getKelamin());
            editTextAlamat.setText(mahasiswa.getAlamat());
        }

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMahasiswa();
            }
        });
    }

    private void updateMahasiswa() {
        String nama = editTextNama.getText().toString().trim();
        String nim = editTextNim.getText().toString().trim();
        String tanggalLahir = editTextTanggalLahir.getText().toString().trim();
        String kelamin = editTextKelamin.getText().toString().trim();
        String alamat = editTextAlamat.getText().toString().trim();

        if (nama.isEmpty() || nim.isEmpty() || tanggalLahir.isEmpty() || kelamin.isEmpty() || alamat.isEmpty()) {
            Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        mahasiswa.setNama(nama);
        mahasiswa.setNim(nim);
        mahasiswa.setTanggal_lahir(tanggalLahir);
        mahasiswa.setKelamin(kelamin);
        mahasiswa.setAlamat(alamat);

        dbSource.updateMahasiswa(mahasiswa);
        Toast.makeText(this, "Data Mahasiswa diperbarui", Toast.LENGTH_SHORT).show();

        // Log penggantian data
        Log.d("EditMahasiswaActivity", "Data Mahasiswa diperbarui: " + mahasiswa.toString());

        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbSource.close();
    }
}
