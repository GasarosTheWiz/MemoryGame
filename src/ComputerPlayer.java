

import java.util.Random;

class ComputerPlayer {
    private String username;
    private int N, points;


    ComputerPlayer(String username, int x) {
        this.username = username;
        N = x * 2;
    }

    void play(Board b) {
        int enter = 0;
        int[] cards = b.getBoard();
        int[][] Seen = b.getSeen();
        int[] deleted = b.getDeleted();
        int[] allcards = b.getAllcards();
        Random rnd = new Random();
        int firstpick = b.getRandomPosition(); //first random position from cards
        int secondpick = 0; //second position

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Seen[i][j] == allcards[j]) {     //bonus
                    for (int k = 0; k < N; k++) {
                        if (Seen[i][k] == allcards[j] && j != k && b.containsCard(j)&&b.containsCard(k)) {
                            firstpick = j;
                            secondpick = k;
                            enter++;
                            break;
                            }
                        }
                    }
                }
            }
        if( enter!=0) {
            System.out.println("\nfirst pick: " + firstpick + "\nsecond pick: " + secondpick);
            if (b.openPositions(firstpick, secondpick)) {
                points++;
            }
        }else{
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (Seen[i][j] == allcards[firstpick]) {
                        secondpick = j;
                    }
                }
            }
            while (!b.containsCard(secondpick) ) {
                secondpick = b.getRandomPosition(firstpick);
            }
            System.out.println("\nfirst pick: " + firstpick + "\nsecond pick: " + secondpick);

            if (b.openPositions(firstpick, secondpick)) {
                points++;
            }
        }
    }

    int getPoints() {
        return points;
    }

    public String toString() {
        return username;
    }

}