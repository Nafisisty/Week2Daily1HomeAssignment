package com.example.week2daily1_homeassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserInputActivity extends AppCompatActivity {

    EditText yearEditText, makeEditText, modelEditText, colorEditText, engineEditText, transTypeEditText, titleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);

        yearEditText = findViewById(R.id.yearEditTextId);
        makeEditText = findViewById(R.id.makeEditTextId);
        modelEditText = findViewById(R.id.modelEditTextId);
        colorEditText = findViewById(R.id.colorEditTextId);
        engineEditText = findViewById(R.id.engineEditTextId);
        transTypeEditText = findViewById(R.id.transTypeEditTextId);
        titleEditText = findViewById(R.id.titleEditTextId);
    }

    public void onClick(View view) {

        String year = yearEditText.getText().toString();
        String make = makeEditText.getText().toString();
        String model = modelEditText.getText().toString();
        String color = colorEditText.getText().toString();
        String engine = engineEditText.getText().toString();
        String transType = transTypeEditText.getText().toString();
        String title = titleEditText.getText().toString();

        Car car = new Car(year, make, model, color, engine, transType, title);

        if(MainActivity.selectedButton == 1){

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("carObject", car);
            setResult(666, intent);
            finish();
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);

            Bundle aBundle = new Bundle();
            aBundle.putParcelable("carObject", car);
            intent.putExtras(aBundle);
            startActivity(intent);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("carInfo", car.getMake() +" " + car.getModel());
        editor.commit();
    }
}
