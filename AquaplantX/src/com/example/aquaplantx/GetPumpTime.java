package com.example.aquaplantx;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class GetPumpTime extends Activity{
	
	private TextView myLabel;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_pump_times);
		
		Button confirmButton = (Button) findViewById(R.id.confirmPumpButton);
		Button cancelButton = (Button) findViewById(R.id.cancelPumpButton);
		
		NumberPicker np = (NumberPicker) findViewById(R.id.np);
		
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		
		int screenWidth = size.x;
		int halfScreenWidth = (int)(screenWidth *0.5);
		
		np.setMaxValue(60);
		np.setMinValue(0);
		
		myLabel = (TextView)findViewById(R.id.textViewSetPumpTimes);
		
		
		myLabel.setText("Defina o Tempo que a Bomba Trabalha");
		myLabel.setWidth(halfScreenWidth);
		
		confirmButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				//todo return result
				finish();
			}
		});
		
		cancelButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				finish();
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
}
