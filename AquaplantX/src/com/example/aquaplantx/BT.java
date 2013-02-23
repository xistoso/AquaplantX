package com.example.aquaplantx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.text.format.DateFormat;

public class BT{
	
	
	Activity context;
	BluetoothAdapter mBluetoothAdapter;
	BTDevice bTDevice;
	
    BluetoothSocket mmSocket;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
   
	
	public InputStream getMmInputStream() {
		return mmInputStream;
	}

	public BT(String string, Activity c) {
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
        
        
        return "Bluetooth Opened";
    }
    
    String closeBT()
    {   
    	FetchTimeData.stopWorker = true;
    	try{
        mmOutputStream.close();
        mmInputStream.close();
        mmSocket.close();
    	}catch (Exception e) {
			return "Already closed or not connected:" + e;
		}
        
        return "Bluetooth Disconnected";
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
}
