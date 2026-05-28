public class SistemaPlaylist {
    //atributos da classe SistemaPlaylist, variaveis das playlists, catalogo, e historico(pilha)
    //resolvemos utilizar vetores simples pois há uma quantidade limitada de musicas disponibilizadas no .txt
    //as playlists tendo uma capacidade 10 e musicas 100
    private Playlist[] playlists;
    private int totalPlaylists;
    private Playlist playlistAtual;

    private Musica[] catalogo;
    private int totalMusicas;

    private Musica[] historico;
    private int topoHistorico;

    private Musica[] filaReproducao;
    private int inicioFila;
    private int fimFila;

    //construtor da classe, inicializa as playlists, catalogo, pilha de historico e a fila de reprodução
    public SistemaPlaylist(){
        playlists = new Playlist[10];
        totalPlaylists = 0;

        catalogo = new Musica[100];
        totalMusicas=  0;

        historico = new Musica[100];
        topoHistorico = -1; // -1 significa vazia, o 0 ja representaria uma posição valida do primeiro elemento
        // exemplo: topo++ -> topo = 0 -> historico[0] = primeira musica na posição 0

        filaReproducao = new Musica[100];
        inicioFila = 0;
        fimFila = -1;
    }
    
    // metodo que cadastra uma musica no catalogo (que tem capacidade para 100 musicas)
    public void cadastrarMusicaCatalogo(Musica musica) {
        catalogo[totalMusicas] = musica;
        totalMusicas++;
    }

    // percorre o catalogo com um for e exibe todas as musicas dentro do mesmo
    public void exibirCatalogo() {

        if (totalMusicas == 0) {
            System.out.println("Catálogo vazio.");
            return;
        }

        for(int i=0; i<totalMusicas; i++) {
            catalogo[i].exibir();
        }
    }

    // cria uma nova playlist e a define como atual
    public void criarPlaylist(String nome) {
        Playlist nova = new Playlist(nome);

        playlists[totalPlaylists] = nova;
        playlistAtual = nova; //define a nova playlist como atual

        totalPlaylists++;

        System.out.println("Playlist criada e selecionada");
    }

    // percorre todas as playlists com um for e exibe o conteudo
    public void exibirPlaylists() {

        if (totalPlaylists == 0) {
            System.out.println("Nenhuma playlist cadastrada.");
            return;
        }

        for (int i = 0; i < totalPlaylists; i++) {
            System.out.println(i + " - " + playlists[i].getNome());
        }
    }

    // seleciona uma playlist dado um indice do vetor playlists
    public void selecionarPlaylist(int indice) {

        if (indice < 0 || indice >= totalPlaylists) {
            System.out.println("Playlist inválida.");
            return;
        }

        playlistAtual = playlists[indice];

        System.out.println("Playlist selecionada: " + playlistAtual.getNome());
    }

    // busca uma musica no catalogo pelo id
    public Musica buscarMusicaCatalogo(int id) {
        for (int i=0; i<totalMusicas; i++) {
            if(catalogo[i].getId() ==id) {
                return catalogo[i];
            }
        }
        return null;
    }

    // adiciona uma musica do catalogo a playlist atual
    public void adicionarMusicaPlaylistAtual(int id) {
        //verifica se há uma playlist selecionada
        if(playlistAtual == null) {
            System.out.println("Nenhuma playlist selecionada.");
            return;
        }

        // busca musica no catalogo
        Musica musica = buscarMusicaCatalogo(id);

        // verifica se encontrou
        if(musica == null) {
           System.out.println("Musica não encontrada no catálogo.");
           return;
        }

        // adiciona à playlist
        playlistAtual.adicionarMusica(musica);
        System.out.println("Música adicionada à playlist.");
    }

    // remove uma musica da playlist
    public void removerMusicaPlaylistAtual(int id) {

    if (playlistAtual == null) {
        System.out.println("Nenhuma playlist selecionada.");
        return;
    }

    No musicaRemover = playlistAtual.buscarPorId(id);

    if (musicaRemover == null) {
        System.out.println("Música não encontrada na playlist.");
        return;
    }

    playlistAtual.removerPorId(id);

    System.out.println("Música removida da playlist.");
}

    //exibe a playlist atualmente selecionada
    public void exibirPlaylistAtual() {
        if(playlistAtual == null) {
            System.out.println("Nenhuma playlist selecionada.");
            return;
        }
        System.out.println("Playlist atual: " + playlistAtual.getNome());
        playlistAtual.exibirPlaylist();
    }

    // adiciona uma musica oa historico (push)
    public void adicionarAoHistorico(Musica musica) {

        topoHistorico++;

        historico[topoHistorico] = musica;
    }

    //remove e exibe a musica mais recente (pop)
    public void voltarReproducao() {

        // verifica se pilha esta vazia
        if (topoHistorico == -1) {
            System.out.println("Histórico vazio.");
            return;
        }

        // recupera musica do topo
        Musica musica = historico[topoHistorico];

        System.out.println("Voltando reprodução:");
        System.out.println("Reproduzindo " + musica.getTitulo() + " - " + musica.getArtista());

        // remove do topo
        topoHistorico--;
    }

    // exibe todo o historico  da musica mais recente a mais antiga
     public void exibirHistorico() {

        if (topoHistorico == -1) {
            System.out.println("Histórico vazio.");
            return;
        }

        System.out.println("Histórico de reprodução:");

        for (int i = topoHistorico; i >= 0; i--) {
            historico[i].exibir();
        }
    }
    public void adicionarMusicaFila(int id) {

    // busca musica no catálogo
    Musica musica = buscarMusicaCatalogo(id);

    // verifica se existe
    if (musica == null) {
        System.out.println("Música não encontrada no catálogo.");
        return;
    }

    // adiciona ao final da fila
    fimFila++;
    filaReproducao[fimFila] = musica;

    System.out.println("Música adicionada à fila de reprodução.");
}


public void reproduzirProxima() {

    // verifica se fila está vazia
    if (inicioFila > fimFila) {
        System.out.println("Fila de reprodução vazia.");
        return;
    }

    // recupera musica da frente da fila
    Musica musica = filaReproducao[inicioFila];

    System.out.println("Reproduzindo agora:");
    musica.exibir();

    // adiciona ao histórico
    adicionarAoHistorico(musica);

    // remove logicamente da fila
    inicioFila++;
}

public void exibirFila() {

    if (inicioFila > fimFila) {
        System.out.println("Fila de reprodução vazia.");
        return;
    }

    System.out.println("Fila de reprodução:");

    for (int i = inicioFila; i <= fimFila; i++) {
        filaReproducao[i].exibir();
    }
}

// serve para o if no case 14 do main, verificando se há uma playlist atual ja que o atributo é privado o main pode acessa-lo atraves desse metodo
public Playlist getPlaylistAtual() {
    return playlistAtual;
}

// chama o metodo que ordenada por ID (bubblesort) de Playlist.java
public void ordenarPlaylistAtualPorId() {

    if (playlistAtual == null) {
        System.out.println("Nenhuma playlist selecionada.");
        return;
    }

    playlistAtual.ordenarPorId();
}

// chama o metodo que ordenada por Titulo (bubblesort) de Playlist.java
public void ordenarPlaylistAtualPorTitulo() {

    if (playlistAtual == null) {
        System.out.println("Nenhuma playlist selecionada.");
        return;
    }

    playlistAtual.ordenarPorTitulo();
}

// chama o metodo que ordenada por Duracao (bubblesort) de Playlist.java
public void ordenarPlaylistAtualPorDuracao() {

    if (playlistAtual == null) {
        System.out.println("Nenhuma playlist selecionada.");
        return;
    }

    playlistAtual.ordenarPorDuracao();
}

}
