package com.example.sekolahku;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import com.example.sekolahku.db.DBSource;
import com.example.sekolahku.model.Mahasiswa;

import java.util.List;

public class LihatDataActivity2 extends AppCompatActivity {

    private ListView listViewMahasiswa;
    private DBSource dbSource;
    private List<Mahasiswa> mahasiswaList;
    private ArrayAdapter<Mahasiswa> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatdata);

        listViewMahasiswa = findViewById(R.id.listViewMahasiswa);
        dbSource = new DBSource(this);
        dbSource.open();

        loadMahasiswaList();

        listViewMahasiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showOptionsDialog(mahasiswaList.get(position));
            }
        });
    }

    private void loadMahasiswaList() {
        mahasiswaList = dbSource.getAllMahasiswa();
        adapter = new ArrayAdapter<>(this, R.layout.list_item_mahasiswa, R.id.textViewItemNama, mahasiswaList);
        listViewMahasiswa.setAdapter(adapter);
    }

    void showOptionsDialog(final Mahasiswa mahasiswa) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if result is from EditMahasiswaActivity and update list if needed
        if (resultCode == RESULT_OK) {
            loadMahasiswaList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbSource.close();
    }
}

