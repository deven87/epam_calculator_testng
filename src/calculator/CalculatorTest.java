package calculator;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.tat.module4.Calculator;

public class CalculatorTest {

	Calculator calculator;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		calculator = new Calculator();
	}

	@AfterSuite
	public void afterSuite() {
		// close down activities for the whole suite
	}

	@Test(priority = 1, groups = { "positive_test" }, dataProvider = "dataprovider")
	public void testSubtract(int x, int y) {
		System.out.println("subtracting values " + x + " " + y);
		Assert.assertEquals(calculator.sub(x, y), Math.subtractExact(x, y), "subtraction not working");
	}

	@Test(priority = 2, groups = { "positive_test" })
	public void testAddition() {
		Assert.assertEquals(calculator.sum(10, 20), 30, "addition not working");
	}

	@Test(priority = 3, groups = { "positive_test" })
	public void testDivision() {
		Assert.assertEquals(calculator.div(10, 20), 0, "division not working");
	}

	@Test(priority = 4, groups = { "positive_test" })
	public void testMultiplication() {
		Assert.assertEquals(calculator.mult(10, 20), 200, "multiplication not working");
	}

	@Test(priority = 5, groups = { "positive_test" })
	public void testIsNegative() {
		Assert.assertEquals(calculator.isNegative(-10), true, "is Negative not working");
	}

	@Test(enabled = true, priority = 6, groups = { "negative_test" }, expectedExceptions = { ArithmeticException.class,
			NumberFormatException.class })
	public void testDivisionByZero() {
		calculator.div(10, 0);
	}

	@Test(priority = 7, groups = { "negative_test" })
	public void additionAboveDoubleRange() {
		double number = calculator.sum(Double.MAX_VALUE, Double.MAX_VALUE);
		Assert.assertTrue(number == Double.POSITIVE_INFINITY, "Double max range not handled");
	}

	@Test(priority = 8, groups = { "negative_test" })
	public void subNegative() {
		System.out.println(Double.MIN_VALUE);
		double number = calculator.sub(Double.MIN_VALUE, 1);
		Assert.assertTrue(number < 0, "Double Min Value subtraction underflow not handled");
	}

	@Test(priority = 9, groups = { "negative_test" })
	public void negativeDivision() {
		Assert.assertEquals(calculator.div(-123.23, 0), Double.NEGATIVE_INFINITY,
				"negative double divide by zero not handled");
	}

	@Test(priority = 10, groups = { "negative_test" })
	public void squareRootNegativeNumber() {
		Assert.assertEquals(calculator.sqrt(-10), Double.NaN, "Negative number square root is not working");
	}

	@DataProvider(name= "dataprovider")
	public Object[][] provideData() {
		return new Object[][] { { 1, 2 }, { 3, 4 } };
	}
}
