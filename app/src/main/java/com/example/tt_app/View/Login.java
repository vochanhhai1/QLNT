package com.example.tt_app.View;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.tt_app.Database.dbmanager;
import com.example.tt_app.MainActivity;
import com.example.tt_app.UserSessionManager;
import com.example.tt_app.databinding.ActivityLoginBinding;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //it nhat 1 so
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +         //it nhat 1 ky tu viet hoa
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[@#$%^&+=])" +    //it nhat 1 ky tu dac biet
                    "(?=\\S+$)" +           //khong khoang trang
                    ".{6,}" +               //toi thieu 6 ky thu
                    "$");
    ActivityLoginBinding binding;
    dbmanager databaseHelper;
    private UserSessionManager userSessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userSessionManager = new UserSessionManager(this);

        if (userSessionManager.getRememberMe()) {
            String savedUsername = userSessionManager.getUsername();
            String savedPassword = userSessionManager.getPassword();
            binding.loginEmail.getEditText().setText(savedUsername);
            binding.loginPassword.getEditText().setText(savedPassword);
            binding.rememberMeCheckBox.setChecked(true);
        }
        binding.rememberMeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Lưu thông tin đăng nhập vào SharedPreferences
                    String username = binding.loginEmail.getEditText().getText().toString();
                    String password = binding.loginPassword.getEditText().getText().toString();
                    userSessionManager.saveCredentials(username, password);
                    userSessionManager.saveRememberMe(true);
                } else {
                    // Xóa thông tin đăng nhập từ SharedPreferences
                    userSessionManager.clearCredentials();
                    userSessionManager.saveRememberMe(false);
                }
            }
        });

        databaseHelper = new dbmanager(this);
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);

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