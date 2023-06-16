
public class board {
    
    private final int no_of_rows;
    private final int no_of_coloumns;
    public pieces [][] game_board;
   
    public board(int rows,int cols){
        
        this.no_of_rows = rows;
        this.no_of_coloumns = cols;
        this.game_board = new pieces [rows][cols];
        this.reset();
        
       
    }
    
    public void reset(){
       int i,j;
       for(i = 0;i < no_of_rows;i++){
           for(j = 0;j < no_of_coloumns;j++){
               game_board[i][j] = pieces.emp;
           }
       }
    }
    public int get_no_of_rows(){
        return no_of_rows;
    }
    public int get_no_of_coloumns(){
        return no_of_coloumns;
    }
    public void printBoard(){
        int i,j;
        for(i = 0;i < no_of_rows;i++){
            
            for(j = 0;j < no_of_coloumns;j++){
                System.out.print(game_board[i][j] + " ");
            }
            
            System.out.println();
        }
    }
}
