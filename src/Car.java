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


