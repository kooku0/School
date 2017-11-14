import java.util.Random;

public class EightQueensPuzzle {

	public static void main(String[] args) {
		Random random = new Random();
		char[][] board = new char[8][8];
		int QueensNum=8;
		for(int a=0;a<8;a++) {
			for(int b=0;b<8;b++) {
				board[a][b]=' ';
			}
		}
		
		while(QueensNum>0) {			
			int a=random.nextInt(8);
			int b=random.nextInt(8);

			if(boardCheck(board)) {
				QueensNum=8;
			}
			else if(board[a][b]!='Q'&&board[a][b]!='x') {
				board[a][b]='Q';
				drawX(a,b,board);
				QueensNum--;
			}
			
		}
		for(int a=0;a<8;a++) {
			System.out.print("|");
			for(int b=0;b<8;b++) {			
				if(board[a][b]=='Q')System.out.print(board[a][b]+"|");
				else System.out.print(" "+"|");
			}
			System.out.println();
		}
	}
	public static boolean boardCheck(char[][] board) {
		for(int a=0;a<8;a++) {
			for(int b=0;b<8;b++) {
				if(board[a][b]==' ')return false;
			}
		}
		for(int a=0;a<8;a++) {
			for(int b=0;b<8;b++) {
				board[a][b]=' ';
			}
		}
		return true;
	}
	public static void drawX(int a,int b, char[][]board) {
		for(int i=0;i<8;i++) {
			if(board[a][i]!='Q')board[a][i]='x';
		}
		for(int i=0;i<8;i++) {
			if(board[i][b]!='Q')board[i][b]='x';
		}
		int row=a;
		int col=b;
		while(row!=0&&col!=0) {
			row--;
			col--;
			if(board[row][col]!='Q')board[row][col]='x';
		}
		row=a;
		col=b;
		while(row!=7&&col!=7) {
			row++;
			col++;
			if(board[row][col]!='Q')board[row][col]='x';
		}
		row=a;
		col=b;
		while(row!=0&&col!=7) {
			row--;
			col++;
			if(board[row][col]!='Q')board[row][col]='x';
		}
		row=a;
		col=b;
		while(col!=0&&row!=7) {
			row++;
			col--;
			if(board[row][col]!='Q')board[row][col]='x';
		}
	}
}