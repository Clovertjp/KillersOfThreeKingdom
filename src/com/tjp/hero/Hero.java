package com.tjp.hero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.tjp.card.Card;
import com.tjp.card.type.CardType;
import com.tjp.game.manager.card.CardsManager;
import com.tjp.inter.IEffect;
import com.tjp.inter.IHero;
/**
 * 英雄类(后期加入其他英雄)
 * @author TangJP
 *
 */
public class Hero implements IHero {
	
	public Hero(String name,int maxBlood,int blood,CardsManager cardManager) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.maxBlood=maxBlood;
		this.blood=blood;
		this.cardManager=cardManager;
		cardList.addAll(this.cardManager.drawCard(4));
	}
	
	private String name;//英雄名称
	private int maxBlood;//最大血量
	private int blood;//剩余血量
	private List<Card> cardList=new ArrayList<>();//持有牌
	private List<IEffect> effectList=new ArrayList<>();//英雄自身效果
	private CardsManager cardManager;//卡牌管理
	private static List<CardType> disCardRule=new ArrayList<>();//弃牌规则
	private static List<CardType> playCardRule=new ArrayList<>();//出牌规则 桃倒数第二出 杀默认最后出
	static{
		disCardRule.add(CardType.KILL);
		disCardRule.add(CardType.DISCARD);
		disCardRule.add(CardType.STEALCARD);
		disCardRule.add(CardType.PEACH);
		disCardRule.add(CardType.DODGE);
		
		playCardRule.add(CardType.STEALCARD);
		playCardRule.add(CardType.DISCARD);	
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}
	public List<Card> getCardList() {
		return cardList;
	}
	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}
	public List<IEffect> getEffectList() {
		return effectList;
	}
	public void setEffectList(List<IEffect> effectList) {
		this.effectList = effectList;
	}
	
	
	public int getMaxBlood() {
		return maxBlood;
	}
	public void setMaxBlood(int maxBlood) {
		this.maxBlood = maxBlood;
	}
	public void castBlood(int castNum)
	{
		if(castNum<=0)
		{
			return;
		}
		blood-=castNum;
	}
	
	public void addBlood(int addNum)
	{
		if(addNum<=0 || blood>=maxBlood)
		{
			return ;
		}
		blood=Math.min(blood+addNum, maxBlood);
	}
	
	/**
	 * 摸牌
	 */
	public void drawCard()
	{
		cardList.addAll(cardManager.drawCard(2));
	}
	
	/**
	 * 弃牌
	 */
	public void disCard()
	{
		if(cardList.size()<=blood)
		{
			return;
		}
		int leftNum=cardList.size()-blood;
		int[] cardN=new int[CardType.CardNum.getTypeNum()];
		for(Card c : cardList)
		{
			cardN[c.getCardType().getTypeNum()]++;
		}
		int[] subNum=new int[CardType.CardNum.getTypeNum()];
		int sub=0;
		
		
		for(CardType typ :disCardRule)
		{
			if(sub<leftNum)
			{
				if(cardN[typ.getTypeNum()]<=leftNum-sub)
				{
					sub+=cardN[typ.getTypeNum()];
					subNum[typ.getTypeNum()]=cardN[typ.getTypeNum()];
				}else
				{
					subNum[typ.getTypeNum()]=leftNum-sub;
					sub+=(leftNum-sub);
				}
			}
		}
		
		List<Card> delete=new ArrayList<>();
		for(Card c : cardList)
		{
			if(subNum[c.getCardType().getTypeNum()]>0)
			{
				delete.add(c);
				subNum[c.getCardType().getTypeNum()]--;
			}
		}
		for(Card card : delete)
		{
			cardList.remove(card);
			cardManager.discard(card);
		}
	}
	
	/**
	 * 出牌
	 * @param target
	 */
	public void play(Hero target)
	{
		List<Card> delete=new ArrayList<>();
		
		for(CardType type:playCardRule)
		{
			
			for(int i=0;i<cardList.size();i++)
			{
//				System.out.println("1");
				Card c=cardList.get(i);
				if(c.getCardType()==type )
				{
					c.play(this, target,delete);
				}
			}
		}
		
		
		if(getBlood()<getMaxBlood())
		{
			Iterator<Card> iter = cardList.iterator();
			while(iter.hasNext())
			{
				Card c=iter.next();
				if(getBlood()>=getMaxBlood())
				{
					break;
				}
				if(c.getCardType()==CardType.PEACH )
				{
					c.play(this, target,delete);
				}
			}
			
		}
		
		Iterator<Card> iter1 = cardList.iterator();
		while(iter1.hasNext())
		{
			Card c=iter1.next();
			if(c.getCardType()==CardType.KILL)
			{
				
				c.play(this, target,delete);
				break;
			}
		}
		
		for(Card card : delete)
		{
			cardList.remove(card);
			cardManager.discard(card);
		}
	}
	
	/**
	 * 死亡
	 */
	public void die()
	{
		List<Card> delete=new ArrayList<>();
		Iterator<Card> iter = cardList.iterator();
		while(iter.hasNext())
		{
			Card c=iter.next();
			if(c.getCardType()==CardType.PEACH )
			{
				c.play(this, null,delete);
				break;
			}
		}
		for(Card card : delete)
		{
			cardList.remove(card);
			cardManager.discard(card);
		}
	}

}
