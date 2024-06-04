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
    private int P1Potions = 5;
    private int P2Potions = 5;
    private int playedTurn = 0;
    
    
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
    	this.dialogueBoxTourP1.setChoices(Arrays.asList("Attaquer", "Se soigner"), choice -> {
            switch (choice) {
            case 0:
                // Gérer l'attaque
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)
    			this.tmpDialogbox1.setChoices(Arrays.asList("bob", "bob mais en mieux"), choice2 -> {
                    switch (choice2) {
                        case 0:
                        	this.tmpDialogbox1.setActiveTempDialogbox(true);
                        	this.enemy[0].getHit(Global.P1.getDmg());
                            //this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"           AIE !!!!!!!!!"});
                            
                            //Permet de dire qu'il s'agissait du dernier choix
                            this.tmpDialogbox1.setChoices(Arrays.asList(),null);
                            break;

                        case 1:
                        	this.tmpDialogbox1.setActiveTempDialogbox(false);
                        	this.enemy[1].getHit(Global.P1.getDmg());

                    }
    			});

                break;
            case 1:
                // Gérer le soin
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)
    			Global.P1.healEntity();
    			this.P1Potions --;

                break;
            }
    });
    	
    	this.dialogueBoxTourP2 = new DialogueBox(new String[] {
    			"\n "+
    					"     \n" +
    					"           C'est à vous de jouer"
    	});
    	this.dialogueBoxTourP2.setChoices(Arrays.asList("Attaquer", "Se soigner"), choice -> {
            switch (choice) {
            case 0:
                // Gérer l'attaque
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)
    			this.tmpDialogbox1.setChoices(Arrays.asList("bob", "bob mais en mieux"), choice2 -> {
                    switch (choice2) {
                        case 0:
                        	this.tmpDialogbox1.setActiveTempDialogbox(true);
                        	this.enemy[0].getHit(Global.P2.getDmg());
                            //this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"           AIE !!!!!!!!!"});
                            
                            //Permet de dire qu'il s'agissait du dernier choix
                            this.tmpDialogbox1.setChoices(Arrays.asList(),null);
                            break;

                        case 1:
                        	this.tmpDialogbox1.setActiveTempDialogbox(false);
                        	this.enemy[1].getHit(Global.P2.getDmg());
                        	break;
                    }
    			});
    			
                break;
            case 1:
                // Gérer le soin
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)
    			Global.P2.healEntity();
    			this.P2Potions --;
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
            	this.tmpDialogbox1.setActiveTempDialogbox(true);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)
    	        this.tmpDialogbox1.setChoices(Arrays.asList(),null);

                break;
        }
      this.tmpDialogbox1.setActiveTempDialogbox(false);

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
            this.P1Name = Global.P1.getName();
            this.P2Name = Global.P2.getName();   
        }
    }
        
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Initialisation des ressources de la scène de combat
    	this.tmpDialogbox1.setTriggerZone(-1, -1, 0, 0);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(new Image("data/BattleScenes/Bottom.png").getScaledCopy(Global.width, Global.height), 0, 0);
        g.drawImage(new Image("data/BattleScenes/Foret.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        this.initializeBattle();
        // gère l'affichage de la dialogBox pour l'entité qui joue le tour
        
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
    		Global.canMoovPlayer=true;
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
    	
    	if(!isWin() && !(Global.P1.isDead() && Global.P2.isDead())) {    		
    		    		
    		this.tmpDialogbox1.renderTempDialgbox(g);
			this.dialogueBoxTourP1.render(g);
			this.dialogueBox.render(g); // Ajout de la variable dialogueBox
			this.dialogueBoxTourP1.render(g);
			this.dialogueBoxTourP2.render(g);
			this.dialogueBoxTourCurrentMobAttaque.render(g);
			this.dialogueBoxTourCurrentMobSoin.render(g);
			
			
    		if(currentTurnIndex == playedTurn) {    	
    			
    			tour(g);
    		}
    	}
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
    	Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet2,delta);
		Global.P1.AnimateWhileMoove();
    	 
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
    
    public void tour(Graphics g) {
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
					//System.out.println( "-"+ hit + " pour P1");
					if( Global.P1.isDead()) {

						this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice2 -> {
			                   switch (choice2) {

			                       case 0:
			                    	   this.tmpDialogbox1.setActiveTempDialogbox(true);
			                           this.tmpDialogbox1.setMessages(new String[] {
				    			    			"\n "+
				    			    					"     \n" +
				    			    					"           Bob attaque " + Global.Player1Classe +"     \n"+
				    			    					"            " + Global.Player1Classe + " est mort"});
			                            
			                            //Permet de dire qu'il s'agissait du dernier choix
			                           this.tmpDialogbox1.setChoices(Arrays.asList(),null);
			                           break;

			                   }
			                    
			               });
						this.tmpDialogbox1.setActiveTempDialogbox(false);
					} else {    	
						this.tmpDialogbox1= new DialogueBox(new String[] {
								"\n "+
										"     \n" +
										"           Bob attaque " + Global.Player1Classe + "     \n"+
										"            " + Global.Player1Classe + "  possède maintenant " + Global.P1.getHpActuel() + " HP"
						});
					}
					
				} else {
					System.err.println("toto");
					Global.P2.getHit(hit);
					//System.out.println( "-"+ hit + " pour P2");
					if( Global.P2.isDead()) {
						this.tmpDialogbox1= new DialogueBox(new String[] {
    			    			"\n "+
    			    					"     \n" +
    			    					"           Bob attaque " + Global.Player2Classe +"     \n"+
    			    							"            " + Global.Player2Classe + " est mort"
    			    	});
					} else {    						
							this.tmpDialogbox1= new DialogueBox(new String[] {
									"\n "+
										"     \n" +
										"           Bob attaque " + Global.Player2Classe +"     \n"+
										"            " + Global.Player2Classe + "  possède maintenant " + Global.P2.getHpActuel() + " HP"
						});
					}
				}
			}
			playedTurn++;

		}
		System.err.println("P1 death:"+Global.P1.isDead()+"  ;   P2 death:"+Global.P2.isDead());

    }
}