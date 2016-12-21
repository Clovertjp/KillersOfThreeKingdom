package com.tjp.hero;

import java.util.ArrayList;
import java.util.List;

import com.tjp.card.Card;
import com.tjp.card.type.CardType;
import com.tjp.game.manager.card.CardsManager;
import com.tjp.inter.IEffect;
import com.tjp.inter.IHero;

public class Hero implements IHero {
	
	public Hero(String name,int maxBlood,int blood,CardsManager cardManager) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.maxBlood=maxBlood;
		this.blood=blood;
		this.cardManager=cardManager;
		cardList.addAll(this.cardManager.drawCard(4));
	}
	
	private String name;
	private int maxBlood;
	private int blood;
	private List<Card> cardList=new ArrayList<>();
	private List<IEffect> effectList=new ArrayList<>();
	private CardsManager cardManager;
	private static List<CardType> disCardRule=new ArrayList<>();
	private static List<CardType> playCardRule=new ArrayList<>();
	static{
		disCardRule.add(CardType.KILL);
		disCardRule.add(CardType.PEACH);
		disCardRule.add(CardType.DODGE);
		
		
		playCardRule.add(CardType.PEACH);
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
	
	public void drawCard()
	{
		cardList.addAll(cardManager.drawCard(2));
	}
	
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
	
	
	public void play(Hero target)
	{
		List<Card> delete=new ArrayList<>();
		if(getBlood()<getMaxBlood())
		{
			for(Card c : cardList)
			{
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
		
		
		for(Card c : cardList)
		{
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
	
	public void die()
	{
		List<Card> delete=new ArrayList<>();
		for(Card c : cardList)
		{
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
