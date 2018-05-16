package msClient.sv;

import java.awt.Rectangle;

import net.minecraft.client.Minecraft;

public class InGuiObject {
	
	int x, y, width, height;
	String val;
	String val2[];
	boolean isVisible;
	boolean isMehrZeilig;
	boolean scale;
	
	public InGuiObject() {
		
	}
	
	public InGuiObject(int x, int y, int width, int height, String val, boolean isVisible, boolean mehrzeilig, boolean scale) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.val = val;
		this.isVisible = isVisible;
		this.isMehrZeilig = mehrzeilig;
		this.scale = scale;
	}
	
	/*public InGuiObject(int x, int y, int width, int height, String val, boolean isVisible) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.val = val;
		this.isVisible = isVisible;
	}*/
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCoordX(int x) {
		this.x = x;
	}
	
	public void setCoordY(int y) {
		this.y = y;
	}
	
	public int getCoordX() {
		return this.x;
	}
	
	public int getCoordY() {
		return this.y;
	}
	
	public void setVisibility(boolean b) {
		this.isVisible = b;
	}
	
	public int getWidth() {
		int i = Minecraft.getMinecraft().fontRendererObj.getStringWidth(val);
		return i;
	}
	
	public int getHeight() {
		int i = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
		return i;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
}
