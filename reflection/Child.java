package reflection;

public class Child extends Parent implements Study{
	
	private String mWork;
	private String mCompany;
	
	public int age;

	public Child(String name) {
		super(name);
	}
	
	public Child(String sex,String name) {
		super(sex,name);
	}
	
	public Child(String sex,String name,String work,String company) {
		super(sex,name);
		mWork = work;
		mCompany  = company;
	}

	private void setWorkAndCompany(String work,String company) {
		mWork = work;
		mCompany = company;
	}
	
	public String toString() {
		return "My name is " + mName + " and my work is " + mWork + " and I am working at " + mCompany;
	}
	
	public void study() {
		System.out.println("I am studying English.");
	}

	void greet() {
		// TODO Auto-generated method stub
		System.out.println("Hello, nice to meet you!");
	}

	void introduce() {
		// TODO Auto-generated method stub
		System.out.println("My name is " + mName);
	}
	
	
 	
}
