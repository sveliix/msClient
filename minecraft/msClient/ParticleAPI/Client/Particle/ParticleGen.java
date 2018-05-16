package msClient.ParticleAPI.Client.Particle;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lwjgl.input.Mouse;

import msClient.ParticleAPI.Client.Particle.Impl.AutismParticle;

public class ParticleGen {

    private final int amount;

    private final List<Particle> particles = new ArrayList<>();
    private final Set<BorderingSide> borderedSides = new HashSet<>();

    private static final int CONNECT_RANGE = 50;
    private static final int CONNECT_RANGE1 = 50;

    // public:

    public ParticleGen(int amount) {
        this.amount = amount;
    }

    public void drawParticles() {
    	
        if (particles.size() < amount)
            particles.add(new AutismParticle(2F));

        particles.forEach(particle -> {
            final BorderingSide borderingSide = resolveBorderingSide(particle.getX(), particle.getY());

            if (borderingSide != null) {
                particle.deploy(borderingSide);

                if (!borderedSides.contains(borderingSide)) borderedSides.add(borderingSide);
            } else {
                particle.setX(particle.getX() + particle.getXIncrease());
                particle.setY(particle.getY() + particle.getYIncrease());
            }

            if ( borderedSides.size() == BorderingSide.values().length) {
                particles.stream().filter(possiblyConnectable ->
                        (possiblyConnectable.getX() > particle.getX() && possiblyConnectable.getX() - particle.getX() < CONNECT_RANGE
                                || particle.getX() > possiblyConnectable.getX() && particle.getX() - possiblyConnectable.getX() < CONNECT_RANGE)
                                && (possiblyConnectable.getY() > particle.getY() && possiblyConnectable.getY() - particle.getY() < CONNECT_RANGE
                                || particle.getY() > possiblyConnectable.getY() && particle.getY() - possiblyConnectable.getY() < CONNECT_RANGE))
                        .forEach(connectable -> particle.connect(connectable.getX(), connectable.getY()));
            }
            
            if (borderedSides.size() == BorderingSide.values().length && !(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu)) {
                particles.stream().filter(possiblyConnectable ->
                        (Mouse.getX()/3 > particle.getX() && Mouse.getX()/3 - particle.getX() < CONNECT_RANGE1
                                || particle.getX() > Mouse.getX()/3 && particle.getX() - Mouse.getX()/3 < CONNECT_RANGE1)
                                && ((360 - Mouse.getY()/3) > particle.getY() && (360 - Mouse.getY()/3) - particle.getY() < CONNECT_RANGE1
                                || particle.getY() > (360 - Mouse.getY()/3) && particle.getY() - (360 - Mouse.getY()/3) < CONNECT_RANGE1))
                        .forEach(connectable -> particle.connect(Mouse.getX()/3, 360 - Mouse.getY()/3));
            }
            particle.draw();
        });
    }

 
    private BorderingSide resolveBorderingSide(int x, int y) {
         ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        if (x < 0)
            return BorderingSide.LEFT;
        else if (y < 0)
            return BorderingSide.TOP;
        else if (x > scaledResolution.getScaledWidth())
            return BorderingSide.RIGHT;
        else if (y > scaledResolution.getScaledHeight())
            return BorderingSide.BOTTOM;
        else return null;
    }

}