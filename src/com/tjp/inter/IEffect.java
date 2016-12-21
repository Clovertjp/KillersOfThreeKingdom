package com.tjp.inter;

import java.util.List;

import com.tjp.card.Card;
import com.tjp.hero.Hero;

public interface IEffect {
	
	public boolean effectBefore(Hero hero,Hero target);
	
	public boolean effect(Hero hero,Hero target);
	
	public boolean effectAfter(Hero hero,Hero target);

}
