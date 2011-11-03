// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
// GuiScreen, StringTranslate, EnumOptions, GuiSmallButton,
// GameSettings, GuiSlider, GuiButton, GuiVideoSettings,
// GuiControls

public class GuiName extends GuiScreen
{

    private String cname;
public GuiName(GuiScreen guiscreen)
    {
        screenTitle = "Change Name";
        parentScreen = guiscreen;
    
    }
public void updateScreen()
{

namechange.updateCursorCounter();
}


    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        String s1 = mc.session.username;
        if(cname != null)
        {
            s1 = cname;
        }
        namechange = new GuiTextField(this, fontRenderer, width / 2 - 100, height / 4 + 73 + 12, 200, 20, s1);
        namechange.setMaxStringLength(16);
        controlList.add(new GuiButton(201, width / 2 - 100, height / 6 + 138, "Change name"));
        controlList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, stringtranslate.translateKey("gui.done")));
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(!guibutton.enabled)
        {
            return;
        }
        
        if(guibutton.id == 200)
        {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(parentScreen);
        }
        
        if(guibutton.id == 201)
        {
         String s1 = namechange.getText().trim();
           if(s1 != null) {
         mc.session.username = s1;
           }
        }
    }
    protected void keyTyped(char c, int i)
    {
    
     namechange.textboxKeyTyped(c, i);
            ((GuiButton)controlList.get(0)).enabled = namechange.getText().length() > 2;
            cname = namechange.getText();
        
    }
    protected void mouseClicked(int i, int j, int k)
    {
        super.mouseClicked(i, j, k);
        namechange.mouseClicked(i, j, k);
    }
    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, screenTitle, width / 2, 20, 0xffffff);
        namechange.drawTextBox();
        super.drawScreen(i, j, f);
    }

    private GuiScreen parentScreen;
    protected String screenTitle;
    private GuiTextField namechange;
}