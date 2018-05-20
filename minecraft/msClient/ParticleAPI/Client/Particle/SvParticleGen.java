package msClient.ParticleAPI.Client.Particle;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lwjgl.input.Mouse;

import msClient.ParticleAPI.Client.Particle.Impl.SvAutismParticle;
import msClient.config.Vbs;

public class SvParticleGen {
	
	private final int amount;
	
	

	public static ArrayList<SvParticle> particles = new ArrayList<SvParticle>();
	
    private static final int CONNECT_RANGE = 50;
    private static final int CONNECT_RANGE1 = 60;

	public SvParticleGen(int amount) {
		this.amount = amount;
	}
	
	public void tick() {
		while (amount > particles.size()) {
			particles.add(new SvAutismParticle(Vbs.size));
		}
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		
		for (SvParticle p : particles) {
			float currX = p.getX() + p.getXIncrease();
			float currY = p.getY() + p.getYIncrease();
			
			if (currX < 0) {
				p.setX(-p.getX() - p.getXIncrease());
				p.setXIncrease(-p.getXIncrease());
			} else if (currX > sr.getScaledWidth()) {
				p.setX(2*sr.getScaledWidth() - p.getXIncrease() - p.getX());
				p.setXIncrease(-p.getXIncrease());
			} else {
				p.setX(currX);
			}
			
			if (currY < 0) {
				p.setY(-p.getY() - p.getYIncrease());
				p.setYIncrease(-p.getYIncrease());
			} else if (currY > sr.getScaledHeight()) {
				p.setY(2*sr.getScaledHeight() - p.getYIncrease() - p.getY());
				p.setYIncrease(-p.getYIncrease());
			} else {
				p.setY(currY);
			}
			
			for(SvParticle p0 : particles) {
				if (p0.equals(p)) {continue;}
				
				if ((Math.abs(p.getX() - p0.getX()) < CONNECT_RANGE)
						&& (Math.abs(p.getY() - p0.getY()) < CONNECT_RANGE)) {
					p.connect((int)p0.getX(), (int)p0.getY());
				}
			}
			
			if (!(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu)) {
				
				if ((Math.abs(p.getX() - Mouse.getX()/sr.getScaleFactor()) < CONNECT_RANGE1)
						&& Math.abs(p.getY() - (sr.getScaledHeight() - Mouse.getY()/sr.getScaleFactor())) < CONNECT_RANGE1) {
					p.connect(Mouse.getX()/sr.getScaleFactor(), sr.getScaledHeight() - Mouse.getY()/sr.getScaleFactor(), Vbs.radius);
				}
			}
			p.draw();
		}
	}
	
	public static void updateParticles (float size, float speed) {
		for (SvParticle p : particles) {
			p.setSpeed(speed);
			p.setRadius(size);
		}
	}
}
