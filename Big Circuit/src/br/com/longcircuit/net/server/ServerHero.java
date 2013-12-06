package br.com.longcircuit.net.server;

import br.com.longcircuit.characters.hero.Hero;
import br.com.luvia.linear.Point3D;

public class ServerHero extends Point3D{

	private Hero hero;
	
	private double speed = 0.8;
	private double angle = 0;
	
	private double targetX = 0;
	private double targetZ = 0;
	
	public ServerHero(Hero hero){
		super();
		
		this.hero = hero;
		x = hero.getModel().getX();
		z = hero.getModel().getZ();
		targetX = hero.getTargetX();
		targetZ = hero.getTargetZ();
		
		speed = hero.getSpeed();
		angle = hero.getModel().getAngleY();
	}
	
	public Hero getHero(){
		return hero;
	}
		
	public void walk(){
		
		double theta = Math.toRadians(angle);
		
		z += (speed * Math.cos(theta));
		x += (speed * Math.sin(theta));
		
	}
	
	public double getAngle(){
		return angle;
	}

	public double getTargetX() {
		return targetX;
	}

	public void setTarget(double targetX, double targetZ) {
		this.targetX = targetX;
		this.targetZ = targetZ;
		
		this.angle = getTargetAngle(targetX, targetZ);
	}

	public double getTargetZ() {
		return targetZ;
	}

	public double getTargetAngle(double mx, double mz) {
				
	    //double angle = (double) Math.toDegrees(Math.atan2(mx - x, mz - y));
		double angle = (double) Math.toDegrees(Math.atan2(mx - x, mz - z));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	public boolean reachTarget(){
		
		double rx = round(x);
		double tx = round(targetX);
		
		double rz = round(z);
		double tz = round(targetZ);
		
		
		if(rx!=tx){
			
			return false;
		}else if(rz!=tz){
			
			return false;
		}
		
		return true;
		
	}
	
	private double round(double d){
		
		final double precision = 10.0;
		
		//return ((double)Math.round(d*precision)/precision);
		return ((double)Math.round(d));
	}
	
}
