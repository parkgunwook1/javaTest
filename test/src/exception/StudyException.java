package exception;

import java.io.IOException;

public class StudyException {

	/* 예외 클래스
	 * 자바에서 모든 예외의 조상 클래스가 되는 Exception 클래스는 크게 다음과 같이 구분할 수 있다.
	 *  * 1. RuntimeException 클래스
	 *  * 2. 그 외의 Exception 클래스의 자식 클래스
	 *  
	 *  "RuntimeException" 클래스를 상속받는 자식 클래스들은 주로 치명적인 예외 상황을 발생시키지 않는 예외들로 구성된다.
	 *  따라서 try / catch 문을 사용하기보다는 프로그램을 작성하면서 예외가 발생하지 않도록 주의를 기울이는 편이 좋다.
	 *  
	 *  하지만 그 외의 "Exception" 클래스에 속하는 자식 클래스들은 치명적인 예외 상황을 발생시키므로, 반드시 try/catch 문을 사용하여
	 *  예외를 처리해야만 한다.
	 *  따라서 자바 컴파일러는 RuntimeException 클래스 이외의 Exception 클래스의 자식 클래스에 속하는 예외가 발생할 가능성이 있는 구문은 
	 *  예외 처리하도록 강제하고 있다.
	 *  
	 *  만약 이러한 예외가 발생할 가능성이 있는 구문을 예외 처리하지 않았을 때는 컴파일 시 오류를 발생한다.
	 * */
	
	// 아래는 IOException 임으로 try/catch 문을 사용해서 예외 처리를 해줘야한다.
	// 예외 처리를 안해주면 컴파일 오류난다.
	public static void main(String[] args) {
	    byte[] list = {'a', 'b', 'c'};
	    
	    // try 블록과 가장 가까운 catch 블록부터 순서대로 검사한다.
	    // 따라서 여러 개의 catch 블록을 사용할 때는 Exception 클래스의 계층 관계에도 주의를 기울여야 한다.
	    try {
	        System.out.write(list);
	        // Exception 자식 exception 들이 먼저 앞에 나와서 예외 처리를 해주어야한다.
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

