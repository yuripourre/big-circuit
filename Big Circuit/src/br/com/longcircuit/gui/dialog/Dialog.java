package br.com.longcircuit.gui.dialog;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.video.Graphic;

public class Dialog implements Drawable{

	private List<String> messages;
	
	public Dialog(){
		super();
		
		messages = new ArrayList<String>();
	}
	
	public void addMessage(String message){
		messages.add(message);
	}
	
	public void draw(Graphic g){
		
	}
	
}
