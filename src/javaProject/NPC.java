package javaProject;

import javax.swing.JFrame;

	public class NPC {
		
		int[] position;
		int threshold;
		String thresholdName;
		
		int reward;
		int punishment;
		
			void interaction(int value) {
			
				if(this.threshold<=value)	{
				this.reward = 20; //reward the player	
									
				}
				else {
				 this.punishment  = 20; //punishment the player
					
				}
				
			}
		
	
	}
	
	
	 class Princess extends NPC{
			
		JFrame message;		//can also be a JOption pane
		
		
		Princess(int[] position){
			this.position = position;
			this.threshold = 20;	// get value from difficulty
			this.thresholdName = "Win the game";
	
			}	
	}
	
	
	
	class Dragon extends NPC{
		
		int damage;
			
		Dragon(int[] position){
			this.position = position;
			this.threshold = 20;	//
			this.damage = 20;
			this.thresholdName = "Kill the Dragon";		
	}	
	
	}
	
	class WiseMan extends NPC{
		String symbol;
		
		WiseMan(int[] position){
			this.position = position;
			this.threshold = 20;
			this.thresholdName = "A Grand Gift";
			this.symbol = " )( ";
					
		}
	}	
	
	class Friend extends NPC{
		
		String symbol;
		
		
		Friend(int[] position){
			this.position = position;
			this.threshold = 20;
			this.thresholdName = "Some Help";
			
			
			this.symbol = "  +  ";
					
		}
	}	
	
	
	
	class Enemy extends NPC{
		int threshold2;
		String threshold2Name;
		String symbol;
		
		Enemy(int[] position){
			this.position = position;
			this.threshold = 20;
			this.thresholdName = "Difficulty";
			this.threshold2 = 20;
			this.threshold2Name = "Compensation";	
			
			this.symbol = "  -  ";
		}	
		
	}

	
	
	class Wall extends NPC{
				
		Wall(int[] position){
		this.position = position;
						
		}
	}
	
	
	
	class Archer extends NPC{		
		
		Archer(int [] position) {	
			this.position = position;
			this.threshold = 20;
			this.thresholdName = "Damage";						
		
		}
		
		Archer(int [] position, int damage) {	
			this.position = position;
			this.threshold = damage;
			this.thresholdName = "Damage";						
		
		}		
		
	}

	

