//package com.studentportal.crudsqliteapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//
//public class MessageActivity extends AppCompatActivity {
//
//    private TextView textMessage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message);
//
//        textMessage = findViewById(R.id.textMessage);
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            String title = intent.getStringExtra("title");
//            String message = intent.getStringExtra("message");
//            if (title != null && message != null) {
//                setTitle(title);
//                textMessage.setText(message);
//            }
//        }
//    }
//
//}

package com.studentportal.crudsqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    private TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Student Info");

        textMessage = findViewById(R.id.textMessage);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String message = intent.getStringExtra("message");
            if (title != null && message != null) {
                setTitle(title);
                textMessage.setText(message);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
