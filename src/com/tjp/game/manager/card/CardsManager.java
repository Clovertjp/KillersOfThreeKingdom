package com.tjp.game.manager.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tjp.card.Card;
import com.tjp.card.type.CardColorType;
import com.tjp.card.type.CardType;
/**
 * 卡牌管理类
 * @author TangJP
 *
 */
public class CardsManager {
	
	public static Map<CardType,Integer> map =new HashMap<>();//卡牌每个类型的个数
	public List<Card> cardList=new ArrayList<>();//牌堆
	public List<Card> otherCardList=new ArrayList<>();//弃牌堆
	
	static{
		map.put(CardType.KILL, 30);
		map.put(CardType.DODGE, 15);
		map.put(CardType.PEACH, 8);
		map.put(CardType.STEALCARD, 5);
		map.put(CardType.DISCARD, 8);
	}
	
	
	public CardsManager()
	{
		for(Map.Entry<CardType,Integer> entry : map.entrySet())
		{
			Class cardClass=entry.getKey().getCardClass();
			int count=entry.getValue();
			for(int i=1;i<=count;i++)
			{
				try {
					Class[] parameterTypes={Integer.class,Byte.class};
					java.lang.reflect.Constructor constructor=cardClass.getConstructor(parameterTypes);
					Object[] parameters={i,(byte)(i%4+1)}; 
					Card card=(Card) constructor.newInstance(parameters); 
					cardList.add(card);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		
		Collections.shuffle(cardList);
	}
	
	/**
	 * 抽卡
	 * @param num
	 * @return
	 */
	public List<Card> drawCard(int num)
	{
		List<Card> list=new ArrayList<>();
		
		if(cardList.size()==0 && otherCardList.size()==0)
		{
			return list;
		}
		
		if(cardList.size()<=num)
		{
			list.addAll(cardList);
			cardList.clear();
			cardList.addAll(otherCardList);
			Collections.shuffle(cardList);
			otherCardList.clear();	
		}
		
		int leftNum=num-list.size();
		if(leftNum>0)
		{
			List<Card> delete=new ArrayList<>();
			for(Card card : cardList)
			{
				if(leftNum<=0)
				{
					break;
				}
				leftNum--;
				list.add(card);
				delete.add(card);
			}
			for(Card card : delete)
			{
				cardList.remove(card);
			}
		}
		
		return list;
	}
	
	/**
	 * 弃卡
	 * @param cards
	 */
	public void discard(List<Card> cards)
	{
		if(cards==null || cards.size()<=0)
		{
			return ;
		}
		otherCardList.addAll(cards);
	}
	
	/**
	 * 弃卡
	 * @param cards
	 */
	public void discard(Card cards)
	{
		if(cards==null)
		{
			return ;
		}
		otherCardList.add(cards);
	}

}
