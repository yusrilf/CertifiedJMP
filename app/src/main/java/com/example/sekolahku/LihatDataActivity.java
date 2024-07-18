package com.example.sekolahku;// LihatDataActivity.java
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sekolahku.MahasiswaAdapter;
import com.example.sekolahku.db.DBSource;
import com.example.sekolahku.model.Mahasiswa;

import java.util.List;

public class LihatDataActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMahasiswa;
    private DBSource dbSource;
    private List<Mahasiswa> mahasiswaList;
    private MahasiswaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatdata);

        recyclerViewMahasiswa = findViewById(R.id.recyclerViewMahasiswa);
        recyclerViewMahasiswa.setLayoutManager(new LinearLayoutManager(this));
        dbSource = new DBSource(this);
        dbSource.open();

        loadMahasiswaList();
    }

    private void loadMahasiswaList() {
        mahasiswaList = dbSource.getAllMahasiswa();
        adapter = new MahasiswaAdapter(this, mahasiswaList);
        recyclerViewMahasiswa.setAdapter(adapter);
    }

    public void showOptionsDialog(final Mahasiswa mahasiswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Aksi");
        String[] options = {"View", "Edit", "Delete"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    viewMahasiswa(mahasiswa);
                } else if (which == 1) {
                    editMahasiswa(mahasiswa);
                } else if (which == 2) {
                    deleteMahasiswa(mahasiswa);
                }
            }
        });
        builder.show();
    }

    private void viewMahasiswa(Mahasiswa mahasiswa) {
        Intent intent = new Intent(this, DetailMahasiswaActivity.class);
        Bundle bun = new Bundle();
        bun.putLong("id", mahasiswa.getId());
        bun.putString("nama", mahasiswa.getNama());
        bun.putString("nim", mahasiswa.getNim());
        bun.putString("tanggal_lahir", mahasiswa.getTanggalLahir());
        bun.putString("kelamin", mahasiswa.getKelamin());
        bun.putString("alamat",  mahasiswa.getAlamat());
        intent.putExtras(bun);

        startActivity(intent);
    }

    private void editMahasiswa(Mahasiswa mahasiswa) {
        Intent intent = new Intent(this, EditMahasiswaActivity.class);
        intent.putExtra("mahasiswaId", mahasiswa.getId());
        startActivity(intent);
    }

    private void deleteMahasiswa(Mahasiswa mahasiswa) {
        dbSource.deleteMahasiswa(mahasiswa);
        Toast.makeText(this, "Mahasiswa dihapus", Toast.LENGTH_SHORT).show();

        // Perbarui list setelah penghapusan
        loadMahasiswaList();
    }

    // LihatDataActivity.java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            loadMahasiswaList();
        }
    }

    // LihatDataActivity.java
    @Override
    protected void onResume() {
        super.onResume();
        loadMahasiswaList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbSource.close();
    }
}
