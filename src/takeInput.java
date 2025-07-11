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
        scanner.close();

//        System.out.println("Hi "+name);
//        System.out.println("You are "+age+" year old");

        if(!name.isEmpty()){
                    String formattedString = String.format("Hi %s. You are %d years old. Your cgpa is %.2f.",
                            name, age, cgpa);

                    System.out.println(formattedString);
                    System.out.println("Your name length is "+name.length()+" character long");
        }else{
            System.out.println("Please Enter Name");
        }

        System.out.println("In Lower case: "+name.toLowerCase());
        System.out.println("In upper case: "+name.toUpperCase());
    }
}
