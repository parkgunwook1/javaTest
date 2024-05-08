package exception;

import java.io.BufferedReader;
import java.io.FileReader;

public class StudyThrowale {
	
	public static void main(String[] args) {
		/* Throwable 클래스
		 * 
		 * Throwable 클래스는 모든 예외의 조상이 되는 Exception 클래스와 모든 오류의 조상이 되는 Error 클래스의 부모 클래스이다.
		 * Throwable 타입과 이 클래스를 상속받은 서브 타입만이 자바 가상 머신이나 throw 키워드에 의해 던져질 수 있다.
		 * 
		 * 이 클래스에는 예외나 오류에 관한 다양한 정보를 확인할 수 있는 메소드가 포함되어 있다.
		 * - String getMessage() => 해당 throwable 객체에 대한 자세한 내용을 문자열로 반환
		 * - void printStakTrace() => 해당 throwable 객체와 오류 스트림에서 해당 객체의 스택 트레이스를 출력함
		 * - String toString() => 해당 throwable 객체에 대한 간략한 내용을 문자열로 반환함
		 * */
		
		/* 오류(error)와 예외(exception)
		 * 
		 * 오류 : 시스템 레벨에서 프로그램에 심각한 문제를 야기하여 실행중인 프로그램을 종료시킨다.
		 * 		이러한 오류는 개발자가 미리 예측하여 처리할 수 없는 것이 대부분이므로, 오류에 대한 처리는 할 수 없다.
		 * 
		 * 예외 : 오류와 마찬가지로 실행 중인 프로그램을 비정상적으로 종료시키지만, 발생할 수 있는 상황을 미리 예측하여 처리할 수 있다.
		 * 		따라서 개발자는 예외 처리를 통해 예외 상황을 처리할 수 있도록 코드의 흐름을 바꿀 수 있다.
		 *  */
		
		/* try/catch/finally 문
		 * 
		 * try {
		 * 		try => 예외를 처리하길 원하는 실행 코드
		 * }catch(el) {
		 * 		catch => el 예외가 발생할 경우에 실행될 코드
		 * 				- try와 가장 가까운 catch 부터 아래로 차례대로 실행함
		 * } finally {
		 * 	    finally = > 예외 발생 여부와 상관없이 무조건 실행될 코드
 		 * 
		 * */
		
		/* 예외 발생시키기 
		 * 
		 * 자바에서는 throw 키워드를 사용하여 강제로 예외를 발생시킬 수 있다.
		 * 메소드 선언부에 throws 키워드를 사용하여 해당 메소드를 사용할 때 발생할 수 있는 예외를 미리 명시할 수 있다.
		 * 이렇게 하면 해당 메소드를 사용할 때 발생할 수 있는 예외를 사용자가 충분히 인지할 수 있으며, 그에 대한 처리까지도 강제할 수 있다.
		 * 
		 * 자바에서는 Exception 클래스를 상속받아 자신만의 새로운 예외 클래스를 정의하여 사용할 수 있다.
		 * 사용자 정의 예외 클래스에는 생성자 뿐만 아니라 필드 및 메소드도 원하는 만큼 추가할 수 있다.
		 * 요즘에는 Exception 클래스가 아닌 예외 처리를 강제하지 않는 RuntimeException 클래스를 상속받아 작성하는 경우가 많다.
		 * */
		
		
		
		/* try-with-resources 문을 사용하면 다음과 같이 자동으로 파일의 닫기를 수행할 수 있다.
		 * 
		 * static String readFile(String filePath) throws IOException { try
		 * (BufferedReader br = new BufferedReader(new FileReader(filePath))) { return
		 * br.readLine(); } }
		 */
	}
}
