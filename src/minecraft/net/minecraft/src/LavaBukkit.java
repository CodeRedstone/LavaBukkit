package net.minecraft.src;

import java.awt.*;
import java.util.*;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;

public class LavaBukkit {
	static boolean[] keyStates = new boolean[256];

	public static boolean visibleNames = false;
	public static boolean jumpHack = false;
	public static int jumpHeight = 2;
	public static boolean radar = false;

	public static int radarX = 420;
	public static int radarY = 200;

	public static boolean day = false;
	public static boolean hideChat = false;
	
	public static boolean killAura = false;
	public static boolean targetPlayersOnly = false;

	public static ArrayList activatedHacks = new ArrayList();


	public static void tick(){
		activatedHacks = new ArrayList();
		if(checkKey(Keyboard.KEY_J)) jumpHack = !jumpHack;
		if(jumpHack) activatedHacks.add("Jump Heck");

		if(checkKey(Keyboard.KEY_P)) hideChat = !hideChat;
		if(hideChat) activatedHacks.add("Hide Chat");

		if(checkKey(Keyboard.KEY_V)) visibleNames = !visibleNames;
		if(visibleNames) activatedHacks.add("Nametags");

		if(checkKey(Keyboard.KEY_L)) radar = !radar;

		if(day){
			for(int t8 = -1; t8 < 0; t8++)
			{
				WorldInfo.worldTime = 5975L;
			}
		}
		
		if (checkKey(Keyboard.KEY_R)) killAura = !killAura;
		if (killAura) activatedHacks.add("KillAura");
	}

	private static boolean checkKey(int i){
		if(Minecraft.currentScreen != null){
			return false;
		}
		if(Keyboard.isKeyDown(i) != keyStates[i]){
			return keyStates[i] = !keyStates[i];
		}else{
			return false;
		}
	}

	public static void parseCommand(String s){
		try
		{
			String chat[] = s.trim().split(" ");
			if(chat[0].equals("#spam"))
			{
				try
				{
					boolean flag = false;
					String as1[] = s.split("=");
					String s1 = as1[1];
					int j = Integer.parseInt(as1[2]);
					for(int k = 0; k < j; k++)
					{
						Minecraft.getSendQueue().addToSendQueue(new Packet3Chat(s1));
					}
				}
				catch(Exception exception)
				{
					Minecraft.thePlayer.addChatMessage("Invalid Syntax: #spam=<Message>=[Integer]");
				}
			}
			if(chat[0].equals("#help"))
			{
				Minecraft.thePlayer.addChatMessage("\247cWelcome to LavaBukkit's Help page");
				Minecraft.thePlayer.addChatMessage("#credits");
				Minecraft.thePlayer.addChatMessage("#hChat - Hides Chat");
			}
			if(chat[0].equals("#credits"))
			{
				Minecraft.thePlayer.addChatMessage("\247c### LavaBukkit ###");
				Minecraft.thePlayer.addChatMessage("Please contribute by helping out on GitHub");
				Minecraft.thePlayer.addChatMessage("http://www.github.com/XiCracked/LavaBukkit");
			}
			if(chat[0].equals("#hChat")) LavaBukkit.hideChat = !LavaBukkit.hideChat;
			if(chat[0].equals("#jump")) LavaBukkit.jumpHack = !LavaBukkit.jumpHack;
			if(chat[0].equals("#radar")){
				LavaBukkit.radarX = Integer.parseInt(chat[1]);
				LavaBukkit.radarY = Integer.parseInt(chat[2]);
			}
		}
		catch(Exception ex){
			Minecraft.thePlayer.addChatMessage("Failed command. :(");
		}
	}
}
