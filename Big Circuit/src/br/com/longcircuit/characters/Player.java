package br.com.longcircuit.characters;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.video.Graphic;
import br.com.longcircuit.Object3D;
import br.com.luvia.linear.Point3D;

public class Player extends Object3D implements Drawable{

	protected Point3D namePosition;
	
	public Player(double x, double y, double z){
		super(x,y,z);
		
		namePosition = new Point3D(0,0,0);
		
	}
	
	@Override
	public void draw(Graphic g) {
		
		//System.out.println("draw "+name+" "+namePosition.getX()+","+namePosition.getY());
		
		g.write((int)Math.abs(namePosition.getX()), (int)Math.abs(namePosition.getY()), name);
	}

	public Point3D getNamePosition() {
		return namePosition;
	}

	public void setNamePosition(Point3D namePosition) {
		this.namePosition = namePosition;
	}
	
}