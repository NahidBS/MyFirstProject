public class Array {
    public static void main(String[] args) {
        String[] cars = {"VOlVO" +
                "", "CO" ,"DO"};

        System.out.println(cars[0]);
        System.out.println();
        for(String i : cars){
            System.out.println(i);
        }
        System.out.println();

        for(int j=0; j < cars.length; j++){
            System.out.println(cars[j]);
        }
    }

}
