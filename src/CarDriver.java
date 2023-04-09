import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;



public class CarDriver {
    public static void main(String[] args){
        Car firstCar = new Car("1990", "infiniti", "M30");
        Car secondCar = new Car("2005", "Honda", "Civic");
        serializeAsCsv(firstCar, "cars.txt");
        Car firstCarClone = deserializeFromCsv("cars.txt");
        if (firstCarClone != null) {
            System.out.println("firstCar = firstCarClone: " + (firstCar.compareTo(firstCarClone) == 0));
        }
    }

    public static void serializeAsCsv(Car car, String fileName){
        String carAttributes = prePrintCsv(car);
        System.out.println(carAttributes);

        Path desktopPath = Paths.get(System.getProperty("user.home"), "Desktop");
        Path filePath = desktopPath.resolve(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(carAttributes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String prePrintCsv(Car car){
        return car.getYear()+","+car.getMake()+","+car.getModel();
    }

    public static Car deserializeFromCsv(String fileName){
        Path desktopPath = Paths.get(System.getProperty("user.home"), "Desktop");
        Path filePath = desktopPath.resolve(fileName);

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String carData = reader.readLine();
            String[] carAttributes = carData.split(",");

            if (carAttributes.length != 3) {
                throw new IllegalArgumentException("Invalid data format");
            }

            String year = carAttributes[0].trim();
            String make = carAttributes[1].trim();
            String model = carAttributes[2].trim();

            return new Car(year, make, model);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

