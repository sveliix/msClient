package msClient.events.addons;

import msClient.config.Vbs;
import msClient.utils.OldSneaking;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class OldSneak {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static float run (EntityPlayer e) {
        return OldSneaking.getCustomEyeHeight(e);
	}

}
