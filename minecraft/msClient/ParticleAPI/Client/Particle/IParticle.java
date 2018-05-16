package msClient.ParticleAPI.Client.Particle;

public interface IParticle {
    void draw();

    void connect(int x, int y);
    void connect(int x, int y, float width);
}