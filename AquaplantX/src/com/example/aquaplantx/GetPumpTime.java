package com.example.aquaplantx;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GetPumpTime extends Activity{
	
	private TextView myLabel;
	private Spinner spinner1;
	private String pump;
	
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
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		
		confirmButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				NumberPicker np = (NumberPicker) findViewById(R.id.np);
				int number = np.getValue();
				pump = String.valueOf(spinner1.getSelectedItem());
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result",""+number);
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
		
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			 
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
			Toast.makeText(parent.getContext(), 
				"Selecionou : " + parent.getItemAtPosition(pos).toString(),
				Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?>parent) {
			
			}

		});
	}
	
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
