package com.example.aquaplantx;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class GetTime extends Activity {
	public static BT mybt;
	public static TextView myLabel;
	public static TextView timeLabel;
	public static TimePicker myTimePicker;
	public static NumberPicker myNumberPicker;
	public static int bomba;
	public BluetoothDispatcher bd;
	protected Intent intent;
	
	int BLUETOOTH_REQUEST = 1;
	
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time);
        
        Button connectButton = (Button)findViewById(R.id.connectButton);
        Button debugButton = (Button)findViewById(R.id.debugButton);
        
        bd = new BluetoothDispatcher();
        
        myLabel = (TextView)findViewById(R.id.textView1);
        mybt = new BT("FireFly-2B1A", this);
        
        intent = new Intent(this, Menu2Program.class);
		connectButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				myLabel.setText(mybt.openBT());
				if(mybt.isOpened()){
					mybt.read(bd);
					startActivity(intent);
					overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
				}
				
			}
		});
		
		debugButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
					startActivity(intent);
					overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			}
				
		});
        
    }
    
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    	if (requestCode == BLUETOOTH_REQUEST) {

    	     if(resultCode == RESULT_OK){
    	    	myLabel.setText(mybt.openBT());
    	     }

    	     if (resultCode == RESULT_CANCELED) {

    	    	 myLabel.setText("Please Enable BLuetooth");

    	     }
    	}
}//onAcrivityResult
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_get_time, menu);
        return true;
    }
    
    
    
}
