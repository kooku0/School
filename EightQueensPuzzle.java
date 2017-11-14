
public class EightQueensPuzzle {
	static char[][] board = new char[8][8];

	public static void main(String[] args) {
		
		putQueens();
		
	}

	   static void putQueens() {
		   int count=0;
		   for(int x=0;x<8;x++) {
			   for(int z=0;z<8;z++) {
				   board[x][z]=' ';
			   }
		   }
		   for(int a=0;a<8;a++) {
			   for(int b=0;b<8;b++) {
				   for(int c=0;c<8;c++) {
					   for(int d=0;d<8;d++) {
						   for(int e=0;e<8;e++) {
							   for(int f=0;f<8;f++) {
								   for(int g=0;g<8;g++) {
									   for(int h=0;h<8;h++) {
										   if(Check(0,a)) {
											   board[0][a]='Q';
											   count++;
										   }
										   if(Check(1,b)) {
											   board[1][b]='Q';
											   count++;
										   }
										   if(Check(2,c)) {
											   board[2][c]='Q';
											   count++;
										   }
										   if(Check(3,d)) {
											   board[3][d]='Q';
											   count++;
										   }
										   if(Check(4,e)) {
											   board[4][e]='Q';
											   count++;
										   }
										   if(Check(5,f)) {
											   board[5][f]='Q';
											   count++;
										   }
										   if(Check(6,g)) {
											   board[6][g]='Q';
											   count++;
										   }
										   if(Check(7,h)) {
											   board[7][h]='Q';
											   count++;
											   if(count==8) {
												   printBoard();
											   }
										
										   }
										   count=0;
										   for(int x=0;x<8;x++) {
											   for(int z=0;z<8;z++) {
												   board[x][z]=' ';
											   }
										   }
									   }
								   }
							   }
						   }
					   }
				   }
			   }
		   }
		   
	    }
		public static void printBoard(){
			for(int a=0;a<8;a++) {
				System.out.print("|");
				for(int b=0;b<8;b++) {			
					if(board[a][b]=='Q')System.out.print(board[a][b]+"|");
					else System.out.print(" "+"|");
				}
				System.out.println();
			}
			System.out.println();
		}


	public static boolean Check(int a,int b) {
		for(int i=0;i<8;i++) {
			if(board[a][i]=='Q')return false;
		}
		for(int i=0;i<8;i++) {
			if(board[i][b]=='Q')return false;
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