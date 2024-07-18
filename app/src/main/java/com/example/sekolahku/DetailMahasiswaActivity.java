package com.example.sekolahku;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sekolahku.model.Mahasiswa;

public class DetailMahasiswaActivity extends AppCompatActivity {


    private TextView textViewNama;
    private TextView textViewNim;
    private TextView textViewTanggalLahir;
    private TextView textViewKelamin;
    private TextView textViewAlamat;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaildata);

        textViewNama = findViewById(R.id.textViewNama);
        textViewNim = findViewById(R.id.textViewNim);
        textViewTanggalLahir = findViewById(R.id.textViewTanggalLahir);
        textViewKelamin = findViewById(R.id.textViewKelamin);
        textViewAlamat = findViewById(R.id.textViewAlamat);

        Bundle bun = this.getIntent().getExtras();

        assert bun != null;
        id = bun.getLong("id");
        String nama = bun.getString("nama");
        String nim = bun.getString("nim");
        String tanggal_lahir = bun.getString("tanggal_lahir");
        String kelamin = bun.getString("kelamin");
        String alamat = bun.getString("alamat");

        // Set data ke TextView
        if (bun != null) {
            textViewNama.setText("Nama: " + nama);
            textViewNim.setText("NIM: " + nim);
            textViewTanggalLahir.setText("Tanggal Lahir: " + tanggal_lahir);
            textViewKelamin.setText("Kelamin: " + kelamin);
            textViewAlamat.setText("Alamat: " + alamat);
        }
    }
}
