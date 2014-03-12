package com.example.aquaplantx;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class GetTime extends Activity {
	public static final int PUMP_RESULT = 0;
	public static BT mybt;
	public static ListaMenu mymenu;
	public static TextView myLabel;
	public static TextView timeLabel;
	public static TimePicker myTimePicker;
	public static NumberPicker myNumberPicker;
	public static ArrayAdapter<Work> adapter;
	public static int programa;
	public static int bomba;
	protected Intent intent;
	
	
	int BLUETOOTH_REQUEST = 1;
	
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time);
        
        Work[] values = new Work[] { new OBT()};
        
        ArrayList<Work> lst = new ArrayList<Work>();
        lst.addAll(Arrays.asList(values));
        
        adapter = new ArrayAdapter<Work>(this, android.R.layout.simple_list_item_1, lst);
        
        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        
        
       // Button setTimeButton = (Button)findViewById(R.id.menu_settings);
        
        myLabel = (TextView)findViewById(R.id.textView1);
        timeLabel = (TextView)findViewById(R.id.textView2);
        myTimePicker = (TimePicker)findViewById(R.id.timePicker1);
        myTimePicker.setVisibility(8);
        myTimePicker.setIs24HourView(true);
        myNumberPicker = (NumberPicker)findViewById(R.id.numberPicker1);
        myNumberPicker.setVisibility(8); 
        mybt = new BT("FireFly-2B1A", this);
        mymenu = new ListaMenu();
        
        listView.setOnItemClickListener(new OnItemClickListener() {
        	  @Override
        	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		  Work w = (Work)parent.getItemAtPosition(position);
        		  if(w.work()==7) startActivityForResult(intent, PUMP_RESULT);
        	  }
        });
        
        intent = new Intent(this, GetPumpTime.class);
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
    	    	mymenu.setMenu(2);
    	}

    	if (resultCode == RESULT_CANCELED) {

    	     myLabel.setText("Please Enable BLuetooth");

    		}
    	}//onAcrivityResult
    }
    
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
