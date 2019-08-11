import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SinhFunctionTest {

  private final ByteArrayOutputStream outData = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outData));
    SinhFunction.maxSteps = 20;
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  /**
   * Test for : EF_F3_D1_REQ-2 This test verifies if user inputs invalid value (alphabet/special
   * characters) then function is returning proper error message or not
   */
  @Test
  public void testInitCalculationInvalidInput() {
    SinhFunction sinhFunction = new SinhFunction();
    ByteArrayInputStream input = new ByteArrayInputStream(
        "This is non number invalid input".getBytes());
    System.setIn(input);
    sinhFunction.initCalculation();
    Assert.assertTrue(
        UserInterfaceMessages.ERROR_MESSAGE.equalsIgnoreCase(outData.toString().trim()));
    System.setIn(System.in);
  }

  /**
   * Test for : EF_F3_D1_ASSUMPTION-1 This test verifies when user inputs valid number value but
   * more than what datatype can handle then function is returning proper error message or not.
   */
  @Test
  public void testInitCalculationInvalidUpperBoundInput() {
    SinhFunction sinhFunction = new SinhFunction();
    ByteArrayInputStream input = new ByteArrayInputStream(
        String.valueOf(2 * Double.MAX_VALUE).getBytes());
    System.setIn(input);
    sinhFunction.initCalculation();
    Assert.assertTrue(UserInterfaceMessages.ERROR_MESSAGE_RANGE.equalsIgnoreCase(
        outData.toString().trim()));
    System.setIn(System.in);
  }

  /**
   * Test for : EF_F3_D1_REQ-1 . This tests verifies if user inputs valid number value then function
   * is returning proper valid calculated value.
   */
  @Test
  public void testInitCalculationValidInput() {
    SinhFunction sinhFunction = new SinhFunction();
    ByteArrayInputStream input = new ByteArrayInputStream(
        "1".getBytes());//input will be passed as number , not string
    System.setIn(input);
    sinhFunction.initCalculation();
    //expected result calculated from : https://keisan.casio.com/exec/system/1223039747
    String expectedResult = String.valueOf(1.1752011936438);
    Assert.assertTrue(outData.toString().trim().contains(expectedResult));
    System.setIn(System.in);
  }


  /**
   * Test for : EF_F3_D1_REQ-3 ,EF_F3_D1_ASSUMPTION-3. This test verifies calculated value of e
   * Euler’s number is finite or not using x =1 so that it test for EF_F3_D1_ASSUMPTION-3 too.
   */
  @Test
  public void testEPowerXFinite() {
    //Calculated value of e (Euler’s number) should be finite means e^1 should be finite
    SinhFunction sinhFunction = new SinhFunction();
    double result = sinhFunction.epowerx(1.0);
    Assert.assertEquals(result, 2.718281828459045, 0.0);
    Assert.assertFalse(Double.isNaN(result) && Double
        .isInfinite(result)); // making sure that result is not infinite ,
  }

  /**
   * Test for :EF_F3_D1_REQ-1. This test validate calculatesinh function with valid input
   */
  @Test
  public void testCalculateSinh() {
    SinhFunction sinhFunction = new SinhFunction();
    double epowerX = sinhFunction.epowerx(2.0);
    double epowerMinusX = sinhFunction.epowerx(-2.0);
    double expectedResult = 3.6268604078469773;
    Assert.assertEquals(expectedResult, sinhFunction.calculateSinh(epowerX, epowerMinusX), 0.0);
  }

  /**
   * Test for : EF_F3_D1_ASSUMPTION-2. When user inputs valid value but decimal points are more than
   * 15 then function should take only first 15 decimal points as significant decimal points.
   */
  @Test
  public void testSignificantDecimalPoints() {
    SinhFunction sinhFunction = new SinhFunction();
    ByteArrayInputStream input = new ByteArrayInputStream(
        "1.111111111111111222222222222222222222"
            .getBytes());//input will be passed as number , not string
    System.setIn(input);
    sinhFunction.initCalculation();
    Assert.assertTrue(outData.toString().trim().contains("1.111111111111111"));
    Assert.assertFalse(outData.toString().trim().contains("222222222222222222222"));
    System.setIn(System.in);
  }
}