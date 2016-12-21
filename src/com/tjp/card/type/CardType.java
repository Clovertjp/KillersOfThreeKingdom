package com.tjp.card.type;

import com.tjp.card.DodgeCard;
import com.tjp.card.KillCard;
import com.tjp.card.PeachCard;

public class CardType {
	
	private byte typeNum;
	private Class cardClass;
	private CardType(byte typeNum,Class cardClass) {
		// TODO Auto-generated constructor stub
		this.typeNum=typeNum;
		this.cardClass=cardClass;
	}
	
	public byte getTypeNum() {
		return typeNum;
	}
	public void setTypeNum(byte typeNum) {
		this.typeNum = typeNum;
	}
	public Class getCardClass() {
		return cardClass;
	}

	public void setCardClass(Class cardClass) {
		this.cardClass = cardClass;
	}



	public static final CardType KILL = new CardType((byte) 1,KillCard.class) ;//杀
	public static final CardType DODGE = new CardType((byte) 2,DodgeCard.class) ;//闪
	public static final CardType PEACH = new CardType((byte) 3,PeachCard.class) ;//桃
	public static final CardType CardNum = new CardType((byte) 4,null) ;//总数
	
	

}
