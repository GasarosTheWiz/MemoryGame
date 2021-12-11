import java.util.Scanner;
public class MemoryGame {
    public static void main(String[] args) {
        Board x = new Board(5);
        HumanPlayer one = null;
        HumanPlayer two = null;
        ComputerPlayer cp = null;
        Scanner answer = new Scanner(System.in);
        System.out.println("do you prefer a game Human player vs Computer player or  2 Human players? Answer me one or two");
        String p = answer.next();
        while (!p.equals("one") && !p.equals("two")) {
            System.out.println("Choose one of the two options please, one or two");
            p = answer.next();
        }
        if (p.equals("one")) {
            cp = new ComputerPlayer("ComputerPlayer", 5);
            one = new HumanPlayer("1st player");
        } else if (p.equals("two")) {
            one = new HumanPlayer("1st player");
            two = new HumanPlayer("2nd player");
        }
        while (!x.allPairsFound()) {
            if (p.equals("one")) {
                one.play(x);
                if (x.allPairsFound()) {             //so as to break if all pairs found before asking computer player to play
                    break;
                }
                cp.play(x);
                System.out.println("\n" +one.toString()+" " +one.playerPoints()+ " "+"Point(s)");
                System.out.println("\n" +cp.toString()+" " +cp.getPoints()+ " "+"Point(s)");
            } else if (p.equals("two")) {
                System.out.println("\n1st player its your turn");
                one.play(x);
                if (x.allPairsFound()) {
                    break;
                }
                System.out.println("\n2nd player its your turn");
                two.play(x);
                System.out.println("\n" +one.toString()+" " +one.playerPoints()+ " "+"Point(s)");
                System.out.println("\n" +two.toString()+" " +two.playerPoints()+ " "+"Point(s)");
            }
        }
        if (p.equals("one")) {
            if (one.playerPoints() > cp.getPoints()) {
                System.out.println("\nThe winner is:" + " " + one.toString());
            } else if (one.playerPoints() == cp.getPoints()) {
                System.out.println("\nThats a draw");
            } else {
                System.out.println("\nThe winner is:" + " " + cp.toString());
            }
        } else {
            if (one.playerPoints() > two.playerPoints()) {
                System.out.println("\nThe winner is:" + " " + one.toString());
            } else if (one.playerPoints() == two.playerPoints()) {
                System.out.println("\nThats a draw");
            } else {
                System.out.println("\nThe winner is:" + " " + two.toString());
            }
        }
    }
}








