package solid.dip;

public class CarService {
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public String drive() {
        return car.drive();
    }

    public void print() {
        System.out.println(car.drive());
    }
}
