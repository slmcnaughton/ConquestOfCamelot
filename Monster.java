
public class Monster
{
	private String name;	//chosen through randomName()
	private int accuracy;
	private int strength;
	private int defense;
	private int hp;
	private int expValue;
	private Weapon weapon;
	private Item item;
	private Armor[] armor;
	
	public Monster(Character main)
	{
		this(main, 0);	//Bosses start at 1, 0 is ordinary
	}
	public Monster(Character main, int whichBoss)
	{
		int power = main.getMaxHp()+main.getAccuracy()+main.getDefense()+main.getStrength();
		accuracy = (int)((Math.random()*.2+1.2)*power/4-3);
		strength = (int)((Math.random()*.2+1.2)*power/3-11);
		defense = (int)((Math.random()*.2+1.2)*power/7-5);
		name = randomName();
		hp = accuracy + strength + defense;
		expValue = (int)(Math.random()*20+10);
		if (expValue <= 4)
			expValue = 5;
		weapon = new Weapon(power);
		armor = new Armor[5];
		equipRandomArmor(power, main.getLevel());
		item = new Item();
		
		if (whichBoss > 0)
		{
			String[] bosses = {"Mordred", "Kay", "Bedivere", "Gawain", "Gareth", "Percival", 
					"Bors", "Galahad", "Lamorak", "Tristan", "Lancelot", "King Arthur"};
			name = "Sir " + bosses[whichBoss-1];
			expValue = 5;
			
			double increase = 1 + whichBoss/10.0;
			accuracy *= increase;
			strength *= increase;
			hp *= (0.5 + increase);
			//public Armor(String n, int d, int s)
			// Head Body Hands Legs Feet
			if (whichBoss == 1)
				armor[2] = new Armor("Arthurian Gauntlets", 18, 2);
			else if (whichBoss == 2)
				armor[4] = new Armor("Arthurian Boots", 21, 4);
			else if (whichBoss == 3)
				armor[1] = new Armor("Arthurian Platebody", 30, 1);
			else if (whichBoss == 4)
				armor[4] = new Armor("Arthurian Platelegs", 27, 4);
			else if (whichBoss == 5)
			{
				armor[0] = new Armor("Arthurian Helmet", 24, 0);
			}
			else if (whichBoss == 6)
			{
				weapon = new Weapon("Arthurian Excalibur", 35, 35, 35);
			}
			else if (whichBoss == 7)
				armor[2] = new Armor("Arthurian Gauntlets", 50, 2);
			else if (whichBoss == 8)
				armor[4] = new Armor("Arthurian Boots", 50, 4);
			else if (whichBoss == 9)
				armor[1] = new Armor("Arthurian Platebody", 60, 1);
			else if (whichBoss == 10)
				armor[4] = new Armor("Arthurian Platelegs", 55, 4);
			else if (whichBoss == 11)
			{
				armor[0] = new Armor("Arthurian Helmet", 55, 0);
			}
			else if (whichBoss == 12)
			{
				weapon = new Weapon("Arthurian Excalibur", 100, 100, 100);
			}
		
			
		}
	}
	public String randomName()
	{
		String [] nameList = {"Goblin", "Bear", "Troll", "Wizard", "Pirate", 
									"Witch", "Giant Rodent", "Orc", "Samuri",};
		return nameList[(int)(Math.random()*nameList.length)];
	}
	public void equipRandomArmor(int power, int level)
	{
		for(int i = 0; i < 5; i++)	//5 chances to create armor
		{
			if (Math.random()*100 < 15 + 5*level)
			{
				Armor a = new Armor(power);
				armor[a.getSlot()] = a;
			}
		}
	}
	public String toString()
	{
		return "a " + name + " with " + strength + " strength " + "and " + hp + " hp.";
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
	public void attack(Character main)
	{
		System.out.println("\tThe " + name + " uses his " + weapon.getName() + " to attack " + main.getName());
		
		int acc = accuracy + weapon.getAccBonus();// + setBonusValue;
		int str = strength + weapon.getStrBonus();// + setBonusValue;
		int def = defense + weapon.getDefBonus() + armorDefBonus();// + setBonusValue;
		int enemyDef = main.getDefense() + main.getTempDefense()+main.armorDefBonus() + main.getArmorSetBonusValue();
		int hitChance = 40 + 2*(acc)-enemyDef/2;
		int hitPower = str - enemyDef/2;
		if (hitPower <= 0)
			hitPower = 1;
		
		int roll = (int)(Math.random()*100+1);
		if (roll < hitChance)
		{
			int amount = (int)(Math.random()*hitPower + 1);
			System.out.println("\tThe " + name + " hits " + main.getName() + " for " + amount + " damage.");
			main.reduceHp(amount);
		}
		else
		{
			System.out.println("\tThe " + name + "'s attack fails!");
		}
		System.out.println("\t"+main.getName() + " has " + main.getHp() + " hp remaining.");
		System.out.println("\tThe " + name + " has " + hp + " hp remaining.");
		System.out.println();
		
	}
	public void reduceHp(int amount)
	{
		hp -= amount;
		if (hp <= 0)
		{
			hp = 0;
		}
	}
	
	public int getHp()
	{
		return hp;
	}
	public int getDefense()
	{
		return defense;
	}
	public String getName()
	{
		return name;
	}
	public Weapon getWeapon()
	{
		return weapon;
	}
	public int getExpValue()
	{
		return expValue;
	}
	public Item getItem()
	{
		return item;
	}
	public Armor[] getArmor()
	{
		return armor;
	}
}
