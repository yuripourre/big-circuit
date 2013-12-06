package br.com.longcircuit.characters.npc;

import br.com.luvia.loader.mesh.MeshLoader;

public class Oracle extends Npc{

	public Oracle(double x, double y, double z) {
		super(x, y, z);
		this.name = "Oracle";
		this.code = 0;
		
		model = MeshLoader.getInstance().loadModel("mech/block.obj");
		model.setX(x);
		model.setY(y);
		model.setZ(z);
		
	}

	
	
}
