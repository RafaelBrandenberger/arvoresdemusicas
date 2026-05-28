public class ArvoreMusicas {
    NoArvore raiz;

    ArvoreMusicas() {
        this.raiz = null;
    }

    public void inserir(Musica musica) {
        raiz = inserirRecursivo(raiz, musica);
    }

    private NoArvore inserirRecursivo(NoArvore no, Musica musica) {
        if (no == null) return new NoArvore(musica);

        if (musica.getId() < no.musica.getId())
            no.esq = inserirRecursivo(no.esq, musica);
        else if (musica.getId() > no.musica.getId())
            no.dir = inserirRecursivo(no.dir, musica);

        return no;
    }

    public Musica buscar(int id) {
        return buscarRecursivo(raiz, id);
    }

    private Musica buscarRecursivo(NoArvore no, int id) {
        if (no == null) return null;
        if (id == no.musica.getId()) return no.musica;

        if (id < no.musica.getId())
            return buscarRecursivo(no.esq, id);
        else
            return buscarRecursivo(no.dir, id);
    }

    public void imprimirEmOrdem() {
        System.out.println("\n ÁRVORE DE MÚSICAS (ordem por ID) ");
        emOrdem(raiz);
    }

    private void emOrdem(NoArvore no) {
        if (no != null) {
            emOrdem(no.esq);
            no.musica.exibir();
            emOrdem(no.dir);
        }
    }
}