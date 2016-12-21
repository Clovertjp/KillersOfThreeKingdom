package com.tjp.inter;

import java.util.List;

import com.tjp.card.Card;
import com.tjp.hero.Hero;

/**
 * 特效接口
 * @author TangJP
 *
 */
public interface IEffect {
	//特效生效前
	public boolean effectBefore(Hero hero,Hero target);
	//特效生效
	public boolean effect(Hero hero,Hero target);
	//特效生效后
	public boolean effectAfter(Hero hero,Hero target);

}
