package com.example.coolshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.coolshop.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TextView textView;
    private int[] allCheckBox;
    private CheckBox checkBox;
    private int[] allRadioButton;
    private RadioButton radioButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView = findViewById(R.id.textInfo);

        allCheckBox = new int[]{
                R.id.ch1, R.id.ch2, R.id.ch3, R.id.ch4, R.id.ch5, R.id.ch6,
                R.id.ch7, R.id.ch8, R.id.ch9, R.id.ch10, R.id.ch11, R.id.ch12};
        allRadioButton = new int[]{
                R.id.rbDelivery, R.id.rbPickup, R.id.rbToDoor
        };

        binding.btBuy.setOnClickListener(view -> {
            for(int ch: allCheckBox){
                checkBox = findViewById(ch);
                if(checkBox.isChecked()){
                    textView.setText(((String) textView.getText()).replace("Выбранные товары: ", ""));
                    if(textView.getText().equals("Пока ничего нет"))
                        textView.setText("Выбранные товары: " + checkBox.getText());
                    else
                        textView.setText("Выбранные товары: " + textView.getText() + "; " + checkBox.getText());
                }
            }
            for(int rb: allRadioButton){
                radioButton = findViewById(rb);
                if(radioButton.isChecked()){
                    textView.setText(((String) textView.getText()).replace("\"\\nТип доставки: \"", ""));
                    if(textView.getText().equals("Пока ничего нет"))
                        textView.setText("Нет выбранных товаров");
                    else
                        textView.setText(textView.getText() + "\nТип доставки: " + radioButton.getText());
                }
            }
        });
        binding.btClear.setOnClickListener(view -> {
            textView.setText("Пока ничего нет");

            for(int ch: allCheckBox){
                checkBox = findViewById(ch);
                if(checkBox.isChecked())
                    checkBox.setChecked(false);
            }
            for(int rb: allRadioButton){
                radioButton = findViewById(rb);
                if(radioButton.isChecked())
                    radioButton.setChecked(false);
            }
        });
    }
}