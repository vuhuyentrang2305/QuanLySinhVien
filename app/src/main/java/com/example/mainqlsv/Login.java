package com.example.mainqlsv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    static final int CODE_REGISTER=68;
    EditText  email,password;
    Button btlogin, btregis;
    User user=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.l_email);
        password=findViewById(R.id.l_pass);
        btlogin=(Button)findViewById(R.id.l_login);
        btregis=(Button)findViewById(R.id.l_new_register);
        btregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Register.class);
                startActivityForResult(i,CODE_REGISTER);
            }
        });
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==CODE_REGISTER)
        {
            String fname=data.getStringExtra("fullname");
            String email= data.getStringExtra("email");
            String matkhau= data.getStringExtra("password");
            user= new User(fname,email,matkhau);
            Toast.makeText(Login.this,"Đăng ký thành công!",Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void checkLogin() {
        String edemail=email.getText().toString();
        String edpass=password.getText().toString();
        if(email.getText().length()!=0&&password.getText().length()!=0){
            if(user==null){
                Toast.makeText(this, "Chưa có tài khoản đăng ký!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(edemail.equalsIgnoreCase(user.getEmail()) && edpass.equalsIgnoreCase(user.getPassword())){
                Toast.makeText(this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Login.this,SinhVien.class);
                startActivity(i);
            }else{
                Toast.makeText(this,"Kiểm tra lại!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(Login.this,"Mời bạn nhập đủ thông tin!",Toast.LENGTH_LONG).show();
        }
    }
}