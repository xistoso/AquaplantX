package com.example.aquaplantx;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GetArduinoClock extends Activity{
	
	public static TextView myLabel;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arduino_clock);
		
		Button getClockButton = (Button) findViewById(R.id.getClockButton);
		Button setClockButton = (Button) findViewById(R.id.setClockButton);
		Button backButton = (Button) findViewById(R.id.backClockButton);
		
		
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		
		int screenWidth = size.x;
		int halfScreenWidth = (int)(screenWidth *0.5);
		
		
		myLabel = (TextView)findViewById(R.id.textViewClock);
		
		
		myLabel.setText("Selecione uma opção:");
		myLabel.setWidth(halfScreenWidth);
		
		getClockButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {				
				new CountDownTimer(2400, 800){
		    		public void onTick(long millisUntilFinished) {
		    			String msg = "GT";
		    			msg += "\n"; 
		    			GetTime.mybt.sendData(msg);
		    		}

		    		public void onFinish() {
		    			//After 60000 milliseconds (60 sec) finish current 
		    			//if you would like to execute something when time finishes          
		    		}
		    	}.start();
				}
		});
		
		setClockButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				GetTime.mybt.setTime();
			}
		});
		backButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
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
	    super.onBackPressed();
	    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}
}
