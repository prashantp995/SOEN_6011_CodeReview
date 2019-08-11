import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    printWelcomeMessage();
    SinhFunction sinhFunction = new SinhFunction();
    try {
      sinhFunction.initCalculation();
      continueApplication(sinhFunction);
    } catch (Exception e) {
      System.out.println(UserInterfaceMessages.ERROR_MESSAGE_PROGRAM_STOPPED);
      System.out.println(e.getMessage());
    }

  }

  private static void continueApplication(SinhFunction sinhFunction) {
    while (true) {
      System.out.println("\n" + UserInterfaceMessages.DOTS + "\n"
          + UserInterfaceMessages.OPTIONS_MESSAGE);
      Scanner sc = new Scanner(System.in);
      if (sc.hasNextInt()) {
        int choice = sc.nextInt();
        if (choice == 1) {
          System.out.println(UserInterfaceMessages.INPUT_MESSAGE);
          sinhFunction.initCalculation();
        } else if (choice == 0) {
          System.exit(0);
        } else {
          System.out.println(UserInterfaceMessages.ERROR_INVALID_CHOICE);
        }
      } else {
        System.out.println(UserInterfaceMessages.ERROR_INVALID_CHOICE);
      }
    }
  }

  private static void printWelcomeMessage() {
    System.out.println(UserInterfaceMessages.DOTS);
    System.out.println(UserInterfaceMessages.WELCOME_MESSAGE);
    System.out.println(UserInterfaceMessages.DOTS);
    System.out.println(UserInterfaceMessages.INPUT_MESSAGE);
  }
}
