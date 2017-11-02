import java.util.Scanner;

public class CreditCardNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String cardNumber;
		System.out.println("Enter a credit card number as a long integer:");
		cardNumber=input.nextLine();
		long number = Long.parseLong(cardNumber);
		if(isValid(number)) {
			System.out.println(cardNumber+" is valid.");
		}
		else {
			System.out.println(cardNumber+" is invalid.");
		}
	}
	//valid���� �ƴ��� ����
	public static boolean isValid(long number) {
		if(getSize(number)<13 ||getSize(number)>16) {
			System.out.println("Wrong size");
			return false;
		}
		if(prefixMatched(number,4)||prefixMatched(number,5)||prefixMatched(number,37)||prefixMatched(number,6));
		else {
			System.out.println("Don't make kind of card");
			return false;
		}
		if((sumOfDoubleEvenPlace(number)+sumOfOddPlace(number))%10==0)
			return true;
		else {
			System.out.println("No moduler 10!!");
			return false;
		}
	}
	//¦����°������ �ι��ؼ� ���Ѵ�.
	public static int sumOfDoubleEvenPlace(long number) {
		int sum=0;
		int size=getSize(number);
		number/=10;
		for(int i=0;i<size;i+=2) {
			sum+= getDigit((int)(number%10)*2);
			number/=100;
		}
		return sum;
	}
	//�� ���ڵ��� �����Ѵ�. ���ڸ��� ���ļ� �����Ѵ�.
	public static int getDigit(int number) {
		if(number<9)return number;
		else return number/10 + number%10;
	}
	//Ȧ����° ������ ���Ѵ�.
	public static int sumOfOddPlace(long number) {
		int sum=0;
		int size=getSize(number);
		for(int i=0;i<size;i+=2) {
			sum+=number%10;
			number/=100;
		}
		return sum;
	}
	//
	public static boolean prefixMatched(long number, int d) {
		return getPrefix(number,getSize(d))==d;
	}
	//������ ����� �����Ѵ�.
	public static int getSize(long d) {
		int size=0;
		while(d>0) {
			size++;
			d/=10;
		}
		return size;
	}
	
	public static long getPrefix(long number, int k) {
		if(getSize(number)<k) {
			return number;
		}
		String tempNumber=number+"";
		return Long.parseLong(tempNumber.substring(0,k));
	}
	

}
