package com.tjp.card;

import com.tjp.card.type.CardColorType;
import com.tjp.card.type.CardType;
import com.tjp.effect.KillEffect;

public class KillCard extends Card {
	
	public KillCard(Integer num,Byte cardColorType) {
		// TODO Auto-generated constructor stub
		super("杀",num, cardColorType, CardType.KILL);
		super.getEffectList().add(new KillEffect(1));
	}

}
