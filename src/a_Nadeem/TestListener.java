package a_Nadeem;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("<tr><td>" + result.getName() + "</td>");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		long timeTaken = (result.getEndMillis() - result.getStartMillis());
		Reporter.log("<td style='background-color:green; font-weight:bold; color:white;'>PASS</td><td>" + timeTaken
				+ " ms</td></tr>");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		long timeTaken = (result.getEndMillis() - result.getStartMillis());
		Reporter.log("<td style='background-color:red; font-weight:bold; color:white;'>FAIL</td><td>" + timeTaken
				+ " ms</td></tr>");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		long timeTaken = (result.getEndMillis() - result.getStartMillis());
		Reporter.log("<td style='background-color:yellow; font-weight:bold; color:black;'>SKIPPED</td><td>" + timeTaken
				+ " ms</td></tr>");
	}

	@Override
	public void onStart(ITestContext context) {
		Reporter.log("<html><body>");
		Reporter.log("<h2>Test Execution Report</h2>");
		Reporter.log("<table border='1' style='border-collapse: collapse; width: 80%;'>");
		Reporter.log("<tr style='background-color:lightgray; font-weight:bold; text-align:center;'>"
				+ "<th>Test Case Name</th>" + "<th>Status</th>" + "<th>Time Utilized (ms)</th>" + "</tr>");
	}

	@Override
	public void onFinish(ITestContext context) {
		Reporter.log("</table></body></html>");
	}
}
