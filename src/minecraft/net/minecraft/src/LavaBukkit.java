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
	public static boolean day = false;
	public static boolean fly = false;
	public static boolean slowfly = false;
	public static boolean nofall = true;
	public static boolean hideChat = false;
	public static boolean radar = false;
	public static int radarX = 430;
	public static int radarY = 200;

	public static boolean killAura = false;
	public static boolean targetPlayersOnly = false;

	public static boolean sneak = false;
	public static double ja = 3.0;
	public static boolean Xray = false;

	public static ArrayList activatedHacks = new ArrayList();


	public static void tick(){
		activatedHacks = new ArrayList();
		if(checkKey(Keyboard.KEY_J)) jumpHack = !jumpHack;
		if(checkKey(Keyboard.KEY_F)) fly = !fly;
		if(checkKey(Keyboard.KEY_R)) radar = !radar;
		if(checkKey(Keyboard.KEY_X)) {
			Xray = !Xray;
			Minecraft.renderGlobal.loadRenderers();
		} 
		if(jumpHack) activatedHacks.add("Jump Heck");
		if(Xray) activatedHacks.add("Xray");
		if(nofall) activatedHacks.add("No fall");
		if(fly) {
			activatedHacks.add(slowfly ? "Flying slow":"Flying fast");
		}
		if(checkKey(Keyboard.KEY_P)) hideChat= !hideChat;
		if(checkKey(Keyboard.KEY_Z)) sneak = !sneak;
		if(hideChat) activatedHacks.add("Hide Chat");
		if(sneak) activatedHacks.add("Sneaking ");
		if(day) {
			activatedHacks.add("Day Time");
			for(int t8 = -1; t8 < 0; t8++)
			{
				WorldInfo.worldTime = 5975L;
			}
		}

		if (checkKey(Keyboard.KEY_R)) killAura = !killAura;
		if (killAura) activatedHacks.add("KillAura");
		if(checkKey(Keyboard.KEY_V)) visibleNames= !visibleNames;
		if(visibleNames) activatedHacks.add("Nametags");

	}

	public static void parseCommand(String s){
		String chat[] = s.trim().split(" ");
		if(chat[0].equals("keys")) 
		{
			Minecraft.thePlayer.addChatMessage("\247cKeybinds:");
			Minecraft.thePlayer.addChatMessage("F - Fly");
			Minecraft.thePlayer.addChatMessage("J - HighJump");
			Minecraft.thePlayer.addChatMessage("V - Nametags");
			Minecraft.thePlayer.addChatMessage("P - HideChat");
			Minecraft.thePlayer.addChatMessage("Z - Sneak");
		}
		if(chat[0].startsWith("sjump")) 
		{ 
			try{
				LavaBukkit.ja = Double.parseDouble(chat[1]);
			}catch(Exception ex){
				Minecraft.thePlayer.sendChatMessage("Invalid Syntax");
			}
		}
		if(chat[0].equals("day")) { LavaBukkit.day = !LavaBukkit.day; }
		if(chat[0].equals("hChat")) LavaBukkit.hideChat = !LavaBukkit.hideChat;
		if(chat[0].equals("slowfly")) { LavaBukkit.slowfly = !LavaBukkit.slowfly; } 
		if(chat[0].equals("nofall")) { LavaBukkit.nofall = !LavaBukkit.nofall; }
		if(chat[0].trim().equals("jump") ){ LavaBukkit.jumpHack =  !LavaBukkit.jumpHack; }
		if(chat[0].equals("spam"))
		{
			try
			{
				boolean flag = false;
				String s2 = chat[1];
				int j = Integer.parseInt(chat[2]);
				for(int k = 0; k < j; k++)
				{
					Minecraft.thePlayer.sendChatMessage(s2);
				}
			}
			catch(Exception exception)
			{
				Minecraft.thePlayer.addChatMessage("Invalid Syntax: #spam=<Message>=[Integer]");
			}
		}
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
}
