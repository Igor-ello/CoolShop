package com.example.coolshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coolshop.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TextView textView;
    private int[] allCheckBox;
    private CheckBox checkBox;
    private int[] allRadioButton;
    private RadioButton radioButton;
    private Boolean isSelectedProducts = Boolean.FALSE,
            isSelectedDelivery = Boolean.FALSE;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initResources();

        binding.btUpdate.setOnClickListener(view -> {
            resetToDefault();
            for(int ch: allCheckBox){
                checkBox = findViewById(ch);
                if(checkBox.isChecked()){
                    isSelectedProducts = Boolean.TRUE;
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
                    isSelectedDelivery = Boolean.TRUE;
                    textView.setText(((String) textView.getText()).replace("\"\\nТип доставки: \"", ""));
                    textView.setText(textView.getText() + "\nТип доставки: " + radioButton.getText());
                }
            }
            if(!isSelectedProducts) textView.setText("Выберите продукты");
            if(!isSelectedDelivery) textView.setText("Выберите тип доставки");
        });
        binding.btBuy.setOnClickListener(view -> {
            if(isSelectedProducts && isSelectedDelivery) {
                clearTextView();
                textView.setText("Спасибо за покупку!");
            }
            else{
                Toast.makeText(this, "Нажмите кнопку Update перед покупкой", Toast.LENGTH_LONG).show();
            }
        });
        binding.btClear.setOnClickListener(view -> {
            clearTextView();
        });
    }

    private void resetToDefault(){
        textView.setText("Пока ничего нет");
        isSelectedProducts = Boolean.FALSE;
        isSelectedDelivery = Boolean.FALSE;
    }

    private void clearTextView(){
        resetToDefault();

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
    }

    private void initResources(){
        textView = findViewById(R.id.textInfo);

        allCheckBox = new int[]{
                R.id.ch1, R.id.ch2, R.id.ch3, R.id.ch4, R.id.ch5, R.id.ch6,
                R.id.ch7, R.id.ch8, R.id.ch9, R.id.ch10, R.id.ch11, R.id.ch12};
        allRadioButton = new int[]{
                R.id.rbDelivery, R.id.rbPickup, R.id.rbToDoor
        };
    }
}