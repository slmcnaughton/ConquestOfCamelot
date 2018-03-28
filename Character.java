import java.util.*;

public class Character
{
	private String name;
	private int exp;
	private int level;
	private String title;
	
	private int accuracy;
	private int strength;
	private int defense;
	private int maxHp;
	private int hp;
	
	private int tempDefense;
	private int armorSetBonusValue;
	
	private Weapon weapon;
	private Armor[] armor;

	private List<Item> pouch;
	private Map<String,Integer> defeatedEnemyCounter;
	private int bossesDefeated;
//	private int goldCoins;
	
	public Character(String n)
	{
		name = n;
		accuracy = (int)(Math.random()*3 + 6);
		strength = (int)(Math.random()*3 + 6);
		defense = (int)(Math.random()*3 + 6);
		maxHp = 25;
		hp = maxHp;
		weapon = new Weapon("Rusty Sword", 1, 2, 1);
		armor = new Armor[5];
		equip(new Armor("Knit Cap", 0, 0));
		equip(new Armor("Bent Chainmail", 3, 1));
		equip(new Armor("Leather Gloves", 1, 2));
		equip(new Armor("Cotton Pants", 0, 3));
		equip(new Armor("Leather Boots", 1, 4));
		exp = 0;
		level = 1;
		title = determineTitle();
		pouch = new ArrayList<Item>();
		pouch.add(new Item("Super Strength Potion", 0,3,0) );
		pouch.add(new Item(12, "Small Health Potion") );
		pouch.add(new Item(12, "Small Health Potion") );
		pouch.add(new Item() );
		defeatedEnemyCounter = new HashMap<>();
	}
	public void addDefeatedEnemy(Monster enemy)
	{
		String adj = enemy.getName();
		Integer c = defeatedEnemyCounter.get(adj);
		if (c == null)
			c = new Integer(0);
		c++;
		defeatedEnemyCounter.put(adj,c);
	}
	public void viewDefeatedEnemyCounter()
	{
		if (defeatedEnemyCounter.size() <= 0)
			System.out.println("You haven't defeated any enemies yet.");
		else
		{
			System.out.println("Enemy Name:\t\tNumber Defeated:");
			for (Map.Entry<String,Integer> element : defeatedEnemyCounter.entrySet())
			{
				System.out.println(element.getKey() + "\t\t" + element.getValue() );
			}
			System.out.println("Knights of the Round Table: " + bossesDefeated + " / 12");
		}
	
			
	}
	
	public void displayPouch()
	{
		System.out.println("Pouch Contents:");
		if (pouch.size() > 0)
		{
			System.out.println("Item #\tName\t\t\tEffect");
			for (int i = 0; i < pouch.size(); i++)
			{
				Item item = pouch.get(i);
				System.out.print(i + "\t" + item.getName());
				for(int j = 1; j <= (31 - item.getName().length())/8; j++)	//31 is less than 4 tabs...8 spaces per tab
					System.out.print("\t");
				System.out.println(item.showEffect());
			}
			System.out.println();
		}
		else
		{
			System.out.println("The pouch is empty!\n");
		}
		
	}
	public void addToPouch(Item item)
	{
		System.out.println("You place the " + item.getName() + " in your pouch.");
		pouch.add(item);
	}
	public boolean usePouch()
	{
		Scanner scan = new Scanner(System.in);
		displayPouch();
		System.out.println("Choose an item number to use it, or type -1 to return to the other options.");
		int itemNumber = scan.nextInt();
		while(itemNumber < -1 || itemNumber >= pouch.size())
		{
			System.out.println("That item doesn't exist!");
			itemNumber = scan.nextInt();
		}
		if (itemNumber == -1)
		{
			return false;
		}
		else
		{
			Item item = pouch.get(itemNumber);
			item.useItem();
			accuracy += item.getAccBoost();
			strength += item.getStrBoost();
			defense += item.getDefBoost();
			changeExp(item.getExpBoost());
			recoverHp(item.getHpRecover());
			pouch.remove(itemNumber);
			System.out.println("\n"+toString());
			return true;
		}
	}
	public void equip(Armor a)
	{
		armor[a.getSlot()] = a;
	}
	public void computeArmorSetBonusValue(String type, int armorPieces)
	{
		if (armorPieces == 3)
		{
			armorSetBonusValue = 3;
		}
		else if (armorPieces == 4)
		{
			armorSetBonusValue = 5;
		}
		else if (armorPieces  == 5)
		{
			armorSetBonusValue = 8;
		}
		else if (armorPieces == 6)
		{
			armorSetBonusValue = 12;
		}
		else
		{
			armorSetBonusValue = 0;
		}
		
		if (type.indexOf("Arthurian") != -1)
		{
			armorSetBonusValue += 4;
		}
	}
	public String armorSetBonus()
	{
		String str = "None";
		List<String> adjectives = new ArrayList<String>();
		for (int i = 0; i < armor.length; i++)
		{
			if (armor[i] != null)
				adjectives.add(armor[i].getName().substring(0,armor[i].getName().indexOf(" ")));
		}
		adjectives.add(weapon.getName().substring(0,weapon.getName().indexOf(" ")));
		
		Map<String, Integer> stringCount = new HashMap<>();
		//loop through list of adjectives, increment counter
		for (String s: adjectives)
		{
			Integer c = stringCount.get(s);
			if (c == null)
				c = new Integer(0);
			c++;
			stringCount.put(s,c);
		}
		Map.Entry<String,Integer> mostRepeated = null;
		for (Map.Entry<String, Integer> element : stringCount.entrySet())
		{
			if (mostRepeated == null || mostRepeated.getValue() < element.getValue())
			{
				mostRepeated = element;
			}
		}
		if (mostRepeated != null && mostRepeated.getValue() > 2)
		{
			str = mostRepeated.getKey();
			computeArmorSetBonusValue(str, mostRepeated.getValue());
		}
		return str;
		
	}
	public void viewEquipment()
	{
		System.out.println("Equipped Weapons and Armor: " );
		System.out.println("Weapon: " + weapon);
		String [] bodyParts = {"Head: ","Body: ","Hands: ","Legs: ","Feet: "};
		for (int i = 0; i < armor.length; i++)
		{
			System.out.print(bodyParts[i]);
			if (armor[i] != null)
				System.out.println(armor[i]);
			else
				System.out.println("None equipped");
		}
		if (!armorSetBonus().equals("None"))
		{
			System.out.println(armorSetBonus() + " Set Bonus: Accuracy, Strength, Defense +" + armorSetBonusValue);
		}
	}
	public void changeExp(int n)
	{
		exp += n;
		if (exp > 99)
		{
			levelUp();
			exp -= 100;
		}
	}
	private void levelUp()
	{
		level++;
		title = determineTitle();
		System.out.println("Congratulations! You've reached level " + level + "!!!");
		System.out.println("Your accuracy, strength, defense, and HP have all increased.");
		int levelUpBoost = 2;
		accuracy += levelUpBoost;
		strength += levelUpBoost;
		defense += levelUpBoost;
		maxHp += (levelUpBoost+1);
		recoverHp(maxHp);
	}
	public void attack(Monster enemy)
	{
		Scanner scan = new Scanner (System.in);
		int choice;
		
		do
		{
			System.out.println("It's " + name + "'s turn to attack. What would you like to do?");
			System.out.println("1) Attack accurately");
			System.out.println("2) Attack aggressively");
			System.out.println("3) Attack defensively");
			System.out.println("4) Use an item from the pouch");
			//System.out.println("5) Attempt to run away");
			
			tempDefense = 0;	//reset tempDefense if the user's last attack was defensive
			int acc = accuracy + weapon.getAccBonus() + armorSetBonusValue;
			int str = strength + weapon.getStrBonus() + armorSetBonusValue;
			int def = defense + weapon.getDefBonus() + armorDefBonus() + armorSetBonusValue;
			int enemyDef = enemy.getDefense() + enemy.armorDefBonus() ;
			int hitChance = 40 + 2*(acc)-enemyDef;
			int hitPower = str - enemyDef/2;
			if (hitPower <= 0)
				hitPower = 1;
			
			choice = scan.nextInt();
			if (choice >=1 && choice <= 3)
			{		
				if (choice == 1)
				{
					hitChance += 35;
				}
				else if (choice == 2)
				{
					
					hitPower = (int) ( str * 1.2 - enemyDef/2) + 2;
				}
				else if (choice == 3)
				{
					tempDefense = (int) (def*.5);
				}
				int roll = (int)(Math.random()*100+1);
				if (roll < hitChance)
				{
					int amount = (int)(Math.random()*hitPower + 1);
					System.out.println(name + " hits the " + enemy.getName() + " with the " + weapon.getName() + " for " + amount + " damage.");
					enemy.reduceHp(amount);
				}
				else
				{
					System.out.println(name + "'s attack fails!");
				}
				System.out.println(name + " has " + hp + " hp remaining.");
				System.out.println("The " + enemy.getName() + " has " + enemy.getHp() + " hp remaining.");
				System.out.println("Press enter to continue:");
				scan.nextLine();
				scan.nextLine();
			}
			else if (choice == 3)
			{
				
			}
			else if (choice == 4)
			{
				if( !usePouch() )
				{
					choice = 0;	//the pouch was not used;
				}
			}
		} while ( choice < 1 || choice > 4);
	}
	public void defeated(Monster enemy)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println(name + " has defeated the " + enemy.getName() + " in combat!");
		System.out.println(name + " has gained " + enemy.getExpValue() + " experience.");
		changeExp(enemy.getExpValue());
		if (enemy.getName().contains("Sir"))
		{
			bossesDefeated++;
		}
		else
			addDefeatedEnemy(enemy);
		System.out.println("The enemy drops a " + enemy.getItem().getName() + ".");
		addToPouch(enemy.getItem());
		if (enemy.getWeapon() != null)
		{
			System.out.println("Would you like to equip the enemy's " + enemy.getWeapon() + 
					",\n\t and discard your " + weapon + "? (y/n)");
			String response = scan.nextLine().toLowerCase();
			if (response.length()>0 && response.charAt(0) == 'y')
			{
				weapon = enemy.getWeapon();
			}
		}
		for(int i = 0; i < enemy.getArmor().length; i++)
		{
			Armor enArm = enemy.getArmor()[i];	//ENemy ARMor
			if (enArm != null && enArm.getDefBonus() > armor[i].getDefBonus()-10)
			{
				System.out.print("Would you like to equip the enemy's " + enArm);
				if (armor[i] != null)	
				{
					System.out.print(",\n\tand discard your " + armor[i]);
				}
				System.out.println("? (y/n)");
				String response = scan.nextLine().toLowerCase();
				if (response.length()>0 && response.charAt(0) == 'y')
				{
					armor[i] = enemy.getArmor()[i];
				}
			}	
		}
		armorSetBonus();
	}
	
	public void reduceHp(int amount)
	{
		hp -= amount;
		if (hp <= 0)
		{
			hp = 0;
		}
	}
	public void recoverHp(int amount)
	{
		if (amount > 0)
		{
			hp += amount;
			if (hp >= maxHp)
			{
				System.out.println("You're now at full health.");
				hp = maxHp;
			}
		}
	}
	
	private String determineTitle()
	{
		if (level < 4)
		{
			return "apprentice";
		}
		else if (level < 7)
		{
			return "knight";
		}
		else if (level < 10)
		{
			return "paladin";
		}
		else
		{
			return "king";
		}
		
	}
	
	public String toString()
	{
		int accBonus = weapon.getAccBonus() + armorSetBonusValue;
		int acc = accuracy + accBonus; ;
		int strBonus = weapon.getStrBonus() + armorSetBonusValue;
		int str = strength + strBonus;
		int defBonus = weapon.getDefBonus() + armorDefBonus() + armorSetBonusValue;
		int def = defense + defBonus;
		
		return "You are a level " + level + " " + title + " with " + hp + "/" + maxHp + " hp." +
				"\nExperience towards next level: " + exp +
				"\nAccuracy: " + acc + " (+" + accBonus + ")" +
				"\nStrength: " + str +" (+" + strBonus + ")" +
				"\nDefense: " + def + " (+" + defBonus + ")";
	}
	public int armorDefBonus()
	{
		int sum = 0;
		for (int i = 0; i < armor.length; i++)
		{
			if (armor[i] != null)
				sum += armor[i].getDefBonus();
		}
		return sum;
	}
	public int getHp()
	{
		return hp;
	}

	public List<Item> getPouch()
	{
		return pouch;
	}

	public int getAccuracy()
	{
		return accuracy;
	}

	public int getStrength()
	{
		return strength;
	}

	public int getDefense()
	{
		return defense;
	}

	public int getMaxHp()
	{
		return maxHp;
	}
	public String getName()
	{
		return name;
	}
	public int getLevel()
	{
		return level;
	}

	public int getTempDefense()
	{
		return tempDefense;
	}
	public int getArmorSetBonusValue()
	{
		return armorSetBonusValue;
	}
	public int getBossesDefeated()
	{
		return bossesDefeated;
	}
	
}
