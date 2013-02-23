package com.example.aquaplantx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.text.format.DateFormat;

public class BT{
	
	
	Activity context;
	BluetoothAdapter mBluetoothAdapter;
	BTDevice bTDevice;
	
    BluetoothSocket mmSocket;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;
    Thread sendworkerThread;
    byte[] readBuffer;
    int readBufferPosition;
    boolean opened;
    volatile boolean stopWorker;
   
	
	public InputStream getMmInputStream() {
		return mmInputStream;
	}

	public BT(String string, Activity c) {
		opened = false;
		context = c;
		bTDevice = new BTDevice(string,c);
	}
    
    String openBT(){
    	if(bTDevice.find()){
    	try{
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
        mmSocket = bTDevice.getDevice().createRfcommSocketToServiceRecord(uuid);        
        mmSocket.connect();
        mmOutputStream = mmSocket.getOutputStream();
        mmInputStream = mmSocket.getInputStream();
        }catch (Exception e) {
			return "No Device in range!";
		}
    	}else{
    		return "No Bluetooth Adapter Found";
    	}
        
        opened = true;
        return "Bluetooth Opened";
    }
    
    String closeBT()
    {   
    	try{
        mmOutputStream.close();
        mmInputStream.close();
        mmSocket.close();
    	}catch (Exception e) {
			return "Already closed or not connected:" + e;
		}
        opened = false;
        return "Bluetooth Disconnected";
    }
    
    void read(){
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
                                            GetTime.timeLabel.setText(data);
                                            //this goes for a dispatcher that is going to be created in the future to read all the commands.
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
    
    
    
    String setTime()  //acerta as horas pelas horas do sistema
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
		return msg + "Sent";
    }

    void sendData(String msg)
    { 
    			try {
    				mmOutputStream.write(msg.getBytes());
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    }
    
    void stopWorker(){
    	stopWorker = true;
    	
    }
    
    boolean isOpened(){
    	return opened;
    }
}
