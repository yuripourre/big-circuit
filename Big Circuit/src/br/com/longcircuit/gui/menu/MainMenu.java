package br.com.longcircuit.gui.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.media.opengl.GLAutoDrawable;

import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.Button;
import br.com.etyllica.i18n.Language;
import br.com.etyllica.i18n.gui.label.MultiLangLabel;
import br.com.etyllica.layer.ImageLayer;
import br.com.longcircuit.scene.BigCircuit;
import br.com.luvia.core.ApplicationGL;


public class MainMenu extends ApplicationGL{

	public MainMenu(int w, int h) {
		super(w,h);
	}

	//private ImageLayer il = new ImageLayer("long/wallpaper.jpg");
	private ImageLayer il = new ImageLayer("mystic/mystic.png");

	public void init(GLAutoDrawable drawable) {

		System.out.println("Main Menu - Init Called");
	}

	public void load() {

		System.out.println("Main Menu - Load Called");

		//Play Button
		Map<Language, String> playTexts = new HashMap<Language, String>();
		playTexts.put(Language.ENGLISH_USA, "PLAY");
		playTexts.put(Language.PORTUGUESE_BRAZIL, "JOGAR");

		Button play = new Button(w/2-200/2, 220, 200, 60);
		play.setLabel(new MultiLangLabel(playTexts));
		play.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "playGameAction"));
		add(play);

		//Options Button
		Map<Language, String> optionsTexts = new HashMap<Language, String>();
		optionsTexts.put(Language.ENGLISH_USA, "OPTIONS");
		optionsTexts.put(Language.PORTUGUESE_BRAZIL, "OPCOES");

		Button options = new Button(w/2-200/2, 320, 200, 60);
		options.setLabel(new MultiLangLabel(optionsTexts));
		options.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "optionsAction"));
		add(options);

		//Exit Button
		Map<Language, String> exitTexts = new HashMap<Language, String>();
		exitTexts.put(Language.ENGLISH_USA, "EXIT");
		exitTexts.put(Language.PORTUGUESE_BRAZIL, "SAIR");

		Button exit = new Button(w/2-200/2, 420, 200, 60);
		exit.setLabel(new MultiLangLabel(exitTexts));
		add(exit);

		//Simulating Loads
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


	}

	public void playGameAction(){

		System.out.println("Main Menu - PlayGame Action");

		//TODO Change to BigCircuit
		returnApplication3D = new BigCircuit(w, h);

		//TODO Create List<GUIEvents> in Application 
		//addGUIEvent(GUIEvent.APPLICATION_CHANGED);
	}

	public void optionsAction(){

		System.out.println("Main Menu - Options Action");
		returnApplication3D = new OptionsMenu(w, h); 
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

		il.draw(g);

	}

	public void timeUpdate() {

	}

}