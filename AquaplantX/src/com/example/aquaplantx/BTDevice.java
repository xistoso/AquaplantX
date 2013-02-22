package com.example.aquaplantx;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

public class BTDevice {
	
	Activity context;
	String btDeviceName;
	BluetoothAdapter mBluetoothAdapter;
	BluetoothDevice mmDevice;
	
	public BTDevice(String string, Activity c) {
		btDeviceName = string;
		context = c;
	}
	
	
	boolean find()
    {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null)
        {
            return false;
        }
        
        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            context.startActivityForResult(enableBluetooth);
            
        }
        
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                if(device.getName().equals(btDeviceName)) 
                {
                    mmDevice = device;
                    break;
                }
            }
        }
        return true;
    }
	BluetoothDevice getDevice(){
    	return mmDevice;
    }
}
