import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
  public static void main(String[] args) throws IncorrectBaseException {
    Scanner scanner = new Scanner(System.in);

    BaseConverter bc = start(scanner);

    while(true){
      System.out.print("\nVal: ");
      String val = scanner.next();

      switch(val){
        case "/help":
          outputHelp();
          break;
        case "/reverse":
          bc.reverse();
          break;
        case "/restart":
          System.out.println("Restarted.\n");
          bc = start(scanner);
          break;
        case "/exit":
          System.exit(0);
          break;
        default:
          try{
            System.out.println(bc.convert(val));
          }catch(IncorrectBaseException e){
            System.out.println(e.getMessage());
          }
      }
    }
  }

  static BaseConverter start(Scanner scanner) throws IncorrectBaseException {
    int from = askForInt("From Base", scanner);
    int to = askForInt("To Base", scanner);

    System.out.println("\nType /help to show all commands.");

    return new BaseConverter(from, to);
  }

  static void outputHelp(){
    System.out.print(
      "\n" +
      "/help          show all commands\n" +
      "/reverse       switches the 'to' and 'from' base\n" +
      "/restart       restarts the program\n" +
      "/exit          exits the program\n"
    );
  }

  static int askForInt(String prompt, Scanner scanner) {
    int val = -1;

    while(val <= 0){
      try{
        System.out.printf("%s: ", prompt);
        val = scanner.nextInt();

        if(val <= 0)
          System.out.println("Integer cant be less than or equal to 0!");

      }catch(InputMismatchException e){
        System.out.println("Not a valid integer!");
        scanner.next();
      }
    }

    return val;
  }
}