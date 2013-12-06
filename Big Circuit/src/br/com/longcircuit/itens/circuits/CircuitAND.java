package br.com.longcircuit.itens.circuits;

import br.com.longcircuit.itens.Item;
import br.com.luvia.loader.mesh.MeshLoader;

public class CircuitAND extends Item{

	public CircuitAND() {
		super("AND Circuit");
		
		model = MeshLoader.getInstance().loadModel("mech/itens/circuits/circuit.obj");
	}

}
