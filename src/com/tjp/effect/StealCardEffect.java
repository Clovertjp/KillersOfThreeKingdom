package com.tjp.effect;

import java.util.Random;

import com.tjp.card.Card;
import com.tjp.hero.Hero;
import com.tjp.inter.IEffect;

public class StealCardEffect implements IEffect {

	@Override
	public boolean effectBefore(Hero hero, Hero target) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean effect(Hero hero, Hero target) {
		// TODO Auto-generated method stub
		if(target==null || target.getCardList().size()<=0)
		{
			return false;
		}
//		System.out.println("steal");
		int index=new Random().nextInt(target.getCardList().size());
		Card c=target.getCardList().remove(index);
		hero.getCardList().add(c);
		return true;
	}

	@Override
	public boolean effectAfter(Hero hero, Hero target) {
		// TODO Auto-generated method stub
		return true;
	}

}
