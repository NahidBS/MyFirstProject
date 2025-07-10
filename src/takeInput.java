import java.util.Scanner;

public class takeInput {
    public static void main(String[] arg){

        Scanner scanner = new Scanner(System.in);

        //string input
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        //int input
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.println("Hi "+name);
        System.out.println("You are "+age+" year old");


    }
}
