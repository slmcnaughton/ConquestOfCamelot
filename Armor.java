
public class Armor
{
	private String name;
	private int defBonus;
	private int slot;	//head, body, hands, legs, feet
						//  0 ,   1 ,   2  ,   3 ,   4
	
	public Armor(int power)
	{
		defBonus = 1;
		name = adjective(power/8-3);
		slot = (int)(Math.random()*5);	//5 slots, see above
		if (slot == 0)
		{
			defBonus += 3;
			name += " Helmet";
		}
		else if (slot == 1)
		{
			defBonus += 5;
			name += " Chestplate";
		}
		else if (slot == 2)
		{
			defBonus += 2;
			name += " Gauntlets";
		}
		else if (slot == 3)
		{
			defBonus += 4;
			name += " Platelegs";
		}
		else
		{
			defBonus += 1;
			name += " Boots";
		}
	}
	public Armor(String n, int d, int s)
	{
		name = n;
		defBonus = d;
		slot = s;
	}
	public String adjective(int tier)
	{
		int choice = (int) (Math.random()*tier + tier / 5);
		defBonus += (int)(Math.random()*choice + choice);
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
			defBonus += 1;
			String [] tierAdjs = {"Steel", "Marble", "Granite"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 6)
		{
			defBonus += 2;
			String [] tierAdjs = {"Topaz", "Emerald", "Sapphire"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 7)
		{
			defBonus += 3;
			String [] tierAdjs = {"Diamond", "Platinum", "Ruby"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 8)
		{
			defBonus += 4;
			String [] tierAdjs = {"Fire", "Ice", "Water"};
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 9)
		{
			String [] tierAdjs = {"Plasma", "Electric", "Lightning" };
			defBonus += 5;
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 10)
		{
			String [] tierAdjs = {"Nth Metal", "Mithril" };
			defBonus += 6;
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
		else if (choice == 11)
		{
			String [] tierAdjs = {"Vibranium", "Adamantium" };
			defBonus += 8;
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}	
		else
		{
			String [] tierAdjs = {"Invincible" };
			defBonus += 10;
			return tierAdjs[(int)(Math.random()*tierAdjs.length)];
		}
	}
	public String toString()
	{
		return name + " (Def:+" + defBonus + ")";
	}
	public int getDefBonus()
	{
		return defBonus;
	}

	public int getSlot()
	{
		return slot;
	}
	public String getName()
	{
		return name;
	}
	
	
}
