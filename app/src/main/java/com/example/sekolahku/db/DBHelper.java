package com.example.sekolahku.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sekolahku.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MAHASISWA = "mahasiswa";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_TANGGAL_LAHIR = "tanggal_lahir";
    public static final String COLUMN_KELAMIN = "kelamin";
    public static final String COLUMN_ALAMAT = "alamat";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_MAHASISWA + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAMA + " TEXT, " +
                    COLUMN_NIM + " TEXT, " +
                    COLUMN_TANGGAL_LAHIR + " TEXT, " +
                    COLUMN_KELAMIN + " TEXT, " +
                    COLUMN_ALAMAT + " TEXT" +
                    ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        onCreate(db);
    }
}
