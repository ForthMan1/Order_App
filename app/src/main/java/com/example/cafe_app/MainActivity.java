package com.example.cafe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextTextPersonName);
        editTextPassword = findViewById(R.id.editTextTextPassword);
    }

    public void onClickCreateOrder(View view) {
        String name = editTextName.getText().toString().trim();// Позволяет убрать лишьние пробелы в строке если они есть
        String password = editTextPassword.getText().toString().trim();
        // Теперь новую активность мы дожны запустить лишь в том случае если имя и пороль содержат символы
        if(!name.isEmpty() && !password.isEmpty()){
            Intent intent = new Intent(this, onClickCreateOrderActivity.class);
            // так же в наши интенты мы должны сложить наши значения имени и пароля
            intent.putExtra("name", name);
            intent.putExtra("password", password);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, R.string.warning_fill_fields, Toast.LENGTH_SHORT).show();
        }

    }
}




















