package com.example.ziolson.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDataBase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");

            myDataBase.execSQL("DELETE FROM newUsers WHERE id > 4");

//            myDataBase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Rob', 34)");
//            myDataBase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Mariusz', 23)");
//            myDataBase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Krzysiek', 21)");
//            myDataBase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Rafał', 18)");

            Cursor c = myDataBase.rawQuery("SELECT * FROM newUsers", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();

            while (c != null) {
                Log.i("UserResults - name", c.getString(nameIndex));
                Log.i("UserResults - age", Integer.toString(c.getInt(ageIndex)));
                Log.i("UserResults - id", Integer.toString(c.getInt(idIndex)));


                c.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
