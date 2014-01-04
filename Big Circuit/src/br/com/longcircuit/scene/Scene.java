package br.com.longcircuit.scene;

import static javax.media.opengl.GL.GL_LINEAR;
import static javax.media.opengl.GL.GL_TEXTURE_2D;
import static javax.media.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static javax.media.opengl.GL.GL_TEXTURE_MIN_FILTER;

import java.awt.Color;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.video.Graphic;
import br.com.longcircuit.characters.npc.Npc;
import br.com.longcircuit.characters.npc.Oracle;
import br.com.longcircuit.itens.Battery;
import br.com.longcircuit.itens.circuits.CircuitAND;
import br.com.luvia.linear.Point3D;
import br.com.luvia.loader.TextureLoader;

import com.jogamp.opengl.util.texture.Texture;

public abstract class Scene extends GameScene {

	private Color markColor = Color.CYAN;

	private Texture floor;
	private Texture fire;
	private Texture crom;

	//Itens
	private Battery battery;
	private CircuitAND circuit;

	protected float mx = 0;
	protected float my = 0;

	//TODO Put in server
	//NPC
	//private Oracle oracle;

	public Scene(int w, int h) {
		super(w, h);
	}

	protected void loadModels(){

		//TODO Itens
		battery = new Battery();
		circuit = new CircuitAND();

		//TODO Npcs
		npcs.add(new Oracle(10, 0, 2));

	}

	protected void loadTextures(){
		floor = TextureLoader.getInstance().loadTexture("long/textures/trak_tile_g.jpeg");
		fire = TextureLoader.getInstance().loadTexture("long/textures/fire.png");
		crom = TextureLoader.getInstance().loadTexture("long/textures/crom.jpeg");
	}

	protected void drawScene(GL2 gl){

		drawAxis(gl);

		//TODO drawfloor
		//drawFloor(gl);

		drawItens(gl);

		drawNPCs(gl);
		
		drawHero(gl);

	}

	private void drawNPCs(GL2 gl){
		//battery.getModel().draw(gl);
		for(Npc npc: npcs){
			npc.draw(gl);
		}
	}

	private void drawItens(GL2 gl){
		//battery.getModel().draw(gl);
		circuit.getModel().draw(gl);
	}

	protected void drawHero(GL2 gl){
		
		double dummySize = 0.5;

		//Draw Hero Dummy
		gl.glColor3d(0.0, 0.0, 1.0);
		gl.glBegin(GL.GL_LINE_LOOP);

		gl.glVertex3d(model.getX()-dummySize, model.getY()-0.03, model.getZ()-dummySize);
		gl.glVertex3d(model.getX()-dummySize, model.getY()-0.03, model.getZ()+dummySize);
		gl.glVertex3d(model.getX()+dummySize, model.getY()-0.03, model.getZ()+dummySize);
		gl.glVertex3d(model.getX()+dummySize, model.getY()-0.03, model.getZ()-dummySize);		

		gl.glEnd();
		
		model.draw(gl);

	}

	protected void drawFloor(GL2 gl){

		gl.glColor3d(1,1,1);

		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		// Use linear filter for texture if image is smaller than the original texture
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

		drawGrid(gl,200,120);

	}

	private void drawGrid(GL2 gl, double x, double y){

		double tileSize = 5;

		crom.enable(gl);
		crom.bind(gl);

		for(int j=0;j<y;j++){

			/*if(j%2==0){
				fire.enable(gl);
				fire.bind(gl);
			}else{
				floor.enable(gl);
				floor.bind(gl);
			}*/

			for(int i=0;i<x;i++){
				drawTile(gl, i, j, tileSize);
			}
		}

		crom.disable(gl);
		//floor.disable(gl);
		//fire.disable(gl);
	}



	private void drawTile(GL2 gl, double x, double y, double tileSize){

		gl.glBegin(GL2.GL_QUADS);

		//(0,0)
		gl.glTexCoord2d(0, 0);
		gl.glVertex3d(x*tileSize, 0, y*tileSize);

		//(1,0)
		gl.glTexCoord2d(1, 0);
		gl.glVertex3d(x*tileSize+tileSize, 0, y*tileSize);

		//(1,1)
		gl.glTexCoord2d(1, 1);
		gl.glVertex3d(x*tileSize+tileSize, 0, y*tileSize+tileSize);

		//(0,1)
		gl.glTexCoord2d(0, 1);
		gl.glVertex3d(x*tileSize, 0, y*tileSize+tileSize);

		gl.glEnd();
	}

	private void drawAxis(GL2 gl){

		double axisSize = 100;

		//Draw Axis
		gl.glLineWidth(2.5f);

		//Draw X Axis
		gl.glColor3d(1.0, 0.0, 0.0);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3d(0.0, 0.0, 0.0);
		gl.glVertex3d(axisSize, 0, 0);
		gl.glEnd();

		//Draw Y Axis
		gl.glColor3d(0.0, 1.0, 0.0);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3d(0.0, 0.0, 0.0);
		gl.glVertex3d(0, axisSize, 0);
		gl.glEnd();

		//Draw Z Axis
		gl.glColor3d(0.0, 0.0, 1.0);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3d(0.0, 0.0, 0.0);
		gl.glVertex3d(0, 0, axisSize);
		gl.glEnd();

	}

	/*private void drawMouseLine(GL2 gl){
		gl.glColor3d(1.0, 1.0, 0.0);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3d(model.getX()+camera.getX(),camera.getY(),model.getZ()+camera.getZ());
		gl.glVertex3d(0, 0, 0);
		gl.glEnd();
	}*/

	@Override
	public void draw(Graphic g) {

		int size = 100;

		g.setColor(Color.RED);
		g.fillRect(w/2,50,20,20);

		g.setColor(Color.BLUE);
		g.drawRect(w/2-size/2, h/2-size/2, size, size);

		//Draw Gui
		g.setColor(Color.WHITE);
		//g.escreveSombra(20,20, "Scene",Color.BLACK);
		g.write(20,20,"Scene");
		//System.out.println("w = "+w);
		//System.out.println("h = "+h);
		//g.drawLine(w/2, h/2, w/2+mx, h/2+my);

		drawNames(g);
		
		gui.draw(g);
	}
	
	protected void drawNames(Graphic g){
		hero.draw(g);

		for(Npc npc: npcs){
			npc.draw(g);
		}
	}

	protected void getMousePosition(GL2 gl, float mx, float my) {

		Point3D p = get3DPointerFromMouse(gl, mx, my);

		drawCross(gl, p.getX(), p.getY(), p.getZ());

	}

	//TODO Remove
	protected void getMousePositionAndTrata(GL2 gl, float mx, float my) {

		Point3D p = get3DPointerFromMouse(gl, mx, my);

		trataMouse(gl, p.getX(), p.getZ());

	}

	private void drawCross(GL2 gl, double wx, double wy, double wz){

		verifyColision(wx,wz);

		double crossSize = 0.005;

		//Follow Line
		gl.glColor3d(1.0, 1.0, 0.0);
		gl.glBegin(GL.GL_LINES);

		gl.glVertex3d(wx-crossSize,wy,wz);
		gl.glVertex3d(wx+crossSize,wy,wz);

		gl.glVertex3d(wx,wy,wz-crossSize);
		gl.glVertex3d(wx,wy,wz+crossSize);

		gl.glVertex3d(model.getX(),model.getY(),model.getZ());
		gl.glVertex3d(wx,wy,wz);

		gl.glEnd();

		double dummySize = 0.5;

		//Draw Follow Dummy
		//gl.glColor3d(0.0, 1.0, 1.0);
		gl.glColor3d((double)markColor.getRed()/255, (double)markColor.getGreen()/255, (double)markColor.getBlue()/255);

		gl.glBegin(GL.GL_LINE_LOOP);

		gl.glVertex3d(wx-dummySize, wy, wz-dummySize);
		gl.glVertex3d(wx-dummySize, wy, wz+dummySize);
		gl.glVertex3d(wx+dummySize, wy, wz+dummySize);
		gl.glVertex3d(wx+dummySize, wy, wz-dummySize);		

		gl.glEnd();

		//Draw Orign Dummy
		gl.glBegin(GL.GL_LINE_LOOP);

		double fx = 0;
		double fz = 0;

		gl.glVertex3d(fx-dummySize, model.getY(), fz-dummySize);
		gl.glVertex3d(fx-dummySize, model.getY(), fz+dummySize);
		gl.glVertex3d(fx+dummySize, model.getY(), fz+dummySize);
		gl.glVertex3d(fx+dummySize, model.getY(), fz-dummySize);

		gl.glEnd();

	}

	private void verifyColision(double wx, double wz){

		for(Npc npc: npcs){

			if((Math.round(npc.getModel().getX())==Math.round(wx))&&
					(Math.round(npc.getModel().getZ())==Math.round(wz))){

				npcOver = npc;

				markColor = Color.MAGENTA;
				
				return;
			}else{
				
				markColor = Color.CYAN;
				
			}

		}

		npcOver = null;

	}

	public double getAngle(double mx, double mz) {

		double x = model.getX();
		double y = model.getY();

		double angle = (double) Math.toDegrees(Math.atan2(mx - x, mz - y));

		if(angle < 0){
			angle += 360;
		}

		return angle;
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glViewport ((int)x, (int)y, (int)w, (int)h);

		gl.glMatrixMode(GL2.GL_PROJECTION);

		gl.glLoadIdentity();

		glu.gluPerspective(60.0, (double) w / (double) h, 0.1, 500.0);

		gl.glMatrixMode(GL2.GL_MODELVIEW);

		gl.glLoadIdentity();

	}	

	protected boolean click = false;

	public GUIEvent updateMouse(PointerEvent event) {

		mx = event.getX();
		my = event.getY();

		if(event.onButtonDown(MouseButton.MOUSE_BUTTON_LEFT)){

			click = true;
		}

		if(event.onButtonUp(MouseButton.MOUSE_BUTTON_LEFT)){
			click = false;
		}

		return GUIEvent.NONE;
	}

}