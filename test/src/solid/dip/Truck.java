package solid.dip;

public class Truck implements Car{
    @Override
    public String drive() {
        return "Truck";
    }
}
