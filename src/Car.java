import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

    public int compareTo(Car other) {
        if (this.year.compareTo(other.year)!=0){
            return -1;
        }
        if (this.make.compareTo(other.make)!=0){
            return -1;
        }
        return this.model.compareTo(other.model);
    }
}


