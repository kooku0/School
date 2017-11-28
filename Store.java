package java;

class Store {
	  
	  // this is a customer list.
	  private CustomerList records;
	  
	  public void register(Customer customer) {
	     /*  
	        this method registers customer.
	        you should make sure this customer has registered before,
	          then add him/her into the customer list.
	        also you should give him/her an ID and a credit.
	     */
		  CustomerNode newNode = new CustomerNode();
		  newNode.setItem(customer);
		  if(!records.isEmpty()) {
			  if(records.getHead().getNext()!=null)
				  newNode.setNext(records.getHead());
		  }
		  records.setHead(newNode);
	  }
	  public void register(String name) {
		  
		  Customer customer = new Customer(name);

	  }

	  public void remove(Customer customer) {    
	     // remove customer from the customer list. 
		  records.getHead().removeCustomer(customer);
		  
	  }

	  public Customer getCustomer(int id) {
	     // find a customer by an ID. 
		  if (id < 0) 
		      return null;
		    else {
		      CustomerList list = records;
		      while (!list.isEmpty()) {
		        Customer customer = list.getHead().getItem();
		        if (customer.getID() == id) {
		          return customer;
		        } else {
		          list.setHead(list.getHead().getNext());
		        }
		      }
		      return null;
		    }
	  }
	  public static void main(String args[]) {
		    // create a new store.
		    Store theStore = new Store();

		    // create three customers
		    Customer john = 
		      new Customer("John", 
		                   new Address("Regents", 1234, "San Diego", "CA", "92037"));

		    Customer mary = 
		      new Customer("Mary", 
		                   new Address("Salem", 7803, "San Diego", "CA", "92045"));

		    Customer hans = 
		      new Customer("Hans", 
		                   new Address("Corel",  5641, "San Diego", "CA", "91004"));
		    
		    // register john
		    theStore.register(john);
		      System.out.println(john.getName()+" registers successfully.");


		    // register mary
		    theStore.register(mary);
		      System.out.println(mary.getName()+" registers successfully.");


		    // register hans
		      theStore.register(hans);
		      System.out.println(hans.getName()+" registers successfully.");


		    // register hans again
		      theStore.register(hans);
		      System.out.println(hans.getName()+" registers successfully.");


		    System.out.println("\nCUSTOMER LIST");
		    theStore.records.getHead().printAllCustomers();

		    System.out.println("\nremove Mary from the list");
		    theStore.remove(mary);

		    System.out.println("John made purchase.\n");
		    john.makePurchase(1000);

		    System.out.println("\nCUSTOMER LIST");
		    theStore.records.getHead().printAllCustomers();

		  }
		  
	  
	  }

	


	class  CustomerNode {

	  // an item in the link list 

	  private Customer item;      
	  private CustomerNode next;
	  
	  public Customer getItem() { 
		  return item;
	  }
	  public CustomerNode getNext() {
		  return next;
	  }
	  public boolean isEmpty() {
		    return item == null;
		  }

	  public void setItem(Customer customer) {
		  this.item=customer;
	  }
	  public void setNext(CustomerNode nextNode) {
		  this.next=nextNode;
	  }
	  public void printAllCustomers() {
		    if (!isEmpty()) {
		      System.out.println("==========================");
		      System.out.println(item);
		      if (next != null)
		        next.printAllCustomers();
		    }
		  }
	  public void removeCustomer(Customer customer) {
		    if (!isEmpty()) {
		      if (customer.equals(item)) {
		        if (next == null) {
		          item = null;
		        } else {
		          item = next.item;
		          next = next.next;
		        }
		      } else if (next != null) {
		        next.removeCustomer(customer); 
		      }
		    } 
	  }
	}


	class CustomerList {

	  private CustomerNode head;

	  // add any methods on demand
	  public boolean isEmpty() {
		    return head == null;
		  }
	  public CustomerNode getHead() {
		    return head;
		  }
	  public void setHead(CustomerNode head) {
		  this.head=head;
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
	  }
	  public Customer(String name) {
		     // a constructor to create a new customer.
			  this.name=name;
			  this.address=null;
		  }

	  public int getID() {
	     // return the id of this customer.
		  return accountID;
	  }

	  public String getName() {
	     // return the name of this customer.
		  return name;
	  }

	  public Address getAddress() {
	     // return the address of this customer.
		  return address;
	  }

	  protected Credit getCredit() {
	     // return the Credit of this customer.
		  return credit;
	  }

	  public void setPasswd(String pwd) {
	     // set a new password.
		  this.passwd=pwd;
	  }

	  public void setAddress(Address addr) {
	     // set a new address.
		  this.address=addr;
	  }

	  protected void setID(int i) {
	     // set the id to i
		  this.accountID=i;
	  }
	  
	  protected void setCredit(Credit credit) {
	     // set the credit to the input credit.
		  this.credit=credit;
	  }
	  
	  public boolean equals(Customer customer) {
	     // check whether this customer is same as customer.
		    return this.name.equals(customer.name) && 
		            this.address.equals(customer.address);
	  }

	  public boolean makePurchase(int amount) {
	     /* 
	         buy by debit.
	         return true if the debit of this customer is less than the
	           maximum limitation, otherwise return false.
	     */
		  if((this.credit).getDebit() < credit.MAX_AMOUNT) {
			  return true;
		  }
		  else return false;
	  }

	  public void makePayment(int amount) {
	     // buy by cash
		  (this.credit).addDebit(amount);
	  }

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
	  }

	  public String getStreetName() {
	     // get the street name
		  return streetName;
	  }

	  public int getStreetNumber() {
	    // get the street number
		  return streetNumber;
	  }

	  public String getCity() {
	    // get the city name
		  return city;
	  }

	  public String getState() {
	    // get the state name
		  return state;
	  }
	  
	  public String getZipCode() {
	    // get the zip code
		  return zipcode;
	  }
	 
	}


	// this class represents credit

	class  Credit {

	   final public static int MAX_AMOUNT = 5000; 
	   private int debit;

	   public int getDebit() {
	      // get the current debit.
		   return debit;
	   }
	   
	   protected void addDebit(int amount) {
	      // update the debit, also make sure not bigger than the limitation.
		   this.debit+=amount;
	   }
	}


