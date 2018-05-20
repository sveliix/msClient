package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import msClient.config.Vbs;
import msClient.utils.AccountManager;
import msClient.utils.ModGuiTextField;

public class MSLogin extends GuiScreen
{
    
    GuiScreen lastScreen;
    String selectedFriend = "";
    boolean allowScroll = false;
    ModGuiTextField username;
    ModGuiTextField password;
    GuiButton done;
    GuiButton cancel;
    String badLogin = "";
    long time = 0L;
    boolean flash = false;
    boolean login = false;

    public MSLogin(GuiScreen lastScreen)
    {
        //this.draw = LabyMod.getInstance().draw;
        this.lastScreen = lastScreen;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.username = new ModGuiTextField(-1, this.fontRendererObj, this.width / 2 - 100, this.height / 2 - 50, 200, 20);
        this.username.setBlacklistWord(" ");
        this.username.setText(AccountManager.accountManagerUsername);
        this.username.setMaxStringLength(64);
        this.password = new ModGuiTextField(-1, this.fontRendererObj, this.width / 2 - 100, this.height / 2, 200, 20);
        this.password.setBlacklistWord(" ");
        this.password.setText(AccountManager.accountManagerPassword);
        this.password.setPasswordBox(true);
        this.password.setMaxStringLength(64);
        this.done = new GuiButton(0, this.width / 2 - 100, this.height / 2 + 28, "Login");
        this.buttonList.add(this.done);
        this.cancel = new GuiButton(1, this.width / 2 - 100, this.height / 2 + 53, "Cancel");
        this.buttonList.add(this.cancel);
        super.initGui();
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (!this.login)
        {
            if (this.username.textboxKeyTyped(typedChar, keyCode))
            {
                AccountManager.accountManagerUsername = this.username.getText();
            }

            if (this.password.textboxKeyTyped(typedChar, keyCode))
            {
                AccountManager.accountManagerPassword = this.password.getText();
            }
        }

        if (this.done.enabled && (keyCode == 28 || keyCode == 156))
        {
            this.actionPerformed(this.done);
        }

        if (keyCode == 15)
        {
            if (!this.password.isFocused())
            {
                this.username.setFocused(false);
                this.password.setFocused(true);
            }
            else
            {
                this.username.setFocused(true);
                this.password.setFocused(false);
            }
        }

        if (keyCode == 1)
        {
            if (this.login)
            {
                this.mc.displayGuiScreen(this.lastScreen);
            }
        }
        else
        {
            super.keyTyped(typedChar, keyCode);
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        this.username.mouseClicked(mouseX, mouseY, mouseButton);
        this.password.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        super.actionPerformed(button);

        if (button.id == 0)
        {
            this.login = true;
            (new MSLogin.Login()).start();
        }

        if (button.id == 1)
        {
            this.mc.displayGuiScreen(this.lastScreen);
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	/*if(MSColorGui.rainBow) {
			variables.MSColor = java.awt.Color.HSBtoRGB((float)(System.currentTimeMillis()/1.5 % 1000L) / 1000.0F, 0.8F, 0.8F);
    	}*/
        this.drawDefaultBackground();       
        this.done.enabled = !this.login;
        this.cancel.enabled = !this.login;
        this.fontRendererObj.drawString("Username/Email:", (int)(this.width / 2 - 100), (int)(this.height / 2 - 63), Vbs.MSColor);
        this.fontRendererObj.drawString("Password:", (int)(this.width / 2 - 100), (int)(this.height / 2 - 13), Vbs.MSColor);

        if (!this.badLogin.isEmpty())
        {
            String s = "§f";
            drawRect(0, 10, this.width, 30, java.awt.Color.RED.getRGB());

            if (this.time + 1000L > System.currentTimeMillis() && this.flash)
            {
                this.drawCenteredString(this.fontRendererObj, s + "Error: " + this.badLogin, this.width / 2 - 1, 16, Vbs.MSColor);
            }
            else
            {
                this.drawCenteredString(this.fontRendererObj, s + "Error: " + this.badLogin, this.width / 2, 16, Vbs.MSColor);
            }

            this.flash = !this.flash;
        }

        if (this.login)
        {
            drawRect(0, 10, this.width, 30, java.awt.Color.BLUE.getRGB());
            this.drawCenteredString(this.fontRendererObj, "Logging in..", this.width / 2 - 1, 16, Vbs.MSColor);
        }

        this.username.drawTextBox();
        this.password.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    class Login extends Thread
    {
        public void run()
        {
            String s = AccountManager.login(MSLogin.this.username.getText(), MSLogin.this.password.getText());

            if (s.isEmpty())
            {
                MSLogin.this.badLogin = "";
                MSLogin.this.mc.displayGuiScreen(MSLogin.this.lastScreen);
            }
            else
            {
                MSLogin.this.badLogin = s;
                MSLogin.this.time = System.currentTimeMillis();
            }

            MSLogin.this.login = false;
        }
    }
}
