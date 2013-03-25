package com.example.aquaplantx;

public class SetProgramTime implements Work{
	public void work(){
		GetTime.mymenu.setMenu(5);
	}
	
	public String toString(){
		return "Definir Hora de Arranque";
	}

}
