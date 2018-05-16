package msClient.sv;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import msClient.config.Variables;
import msClient.gui.MSMainMenu;
import msClient.utils.ClickCounter;

import java.awt.*;
import java.awt.event.MouseEvent;

import net.minecraft.client.gui.GuiIngameMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import net.minecraft.client.gui.GuiScreen;

import net.minecraft.util.ResourceLocation;

public class SVGuiPos extends GuiScreen{
	
	int currObj = -1;
	int[] currCoords = new int[2];
	int[] lastCoords = new int[2];
	int xDiff = 0;
	int yDiff = 0;
	ArrayList<Integer> linesX = new ArrayList<Integer>(); //Must be ordered Lists!
	ArrayList<Integer> linesY = new ArrayList<Integer>();
	
	private GuiScreen parentScreen;
	
	String message = "ESC to Exit";
	
	boolean isMouseOverMsg = false;
	
	boolean useLines = false;
	boolean tempBool = true;
	
	public static void readObjects() {
		// This doesn't do much right now; i'll keep it just in case i wanna import igo's form other classes at some point
	}
	
	private static boolean collideRect(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
		
		if (((x1 > x2 && x1 < x2 + width2) ||
				(x1 + width1 > x2 && x1 + width1 < x2 + width2)) && 
				((y1 > y2 && y1 < y2 + height2) ||
				(y1 + height1 > y2 && y1 + height1 < y2 + height2))) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void initGui() {
		
		
		
		if (tempBool) {
			readObjects();
			
			// Reading values from lines.txt
			linesX.add(0); linesX.add(100); linesX.add(539); linesX.add(639);
			linesY.add(0); linesY.add(100); linesY.add(259); linesY.add(359);
			tempBool = false;
		}
		
		int lineColor = Color.WHITE.getRGB();
		if (!useLines) {
			lineColor = Color.LIGHT_GRAY.getRGB();
		}
		
		for (int l : linesX) {
			this.drawVerticalLine(l, 0, height, lineColor);
		}
		for (int l : linesY) {
			this.drawHorizontalLine(0, width, l, lineColor);
		}
		
		
		for(InGuiObject o : Variables.objects) {
			if(o.isMehrZeilig) {
				String[] vals = o.val.split(";");
				if (o.isVisible) {
					this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.TRANSLUCENT);
					this.drawString(fontRendererObj, vals[0], o.x, o.y, Color.WHITE.getRGB());
					this.drawString(fontRendererObj, vals[1], o.x+10, o.y, Color.WHITE.getRGB());
					this.drawString(fontRendererObj, vals[2], o.x+20, o.y, Color.WHITE.getRGB());
				} else {
					this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.RED.getRGB());
					this.drawString(fontRendererObj, vals[0], o.x, o.y, Color.WHITE.getRGB());
					this.drawString(fontRendererObj, vals[1], o.x+10, o.y, Color.WHITE.getRGB());
					this.drawString(fontRendererObj, vals[2], o.x+20, o.y, Color.WHITE.getRGB());
				}
			} else {
				if (o.isVisible) {
					this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.TRANSLUCENT);
					this.drawString(fontRendererObj, o.val, o.x, o.y, Color.WHITE.getRGB());
				} else {
					this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.RED.getRGB());
					this.drawString(fontRendererObj, o.val, o.x, o.y, Color.WHITE.getRGB());
				}
		  	}
		}
	}
	
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if(keyCode == Keyboard.KEY_ESCAPE) {
			Minecraft.getMinecraft().displayGuiScreen(this.parentScreen);
			return;
		} else if (keyCode == Keyboard.KEY_L) { // L
			//useLines = !useLines;
			initGui();
		}
		super.keyTyped(typedChar, keyCode);
	}
	
	public SVGuiPos(GuiScreen parentScreen) {
		this.parentScreen = parentScreen;
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException{
		
		for(int i = 0; i < Variables.objects.size(); i++) {
			InGuiObject o = Variables.objects.get(i);
			if(o.isMehrZeilig) {
				if (mouseX >= o.x && mouseX <= o.x + o.width &&
						mouseY >= o.y && mouseY <= o.y + o.height*3
						&& currObj == -1) {
					xDiff = o.x - mouseX;
					yDiff = o.y - mouseY;
					currCoords[0] = o.x; currCoords[1] = o.y;
					currObj = i;
					break;
				}
			} else {
				if (mouseX >= o.x && mouseX <= o.x + o.width &&
						mouseY >= o.y && mouseY <= o.y + o.height
						&& currObj == -1) {
					xDiff = o.x - mouseX;
					yDiff = o.y - mouseY;
					currCoords[0] = o.x; currCoords[1] = o.y;
					currObj = i;
					break;
				}
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		if (currObj != -1) {
			Variables.objects.get(currObj).setCoords(currCoords[0], currCoords[1]);
		}
		currObj = -1;
		
		initGui(); // Not sure whether this is necessary, ill do it just in case
		
		super.mouseReleased(mouseX, mouseY, state);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		Variables.objects.get(0).val = "FPS: 120" ;		
		Variables.objects.get(1).val = "X: 150 ;Y: 150 ;Z: 50" ;		
		Variables.objects.get(2).val = "CPS: 15";
		Variables.objects.get(3).val = "Mem: 25%";
		
		for(InGuiObject k : Variables.objects) {
			if(!k.scale) {
				if(k.isMehrZeilig) {					
					int w = (int) (k.getWidth());
					int h = (int) ((k.getHeight())*3);		
					k.setWidth(w);
					k.setHeight(h);					
				}
				int w = (int) (k.getWidth());
				int h = (int) (k.getHeight());		
				k.setWidth(w);
				k.setHeight(h);				
			}else {
			if(k.isMehrZeilig) {
				GL11.glPushMatrix();
				GL11.glScalef(Variables.x, Variables.x, Variables.x);
				int w = (int) (k.getWidth() * Variables.x);
				int h = (int) ((k.getHeight() * Variables.x)*3);		
				k.setWidth(w);
				k.setHeight(h);
				GL11.glPopMatrix();
			}
			
			GL11.glPushMatrix();
			GL11.glScalef(Variables.x, Variables.x, Variables.x);
			int w = (int) (k.getWidth() * Variables.x);
			int h = (int) (k.getHeight() * Variables.x);		
			k.setWidth(w);
			k.setHeight(h);
			GL11.glPopMatrix();
			}
		}
		
		drawDefaultBackground();
		
		int lineColor = Color.WHITE.getRGB();
		if (!useLines) {
			lineColor = Color.GRAY.getRGB();
		}
		
		for (int l : linesX) {
			this.drawVerticalLine(l, 0, height, lineColor);
		}
		for (int l : linesY) {
			this.drawHorizontalLine(0, width, l, lineColor);
		}
		
		
		for(int i = 0; i < Variables.objects.size(); i++) {
			if(i == currObj) {continue;}
			
			InGuiObject o = Variables.objects.get(i);
			this.drawRect(o.x, o.y, o.x + o.width, o.y + o.height*3, Color.TRANSLUCENT);
			
			if(!o.scale) {
				if(o.isMehrZeilig) {
					String[] vals = o.val.split(";");
					if (o.isVisible) {
						//this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.TRANSLUCENT);
						this.drawString(fontRendererObj, vals[0], Math.round(o.x), Math.round((o.y)), Color.WHITE.getRGB());
						this.drawString(fontRendererObj, vals[1], Math.round(o.x), Math.round(o.y)+10, Color.WHITE.getRGB());
						this.drawString(fontRendererObj, vals[2], Math.round(o.x), Math.round((o.y))+20, Color.WHITE.getRGB());
					} else {
						//this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.RED.getRGB());
						this.drawString(fontRendererObj, vals[0], Math.round(o.x), Math.round(o.y), Color.LIGHT_GRAY.getRGB());
						this.drawString(fontRendererObj, vals[1], Math.round(o.x), Math.round(o.y), Color.LIGHT_GRAY.getRGB());
						this.drawString(fontRendererObj, vals[2], Math.round(o.x), Math.round(o.y), Color.LIGHT_GRAY.getRGB());
					}
				  }else {
					  if (o.isVisible) {
							//this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.TRANSLUCENT);
							this.drawString(fontRendererObj, o.val, Math.round(o.x), Math.round(o.y), Color.WHITE.getRGB());
						} else {
							//this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.RED.getRGB());
							this.drawString(fontRendererObj, o.val, Math.round(o.x), Math.round(o.y), Color.LIGHT_GRAY.getRGB());
						}
				  }
			}else {
			
			GL11.glPushMatrix();
			GL11.glScalef(Variables.x, Variables.x, Variables.x);
						
			if(o.isMehrZeilig) {
				String[] vals = o.val.split(";");
				if (o.isVisible) {
					//this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.TRANSLUCENT);
					this.drawString(fontRendererObj, vals[0], Math.round(o.x/Variables.x), Math.round((o.y)/Variables.x), Color.WHITE.getRGB());
					this.drawString(fontRendererObj, vals[1], Math.round(o.x/Variables.x), Math.round(o.y/Variables.x)+10, Color.WHITE.getRGB());
					this.drawString(fontRendererObj, vals[2], Math.round(o.x/Variables.x), Math.round((o.y)/Variables.x)+20, Color.WHITE.getRGB());
				} else {
					//this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.RED.getRGB());
					this.drawString(fontRendererObj, vals[0], Math.round(o.x/Variables.x), Math.round(o.y/Variables.x), Color.LIGHT_GRAY.getRGB());
					this.drawString(fontRendererObj, vals[1], Math.round(o.x/Variables.x), Math.round(o.y/Variables.x), Color.LIGHT_GRAY.getRGB());
					this.drawString(fontRendererObj, vals[2], Math.round(o.x/Variables.x), Math.round(o.y/Variables.x), Color.LIGHT_GRAY.getRGB());
				}
			  }else {
				  if (o.isVisible) {
						//this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.TRANSLUCENT);
						this.drawString(fontRendererObj, o.val, Math.round(o.x/Variables.x), Math.round(o.y/Variables.x), Color.WHITE.getRGB());
					} else {
						//this.drawRect(o.x, o.y, o.x + o.getWidth(), o.y + o.getHeight()*3, Color.RED.getRGB());
						this.drawString(fontRendererObj, o.val, Math.round(o.x/Variables.x), Math.round(o.y/Variables.x), Color.LIGHT_GRAY.getRGB());
					}
			  }
			
			
			/*if(o.isVisible) {
				this.drawString(fontRendererObj, o.val, Math.round(o.x/Variables.x), Math.round(o.y/Variables.x), Color.WHITE.getRGB());
			} else {
				this.drawString(fontRendererObj, o.val,  Math.round(o.x/Variables.x), Math.round(o.y/Variables.x), Color.LIGHT_GRAY.getRGB());
			}*/
			GL11.glPopMatrix();
			}
		}
		
		if(!(mouseX >= 200 && mouseX <= 440 && mouseY <= 100)) {
			this.drawCenteredString(fontRendererObj, message, 320, 50, Color.WHITE.getRGB());
		} else {
			this.drawCenteredString(fontRendererObj, message, 320, 310, Color.WHITE.getRGB());
		}
		
		
		if (currObj != -1) {
			InGuiObject o = Variables.objects.get(currObj);

			int drawX = mouseX + xDiff; int drawY = mouseY + yDiff;
			int[] changes = new int[2];
			
			changes[0] = drawX - currCoords[0];
			changes[1] = drawY - currCoords[1];
			
			
			// CHECKING FOR COLLISION WITH OTHER IGOS
			
			
			for (int i = 0; i < Variables.objects.size(); i++) {
				if (i == currObj) {continue;}
				InGuiObject o1 = Variables.objects.get(i);
				
				if (!collideRect(drawX, drawY, o.width, o.height, o1.x, o1.y, o1.width, o1.height)) {continue;}
				
				int currX = currCoords[0];
				int currY = currCoords[1];
				
				for (int j = 0; j < changes[0] + changes[1]; j++) {
					if (changes[0] == 0) {
						currY++;
						if (collideRect(currX, currY, o.width, o.height, o1.x, o1.y, o1.width, o1.height)) {
							drawY = --currY;
							break;
						}
					} else {
						int temp = Math.floorDiv(changes[0], changes[0] + changes[1]); // trying to have the checking position
						if (temp == 0) {
							temp = 1;
						}
						if (j % temp == 0 && currX != currCoords[0] + changes[0]) { // moving upwards in a diagonal line
							currX++;
							if(collideRect(currX, currY, o.width, o.height, o1.x, o1.y, o1.width, o1.height)) {
								drawX = --currX;
								break;
							}
						} else {
							currY++;
							if(collideRect(currX, currY, o.width, o.height, o1.x, o1.y, o1.width, o1.height)) {
								drawY = --currY;
								break;
							}
						}
					}
				}
			}
			
			// MAKING THE IGO STICK TO THE LINES
			
			if(useLines) {
				int tempX = -1, tempY = -1;
			
				for(int l : linesX) {
					if(l - (drawX + o.width) <= 10 && l - (drawX + o.width) >= 0) {
						tempX = l - (drawX + o.width);
						drawX = l - o.width;
						break;
					}
				}
			
				for(int i = linesX.size() - 1; i >= 0; i--) {
					if(mouseX + xDiff - linesX.get(i) <= 10 && mouseX + xDiff - linesX.get(i) >= 0) {
						if (tempX != -1) {
							if(mouseX + xDiff - linesX.get(i) > tempX) {
								drawX = linesX.get(i);
							}
						} else {
							drawX = linesX.get(i);
						}
						break;
					}
				}
			
				for(int l : linesY) {
					if(l - (drawY + o.height) <= 10 && l - (drawY + o.height) >= 0) {
						tempY = l - (drawY + o.height);
						drawY = l - o.height;
						break;
					}
				}
				
				for(int i = linesY.size() - 1; i >= 0; i--) {
					if (mouseY + yDiff - linesY.get(i) <= 10 && mouseY + yDiff - linesY.get(i) >= 0) {
						if (tempY != -1) {
							if (mouseY + yDiff - linesY.get(i) > tempY) {
								drawY = linesY.get(i);
							}
						} else {
							drawY = linesY.get(i);
						}
						break;
					}
				}
			}
			
			this.drawRect(drawX, drawY, drawX + o.getWidth()/3, drawY + o.getHeight()*3, Color.TRANSLUCENT);
			
			if(!o.scale) {
				if(o.isMehrZeilig) {
					String[] vals = o.val.split(";");	
					this.drawString(fontRendererObj, vals[0], Math.round(drawX), Math.round((drawY)), Color.BLACK.getRGB());
					this.drawString(fontRendererObj, vals[1], Math.round(drawX), Math.round((drawY))+10, Color.BLACK.getRGB());
					this.drawString(fontRendererObj, vals[2], Math.round(drawX), Math.round((drawY))+20, Color.BLACK.getRGB());
				}else {
					this.drawString(fontRendererObj, o.val, Math.round(drawX), Math.round(drawY), Color.BLACK.getRGB());					
				}
			}
			else {
			GL11.glPushMatrix();
			GL11.glScalef(Variables.x, Variables.x, Variables.x);		
			if(o.isMehrZeilig) {
				String[] vals = o.val.split(";");	
				this.drawString(fontRendererObj, vals[0], Math.round(drawX/Variables.x), Math.round((drawY)/Variables.x), Color.BLACK.getRGB());
				this.drawString(fontRendererObj, vals[1], Math.round(drawX/Variables.x), Math.round((drawY)/Variables.x)+10, Color.BLACK.getRGB());
				this.drawString(fontRendererObj, vals[2], Math.round(drawX/Variables.x), Math.round((drawY)/Variables.x)+20, Color.BLACK.getRGB());
			}else {
				this.drawString(fontRendererObj, o.val, Math.round(drawX/Variables.x), Math.round(drawY/Variables.x), Color.BLACK.getRGB());					
			}
			GL11.glPopMatrix();
			}
			currCoords[0] = drawX; currCoords[1] = drawY;
		}
		this.mc.gameSettings.saveMSOptions();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
}
