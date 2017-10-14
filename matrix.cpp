
/*

ex input file=

 

1*2

10

2

1

9

1, 1, 1

2, 1, 2

3, 1, 3

4, 1, 4

5, 1, 5

6, 1, 6

7, 1, 7

8, 1, 8

9, 1, 9

2

9

1, 1, 1

1, 2, 2

1, 3, 3

1, 4, 4

1, 5, 5

1, 6, 6

1, 7, 7

1, 8, 8

1, 9, 9

 

*/

 

#include <iostream>

#include <fstream>

#include <string>

 

using namespace std;

 

class SparseMatrix;

 

class MatrixElement {

	friend class SparseMatrix;

private:

	int row, col, value;

public:

	MatrixElement(int row = 0, int col = 0, int value = 0) {

		this->row = row;

		this->col = col;

		this->value = value;

	}

	void SetMatrixElement(int row = 0, int col = 0, int value = 0) {

		this->row = row;

		this->col = col;

		this->value = value;

	}

	void const PrintMatrixElement() {

		cout << row << " " << col << " " << value << endl;

	}

	void SetRow(int row) {

		this->row = row;

	}

	void SetCol(int col) {

		this->col = col;

	}

	void SetValue(int value) {

		this->value = value;

	}

	int const OutputRow() {

		return row;

	}

	int const OutputCol() {

		return col;

	}

	int const OutputValue() {

		return value;

	}

	void const swap_col_row() {

		int temp;

		temp = row;

		row = col;

		col = temp;

	}

	~MatrixElement() {};

};

 

class SparseMatrix :public MatrixElement{

	friend class MatrixElement;

private:

	int nElements=0;

	MatrixElement smArray[100];

public:

	void SetnElement(int n) {

		this->nElements = n;

	}

	void InputMatrixElement(MatrixElement* AddMatrixElement) {

		smArray[nElements] = *AddMatrixElement;

		nElements++;

	}

	void ChMatrixElement(int idx,int row, int col, int value ) {

		smArray[idx].SetMatrixElement(row, col, value);

	}

	void const PrintSparseMatrix() {

		for (int i = 0; i < nElements; i++) {

			smArray[i].PrintMatrixElement();

		}

	}

	int const OutputnElements() {

		return nElements;

	}

	MatrixElement OutputMatrixElement(int c) {

		return smArray[c];

	}

	void SwapElement(int a, int b) {

		MatrixElement m = smArray[a];

		smArray[a] = smArray[b];

		smArray[b] = m;

	}

	void SwapColRow(int c) {

		int temp;

		temp = smArray[c].col;

		smArray[c].col = smArray[c].row;

		smArray[c].row = temp;

	}

	

	~SparseMatrix() {};

};

class Matrix :public SparseMatrix {

	friend class MatrixElement;

private:

	int num=0;

	SparseMatrix mArray[100];

public:

	void InputSparseMatrix(SparseMatrix* inputSM) {

		mArray[num] = *inputSM;

		num++;

	}

	void InputSparseMatrix2(SparseMatrix* inputSM, int c) {

		mArray[c] = *inputSM;

	}

	void PrintMatrix() {

		for (int i = 0; i < num; i++) {

				cout << i + 1 << endl;

				mArray[i].PrintSparseMatrix();

			}

	}

	SparseMatrix OutputSpareMatrix(int c) {

		return mArray[c];

	}

	

	~Matrix() {};

};

 

Matrix M;

SparseMatrix MulF(SparseMatrix sm1, SparseMatrix sm2);

SparseMatrix MinF(SparseMatrix sm1, SparseMatrix sm2);

SparseMatrix PlusF(SparseMatrix sm1, SparseMatrix sm2);

SparseMatrix MatrixSort(SparseMatrix inputSM);

SparseMatrix* OpF(string MatrixExpression);

void swap(int& a, int& b);

void MatrixTranspose(int c);

string CheckT(string MatrixExpression);

int main() {

	ifstream inFile("matrix.inp");

	string MatrixExpression;

	int SizeMatrix;

	int NumberMatrix;

	int OrderMatrix;

	int NumberMatrixElements;

	char de;

	if (inFile.is_open());

//		cout << "Open" << endl;

	else {

//		cout << "Fail" << endl;

		return 0;

	}

	getline(inFile,MatrixExpression);

	inFile >> SizeMatrix;

	inFile >> NumberMatrix;

	for (int i = 0; i < NumberMatrix; i++) {

 

		inFile >> OrderMatrix;

		inFile >> NumberMatrixElements;

		SparseMatrix *SM = new SparseMatrix;

		for (int z = 0; z < NumberMatrixElements; z++) {

			int a, b, c;

			inFile >> a >> de >> b >> de >> c;

			MatrixElement *ME = new MatrixElement(a,b,c);

			(*SM).InputMatrixElement(ME);

			*SM = MatrixSort(*SM); //나중에 추가한거라서 여의치 않으면 삭제

		}

		M.InputSparseMatrix(SM);

	}

	inFile.close();

 

 

	MatrixExpression=CheckT(MatrixExpression); // Transpose 함수

//	cout << endl << endl;

//	M.PrintMatrix();  // Transpose 되었는지 확인 함수

 

	SparseMatrix SM;

	SM=*OpF(MatrixExpression);

	cout << endl <<"결과값" <<endl;

	for (int i = 0; i < SM.OutputnElements(); i++) {

		SM.OutputMatrixElement(i).PrintMatrixElement();

	}

	SM = MatrixSort(SM);

	ofstream outFile("matrix.out");

	outFile << SM.OutputnElements() << endl;

	for (int i = 0; i < SM.OutputnElements(); i++) {

		outFile << SM.OutputMatrixElement(i).OutputRow() <<", "<< SM.OutputMatrixElement(i).OutputCol()<<", " << SM.OutputMatrixElement(i).OutputValue() << endl;

	}

	outFile.close();

	return 0;

}

 

 

 

 

string CheckT(string MatrixExpression) {

	int size = MatrixExpression.length();

	for (int i = 0; i < size; i++) {

		if (MatrixExpression[i] == 'T') {

			int c;

			c = MatrixExpression[i - 1] - '0';

			MatrixTranspose(c);

			for (int j = i; j < size-1; j++) {

				MatrixExpression[j] = MatrixExpression[j + 1];

			}

			MatrixExpression[size - 1] = NULL;

			size--;

		}

	}

	return MatrixExpression;

}

void MatrixTranspose(int c) {

	SparseMatrix SM1=M.OutputSpareMatrix(c-1);

	for (int i = 0; i < SM1.OutputnElements(); i++) {

		SM1.SwapColRow(i);

	}

	SparseMatrix *SM2 = new SparseMatrix ;

	SM2 = &SM1;

	M.InputSparseMatrix2(SM2,c-1);

}

 

 

 

 

 

SparseMatrix* OpF(string MatrixExpression) {

	SparseMatrix SM;

	SparseMatrix *SM1;

	int c;

	c = MatrixExpression[0] - '0';

	if ((0 <= MatrixExpression[1] - '0') && (MatrixExpression[1] - '0' < 10))c = c * 10 + (MatrixExpression[1] - '0');

	SM = M.OutputSpareMatrix(c-1);

	for (int i = 0; i < MatrixExpression.length(); i++) {

		int z;

		z = MatrixExpression[i + 1] - '0';

		if (MatrixExpression[i] == '+') {

			if (0<=(MatrixExpression[i + 2]-'0')&&((MatrixExpression[i+2]-'0')<10)) {

				z = z * 10 + (MatrixExpression[i + 2] - '0');

			}

			SM=PlusF(SM, M.OutputSpareMatrix(z-1));

		}

		else if (MatrixExpression[i] == '-') {

			if (0 <= (MatrixExpression[i + 2] - '0') && ((MatrixExpression[i + 2] - '0')<10)) {

				z = z * 10 + (MatrixExpression[i + 2] - '0');

			}

			SM=MinF(SM, M.OutputSpareMatrix(z-1));

		}

		else if (MatrixExpression[i] == '*') {

			if (0 <= (MatrixExpression[i + 2] - '0') && ((MatrixExpression[i + 2] - '0')<10)) {

				z = z * 10 + (MatrixExpression[i + 2] - '0');

			}

			SM=MulF(SM, M.OutputSpareMatrix(z-1));

		}

	}

	SM1= new SparseMatrix(SM);

	return SM1;

}

 

SparseMatrix PlusF(SparseMatrix sm1, SparseMatrix sm2) {

	SparseMatrix *sm;

	int boolean;

	for (int a = 0; a < sm2.OutputnElements(); a++) {

		boolean = 0;

		for (int b = 0; b < sm1.OutputnElements(); b++) {

			if ((sm1.OutputMatrixElement(b).OutputCol() == sm2.OutputMatrixElement(a).OutputCol()) &&

				(sm1.OutputMatrixElement(b).OutputRow() == sm2.OutputMatrixElement(a).OutputRow())) {			

				sm1.ChMatrixElement(b,sm2.OutputMatrixElement(a).OutputRow(),sm2.OutputMatrixElement(a).OutputCol(),sm1.OutputMatrixElement(b).OutputValue()+sm2.OutputMatrixElement(a).OutputValue());

				boolean = 1;

				break;

			}

		}

		MatrixElement *m1 = new MatrixElement(sm2.OutputMatrixElement(a).OutputRow(), sm2.OutputMatrixElement(a).OutputCol(), sm2.OutputMatrixElement(a).OutputValue());

		if (boolean == 0) sm1.InputMatrixElement(m1);

 

	}

	sm = new SparseMatrix(sm1);

	return *sm;

}

 

SparseMatrix MinF(SparseMatrix sm1, SparseMatrix sm2) {

	SparseMatrix *sm;

	int boolean;

	for (int a = 0; a < sm2.OutputnElements(); a++) {

		boolean = 0;

		for (int b = 0; b < sm1.OutputnElements(); b++) {

			if ((sm1.OutputMatrixElement(b).OutputCol() == sm2.OutputMatrixElement(a).OutputCol()) &&

				(sm1.OutputMatrixElement(b).OutputRow() == sm2.OutputMatrixElement(a).OutputRow())) {

				sm1.ChMatrixElement(b, sm2.OutputMatrixElement(a).OutputRow(), sm2.OutputMatrixElement(a).OutputCol(), sm1.OutputMatrixElement(b).OutputValue() - sm2.OutputMatrixElement(a).OutputValue());

				boolean = 1;

				break;

			}

		}

		MatrixElement *m1 = new MatrixElement(sm2.OutputMatrixElement(a).OutputRow(), sm2.OutputMatrixElement(a).OutputCol(), sm2.OutputMatrixElement(a).OutputValue());

		if (boolean == 0) sm1.InputMatrixElement(m1);

 

	}

	sm = new SparseMatrix(sm1);

	return *sm;

}

 

SparseMatrix MulF(SparseMatrix sm1, SparseMatrix sm2) {

	int Asm[100][100];

	int Asm1[100][100];

	int Asm2[100][100];

	SparseMatrix sm;

 

	for (int a = 0; a < 100; a++) {

		for (int b = 0; b < 100; b++) {

			Asm1[a][b] = 0;

			Asm2[a][b] = 0;

			Asm[a][b] = 0;

		}

	}

	for (int a = 0; a < sm1.OutputnElements(); a++) {

		int q;

		int w;

		q=sm1.OutputMatrixElement(a).OutputRow();

		w = sm1.OutputMatrixElement(a).OutputCol();

		Asm1[q][w] = sm1.OutputMatrixElement(a).OutputValue();

	}

	for (int a = 0; a < sm2.OutputnElements(); a++) {

		int q;

		int w;

		q = sm2.OutputMatrixElement(a).OutputRow();

		w = sm2.OutputMatrixElement(a).OutputCol();

		Asm2[q][w] = sm2.OutputMatrixElement(a).OutputValue();

	}

	int temp=0;

	for (int a = 0; a < 100; a++) {

		for (int b = 0; b < 100; b++) {

 

			for (int c = 0; c < 100; c++) {

				temp += Asm1[a][c] * Asm2[c][b];

			}

			Asm[a][b] = temp;

			temp = 0;

		}

	}

	for (int a = 0; a < 100; a++) {

		for (int b = 0; b < 100; b++) {

			if (Asm[a][b] != 0) {

				MatrixElement * m = new MatrixElement(a, b, Asm[a][b]);

				sm.InputMatrixElement(m);

			}

		}

	}

 

	return sm;

}

 

SparseMatrix MatrixSort(SparseMatrix inputSM) {

	SparseMatrix sm;

	for (int a = 0; a < inputSM.OutputnElements(); a++) {

		for (int b = 0; b < inputSM.OutputnElements() - 1; b++) {

			if (inputSM.OutputMatrixElement(b).OutputRow()>inputSM.OutputMatrixElement(b+1).OutputRow()) {

				inputSM.SwapElement(b, b + 1);

			}

		}

	}

	for (int a = 0; a < inputSM.OutputnElements(); a++) {

		for (int b = 0; b < inputSM.OutputnElements() - 1; b++) {

			if (inputSM.OutputMatrixElement(b).OutputRow()==inputSM.OutputMatrixElement(b + 1).OutputRow()&&inputSM.OutputMatrixElement(b).OutputCol()>inputSM.OutputMatrixElement(b).OutputCol()) {

				inputSM.SwapElement(b, b + 1);

			}

		}

	}

	return inputSM;

}

맨위로

    구민규
    시소.docx
    새창으로 메일 보기
        다음
        10-12 10:12

맨 위로


