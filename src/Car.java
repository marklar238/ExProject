import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class Car implements Comparable<Car> {
    private String make;
    private String model;
    private String year;

    //constructor for car
    public Car(String year, String make, String model){
        this.year = year;
        this.make = make;
        this.model = model;
    }

    //getters for Car
    public String getMake(){
        return make;
    }

    public String getModel(){
        return model;
    }

    public String getYear() {
        return year;
    }

    //setters for Car
    public void setMake(String make){
        this.make = make;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setYear(String year){
        this.year = year;
    }

    public static void serializeAsCsv(Car car, String fileName){
        String carAttributes = prePrintCsv(car);
        System.out.println(carAttributes);

        Path desktopPath = Paths.get(System.getProperty("user.home"), "Desktop");
        Path filePath = desktopPath.resolve(fileName);

        try {
            Files.write(filePath, carAttributes.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String prePrintCsv(Car car){
        return car.getYear()+","+car.getMake()+","+car.getModel();
    }

    public static Car deserializeFromCsv(String fileName) {
        Path desktopPath = Paths.get(System.getProperty("user.home"), "Desktop");
        Path filePath = desktopPath.resolve(fileName);

        try {
            List<String> lines = Files.readAllLines(filePath);
            if (lines.size() != 1) {
                throw new IllegalArgumentException("Invalid data format");
            }

            String[] carAttributes = lines.get(0).split(",");

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(make, car.make) && Objects.equals(model, car.model) && Objects.equals(year, car.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year);
    }

    public int compareTo(Car other) {
        return this.year.compareTo(other.year);
    }
}


