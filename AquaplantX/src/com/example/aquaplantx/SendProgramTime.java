package com.example.aquaplantx;

public class SendProgramTime implements ProcessData{
	
	int hour;
	int minute;
	String msg;
	
	public void process(){
		hour = GetTime.myTimePicker.getCurrentHour();
		minute = GetTime.myTimePicker.getCurrentMinute();
		msg="P";
		msg+=(GetTime.programa-1)+"A";
		if(hour < 10)msg=msg+0;
		msg+=hour;
		if(minute < 10)msg=msg+0;
		msg+=minute+"\n";
		GetTime.mybt.sendData(msg);
	}

}
