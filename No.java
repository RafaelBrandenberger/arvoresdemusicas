public class No {

    private Musica musica;
    private No proximo;
    private No anterior;

    public No(Musica musica) {
        this.musica = musica;
        this.proximo = null;
        this.anterior = null;
    }

    public Musica getMusica() {
        return musica;
    }
    
    public void setMusica(Musica musica) {
    this.musica = musica;
}

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public No getAnterior() {
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }
}
