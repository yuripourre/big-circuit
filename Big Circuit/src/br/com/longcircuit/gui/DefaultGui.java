package br.com.longcircuit.gui;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class DefaultGui implements Drawable{

	private ImageLayer lowerBar;
	private ImageLayer avatarSlot;
	
	private ImageLayer lifeBar;
	private ImageLayer lifeFill;
	
	private ImageLayer foodBar;
	private ImageLayer foodFill;
	
	private ImageLayer energyBar;
	private ImageLayer energyFill;
	
	private ImageLayer expBar;
	private ImageLayer expFill;
	
	public DefaultGui(){
		super();
		
		//lowerBar = new ImageLayer(20, 500, "long/gui/lowerbar(fake).png");
		lowerBar = new ImageLayer(20, 500, "long/gui/lowerbar.png");
		
		lifeBar = new ImageLayer(lowerBar.getX()+35, lowerBar.getY()+8, "long/gui/lifebar.png");
		foodBar = new ImageLayer(lowerBar.getX()+48, lowerBar.getY()+28, "long/gui/foodbar.png");
		//energyBar = new ImageLayer(55, 508, "long/gui/energybar.png");
		//expBar = new ImageLayer(55, 508, "long/gui/expbar.png");
		 
	}
	
	@Override
	public void draw(Graphic g) {
			
		lowerBar.draw(g);
		lifeBar.draw(g);
		foodBar.draw(g);
	}
	
}
