
import java.util.Arrays;
import java.util.Random;


class Board {
    private int[] cards;
    private int N, cards_Size;
    private String[] table;
    private int[] deleted; //deleted cards
    private int[] allcards; //all starting cards
    private int [][]Seen;
    private int[]printer;
    int seed =6;
    Random rnd = new Random();     //seeded for tests


    Board(int x) {
        N = x * 2;
        deleted = new int[N];
        allcards = new int[N];
        cards = new int[N];
        table = new String[N];
        Seen = new int[N][N];
        for (int i = 0; i < x; i++) {
            int d = rnd.nextInt(15);
            cards[i] = d;     //every d twice in cards
            cards[x + i] = d;
        }

        Arrays.sort(cards);
        for (int i = cards.length - 1; i >= 0; i--) {
            int randomPosition = rnd.nextInt(cards.length);
            int temp = cards[i];
            cards[i] = cards[randomPosition];
            cards[randomPosition] = temp;
        }
        cards_Size = cards.length;      // keeps the size of the array
        for (int i = 0; i < N; i++) {
            allcards[i] = cards[i];     //filling all the starting cards
        }
        for (int i=0; i<N; i++){
            deleted[i] =-1;               //so as no possibility to have same values as allcards before deleting
        }
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                Seen[i][j] = -1;
            }
        }

    }

    void print() {
        printer=new int[cards.length];
        for (int i=0; i<cards.length; i++){         //copying existed cards in printer
            printer[i] =cards[i];
        }
        Arrays.sort(printer);
        table = getTable();
        for (int i = 0; i < N; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print(table[i] + "   ");
        }
        System.out.println(" ");
        System.out.println("\nThe numbers on table are");

        for (int i=0; i<cards.length; i++){         //using printer so as remaining cards are sorted and not showing their position
            System.out.print(printer[i] + " ");
        }

    }

    void flash(int first, int second) {
        table = getTable();
        if (first > N || first < 0 || second > N || second < 0 ||allcards[first] == deleted[first]||allcards[second] == deleted[second]) {
            System.out.println("\nNot a valid position");
            return ;
        }

        String a = "";
        String b = "";

        for (int i = 0; i < N; i++) {
            if (i == first&&allcards[first]!=deleted[first]) {
                a = String.valueOf(allcards[i]);
                table[i] = a;
            } else if (i == second && allcards[second]!=deleted[second]) {
                b = String.valueOf(allcards[i]);
                table[i] = b;
            }

        }

        for (int i = 0; i < N; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print(table[i] + "   ");
        }

        delay(3);
        System.out.println("\n");

        for (int i=0; i< N; i++)
        {
            if (table[i].equals(a) || table[i].equals(b)) {     //hiding again the flashed positions
                table[i] = "*";
            }
        }
        for (int i = 0; i < N; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();
        for (int i=0; i<N; i++)
        {
            System.out.print(table[i] + "   ");
        }
    }

    void delay(int sec) {
        try {
            Thread.currentThread();
            Thread.sleep(1000 * sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean openPositions(int first, int second) {

        int[] anotherArray = new int[N];

        if (first > N || first < 0 || second > N || second < 0 ||allcards[first] == deleted[first]||allcards[second] == deleted[second]) {
            System.out.println("\nNot a valid position");
            return false;
        }
        if (allcards[first] == allcards[second] && allcards[first] !=deleted[first] && allcards[second] != deleted[second]) {
            System.out.println("\nWell done, pair found (a=" + allcards[first] + " b=" + allcards[second] + ")");

            for (int i = 0, k = 0; i < N; i++) {
                if (i != first && i != second) {
                    anotherArray[k++] =allcards[i];
                } else {
                    cards_Size--;
                    deleted[first] = allcards[first];           //storing the deleted value in same position as the allcards to compare anytime
                    deleted[second] = allcards[second];
                }
            }
            cards = new int[cards_Size];
            for (int i = 0; i < cards.length; i++) {
                cards[i] = anotherArray[i];
            }
            for (int i=0; i<N; i++){
                if(Seen[i][first] ==-1){
                    Seen[i][first] =allcards[first];
                    break;
                }
            }

            for (int i=0; i<N; i++){
                if(Seen[i][second] ==-1){
                    Seen[i][second] =allcards[second];
                    break;
                }
            }
            print();
            return true;
        }
        System.out.println("\nTry more the next time");
        flash(first, second);
        for (int i=0; i<N; i++){
            if(Seen[i][first] ==-1){
                Seen[i][first] =allcards[first];
                break;
            }
        }
        for (int i=0; i<N; i++){
            if(Seen[i][second] ==-1){
                Seen[i][second] =allcards[second];
                break;
            }
        }
        return false;
    }


    int getRandomPosition() {

       int x = rnd.nextInt(N);
        while(!containsCard(x)){
            x = rnd.nextInt(N);
        }
        return x;
    }

    int getRandomPosition(int position) {
        int random_Position=getRandomPosition();

        while (true) {
            random_Position = getRandomPosition();

            if (random_Position != position)
                break;
        }
        return random_Position;
    }

    boolean containsCard(int position) {
        if (allcards[position] != deleted[position]) {
            return true;
        }
        return false;
    }

    int getCard(int position) {
        for (int i = 0; i < N; i++) {
            if (i == position) {
                return allcards[i];
            }
        }
        return 0;
    }

    boolean allPairsFound() {
        getTable();
        int end=0;
        for (int i=0; i<N; i++){
            if (table[i] == "*"){
                end++;
            }
        }
        if (end==0) {
            return true;
        }
        return false;
    }

    int [] getBoard(){      //all get helps in ComputerPlayer
        return cards;
    }
    int [] getAllcards(){
        return allcards;
    }
    int [][] getSeen(){
        return Seen;
    }
    int [] getDeleted(){
        return deleted;
    }
    String []  getTable(){              //this get helps not to create always a table when needed
        for (int i = 0; i < N; i++) {
            if (allcards[i] == deleted[i]) {
                table[i] = " ";
            } else{
                table[i] = "*";
            }
        }
        return table;
    }
    private void printTable(){
        System.out.println(" ");
        for (int i = 0; i < N; i++){
            System.out.print(i + "   ");
        }
        System.out.println(" ");
        for (int i = 0; i < N; i++){
            System.out.print(table[i] + "   ");
        }
    }

}
