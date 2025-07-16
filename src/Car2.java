public class Car2 {
    String color;
    int speed;

    public Car2(String color, int speed){
        this.color = color; //this.color refers to the class filed
        this.speed = speed;
    }

    public void displayInfo(){
        System.out.println("Color: "+ this.color);
        System.out.println("Speed: "+ speed);
    }







    public static void main(String[] args) {
        Car a = new Car();
        a.fullThrottle();
        a.speed(400);

        Car2 c2 = new Car2("Red", 500);
        c2.displayInfo();

    }
}
