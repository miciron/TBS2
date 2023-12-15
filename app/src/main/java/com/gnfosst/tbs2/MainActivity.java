package com.gnfosst.tbs2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
    }

    public void dataAdd(View view) {
        Intent intent = new Intent(this, dataUpdate.class);
        startActivity(intent);
    }
    public void dataView(View view)
    {
        Intent intent = new Intent(this, dataView.class);
        startActivityForResult(intent, 1);
    }

    private int getLastID() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(" + DBHelper.COLUMN_ID + ") FROM " + DBHelper.TABLE_NAME, null);
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }

    public void updLastRec(View view){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_LAST_NAME, "Иванов");
        values.put(DBHelper.COLUMN_FIRST_NAME, "Иван");
        values.put(DBHelper.COLUMN_MIDDLE_NAME, "Иванович");

        String selection = DBHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(getLastID())};

        int count = db.update(DBHelper.TABLE_NAME, values, selection, selectionArgs);
        if (count > 0) {
            Toast.makeText(this, ":)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, ":(", Toast.LENGTH_SHORT).show();
        }
    }


}