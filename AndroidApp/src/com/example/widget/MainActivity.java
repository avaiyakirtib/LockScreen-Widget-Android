package com.example.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.androidapp.R;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
        .withDrawable(getResources().getDrawable(R.drawable.ic_launcher))
        .withButtonColor(Color.WHITE)
        .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
        .withMargins(0, 0, 55, 16)
        .create();
		
  		fabButton.setOnClickListener(this);
  		
 	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 Intent intent = new Intent(Intent.ACTION_DIAL); 
		 startActivity(intent); 
		
	}
}
