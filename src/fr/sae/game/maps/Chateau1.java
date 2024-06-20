package fr.sae.game.maps;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.DialogueBox;
import fr.sae.game.Global;
import fr.sae.game.Warp;
import fr.sae.menus.MainMenu;
import fr.sae.menus.OptionMenu;
import fr.sae.menus.SetCharacterName;


public abstract class Chateau1 extends BasicGameState {
	
	public Chateau1(int stateID) {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Global.actualId = 51;

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input =gc.getInput();
		
		Global.updatePlayerMovement(input, Global.CollisionMapForet1,delta);
		Global.P1.AnimateWhileMoove();
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
	}
	
	@Override
	public int getID() {
		return 51;
	}


}
