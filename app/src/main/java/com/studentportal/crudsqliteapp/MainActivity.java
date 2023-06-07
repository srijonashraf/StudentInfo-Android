package com.studentportal.crudsqliteapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsertData, btnDeleteData, btnUpdateData, btnReadData;
    EditText textId, textName, textSurname, textMarks;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        btnInsertData = findViewById(R.id.btnInsertData);
        btnDeleteData = findViewById(R.id.btnDeleteData);
        btnUpdateData = findViewById(R.id.btnUpdateData);
        btnReadData = findViewById(R.id.btnReadData);

        textId = findViewById(R.id.textId);
        textName = findViewById(R.id.textName);
        textSurname = findViewById(R.id.textSurname);
        textMarks = findViewById(R.id.textMarks);

        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = textId.getText().toString();
                String name = textName.getText().toString();
                String surname = textSurname.getText().toString();
                double marks = Double.parseDouble(textMarks.getText().toString());

                boolean isInserted = myDB.insertData(id, name, surname, marks);

                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Not Inserted!", Toast.LENGTH_SHORT).show();
                }

                textId.setText("");
                textName.setText("");
                textSurname.setText("");
                textMarks.setText("");
            }
        });

        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = myDB.getAllData();

                if (cursor.getCount() == 0) {
                    showMessage("Error", "No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("ID: " + cursor.getString(0) + "\n");
                    buffer.append("Name: " + cursor.getString(1) + "\n");
                    buffer.append("Surname: " + cursor.getString(2) + "\n");
                    buffer.append("Marks: " + cursor.getDouble(3) + "\n\n");
                }

                showMessage("Data", buffer.toString());
            }
        });

        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = textId.getText().toString();
                String name = textName.getText().toString();
                String surname = textSurname.getText().toString();
                double marks = Double.parseDouble(textMarks.getText().toString());

                if (id.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                    showMessage("Error", "Please fill all the fields for updating");
                    return;
                }

                boolean isUpdated = myDB.updateData(id, name, surname, marks);

                if (isUpdated) {
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = textId.getText().toString();

                if (id.isEmpty()) {
                    showMessage("Error", "Please enter an ID for deleting");
                    return;
                }

                Integer isDeleted = myDB.deleteData(id);

                if (isDeleted > 0) {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showMessage(String title, String message) {
        Intent intent = new Intent(MainActivity.this, MessageActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("message", message);
        startActivity(intent);
    }

}
