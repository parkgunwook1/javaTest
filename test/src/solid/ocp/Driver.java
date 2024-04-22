package solid.ocp;

public class Driver {

    /**
     * 개방-폐쇄 원칙 OCP
     * 개방 폐쇄 원칙이란 객체를 다룸에 있어서 객체의 확장은 개방적으로, 객체의 수정은 폐쇄적으로 대하는 원칙이다.
     * 쉽게 말하자면, 기능이 변하거나 확장 가능하지만, 해당 기능의 코드는 수정하면 안 된다는 뜻이다.
     *
    * */

    public static void main(String[] args) {

        Car[] driver = new Car[2];
        driver[0] = new Truck();
        driver[1] = new Bus();

        for (int i = 0; i < driver.length; i++) {
            driver[i].drive();
        }
    }
}

class Car {
    public String carType = "";

    public void car(String carType) {
        this.carType = carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
    public void drive() {
        System.out.println(carType + "Drive");
    }
}

class Truck extends Car {
    public Truck() {
        setCarType("Truck");
    }
    @Override
    public void drive() {
        super.drive();
    }
}

class Bus extends Car {
    public Bus() {
        setCarType("Bus");
    }
    @Override
    public void drive() {
        super.drive();
    }
}
