package com.example.android.projectlattice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.projectlattice.DataModel.UserInfo;
import com.example.android.projectlattice.NetworkUtils.PostmanService;
import com.example.android.projectlattice.database.UserDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText nameField;
    private TextInputEditText addressField;
    private TextInputEditText emailField;
    private TextInputEditText phoneNoField;
    private TextInputEditText passwordField;

    private boolean nameValidate = false;
    private boolean addressValidate = false;
    private boolean emailValidate = false;
    private boolean phoneValidate = false;
    private boolean passwordValidate = false;





    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//    private final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])$";
    private final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{4,15}$";
    private Button signUpButton;
    private Pattern pattern;
    private Matcher matcher;
    private PostmanService mPostmanService;

    private UserDatabase mDb;
    private String name;
    private String address;
    private String emailId;
    private String phoneNo;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameField = findViewById(R.id.name_field);
        addressField = findViewById(R.id.address_field);
        emailField = findViewById(R.id.email_id_field);
        phoneNoField = findViewById(R.id.phone_field);
        passwordField = findViewById(R.id.password_field);
        signUpButton = findViewById(R.id.sign_up_button);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameField.getText().toString();
                address = addressField.getText().toString();
                emailId = emailField.getText().toString();
                phoneNo = phoneNoField.getText().toString();
                password = passwordField.getText().toString();

                dbSave();

//                sendUserData(name,address,emailId,phoneNo,password);


            }
        });



        nameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                nameField.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count<4){
                        nameField.setError("Name should be greater than 4");
                        nameValidate = false;
                        buttonVisibility();
                    }else{
                        nameField.setError(null);
                        nameValidate = true;
                        buttonVisibility();
                    }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                }
            }
        });

        addressField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                addressField.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length()<10){
                            addressField.setError("Address should be greater than 10");
                            addressValidate = false;
                            buttonVisibility();
                        }else{
                            addressField.setError(null);
                            addressValidate = true;
                            buttonVisibility();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            } }
        });

        emailField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    emailField.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (emailField.getText().toString().matches(emailPattern)){
                                emailField.setError(null);
                                emailValidate = true;
                                buttonVisibility();
                            }else{

                                emailField.setError("Not a valid Email ID");
                                emailValidate = false;
                                buttonVisibility();
                            }
                        }
                    });
                }
            }
        });

        phoneNoField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    phoneNoField.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length()<10){
                                phoneNoField.setError("Phone No. should at least have 10 digit");
                                phoneValidate = false;
                                buttonVisibility();
                            }else {
                                phoneNoField.setError(null);
                                phoneValidate = true;
                                buttonVisibility();
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        });

        passwordField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    passwordField.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (!isValidPassword(passwordField.getText().toString())){
                                passwordField.setError("Password must contain at least 4 characters, 1 uppercase letter, 1 lowercase letter, and a digit");
                                passwordValidate = false;
                                buttonVisibility();
                            }else{
                                passwordField.setError(null);
                                passwordValidate = true;
                                buttonVisibility();
                            }
                        }
                    });
                }
            }
        });





    }

    public boolean fieldValidation(){
        return nameValidate && addressValidate && emailValidate && phoneValidate && passwordValidate;
    }
    public void buttonVisibility(){
        if (fieldValidation()){
            signUpButton.setVisibility(View.VISIBLE);

        }else {
            signUpButton.setVisibility(View.INVISIBLE);
        }
    }
    public boolean isValidPassword(String password){
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    public void dbSave(){   //this method should be called in the onResponse method of sendUserDatabase method.
        UserInfo userInfo = new UserInfo(name,address,emailId,phoneNo,password);
        mDb.userDao().insertUserInfo(userInfo);
        Toast.makeText(this, "Data saved to db", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
        intent.putExtra("signUp",true);
        startActivity(intent);

    }

    /**
     * This function is used for the network call
    * */

//    public void sendUserData(String name, String address, String email, String phone, String password){
//        mPostmanService.saveUserData(name,address,email,phone,password).enqueue(new Callback<UserData>() {
//            @Override
//            public void onResponse(Call<UserData> call, Response<UserData> response) {
//                Toast.makeText(SignUpActivity.this, "Data saved to server", Toast.LENGTH_SHORT).show();
//                Intent mainActivityIntent = new Intent(SignUpActivity.this,MainActivity.class);
//                mainActivityIntent.putExtra("signUp",true);
//                startActivity(mainActivityIntent);
//            }
//
//            @Override
//            public void onFailure(Call<UserData> call, Throwable t) {
//                Toast.makeText(SignUpActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }


}
