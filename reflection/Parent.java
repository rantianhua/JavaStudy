package reflection;

public abstract class Parent {
	//姓名
	public String mName;
	//性别
	public String mSex;
	
	public Parent (String name) {
		this("男",name);
	}
	
	public Parent (String sex,String name) {
		mSex = sex;
		mName = name;
	}
	
	abstract void greet();

	abstract void introduce();
	
	public void parentMethod() {
		System.out.println("this is a method in parent class");
	}

}
