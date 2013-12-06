package br.com.longcircuit.net.server;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.SheetCollate;

import br.com.longcircuit.characters.hero.Hero;
import br.com.longcircuit.itens.Item;
import br.com.longcircuit.net.protocol.LongCircuitProtocol;

public class Server implements Runnable{

	private final int UPDATE_TIME = 100;

	private LongCircuitProtocol protocol;	

	//private Set<ServerHero> heroes;
	private ServerHero hero;
	
	private Set<Item> items;

	public Server(LongCircuitProtocol protocol){
		super();

		this.protocol = protocol;

		//heroes = new HashSet<ServerHero>();
		items = new HashSet<Item>();

		new Thread(this).start();

	}

	public void addHero(Hero hero){
		System.out.println("Add Hero "+hero.getName());

		//heroes.add(new ServerHero(hero));
		this.hero = new ServerHero(hero);
	}

	public void addItem(Item item){
		items.add(item);
	}

	//TODO Update game 
	public void run(){

		while(true){

			System.out.println("Server Update");

			//for(ServerHero hero: heroes){
			if(hero!=null){

				//TODO Obstacles
				if(!hero.reachTarget()){

					System.out.println("Server walk");

					hero.walk();

					protocol.gerenciaMsgsUDP("WALK RUN "+hero.getAngle()+" "+hero.getX()+" "+hero.getZ());
				}else{
					protocol.gerenciaMsgsUDP("WALK STAND "+hero.getAngle()+" "+hero.getX()+" "+hero.getZ());
				}
			//}
			}
			
			try {
				Thread.sleep(UPDATE_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	//TODO Send to ServerProtocol
	public void setTarget(Hero hero, double tx, double tz){
			
		//TODO getServerHero by hero
		
		this.hero.setTarget(tx,tz);
		
		System.out.println("Server.SetTarget");
	}

}
