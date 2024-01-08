package com.example.tt_app.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.tt_app.databinding.ActivitySignupBinding;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
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
    ActivitySignupBinding binding;
    dbmanager databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new dbmanager(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String email = binding.signupEmail.getText().toString();
               // String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if (!validateEmail() | !validatePassword()| !validateUsername() | !validatePhone()) {
                    return;
                }
                String inputemail =  binding.signupEmail.getText().toString();
                String inputpassword = binding.signupPassword.getText().toString();
                String inputhovaten = binding.signupHoten.getText().toString();
                int inputsodienthoai = Integer.parseInt(binding.signupSdt.getText().toString());
                if(inputemail.equals("")||inputpassword.equals("")||confirmPassword.equals(""))
                    Toast.makeText(Signup.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else{
                    if(inputpassword.equals(confirmPassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(inputemail);

                        if(checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData_user(inputhovaten,inputemail,inputsodienthoai, inputpassword);

                            if(insert == true){
                                Toast.makeText(Signup.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Signup.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Signup.this, "Người dùng đã tồn tại! Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signup.this, "Mật khẩu không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });

    }
    public boolean validatePhone() {
        String validatePhone = binding.signupSdt.getText().toString().trim();

        if (validatePhone.isEmpty()) {
            binding.signupSdt.setError("Trường không thể trống");
            return false;
        } else if (validatePhone.length() != 10) {
            binding.signupSdt.setError("Số điện thoại phải có 10 số");
            return false;
        } else if (!TextUtils.isDigitsOnly(validatePhone)) {
            binding.signupSdt.setError("Số điện thoại chỉ được chứa các chữ số");
            return false;
        } else {
            binding.signupSdt.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = binding.signupHoten.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            binding.signupHoten.setError("Trường không thể trống\n");
            return false;
        } else if (usernameInput.length() > 15) {
            binding.signupHoten.setError("Tên người dùng quá dài");
            return false;
        }else if (usernameInput.matches(".*\\d+.*")) {
            binding.signupHoten.setError("Tên người dùng không thể chứa số");
            return false;
        } else {
            binding.signupHoten.setError(null);
            return true;
        }
    }
    public  boolean validateEmail()
    {
        String validateemail = binding.signupEmail.getText().toString().trim();

        if (validateemail.isEmpty())
        {
            binding.signupEmail.setError("Trường không thể trống");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(validateemail).matches()) {
            binding.signupEmail.setError("Hãy nhập đúng email của bạn");
            return false;
        } else
        {
            binding.signupEmail.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String passwordInput = binding.signupPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            binding.signupPassword.setError("Trường không thể trống");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            binding.signupPassword.setError("Mật khẩu quá yếu");
            return false;
        } else {
            binding.signupPassword.setError(null);
            return true;
        }
    }
}