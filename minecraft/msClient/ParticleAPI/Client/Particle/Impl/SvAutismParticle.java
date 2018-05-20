package msClient.ParticleAPI.Client.Particle.Impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Random;

import org.lwjgl.input.Mouse;

import msClient.ParticleAPI.Client.Particle.BorderingSide;
import msClient.ParticleAPI.Client.Particle.Particle;
import msClient.ParticleAPI.Client.Particle.SvParticle;
import msClient.ParticleAPI.Client.Utilities.RenderUtil;
import msClient.config.Vbs;

public class SvAutismParticle extends SvParticle {

	//private static final float MOVE_SPEED = 1.5F;

    private static final int RAINBOW_SPEED = 6000;
    private static final int RAINBOW_MULTIPLIER = -15;
    private static final int CONNECT_RANGE = 80;
	
	public SvAutismParticle(float radius) {
		super(radius, Vbs.speed);
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		setX((float)Math.random()*sr.getScaledWidth());
		setY((float)Math.random()*sr.getScaledHeight());
	}
	
	@Override
	public void draw() {
		RenderUtil.drawCircle((int)getX(), (int)getY(), getRadius(), RenderUtil.getRainbow(RAINBOW_SPEED, (int)(RAINBOW_MULTIPLIER * getY())));
	}
	
	@Override
	public void connect(int x, int y) {
		RenderUtil.connectPoints((int)getX(), (int)getY(), x, y);
	}
	
	@Override
	public void connect(int x, int y, float width) {
		RenderUtil.connectPoints((int)getX(), (int)getY(), x, y, width);
	}
}
