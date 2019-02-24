import java.util.Scanner;

class Player
{
    public Player()
    {

    }

    public Board move (Board board)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("MOVE: (eg'x y', 0 <= x/y <= 2)\n");
        String move = scan.nextLine();
        scan = new Scanner(move);
        int x = Integer.parseInt(scan.next());
        int y = Integer.parseInt(scan.next());
        while (board.map[y][x] != ' ')
        {
            scan = new Scanner(System.in);
            System.out.print("Unavailable move.\nMOVE: (eg'x y', 0 <= x/y <= 2)\n");
            move = scan.nextLine();
            scan = new Scanner(move);
            x = Integer.parseInt(scan.next());
            y = Integer.parseInt(scan.next());
        }

        board.addMove(x, y, 'X');
        //to be implimementededed
        return board;
    }
}