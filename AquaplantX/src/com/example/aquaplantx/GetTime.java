package com.example.aquaplantx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.util.Calendar;
import java.util.Date;
import java.util.Set;
//import java.util.Timer;
//import java.util.TimerTask;
import java.util.UUID;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GetTime extends Activity {
	TextView myLabel;
	TextView timeLabel;
	BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    
    //To Read Carefully
    Thread workerThread;
    Thread sendworkerThread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time);
        Button connectButton = (Button)findViewById(R.id.button1);
        Button disconnectButton = (Button)findViewById(R.id.button2);
        Button timeButton = (Button)findViewById(R.id.button3);
        Button setTimeButton = (Button)findViewById(R.id.button4);
        myLabel = (TextView)findViewById(R.id.textView1);
        timeLabel = (TextView)findViewById(R.id.textView2);
        
        connectButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try 
                {
                    findBT();
                    openBT();
                }
                catch (IOException ex) { }
            }
        });
        
        disconnectButton.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v)
            {
                try 
                {
                    closeBT();
                }
                catch (IOException ex) { }
            }
		});
        
        timeButton.setOnClickListener(new View.OnClickListener() 
        {			
			@Override
			public void onClick(View v) {
				 try 
	             {
					 fetchTimeData();
					 sendData();
	             }
				 catch (IOException ex) { }
			}
		});
        
        setTimeButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				setTime();
			}
		});
    }
    
    void findBT()
    {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null)
        {
            myLabel.setText("No bluetooth adapter available");
        }
        
        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }
        
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                if(device.getName().equals("FireFly-2B1A")) 
                {
                    mmDevice = device;
                    break;
                }
            }
        }
        myLabel.setText("Bluetooth Device Found");
    }
    
    void openBT() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
        mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);        
        mmSocket.connect();
        mmOutputStream = mmSocket.getOutputStream();
        mmInputStream = mmSocket.getInputStream();
        
        
        myLabel.setText("Bluetooth Opened");
    }
    
    void closeBT() throws IOException
    {   
    	stopWorker = true;
        mmOutputStream.close();
        mmInputStream.close();
        mmSocket.close();
        
        myLabel.setText("Bluetooth Disconnected");
    }
    
    void fetchTimeData() {
    	final Handler handler = new Handler(); 
        final byte delimiter = 10; //This is the ASCII code for a newline character
        
        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {                
               while(!Thread.currentThread().isInterrupted() && !stopWorker)
               {
                    try 
                    {
                        int bytesAvailable = mmInputStream.available();                        
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;
                                    
                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                            timeLabel.setText(data);
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    } 
                    catch (IOException ex) 
                    {
                        stopWorker = true;
                    }
               }
            }
        });

        workerThread.start();
    }
    
    void setTime()  //acerta as horas pelas horas do sistema
    {
    	String msg = "SC";
    	Date d = new Date();
    	CharSequence s  = DateFormat.format("kkmm", d.getTime());
    	msg += s;
    	msg += "\n";
    	try {
			mmOutputStream.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		myLabel.setText(msg + "Sent");
    }

    void sendData() throws IOException
    {
    	new CountDownTimer(30000, 800){
    		public void onTick(long millisUntilFinished) {
    			String msg = "GT";
    			msg += "\n"; 
    			try {
    				mmOutputStream.write(msg.getBytes());
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			myLabel.setText("Data Sent");
    		}

    		public void onFinish() {
    			//After 60000 milliseconds (60 sec) finish current 
    			//if you would like to execute something when time finishes          
    		}
    	}.start();   						
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_get_time, menu);
        return true;
    }
    
}
