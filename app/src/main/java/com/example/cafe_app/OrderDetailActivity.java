package com.example.cafe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView textViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        textViewOrder = findViewById(R.id.textViewOrder);
        Intent intent = getIntent();
        // Проверим содержит лиш нащ инетент ключь order
        if (intent.hasExtra("order")) {// Если интент содержит order
            // Ты мы получаем наш заказ
            String order = intent.getStringExtra("order");
            textViewOrder.setText(order);

        }else{ // В противном случае нас перенапровляетюь на стр регистрацию
            Intent backToLogin = new Intent(this, MainActivity.class);
            startActivity(backToLogin);
        }
    }
}