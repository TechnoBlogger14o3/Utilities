package com.utilities.techno.blogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.utilities.techno.blogger.utilities.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtMobileNumber = findViewById(R.id.edtMobileNumber);
        Utils.addTextWatcherForPhoneNoUS(edtMobileNumber);
    }
}
