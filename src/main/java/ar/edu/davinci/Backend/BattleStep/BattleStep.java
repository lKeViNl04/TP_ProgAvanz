package ar.edu.davinci.Backend.BattleStep;

public class BattleStep {
    private String atacante;
    private String atacado;
    private float dano;
    private float autoDano;
    private float vidaAtacante;
    private float vidaAtacado;

    public BattleStep(String atacante, String atacado, float dano, float autoDano, float vidaAtacante, float vidaAtacado) {
        this.atacante = atacante;
        this.atacado = atacado;
        this.dano = dano;
        this.autoDano = autoDano;
        this.vidaAtacante = vidaAtacante;
        this.vidaAtacado = vidaAtacado;
    }

    public String getAtacante() {
        return atacante;
    }

    public String getAtacado() {
        return atacado;
    }

    public float getDano() {
        return dano;
    }

    public float getAutoDano() {
        return autoDano;
    }

    public float getVidaAtacante() {
        return vidaAtacante;
    }

    public float getVidaAtacado() {
        return vidaAtacado;
    }
}
