package com.hiido.shop.scawelshop.service.processor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class InstagramProcessor {

    @Value("${instagram.username}")
    private String loginUsername;

    @Value("${instagram.password")
    private String loginPwd;

    public void start() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\JOYY\\Downloads\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.chrome.bin","C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");

        ChromeDriver driver = new ChromeDriver(options);

        String url = "https://www.instagram.com/accounts/login/";
        driver.get(url);

        WebElement usernameElement = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement passwordElement = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement submitElement = driver.findElement(By.xpath("//button[@type='submit']"));
        usernameElement.sendKeys(loginUsername);
        passwordElement.sendKeys(loginPwd);
        submitElement.click();
    }

    public static InstagramProcessor getInstance() {
        return new InstagramProcessor();
    }

    public static void main(String[] args) {

        InstagramProcessor.getInstance().start();
    }
}
