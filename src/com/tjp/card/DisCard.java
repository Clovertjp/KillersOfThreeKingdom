package com.tjp.card;

import com.tjp.card.type.CardType;
import com.tjp.effect.DiscardTargetEffect;

/**
 * 
 * @author TangJP
 * @date 2016年12月21日
 */
public class DisCard extends Card {
	
	public DisCard(Integer num,Byte cardColorType) {
		// TODO Auto-generated constructor stub
		super("釜底抽薪",num, cardColorType, CardType.DISCARD);
		super.getEffectList().add(new DiscardTargetEffect());
	}
}
