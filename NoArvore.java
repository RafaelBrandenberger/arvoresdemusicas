public class NoArvore {
    Musica musica;   
    NoArvore esq;    
    NoArvore dir;    

    NoArvore(Musica musica) {
        this.musica = musica;
        this.esq = null;
        this.dir = null;
    }
}