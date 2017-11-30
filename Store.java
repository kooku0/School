package store;

class Store {
	  // this is a customer list.
	  private CustomerList records=null;
//	  처음에 records에 null값을 준다.
	  
	  static int ID=0;
	//ID값은 0부터 등록된 순서대로 1씩 증가하게 된다.
	  public void register(Customer customer) {
	     /*  
	        this method registers customer.
	        you should make sure this customer has registered before,
	          then add him/her into the customer list.
	        also you should give him/her an ID and a credit.
	     */
		 

			  if(records!=null) { 
//등록된 customer가 있을 때 돌아간다.
				  if(records.getHead().contains(customer)) {
//이미 등록된 customer인지 확인한다.
					  System.out.println(customer.getName()+" Already registered");
//등록되었다면 등록된 사용자라고 표시된다.  
				  } 
				  else {
				  ID++;
//등록되지 않았다면 ID값을 증가시킨다.
				  customer.setID(ID);
//ID값을 넣어준다.
				  customer.setCredit(new Credit());
//처음에 credit class를 생성한다. 
				  CustomerNode newNode = new CustomerNode();
//새로운 customerNode를 만들고 여기에 들어온 customer를 넣을 것 이다.
				  newNode.setItem(customer);
//새로운 node에 들어온 customer를 넣는다.
				  newNode.setNext(null);
//그리고 next에는 null을 넣는다.
				  	newNode.setNext(records.getHead());
//그리고 새로운 만든 노드의 뒤에 이전에 있었던 head를 넣는다. 그러면 새로 만든 노드가 가장 앞에 위치한다.
				  	CustomerList newlist = new CustomerList();
//새로운 customerlist를 만들어 준다.
				  	newlist.setHead(newNode);
//새로운 리스트에 이전에 만든 새로운 노드를 head로 위치 시킨다.
				  	records=newlist;
//records에 newlist를 넣으므로써 값이 사라지지 않게 해준다.
				  	System.out.println(customer.getName()+" registers successfully.");
//이렇게 저장이 되면 successfully가 뜬다.
				  }		  	
			  }
			  else{
// register가 아무것도 등록되지 않았을 때 실행 된다.
				  ID++;
//ID값을 증가시킨다.
				  customer.setID(ID);
//ID값을 넣는다.
				  customer.setCredit(new Credit());
// 처음에 credit class를 생성한다.
				  CustomerNode newNode = new CustomerNode();
//새로운 customerNode를 만들고 여기에 들어온 customer를 넣을 것 이다.
				  newNode.setItem(customer);
//새로운 node에 들어온 customer를 넣는다.
				  newNode.setNext(null);
//그리고 새로운 만든 노드의 뒤에 이전에 있었던 head를 넣는다. 그러면 새로 만든 노드가 가장 앞에 위치한다.
				  CustomerList newlist = new CustomerList();
//새로운 customerlist를 만들어 준다.
				  newlist.setHead(newNode);
//새로운 리스트에 이전에 만든 새로운 노드를 head로 위치 시킨다.
				  records=newlist;
//records에 newlist를 넣으므로써 값이 사라지지 않게 해준다.
				  System.out.println(customer.getName()+" registers successfully.");
//이렇게 저장이 되면 successfully가 뜬다.
			  }
	  }
	  public void remove(Customer customer) {    
	     // remove customer from the customer list. 
		  records.getHead().removeCustomer(customer);
	//customerNode class에서 제거를 시킨다.
	  }

	  public Customer getCustomer(int id) {
	     // find a customer by an ID. 
		  if (id < 0) 
//ID가 0 이하이면 null을 리턴한다.
		      return null;
		    else {
		      CustomerList list = records;
//customerList에 records를 넣는다.
		      while (!list.isEmpty()) {
//만약에 list 가 비어 있지 않다면 실행
		        Customer customer = list.getHead().getItem();
//해당 customer을 받아온다.
		        if (customer.getID() == id) {
//만약 찾는 ID값과 같다면 해당 customer를 리턴한다.
		          return customer;
		        } else {
//찾는 ID값과 다르다면
		          list.setHead(list.getHead().getNext());
//다음 customer를 불러온다.
		        }
		      }
		      return null;
//null을 리턴한다.
		    }
	  }
	  public static void main(String args[]) {
		 
		    Store theStore = new Store();
//새로운 store를 만든다.
		    Customer Tom = 
		      new Customer("Tom", 
		                   new Address("Univalsity",986, "Ulsan", "CA", "3459"));
//Tom이라는 customer을 만든 후 address클래스를 생성, 초기화 한다..
		    Customer Phan = 
		      new Customer("Phan", 
		                   new Address("alsa", 75, "Seaul", "CA", "9866"));
//Phan 이라는 customer을 만든 후 address클래스를 생성, 초기화 한다..

		    Customer Lee = 
		      new Customer("Lee", 
		                   new Address("seal",  934, "Pusan", "CA", "2343"));
//Lee이라는 customer을 만든 후 address클래스를 생성, 초기화 한다.		    
		    theStore.register(Tom);
//Tom를 register에 넣는다. 
		    theStore.register(Phan);
//Phan 을 register에 넣는다.
		    theStore.register(Lee);
//Lee 을 register에 넣는다.
		    theStore.register(Lee);
//Lee 을 register에 넣는다.

		    System.out.println("\nCUSTOMER LIST");
		    theStore.records.getHead().printAllCustomers();
//register에 저장된 모든 customer을 출력한다.
		    System.out.println("\nremove Phan from the list");
		    theStore.remove(Phan);
//Phan customer을 register에서 삭제 시킨다.
		    System.out.println("\nCUSTOMER LIST");
		    theStore.records.getHead().printAllCustomers();
//register에 저장된 모든 customer을 출력한다. Phan은 출력되지 않을 것이다.
		    System.out.println("\nSearch ID(3)");
		    System.out.println(theStore.getCustomer(3).getName());
//ID가 3인 customer를 찾아서 Name을 출력한다.
		    System.out.println("\nSet Password of Tom");
		    Tom.setPasswd("123432");
		    System.out.println(Tom.getPasswd());
//Tom의 Password를 입력하고, 출력한다.
		    System.out.println("\nTom Purchase 3000");
		    Tom.makePayment(3000);
//Tom의 credit에 3000을 추가한다.
		    System.out.println("\nLee Purchase 6000");
		    Lee.makePayment(6000);	
//Lee의 credit에 6000을 추가한다. MAX_AMOUNT를 넘어서 들어가지 않는다.
		    System.out.println("\nCUSTOMER LIST");
		    theStore.records.getHead().printAllCustomers();
//register에 저장된 모든 customer을 출력한다. 
		    System.out.println("\nTom Purchase 3000+3000");
		    Tom.makePayment(3000);
//Tom의 credit에 3000을 추가적으로 추가한다. 이러면 MAX_AMOUNT를 넘겨서 추가되지 않는다.
		    System.out.println("\nCUSTOMER LIST");
		    theStore.records.getHead().printAllCustomers();
//regist에 있는 모든 customer를 출력해서 credit을 확인한다.
		  }
	  }

	class  CustomerNode {
	  // an item in the link list 
	  private Customer item;      
	  private CustomerNode next;
	  
	  public Customer getItem() { 
//customer을 반환한다.
		  return item;
	  }
	  public CustomerNode getNext() {
		  return next;
//다음 customerNode를 반환한다.
	  }
	  public boolean isEmpty() {
		    return item == null;
//cutomer가 비었는지 안 비었는지 확인한다.
		  }

	  public void setItem(Customer customer) {
		  this.item=customer;
//입력된 customer을 item에 넣는다.
	  }
	  public void setNext(CustomerNode nextNode) {
		  this.next=nextNode;
//next에 NextNode를 넣는다.
	  }

	  public void removeCustomer(Customer customer) {
//들어오는 customer를 제거한다.
		    if (!isEmpty()) {
//비어있는지 안 비어있는지 확인한다.
		      if (customer.equals(item)) {
//들어오는 customer와 동일한지 확인한다.
		        if (next == null) {
//만약 마지막 node라면 item에 null을 넣어서 제거한다.
		          item = null;
		        } else {
//만약 마지막 node가 아니라면 nextnode의 item과 node를 옮김으로써 제거한다.
		          item = next.item;
		          next = next.next;
		        }
		      } else if (next != null) {
//만약에 nextNode가 null이 아니라면
		        next.removeCustomer(customer); 
//다시 코드를 실행한다.
		      }
		    } 
	  }
	  public void printAllCustomers() {
//모든 customer를 출력한다.
		    if (!isEmpty()) {
//비어있지 않다면
		      System.out.println("--------------------");
		      System.out.println("ID  : "+item.getID());
//해당 customer의 ID를 출력한다.
		      System.out.println("Name: "+item.getName());
//해당 customer의 Name를 출력한다.
		      System.out.println("City: "+item.getAddress().getCity());
//해당 customer의 City를 출력한다.
		      System.out.println("State: "+item.getAddress().getState());
//해당 customer의 State를 출력한다.
		      System.out.println("StreetName: "+item.getAddress().getStreetName());
//해당 customer의 StreetName를 출력한다.
		      System.out.println("StreetNum: "+item.getAddress().getStreetNumber());
//해당 customer의 StreetNumber를 출력한다.
		      System.out.println("ZipCode: "+item.getAddress().getZipCode());
////해당 customer의 zipcode를 출력한다.
		      System.out.println("Debit: "+item.getCredit().getDebit());
//해당 customer의 credit를 출력한다.
		      if (next != null)
//next가 null이 아니라면 다음 customer에서 다시 실행한다.
		        next.printAllCustomers();
		    }
		  }
	  public boolean contains(Customer customer) {
//중복 customer가 있는지 확인하는 문제이다.
		    if (customer.equals(item)) 
//만약에 같은 customer가 있다면 true를 반환한다.
		      return true;
		    else if (next != null && !next.isEmpty())
//마지막 노드가 아니고 비어있지 않다면 실행한다.
		      return next.contains(customer);
//다음 노드가 똑같은지 확인한다.
		    else
		      return false;
//다 다르다면 false를 출력한다.
		  }
	}
	class CustomerList {

	  private CustomerNode head;

	  // add any methods on demand
	  public boolean isEmpty() {
		    return head == null;
//비어있다면 true를 출력한다.
		  }
	  public CustomerNode getHead() {
		    return head;
//head를 반환한다.
		  }
	  public void setHead(CustomerNode head) {
		  this.head=head;
//입력 Node를 head에 넣는다.
	  }
	}
	// this class represents a customer 

	class  Customer {

	  private int accountID;          // an unique ID given by the store
	  private String name;            // the name of this customer
	  private Address address;        // the address of this customer
	  private Credit credit;          // the credit of this customer 
	  private String passwd;          // the password set by this customer

	  public Customer(String name, Address addr) {
	     // a constructor to create a new customer.
		  this.name=name;
		  this.address=addr;
//입력 name과 addr로 생성한다.
	  }
	  public int getID() {
	     // return the id of this customer.
		  return accountID;
//ID값을 반환한다.
	  }

	  public String getName() {
	     // return the name of this customer.
		  return name;
//Name을 반환한다.
	  }

	  public Address getAddress() {
	     // return the address of this customer.
		  return address;
//address를 반환한다.
	  }

	  protected Credit getCredit() {
	     // return the Credit of this customer.
		  return credit;
//credit를 반환한다.
	  }

	  public void setPasswd(String pwd) {
	     // set a new password.
		  this.passwd=pwd;
//입력 passwd를 등록한다.
	  }

	  public void setAddress(Address addr) {
	     // set a new address.
		  this.address=addr;
//입력 addr를 address에 넣는다.
	  }

	  protected void setID(int i) {
	     // set the id to i
		  this.accountID=i;
//ID를 입력한다.
	  }
	  protected void setCredit(Credit credit) {
	     // set the credit to the input credit.
		  this.credit=credit;
//credit를 입력한다.
	  }
	  
	  public boolean equals(Customer customer) {
	     // check whether this customer is same as customer.
		    return this.name.equals(customer.name) && 
		            this.address.equals(customer.address);
//customer가 똑같은지 아닌지 확인한다.
	  }
	  
	  public String getPasswd() {
		  return passwd;
//passwd를 반환한다.
	  }

	  public boolean makePurchase(int amount) {
	     /* 
	         buy by debit.
	         return true if the debit of this customer is less than the
	           maximum limitation, otherwise return false.
	     */
		  if((this.credit).getDebit() < credit.MAX_AMOUNT) {
			  return true;
//credit이 MAX_AMOUNT보다 작으면 true를 출력한다.
		  }
		  else return false;
//아니면 false를 출력한다.
	  }

	  public void makePayment(int amount) {
	     // buy by cash
		  if(makePurchase(amount)) {
			  (this.credit).addDebit(amount);
		  }
	  }
//makePurchase로 돈을 사용가능한지 확인하고, 사용가능하면 credit에 amount를 추가한다.
	}
	// this class represents an address
	class  Address {

	  private String streetName;
	  private int streetNumber;
	  private String city;
	  private String state;
	  private String zipcode;

	  public Address(String stName, int stNumber, 
	                   String city, String state, String zipcode) {
	     // constructor
		  this.streetName=stName;
		  this.streetNumber=stNumber;
		  this.city=city;
		  this.state=state;
		  this.zipcode=zipcode;
//입력된 값들을 private에 넣는다.
	  }
	  public String getStreetName() {
	     // get the street name
		  return streetName;
//streetname을 반환한다.
	  }

	  public int getStreetNumber() {
	    // get the street number
		  return streetNumber;
//streetNumber를 반환한다.
	  }

	  public String getCity() {
	    // get the city name
		  return city;
//city를 반환한다.
	  }

	  public String getState() {
	    // get the state name
		  return state;
//state를 반환한다.
	  }
	  
	  public String getZipCode() {
	    // get the zip code
		  return zipcode;
	  }
//zipcode를 반환한다.
	}

	// this class represents credit

	class  Credit {

	   final public static int MAX_AMOUNT = 5000; 
	   private int debit=0;

	   public int getDebit() {
	      // get the current debit.
		   return debit;
//debit을 반환한다.
	   }
	   
	   protected void addDebit(int amount) {
	      // update the debit, also make sure not bigger than the limitation.
		   int temp=debit;
		   if((temp+=amount)>5000) {
			   System.out.println("Over MAX_AMOUNT, Don't Buy!");
//입력amount로 안하여 debit이 MAX_AMOUNT를 넘는다면 못 산다고 출력한다.
		   }
		   else{this.debit+=amount;}
//살수 있다면 debit에 amount를 추가한다.
	   }
	}
	
