package solid.lsp;

public class Whale implements Animal {

    // 상위 클래스에서 동물이라면 해야하는 부분을 지정해주고 그 동물에 속하는 종들은 해당 행동(메서드) 들을 반드시 이행 해야한다.
    @Override
    public void eat(String food) {
        System.out.println(food + "을/를 먹다.");
    }

    @Override
    public void sleep() {
        System.out.println("잔다.");
    }
}
