package com.example.adriana.myapplication2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
/**
 * Created by Adriana on 11/25/2017.
 */

public class EditActivity extends AppCompatActivity {
    private Integer sendBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        String[] categories = getIntent().getStringArrayExtra("categories");
        this.sendBack = getIntent().getIntExtra("edit", -1);
        ((EditText) findViewById(R.id.editText4)).setText(getIntent().getStringExtra("name"));
        ((EditText) findViewById(R.id.editText5)).setText(getIntent().getStringExtra("location"));
        ((EditText) findViewById(R.id.editText6)).setText(getIntent().getStringExtra("stars"));
        //Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
    }

    public void addRestaurant(View view) {
        Intent intent = new Intent();
        Editable title = ((EditText) findViewById(R.id.editText4)).getText();
        Editable description = ((EditText) findViewById(R.id.editText5)).getText();
        if (title.toString().equals("") || description.toString().equals("")) {
            Snackbar snackbar = Snackbar.make(view, "Title and description cannot be empty", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        intent.putExtra("name", title.toString());
        intent.putExtra("location", description.toString());
        intent.putExtra("stars", description.toString());
        //intent.putExtra("category", ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString());
        intent.putExtra("edit", this.sendBack);
        setResult(RESULT_OK, intent);
        finish();
    }
}
