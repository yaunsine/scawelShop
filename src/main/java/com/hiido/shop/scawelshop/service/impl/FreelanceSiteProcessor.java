package com.hiido.shop.scawelshop.service.impl;

import com.beust.jcommander.internal.Lists;
import com.hiido.shop.scawelshop.mapper.FreelanceMapper;
import com.hiido.shop.scawelshop.mapper.GigReviewMapper;
import com.hiido.shop.scawelshop.mapper.GigReviewsMapper;
import com.hiido.shop.scawelshop.mapper.ProgramTechMapper;
import com.hiido.shop.scawelshop.po.DeveloperPo;
import com.hiido.shop.scawelshop.po.GigReviewPo;
import com.hiido.shop.scawelshop.po.ProgramTechPo;
import com.hiido.shop.scawelshop.po.ReviewPo;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @description: TODO 使用selenium爬取gig评论数据
 * @author YSLin
 * @date 2023/2/10 17:02
 */
@Service
public class FreelanceSiteProcessor {

    ChromeOptions options = new ChromeOptions();

    @Resource
    private FreelanceMapper freelanceMapper;

    @Resource
    private GigReviewMapper gigReviewMapper;

    @Resource
    private GigReviewsMapper gigReviewsMapper;


    @Resource
    private ProgramTechMapper programTechMapper;


    /**
     * 按照freelance-id批量请求抓取gig评论数据
     * @throws InterruptedException
     */
    public void batchRequests() throws Exception {
        List<ProgramTechPo> programTechPoList = programTechMapper.listProgramTechs();

        for (ProgramTechPo programTechPo: programTechPoList) {
            List<DeveloperPo> developerPoList = freelanceMapper.listFreelanceById(programTechPo.getId());
            for (DeveloperPo developerPo: developerPoList) {
                // if (developerPo.getId() <= 911 || developerPo.getId() >= 934) {
                //     continue;
                // }
                seleniumStart(developerPo.getId(), developerPo.getLink());
            }
        }
    }


    /**
     * 反爬处理
     * @param driver chromedriver驱动
     */
    public void dialogDeal(WebDriver driver, int seeMore) {
        try {
            while (true) {
                Actions actions = new Actions(driver);
                List<WebElement> longClickElements = driver.findElements(By.xpath("/html/body/div/div/div[2]"));
                if (longClickElements.size() > 0) {
                }

                List<WebElement> pressElements = driver.findElements(By.xpath("//div[@aria-label='按住']"));
                if (pressElements.size() > 0) {
                }

                List<WebElement> longPressElements = driver.findElements(By.xpath("//dialog[@class='perimeterx-async-challenge']"));

                if (longPressElements.size() > 0) {
                }

                if (!driver.findElements(By.xpath("//*[@id='px-captcha']")).isEmpty()) {
                }

                List<WebElement> btnElements = driver.findElements(By.xpath("//*[@class='see-more-button']"));
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                seeMore--;
                if (btnElements.size() > 0 && seeMore >= 0) {
                    executor.executeScript("arguments[0].click();",btnElements.get(0));
                } else {
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("全部加载完成......");
        }
    }

    private void optionsSpicify(ChromeOptions options) {
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");
        options.addArguments("--incognito");
        options.addArguments("--disable-blink-features");
        options.addArguments("--disable-blink-features=AutomationControlled");

        List<String> excludeSwitches = Lists.newArrayList("enable-automation");
        options.setExperimentalOption("excludeSwitches", excludeSwitches);
        options.addArguments("--disable-popup-blocking");
        // 关闭弹窗拦截，不然新页面打不开
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        // 以最高权限运行
        options.addArguments("--headless=new");
        options.addArguments("--ignore-certificate-errors");
        // options.addArguments("blink-settings=imagesEnabled=false");
        // 禁止加载图片,不加载图片，详情页图片获取不到
        // options.addArguments("excludeSwitches", ["enable-automation"]);  // 关闭 “Chrome 正受到自动测试软件的控制。”

        options.addArguments("--disable-blink-features=AutomationControlled");
        // 就是这一行告诉chrome去掉了webdriver痕迹，令navigator.webdriver=false，极其关键
        // 禁用浏览器弹窗  包括不限于提示保存密码的弹窗

        options.addArguments("disable-infobars");
        options.addExtensions(new File("E:\\code\\java\\scawelShop\\crx\\shadowOpen.crx"));
        // 隐藏"Chrome正在受到自动软件的控制"
    }

    public void seleniumStart(int id, String link) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\JOYY\\Downloads\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.chrome.bin","C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        ChromeOptions options = new ChromeOptions();

        optionsSpicify(options);
        ChromeDriver driver = new ChromeDriver(options);

        Map<String, Object> command = new HashMap<>();
        command.put("source", "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
        driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", command);
        driver.executeScript("");
        // String url = "https://www.fiverr.com/samratpro/create-ai-writing-automation-script-blog-with-openai-gpt3?context_referrer=subcategory_listing&ref_ctx_id=133ea6860f0c44700d02b008774cbde5&pckg_id=1&pos=4&context_type=rating&funnel=133ea6860f0c44700d02b008774cbde5&imp_id=cf74aaa2-ede6-437c-b952-eac92a3286db";
        driver.manage().window().maximize();

        int seeMore = 5;
        try{
            Thread.sleep(2000);
            driver.get(link);
            Thread.sleep((12-(int)(Math.random()*10)) * 1000);
            dialogDeal(driver, seeMore);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> elements = driver.findElements(By.xpath("//*[@class='gig-page-reviews']//ul/span"));
        List<DeveloperPo> developerPoList = new ArrayList<>();
        String dialogElement = "//div[@class='perimeterx-async-challenge']";
        while (!driver.findElements(By.xpath("//*[@id='px-captcha']")).isEmpty()) {
            logger.info("请求出错");
            driver.quit();
            seleniumStart(id, link);
            return;
        }
        String ratingCount = driver.findElement(By.xpath("//*[contains(@class, 'seller-card')]//*[@class='ratings-count']")).getText().replace("(", "").replace(")", "");
        String sellFrom = driver.findElement(By.xpath("//*[@class='user-stats']/li[1]/strong")).getText();
        String description = driver.findElement(By.xpath("//*[@class='description-content']")).getText().replace("\n", ".");
        List<WebElement> reviewCountsElements = driver.findElements(By.xpath("//*[@class='header-desktop']/span"));
        String reviewCounts = reviewCountsElements.isEmpty() ? "": reviewCountsElements.get(0).getText();
        String buildTool = driver.findElement(By.xpath("//div[contains(@class, 'gig-overview')]/nav/ul/li[last()]/span[1]")).getText();
        String sellerName = driver.findElement(By.xpath("//*[@class='seller-link']")).getText();
        String sellerScore = driver.findElement(By.xpath("//div[contains(@class,'seller-rating')]/b[@class='rating-score']")).getText();

        List<String> countryList = new ArrayList<>();

        GigReviewPo gigReviewPo = new GigReviewPo();

        gigReviewPo.setSellerRatingCount(ratingCount);
        gigReviewPo.setFreelanceId(String.valueOf(id));
        gigReviewPo.setSellerFrom(sellFrom);
        gigReviewPo.setUrl(link);
        gigReviewPo.setGigDescription(description);
        gigReviewPo.setReviewsCount(reviewCounts);
        gigReviewPo.setBuildingTool(buildTool);
        gigReviewPo.setSellerName(sellerName);
        gigReviewPo.setSellerScore(sellerScore);
        for (WebElement element: elements) {
            List<WebElement> countryListElements = element.findElements(By.className("country-name"));
            String countryName = countryListElements.size() == 0 ? "": countryListElements.get(0).getText();

            countryList.add(countryName);
        }
        String countryNames = StringUtils.join(countryList);
        gigReviewPo.setReviewsCountryName(countryNames);
        gigReviewsMapper.batchSaveGigReviews(gigReviewPo);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.close();
        driver.quit();
    }

    /**
     * @deprecated
     */
    private void getReviews(List<WebElement> elements, ChromeDriver driver, int id, String link) {
        List<ReviewPo> reviewPoList = new ArrayList<>();
        List<String> countryList = new ArrayList<>();
        for (WebElement element: elements) {
            String imgSrc = element.findElement(By.xpath("//label[@class='profile-pict']//img")).getAttribute("src");
            String username = element.findElement(By.xpath("li/div[1]/div/div[2]/div[1]/div[2]/span/b")).getText();

            List<WebElement> countryListElements = element.findElements(By.className("country-name"));
            String countryName = countryListElements.size() == 0 ? "": countryListElements.get(0).getText();

            countryList.add(countryName);
            String ratingScore = element.findElement(By.xpath("li/div[2]/div[1]/div/b")).getText();
            String time = element.findElement(By.xpath("li/div[2]/div[1]/time")).getText();



            List<WebElement> metaElements = driver.findElements(By.xpath("//*[@class='metadata-attribute']"));

            String websiteType = metaElements.get(0).findElement(By.xpath("ul")).getText();;
            websiteType = websiteType.replace("\n", ",");
            String platFormAndTool = metaElements.get(1).findElement(By.xpath("ul")).getText();
            platFormAndTool = platFormAndTool.replace("\n", ",");
            String websiteFeature = metaElements.get(2).findElement(By.xpath("ul")).getText();
            websiteFeature = websiteFeature.replace("\n", ",");

            ReviewPo reviewPo = new ReviewPo();
            List<WebElement> scoreElements = driver.findElements(By.xpath("//*[@class='rating-score']"));
            if (scoreElements.size() > 0) {
                String score = scoreElements.get(0).getText();
                reviewPo.setScore(score);
            }

            reviewPo.setFreelanceId(id);
            reviewPo.setUrl(link);
            reviewPo.setTime(time);
            reviewPo.setImgSrc(imgSrc);

            reviewPo.setUsername(username);
            reviewPo.setCountryName(countryName);
            reviewPo.setRatingScore(ratingScore);
            reviewPo.setWebsiteType(websiteType);
            reviewPo.setWebsiteFeature(websiteFeature);
            reviewPo.setPlatFormAndTool(platFormAndTool);
            reviewPoList.add(reviewPo);
        }
    }

    public static void main(String[] args) {
        new FreelanceSiteProcessor().seleniumStart(0, null);
    }

    private final Logger logger = LoggerFactory.getLogger(FreelanceSiteProcessor.class);

}
