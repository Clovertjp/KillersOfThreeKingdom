package com.tjp.main;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.tjp.game.manager.GameManager;


public class gameMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("######0.00");
		for(int j=0;j<10;j++)
		{
			int i=1;
			int sum=0;
			Map<Integer,Integer> map=new HashMap<>();
			map.put(1, 0);map.put(2, 0);
			for(;i<=100000;i++)
			{
				sum+=new GameManager().Play(map);
			}
			
			System.out.print("回合数: "+df.format((double)sum/(double)i)+"   ");
			System.out.print("先手输："+map.get(1)+"   ");
			System.out.print("后手输："+map.get(2));
			System.out.println();
		}
		
	}

}
