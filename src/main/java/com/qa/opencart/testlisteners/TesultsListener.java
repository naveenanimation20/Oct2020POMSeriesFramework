package com.qa.opencart.testlisteners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.opencart.factory.DriverFactory;
import com.tesults.tesults.Results;

public class TesultsListener extends DriverFactory implements ITestListener {

	List<Map<String, Object>> testCases = new ArrayList<Map<String, Object>>();

	public static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public static Object[] getTestParams(ITestResult iTestResult) {
		return iTestResult.getParameters();
	}
	
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("I am in onTestStart method " + getTestMethodName(result) + " start");

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Map<String, Object> testCase = new HashMap<String, Object>();
		testCase.put("name", getTestMethodName(iTestResult));
		testCase.put("suite", "TesultsExample");
		testCase.put("result", "pass");
		testCase.put("params", getTestParams(iTestResult));
		testCases.add(testCase);
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Map<String, Object> testCase = new HashMap<String, Object>();
		testCase.put("name", getTestMethodName(iTestResult));
		testCase.put("suite", "TesultsExample");
		testCase.put("result", "fail");
		testCase.put("params", getTestParams(iTestResult));
		List<String> files = new ArrayList<String>();
		files.add(getScreenshot());
		testCase.put("files", files);

		testCases.add(testCase);
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		Map<String, Object> testCase = new HashMap<String, Object>();
		testCase.put("name", getTestMethodName(iTestResult));
		testCase.put("suite", "TesultsExample");
		testCase.put("result", "fail");
		testCase.put("params", getTestParams(iTestResult));
		List<String> files = new ArrayList<String>();
		files.add(getScreenshot());
		testCase.put("files", files);

		testCases.add(testCase);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		// Map<String, Object> to hold your test results data.
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("target","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjQ2MWEwMWJkLTE5NjctNGEyZS05MzhmLWQ3YTMxNjk3YTE2Ny0xNjEwNTAzMTk4MzY1IiwiZXhwIjo0MTAyNDQ0ODAwMDAwLCJ2ZXIiOiIwIiwic2VzIjoiM2U1ZDQ3OTgtZTAxNy00OTE3LWEzMDgtYmVmN2EyNmI5YmI0IiwidHlwZSI6InQifQ.791N2eU8uDAh35qsF6OHIBrthUi8tQ0iu2kr8Ndd-lM");

		Map<String, Object> results = new HashMap<String, Object>();
		results.put("cases", testCases);
		data.put("results", results);

		// Upload
		Map<String, Object> response = Results.upload(data);
		System.out.println("success: " + response.get("success"));
		System.out.println("message: " + response.get("message"));
		System.out.println("warnings: " + ((List<String>) response.get("warnings")).size());
		System.out.println("errors: " + ((List<String>) response.get("errors")).size());
	}

}
