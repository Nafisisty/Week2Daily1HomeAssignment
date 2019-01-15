package com.example.week2daily1_homeassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_CODE = 666;
    public static int selectedButton = 1;
    public static final String TAG = "tag_act_one";

    TextView yearTextView, makeTextView, modelTextView, colorTextView, engineTextView, transTypeTextView, titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        String carInfo = sharedPreferences.getString("carInfo", "No Value has been saved");


        Log.d(TAG, "" + carInfo);

        Car aCar = null;

        Intent passedIntent = getIntent();
        Bundle passedBundle = passedIntent.getExtras();
        if(passedBundle != null) {
             aCar = new Car();
             aCar = passedBundle.getParcelable("carObject");
        }

        yearTextView = findViewById(R.id.yearTextViewId);
        makeTextView = findViewById(R.id.makeTextViewId);
        modelTextView = findViewById(R.id.modelTextViewId);
        colorTextView = findViewById(R.id.colorTextViewId);
        engineTextView = findViewById(R.id.engineTextViewId);
        transTypeTextView = findViewById(R.id.transTypeTextViewId);
        titleTextView = findViewById(R.id.titleTextViewId);

        if(aCar != null) {
            yearTextView.setText(aCar.getYear());
            makeTextView.setText(aCar.getMake());
            modelTextView.setText(aCar.getModel());
            colorTextView.setText(aCar.getColor());
            engineTextView.setText(aCar.getEngine());
            transTypeTextView.setText(aCar.getTransmissionType());
            titleTextView.setText(aCar.getTitleStatus());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Intent passedIntentData = data;
            Car passedCarInfo = passedIntentData.getParcelableExtra("carObject");

            yearTextView.setText(passedCarInfo.getYear());
            makeTextView.setText(passedCarInfo.getMake());
            modelTextView.setText(passedCarInfo.getModel());
            colorTextView.setText(passedCarInfo.getColor());
            engineTextView.setText(passedCarInfo.getEngine());
            transTypeTextView.setText(passedCarInfo.getTransmissionType());
            titleTextView.setText(passedCarInfo.getTitleStatus());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.resultButtonId : {
                Intent intent = new Intent(this, UserInputActivity.class);
                startActivityForResult(intent, RESULT_CODE);
                break;
            }
            case R.id.goToNextActivityButtonId : {
                selectedButton = 2;
                Intent intent = new Intent(this, UserInputActivity.class);
                startActivity(intent);
            }
        }
    }
}
