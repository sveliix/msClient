package msClient.test;

import msClient.config.Vbs;
import net.minecraft.client.gui.GuiIngame;

public class Animation {
	   
    public final static Animation instance = new Animation();
    //private CommandOn cmdTest;
    private int fadeAmount;
    private boolean fading;
   
    public int getFadeAmount() {
        return fadeAmount;
    }

    public void setFadeAmount(int fadeAmount) {
        this.fadeAmount = fadeAmount;
    }
   
    public boolean isFading() {
        return fading;
    }
   
    public void setFading (boolean fading){
        this.fading = fading;
    }
   
    public void animation(int amount, int time) {
        if (!Vbs.keyStrokesMod) {
            if (!(Animation.instance.getFadeAmount() == amount)) {
                Animation.instance.setFadeAmount(Animation.instance.getFadeAmount() + time);
            } else {
                Animation.instance.setFading(false);
            }
        } else {
            if (!(Animation.instance.getFadeAmount() == -(50))) {
                Animation.instance.setFadeAmount(Animation.instance.getFadeAmount() - time);
            } else {
                Animation.instance.setFading(false);
            }
        }
    }

}
