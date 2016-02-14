package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Reflection reflection = new Reflection();
		Class childClass = reflection.getClassObject();
		Child child = (Child)reflection.getClassInstance(childClass);
		reflection.getDeclaredMethods(child);
		reflection.getMethods(child);
		reflection.getCurrentClassFields(child);
		reflection.getAllClassFields(child);
		reflection.getParentClass(child);
		reflection.getInterface(child);
	}
	
	/**
	 * 获取class对象
	 */
	public Class getClassObject() {
		System.out.println("\n==============getClassObject start==============" );
		//方法一：
		Class<?>  childClass = Child.class;
		System.out.println("get class object by class name : " + childClass.getName());
		//方法二：
		try {
			childClass = Class.forName("reflection.Child");
			System.out.println("get class object by the whole path : " + childClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//方法三：
		Child child = new Child("rth");
		childClass = child.getClass();
		System.out.println("get class object by the known object : " + childClass.getName());
		System.out.println("==============getClassObject end==============\n" );
		return childClass;
	}
	
	/**
	 * 通过class对象构造目标类型的对象
	 * @param mClass
	 * @return
	 */
	public Object getClassInstance(Class<?> mClass) {
		System.out.println("\n==============getClassInstance start==============" );
		Object ob = null;
		try {
			//通过class 对象获取构造器对象
			Constructor<?> constructor =  mClass.getConstructor(String.class,String.class,String.class,String.class);
			//设置constructor的Accessible
			constructor.setAccessible(true);
			//通过constructor对象创建Child对象
			ob = constructor.newInstance("男","rth","coder","Google");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("==============getClassInstance end==============\n" );
		return ob;
	}
	
	/**
	 * 通过反射获取指定对象中的方法
	 * @param child
	 */
	public void getDeclaredMethods(Child child) {
		System.out.println("\n==============getDeclaredMethods start==============" );
		//获取该类中所有的方法(public,private,protected,default)，但不包括从父类继承的方法
		Method[] methods = child.getClass().getDeclaredMethods();
		for(Method method : methods) {
			System.out.println("declared method name : " + method.getName());
		}
		try {
			//获取该类中特定的方法，与方法的可见性无关
			Method setWorkAndCompanyMethod = child.getClass().getDeclaredMethod("setWorkAndCompany"
					, String.class,String.class );
			//判断该方法是不是私有的
			if(Modifier.isPrivate(setWorkAndCompanyMethod.getModifiers())) {
				System.out.println(setWorkAndCompanyMethod.getName() + " is private");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("==============getDeclaredMethods end==============\n" );
	}

	/**
	 * 获取当前类以及父类中的所有public方法
	 * @param child
	 */
	public void getMethods(Child child) {
		System.out.println("\n==============getMethods start==============" );
		//获取所有public方法
		Method[] methods = child.getClass().getMethods();
		for(Method method : methods) {
			System.out.println("method name : " + method.getName());
		}
		//获取制定的方法
		try {
			//只能获取共有方法，私有方法会抛出异常
			Method studyMethod = child.getClass().getMethod("study");
			//调用该public方法
			studyMethod.invoke(child);
			//尝试获取一个私有方法，将会抛出NoSuchMethodException错误
			Method setWorkAndCompanyMethod = child.getClass().getMethod("setWorkAndCompany"
					, String.class,String.class );
		} catch (Exception e) {
			System.out.println("getMethods出错："+e.getMessage());
		}
		System.out.println("==============getMethods end==============\n" );
	}
	
	/**
	 * 获取当前类中定义的属性
	 * @param child
	 */
	public void getCurrentClassFields(Child child) {
		System.out.println("\n==============getCurrentClassFields start==============" );
		//获取当前类中所有属性，（public、private、protected）
		Field[] fields = child.getClass().getDeclaredFields();
		for(Field field : fields) {
			System.out.println("filed's name is : " + field.getName());
		}
		//获取指定的属性，与可见性无关
		try {
			Field mWork = child.getClass().getDeclaredField("mWork");
			//判断属性的可见性
			if(Modifier.isPrivate(mWork.getModifiers())) {
				System.out.println(mWork.getName() + " is private");
			}
		} catch (NoSuchFieldException | SecurityException|IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("==============getCurrentClassFields end==============\n" );
	}
	
	/**
	 * 获取当前类和父类中所有的公有属性
	 * @param child
	 */
	public void getAllClassFields(Child child) {
		System.out.println("\n==============getAllClassFields start==============" );
		//获取当前类和父类所有的公共属性
		Field[] publicFields = child.getClass().getFields();
		for(Field field : publicFields) {
			System.out.println("public field name : " + field.getName());
		}
		//获取当前类和父类中指定的公共属性
		try {
			Field ageField = child.getClass().getField("age");
			//设置年龄
			ageField.setInt(child, 21);
			//打印年龄
			System.out.println("My age is " + ageField.getInt(child));
		} catch (NoSuchFieldException | SecurityException|IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("==============getAllClassFields end==============\n" );
	}
	
	/**
	 * 获取父类
	 * @param child
	 */
	public void getParentClass(Child child) {
		System.out.println("\n==============getParentClass start==============" );
		Class<?> parentClass = child.getClass().getSuperclass();
		while(parentClass != null) {
			System.out.println("Child has a  super class is : " + parentClass.getName());
			parentClass = parentClass.getSuperclass();
		}
		System.out.println("==============getParentClass end==============\n" );
	}
	
	/**
	 * 获取当前类的接口
	 * @param child
	 */
	public void getInterface(Child child) {
		System.out.println("\n==============getInterface start==============" );
		Class<?>[] interfaces = child.getClass().getInterfaces();
		for(Class<?> inf : interfaces) {
			System.out.println("child has implements a interface : " + inf.getName());
		}
		System.out.println("==============getInterface end==============\n" );
	}
	
	
	}
