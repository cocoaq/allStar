package com.example.allstar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;

public class DialogTest extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener callbackMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);

        this.InitializeListner();
        this.InitializeView();
    }

    private void InitializeView() {

    }

    private void InitializeListner() {
    }
}