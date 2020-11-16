
import java.util.Scanner;


    /**
     * Created 2/9/18.
     */
    public class Console {

        public void print(String output, Object... args) {
            System.out.printf(output, args);
        }

        public void println(String output, Object... args) {
            print(output + "\n", args);
        }

        public String getStringInput(String prompt) {
            Scanner scanner = new Scanner(System.in);
            this.println(prompt);
            String userInput = scanner.nextLine();
            return userInput;
        }

        public Integer getIntegerInput(String prompt) {
            return getDoubleInput(prompt).intValue();  //take the Double value, convert to an int, and return int
        }

        public Double getDoubleInput(String prompt) {
            Scanner scanner = new Scanner(System.in);
            println(prompt);
            while (!scanner.hasNextDouble()){
                scanner.next();
                System.out.println("Please enter a valid price. You do not need to include $.");
            }
            double userInput = scanner.nextDouble();
            return userInput;
        }

        public void notLoggedInError(){
            print("You are not logged in to an account. Please log in to continue.");
        }
    }
