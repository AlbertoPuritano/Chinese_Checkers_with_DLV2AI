package com.winnie.the.pooh;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("mossa")
public class Mossa {
	
	@Param(0)
	private int posizione;
	@Param(1)
	private int mossa;
	@Param(2)
	private int colore;
	@Param(3)
	private int rigaPos;
	@Param(4)
	private int rigaMos;
	
	private boolean isJump;
	
	public Mossa(int posizione, int mossa, int colore, boolean isJump) {
		this.posizione = posizione;
		this.mossa = mossa;
		this.colore = colore;
		rigaPos= calcolaRiga(posizione);
		rigaMos= calcolaRiga(mossa);
		this.isJump= isJump;
	}
	
	public int calcolaRiga(int posizione) {
		if(posizione==0)
			return 0;
		if(posizione == 1 || posizione == 2)
			return 1;
		if(posizione >= 3 && posizione <= 5)
			return 2;
		if(posizione >= 6 && posizione <= 9)
			return 3;
		if(posizione >= 10 && posizione <= 22)
			return 4;
		if(posizione >= 23 && posizione <= 34)
			return 5;
		if(posizione >= 35 && posizione <= 45)
			return 6;
		if(posizione >= 46 && posizione <= 55)
			return 7;
		if(posizione >= 56 && posizione <= 64) 
			return 8;
		if(posizione >= 65 && posizione <= 74)
			return 9;
		if(posizione >= 75 && posizione <= 85)
			return 10;
		if(posizione >= 86 && posizione <= 97) 
			return 11;
		if(posizione >= 98 && posizione <= 110)
			return 12;
		if(posizione >= 111 && posizione <= 114)
			return 13;
		if(posizione >= 115 && posizione <= 117)
			return 14;
		if(posizione == 118 || posizione == 119)
			return 15;
		if(posizione == 120)
			return 16;
		return 0;
	}
	
	
	public int getRigaPos() {
		return rigaPos;
	}
	public void setRigaPos(int rigaPos) {
		this.rigaPos = rigaPos;
	}
	public int getRigaMos() {
		return rigaMos;
	}
	public void setRigaMos(int rigaMos) {
		this.rigaMos = rigaMos;
	}
	public int getPosizione() {
		return posizione;
	}
	public int getMossa() {
		return mossa;
	}
	public int getColore() {
		return colore;
	}
	public boolean isJump() {
		return isJump;
	}
}
