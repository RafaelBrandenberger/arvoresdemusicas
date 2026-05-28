import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SistemaPlaylist sistema = new SistemaPlaylist();
        ArvoreMusicas arvore = new ArvoreMusicas(); // NOVO

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE PLAYLIST =====");
            System.out.println("1 - Cadastrar musica no catalogo");
            System.out.println("2 - Exibir catalogo");
            System.out.println("3 - Criar nova playlist");
            System.out.println("4 - Exibir playlists");
            System.out.println("5 - Selecionar playlist");
            System.out.println("6 - Adicionar musica à playlist atual");
            System.out.println("7 - Exibir playlist atual");
            System.out.println("8 - Adicionar música à fila de reprodução");
            System.out.println("9 - Exibir fila de reprodução");
            System.out.println("10 - Reproduzir próxima música");
            System.out.println("11 - Exibir histórico");
            System.out.println("12 - Voltar reprodução");
            System.out.println("13 - Remover musica da playlist atual");
            System.out.println("14 - Ordenar a playlist atual");
            System.out.println("15 - Buscar música na árvore por ID"); // NOVO
            System.out.println("16 - Exibir árvore de músicas em ordem"); // NOVO
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Digite o ID da musica: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o título da musica: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o artista: ");
                    String artista = scanner.nextLine();
                    System.out.print("Digite a duração (double, ex: 1,45): ");
                    double duracao = scanner.nextDouble();
                    scanner.nextLine();

                    Musica novaMusica = new Musica(id, titulo, artista, duracao);
                    sistema.cadastrarMusicaCatalogo(novaMusica);
                    arvore.inserir(novaMusica); // NOVO
                    System.out.println("Musica cadastrada com sucesso.");
                    break;

                case 2:
                    System.out.println("\n===== CATALOGO =====");
                    sistema.exibirCatalogo();
                    break;

                case 3:
                    System.out.print("Digite o nome da nova playlist: ");
                    String nomePlaylist = scanner.nextLine();
                    sistema.criarPlaylist(nomePlaylist);
                    break;

                case 4:
                    System.out.println("\n===== PLAYLISTS =====");
                    sistema.exibirPlaylists();
                    break;

                case 5:
                    System.out.println("\n===== PLAYLISTS DISPONIVEIS =====");
                    sistema.exibirPlaylists();
                    System.out.print("Digite o Indice da playlist: ");
                    int indice = scanner.nextInt();
                    scanner.nextLine();
                    sistema.selecionarPlaylist(indice);
                    break;

                case 6:
                    System.out.println("\n===== CATALOGO =====");
                    sistema.exibirCatalogo();
                    System.out.print("Digite o ID da musica que deseja adicionar: ");
                    int idMusica = scanner.nextInt();
                    scanner.nextLine();
                    sistema.adicionarMusicaPlaylistAtual(idMusica);
                    break;

                case 7:
                    System.out.println("\n===== PLAYLIST ATUAL =====");
                    sistema.exibirPlaylistAtual();
                    break;

                case 8:
                    System.out.println("\n===== CATÁLOGO =====");
                    sistema.exibirCatalogo();
                    System.out.print("Digite o ID da música que deseja adicionar à fila: ");
                    int idFila = scanner.nextInt();
                    scanner.nextLine();
                    sistema.adicionarMusicaFila(idFila);
                    break;

                case 9:
                    System.out.println("\n===== FILA DE REPRODUÇÃO =====");
                    sistema.exibirFila();
                    break;

                case 10:
                    System.out.println("\n===== REPRODUÇÃO =====");
                    sistema.reproduzirProxima();
                    break;

                case 11:
                    System.out.println("\n===== HISTORICO =====");
                    sistema.exibirHistorico();
                    break;

                case 12:
                    System.out.println("\n===== VOLTAR REPRODUÇÃO =====");
                    sistema.voltarReproducao();
                    break;

                case 13:
                    System.out.println("\n===== PLAYLIST ATUAL =====");
                    sistema.exibirPlaylistAtual();
                    System.out.print("Digite o ID da música que deseja remover: ");
                    int idRemover = scanner.nextInt();
                    scanner.nextLine();
                    sistema.removerMusicaPlaylistAtual(idRemover);
                    break;

                case 14:
                    System.out.println("\n===== ORDENAR PLAYLIST =====");
                    if (sistema.getPlaylistAtual() == null) {
                        System.out.println("Nenhuma playlist selecionada.");
                        break;
                    }
                    System.out.println("1 - Ordenar por ID");
                    System.out.println("2 - Ordenar por Título");
                    System.out.println("3 - Ordenar por Duração");
                    System.out.print("Escolha uma opção de ordenação: ");
                    int criterio = scanner.nextInt();
                    scanner.nextLine();
                    switch (criterio) {
                        case 1:
                            sistema.ordenarPlaylistAtualPorId();
                            break;
                        case 2:
                            sistema.ordenarPlaylistAtualPorTitulo();
                            break;
                        case 3:
                            sistema.ordenarPlaylistAtualPorDuracao();
                            break;
                        default:
                            System.out.println("Critério inválido.");
                    }
                    break;

                // NOVO
                case 15:
                    System.out.print("Digite o ID da música que deseja buscar: ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();
                    Musica encontrada = arvore.buscar(idBusca);
                    if (encontrada != null) {
                        System.out.println("Música encontrada:");
                        encontrada.exibir();
                    } else {
                        System.out.println("Música não encontrada na árvore.");
                    }
                    break;

                // NOVO
                case 16:
                    arvore.imprimirEmOrdem();
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}