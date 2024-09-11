package test_01.Assisment_project;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class Test_cases {
	public static WebDriver driver;
	public static ExtentReports er;

	@BeforeSuite
	public static void beforeSuite() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		driver.get("https://germanyiscalling.com/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()=\"Accept\"]")).click();
	}

	@BeforeClass
	public static void report() throws Exception {

		String projectpath = System.getProperty("user.dir");
		System.out.println(projectpath);

		ExtentSparkReporter esr = new ExtentSparkReporter(projectpath + "\\reports\\ main_report");
		esr.config().setDocumentTitle("document1");
		esr.config().setReportName("test_report");
		esr.config().setTheme(Theme.STANDARD);

		er = new ExtentReports();
		er.attachReporter(esr);

		er.setSystemInfo("operating system", "windows");
		er.setSystemInfo("browser", "chrome");
		er.setSystemInfo("tester", "kalyani");
		er.setSystemInfo("application", "germanyiscalling");

		ExtentTest test = er.createTest("successfull_login");
		test.log(Status.INFO, "login to the app");

		ExtentTest test1 = er.createTest("unsuccessfull_login");
		test1.log(Status.INFO, "not login to the app");

		
	}

	@Test(priority = 2)
	public static void succesfull_login() throws Exception {

//	  driver.findElement(By.linkText("Login / SignUp")).click();

		Thread.sleep(2000);
		WebElement email = driver.findElement(By.cssSelector("input#username"));
		email.click();
		email.sendKeys("kalyanikhere03@gmail.com");
		Thread.sleep(2000);

		WebElement pass = driver.findElement(By.cssSelector(" input#password"));
		pass.click();
		pass.sendKeys("Kalyani@22");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[text()=\"Log In\"]")).click();
		Thread.sleep(2000);

//		 used assertion to to verify succefull login

		String expected_url = "https://app.germanyiscalling.com/cv/upload/?utm_source=website&utm_medium=website&utm_campaign=navbar_menu";
		String actual_url = driver.getCurrentUrl();
		Assert.assertEquals(expected_url, actual_url);
		System.out.println("user unable to go to the dashboard");
	}

	@Test(priority = 1)
	public void unsuccessful_login() throws InterruptedException {
//	  locate the login button
		driver.findElement(By.linkText("Login / SignUp")).click();

		WebElement email1 = driver.findElement(By.cssSelector("input#username"));
		email1.click();
		email1.sendKeys("kalyanikheregmail.com");
		Thread.sleep(2000);

		WebElement pass1 = driver.findElement(By.cssSelector(" input#password"));
		pass1.click();
		pass1.sendKeys("kalyani11");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[text()=\"Log In\"]")).click();
		Thread.sleep(2000);

//	 use assertion to verify error massage

		String expected_errormassage = "Please enter a correct username and password. Note that both fields may be case-sensitive.";
		String actual_errormassage = driver.findElement(By.xpath(
				"//li[text()=\"Please enter a correct username and password. Note that both fields may be case-sensitive.\"]"))
				.getText();
		if (expected_errormassage.equalsIgnoreCase(actual_errormassage)) {
			System.out.println("user entered invalid credentials");
		}
	}

	@AfterSuite
	public void afterSuite() {
		driver.close();
		er.flush();
	}
}
