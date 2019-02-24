class TicAI
{
    public static void main (String [] args)
    {
        Board board = new Board();

        Player human = new Player();
        AI pc = new AI();

        board = human.move(board);
        board.print();
        board = pc.firstMove(board);
        board.print();
        
        Boolean cont = true;
        while (cont)
        {
            board = human.move(board);
            board.print();    
            switch (board.checkForWin())
            {
                case -1:
                    System.out.println("AI LOST. FUCK.");
                    return;
                case 0:
                    System.out.println("DRAW. COOL.");
                    return;
                case 2:
                    System.out.println("AI WINS. SICKO");
                    return;
            }    
            board = pc.move(board);
            board.print();  
            switch (board.checkForWin())
            {
                case -1:
                    System.out.println("AI LOST. FUCK.");
                    return;
                case 0:
                    System.out.println("DRAW. COOL.");
                    return;
                case 2:
                    System.out.println("AI WINS. SICKO");
                    return;
            }
        }
    }
}