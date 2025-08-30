import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static String[] quadro; // Tabuleiro de 9 posições em String
    static String turno; // Guarda quem é a vez "X" ou "O"

    static String checarGanhar(){
        for (int a = 0; a < 8; a++){
            String linha = null;

            // Verifica as 8 combinações de vitória
            switch (a) {
                case 0:
                    linha = quadro[0] + quadro[1] + quadro[2];
                    break;
                case 1:
                    linha = quadro[3] + quadro[4] + quadro[5];
                    break;
                case 2:
                    linha = quadro[6] + quadro[7] + quadro[8];
                    break;
                case 3:
                    linha = quadro[0] + quadro[3] + quadro[6];
                    break;
                case 4:
                    linha = quadro[1] + quadro[4] + quadro[7];
                    break;
                case 5:
                    linha = quadro[2] + quadro[5] + quadro[8];
                    break;
                case 6:
                    linha = quadro[0] + quadro[4] + quadro[8];
                    break;
                case 7:
                    linha = quadro[2] + quadro[4] + quadro[6];
                    break;
            }

            // Se alguma linha for "XXX" ou "OOO" ativa temos um ganhador
            if (linha.equals("XXX")){
                return "X";
            } else if (linha.equals("OOO")) {
                return "O";
            }
        }

        // Se não há vencedor, verifica se ainda tem jogadas
        for (int a = 0; a < 9; a++){
            if (Arrays.asList(quadro).contains(String.valueOf(a + 1))){
                break;
            } else if (a == 8) {
                return "empate"; // Se nenhuma tiver livre == empate
            }
        }
        // Se ainda não tiver um ganhador e ainda tiver jogadas informa o turno
        System.out.println(turno + " Turno, Insira um número de slot para colocar " + turno + "em: ");
        return null;
    }

    // Mostra o jogo da velha
    static void printQuadro(){
        System.out.println("|---|---|---|");
        System.out.println("| " + quadro[0] + " | " + quadro[1] + " | " + quadro[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + quadro[3] + " | " + quadro[4] + " | " + quadro[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + quadro[6] + " | " + quadro[7] + " | " + quadro[8] + " |");
        System.out.println("|---|---|---|");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        quadro = new String[9]; // Cria um array de 9 posições
        turno = "X"; // X sempre começa
        String ganhador = null;

        // Númera os Quadradinhos do jogo da velha (1 a 9)
        for (int a = 0; a < 9; a++){
            quadro[a] = String.valueOf(a + 1);
        }
        System.out.println("Bem Vindo a 3x3 Jogo da Velha");
        printQuadro();
        System.out.println("X Vai jogar primeiro. Digite um número de slot para colocar X:");

        while (ganhador == null){
            int numEntrada;

            try {
                numEntrada = scanner.nextInt(); // Recebe a jogada

                // Verifica se a entrada e de 0 a 9
                if (!(numEntrada > 0 && numEntrada <= 9)){
                    System.out.println("Entrada invalida, coloque outro número");
                    continue;
                }

                // Se a posição ainda não foi usada
                if (quadro[numEntrada - 1].equals(String.valueOf(numEntrada))){
                    quadro[numEntrada - 1] = turno; // Marca a jogada nos quadradinhos

                    turno = turno.equals("X") ? "O" : "X"; // Muda o turno
                    printQuadro();
                    ganhador = checarGanhar(); // Verifica se há ganhador
                }else {
                    System.out.println("Vaga já ocupada; digite novamente o número da vaga: ");
                }
            }catch (InputMismatchException e){
                System.out.println("Entrada inválida; digite novamente o número do slot: ");
                scanner.nextLine();
            }
        }


        // Mensagem final
        if (ganhador.equalsIgnoreCase("Empate")){
            System.out.println("Obrigado por Jogar");
        } else {
            System.out.println("Parabéns! "+ganhador+" Obrigado por jogar.");
        }
        scanner.close();


    }
}
