
public class Item
{
	private String name;
	private int accBoost;
	private int strBoost;
	private int defBoost;
	private int expBoost;
	private int hpRecover;
	
	/*
	 * 20% - Small Health Potion (50% hp)
	 * 10% - Large Health Potion (100% hp)
	 * 15% - Accuracy Potion (Accuracy + 2)
	 * 15% - Strength Potion (Strength + 2)
	 * 15% - Defense Potion (Defense + 2)
	 * 25% - Experience Tome (25 exp)
	 */
	public Item()
	{
		int num = (int)(Math.random()*100 + 1);
		if (num > 80)
		{
			name = "Small Health Potion";
			hpRecover = 12;
		}
		else if (num > 70)
		{
			name = "Large Health Potion";
			hpRecover = 25;
		}
		else if (num > 55)
		{
			name = "Accuracy Potion";
			accBoost = 2;
		}
		else if (num > 40)
		{
			name = "Strength Potion";
			strBoost = 2;
		}
		else if (num > 25)
		{
			name = "Defense Potion";
			defBoost = 2;
		}
		else 
		{
			name = "Experience Tome";
			expBoost = 25;
		}
	}
	public Item(String n, int a, int s, int d)
	{
		name = n;
		accBoost = a;
		strBoost = s;
		defBoost = d;
	}
	public Item(String n, int e)
	{
		name = n;
		expBoost = e;
	}
	public Item(int h, String n)
	{
		hpRecover = h;
		name = n;
	}
	public void useItem()
	{
		System.out.println("You use the " + name );
		if (accBoost != 0)
			System.out.println("Accuracy increased by " + accBoost + ".");
		if (strBoost != 0)
			System.out.println("Strength increased by " + strBoost + ".");
		if (defBoost != 0)
			System.out.println("Defense increased by " + defBoost + ".");
		if (expBoost != 0)
			System.out.println("You gained " + expBoost + " experience!");
		if (hpRecover != 0)
			System.out.println("You recovered " + hpRecover + " hp!");
	}
	public String showEffect()
	{
		String boost = "";
		if (accBoost != 0)
			boost += ("Acc+" + accBoost + " ");
		if (strBoost != 0)
			boost += ("Str+" + strBoost + " ");
		if (defBoost != 0)
			boost += ("Def+" + defBoost + " ");
		if (expBoost != 0)
			boost += ("Exp+" + expBoost + " ");
		if (hpRecover != 0)
			boost += ("HP+" + hpRecover + " ");
		return boost;
	}

	public String getName()
	{
		return name;
	}

	public int getAccBoost()
	{
		return accBoost;
	}

	public int getStrBoost()
	{
		return strBoost;
	}

	public int getDefBoost()
	{
		return defBoost;
	}

	public int getExpBoost()
	{
		return expBoost;
	}

	public int getHpRecover()
	{
		return hpRecover;
	}
	
	
}
