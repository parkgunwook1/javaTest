package solid.isp;

public class Telsa implements Car,ElectricCar{
    @Override
    public String drive() {
        return "drive";
    }

    @Override
    public String breakk() {
        return "breakk";
    }

    @Override
    public String autoDrive() {
        return "autoDrive";
    }

    @Override
    public String autoParking() {
        return "autoParking";
    }
}
