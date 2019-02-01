package com.example.android.projectlattice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public boolean signUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle incomingBundle = getIntent().getExtras();
        if (incomingBundle!= null){
            signUp = incomingBundle.getBoolean("signUp");
        }

        if (!signUp){
            Intent signUpIntent = new Intent(this,SignUpActivity.class);
            startActivity(signUpIntent);
        }
    }
}
