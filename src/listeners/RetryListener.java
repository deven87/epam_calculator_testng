package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

	final int retryMax = 2;
	int retryCount = 0;

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < retryMax) {
			retryCount++;
			return true;
		}
		return false;
	}

}
