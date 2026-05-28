import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // instancia o sistema principal(hub que coordenada tudo)
        SistemaPlaylist sistema = new SistemaPlaylist();
        int opcao; // loop do while (tipo um boolean)

        // menu interativo
        do {

            // exibe o menu
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
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {

                // CADASTRAR MUSICA
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

                    // cria objeto musica com os dados informados
                    Musica novaMusica = new Musica(id, titulo, artista, duracao);

                    // cadastra a nova musica dentro do catalogo (posteriormente pode-se adicionar as musicas a uma playlist pelo o ID das mesmas)
                    sistema.cadastrarMusicaCatalogo(novaMusica);

                    System.out.println("Musica cadastrada com sucesso.");
                    break;


                // EXIBIR CATALOGO
                case 2:

                    System.out.println("\n===== CATALOGO =====");
                    sistema.exibirCatalogo();
                    break;


                // CRIAR PLAYLIST
                case 3:

                    System.out.print("Digite o nome da nova playlist: ");
                    String nomePlaylist = scanner.nextLine();

                    sistema.criarPlaylist(nomePlaylist);
                    break;


                // EXIBIR PLAYLISTS
                case 4:

                    System.out.println("\n===== PLAYLISTS =====");
                    sistema.exibirPlaylists();
                    break;


                // SELECIONAR PLAYLIST
                case 5:

                    System.out.println("\n===== PLAYLISTS DISPONIVEIS =====");
                    sistema.exibirPlaylists();

                    System.out.print("Digite o Indice da playlist: ");
                    int indice = scanner.nextInt();
                    scanner.nextLine();

                    sistema.selecionarPlaylist(indice);
                    break;


                // ADICIONAR MUSICA À PLAYLIST ATUAL
                case 6:

                    System.out.println("\n===== CATALOGO =====");
                    sistema.exibirCatalogo();

                    System.out.print("Digite o ID da musica que deseja adicionar: ");
                    int idMusica = scanner.nextInt();
                    scanner.nextLine();

                    sistema.adicionarMusicaPlaylistAtual(idMusica);
                    break;


                // EXIBIR PLAYLIST ATUAL
                case 7:

                    System.out.println("\n===== PLAYLIST ATUAL =====");
                    sistema.exibirPlaylistAtual();
                    break;

                // ADICIONAR MÚSICA À FILA DE REPRODUÇÃO
                case 8:

                    System.out.println("\n===== CATÁLOGO =====");
                    sistema.exibirCatalogo();

                    System.out.print("Digite o ID da música que deseja adicionar à fila: ");
                    int idFila = scanner.nextInt();
                    scanner.nextLine();

                    sistema.adicionarMusicaFila(idFila);
                    break;


                // EXIBIR FILA
                case 9:

                    System.out.println("\n===== FILA DE REPRODUÇÃO =====");
                    sistema.exibirFila();
                    break;


                // REPRODUZIR PRÓXIMA
                case 10:

                    System.out.println("\n===== REPRODUÇÃO =====");
                    sistema.reproduzirProxima();
                    break;

                // EXIBIR HISTORICO
                case 11:

                    System.out.println("\n===== HISTORICO =====");
                    sistema.exibirHistorico();
                    break;

                case 12:

                     System.out.println("\n===== VOLTAR REPRODUÇÃO =====");
                     sistema.voltarReproducao();
                     break;
                    
                // remove uma musica da playlist atual
                case 13:

                    System.out.println("\n===== PLAYLIST ATUAL =====");
                    sistema.exibirPlaylistAtual();

                    System.out.print("Digite o ID da música que deseja remover: ");
                    int idRemover = scanner.nextInt();
                    scanner.nextLine();

                    sistema.removerMusicaPlaylistAtual(idRemover);
                    break;
                
                // ORDENAR PLAYLIST ATUAL
                case 14:
               // Verifica se existe playlist selecionada
                    System.out.println("\n===== ORDENAR PLAYLIST =====");

                    if (sistema.getPlaylistAtual() == null) {
                        System.out.println("Nenhuma playlist selecionada.");
                        break;
                    }

                    // Submenu de ordenação
                    System.out.println("1 - Ordenar por ID");
                    System.out.println("2 - Ordenar por Título");
                    System.out.println("3 - Ordenar por Duração");
                    System.out.print("Escolha uma opção de ordenação: ");

                    int criterio = scanner.nextInt();
                    scanner.nextLine();

                    switch (criterio) {

                        // ORDENAR POR ID
                        case 1:
                            sistema.ordenarPlaylistAtualPorId();
                            break;

                        // ORDENAR POR TÍTULO
                        case 2:
                            sistema.ordenarPlaylistAtualPorTitulo();
                            break;

                        // ORDENAR POR DURAÇÃO
                        case 3:
                            sistema.ordenarPlaylistAtualPorDuracao();
                            break;

                        // OPÇÃO INVÁLIDA
                        default:
                            System.out.println("Critério inválido.");
                    }

                    break;
                    // SAIR
                case 0:

                    System.out.println("Encerrando sistema...");

                default:

                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        // fecha scanner
        scanner.close();
    }
}