import java.util.Scanner;

public class Calculator {
    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 1st Number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter 2nd Number: ");
        double num2 = scanner.nextDouble();

        scanner.nextLine(); // Consume leftover newline

        System.out.print("What operation do you want to perform? ");
        String operation = scanner.nextLine();

        if(operation.equals("sum")){
            System.out.printf("%.2f + %.2f = %.2f", num1, num2, num1 + num2);
        }
        scanner.close();
    }
}
