package solid.srp.test2;

public class Employee {

    /*
    * 클래스나 메소드가 너무 많은 일을 담당하고 있다면, 그 기능을 수정하거나 변경하는 데 필요한 노력이 늘어나며,
    * 버그 발생 확률도 증가한다.
    * 하나의 클래스나 메소드가 수행하는 일을 한 가지로 제한함으로써 코드의 유지 관리를 용이하게 한다.
    *
    * 사용자 정보 처리
    *   사용자 정보를 데이터베이스에서 가져오는 메소드와 사용자 정보를 화면에 보여준는 메소드가 하나의 클래스에 포함
    *   되어 있다면, 이는 단일 책임 원칙을 위반한 것이다.
    *   데이터베이스에서 정보를 가져오는 기능과 화면에 정보를 보여주는 기능은 서로 다른 책임을 가지므로 이를 분리하는 것이
    *   유지보수 관점에서 바람직하다.
    *
    *   SRP를 준수하면 한 기능에 대한 변경이 다른 기능에 영향을 미치는 일이 줄어들게 된다.
    *   이로써 코드의 가독성과 유지 보수성이 향상되며, 테스트하기도 더 쉬어진다.
    * */

    // 아래에서 정보를 가져오는 클래스와, 직원 정보를 화면에 출력하는 코드를 분리해서 설계했습니다.
    // 이렇게 클래스를 분리함으로써 각 클래스는 하나의 책임만을 가지게 되고, 단일 책임 원칙을 준수하게 된다.
    private String employeeId;
    private String name;
    private int age;

    public void getEmployeeDetails() {
        // 데이터베이스에서 직원 정보를 가져오는 코드
    }
}
class EmployeePrinter {
    public void printEmployeeDetails(Employee employee) {
        // 직원 정보를 화면에 출력하는 코드
    }
}
