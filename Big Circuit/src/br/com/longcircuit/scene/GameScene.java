package br.com.longcircuit.scene;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

import br.com.longcircuit.characters.Player;
import br.com.longcircuit.characters.hero.Hero;
import br.com.longcircuit.characters.npc.Npc;
import br.com.longcircuit.gui.DefaultGui;
import br.com.longcircuit.net.protocol.LongCircuitProtocol;
import br.com.longcircuit.net.server.Server;
import br.com.longcircuit.quest.FindOracleQuest;
import br.com.longcircuit.quest.Quest;
import br.com.longcircuit.quest.QuestEvent;
import br.com.longcircuit.quest.QuestEventType;
import br.com.luvia.animation.Animation;
import br.com.luvia.core.ApplicationGL;
import br.com.luvia.linear.Modelo3D;
import br.com.luvia.util.Camera;

public abstract class GameScene extends ApplicationGL {

	protected List<Npc> npcs = new ArrayList<Npc>();

	protected Npc npcOver = null;

	private Server server;
	//Quest to Server
	private Quest quest = new FindOracleQuest();

	protected DefaultGui gui;

	private LongCircuitProtocol protocol;

	private Camera camera;

	//In Game Stuff
	protected Hero hero;
	protected Modelo3D model;
	protected Animation animation;


	public GameScene(int w, int h) {
		super(w, h);

		gui = new DefaultGui();

		protocol = new LongCircuitProtocol(); 

		server = new Server(protocol);

		camera = new Camera(0,10,-5);
	}

	protected void lookCamera(GL2 gl){
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();

		//glu.gluLookAt( camera.getX(), camera.getY(), camera.getZ(), model.getX(), model.getY(), model.getZ(), 0, 1, 0 );
		glu.gluLookAt( model.getX()+camera.getX(), camera.getY(), model.getZ()+camera.getZ(), model.getX(), model.getY(), model.getZ(), 0, 1, 0 );

		//gl.glTranslated(-model.getX(),-model.getY(), -model.getZ());
		//gl.glRotated(model.getAngleY(), 0, 1, 0);
		//gl.glTranslated(model.getX(),model.getY(), model.getZ());

		//TODO Supondo que o personagem andou
		updateNPCs(gl);
	}

	protected void loadGameModels(){

		hero = new Hero(0,0,0);
		model = hero.getModel();
		animation = hero.getAnimation();

		protocol.setHero(hero);
		server.addHero(hero);

	}

	private void openDialog(){

		if(npcOver!=null){
			System.out.println("Open Dialog");
		}

	}

	private void trataQuests(){

		//if(hasQuests){

		if(npcOver!=null){

			if(!quest.isCompleted()){
				
				QuestEvent event = quest.getCurrentEvent();

				if(event.getType()==QuestEventType.TALK_PERSON){
					if(npcOver.getCode()==event.getCode()){
						quest.eventConcluded();
					}
				}
				
			}
			
		}

	}

	//private
	protected void trataMouse(GL2 gl, double wx, double wz){
		//model.setCoordenadas(wx,model.getY(),model.getZ()/*model.getZ()+wz-model.getZ()*/);
		//model.setCoordenadas(wx,model.getY(),wz);
		//double speed = 2;
		//camera.setCoordenadas(wx-model.getX()+camera.getX(),camera.getY(),wz-model.getZ()+camera.getZ());
		//model.setCoordenadas(wx,model.getY(),wz);

		//model.setAngleY(getAngle(wx, wz));

		trataQuests();

		openDialog();

		server.setTarget(hero, wx, wz);

	}

	private void updateNPCs(GL2 gl){

		updatePlayerName(gl, hero);

		for(Npc npc: npcs){

			updatePlayerName(gl, npc);
		}
	}

	private void updatePlayerName(GL2 gl, Player player){

		final int X = 0;
		final int Y = 1;
		final int Z = 2;

		double[] pos = get2DPositionFromPoint(gl, player.getModel().getX(), player.getModel().getY(), player.getModel().getZ());

		player.getNamePosition().setX(pos[X]);
		player.getNamePosition().setY(h-pos[Y]+34);
	}

}
