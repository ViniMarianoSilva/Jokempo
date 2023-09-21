package algoritmoEProgramacaoII.Projeto;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.JOptionPane.*;

public class Jokenpo {
    public static void main(String[] args) {
        // Variavél para conseguir pegar um número aleatório para a escolha da máquina
        Random random = new Random();

        // Declaração das variaveis:
        int usuEscolha = 0, maqEscolha = 0, gamUsu = 0, gamMaq = 0, setUsu = 0, setMaq = 0, modJgo;
        int usuCon = 0;
        int seqBat = 0;
        String nomUsu = "", nomMaq = "Máquina";

        // Array List para registrar as batalhas;
        ArrayList<String> logs = new ArrayList<String>();

        // Array de escolhas
        String[] escolhas = new String[3];
        escolhas[0] = "1- Pedra";
        escolhas[1] = "2- Papel";
        escolhas[2] = "3- Tesoura";

        // Array de dificuldade
        String[] dificuldades = new String[3];
        dificuldades[0] = "1- Fácil";
        dificuldades[1] = "2- Normal";
        dificuldades[2] = "3- Difícil";
        //Inicio de visualização do usuário
        showMessageDialog(null, "Seja bem vindo(a) ao Jokenpo Challenge!");
        showMessageDialog(null, "Aqui no Jokenpo Challenge as instruções são simples:\n" +
                "1- O jogo é composto por batalhas, onde você pode escolher entre pedra, papel ou tesoura para sua jogada!\n" +
                "2- Ao ganhar 3 batalhas, você ganhará 1 set da partida!\n" +
                "3- Para ganhar a partida você deverá ganhar 3 sets!");
        showMessageDialog(null, "No Jokenpo Challenge você disputa contra seu maior inimigo, a máquina.\nE para este desafio você pode escolher entre três dificuldades:\n" +
                "1- Fácil\n" +
                "2- Normal\n" +
                "3- Dificil");
        showMessageDialog(null, "Ao fim da partida, você poderá ver todos os registros da batalha caso deseje.");
        nomUsu = showInputDialog("Para começar o desafio, qual seu nome? ");

        // Variavel para ver se quer mudar o nome do adversário ou não
        int maqSN;
        maqSN = showConfirmDialog(null, "Você gostaria de dar um nome a seu adversário?", "Nome Maquina", YES_NO_OPTION);

        // Se dizer que sim, muda o nome, se não, segue como máquina.
        if (maqSN == YES_OPTION) {
            nomMaq = JOptionPane.showInputDialog("Informe o nome de seu adversário: ");
        }

        // Define modo de jogo
        modJgo = getModJgo();

        // Se não digitar um válido, entra em looping
        while (modJgo != 1 && modJgo != 2 && modJgo != 3) {
            showMessageDialog(null, "Você informou um número de modo inexistente, tente novamente!");
            modJgo = getModJgo();
        }

        // Enquanto não tiverem 3 sets (necessário para vencer a partida)
        while (setMaq < 3 && setUsu < 3) {

            // Se ganha três batalhas (games), ganha um set e reseta as batalhas, tanto para o usuário, tanto para a máquina
            if (gamMaq == 3) {
                setMaq++;
                gamMaq = 0;
                gamUsu = 0;
                showMessageDialog(null, nomMaq + " ganhou o seu " + setMaq + "ª set na partida");
            } else if (gamUsu == 3) {
                setUsu++;
                gamMaq = 0;
                gamUsu = 0;
                showMessageDialog(null, nomUsu + " ganhou o seu " + setUsu + "ª set na partida");
            }

            // Quando é para acabar o jogo ( 3 sets vencidos), sai do Looping, tive que forçar com o "break", pq não estava saindo de jeito nenhum
            if(setMaq == 1 || setUsu == 1){
                break;
            }

            // Pega a escolha do usuário
            usuEscolha = getUsuEscolha();

            // Se não for uma das válidas, entra em looping
            while ((usuEscolha != 1 && usuEscolha != 2 && usuEscolha != 3)) {
                showMessageDialog(null, "Informe novamente sua jogada:");
                usuEscolha = getUsuEscolha();
            }

            // Pede a confirmação da escolha, se foi mesmo o que o usuário pediu
            usuCon = getConfimacao(escolhas[usuEscolha - 1]);

            // Se não for, dá a escolha do usuário escolher de novo
            if (usuCon == NO_OPTION) {
                usuEscolha = getUsuEscolha();
            }

            // Sempre aumenta para saber a sequência da batalha
            seqBat++;

            // Mostra a jogada do usuário
            showMessageDialog(null, "Sua jogada na " + seqBat + "ª batalha foi " + escolhas[usuEscolha - 1]);

            // Pega a escolha da maquina, que depende do modo de jogo escolhido
            maqEscolha = JogadaMaquina(random, usuEscolha, modJgo);

            // Verifica as vitórias do usuário partindo de que:
            // Pedra (1) ganha da tesoura (3), Tesoura(3) ganha do Papel(2) e Papel(2) ganha da pedra(1)
            if ((usuEscolha == 1 && maqEscolha == 3) || (usuEscolha == 2 && maqEscolha == 1) || (usuEscolha == 3 && maqEscolha == 2)) {
                gamUsu++;

                // Para mostrar as mensagem para o usuário de como foi a batalha foi criado uma função,
                // passa todos esses paramêtros de forma "autômatica".
                // Na função também grava os logs da batalha
                mensagemLogJogadorVenceu(usuEscolha, maqEscolha, gamUsu, gamMaq, setUsu, setMaq, seqBat, nomUsu, nomMaq, escolhas, logs);
            }

            // Verifica as vitórias da máquina partindo de que:
            // Pedra (1) ganha da tesoura (3), Tesoura(3) ganha do Papel(2) e Papel(2) ganha da pedra(1)
            else if ((maqEscolha == 1 && usuEscolha == 3) || (maqEscolha == 2 && usuEscolha == 1) || (maqEscolha == 3 && usuEscolha == 2)) {
                gamMaq++;

                // Para mostrar as mensagem para o usuário de como foi a batalha foi criado uma função,
                // passa todos esses paramêtros de forma "autômatica".
                // Na função também grava os logs da batalha
                mensagemLogMaqGanhou(usuEscolha, maqEscolha, gamUsu, gamMaq, setUsu, setMaq, seqBat, nomUsu, nomMaq, escolhas, logs);
            }
            // Verifica os empates, se ambas as escolhas forem iguais, empata.
            else if (maqEscolha == usuEscolha) {

                // Para mostrar as mensagem para o usuário de como foi a batalha foi criado uma função,
                // passa todos esses paramêtros de forma "autômatica".
                // Na função também grava os logs da batalha
                mensagemLogEmpate(usuEscolha, gamUsu, gamMaq, setUsu, setMaq, seqBat, nomUsu, nomMaq, escolhas, logs);
            }
        }

        // Saindo do looping, mostra as mensagem conforme quem venceu
        if (setMaq == 1) {
            JOptionPane.showMessageDialog(null, "Infelizemnte " + nomMaq + ", foi quem ganhou a partida.");
        } else {
            showMessageDialog(null, "PARABÉNS " + nomUsu + ", você ganhou a partida!");
        }

        // Pergunta se quer ver os logs, através da variavel Log Sim ou Não
        int logSN;
        logSN = showConfirmDialog(null, "Você deseja ver o que aconteceu durante a partida?", "Ver logs", YES_NO_OPTION);
        if (logSN == YES_OPTION){

            // Esse for é basicamente um for normal
            // for (int i = 0, i < length (tamanho); I ++)
            // Só tá escrito de uma forma mais bonita que o chat mandou
            for (String log : logs) {
                JOptionPane.showMessageDialog(null, log);
            }
        }
    }

    // Gravar no Array do Log como foi a batalha foi empatada
    // Além de mostrar a mensagem ao usuário que foi um empate
    private static void mensagemLogEmpate(int usuEscolha, int gamUsu, int gamMaq, int setUsu, int setMaq, int seqBat, String nomUsu, String nomMaq, String[] escolhas, ArrayList<String> logs) {

        logs.add("Batalha: " + seqBat + "terminou empatada" + "\nAmbos utilizaram " + escolhas[usuEscolha - 1] +
                "\n O resultado do set está: " + nomUsu + " " + gamUsu + " X " + gamMaq + " " + nomMaq +
                "\n O resultado da partida está " + nomUsu + " " + setUsu + " X " + setMaq + " " + nomMaq + "(Em sets da Partida)");
        showMessageDialog(null, "Esta batalha terminou empatada !\n" +
                "O placar do Set está  " + nomUsu + " " + gamUsu + " X " + gamMaq + " " + nomMaq + " (Em batalhas no Set)\n" +
                "O placar da Partida está " + nomUsu + " " + setUsu + " X " + setMaq + " " + nomMaq + "(Em sets da Partida)");
    }

    // Gravar no Array do Log como foi a batalha vencida pela máquina
    // Além de mostrar a mensagem de que a máquina venceu esta batalha
    private static void mensagemLogMaqGanhou(int usuEscolha, int maqEscolha, int gamUsu, int gamMaq, int setUsu, int setMaq, int seqBat, String nomUsu, String nomMaq, String[] escolhas, ArrayList<String> logs) {
        logs.add("Batalha: " + seqBat + "teve como vencedor " + nomMaq + "\n Usando " + escolhas[maqEscolha - 1] +
                " contra a escolha de " + escolhas[usuEscolha - 1] + " de " + nomUsu +
                "\n O resultado do set está: " + nomUsu + " " + gamUsu + " X " + gamMaq + " " + nomMaq +
                "\n O resultado da partida está " + nomUsu + " " + setUsu + " X " + setMaq + " " + nomMaq + "(Em sets da Partida)");
        showMessageDialog(null, "Esta batalha foi vencida por " + nomMaq + " !!\n" +
                "O placar do Set está  " + nomUsu + " " + gamUsu + " X " + gamMaq + " " + nomMaq + " (Em batalhas no Set)\n" +
                "O placar da Partida está " + nomUsu + " " + setUsu + " X " + setMaq + " " + nomMaq + "(Em sets da Partida)");
    }

    // Gravar no Array do Log como foi a batalha vencida pelo jogador
    // Além de mostrar a mensagem de que ele venceu esta batalha
    private static void mensagemLogJogadorVenceu(int usuEscolha, int maqEscolha, int gamUsu, int gamMaq, int setUsu, int setMaq, int seqBat, String nomUsu, String nomMaq, String[] escolhas, List<String> logs) {
        logs.add("Batalha: " + seqBat + "teve como vencedor " + nomUsu + "\n Usando " + escolhas[usuEscolha - 1] +
                " contra a escolha de " + escolhas[maqEscolha - 1] + " de " + nomMaq + "\n O resultado do set está: " + nomUsu + " " + gamUsu + " X " + gamMaq + " " + nomMaq +
                "\n O resultado da partida está " + nomUsu + " " + setUsu + " X " + setMaq + " " + nomMaq + "(Em sets da Partida)");
        showMessageDialog(null, "Esta batalha foi vencida por " + nomUsu + " !\n" +
                "O placar do Set está  " + nomUsu + " " + gamUsu + " X " + gamMaq + " " + nomMaq + " (Em batalhas no Set)\n" +
                "O placar da Partida está " + nomUsu + " " + setUsu + " X " + setMaq + " " + nomMaq + "(Em sets da Partida)\n");
    }

    // Realiza a jogada da máquina a partir desta função
    private static int JogadaMaquina(Random random, int usuEscolha, int modJgo) {
        int maqEscolha;

        // Vê o modo de jogo do entra na função "getPct" de seu modo
        // Get PCT seria a porcentagem de chance de cair em tal jogada, explico melhor dento da função
        if (modJgo == 1) {
            int numAlt = getPctMod1(random, usuEscolha);
            maqEscolha = numAlt;
        } else if (modJgo == 2) {
            int numAlt = getPctMod2(random, usuEscolha);
            maqEscolha = numAlt;
        } else {
            int
                    numAlt = getPctMod3(random, usuEscolha);
            maqEscolha = numAlt;
        }
        return maqEscolha;
    }


    // função que traz a chance das escolhas da máquina
    // Como aqui é o modo fácil, sempre traz 60 % para a vitória e 20% para derrota e empate
    // Aqui seria tipo, escolhi 1 - Pedra, se a máquina escolhar tesoura (3) que tem 60% de chance de cair, ele vai
    // ganhar, por isso modo fácil
    // Depois ele vai para a função getNumAlt que pega realmente o número escolhido pela máquina
    private static int getPctMod1(Random random, int usuEscolha) {
        int numAlt;
        if (usuEscolha == 1) {
            numAlt = getNumAlt(random, 20, 20, 60);
        } else if (usuEscolha == 2) {
            numAlt = getNumAlt(random, 60, 20, 20);
        } else {
            numAlt = getNumAlt(random, 60, 20, 20);
        }
        return numAlt;
    }

    // função que traz a chance das escolhas da máquina
    // Como aqui é o modo Normal, sempre traz 34 % para a vitória e 33% para derrota e empate
    // Aqui seria tipo, escolhi 1 - Pedra, se a máquina escolhar tesoura (3) que tem 34% de chance de cair, ele vai
    // ganhar, por isso modo Normal, onde equilibra as chances
    // Depois ele vai para a função getNumAlt que pega realmente o número escolhido pela máquina
    private static int getPctMod2(Random random, int usuEscolha) {
        int numAlt;
        if (usuEscolha == 1) {
            numAlt = getNumAlt(random, 33, 33, 34);
        } else if (usuEscolha == 2) {
            numAlt = getNumAlt(random, 34, 33, 33);
        } else {
            numAlt = getNumAlt(random, 33, 34, 33);
        }
        return numAlt;
    }

    // função que traz a chance das escolhas da máquina
    // Como aqui é o modo dificil, sempre traz 20 % para a vitória, 30% para empate e 50% para derrota
    // Aqui seria tipo, escolhi 1 - Pedra, se a máquina escolhar tesoura (3) que tem 20% de chance de cair, ele vai
    // ganhar, por isso modo Dificil
    // Depois ele vai para a função getNumAlt que pega realmente o número escolhido pela máquina
    private static int getPctMod3(Random random, int usuEscolha) {
        int numAlt;
        if (usuEscolha == 1) {
            numAlt = getNumAlt(random, 30, 50, 20);
        } else if (usuEscolha == 2) {
            numAlt = getNumAlt(random, 20, 30, 50);
        } else {
            numAlt = getNumAlt(random, 50, 20, 30);
        }
        return numAlt;
    }

    // Faz a confirmação da escolha do usuário
    private static int getConfimacao(String escolha) {
        return showConfirmDialog(null, "Você confima sua escolha de " + escolha + "?", "Confimação", YES_NO_OPTION);
    }

    // Pede a escolha do usuário e retorna para a variavel usuEscolha
    private static int getUsuEscolha() {
        return Integer.parseInt(showInputDialog("Informe sua jogada:\n" +
                "1- Pedra\n" +
                "2- Papel\n" +
                "3- Tesoura"));
    }

    // Pega o modo de jogo e retorna lá encima
    private static int getModJgo() {
        int modJgo;
        modJgo = Integer.parseInt(showInputDialog("Para começarmos o jogo, em que dificuldade você gostaria de jogar:\n" +
                "1- Fácil\n" +
                "2- Normal\n" +
                "3- Dificil"));
        return modJgo;
    }

    // Aqui escolhe qual vai ser a jogada da máquina, com base nas chances da função anterior
    // Dar um exemplo para ajudar, se o jogo estiver no modo fácil e o usuário escolher 1 - pedra
    // As chances vão ser 20 - 20 - 60, dando 100 números.
    // Caso o Aleatório.nextInt (que pega um número aletório entre esses informados) dê entre 1 e 20, ele cai no primeiro if,
    // que vai retornar a jogada da máquina como 1- Pedra, dando empate na batalha
    // Caso caia em 21 e 40 (Soma de chance 1 + chance 2) , cai no segundo if, a jogada da máquina será
    // 2 - Papel, dando a vitória para a máquina na rodada
    // Caso caia 41 e 100 (Soma de chance1 + chance2 + chance3), caindo no terceiro if,
    // a jogada da máquina será 3- Tesoura, dando a vitória para o usuário na rodada

    private static int getNumAlt(Random aleatorio, int chance1, int chance2, int chance3) {
        int numAlt = aleatorio.nextInt(chance1 + chance2 + chance3) + 1;
        if (numAlt <= chance1) {
            return 1;
        } else if (numAlt <= chance1 + chance2) {
            return 2;
        } else {
            return 3;
        }
    }

}
