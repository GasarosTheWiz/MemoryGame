

import java.util.Scanner;
class HumanPlayer
{

    private String player;
    private int points = 0;

    HumanPlayer(String player)
    {
        this.player = player;
    }

    void play(Board p)
    {
        int allcards[] =p.getAllcards();
        int N=allcards.length;                  // needing N for checking first and second position
        Scanner position = new Scanner(System.in);

        System.out.print("Choose your first position: ");
        int first_position = position.nextInt();

        System.out.print("Choose your second position: ");
        int second_position = position.nextInt();

        while (first_position == second_position || !p.containsCard(first_position) || !p.containsCard(second_position)
                ||first_position<0 || second_position<0 || first_position>N ||second_position>N)
        {
            System.out.println("Please choose wisely your first position");
            first_position = position.nextInt();

            System.out.println("And now your second");
            second_position = position.nextInt();
        }


        if (p.openPositions(first_position, second_position)) {
            points++;
        }
    }
    int playerPoints()
    {
        return points;
    }

    public String toString()
    {
        return player;
    }
}