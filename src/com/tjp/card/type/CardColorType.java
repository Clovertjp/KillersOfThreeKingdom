package com.tjp.card.type;

public class CardColorType {
	
	public static final byte HEART = 1 ;//红桃
	public static final byte DIAMOND = 2 ;//方块
	public static final byte SPADE = 3 ;//黑桃
	public static final byte CLUB = 4 ;//梅花
	
	public static boolean isBlack(byte type)
	{
		if(type>2)
			return true;
		return false;
	}
	
	public static boolean isHeart(byte type)
	{
		if(type==HEART || type==SPADE)
		{
			return true;
		}
		return false;
	}

}
