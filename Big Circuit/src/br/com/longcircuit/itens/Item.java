package br.com.longcircuit.itens;

import br.com.luvia.linear.Modelo3D;

public class Item {
	
	protected String name;
	
	protected Modelo3D model;
	
	public Item(String name){
		this.name = name;
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
	
}
