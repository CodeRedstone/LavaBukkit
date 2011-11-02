// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

// Referenced classes of package net.minecraft.src:
//            GuiScreen, EntityPlayerSP, GuiIngame, ChatAllowedCharacters

public class GuiConsole extends GuiScreen
{

	public GuiConsole()
	{
		message = "";
		updateCounter = 0;
	}

	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
	}

	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}

	public void updateScreen()
	{
		updateCounter++;
	}

	protected void keyTyped(char c, int i)
	{
		if(i == 1)
		{
			mc.displayGuiScreen(null);
			return;
		}
		if(i == 28)
		{
			String s = message.trim();
			if(s.length() > 0)
			{
				String s1 = message.trim();


				String chat[] = s1.split(" ");
				if(chat[0].trim().equals("jump") ){ LavaBukkit.jumpHack = !LavaBukkit.jumpHack; }
				if(chat[0].equals("spam"))
				{
					try
					{
						boolean flag = false;
						String as1[] = s.split("=");
						String s2 = as1[1];
						int j = Integer.parseInt(as1[2]);
						for(int k = 0; k < j; k++)
						{
							mc.thePlayer.sendChatMessage(s2);
						}
					}
					catch(Exception exception)
					{
						mc.thePlayer.addChatMessage("Invalid Syntax: #spam=<Message>=[Integer]");
					}
				}
				if(chat[0].equals("help"))
				{
					mc.thePlayer.addChatMessage("\247cWelcome to LavaBukkit's Help page");
					mc.thePlayer.addChatMessage("credits");
					mc.thePlayer.addChatMessage("hChat - Hides Chat");
					mc.thePlayer.addChatMessage("target - Toggles KillAura targeting mode");
				}
				if(s1.equals("day")) { LavaBukkit.day = !LavaBukkit.day; }  
				if(chat[0].equals("credits"))
				{
					mc.thePlayer.addChatMessage("\247c### LavaBukkit ###");
					mc.thePlayer.addChatMessage("Please contribute by helping out on GitHub");
					mc.thePlayer.addChatMessage("http://www.github.com/XiCracked/LavaBukkit");
				}
				if(chat[0].equals("hChat")) LavaBukkit.hideChat = !LavaBukkit.hideChat;
				if(chat[0].equals("target")) 
				{
					LavaBukkit.targetPlayersOnly = !LavaBukkit.targetPlayersOnly;
					if (LavaBukkit.targetPlayersOnly)
					{
						mc.thePlayer.addChatMessage("KillAura will only target players.");
					}
					else
					{
						mc.thePlayer.addChatMessage("KillAura will target players and mobs.");
					}
				}

			}
			mc.displayGuiScreen(null);
			return;
		}


		if(i == 14 && message.length() > 0)
		{
			message = message.substring(0, message.length() - 1);
		}
		if(allowedCharacters.indexOf(c) >= 0 && this.message.length() < 100) {
			this.message = this.message + c;
		}
	}

	public void drawScreen(int i, int j, float f)
	{
		drawRect(2, height - 14, width - 2, height - 2, 0x80000000);
		drawString(fontRenderer, (new StringBuilder()).append("> ").append(message).append((updateCounter / 6) % 2 != 0 ? "" : "_").toString(), 4, height - 12, 0xe0e0e0);
		super.drawScreen(i, j, f);
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		if(var3 == 0) {
			if(this.mc.ingameGUI.field_933_a != null) {
				if(this.message.length() > 0 && !this.message.endsWith(" ")) {
					this.message = this.message + " ";
				}

				this.message = this.message + this.mc.ingameGUI.field_933_a;
				byte var4 = 100;
				if(this.message.length() > var4) {
					this.message = this.message.substring(0, var4);
				}
			} else {
				super.mouseClicked(var1, var2, var3);
			}
		}

	}

	protected String message;
	private int updateCounter;
	private static final String allowedCharacters;



	static 

	{

		allowedCharacters = ChatAllowedCharacters.allowedCharacters;

	}
}