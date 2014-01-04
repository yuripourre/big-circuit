package br.com.longcircuit.gui.menu;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.media.opengl.GLAutoDrawable;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.Button;
import br.com.etyllica.i18n.Language;
import br.com.etyllica.i18n.gui.label.MultiLangLabel;
import br.com.luvia.core.ApplicationGL;


public class OptionsMenu extends ApplicationGL{

	public OptionsMenu(int w, int h) {
		super(w,h);
	}
	
	public void init(GLAutoDrawable drawable) {

		System.out.println("OptionsMenu - Init Called");

		/*GL2 gl = drawable.getGL().getGL2();

		gl.glViewport (0, 0, w, h);

		loading = 41;
		gl.glMatrixMode(GL2.GL_PROJECTION);

		loading = 52;
		gl.glLoadIdentity();

		loading = 55;
		glu.gluPerspective(60.0, (double)w/(double)h,1.0, 90.0);

		loading = 67;
		gl.glMatrixMode(GL2.GL_MODELVIEW);

		loading = 79;
		gl.glLoadIdentity();*/
		
	}
	
	public void load() {

		System.out.println("OptionsMenu - Load Called");
		
		//Play Button
		Map<Language, String> playTexts = new HashMap<Language, String>();
		playTexts.put(Language.ENGLISH_USA, "BACK");
		playTexts.put(Language.PORTUGUESE_BRAZIL, "VOLTAR");
		
		Button back = new Button(w/2-200/2, 220, 200, 60);
		back.setLabel(new MultiLangLabel(playTexts));
		back.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "backMenuAction"));
		add(back);
		
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
				
		loading = 100;
				
	}

	public void backMenuAction(){
				
		returnApplication3D = new MainMenu(w, h);
		//addGUIEvent(GUIEvent.APPLICATION_CHANGED);
	}
	
	public GUIEvent updateKeyboard(KeyEvent event) {

		System.out.println("updateKeyboard");

		return GUIEvent.NONE;
	}

	public void display(GLAutoDrawable drawable) {
		
	}

	public GUIEvent updateMouse(PointerEvent event) {

		return GUIEvent.NONE;
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	public void draw(Graphic g) {
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, w, h);

		g.setColor(Color.WHITE);
		g.drawShadow(50,50,"2D Stuff!");
		
	}

	public void timeUpdate() {

	}

}