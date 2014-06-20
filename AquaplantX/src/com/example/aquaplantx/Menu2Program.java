package com.example.aquaplantx;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu2Program extends Activity{
	protected Intent intent;
	protected Intent intent1;
	
	public static int programa;
	public static TextView myLabel;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button program1Button = (Button) findViewById(R.id.program1Button);
		Button program2Button = (Button) findViewById(R.id.program2Button);
		Button program3Button = (Button) findViewById(R.id.program3Button);
		Button program4Button = (Button) findViewById(R.id.program4Button);
		Button menuClockButton = (Button) findViewById(R.id.menuClockButton);
		Button backButton = (Button) findViewById(R.id.backProgramButton);
		
		
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		
		int screenWidth = size.x;
		int halfScreenWidth = (int)(screenWidth *0.5);
		
		
		myLabel = (TextView)findViewById(R.id.textViewMenu2);
		
		
		myLabel.setText("Escolha uma opção");
		myLabel.setWidth(halfScreenWidth);
		
		intent = new Intent(this, Menu3Program.class);
        intent1 = new Intent(this, GetArduinoClock.class);
		
		menuClockButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {				
					startActivity(intent1);
					overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
				}
		});
		
		program1Button.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
					myLabel.setText("Programa" + 1);
					programa = 1;
					startActivity(intent);
					overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
				}
				
		});
		
		program2Button.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {

					myLabel.setText("Programa" + 2);
					programa = 2;
					startActivity(intent);
					overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
				}
				
		});
		
		program3Button.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				myLabel.setText("Programa" + 3);
				programa = 3;
					startActivity(intent);
					overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
				}
				
		});
		
		program4Button.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				myLabel.setText("Programa" + 4);
				programa = 4;
					startActivity(intent);
					overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
				}
				
		});
		backButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				GetTime.mybt.closeBT();
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
	
	@Override
	public void onBackPressed() {
		GetTime.mybt.closeBT();
	    super.onBackPressed();
	    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}
}
