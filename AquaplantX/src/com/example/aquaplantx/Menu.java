package com.example.aquaplantx;

public class Menu {
	public void setMenu(int num){
    	switch(num)
    	{
    	case 1 :
    	{
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new OBT());
    	}
    	case 2 :
    	{
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new SetArduinoTime());
    		GetTime.adapter.add(new GetArduinoTime());
    		GetTime.adapter.add(new CloseBT());
    	}
    	default:GetTime.adapter.clear();
    	}
    	
    }
}
