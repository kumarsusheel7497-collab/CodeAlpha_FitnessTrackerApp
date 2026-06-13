package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etExercise, etDuration, etCalories;
    Button btnSave;
    ListView listView;

    DatabaseHelper db;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etExercise = findViewById(R.id.etExercise);
        etDuration = findViewById(R.id.etDuration);
        etCalories = findViewById(R.id.etCalories);
        btnSave = findViewById(R.id.btnSave);
        listView = findViewById(R.id.listView);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {

            String exercise = etExercise.getText().toString();
            String duration = etDuration.getText().toString();
            String calories = etCalories.getText().toString();

            if (db.insertData(exercise, duration, calories)) {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                loadData();
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

        loadData();
    }

    private void loadData() {
        Cursor cursor = db.getAllData();

        list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add(
                    "Exercise: " + cursor.getString(1) +
                    "\nDuration: " + cursor.getString(2) + " mins" +
                    "\nCalories: " + cursor.getString(3)
            );
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

        listView.setAdapter(adapter);
    }
}
