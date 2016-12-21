package com.tjp.card;

import com.tjp.card.type.CardColorType;
import com.tjp.card.type.CardType;
import com.tjp.effect.DodgeEffect;
/**
 * 闪 卡
 * @author TangJP
 *
 */
public class DodgeCard extends Card {
	
	public DodgeCard(Integer num,Byte cardColorType) {
		// TODO Auto-generated constructor stub
		super("闪",num, cardColorType, CardType.DODGE);
		super.getEffectList().add(new DodgeEffect());
	}

}
