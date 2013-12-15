import br.com.longcircuit.scene.BigCircuit;
import br.com.luvia.Luvia;


public class MainCircuit extends Luvia{

	public MainCircuit(int w, int h) {
		super(w, h);
	}

	// Main program
	public static void main(String[] args) {

		MainCircuit engine = new MainCircuit(1024,576);
		engine.init();

	}
	
	@Override
	public void startGame(){

		/** Mystic **/
		//setMainApplication(new MysticLogin(w, h));
				
		//setMainApplication(new MainApplication(w, h));
		//setMainApplication(new SkelAnimation(w, h));
		//setMainApplication(new CompHouse(w, h));
		//setMainApplication(new LuviaTextureApp(w, h));
		
		
		//setMainApplication(new MechAnimation(w, h));
		
		setMainApplication(new BigCircuit(w, h));
		
		//setMainApplication(new MainMenu(w, h));
		
		
	}

}
