import java.util.Random;
import java.util.Scanner;

public class Volei {

    static Scanner entrada = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        System.out.print("Digite o nome do jogador: ");
        String nome = entrada.nextLine();

        int habilidade = 55;
        int pontos = 0;
        int vitorias = 0;
        int derrotas = 0;
        int saldo = 600;
        int partidas = 0;
        int opcao;

        int[] historico = new int[5];
        int quantidade = 0;

        do {
            System.out.println("\n===== JOGO VIRTUAL DE VOLEI =====");
            System.out.println("1 - Visualizar jogador");
            System.out.println("2 - Treinar");
            System.out.println("3 - Jogar partida");
            System.out.println("4 - Consultar estatisticas");
            System.out.println("5 - Historico de partidas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = entrada.nextInt();

            switch (opcao) {

                case 1:
                    mostrarJogador(nome, habilidade, pontos,
                            vitorias, derrotas, saldo);
                    break;

                case 2:
                    if (saldo >= 200 && habilidade < 100) {

                        saldo -= 200;

                        int resultado = random.nextInt(10) + 1;

                        habilidade = treinar(habilidade, resultado);

                        System.out.println("Resultado do treino: " + resultado);
                        System.out.println("Habilidade: " + habilidade);

                    } else {
                        System.out.println("Nao e possivel treinar!");
                    }
                    break;

                case 3:
                    System.out.println("Escolha a estrategia:");
                    System.out.println("1 - Defensiva");
                    System.out.println("2 - Equilibrada");
                    System.out.println("3 - Ofensiva");

                    int estrategia = entrada.nextInt();
                    int bonus = 0;

                    if (estrategia == 1) {
                        bonus = 0;
                    } else if (estrategia == 2) {
                        bonus = 8;
                    } else if (estrategia == 3) {
                        bonus = 15;
                    } else {
                        System.out.println("Estrategia invalida!");
                        break;
                    }

                    int sorte = random.nextInt(11);

                    int desempenho = calcularDesempenho(
                            habilidade, bonus, sorte
                    );

                    int pontosPartida = calcularPontos(desempenho);
                    int premio = calcularPremio(desempenho);

                    pontos += pontosPartida;
                    saldo += premio;
                    partidas++;

                    if (desempenho >= 75) {
                        vitorias++;
                        System.out.println("Voce venceu a partida!");
                    } else {
                        derrotas++;
                        System.out.println("Voce perdeu a partida!");
                    }

                    if (quantidade < 5) {
                        historico[quantidade] = desempenho;
                        quantidade++;
                    } else {
                        for (int i = 0; i < 4; i++) {
                            historico[i] = historico[i + 1];
                        }
                        historico[4] = desempenho;
                    }

                    System.out.println("Desempenho: " + desempenho);
                    System.out.println("Pontos ganhos: " + pontosPartida);
                    System.out.println("Premio: R$ " + premio);
                    System.out.println("Sorte: " + sorte);

                    break;

                case 4:
                    System.out.println("Partidas: " + partidas);
                    System.out.println("Vitorias: " + vitorias);
                    System.out.println("Derrotas: " + derrotas);
                    System.out.println("Total de pontos: " + pontos);
                    System.out.println("Saldo: R$ " + saldo);
                    break;

                case 5:
                    System.out.println("===== HISTORICO =====");

                    if (quantidade == 0) {
                        System.out.println("Nenhuma partida realizada!");
                    } else {
                        for (int i = 0; i < quantidade; i++) {
                            System.out.println(
                                    "Partida " + (i + 1) + ": "
                                            + historico[i]
                            );
                        }
                    }
                    break;

                case 0:
                    System.out.println("Fim de jogo!");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

        entrada.close();
    }

    public static void mostrarJogador(
            String nome, int habilidade, int pontos,
            int vitorias, int derrotas, int saldo
    ) {
        System.out.println("\n===== JOGADOR =====");
        System.out.println("Nome: " + nome);
        System.out.println("Habilidade: " + habilidade);
        System.out.println("Pontos: " + pontos);
        System.out.println("Vitorias: " + vitorias);
        System.out.println("Derrotas: " + derrotas);
        System.out.println("Saldo: R$ " + saldo);
    }

    public static int treinar(int habilidade, int resultado) {

        if (resultado >= 5) {
            habilidade += 10;

            if (habilidade > 100) {
                habilidade = 100;
            }

            System.out.println("Treinamento realizado com sucesso!");
        } else {
            System.out.println("Treinamento sem sucesso!");
        }

        return habilidade;
    }

    public static int calcularDesempenho(
            int habilidade, int bonus, int sorte
    ) {
        return habilidade + bonus + sorte;
    }

    public static int calcularPontos(int desempenho) {

        if (desempenho >= 90) {
            return 30;
        } else if (desempenho >= 75) {
            return 20;
        } else if (desempenho >= 60) {
            return 10;
        } else {
            return 5;
        }
    }

    public static int calcularPremio(int desempenho) {

        if (desempenho >= 90) {
            return 1000;
        } else if (desempenho >= 75) {
            return 700;
        } else if (desempenho >= 60) {
            return 300;
        } else {
            return 100;
        }
    }
}

