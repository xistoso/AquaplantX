package com.example.aquaplantx;

public class SetProgramTime implements Work{
	public int work(){
		GetTime.mymenu.setMenu(5);
		return 0;
	}
	
	public String toString(){
		return "Definir Hora de Arranque";
	}

}
