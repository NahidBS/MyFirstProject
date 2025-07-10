import java.util.Scanner;

public class takeInput {
    public static void main(String[] arg){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Hi "+name);
    }
}
