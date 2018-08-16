package com.example.androiderasure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button mEraseButton = null;
    
    private Button mDrawingButton = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initEvent();
    }
    
    private void initEvent() {
        initViews();
        
        setViews();
    }
    
    private void initViews() {
        mEraseButton = (Button) findViewById(R.id.activity_main_finger_erase_button);
        mDrawingButton = (Button) findViewById(R.id.activity_main_finger_drawing_button);
    }
    
    private void setViews() {
        mEraseButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FingerEraseActivity.class));
            }
        });
        
        mDrawingButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FingerDrawingBoardActivity.class));
            }
        });
    }
}
