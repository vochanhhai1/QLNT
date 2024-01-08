package com.example.tt_app.View;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tt_app.MainActivity;
import com.example.tt_app.R;
import com.example.tt_app.databinding.ActivityLoginBinding;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");
    ActivityLoginBinding binding;
    dbmanager databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new dbmanager(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

                if (!validateEmail()| !validatePassword()) {
                    return;
                }
                String inputemail =  binding.loginEmail.getEditText().getText().toString();
                String inputpassword = binding.loginPassword.getEditText().getText().toString();

                if(inputemail.equals("")||inputpassword.equals("")) {
                    Toast.makeText(Login.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(inputemail, inputpassword);
                    if(checkCredentials == true){
                        Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                        Intent myintent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(myintent);
                    }else{
                        Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
    }

    public  boolean validateEmail()
    {
        String validateemail = binding.loginEmail.getEditText().getText().toString().trim();

        if (validateemail.isEmpty())
        {
            binding.loginEmail.setError("Trường không thể trống");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(validateemail).matches()) {
            binding.loginEmail.setError("Hãy nhập đúng email của bạn");
            return false;
        } else
        {
            binding.loginEmail.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String passwordInput = binding.loginPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            binding.loginPassword.setError("Trường không thể trống");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            binding.loginPassword.setError("Mật khẩu quá yếu");
            return false;
        } else {
            binding.loginPassword.setError(null);
            return true;
        }
    }

}