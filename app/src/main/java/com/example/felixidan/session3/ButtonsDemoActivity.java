package com.example.felixidan.session3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ButtonsDemoActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int STEP_SIZE = 8;
    private static final int MIN_COLOR_VALUE = 0;
    private static final int MID_COLOR_VALUE = 127;
    private static final int MAX_COLOR_VALUE = 255;
    public static final String HEX_RGB_FORMAT = "#%02X%02x%02x";

    int red = MIN_COLOR_VALUE;
    int green = MIN_COLOR_VALUE;
    int blue = MIN_COLOR_VALUE;

    LinearLayout background;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons_demo);

        background = (LinearLayout) findViewById(R.id.buttonsdemo_resultbackground);
        display = (TextView) findViewById(R.id.buttonsdemo_resulttextview);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCurrentColor();
            }
        });

        Button increaseGreen = (Button)findViewById(R.id.buttonsdemo_incGreen);
        increaseGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                green = calcNewValue(green, 1);
                refreshDisplay();
            }
        });
        Button increaseBlue = (Button)findViewById(R.id.buttonsdemo_incBlue);
        increaseBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blue = calcNewValue(blue, 1);
                refreshDisplay();
            }
        });

        Button decreaseRed = (Button)findViewById(R.id.buttonsdemo_decRed);
        decreaseRed.setOnClickListener(this);
        Button decreaseGreen = (Button)findViewById(R.id.buttonsdemo_decGreen);
        decreaseGreen.setOnClickListener(this);
        Button decreaseBlue = (Button)findViewById(R.id.buttonsdemo_decBlue);
        decreaseBlue.setOnClickListener(this);

        refreshDisplay();
    }

    private void sendCurrentColor() {
        String color = getHexStringForCurrentColor();
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, color);

        Intent chooser = Intent.createChooser(i, "Share this color");
        startActivity(chooser);

    }

    public void increaseRed(View view) {
        // ...
        red = calcNewValue(red, 1);
        refreshDisplay();
    }

    private int calcNewValue(int oldValue, int step) {
        int newValue = oldValue + (step * STEP_SIZE);
        if (newValue > MAX_COLOR_VALUE){
            newValue = MAX_COLOR_VALUE;
        }
        if (newValue < MIN_COLOR_VALUE){
            newValue = MIN_COLOR_VALUE;
        }
        return newValue;
    }

    private void refreshDisplay() {
        // String color = "#" + Integer.toHexString(red) + Integer.toHexString(green) + Integer.toHexString(blue);

        String color = getHexStringForCurrentColor();

        int pastMidPoint = 0;
        if (red > MID_COLOR_VALUE){
            pastMidPoint ++;
        }

        if (green > MID_COLOR_VALUE){
            pastMidPoint ++;
        }

        if (blue > MID_COLOR_VALUE){
            pastMidPoint ++;
        }

        if (pastMidPoint > 1){
            display.setTextColor(getResources().getColor(R.color.black));
        } else {
            display.setTextColor(getResources().getColor(R.color.white));
        }

        display.setText(color);

        background.setBackgroundColor(Color.parseColor(color));
    }

    private String getHexStringForCurrentColor() {
        return String.format(HEX_RGB_FORMAT, red, green, blue);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonsdemo_decRed:
                red = calcNewValue(red, -1);
                break;
            case R.id.buttonsdemo_decGreen:
                green = calcNewValue(green, -1);
                break;
            case R.id.buttonsdemo_decBlue:
                blue = calcNewValue(blue, -1);
                break;
        }

        refreshDisplay();

    }
}
