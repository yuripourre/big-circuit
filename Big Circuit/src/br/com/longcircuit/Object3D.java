package br.com.longcircuit;

import javax.media.opengl.GL2;

import br.com.luvia.core.GL2Drawable;
import br.com.luvia.linear.Modelo3D;

public class Object3D implements GL2Drawable{

	protected String name;
	
	protected Modelo3D model;
	
	public Object3D(double x, double y, double z){
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Modelo3D getModel() {
		return model;
	}

	public void setModel(Modelo3D model) {
		this.model = model;
	}

	@Override
	public void draw(GL2 gl) {
		model.draw(gl);
	}
		
}
