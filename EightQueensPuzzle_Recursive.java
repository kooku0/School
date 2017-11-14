public class EightQueensPuzzle {

    static char[][] board = new char[8][8];
    static int caseCount = 0;


    public static void main(String[] args) {
        count(0);
        System.out.println("경우의수: "+ String.valueOf(caseCount));

    }


    static void count(int row) {
        
        for(int i=0; i<8; i++) {
           
            board[row][i] = 'Q';
     
            if(Check(row,i)) {
		
                if(row == 7) {
		
                    caseCount += 1;
		
                    board[row][i] = ' ';

                    break;
                }
                count(row+1);
                printBoard(board);
                System.out.println(" ");

            }
         
            board[row][i] = ' ';

        }
    }
	public static void printBoard(char[][] board){
		for(int a=0;a<8;a++) {
			System.out.print("|");
			for(int b=0;b<8;b++) {			
				System.out.print(board[a][b]+"|");				
			}
			System.out.println();
		}
	}

    public static boolean Check(int a,int b) {

        for(int i=0; i< 8; i++) {

            if(i == a)
                break;
            if(board[i][b] == 'Q')
                return false;
        }
        for(int i=0; i<8; i++) {
            if(i == b)
                break;
            if(board[a][i] == 'Q')
                return false;
        }
		int row=a;
		int col=b;
		while(row!=0&&col!=0) {
			row--;
			col--;
			if(board[row][col]=='Q')return false;
		}
		row=a;
		col=b;
		while(row!=7&&col!=7) {
			row++;
			col++;
			if(board[row][col]=='Q')return false;
		}
		row=a;
		col=b;
		while(row!=0&&col!=7) {
			row--;
			col++;
			if(board[row][col]=='Q')return false;
		}
		row=a;
		col=b;
		while(col!=0&&row!=7) {
			row++;
			col--;
			if(board[row][col]=='Q')return false;
		}
		return true;
	}

}
