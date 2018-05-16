package msClient.ParticleAPI.Client.Particle.Impl;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Random;

import org.lwjgl.input.Mouse;

import msClient.ParticleAPI.Client.Particle.BorderingSide;
import msClient.ParticleAPI.Client.Particle.Particle;
import msClient.ParticleAPI.Client.Utilities.RenderUtil;

public class AutismParticle extends Particle {

    private final Random random = new Random();

    private static final int MOVE_SPEED = 38;

    private static final int RAINBOW_SPEED = 6000;
    private static final int RAINBOW_MULTIPLIER = -15;
    private static final int CONNECT_RANGE = 80;

    public AutismParticle(float radius) {
        super(radius);
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int i = (int)(Math.random()*4);
        if(i == 0) {
        	setX(-1);
            setY(random.nextInt(sr.getScaledHeight()));
        }else if(i == 1) {
        	setX(random.nextInt(sr.getScaledWidth()));
            setY(-1);
        }else if(i == 2) {
        	setX(sr.getScaledWidth()+1);
            setY(random.nextInt(sr.getScaledHeight()+1));
        }else if(i == 3){
        	setX(random.nextInt(sr.getScaledWidth()+1));
            setY(sr.getScaledHeight()+1);
        }
        //System.out.println("i: " + i + " ; x: " + getX() + " ; y: " + getY());
    }
    
    @Override
    public void deploy(BorderingSide borderingSide) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        // Sexy Code
        switch (borderingSide) {
            case TOP:
                setY(0);
                setXIncrease(random.nextBoolean() ? random.nextInt(MOVE_SPEED) + 1 : -(random.nextInt(MOVE_SPEED) + 1));
                setYIncrease(random.nextInt(MOVE_SPEED) + 1);
                break;
            case LEFT:
                setX(0);
                setXIncrease(random.nextInt(MOVE_SPEED) + 1);
                setYIncrease(random.nextBoolean() ? random.nextInt(MOVE_SPEED) + 1 : -(random.nextInt(MOVE_SPEED) + 1));
                break;
            case BOTTOM:
                setY(scaledResolution.getScaledHeight());
                setXIncrease(random.nextBoolean() ? random.nextInt(MOVE_SPEED) + 1 : -(random.nextInt(MOVE_SPEED) + 1));
                setYIncrease(-(random.nextInt(MOVE_SPEED) + 1));
                break;
            case RIGHT:
                setX(scaledResolution.getScaledWidth());
                setXIncrease(-(random.nextInt(MOVE_SPEED) + 1));
                setYIncrease(random.nextBoolean() ? random.nextInt(MOVE_SPEED) + 1 : -(random.nextInt(MOVE_SPEED) + 1));
                break;
        }
    }

    @Override
    public void connect(int x, int y) {
        RenderUtil.connectPoints(getX(), getY(), x, y);
    }
    
    @Override
    public void connect(int x, int y, float width) {
    	RenderUtil.connectPoints(getX(), getY(), x, y);
    }

    @Override
    public void draw() {
    	
        RenderUtil.drawCircle(getX(), getY(), getRadius(), RenderUtil.getRainbow(RAINBOW_SPEED, RAINBOW_MULTIPLIER * getY()));
    }
}