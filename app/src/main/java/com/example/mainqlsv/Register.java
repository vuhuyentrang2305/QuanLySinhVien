package com.example.mainqlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Button regis, back;
    EditText edfullname, edemail, edpass, edconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edfullname = (EditText) findViewById(R.id.r_fullname);
        edemail = (EditText) findViewById(R.id.r_email);
        edpass = (EditText) findViewById(R.id.r_pass);
        edconfirm = (EditText) findViewById(R.id.r_confirm);
        regis = (Button) findViewById(R.id.bt_regis);
        back = (Button) findViewById(R.id.bt_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void register() {
        String fullname = edfullname.getText().toString();
        String email = edemail.getText().toString();
        String pass = edpass.getText().toString();
        String confirm = edconfirm.getText().toString();
        if (edfullname.getText().length() != 0 && edemail.getText().length() != 0 && edpass.getText().length() != 0 && edconfirm.getText().length() != 0) {
            if (!pass.equalsIgnoreCase(confirm)) {
                Toast.makeText(Register.this, "Mat khau khong khop", Toast.LENGTH_LONG).show();
            } else {
                Intent i = new Intent();
                i.putExtra("fullname", fullname);
                i.putExtra("email", email);
                i.putExtra("password", pass);
                setResult(Login.CODE_REGISTER, i);
                SharedPreferences sharedPreferences = getSharedPreferences("D02K13", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", fullname);
                editor.putString("email", email);
                editor.putString("pass", pass);
                editor.commit();
                finish();
            }
        } else {
            Toast.makeText(Register.this, "Mời bạn nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }
}