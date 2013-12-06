package br.com.longcircuit.characters.npc;

import br.com.etyllica.core.Drawable;
import br.com.longcircuit.characters.Player;

public class Npc extends Player implements Drawable{
	
	protected int code;
		
	public Npc(double x, double y, double z){
		super(x,y,z);		
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}