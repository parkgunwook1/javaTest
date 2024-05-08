package extendss;

class Parent {
	private int a = 10;
	private int b = 20;
	
	
	// 부모 클래스의 fild가 private 임으로 pulic으로 get 메소드를 뽑아줘야 접근 가능하다.
	// 만약 아래의 public 메소드가 없이 접근하려면 필드가 public 이어야함.
	public int getA() {
		return a;
	}
	public int getB() {
		return b;
	}
	
}
class Child extends Parent {
	Parent p = new Parent();
	
	private void display() {
	int a = p.getA();
	int b = p.getB();
	System.out.println(a + b);
	}
public static void main(String[] args) {
    Child ch = new Child();
    ch.display();
    
	/* Object 클래스 
	 * 
	 * 자바에서 Object 클래스는 모든 클래스의 부모 클래스가 되는 클래스이다.
	 * 따라서 자바의 모든 클래스는 자동으로 Object 클래스의 모든 필드와 메소드를 상속받게 된다.
	 * 
	 * 즉, 자바의 모든 클래스는 별도로 extends 키워드를 사용하여 Object 클래스의 상속을 명시 하지 않아도
	 * Object 클래스의 모든 멤버를 자유롭게 사용할 수 있다.
	 * 
	 * 그래서 자바의 모든 객체에서 toString() 이나 clone() 같은 메소드를 바로 사용할 수 있는 이유가
	 * 해당 메소드들이 Object 클래스의 메소드이기 때문이다.
	 * 
	 * java.lang 패키지
	 * => 자바에서 가장 기본적인 동작을 수행하는 클래스들의 집합이다.
	 * 	  따라서 자바에서는 java.lang 패키지의 클래스들은 import 문을 사용하지 않아도 클래스 이름만으로 바로 사용할 수 있도록 하고있다.
	 * */

}

}
