package msClient.ParticleAPI.Client.Particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Random;

import org.lwjgl.input.Mouse;

import msClient.ParticleAPI.Client.Particle.BorderingSide;
import msClient.ParticleAPI.Client.Particle.Particle;
import msClient.ParticleAPI.Client.Utilities.RenderUtil;
import msClient.config.Variables;

public abstract class SvParticle implements IParticle {
	
	private float x;
    private float y;

    private float radius;
    private float speed;

    private float xIncrease;
    private float yIncrease;

    // public:

    public SvParticle(float radius, float speed) {
        this.radius = radius;
        this.speed = speed;
        
        int facY = (int)Math.round(Math.random())*2 - 1;
		int facX = (int)Math.round(Math.random())*2 - 1;
		// factors will be either -1 or 1
        
        float tempSpeed = facX * (float)Math.random()*this.speed;
        this.xIncrease = tempSpeed;
        this.yIncrease = facY * (float)Math.sqrt(
				Math.pow(Variables.speed, 2) - Math.pow(tempSpeed, 2));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }
    
    public void setRadius(float radius) {
    	this.radius = radius;
    }
    
    public float getSpeed() {
    	return this.speed;
    }
    
    public void setSpeed(float speed) {
    	float currSpeed = getSpeed();
    	
    	float fac = speed / currSpeed;
    	
    	setXIncrease(fac*getXIncrease());
    	setYIncrease(fac*getYIncrease());
    	
    	setSpeed(speed);
    }

    float getXIncrease() {
        return xIncrease;
    }

    protected void setXIncrease(float xIncrease) {
        this.xIncrease = xIncrease;
    }

    float getYIncrease() {
        return yIncrease;
    }

    protected void setYIncrease(float yIncrease) {
        this.yIncrease = yIncrease;
    }

}
