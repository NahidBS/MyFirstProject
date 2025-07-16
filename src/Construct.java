

public class Construct  {
    int modelYear;
    String modelName;

    public Construct (int year, String name){
        modelYear = year;
        modelName = name;
    }

    public static void main(String[] args) {
        Construct  c1 = new Construct (2020, "Mustang");
        System.out.println(c1.modelYear + " " + c1.modelName);

        Pen pen1 = new Pen();
        pen1.color = "Red";
        pen1.details();
        pen1.type = "Gel";

        pen1.write();
        pen1.details();

        Pen pen2 = new Pen();
        pen2.show();
    }
}

class Pen{
    String color;
    String type;

    public Pen(){
        color = "blue";
        type = "red";
    }

    public void write(){
        System.out.println("Writing..........");
    }

    public void show(){
        System.out.println("color: "+color  + ", Pen type: " +type);
    }

    public void details(){
        System.out.println("Pen Color: "+color+ ", Pen type: " +type);
    }
}
