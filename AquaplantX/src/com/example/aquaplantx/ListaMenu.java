package com.example.aquaplantx;

public class ListaMenu {
	public void setMenu(int num){
    	switch(num)
    	{
    	case 1:
    	{
    		GetTime.myTimePicker.setVisibility(8);
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new OBT());
    		break;
    	}
    	case 2:
    	{
    		GetTime.myTimePicker.setVisibility(8);
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new ClockDef());
    		GetTime.adapter.add(new Programa(1));
    		GetTime.adapter.add(new Programa(2));
    		GetTime.adapter.add(new Programa(3));
    		GetTime.adapter.add(new Programa(4));
    		GetTime.adapter.add(new CloseBT());
    		break;
    	}
    	case 3:
    	{
    		GetTime.myTimePicker.setVisibility(8);
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new SetArduinoTime());
    		GetTime.adapter.add(new GetArduinoTime());
    		GetTime.adapter.add(new Back(2));
    		break;
    	}
    	case 4:
    	{
    		GetTime.myTimePicker.setVisibility(8);
       		GetTime.timeLabel.setText(null);
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new SetProgramTime());
    		GetTime.adapter.add(new SetPumpTimes());
    		GetTime.adapter.add(new Back(2));
    		break;
    	}
    	case 5:
    	{
    		GetTime.myTimePicker.setVisibility(0);
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new Confirm(4, new SendProgramTime()));
    		GetTime.adapter.add(new Back(4));
    		break;
    	}
    	case 6:
    	{
    		GetTime.myTimePicker.setVisibility(8);
    		GetTime.timeLabel.setText(null);
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new Pump(1));
    		GetTime.adapter.add(new Pump(2));
    		GetTime.adapter.add(new Pump(3));
    		GetTime.adapter.add(new Pump(4));
    		GetTime.adapter.add(new Back(4));
    		break;
    	}
    	case 7:
    	{
    		GetTime.myTimePicker.setVisibility(0);
    		GetTime.adapter.clear();
    		GetTime.adapter.add(new Confirm(4, new SendProgramTime()));
    		GetTime.adapter.add(new Back(4));
    		break;
    	}
    	
    	}
    	
    }
}
