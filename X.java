package algoritmoEProgramacaoII.Projeto;

import java.util.Random;

public class X {


    private static final Random random = new Random();
    private static final String[] jogadas = {"Pedra", "Papel", "Tesoura"};
    private static int[] pesos = {20, 50, 80}; // Pesos correspondentes a cada jogada

    public static String escolherJogadaMaquina(String modo) {
        int totalPesos = calcularTotalPesos(modo);

        int numeroAleatorio = random.nextInt(totalPesos) + 1;

        for (int i = 0; i < jogadas.length; i++) {
            if (numeroAleatorio <= pesos[i]) {
                return jogadas[i];
            }
        }

        return escolherJogadaAleatoria();
    }

    private static int calcularTotalPesos(String modo) {
        int totalPesos = 0;

        if (modo.equals("Fácil")) {
            totalPesos = pesos[0];
        } else if (modo.equals("Normal")) {
            totalPesos = pesos[0] + pesos[1];
        } else if (modo.equals("Difícil")) {
            totalPesos = pesos[0] + pesos[1] + pesos[2];
        }

        return totalPesos;
    }

    private static String escolherJogadaAleatoria() {
        int indiceAleatorio = random.nextInt(jogadas.length);
        return jogadas[indiceAleatorio];
    }

    public static void atualizarPesos(String jogadaUsuario) {
        if (jogadaUsuario.equals("Pedra")) {
            pesos[1] += 80;  // Aumentar peso de Papel
            pesos[2] -= 80;  // Diminuir peso de Tesoura
        } else if (jogadaUsuario.equals("Papel")) {
            pesos[2] += 80;  // Aumentar peso de Tesoura
            pesos[0] -= 80;  // Diminuir peso de Pedra
        } else if (jogadaUsuario.equals("Tesoura")) {
            pesos[0] += 80;  // Aumentar peso de Pedra
            pesos[1] -= 80;  // Diminuir peso de Papel
        }
    }

    public static void main(String[] args) {
        String modo = "Difícil";
        String jogadaUsuario = "Pedra";

        String jogadaMaquina = escolherJogadaMaquina(modo);
        System.out.println("Jogada da Máquina: " + jogadaMaquina);

        atualizarPesos(jogadaUsuario);

        jogadaMaquina = escolherJogadaMaquina(modo);
        System.out.println("Nova Jogada da Máquina: " + jogadaMaquina);
    }
}


