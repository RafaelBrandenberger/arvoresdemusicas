    /* listas duplamente encadeadas, cada instancia da classe playlist na main pode-se armazenar multiplas musicas,
     cada musica adicionada cria-se um nó, que é ambos a posição de uma musica e ponteiro para a proxima e anterior */
public class Playlist {
    // atributos da classe
    private String nome;
    private No inicioLista = null; //ponteiro inicio da lista
    private No fimLista = null; //ponteiro fim da lista
    private int tamanho;

    // construtor da playlist, define nome e inicializa tamanho, prepara a lista para receber musicas
    public Playlist(String nome) {
       this.nome = nome;
       this.tamanho = 0;
    }

    // retorna o nome da playlist
    public String getNome() {
        return nome;
    }

    // retorna a quantidade de musicas
    public int getTamanho() {
        return tamanho;
    }
    
    // adiciona uma musica ao final da playlist
    public void adicionarMusica(Musica musica) {
        // cria-se um novo no com a musica recebida
        No novo = new No(musica);
        
        // se a playlist estiver vazia, adiciona-se a musica (novo no)
        if(inicioLista == null) {
            inicioLista = novo;
            fimLista = novo;

            // caso contrario liga o ultimo fim ao novo nó, faz o novo nó apontar para tras, e a atualiza o fim da lista
        } else {
            fimLista.setProximo(novo);
            novo.setAnterior(fimLista);
            fimLista = novo;
        }
        tamanho++;
    }

    // percorre a playlist com um while exibindo todas as musica (verifica se a mesma esta vazia em antemao)
    public void exibirPlaylist(){

        if (inicioLista == null) {
            System.out.println("Playlist vazia.");
            return;
        }

        No atual = inicioLista;

        while (atual != null) { 
            atual.getMusica().exibir();
            atual = atual.getProximo();
        }
    }

    // busca uma musica pelo id e retorna o nó (posição) correspondente
    public No buscarPorId(int id) {
        No atual = inicioLista;

        while (atual != null) { //começando pelo inicio percorre toda a playlist, se encontrar o id retorna o nó
            if (atual.getMusica().getId() == id) {
                return atual;
            }

            atual = atual.getProximo();
        }

        return null; // caso nao encontre retorna nulo
    }

    // metodo que remove uma musica da playlist pelo ID
    public void removerPorId(int id) {
        // chama o metodo de busca por id, no intuito de achar o nó a ser removido
        No removido = buscarPorId(id);

        if (removido == null) { // caso nao encontre
            System.out.println("Música não encontrada.");
            return;
        }
        // caso exista apenas uma musica declarase ambos inicio e fim como nulos
        if (inicioLista == fimLista) {
            inicioLista = null;
            fimLista = null;

        // caso o nó removido seja o primeiro, o inicio avança para o proximo nó e seu ponteiro anterior vira nulo
        } else if (removido == inicioLista) {
            inicioLista = inicioLista.getProximo();
            inicioLista.setAnterior(null);

        // caso o nó removido seja o ultimo, o fim recede para o ultimo nó e seu ponteiro anterior vira nulo
        } else if (removido == fimLista) {
            fimLista = fimLista.getAnterior();
            fimLista.setProximo(null);

        // caso ele esteja no meio da lista
        } else { 
            // liga-se o anterior ao proximo e vice-versa ( a <-> b <-> c ) -> (a <-> / <-> c) -> (a <-> c) ponteiros reconectados
            removido.getAnterior().setProximo(removido.getProximo());
            removido.getProximo().setAnterior(removido.getAnterior());
        }
        tamanho--;
    }
    
    //metodo de ordenacao bubblesort, é mais facil trocar o conteudo de lugar do que as posições, assim nao atrapalha os ponteiros
    private void trocarMusicas(No a, No b) {
    Musica temp = a.getMusica();
    a.setMusica(b.getMusica());
    b.setMusica(temp);
}

public void ordenarPorId() {

    if (inicioLista == null) {
        System.out.println("Playlist vazia.");
        return;
    }

    boolean trocou;

    do {
        trocou = false;

        No atual = inicioLista;

        while (atual.getProximo() != null) {

            if (atual.getMusica().getId() > atual.getProximo().getMusica().getId()) {

                trocarMusicas(atual, atual.getProximo());

                trocou = true;
            }

            atual = atual.getProximo();
        }

    } while (trocou);
    // o while percorre a playlist de musicas chamando o metodo bubblesort acima, trocando os conteudos de lugar e ordenando pelo ID
    System.out.println("Playlist ordenada por ID.");
}

public void ordenarPorTitulo() {

    if (inicioLista == null) {
        System.out.println("Playlist vazia.");
        return;
    }

    boolean trocou;

    do {
        trocou = false;

        No atual = inicioLista;

        while (atual.getProximo() != null) {

            if (atual.getMusica().getTitulo().compareToIgnoreCase(
                atual.getProximo().getMusica().getTitulo()) > 0) {

                trocarMusicas(atual, atual.getProximo());

                trocou = true;
            }

            atual = atual.getProximo();
        }

    } while (trocou);
    // o while percorre a playlist de musicas chamando o metodo bubblesort acima, trocando os conteudos de lugar e ordenando pelo titulo
    System.out.println("Playlist ordenada por título.");
}

public void ordenarPorDuracao() {

    if (inicioLista == null) {
        System.out.println("Playlist vazia.");
        return;
    }

    boolean trocou;

    do {
        trocou = false;

        No atual = inicioLista;

        while (atual.getProximo() != null) {

            if (atual.getMusica().getDuracao() >
                atual.getProximo().getMusica().getDuracao()) {

                trocarMusicas(atual, atual.getProximo());

                trocou = true;
            }

            atual = atual.getProximo();
        }

    } while (trocou);
    // o while percorre a playlist de musicas chamando o metodo bubblesort acima, trocando os conteudos de lugar e ordenando pela duração
    System.out.println("Playlist ordenada por duração.");
}
}
