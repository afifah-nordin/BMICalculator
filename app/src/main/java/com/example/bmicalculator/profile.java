package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    TextView linkTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        linkTextView = findViewById(R.id.url);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.bmi) {
            Intent intent = new Intent(profile.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.about) {
            Intent intent = new Intent(profile.this, profile.class);
            startActivity(intent);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}