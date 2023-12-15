package com.gnfosst.tbs2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class dataView extends AppCompatActivity {
    private DBHelper dbHelper;
    private TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        dbHelper = new DBHelper(this);
        displayText = findViewById(R.id.text);

        dataDisplay();
    }

    private void dataDisplay() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);

        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
            String lastName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LAST_NAME));
            String firstName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_FIRST_NAME));
            String middleName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_MIDDLE_NAME));
            String timestamp = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TIME));

            stringBuilder.append("ID: ").append(id)
                    .append(", Фамилия: ").append(lastName)
                    .append(", Имя: ").append(firstName)
                    .append(", Отчество: ").append(middleName)
                    .append(", Дата создания: ").append(timestamp)
                    .append("\n");
        }

        cursor.close();
        db.close();

        displayText.setText(stringBuilder.toString());
    }

}
