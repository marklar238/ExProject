
public class CarDriver {
    public static void main(String[] args){
        Car firstCar = new Car("1990", "infiniti", "M30");
        Car secondCar = new Car("2005", "Honda", "Civic");
        Car.serializeAsCsv(firstCar, "cars.txt");
        Car firstCarClone = Car.deserializeFromCsv("cars.txt");
        if (firstCarClone != null) {
            System.out.println("firstCar = firstCarClone: " + (firstCar.compareTo(firstCarClone) == 0));
        }
    }
}

