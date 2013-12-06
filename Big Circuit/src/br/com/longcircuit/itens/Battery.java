package br.com.longcircuit.itens;

import br.com.luvia.loader.mesh.MeshLoader;

public class Battery extends Item {

	public Battery() {
		super("Battery");
		
		model = MeshLoader.getInstance().loadModel("mech/itens/battery/battery.obj");
	}
	
	

	
}
