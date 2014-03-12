package com.example.aquaplantx;

import android.os.CountDownTimer;

public class GetArduinoTime implements Work{
	public int work(){
    	new CountDownTimer(2400, 800){
    		public void onTick(long millisUntilFinished) {
    			String msg = "GT";
    			msg += "\n"; 
    			GetTime.mybt.sendData(msg);
    		}

    		public void onFinish() {
    			//After 60000 milliseconds (60 sec) finish current 
    			//if you would like to execute something when time finishes          
    		}
    	}.start();
    	return 0;
	}
	public String toString(){
		return "Hora Actual";
	}
}
