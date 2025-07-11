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

        System.out.print("Enter your Cgpa: ");
        float cgpa = scanner.nextFloat();

//        System.out.println("Hi "+name);
//        System.out.println("You are "+age+" year old");
        String formattedString = String.format("Hi %s. You are %d years old. Your cgpa is %f.",
                name, age, cgpa);

        System.out.println(formattedString);
        System.out.println("Your name length is "+name.length()+" character long");

    }
}
