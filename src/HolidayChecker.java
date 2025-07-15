import java.util.Scanner;

public class HolidayChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int day;
        do{
            System.out.println("***********************************************************************");
            System.out.print("Print the number of the day(1-7) to know if it is holiday(0 for exit): ");
            day = scanner.nextInt();
            switch (day){
                case 1:
                    System.out.println("Monday");
                    break;
                case 2:
                    System.out.println("Tuesday");
                    break;
                case 3:
                    System.out.println("Wednesday");
                    break;
                case 4:
                    System.out.println("Thursday");
                    break;
                case 5:
                    System.out.println("Friday");
                    break;
                case 6:
                    System.out.println("Saturday - Holiday");
                    break;
                case 7:
                    System.out.println("Sunday Holiday");
                    break;
                default:
                    System.out.println("Incorrect Day");
            }
        }while(day != 0);
    }
}
