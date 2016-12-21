package com.tjp.game.manager;

import java.util.HashMap;
import java.util.Map;

import com.tjp.game.manager.card.CardsManager;
import com.tjp.hero.Hero;

public class GameManager {
	
	private Map<Integer,Hero> heroMap=new HashMap<>();
	
	private CardsManager cardManager=new CardsManager();
	

	public GameManager()
	{
		heroMap.put(1, new Hero("A", 6, 6,cardManager));
		heroMap.put(2, new Hero("B", 6, 6,cardManager));
	}
	
	public int Play(Map<Integer,Integer> map)
	{
		int count=0;
		while(heroMap.get(1).getBlood()>0 && heroMap.get(2).getBlood()>0)
		{
			
			count++;
			heroMap.get(1).drawCard();
			heroMap.get(1).play(heroMap.get(2));
			heroMap.get(1).disCard();
			
			if(heroMap.get(2).getBlood()<=0)
			{
				heroMap.get(2).die();
				
			}
			
			if(heroMap.get(2).getBlood()<=0)
			{
				break;
			}
			
			heroMap.get(2).drawCard();
			heroMap.get(2).play(heroMap.get(1));
			heroMap.get(2).disCard();
			
			if(heroMap.get(1).getBlood()<=0)
			{
				heroMap.get(1).die();
			}
		}
		
		if(heroMap.get(1).getBlood()<=0)
		{
			int num=map.get(1);
			num++;
			map.put(1, num);
		}else
		{
			int num=map.get(2);
			num++;
			map.put(2, num);
		}
		
//		System.out.println(heroMap.get(1).getBlood()+"   "+heroMap.get(2).getBlood()+"   "+count);
		return count;
	}

}
