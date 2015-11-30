package com.example.felixidan.session3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NonVoidListenersDemoActivity extends ActionBarActivity {

    boolean isLightOn = false;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_void_listeners_demo);

        display = (TextView)findViewById(R.id.nonvoidlisterens_display);

        Button falseTouch = (Button)findViewById(R.id.nonvoidlisterens_returnFalse);
        falseTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        Button trueTouch = (Button)findViewById(R.id.nonvoidlisterens_returnTrue);
        trueTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        refreshDisplay();

    }

    private void refreshDisplay() {
        String text = "Light is " + (isLightOn ? "on" : "off");
        display.setText(text);
    }

    public void toggleTheLight(View view) {
        isLightOn = !isLightOn;
        refreshDisplay();
    }
}
