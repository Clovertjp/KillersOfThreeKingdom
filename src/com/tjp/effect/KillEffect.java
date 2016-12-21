package com.tjp.effect;

import com.tjp.card.Card;
import com.tjp.card.DodgeCard;
import com.tjp.card.type.CardType;
import com.tjp.hero.Hero;
import com.tjp.inter.IEffect;

public class KillEffect implements IEffect {
	
	private int num;
	
	public KillEffect(int num) {
		// TODO Auto-generated constructor stub
		this.num=num;
	}
	

	@Override
	public boolean effectBefore(Hero hero, Hero target) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean effect(Hero hero, Hero target) {
		// TODO Auto-generated method stub
		if(hero==null || target==null)
		{
			return false;
		}
		Card dodgeCard=null;
		for(Card card : target.getCardList())
		{
			if(card.getCardType()==CardType.DODGE )
			{
				dodgeCard=card;
				break;
			}
		}
		
		if(dodgeCard==null)
		{
			target.castBlood(num);
		}else
		{
			target.getCardList().remove(dodgeCard);
		}
		return true;
	}

	@Override
	public boolean effectAfter(Hero hero, Hero target) {
		// TODO Auto-generated method stub
		return true;
	}

}
