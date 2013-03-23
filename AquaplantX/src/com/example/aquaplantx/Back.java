package com.example.aquaplantx;

public class Back implements Work {
	public void work(){
		GetTime.mymenu.setMenu(2);
	}
	
	@Override
	public String toString(){
		return "Voltar"; 
	}
}
