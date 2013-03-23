package com.example.aquaplantx;

public class ListaMenu {
	public void setMenu(int num){
    	switch(num)
    	{
    	case 1:
    	{
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new OBT());
    		break;
    	}
    	case 2:
    	{
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new ClockDef());
    		GetTime.adapter.add(new CloseBT());
    		break;
    	}
    	case 3:
    	{
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new SetArduinoTime());
    		GetTime.adapter.add(new GetArduinoTime());
    		GetTime.adapter.add(new Back());
    		break;
    	}
    	
    	}
    	
    }
}
