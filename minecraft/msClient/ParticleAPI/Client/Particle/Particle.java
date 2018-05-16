package msClient.ParticleAPI.Client.Particle;

public abstract class Particle implements IParticle {

    private int x;
    private int y;

    private final float radius;

    private int xIncrease;
    private int yIncrease;

    // public:

    public Particle(float radius) {
        this.radius = radius;
        
        x = -1;
        y = -1;
    }

    public abstract void deploy(BorderingSide borderingSide);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    int getXIncrease() {
        return xIncrease;
    }

    protected void setXIncrease(int xIncrease) {
        this.xIncrease = xIncrease;
    }

    int getYIncrease() {
        return yIncrease;
    }

    protected void setYIncrease(int yIncrease) {
        this.yIncrease = yIncrease;
    }
}