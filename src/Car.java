public class Car {
    public void fullThrottle(){
        System.out.println("AS fast as it can!");
    }
    public void speed(int maxSpeed){
        System.out.println("Max speed is: "+maxSpeed);
    }

    public static void main(String[] args) {
        Car myCar = new Car();
        Car car1 = new Car();

        myCar.fullThrottle();
        myCar.speed(500);

        car1.fullThrottle();
        car1.speed(800);
    }
}
