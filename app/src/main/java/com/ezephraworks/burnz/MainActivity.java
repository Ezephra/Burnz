package com.ezephraworks.burnz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this,GameActivity.class);
        startActivity(i);
        finish();
    }
}
