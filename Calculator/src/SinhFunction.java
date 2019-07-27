import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This class  implements function sinh , please refer readme file for the details about the
 * function
 *
 * @author :prashantkumar patel
 */
class SinhFunction {

  static int maxSteps = 15;
  private static final double divisor = 2d;

  /**
   * This function calculate e power x based on Taylor series, First 15 steps of Taylor series will
   * be considered to calculate e power x with good accuracy
   *
   * @param x real number provided by user
   * @return calculated value of e power x
   */
  double ePowerX(double x) {
    double result = 1;

    for (int i = SinhFunction.maxSteps; i > 0; --i) {
      result = 1 + x * result / i;
    }

    return result;
  }

  void InitCalculation() {
    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextDouble()) {
      double x = scanner.nextDouble();
      if (ValidationUtil.validateInputRange(x)) {
        //As per assumption consider only first 15 decimal points from input
        DecimalFormat df = new DecimalFormat(".###############");
        x = Double.parseDouble(df.format(x));
        double ePowerx = ePowerX(x);
        double ePowerMinusx = ePowerX(-x);
        validateAndCalculateSinhx(x, ePowerx, ePowerMinusx);
      } else {
        System.out.println(UIMessages.ERROR_MESSAGE_RANGE);
      }

    } else {
      System.out.println(UIMessages.ERROR_MESSAGE);
    }

  }

  private void validateAndCalculateSinhx(double x, double ePowerx, double ePowerMinusx) {
    if (ValidationUtil.validateOutputRange(ePowerx) && ValidationUtil
        .validateOutputRange(ePowerMinusx)) {
      double result = calculateSinh(ePowerx, ePowerMinusx);
      System.out.print(UIMessages.SUCCESS_MESSAGE + x + " is ");
      System.out.printf("%.15f", result);
    }
  }


  /**
   * This function calculates sinh(x) based on formula , sinh(x) = (e^x - e^-x)/2
   *
   * @return result of sinh based on input
   */
  double calculateSinh(double ePowerx, double ePowerMinusx) {
    return (ePowerx - ePowerMinusx) / divisor;
  }


}
