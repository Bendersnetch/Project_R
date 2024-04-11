package fr.sae.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.sae.game.caractere.Berserker;
import fr.sae.game.caractere.Healer;
import fr.sae.game.caractere.Mage;
import fr.sae.game.caractere.Mobs;
import fr.sae.game.caractere.Player;
import fr.sae.game.caractere.Swordsman;
import fr.sae.game.maps.Foret;

import java.util.HashMap;
import java.util.Map;


public class Global {
	
	//Parametrages de players 1 et 2
	public static Player P1 = null;
	public static Player P2 = null;
		
	//Vitesse de deplacemnts
	public static float speed =0.1f;
	
	//Local variable permettant de savoir qui a la main
	
	public static Integer id=null;
	
	//Variables definissant si on doit utiliser les touches de mouvements pour se deplacer dans les dialogbox ou pour deplacer le personnage
	
	public static boolean canMoovPlayer =true;
	public static boolean canMoovDialogbox =false;

	//Variables global d'input
	
	public static int up =Input.KEY_UP;
	public static int down =Input.KEY_DOWN;
	public static int left =Input.KEY_LEFT;
	public static int right =Input.KEY_RIGHT;
	
	public static int pause =Input.KEY_ESCAPE;
	public static int interract =Input.KEY_SPACE;
	

	//Hashmap de conversion des codes ascii des touches en noms
    public static Map<String, Integer> dictionnaireInputs = new HashMap<String, Integer>() {{
        put("UP", Input.KEY_UP);
        put("DOWN", Input.KEY_DOWN);
        put("LEFT", Input.KEY_LEFT);
        put("RIGHT", Input.KEY_RIGHT);
        put("SPACE", Input.KEY_SPACE);
        put("A", Input.KEY_A);
        put("B", Input.KEY_B);
        put("C", Input.KEY_C);
        put("D", Input.KEY_D);
        put("E", Input.KEY_E);
        put("F", Input.KEY_F);
        put("G", Input.KEY_G);
        put("H", Input.KEY_H);
        put("I", Input.KEY_I);
        put("J", Input.KEY_J);
        put("K", Input.KEY_K);
        put("L", Input.KEY_L);
        put("M", Input.KEY_M);
        put("N", Input.KEY_N);
        put("O", Input.KEY_O);
        put("P", Input.KEY_P);
        put("Q", Input.KEY_Q);
        put("R", Input.KEY_R);
        put("S", Input.KEY_S);
        put("T", Input.KEY_T);
        put("U", Input.KEY_U);
        put("V", Input.KEY_V);
        put("W", Input.KEY_W);
        put("X", Input.KEY_X);
        put("Y", Input.KEY_Y);
        put("Z", Input.KEY_Z);
        put("0", Input.KEY_0);
        put("1", Input.KEY_1);
        put("2", Input.KEY_2);
        put("3", Input.KEY_3);
        put("4", Input.KEY_4);
        put("5", Input.KEY_5);
        put("6", Input.KEY_6);
        put("7", Input.KEY_7);
        put("8", Input.KEY_8);
        put("9", Input.KEY_9);
        put("TAB", Input.KEY_TAB);
        put("ENTER", Input.KEY_ENTER);
        put("ESCAPE", Input.KEY_ESCAPE);
        put("INSERT", Input.KEY_INSERT);
        put("DELETE", Input.KEY_DELETE);
        put("HOME", Input.KEY_HOME);
        put("END", Input.KEY_END);
        put("F1", Input.KEY_F1);
        put("F2", Input.KEY_F2);
        put("F3", Input.KEY_F3);
        put("F4", Input.KEY_F4);
        put("F5", Input.KEY_F5);
        put("F6", Input.KEY_F6);
        put("F7", Input.KEY_F7);
        put("F8", Input.KEY_F8);
        put("F9", Input.KEY_F9);
        put("F10", Input.KEY_F10);
        put("F11", Input.KEY_F11);
        put("F12", Input.KEY_F12);
        put("NUMPAD0", Input.KEY_NUMPAD0);
        put("NUMPAD1", Input.KEY_NUMPAD1);
        put("NUMPAD2", Input.KEY_NUMPAD2);
        put("NUMPAD3", Input.KEY_NUMPAD3);
        put("NUMPAD4", Input.KEY_NUMPAD4);
        put("NUMPAD5", Input.KEY_NUMPAD5);
        put("NUMPAD6", Input.KEY_NUMPAD6);
        put("NUMPAD7", Input.KEY_NUMPAD7);
        put("NUMPAD8", Input.KEY_NUMPAD8);
        put("NUMPAD9", Input.KEY_NUMPAD9);
        put("ADD", Input.KEY_ADD);
        put("SUBTRACT", Input.KEY_SUBTRACT);
        put("MULTIPLY", Input.KEY_MULTIPLY);
        put("DIVIDE", Input.KEY_DIVIDE);
        put("EQUALS", Input.KEY_EQUALS);
        put("COMMA", Input.KEY_COMMA);
        put("PERIOD", Input.KEY_PERIOD);
        put("SEMICOLON", Input.KEY_SEMICOLON);
        put("SLASH", Input.KEY_SLASH);
        put("MINUS", Input.KEY_MINUS);
        put("SPACE", Input.KEY_SPACE);

        
        put("EXCLAMATION_MARK", (int) '!');
        put("DOUBLE_QUOTE", (int) '"');
        put("HASH", (int) '#');
        put("DOLLAR", (int) '$');
        put("PERCENT", (int) '%');
        put("AMPERSAND", (int) '&');
        put("SINGLE_QUOTE", (int) '\'');
        put("LEFT_PARENTHESIS", (int) '(');
        put("RIGHT_PARENTHESIS", (int) ')');
        put("ASTERISK", (int) '*');
        put("PLUS", (int) '+');
        put("COLON", (int) ':');
        put("LESS_THAN", (int) '<');
        put("EQUAL", (int) '=');
        put("GREATER_THAN", (int) '>');
        put("QUESTION_MARK", (int) '?');
        put("AT", (int) '@');
        put("LEFT_BRACKET", (int) '[');
        put("BACKSLASH", (int) '\\');
        put("RIGHT_BRACKET", (int) ']');
        put("CARET", (int) '^');
        put("UNDERSCORE", (int) '_');
        put("LEFT_BRACE", (int) '{');
        put("VERTICAL_BAR", (int) '|');
        put("RIGHT_BRACE", (int) '}');
        put("TILDE", (int) '~');
    }};
	// Taille de l'ecran 

	public static int width;
	public static int height;

	//Mobes
	
	public static Mobs[] mobs = new Mobs[4];
	
	//Characters
	public static Integer MainPlayer=null;
	
	public static String Player1Classe;
	public static String Player2Classe;
	
	public static String Player1Name="";	
	
	//Collision maps
	
	public static Collisions Map1= new Collisions();
	public static Collisions Map2= new Collisions();

	

	// Fonctions
	
	public static void InitializeGame() throws SlickException { //Fonction d'initialisation de project complet
		try {	
			
			// Initialisation de P1
			
			switch (Player1Classe) {
			    case "Swordman":
			        P1 = new Swordsman(Player1Name, 1, null);
			        break;
			    case "Berserker":
			        P1 = new Berserker(Player1Name, 1, null);
			        break;
			    case "Healer":
			        P1 = new Healer(Player1Name, 1, null);
			        break;
			    case "Mage":
			        P1 = new Mage(Player1Name, 1, null);
			        break;

			}

			// Initialisation de P2
			
			switch (Player2Classe) {
			    case "Swordman":
			        P2 = new Swordsman("Swordsman", 1, null);
			        break;
			    case "Berserker":
			        P2 = new Berserker("Berserker", 1, null);
			        break;
			    case "Healer":
			        P2 = new Healer("Healer", 1, null);
			        break;
			    case "Mage":
			        P2 = new Mage("Mage", 1, null);
			        break;
			}
			
			P2.setHitbox(null);

		    
		} catch(Exception e){
			
			e.getMessage(); //Affiche le message d'erreur en cas ou l'initialisation du project mne marche pas correctement ducoup c'est une ligne importante en cas de debug
			
			System.out.println("Erreur dans le global sur la fonction initialize");
		}
	}	
	
	public static void switchModeControles() { //Fonction  propre permettant de savoir si on est en dialoge ou en deplacmeent libre
		canMoovPlayer=!canMoovPlayer;
		canMoovDialogbox=!canMoovDialogbox;
	}

}
