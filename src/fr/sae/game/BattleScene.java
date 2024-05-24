package fr.sae.game;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import fr.sae.game.Global;
import fr.sae.game.caractere.Entity;
import fr.sae.game.caractere.Mobs;
import fr.sae.mobes.Chaton;

public class BattleScene extends BasicGameState {

    private Mobs[] enemy;
    private int currentTurn=0; // Ajout de la variable currentTurn
    private DialogueBox dialogueBox; // Ajout de la variable dialogueBox
    private DialogueBox dialogueBoxTourP1;
    private DialogueBox dialogueBoxTourP2;
    private DialogueBox dialogueBoxTourCurrentMobAttaque;
    private DialogueBox dialogueBoxTourCurrentMobSoin;
    private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});
    private String P1Name = "";
    private String P2Name = "";
    
    
    
    // turn managment
    private int currentTurnIndex=0;
    protected ArrayList<Entity> entities = new ArrayList<>();


    public BattleScene(int stateID) {
        this.enemy = Global.mobs;
        
        this.currentTurn = 0; // Initialisation de currentTurn à 0

        // Initialisation de la DialogueBox
        this.dialogueBox = new DialogueBox(new String[] {
            "C'est votre tour. Que voulez-vous faire ?",
            "C'est le tour de l'ennemi."
        });
       
        
        this.dialogueBoxTourP1 = new DialogueBox(new String[] {
    			"\n "+
    					"     \n" +
    					"           C'est à vous de jouer"
    	});
    	this.dialogueBoxTourP1.setChoices(Arrays.asList("Attaquer", "Défendre","Fuir"), choice -> {
            switch (choice) {
            case 0:
                // Gérer l'attaque
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 1:
                // Gérer la défense
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 2:
                // Gérer la fuite
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
        }
    });
    	
    	this.dialogueBoxTourP2 = new DialogueBox(new String[] {
    			"\n "+
    					"     \n" +
    					"           C'est à vous de jouer"
    	});
    	this.dialogueBoxTourP2.setChoices(Arrays.asList("Attaquer", "Défendre", "Fuir"), choice -> {
            switch (choice) {
            case 0:
                // Gérer l'attaque
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 1:
                // Gérer la défense
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 2:
                // Gérer la fuite
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
        }
    });
    	
    	this.dialogueBoxTourCurrentMobAttaque= new DialogueBox(new String[] {
    			"\n "+
    					"     \n" +
    					"           Bob attaque"
    	});
    	
    	
    	this.dialogueBoxTourCurrentMobAttaque.setChoices(Arrays.asList("Continuer"), choice -> {
            switch (choice) {
            case 0:
                // Gérer l'attaque
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
        }
    });
    	
    	this.dialogueBoxTourCurrentMobSoin= new DialogueBox(new String[] {
    		"\n "+
					"     \n" +
					"           Bob se soigne"
    	});
    	
    	this.dialogueBoxTourCurrentMobSoin.setChoices(Arrays.asList("Continuer"), choice -> {
            switch (choice) {
            case 0:
                // Gérer l'attaque
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
        }
    });
    	
    }
    
    
    
    public void initializeBattle() {
        if(this.entities.isEmpty()) {
        	entities.add(Global.P1);
            
            for(int i=0; i < this.enemy.length; i++) {
            	if(i == 1 && this.entities.indexOf(Global.P2) == -1) {
            		entities.add(Global.P2);
            		i--;
            	} else if(this.enemy[i]!= null) {
            		entities.add(this.enemy[i]);
            	}
            }
        }
        
        this.P1Name = Global.P1.getName();
        this.P2Name = Global.P2.getName();        
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Initialisation des ressources de la scène de combat
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(new Image("data/BattleScenes/Bottom.png").getScaledCopy(Global.width, Global.height), 0, 0);
        g.drawImage(new Image("data/BattleScenes/Foret.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        this.initializeBattle();
        // gère l'affichage de la dialogBox pour l'entité qui joue le tour
        
        if(this.currentTurnIndex == 0) {        	
        	this.dialogueBoxTourP1.renderForceDialogbox(g);
        	this.dialogueBoxTourP1.render(g);
        } else if(this.currentTurnIndex == 2) {        	
        	this.dialogueBoxTourP2.renderForceDialogbox(g);
        	this.dialogueBoxTourP2.render(g);
        } else {
        	
        	if(this.entities.get(currentTurnIndex).getHpActuel() < this.entities.get(currentTurnIndex).getHpMax()) {
        		this.dialogueBoxTourCurrentMobSoin.renderForceDialogbox(g);
            	this.dialogueBoxTourCurrentMobSoin.render(g);
        	} else {
        		int hit = ((Chaton) this.entities.get(currentTurnIndex)).griffesHit();
        		this.dialogueBoxTourCurrentMobAttaque.renderForceDialogbox(g);
            	this.dialogueBoxTourCurrentMobAttaque.render(g);
            	
            	if (!Global.P1.isDead()) { // foutre des dégats au plus faible (oui c'est du harcèlement)
            		Global.P1.getHit(hit);
            		System.out.println( "-"+ hit + " pour P1");
            	} else {
            		Global.P2.getHit(hit);
            		System.out.println( "-"+ hit + " pour P2");
            	}
            	
        	}
        }
    	//this.dialogueBox.render(g);

        // Dessin des éléments de la scène de combat

        // Dessin des joueurs à gauche
    	try {
    	    int player1Y = Global.height / 3; // Position du joueur 1 sur le premier tiers vertical de l'écran
    	    // Appel de la méthode BattleScene pour le joueur 1
    	    Global.P1.BattleScene(g, player1Y);
    	} catch(Exception e) {
    	    // Affichage de l'erreur
    	    System.out.println("Affichage des Hitbox prsk sprites ont buggé --> on est dans la classe Battle scene dans le render le 1er try pour P1");
    	    System.out.println(e);
    	    // Tentative d'affichage des hitbox
    	    try {
    	        // Récupération de la hitbox du joueur 1
    	        Shape hitbox1 = Global.P1.getBattlehitbox();
    	        // Affichage de la hitbox
    	        g.draw(hitbox1);
    	    } catch(Exception ex) {
    	        // Génération d'une nouvelle exception si aucune hitbox n'est trouvée pour le joueur 1
    	        throw new RuntimeException("Aucune hitbox trouvée pour le joueur 1", ex);
    	    }
    	}

    	try {
    	    int player2Y = Global.height / 3 * 2; // Position du joueur 2 sur le deuxième tiers vertical de l'écran
    	    // Appel de la méthode BattleScene pour le joueur 2
    	    Global.P2.BattleScene(g, player2Y);
    	} catch(Exception e) {
    	    // Affichage de l'erreur
    	    System.out.println("Affichage des Hitbox prsk sprites ont buggé --> on est dans la classe Battle scene dans le render le 1er try pour P2");
    	    System.out.println(e);
    	    // Tentative d'affichage des hitbox
    	    try {
    	        // Récupération de la hitbox du joueur 2
    	        Shape hitbox2 = Global.P2.getBattlehitbox();
    	        // Affichage de la hitbox
    	        g.draw(hitbox2);
    	    } catch(Exception ex) {
    	        // Génération d'une nouvelle exception si aucune hitbox n'est trouvée pour le joueur 2
    	        throw new RuntimeException("Aucune hitbox trouvée pour le joueur 2", ex);
    	    }
    	}
    	


        // Dessin des ennemis --> sait pas si ça marche
    	try {
    	    // Parcours des ennemis et affichage de leur sprite
    	    for (int i = 0; i < this.enemy.length-1; i++) {
    	        if (this.enemy[i] == null) {
    	            continue; // Si l'ennemi est null, passer au suivant
    	        }
    	        // Affichage du sprite de l'ennemi
    	        g.drawImage(this.enemy[i].getSprite(), 550, 200 + i * 100);
    	    }
    	} catch(Exception e) {
    	    //System.out.println(e.getMessage());
    	    // Tentative de création et d'affichage d'une hitbox
    	    
    	    try {
    	    	for (int i = 0; i < this.enemy.length-1; i++) {
        	        if (this.enemy[i] == null) {
        	            continue; // Si l'ennemi est null, passer au suivant
        	        }
        	        // Affichage du sprite de l'ennemi
        	        Shape hitbox = new Rectangle(1400, 400+i*200, 48, 64);
        	        // Affichage de la hitbox
        	        g.draw(hitbox);
        	    }
    	    } catch(Exception ex) {
    	        // Génération d'une nouvelle exception si la hitbox ne peut pas être créée
    	        throw new RuntimeException("Impossible de créer la hitbox", ex);
    	    }
    	}
    	
    	
    	//Si win 
    	if (this.isWin()) {
    		Global.P1.resetStats();
    		Global.P2.resetStats();
    		Global.mobs = new Mobs[4];
    		//this.entities =  new ArrayList<>();
            sbg.enterState(Global.actualId);
            
    	}
    	
    	//Si loose
    	if (Global.P1.isDead() && Global.P2.isDead()) {
    		Global.P1.resetStats();
    		Global.P2.resetStats();
    		Global.mobs = new Mobs[4];
    		//this.entities =  new ArrayList<>();
            sbg.enterState(Global.actualId);
    	}
    	
    	this.tmpDialogbox1.renderTempDialgbox(g);
    	//this.dialogueBox.renderForceDialogbox(g);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input =gc.getInput();
		boolean boolInput =input.isKeyPressed(Global.interract);
    		
		if(this.currentTurnIndex == 0) {			
			this.dialogueBoxTourP1.forceDialogBox(boolInput,gc.getInput());
			//this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()-1); // sert à incrémenter les tours (oui juste ça)

		} else if (this.currentTurnIndex == 2) {
			this.dialogueBoxTourP2.forceDialogBox(boolInput,gc.getInput());
			//this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()-1); // mais en ne depassant pas le nombre d'entités -1
		} else {
			if(this.entities.get(currentTurnIndex).getHpActuel() < this.entities.get(currentTurnIndex).getHpMax()) {
				this.dialogueBoxTourCurrentMobSoin.forceDialogBox(boolInput,gc.getInput());
        	} else {
        		this.dialogueBoxTourCurrentMobAttaque.forceDialogBox(boolInput,gc.getInput());
        	}
			//this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()-1); // oui c'est moche
		}
    	
    	this.tmpDialogbox1.updateTempDialgbox(boolInput, gc);
    	 
    }
    
    public boolean isWin() {
    	for (int i = 0; i < this.enemy.length; i++) {
    		if (!(enemy[i].isDead())) {
    			return false;
    		}
    	}
    	return true;
    }

    public int getID() {
        return 100;
    }
}
