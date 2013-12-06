package br.com.longcircuit.scene;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;

public class BigCircuit extends Scene {

	public BigCircuit(int w, int h) {
		super(w, h);
	}
	
	@Override
	public void load() {

		System.out.println("BigCircuit Load");
		
		loading = 88;
		loadingPhrase = "Loading Models...";
		//loadModels();
		
		System.out.println("BigCircuit Load Models");
		
		loading = 99;		
		loadingPhrase = "Loading Textures...";
		//loadTextures();
		
		System.out.println("BigCircuit Load Textures");
		
		final ScheduledExecutorService loadSimulator = Executors.newSingleThreadScheduledExecutor();

		loadSimulator.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				loading+=3;

				if(loading<30){

					loadingPhrase = "Loading Nothing...";

				}else if(loading<50){

					loadingPhrase = "Loading Something...";

				}else if(loading<90){

					loadingPhrase = "Almost Loaded...";

				}else if(loading>=100){
					loading = 100;
					loadSimulator.shutdown();
				}

			}

		}, 25, 125, TimeUnit.MILLISECONDS);
		
		//loading = 100;

		System.out.println("BigCircuit Loaded");

	}


	@Override
	public void init(GLAutoDrawable drawable) {

		/*GL2 gl = drawable.getGL().getGL2();

		gl.glViewport (0, 0, w, h);

		gl.glMatrixMode(GL2.GL_PROJECTION);

		gl.glLoadIdentity();

		glu.gluPerspective(60.0, (double) w / (double) h, 0.1, 500.0);
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);

		gl.glLoadIdentity();*/
		
		loadingPhrase = "Loading 3d stuff...";
		
		loadGameModels();
		loadModels();
		loadTextures();
		
		loading = 10;

	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		if (event.isKeyDown(KeyEvent.TSK_J)) {
			model.setX(model.getX() - .1);
			System.out.println("mX = " + model.getX());
		} else if (event.isKeyDown(KeyEvent.TSK_L)) {
			model.setX(model.getX() + .1);
			System.out.println("mX = " + model.getX());
		}
		if (event.isKeyDown(KeyEvent.TSK_K)) {
			model.setY(model.getY() - .1);
			System.out.println("mY = " + model.getY());
		} else if (event.isKeyDown(KeyEvent.TSK_I)) {
			// model.setY(model.getY()+.1);
			// System.out.println("mY = "+model.getY());
		}

		if (event.isKeyDown(KeyEvent.TSK_T)) {

			if (model.isDrawTexture() == false) {
				model.setDrawTexture(true);
			} else {
				model.setDrawTexture(false);
			}

		}

		if (event.isKeyDown(KeyEvent.TSK_PONTO)) {
			animation.saveFrame();
		}

		return GUIEvent.NONE;
	}


	@Override
	public void display(GLAutoDrawable drawable) {

		//System.out.println("BigCircuit Display");
		
		GL2 gl = drawable.getGL().getGL2();

		//TODO TEST
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		gl.glLoadIdentity();
		gl.glRotated(10, 0, 0, 0);
		
		//Transform by Camera
		lookCamera(drawable.getGL().getGL2());		
				
		getMousePosition(gl,mx,h-my);
		
		if(click){

			getMousePositionAndTrata(gl,mx,h-my);
						
			click = false;
		}
		
		
		if("RUN".equals(hero.getAction())){

			//hero.walk();
			animation.nextFrame();

		}else{
			/*if(animation.getFrame()!=1){
				animation.setFrame(1);
			}*/
		}
		
		drawScene(gl);
		

		gl.glFlush();

	}
	
}