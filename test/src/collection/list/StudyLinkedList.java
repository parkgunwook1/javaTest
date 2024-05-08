package collection.list;

import java.util.LinkedList;

public class StudyLinkedList {
	
	public static void main(String[] args) {
		/* LinkedList 클래스 
		 * 
		 * LinkedList 클래스는 ArrayList 클래스가 배열을 이용하여 요소를 저장함으로써 발생하는 단점을 극복하기 위해 고민했다.
		 * LinkedList 클래스는 내부적으로 연결 리스트를 이용하여 요소를 저장한다.
		 * 
		 * 배열은 저장된 요소가 순차적으로 저장된다.
		 * 하지만 연결 리스트는 저장된 요소가 비순차적으로 분포되며, 이러한 요소들 사이를 링크로 연결하여 구성한다.
		 * 다음 요소를 가리키는 참조만을 가지는 연결 리스트를 단일 연결 리스트라고 한다.
		 * 
		 * "단일 연결 리스트"
		 * 	- 요소의 저장과 삭제 작업이 다음 요소를 가리키는 참조만 변경하면 되므로, 아주 빠르게 처리할 수 있다.
		 *    하지만 단일 연결 리스트는 현재 요소에서 이전 요소로 접근하기가 매우 어렵다.
		 *    
		 *  따라서 이전 요소를 가리키는 참조도 가지는 이중 연결 리스트가 좀 더 많이 사용된다.
		 * "이중 연결 리스트"
		 * 	- 서로 화살표를 이중으로 가리키기 때문에 이전 요소를 찾을 때 단일 연결 리스트보다 수월하다. 
		 * 
		 * LinkedList 클래스 역시 List 인터페이스를 구현하므로, ArrayList 클래스와 사용할 수 있는 메소드가 거의 똑같다.
		 * */
		
		LinkedList<Integer> linList = new LinkedList<>();
		
		// add 메소드를 이용한 요소의 저장
		linList.add(4);
		linList.add(2);
		linList.add(3);
		linList.add(1);
		
		// for문과 get 메소드를 이용한 요소의 출력
		for (int i = 0; i < linList.size(); i++) {
			System.out.println(linList.get(i));
		}
		
		// remove 메소드를 이용한 요소의 제거
		linList.remove(1);	
		
		// 향상된 포문
		for (Integer e : linList) {
			System.out.println(e);
		}
		
		linList.set(2, 7);
		
		for (Integer e : linList) {
			System.out.println(e);
		}
		
		// size 메소드를 이용한 요소의 총 개수
		System.out.println("리스트의 크기 : " + linList.size());
	}

}
