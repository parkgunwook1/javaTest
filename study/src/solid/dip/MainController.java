package solid.dip;

public class MainController {
    public static void main(String[] args) {
        CarService carService = new CarService();
        Car truck = new Truck();
        Car van = new Van();

        carService.setCar(truck);
        carService.print();

        carService.setCar(van);
        carService.print();
    }
}
