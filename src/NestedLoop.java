public class NestedLoop {
    public static void main(String[] args) {
        for(int i = 1; i<=2; i++){
            System.out.println("Outer " +i);

            for(int j=1; j<=3; j++){
                System.out.println("\t - Inner : "+j);
            }
        }
    }
}
/* Output:
Outer 1
	 - Inner : 1
	 - Inner : 2
	 - Inner : 3
Outer 2
	 - Inner : 1
	 - Inner : 2
	 - Inner : 3

 */