#include <iostream>
#include <fstream>
#include <string>

using namespace std;
/*  
<Input File>
3
{[][]([[]({})])}
[[]()([[{}]])]
{}}[]][{}]

<Output File>
3
true
true
false

*/
/* Node class ÀÔ´Ï´Ù.*/
class Node {
	friend class Stack;
private:
	char data;
	Node *next;

public:
	Node(char data);
	~Node() {};
	char getData() {
		if (data == NULL)
			return 'e';
		return data;
	}
};
Node::Node(char data) {
	this->data = data;
	next = NULL;
}


class Stack {
private:
	Node *top;
	int size;
public:
	Stack();
	~Stack() {};
	void push(char data);
	char pop();
	bool isEmpty();
	int getSize();
	Node* getTop() {
		if (top == NULL) {
			Node *newNode=new Node('e');
			return newNode;
		}
		return top;
	}
};
Stack::Stack() {
	this->top = NULL;
	this->size = 0;
}
void Stack::push(char data) {
	if (isEmpty() == true) {
		this->top = new Node(data);
	}
	else {
		Node *newNode = new Node(data);
		newNode->next = top;
		top = newNode;
	}
	size++;
}
char Stack::pop() {
	if (top == NULL) {
		cout << "is Empty" << endl;
	}
	char tmpData = top->data;
	Node *tmpNode = top;
	top = top->next;
	delete tmpNode;
	return tmpData;
}

bool Stack::isEmpty() {
	if (this->top == NULL)return true;
	else return false;
}
int Stack::getSize() {
	return size;
}


int main() {
	int NumExpress = 0;
	Stack expression[2000];
	ifstream inFile("bracket.inp");
	if (inFile.is_open());
//		cout << "Open" << endl;
	else {
//		cout << "Fail" << endl;
		return 0;
	}
	inFile >> NumExpress;
	int i = 0;
	int tmpNum = 0;
	while (tmpNum<NumExpress) {
		string strExpression;
		inFile >> strExpression;
		for (int i = 0; i < strExpression.length(); i++) {
			if (strExpression[i] == ')') {
				if (expression[tmpNum].getTop()->getData() == '(') {
					expression[tmpNum].pop();
				}
				else expression[tmpNum].push(strExpression[i]);

			}
			else if (strExpression[i] == '}') {
				if (expression[tmpNum].getTop()->getData() == '{') {
					expression[tmpNum].pop();
				}
				else expression[tmpNum].push(strExpression[i]);
			}
			else if (strExpression[i] == ']') {
				if (expression[tmpNum].getTop()->getData() == '['){
					expression[tmpNum].pop();
				}
				else expression[tmpNum].push(strExpression[i]);
			}
			else expression[tmpNum].push(strExpression[i]);
		}
		tmpNum++;
	}
	inFile.close();

	ofstream outFile("bracket.out");
	outFile << NumExpress << endl;
	tmpNum = 0;
	while (tmpNum < NumExpress) {
		if (expression[tmpNum].isEmpty()) {
			outFile << "true" << endl;
		}
		else {
			outFile << "false" << endl;
		}
		tmpNum++;
	}


	return 0;
}
