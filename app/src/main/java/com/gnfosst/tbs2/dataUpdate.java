package com.gnfosst.tbs2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class dataUpdate extends AppCompatActivity {
    private DBHelper dbHelper;
    private EditText iLastName, iFirstName, iMiddleName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        dbHelper = new DBHelper(this);
        iLastName = findViewById(R.id.lastName);
        iFirstName = findViewById(R.id.firstName);
        iMiddleName = findViewById(R.id.middleName);
    }

    public void svRec(View view){
        String lastName = iLastName.getText().toString().trim();
        String firstName = iFirstName.getText().toString().trim();
        String middleName = iMiddleName.getText().toString().trim();

        if (!lastName.isEmpty() && !firstName.isEmpty() && !middleName.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_LAST_NAME, lastName);
            values.put(DBHelper.COLUMN_FIRST_NAME, firstName);
            values.put(DBHelper.COLUMN_MIDDLE_NAME, middleName);

            long rowID = db.insert(DBHelper.TABLE_NAME, null, values);

            if (rowID != -1) {
                setResult(RESULT_OK);
                finish();
            } else {
                setResult(RESULT_CANCELED);
                finish();
            }

            db.close();
        }
    }

}
