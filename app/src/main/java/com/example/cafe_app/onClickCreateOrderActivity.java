package com.example.cafe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class onClickCreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewAdditions;
    private CheckBox checkboxMilk;
    private CheckBox checkboxSuger;
    private CheckBox checkboxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private String name; //Так же сразу берем значения имени и пороля
    private String password;
    private String drink; // переменная которая хранить значение напитка которую мы выбрали
    private StringBuilder builderAdditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        drink = getString(R.string.radioButtonTea);// По умалчанию ей будет присвоено занчение "чай"
        Intent intent = getIntent(); // Мы присваеваем нащи значения через интенты
        // Делаем проверку есть ли у нас пароль и ник по таким ссылочным именам
        if (intent.hasExtra("name") && intent.hasExtra("password")){
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }else{ // если нет то через файл String.xml мы передаем дефолтные значения
            name = getString(R.string.defualt_name);
            password = getString(R.string.defualt_passowrd);
        }
        // Теперь нужно устоновоить текст к нашему textViewHello
        textViewHello = findViewById(R.id.textViewHello);
        String hello = String.format(getString(R.string.hello_user), name);
        textViewAdditions = findViewById(R.id.textViewAdditions);
        // Мы ставим след 2 строки чтобы они не сробатывали если мы не передаем никакое значение
        String additions = String.format(getString(R.string.textViewAdditions), drink);
        textViewAdditions.setText(additions); // Чтобы был одинковый текст в том и в другом
        checkboxMilk = findViewById(R.id.checkboxMilk);
        checkboxSuger = findViewById(R.id.checkboxSuger);
        checkboxLemon = findViewById(R.id.checkboxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        builderAdditions = new StringBuilder();
    }

    public void onClickChangeDrink(View view) {
        RadioButton radioButton = (RadioButton) view;
        int id = radioButton.getId();
        if (id == R.id.radioButtonTea){
            drink =getString(R.string.radioButtonTea);
            // Теперь скрываем наши спинеры
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            // Отображаем CheckBox Лимона если выбран чай
            checkboxLemon.setVisibility(View.VISIBLE);
        }
        else if (id == R.id.radioButtonCoffee){
            drink = getString(R.string.radioButtonCoffee);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            // Так же скрываем этот CheckBox Лемон если выбрали кофе
            checkboxLemon.setVisibility(View.INVISIBLE);
        }

    }

    public void onClickSendOrder(View view) {
        builderAdditions.setLength(0); //Очишяем от каких либо значений если ранее она была заполнена
        // Теперь нам нужно пробижваться по всем ChexckBox и посмотреть были ли они отмечены
        // и если они отмечены то добавим их в StringBuilder
        if (checkboxMilk.isChecked()){
            builderAdditions.append(getString(R.string.checkboxMilk)).append(" ");
        }
        if (checkboxSuger.isChecked()){
            builderAdditions.append(getString(R.string.checkboxSuger)).append(" ");
        }// Для лемона 2 условия если он выбран и если только выбран чай
        if (checkboxLemon.isChecked() && drink.equals(getString(R.string.radioButtonTea))){
            builderAdditions.append(getString(R.string.checkboxLemon)).append(" ");
        }
        // Теперь соберем все наши данные в строку Order

        // Вид напитка мы получаем из нашего спинера
        String optionOfDrink = "";
        // Вибираем значение того спинера от зависемости который спинер у нас видем
        if(drink.equals(getString(R.string.radioButtonTea))){
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        }
        else{
            optionOfDrink = spinnerCoffee.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order), name, password,drink, optionOfDrink);
        // Создаем строку с добавками
        String additions;
        // В зависемости есть ли добавки в StringBuilder либо пустое значение, либо значение добавки
        if(builderAdditions.length()>0){
            additions = getString(R.string.need_additions)+ builderAdditions.toString();
        }else {
            additions = "";
        }
        // Теперь сделаем полную строку запроса
        String fullOrder = order + "\n" +additions;
        // Создаем интент для отаброжения её в след активности
        Intent intent =  new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);
    }
}























