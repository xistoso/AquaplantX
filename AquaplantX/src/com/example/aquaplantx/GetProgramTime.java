package com.example.aquaplantx;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class GetProgramTime extends Activity{
	
	private TextView myLabel;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_program_times);
		
		Button confirmButton = (Button) findViewById(R.id.confirmProgramTimeButton);
		Button cancelButton = (Button) findViewById(R.id.cancelProgramTimeButton);
		
		//TimePicker tp = (TimePicker) findViewById(R.id.tp2);
		
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		
		int screenWidth = size.x;
		int halfScreenWidth = (int)(screenWidth *0.5);
		
		//todo add get current arduino programed time
		
		myLabel = (TextView)findViewById(R.id.textViewGetProgramTimes);
		
		
		myLabel.setText("Defina o Tempo que a Bomba Trabalha");
		myLabel.setWidth(halfScreenWidth);
		
		confirmButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				TimePicker tp = (TimePicker) findViewById(R.id.tp2);
				int hour = tp.getCurrentHour();
				int minute = tp.getCurrentMinute();
				Intent returnIntent = new Intent();
				returnIntent.putExtra("hour",""+hour);
				returnIntent.putExtra("minute",""+minute);
				setResult(RESULT_OK,returnIntent);
				finish();
				overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
			}
		});
		
		cancelButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent();
				setResult(RESULT_CANCELED, returnIntent);
				finish();
				overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
			}
		});
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    Point size = new Point();
	    getWindowManager().getDefaultDisplay().getSize(size);
	    
	    int screenWidth = size.x;
		int halfScreenWidth = (int)(screenWidth *0.5);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    	myLabel.setWidth(halfScreenWidth);
	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	    	myLabel.setWidth(halfScreenWidth);
	    }
	}
	
	@Override
	public void onBackPressed() {
	    Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED, returnIntent);
	    super.onBackPressed();
	    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}
}
