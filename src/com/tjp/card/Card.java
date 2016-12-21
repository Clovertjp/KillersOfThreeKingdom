package com.tjp.card;

import java.util.ArrayList;
import java.util.List;

import com.tjp.card.type.CardColorType;
import com.tjp.card.type.CardType;
import com.tjp.hero.Hero;
import com.tjp.inter.IEffect;

public class Card {
	
	public Card(String cardName,int num,byte cardColorType,CardType cardType)
	{
		this.cardName=cardName;
		this.num=num;
		this.cardColorType=cardColorType;
		this.cardType=cardType;
	}
	
	private String cardName;
	private int num;//点数
	private byte cardColorType;
	private List<IEffect> effectList=new ArrayList<>();
	private CardType cardType;
	
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public List<IEffect> getEffectList() {
		return effectList;
	}
	public void setEffectList(List<IEffect> effectList) {
		this.effectList = effectList;
	}

	public byte getCardColorType() {
		return cardColorType;
	}
	public void setCardColorType(byte cardColorType) {
		this.cardColorType = cardColorType;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public void play(Hero hero,Hero target,List<Card> delete)
	{
		boolean del=true;
		for(IEffect eff: effectList)
		{
			del=del && eff.effectBefore(hero, target);
			del=del && eff.effect(hero, target);
			del=del && eff.effectAfter(hero, target);
		}
		if(del)
		{
			delete.add(this);
		}
	}

}
