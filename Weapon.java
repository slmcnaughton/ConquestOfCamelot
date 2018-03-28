
public class Weapon
{
	private String name;
	private int accBonus;
	private int strBonus;
	private int defBonus;
	
	public Weapon(int power)
	{
		accBonus = 1;
		strBonus = 1;
		defBonus = 1;
		//power is around 40 at the start of game, goes up by 8ish per level
		//Therefore, start at adjective(2)
		name = adjective(power/8-3) +" "+ noun(power/8);
	}
	
	public Weapon(String n, int a, int s, int d)
	{
		name = n;
		accBonus = a;
		strBonus = s;
		defBonus = d;
	}
	public String adjective(int tier)
	{
		int choice = (int) (Math.random()*tier + tier / 5);
		accBonus += (int)(Math.random()*choice + choice/2);
		strBonus += (int)(Math.random()*choice + choice/2);
		defBonus += (int)(Math.random()*choice + choice/2);
		if (choice == 0)
		{
			String [] tierAdjs = {"Plastic", "Rusty", "Broken", "Bent", "Dull"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
			
		}
		else if (choice == 1)
		{
			String [] tierAdjs = {"Wooden","Clay", "Talc"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 2)
		{
			String [] tierAdjs = {"Water", "Gypsum", "Slate"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 3)
		{
			String [] tierAdjs = {"Bronze", "Glass", "Calcite"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 4)
		{
			String [] tierAdjs = {"Iron", "Ice", "Fluorite"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 5)
		{
			accBonus += 1;
			strBonus += 1;
			defBonus += 1;
			String [] tierAdjs = {"Steel", "Marble", "Granite"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 6)
		{
			accBonus += 2;
			strBonus += 2;
			defBonus += 2;
			String [] tierAdjs = {"Topaz", "Emerald", "Sapphire"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 7)
		{
			accBonus += 3;
			strBonus += 3;
			defBonus += 3;
			String [] tierAdjs = {"Diamond", "Platinum", "Ruby"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 8)
		{
			accBonus += 4;
			strBonus += 4;
			defBonus += 4;
			String [] tierAdjs = {"Fire", "Ice", "Water"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 9)
		{
			accBonus += 5;
			strBonus += 5;
			defBonus += 5;
			String [] tierAdjs = {"Plasma", "Electric", "Lightning" };
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 10)
		{
			accBonus += 6;
			strBonus += 6;
			defBonus += 6;
			String [] tierAdjs = {"Nth Metal", "Mithril" };
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 11)
		{
			accBonus += 8;
			strBonus += 8;
			defBonus += 8;
			String [] tierAdjs = {"Vibranium", "Adamantium" };
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}	
		else
		{
			accBonus += 10;
			strBonus += 10;
			defBonus += 10;
			String [] tierAdjs = {"Invincible" };
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
	}
	public String noun(int type)
	{
		String [] weapons = {"Knife", "Spear", "Mace", "Dagger", "Lance", "Scimitar", "Bow & Arrows", 
				"Broadsword", "Warhammer", "Flail", "Crossbow", "Longsword", "Battle-axe", "Greatsword"};
		int choice = (int) (Math.random()*type);
		accBonus += (int)(Math.random()*choice + choice/2);
		strBonus += (int)(Math.random()*choice + choice/2);
		defBonus += (int)(Math.random()*choice + choice/2);
		if (choice >= weapons.length)
			choice = weapons.length - 1;
		return weapons[choice];
	}
	public String toString()
	{
		return name + " (Acc:+" + accBonus + " Str:+" + strBonus + " Def:+" + defBonus + ")";
	}
	public String getName()
	{
		return name;
	}

	public int getAccBonus()
	{
		return accBonus;
	}

	public int getStrBonus()
	{
		return strBonus;
	}

	public int getDefBonus()
	{
		return defBonus;
	}
	
}
