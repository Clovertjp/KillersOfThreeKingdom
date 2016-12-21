package com.tjp.effect;

import java.util.Random;

import com.tjp.hero.Hero;
import com.tjp.inter.IEffect;

public class DiscardTargetEffect implements IEffect {

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
		
		int index=new Random().nextInt(target.getCardList().size());
		target.getCardList().remove(index);
		return true;
	}

	@Override
	public boolean effectAfter(Hero hero, Hero target) {
		// TODO Auto-generated method stub
		return true;
	}

}
