package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task3 {
    public static void run() {
        List<Player> players = List.of(new Player(List.of(1, 2, 3)), new Player(List.of(4, 5, 6)));

        int numberOfThrows = 500;
        List<Integer> throwsList = generateThrows(numberOfThrows);

        System.out.println(throwsList);
        play(players, throwsList);
    }

    private static List<Integer> generateThrows(int numOfThrows) {
        Random random = new Random();
        List<Integer> throwsList = new ArrayList<>();
        for (int i = 0; i < numOfThrows; i++) {
            throwsList.add(random.nextInt(6) + 1);
        }
        return throwsList;
    }

    public static void play(List<Player> players, List<Integer> throwsList) {
        boolean isPlayer1 = true;
        int tmpScore = 0;
        int player1Probability = 0;
        int player2Probability = 0;
        double drawProbability = 0;
        for (int i = 0; i < throwsList.size() - 2; i++) {
            List<Integer> currentThrow = new ArrayList<>(throwsList.subList(i, i + 3));
            for (Player player : players) {
                if (player.getWait() > 0) {
                    player.setWait(player.getWait() - 1);
                }
                if (areSequencesEqual(player.getSequence(), currentThrow) && player.getWait() == 0) {
                    player.setScore(player.getScore() + 1);
                    player.setWait(2);
                    System.out.printf("Игрок %d получил очко!", (isPlayer1 ? 1 : 2));
                }

                if (isPlayer1) {
                    tmpScore = player.getScore();
                } else {
                    if (tmpScore > player.getScore()) {
                        player1Probability++;
                    } else if (tmpScore < player.getScore()) {
                        player2Probability++;
                    } else {
                        drawProbability++;
                    }
                }
                isPlayer1 = !isPlayer1;
            }
            System.out.println("Ход №" + (i + 1));
            System.out.println("Шанс Игрока 1: " + (double) player1Probability / throwsList.size());
            System.out.println("Шанс Игрока 2: " + (double) player2Probability / throwsList.size());
            System.out.println("Шанс ничьи: " + drawProbability / throwsList.size());
            System.out.println();

        }

        for (Player player : players) {
            System.out.printf("Игрок %d: Количество очков: %d%n", players.indexOf(player) + 1, player.getScore());
        }
    }

    private static boolean areSequencesEqual(List<Integer> playerSequence, List<Integer> throwSequence) {
        return playerSequence.equals(throwSequence);
    }
}

class Player {
    private List<Integer> sequence;
    private Integer score = 0;
    private Integer wait = 0;

    private double winProbability = 0;

    public Player(List<Integer> sequence) {
        this.sequence = sequence;
    }

    public List<Integer> getSequence() {
        return sequence;
    }

    public void setSequence(List<Integer> sequence) {
        this.sequence = sequence;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getWait() {
        return wait;
    }

    public void setWait(Integer wait) {
        this.wait = wait;
    }

    public Double getWinProbability() {
        return winProbability;
    }

    public void setWinProbability(double winProbability) {
        this.winProbability = winProbability;
    }
}
