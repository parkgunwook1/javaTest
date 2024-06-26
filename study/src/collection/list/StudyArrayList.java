package collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class StudyArrayList {

	/* 컬렉션 프레임워크 
	 * 
	 * 컬렉션 프레임워크란 다수의 데이터를 쉽고 효과적으로 처리할 수 있는 표준화된 방법을 제공하는 클래스의 집합을 의미한다.
	 * 즉, 데이터를 저장하는 자료 구조와 데이터를 처리하는 알고리즘을 구조화하여 클래스로 구현해 놓은 것이다.
	 * */
	
	/* 컬렉션 프레임워크 주요 인터페이스 
	 * 
	 * 주요 인터페이스는 다음과 같다.
	 *  * List
	 *  * Set
	 *  * Map
	 *  
	 *  List와 Set 인터페이스는 모두 Collection 인터페이스를 상속받지만, 구조상의 차이로 인해 Map 인터페이스는 별도로 정의된다.
	 *
	   
	 * 주요 프레임워크 특징
	 * 
	 *   * List => 순서가 있는 데이터의 집합으로, 데이터의 중복을 허용함
	 *   			- vector, ArrayList, LinkedList, Stack, Queue
	 *   * Set => 순서가 없는 데이터의 집합으로, 데이터의 중복을 허용하지 않음
	 *   			- HashSet, TreeSet
	 *   * Map => 키와 값의 한쌍으로 이루어지는 데이터의 집합, 순서가 없다. 키는 중복을 허용하지 않지만, 값은 중복 허용
	 *   			- HashMap, TreeMap, Hashtable, Properties
	 * */
	public static void main(String[] args) {
		
		/* Collection 인터페이스 
		 * 
		 * List와 Set 인터페이스의 많은 공통된 부분을 Collection 인터페이스에서 정의하고, 두 인터페이스는 그것을 상속 받는다.
		 * 따라서 Collection 인터페이스는 컬렉션을 다루는데 가장 기본적인 동작들을 정의하고, 그것을 메소드로 제공하고 있다.
		 * */
		
		// List는 순서가 보장되고, 데이터 중복 허용하는 것을 확인할 수 있음
		ArrayList<Integer> arrList = new ArrayList<>();
		
		// 값 추가
		arrList.add(4);
		arrList.add(3);
		arrList.add(3);
		arrList.add(1);
		
		// size로 배열 길이 체크 후, get 메소드를 이용하여 요소 출력
		for(int i = 0; i < arrList.size(); i++) {
			System.out.println(arrList.get(i));
		}
				
		// remove 메소드를 이용한 요소의 제거
		arrList.remove(1);
		
		// Collections.sort() 메소드를 이용한 요소의 정렬
		Collections.sort(arrList);
		
		// iterator() 메소드와 get() 메소드를 이용한 요소의 출력
		Iterator<Integer> iter = arrList.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + "");
		}
		
		// set() 메소드를 이용한 요소의 변경
		arrList.set(0, 10);
		
		for (int e : arrList) {
			System.out.println(e + "");
		}
		System.out.println("리스트의 크기 : " + arrList.size());
	}
}
