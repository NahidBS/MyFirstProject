public class ForEach {
    public static void main(String[] args) {
        String[] cars = {"Volvo", "BMW", "Ford"};
        for(String i: cars){
            System.out.println(i);
        }

        String[] name = {"Nahid", "Fahim", "MD. Fahim Bin Imam Nahid"};
        for(String i: name){
            System.out.println(i);
        }
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                continue;
            }
            System.out.println(i);
        }

    }
}
