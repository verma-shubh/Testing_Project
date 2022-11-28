package com.selenium.test;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// Code by - MEET SHAH(MT2021074) and SHUBHAM VERMA(MT2021132)

public class FinalTest {

	WebDriver driver;

	@BeforeMethod
	public void invokeBrowser() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
		driver.get("http://localhost:3000/");
	}

	@AfterMethod
	public void aftermethod() throws InterruptedException {
		driver.quit();
	}

	private static String generateUsername(int length) {
		StringBuilder sb = new StringBuilder();

		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = upper.toLowerCase();
		String digits = "0123456789";
		String alphabet = upper + lower + digits;

		Random random = new Random();

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		String randomString = sb.toString();
		return randomString;

	}

	private static String generatePassword(int length) {
		String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = "abcdefghijklmnopqrstuvwxyz";
		String special = "!@#$";
		String numbers = "1234567890";
		String combined = caps + lower + special + numbers;
		Random random = new Random();
		char[] password = new char[length];

		password[0] = lower.charAt(random.nextInt(lower.length()));
		password[1] = caps.charAt(random.nextInt(caps.length()));
		password[2] = special.charAt(random.nextInt(special.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < length; i++) {
			password[i] = combined.charAt(random.nextInt(combined.length()));
		}
		String pass;
		pass = String.valueOf(password);
		return pass;
	}
	
	private static String changeCase(String in) {
		
		StringBuffer strBuffer = new StringBuffer(in.length());
        char c = 0;
        for(int i=0; i<in.length(); i++) {
            c = in.charAt(i);
    		if(Character.isLowerCase(c)) {
    			c = Character.toUpperCase(c);
    		}
    		else if(Character.isUpperCase(c)) {
    			c = Character.toLowerCase(c);
    		}
    		strBuffer.append(c);
    	}
		return strBuffer.toString();
	}
	
	private String[] makeRegistration(int userLength, int emailLength, int pwdLength, String domain) throws InterruptedException {
		String res[] = new String[3];
		res[0] = generateUsername(userLength); // user name
		res[1] = generateUsername(emailLength) + domain; // email
		res[2] = generatePassword(pwdLength); // password
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[1]")).sendKeys(res[0]);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[2]")).sendKeys(res[1]);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[3]")).sendKeys(res[2]);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/button")).click();
		Thread.sleep(2000);
		
		return res; 
	}

	@Test(enabled = true, description = "Testing if registration occurs successfully on giving correct inputs")
	public void Test1() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);

		// Enter values into the registration form and submit
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[1]")).sendKeys(generateUsername(5));
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[2]")).sendKeys(generateUsername(5) + "@ymail.com");
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[3]")).sendKeys(generatePassword(10));
		driver.findElement(By.xpath("/html/body/div/div[2]/form/button")).click();
		
		Thread.sleep(2000);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:3000/login", "Unsuccessful registration");
	}

	@Test(enabled = true, description = "Testing invalid username during registration")
	public void Test2() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		// Generating user name of length 2 which will throw an error as user name must be minimum of length 3
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).sendKeys(generateUsername(2));

		// Randomly generate email
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[2]")).sendKeys(generateUsername(5) + "@ymail.com");

		// generate password of correct specifications
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[3]")).sendKeys(generatePassword(8));
		
		// click on register
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button")).click();
		
		Thread.sleep(2000);
		
		// Check
		String message = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).getAttribute("validationMessage").toString();
		Assert.assertTrue(!message.isEmpty(), "No error shown on wrong username");
	}

	@Test(enabled = true, description = "Testing invalid email during registration")
	public void Test3() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		// Randomly generate user name of correct specifications
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).sendKeys(generateUsername(4));

		// Generate wrong email
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[2]")).sendKeys("xyx");

		// generate password of correct specifications
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[3]")).sendKeys(generatePassword(8));
		
		// click on register
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button")).click();
		
		Thread.sleep(2000);

		// Check
		String message = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[2]")).getAttribute("validationMessage").toString();
		Assert.assertTrue(!message.isEmpty(), "No error shown on wrong email");
	}

	@Test(enabled = true, description = "Testing invalid password during registration")
	public void Test4() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		// Randomly generate correct user name
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).sendKeys(generateUsername(5));

		// Randomly generate email according to specifications
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[2]")).sendKeys(generateUsername(5) + "@ymail.com");

		// Enter invalid password(Entering password of length 7 while minimum must be 8)
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[3]")).sendKeys(generatePassword(7));
		
		// click on register
		driver.findElement(By.xpath("/html/body/div/div[2]/form/button")).click();
		
		Thread.sleep(2000);
		
		// Check
		String message = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[3]")).getAttribute("validationMessage").toString();
		Assert.assertTrue(!message.isEmpty(), "No error shown on wrong password");
	}
	
	@Test(enabled = true, description = "Testing to register with the same user name but different email") 
	public void Test5() throws InterruptedException {
		
		// Make registration
		String details[] = new String[3];
		details = makeRegistration(5, 5, 8, "@ymail.com");

		Thread.sleep(2000);
		
		// click on register again
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[1]")).sendKeys(details[0]);
		
		String diffEmail = generateUsername(5) + "@ymail.com";
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[2]")).sendKeys(diffEmail);
		
		String diffPassword = generatePassword(8);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[3]")).sendKeys(diffPassword);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/button")).click();
		
		Thread.sleep(2000);
		String message = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/span[2]")).getText().trim();
		Assert.assertTrue(!message.isEmpty(), "Registration successful with same user name but different email");
	}
	
	@Test(enabled = true, description = "Testing to register with the different user name but same email") 
	public void Test6() throws InterruptedException {
		
		// Make registration
		String details[] = new String[3];
		details = makeRegistration(5, 5, 8, "@ymail.com");

		Thread.sleep(2000);
		
		// click on register again
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		
		String diffUser = generateUsername(6);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[1]")).sendKeys(diffUser);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[2]")).sendKeys(details[1]);
		
		String diffPassword = generatePassword(8);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/input[3]")).sendKeys(diffPassword);
		driver.findElement(By.xpath("/html/body/div/div[2]/form/button")).click();
		
		Thread.sleep(2000);
		String message = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/span[2]")).getText().trim();
		Assert.assertTrue(!message.isEmpty(), "Registeration successful with different user name but same email");
	}
	
	@Test(enabled = true, description = "Testing valid login")
	public void Test7() throws InterruptedException {
		
		// Make registration
		String details[] = new String[3];
		details = makeRegistration(5, 5, 8, "@ymail.com");

		Thread.sleep(2000);
		
		// Login

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[1]/a")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).sendKeys(details[0]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[2]")).sendKeys(details[2]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button")).click();

		// If correct inputs were entered, we would get to the home page where LOGOUT would be visible
		// If false we would remain on the login page where LOGOUT is not visible
		Thread.sleep(2000);
		Assert.assertTrue(!driver.findElements(By.xpath("//li[contains(text(),'LOGOUT')]")).isEmpty(), "Valid login failed");
	}

	@Test(enabled = true, description = "Testing invalid user name during login")
	public void Test8() throws InterruptedException {
		
		// Make registration
		String details[] = new String[3];
		details = makeRegistration(5, 5, 8, "@ymail.com");

		Thread.sleep(2000);
		
		// Login

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).sendKeys(generateUsername(6));
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[2]")).sendKeys(details[2]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button")).click();
		
		Thread.sleep(2000);
		// If LOGOUT visible means we have been able to login with incorrect user name
		Assert.assertTrue(driver.findElements(By.xpath("//li[contains(text(),'LOGOUT')]")).isEmpty(), "Logged in with invalid user name");
	}

	@Test(enabled = true, description = "Testing invalid password during login")
	public void Test9() throws InterruptedException {
		
		// Make registration
		String details[] = new String[3];
		details = makeRegistration(5, 5, 8, "@ymail.com");
		
		Thread.sleep(2000);
		
		// Login

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[1]/a")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).sendKeys(details[0]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[2]")).sendKeys(changeCase(details[2]));
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button")).click();

		Thread.sleep(2000);
		// If LOGOUT visible means we have been able to login with incorrect password
		Assert.assertTrue(driver.findElements(By.xpath("//li[contains(text(),'LOGOUT')]")).isEmpty(), "Logged in with incorrect password");
	}

	// Checking if empty title and content in it can be published
	@Test(enabled = true, description = "Testing if publishing post gives error if no title or content is provided")
	public void Test10() throws InterruptedException {
		
		// Make registration
		String details[] = new String[3];
		details = makeRegistration(5, 5, 8, "@ymail.com");

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		// Enter correct login
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).sendKeys(details[0]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[2]")).sendKeys(details[2]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button")).click();
		Thread.sleep(2000);

		// Click on WRITE
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		// Click on Publish
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button")).click();
		Thread.sleep(2000);

		// Check
		Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:3000/write",
				"Error! Post published even with no title or content provided");
	}

	@Test(enabled = true, description = "This is to check account deletion functionality")
	public void Test11() throws InterruptedException {

		// Make registration
		String details[] = new String[3];
		details = makeRegistration(5, 5, 8, "@ymail.com");
		
		Thread.sleep(2000);
		// Login
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		// Enter correct login
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).sendKeys(details[0]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[2]")).sendKeys(details[2]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button")).click();

		Thread.sleep(3000);

		// click on profile image icon
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/a/img")).click();

		Thread.sleep(2000);

		// click on delete account button
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div/button")).click();

		Thread.sleep(2000);

		// click on login
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/ul/li[1]/a")).click();

		Thread.sleep(2000);

		// Check if able to login from the same user name
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[1]")).sendKeys(details[0]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/input[2]")).sendKeys(details[2]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button")).click();
		
		Thread.sleep(2000);

		// If LOGOUT visible, means deletion has failed as we were successfully able to
		// login
		Assert.assertTrue(driver.findElements(By.xpath("//li[contains(text(),'LOGOUT')]")).isEmpty());
	}
}
