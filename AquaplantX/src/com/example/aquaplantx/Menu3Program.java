package com.example.aquaplantx;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu3Program extends Activity{
	protected Intent intent;
	protected Intent intent1;
	
	public static final int PUMP_RESULT = 0;
	public static final int PROGRAM_TIME_RESULT = 2;
	
	public static TextView myLabel;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_program);
		
		Button progTimeButton = (Button) findViewById(R.id.programTimeButton);
		Button pumpDosageButton = (Button) findViewById(R.id.pumpDosageButton);
		Button backButton = (Button) findViewById(R.id.backProgram2Button);
		
		
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		
		int screenWidth = size.x;
		int halfScreenWidth = (int)(screenWidth *0.5);
		
		
		myLabel = (TextView)findViewById(R.id.textViewMenu3);
		
		
		myLabel.setText("Programa" + Menu2Program.programa);
		myLabel.setWidth(halfScreenWidth);
		
		intent = new Intent(this, GetProgramTime.class);
		intent1 = new Intent(this, GetPumpTime.class);
		
		progTimeButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {				
					startActivityForResult(intent, PROGRAM_TIME_RESULT);
					overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
				}
		});
		
		pumpDosageButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {				
					startActivityForResult(intent1, PUMP_RESULT);
					overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == PUMP_RESULT) {
    		if(resultCode == RESULT_OK){
                String result=data.getStringExtra("result");
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
    	}
    	if (requestCode == PROGRAM_TIME_RESULT) {
    		if(resultCode == RESULT_OK){
    			/*hour = GetTime.myTimePicker.getCurrentHour();
		minute = GetTime.myTimePicker.getCurrentMinute();
		msg="P";
		msg+=(GetTime.programa-1)+"A";
		if(hour < 10)msg=msg+0;
		msg+=hour;
		if(minute < 10)msg=msg+0;
		msg+=minute+"\n";
		GetTime.mybt.sendData(msg);*/
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
    	}
}//onAcrivityResult
}
