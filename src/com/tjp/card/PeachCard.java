package com.tjp.card;

import com.tjp.card.type.CardType;
import com.tjp.effect.AddBloodEffect;

public class PeachCard extends Card {
	public PeachCard(Integer num,Byte cardColorType) {
		// TODO Auto-generated constructor stub
		super("桃",num, cardColorType, CardType.PEACH);
		super.getEffectList().add(new AddBloodEffect(1));
	}
}
