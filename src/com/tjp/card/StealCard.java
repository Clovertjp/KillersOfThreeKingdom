/**
 * 
 */
package com.tjp.card;

import com.tjp.card.type.CardType;
import com.tjp.effect.DiscardTargetEffect;
import com.tjp.effect.StealCardEffect;

/**
 * @author TangJP
 * @date 2016年12月21日
 */
public class StealCard extends Card {
	
	public StealCard(Integer num,Byte cardColorType) {
		// TODO Auto-generated constructor stub
		super("探囊取物",num, cardColorType, CardType.STEALCARD);
		super.getEffectList().add(new StealCardEffect());
	}
}
