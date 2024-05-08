package alone.study.one;

// 의존성 주입, 지역 변수, 인스턴스 변수, 스태틱 변수
// 인스턴스 메서드, 스태틱 메서드
// 오버로딩

// 의존성 주입을 받을 객체
class MyDependency {
	int kind; // -> 인스턴스 변수 : 여러 개의 값 가질 수 있음.
	static int width = 100; // -> 클래스 변수 : 동일한 값만 가짐, 모든 인스턴스가 하나의 저장공간 공유

	public MyDependency() {

	}

	public MyDependency(int kind, int width) {
		this.kind = kind; // this -> 사용 이유는 인스턴스 변수와 지역변수 구분
		this.width = width;
	}

	public void doSomething() {
		int a = 10; // 지역변수 -> 메서드 내부에서 선언 : 휘발됨
		System.out.println("MyDependency is doing somthing");
		System.out.println(kind);
		System.out.println(width);
	}

	// static 메소드에는 인스턴스 변수 불가, static 변수만 참조 가능
	public static void staticMethod() {
//		System.out.println(kind); => static 메서드에 인스턴스 변수 접근 불가.
		System.out.println(width);
	}
}

// 의존성을 주입받아 사용하는 클래스
class MyClass {
	private MyDependency myDependency;

	public MyClass(MyDependency myDependency) {
		this.myDependency = myDependency;
	}

	public void useDependency() {
		myDependency.doSomething();
	}
}

class StaticMyClass {

	public StaticMyClass() {
	}

	public static void staticMethod() {
		System.out.println("스태틱 메소드는 바로 접근 가능");
	}
}

class InstanceClass {

	public void InstanceMethod() {
		System.out.println("인스턴스 메소드");
	}
}

public class AutowiredSpringModule {
	public static void main(String[] args) {

		// 의존성 주입 받을 객체
		MyDependency dependency = new MyDependency();

		// 의존성 주입 받아 사용하는 클래스
		MyClass myClass = new MyClass(dependency);

		// 의존성 주입 받은 객체 사용
		myClass.useDependency();

		StaticMyClass.staticMethod();
//		InstanceClass.InstanceMethod(); => 접근 불가능 static 메모리에 인스턴스 메소드는 안올라옴

		// 인스턴스 메소드는 아래와 같이 접근 가능
		InstanceClass instance = new InstanceClass();
		instance.InstanceMethod();

		int s = MyDependency.width; // -> static 변수 -> 클래스.변수 접근 가능
//		int a = MyDependency.kind; => 인스턴스 변수 접근 불가.
	}
}

class OverrodeClass {

	// 오버로딩은 매개변수 이름이 다른 것은 오버로딩 아님, 리턴타입은 오버로딩과 무관하다.

	// 매개변수의 타입이 다르므로 오버로딩 성립함.
	long add(int a, long b) {
		return a + b;
	}

	long add(long a, int b) {
		return a + b;
	}

	// 아래 메소드명은 동일하고, 매개변수는 다르지만 같은 의미의 기능 수행 O
	int add(int a, int b) {
		return a + b;
	}

	long add(long a, long b) {
		return a + b;
	}

	int add(int[] a) {
		int result = 0;

		for (int i = 0; i < a.length; i++) {
			result += a[i];
		}
		return result;
	}
}
