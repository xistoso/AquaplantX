package com.example.aquaplantx;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GetTime extends Activity {
	public static BT mybt;
	public static TextView myLabel;
	public static TextView timeLabel;
	
	int BLUETOOTH_REQUEST = 1;
	
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time);
        
        Work[] values = new Work[] { new GetArduinoTime(), new SetArduinoTime(),new OBT(),new CloseBT()};
        
        ArrayAdapter<Work> adapter = new ArrayAdapter<Work>(this, android.R.layout.simple_list_item_1, values);
        
        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        
       // Button setTimeButton = (Button)findViewById(R.id.menu_settings);
        
        myLabel = (TextView)findViewById(R.id.textView1);
        timeLabel = (TextView)findViewById(R.id.textView2);
        mybt = new BT("FireFly-2B1A", this);
        
        listView.setOnItemClickListener(new OnItemClickListener() {
        	  @Override
        	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		  Work w = (Work)parent.getItemAtPosition(position);
        		  w.work();
        	  }
        });
        
//		setTimeButton.setOnClickListener(new View.OnClickListener()
//        {
//			
//			@Override
//			public void onClick(View v) {
//				mybt.setTime();
//			}
//		});
        
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    	if (requestCode == 1) {

    	     if(resultCode == RESULT_OK){
    	    	myLabel.setText(mybt.openBT());

    	}

    	if (resultCode == RESULT_CANCELED) {

    	     myLabel.setText("Please Enable BLuetooth");

    		}
    	}//onAcrivityResult
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_get_time, menu);
        return true;
    }
    
}
