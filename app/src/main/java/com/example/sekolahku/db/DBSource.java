package com.example.sekolahku.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sekolahku.model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class DBSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DBSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Mahasiswa createMahasiswa(Mahasiswa mahasiswa) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAMA, mahasiswa.getNama());
        values.put(DBHelper.COLUMN_NIM, mahasiswa.getNim());
        values.put(DBHelper.COLUMN_TANGGAL_LAHIR, mahasiswa.getTanggalLahir());
        values.put(DBHelper.COLUMN_KELAMIN, mahasiswa.getKelamin());
        values.put(DBHelper.COLUMN_ALAMAT, mahasiswa.getAlamat());

        // Debugging log
        Log.d("DBSource", "Nama: " + mahasiswa.getNama());
        Log.d("DBSource", "NIM: " + mahasiswa.getNim());
        Log.d("DBSource", "Tanggal Lahir: " + mahasiswa.getTanggalLahir());
        Log.d("DBSource", "Kelamin: " + mahasiswa.getKelamin());
        Log.d("DBSource", "Alamat: " + mahasiswa.getAlamat());

        long insertId = database.insert(DBHelper.TABLE_MAHASISWA, null, values);

        if (insertId == -1) {
            // Insert failed
            Log.e("DBSource", "Failed to insert data for mahasiswa: " + mahasiswa.getNama());
            return null;
        }

        Cursor cursor = database.query(DBHelper.TABLE_MAHASISWA,
                null, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Mahasiswa newMahasiswa = cursorToMahasiswa(cursor);
            cursor.close();
            return newMahasiswa;
        } else {
            // Failed to retrieve the new entry
            Log.e("DBSource", "Failed to retrieve inserted data for ID: " + insertId);
            return null;
        }
    }


    public Mahasiswa getMahasiswaById(int id) {
        Cursor cursor = database.query(DBHelper.TABLE_MAHASISWA,
                null, DBHelper.COLUMN_ID + " = " + id, null,
                null, null, null);
        cursor.moveToFirst();
        Mahasiswa mahasiswa = cursorToMahasiswa(cursor);
        cursor.close();
        return mahasiswa;
    }

    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswas = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.TABLE_MAHASISWA,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mahasiswa mahasiswa = cursorToMahasiswa(cursor);
            mahasiswas.add(mahasiswa);
            cursor.moveToNext();
        }
        cursor.close();
        return mahasiswas;
    }

    public void deleteMahasiswa(Mahasiswa mahasiswa) {
        int id = mahasiswa.getId();
        database.delete(DBHelper.TABLE_MAHASISWA, DBHelper.COLUMN_ID + " = " + id, null);
    }

    private Mahasiswa cursorToMahasiswa(Cursor cursor) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID)));
        mahasiswa.setNama(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAMA)));
        mahasiswa.setNim(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NIM)));
        mahasiswa.setTanggal_lahir(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_TANGGAL_LAHIR)));
        mahasiswa.setKelamin(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_KELAMIN)));
        mahasiswa.setAlamat(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ALAMAT)));
        return mahasiswa;
    }

    public int updateMahasiswa(Mahasiswa mahasiswa) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAMA, mahasiswa.getNama());
        values.put(DBHelper.COLUMN_NIM, mahasiswa.getNim());
        values.put(DBHelper.COLUMN_TANGGAL_LAHIR, mahasiswa.getTanggalLahir());
        values.put(DBHelper.COLUMN_KELAMIN, mahasiswa.getKelamin());
        values.put(DBHelper.COLUMN_ALAMAT, mahasiswa.getAlamat());

        return database.update(DBHelper.TABLE_MAHASISWA, values, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(mahasiswa.getId())});
    }

}
