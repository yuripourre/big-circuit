package br.com.longcircuit.net.protocol;

import br.com.longcircuit.characters.hero.Hero;
import br.com.mystic.net.protocol.Protocol;

public class LongCircuitProtocol extends Protocol {

	private Hero hero;
	
	public LongCircuitProtocol() {
		super("LONG");
		
	}
	
	public void setHero(Hero hero){
		this.hero = hero;
	}

	@Override
	public void gerenciaMsgsTCP(String msg) {
		// TODO Auto-generated method stub
		
	}

	final String WALK_PREFIX = "WALK"; 
	
	@Override
	public void gerenciaMsgsUDP(String msg) {
		
		if(msg.startsWith(WALK_PREFIX)){
			
			System.out.println("Protocol receive: "+msg);
			
			String str = msg.substring(WALK_PREFIX.length()+1);
			
			String[] pieces = str.split(" ");
			
			String action = pieces[0];
			double angle = Double.parseDouble(pieces[1]);
			double x = Double.parseDouble(pieces[2]);
			double z = Double.parseDouble(pieces[3]);
			
			hero.setAction(action);
			
			hero.getModel().setAngleY(angle);
			hero.getModel().setX(x);
			hero.getModel().setZ(z);
			
									
		}
		
				
	}
		
}
