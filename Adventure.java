/*
 * Conquest of Camelot
 * 
 * @author Seth McNaughton
 * @version V1.0 11/2017
 */
import java.util.*;
public class Adventure
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		asciiIntro();
		while(true)
		{
			System.out.println("Welcome to Conquest of Camelot, a text based adventure.");
			System.out.println("Please choose an option:");
			System.out.println("1) Play game");
			System.out.println("2) Read directions and useful information");
			System.out.println("3) About Conquest of Camelot");
			int choice = scan.nextInt();
			while (choice < 1 || choice > 3)
			{
				System.out.println("That wasn't a valid option. Please choose again.");
			}
			if (choice == 1)
			{
				runGame();
				System.out.println("\nPlay again? (y/n)");
				scan.nextLine();
				if (scan.nextLine().charAt(0) != 'y')
					break;
			}
			//else if (choice == 2)
				//runDirections();
			//else if (choice == 3)
				//runAbout();
				
			
			//Future feature: Save game/load game by outputting/reading to/from text file?
			//Future feature: play again?	
		}
	}
	public static void runGame()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Hey there! What is your name?");
		String name = scan.nextLine();
		Character main = new Character(name);
		System.out.println(main.getName() + "? Very nice. Stay alive as long as you can!");
		System.out.println(main);
		
		while(main.getHp() > 0)
		{
			System.out.println("\n" + name + " what would you like to do?");
			System.out.println("1) Fight enemies");
			System.out.println("2) Show pouch");
			System.out.println("3) View equipment");
			System.out.println("4) Show levels");
			System.out.println("5) Show defeated enemies");
			if (main.getBossesDefeated() != 12 && main.getBossesDefeated() < (main.getLevel() - 1)/2)
				System.out.println("6) Boss Fight");
			//System.out.println("7) Go to Shop");
			int choice = scan.nextInt();
			if (choice == 1)
			{
				fight(main, new Monster(main));
			}
			else if (choice == 2)
			{
				main.usePouch();
			}
			else if (choice == 3)
			{
				main.viewEquipment();
			}
			else if (choice == 4)
			{
				System.out.println(main);
			}
			else if (choice == 5)
			{
				main.viewDefeatedEnemyCounter();
			}
			else if (choice == 6)
			{
				if (main.getBossesDefeated() >= (main.getLevel() - 1)/2 )
				{
					System.out.println("You are not yet ready to fight a boss.");
				}
				else if (main.getBossesDefeated() > 11)
				{
					System.out.println("You have defeated all Knights of the Round Table.");
				}
				else
				{
					fight(main, new Monster(main,main.getBossesDefeated() + 1) );
				}
			}
			else
			{
				System.out.println("Command unknown. Perhaps I should take a nap?");
			}
		}
		System.out.println("Oh no, " + main.getName() + " died! Better luck next time...\n");
		System.out.println("Final Status:");
		System.out.println(main.toString().replace("are", "were"));

	}
	
	public static void fight(Character main, Monster enemy)
	{
		System.out.println(fightIntro() + enemy);
		System.out.println("The " + enemy.getName() + " is equipped with a " + enemy.getWeapon() + ".");
		Scanner scan = new Scanner(System.in);
		while (main.getHp() > 0 && enemy.getHp() > 0)
		{
			main.attack(enemy);			
			if (enemy.getHp() > 0)
			{
				enemy.attack(main);
			}
		}
		if (enemy.getHp() == 0)
		{
			main.defeated(enemy);
		}
	}
	public static String fightIntro()
	{
		String [] intros = {"Instantly, you find ", "After hours of searching, you find ", 
				"Instead of giving up, you settle for attacking ", "Wile getting a drink, you're attacked by "};
		return intros[(int)(Math.random()*intros.length)];
	}
	
	public static void asciiIntro()
	{
		/*
		 * Conquest of Camelot Text
		 * 	 Modified from font: Ogre
		 * 	 http://patorjk.com/software/taag/
		 * Knight Ascii Artwork
		 * 	 Modified from http://ascii.co.uk/art/knights
		 */
		System.out.println("	       _,.			   ___                                    ()   ");
		System.out.println("	     ,` -.)			  / __\\___  _ __   __ _ _   _  ___  ___  _)(_   ");
		System.out.println("	    ( _/-\\\\-._			 / /  / _ \\| '_ \\ / _` | | | |/ _ \\/ __|*-><-* ");
		System.out.println("	   /,|`--._,-^|            ,	/ /__| (_) | | | | (_| | |_| |  __/\\__ \\  ||   ");
		System.out.println("	   \\_| |`-._/||          ,'|	\\____/\\___/|_| |_|\\__, |\\__,_|\\___||___/  ||   ");
		System.out.println("	     |  `-, / |         /  /	                     |_|                  \\/   ");
		System.out.println("	     |     || |        /  / 	");
		System.out.println("	      `r-._||/   __   /  /  			        __   ");
		System.out.println("	  __,-<_     )`-/  `./  /   			  ___  / _|  ");
		System.out.println("	 /  \\   `---'   \\   /  /    			 / _ \\| |_   ");
		System.out.println("	|    |           |./  /    			| (_) |  _|  ");
		System.out.println("	\\   /            //  /      			 \\___/|_|    ");
		System.out.println("	 \\_/' \\         |/  /    			             ");
		System.out.println("	  |    |   _,^-'/  /     	   ___                     _        ()   ");
		System.out.println("	  |    , ``  (\\/  /_		  / __\\__ _ _ __ ___   ___| | ___  _)(_");
		System.out.println("	   \\,.->._    \\X-=/^		 / /  / _` | '_ ` _ \\ / _ \\ |/ _ \\*-><-*");
		System.out.println("	   (  /   `-._//^`  		/ /__| (_| | | | | | |  __/ | (_)|  ||");
		System.out.println("	    `Y-.____(__}		\\____/\\__,_|_| |_| |_|\\___|_|\\___/  ||");
		System.out.println("	           {__)			                                    \\/      ");
		System.out.println("	           ()			");
		System.out.println();
		System.out.println("Press enter to begin.");
	}
	
}
