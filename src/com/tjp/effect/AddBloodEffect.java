package com.tjp.effect;

import java.util.List;

import com.tjp.card.Card;
import com.tjp.hero.Hero;
import com.tjp.inter.IEffect;
/**
 * 加血效果
 * @author TangJP
 *
 */
public class AddBloodEffect  implements IEffect {
	
	public int num;
	
	public AddBloodEffect(int num)
	{
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
		if(hero==null)
		{
			return false;
		}
		hero.addBlood(num);
		return true;
	}

	@Override
	public boolean effectAfter(Hero hero, Hero target) {
		// TODO Auto-generated method stub
		return true;
	}

}
