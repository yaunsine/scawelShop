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
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class FreelanceSiteProcessor implements PageProcessor {
    // ChromeOp

    ChromeOptions options = new ChromeOptions();

    @Resource
    private FreelanceMapper freelanceMapper;

    @Resource
    private GigReviewMapper gigReviewMapper;

    @Resource
    private GigReviewsMapper gigReviewsMapper;

    /**
     * sec-fetch-dest: empty' \
     *   -H 'sec-fetch-mode: cors' \
     *   -H 'sec-fetch-site: cross-site
     */
    private final Site site = Site.me().setSleepTime(2000).setRetryTimes(5).setTimeOut(60 * 60 * 30 * 30)
            .setCharset("utf-8")
            .addHeader("x-csrf-token", "1676877367.OObtOb4mE6ARpjmV4xpa3pJjsrQge1DmYfrZj69s/Lk=")
            .addHeader("x-requested-with", "XMLHttpRequest")
            .addHeader("Access-Control-Allow-Origin", "*")
            .addHeader("sec-fetch-dest", "empty")
            .addHeader("authority", "www.fiverr.com")
            .addHeader("sec-fetch-mode", "navigate")
            .addHeader("sec-fetch-site", "none")
            .addHeader("sec-fetch-user", "?1")
            .addHeader("sec-fetch-dest", "document")
            .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
            .addHeader("cookie", "u_guid=1675653645000-a5636a3bb8c13038aaf5c8533b5dfa30bdade9ea; logged_out_currency=HKD; pxcts=7214c83c-a5cd-11ed-a11e-6b4246614f77; _pxvid=3df4aef7-a5cd-11ed-b42d-586847525156; _gcl_au=1.1.935174384.1675653776; _gid=GA1.2.1071956554.1675653778; _rdt_uuid=1675653779856.fc465f6c-8483-40d0-b88f-fb78bd0c91b3; _fbp=fb.1.1675653789184.193231351; _pin_unauth=dWlkPU5tSTVNVFF4WXpBdE5HVmlPQzAwTXpFMkxXSXpPR010TlRCbFpXWmxaR1l3WVRJMg; _tt_enable_cookie=1; _ttp=VH6SEeiGSEUI9uXbPa1iSUpQ4eR; QuantumMetricUserID=4d1551e3e0879517c136008316421cf3; QuantumMetricSessionID=98ffafa17c24d85610067084fe394bc0; QuantumMetricSessionS=1675666443522; tatari-session-cookie=33e4187a-81c8-c9b0-b918-377e8a30314e; last_content_pages_=gigs|||show|||121227259;gigs|||show|||147427870; last_viewed_gig=121227259; __cfruid=8a533c6b0032b5655453b4d127c19ebfd57f2c99-1675667767; __cf_bm=1CybWmcpAUqe8re6WCiVi9cB1ua3uf80qTTP5EXiSEs-1675668489-0-AfAesYRWFoNnXfIIc/Z62H9LzU+WgI6XnO+32KvAv42JihfdHD9stm2AAIWyVXb0H6VYGPx7DlEmOHumQ1lVfFo=; redirect_url=/categories/programming-tech/website-development/shopify-development?source=vertical-buckets; forterToken=7ae31aeefd3947eaa855cff46ca5e3fd_1675668657002__UDF43_15ck; page_views=27; _gat_UA-12078752-17=1; _ga_GK1NB7HX40=GS1.1.1675667014.4.1.1675668660.60.0.0; _ga=GA1.1.1651686532.1675653778; OptanonConsent=isGpcEnabled=0&datestamp=Mon+Feb+06+2023+15:31:00+GMT+0800+(中国标准时间)&version=202211.1.0&isIABGlobal=false&hosts=&consentId=53ae724f-b98d-485d-864c-43a836113b70&interactionCount=1&landingPath=NotLandingPage&groups=C0001:1,C0002:1,C0004:1&AwaitingReconsent=false; QSI_HistorySession=https://www.fiverr.com/categories/programming-tech?source=category_tree~1675667749561|https://www.fiverr.com/categories/programming-tech/website-development?source=category_tree~1675667775360|https://www.fiverr.com/categories/programming-tech/website-development/shopify-development?source=vertical-buckets~1675668660231; t-ip=1; _px3=4f0d89b22cd9f4cc9e01c9f3f28c5fdbbe52f939bd5191ef94d34d48068c5785:M0gBy1a2NNjhwcMb6dm3+ml0RRsFfpy6nhLyobRz46mK0xMHuvigkzjdK3JfEOOmmqTcS0JvtfQCoDT0ZgqJmA==:1000:uy8RncQpQgcko0AlDyktgGHNaOO2um0OyiDivIAluCYkv0xl0cNcTI5zT85Vii9/NCdon89BeCCEl5dNbQ8ZaO1AwCICvHgfI7RZmpzxa+hTNgtn3bkG/tpnYjOaOs985drU/so3sG2BhLfuEeGu1UVhI6duxPaL4EgqZ/H/z7fzjBC9DZGILJuKNdlg9XCthbUaPHKg0lApk3VqichWkA==; _pxde=12c52529e0f56fbaf128738fc40b7c3f26abd30cbfe5e32e7a60527b5cb0d395:eyJ0aW1lc3RhbXAiOjE2NzU2Njg2NjI0OTcsImZfa2IiOjAsImlwY19pZCI6W119")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.78");


    @Resource
    private ProgramTechMapper programTechMapper;

    public void batchRequests() throws InterruptedException {
        List<ProgramTechPo> programTechPoList = programTechMapper.listProgramTechs();

        for (ProgramTechPo programTechPo: programTechPoList) {
            List<DeveloperPo> developerPoList = freelanceMapper.listFreelanceById(programTechPo.getId());
            for (DeveloperPo developerPo: developerPoList) {
                if (developerPo.getId() <= 821) {
                    continue;
                }
                seleniumStart(developerPo.getId(), developerPo.getLink());
            }
        }
        // List<DeveloperPo> developerPoList = freelanceMapper.listFreelance();

    }

    public void d(WebDriver driver) {
        try {
            // TimeUnit.SECONDS.sleep(15);
            while (true) {
                Actions actions = new Actions(driver);
                // executor.executeScript("window.scrollTo(0,1500)");
                // executor.executeScript("document.querySelector('body > dialog').remove()");
                List<WebElement> longClickElements = driver.findElements(By.xpath("/html/body/div/div/div[2]"));
                if (longClickElements.size() > 0) {
                    // actions.clickAndHold(longClickElements.get(0));
                }

                // WebElement login=driver.findElement(By.xpath("//*[@id='__ZONE__main']/div/div/div[3]/div[2]/div[6]/div[2]/div/div/button"));
                // actions.moveToElement(login);
                // actions.clickAndHold();
                // TimeUnit.SECONDS.sleep(15);
                List<WebElement> pressElements = driver.findElements(By.xpath("//div[@aria-label='按住']"));
                if (pressElements.size() > 0) {
                    // actions.clickAndHold(pressElements.get(0));
                }

                List<WebElement> longPressElements = driver.findElements(By.xpath("//dialog[@class='perimeterx-async-challenge']"));

                if (longPressElements.size() > 0) {
                    // actions.moveByOffset(60, 0);
                    // Thread.sleep(3000);
                    // actions.moveToElement(longPressElements.get(0), 180, 150).clickAndHold().perform();
                    // actions.click();
                    // ((JavascriptExecutor) driver).executeScript("document.querySelector('.perimeterx-async-challenge').remove()");
                    // actions.clickAndHold(longPressElements.get(0));
                }

                if (!driver.findElements(By.xpath("//*[@id='px-captcha']")).isEmpty()) {
                    // WebElement captchaElement = driver.findElement(By.xpath("//*[@id='px-captcha']"));

                    // actions.moveToElement(captchaElement, 300, 300).clickAndHold();
                    // WebElement iframe = driver.findElement(By.cssSelector("#px-captcha>iframe"));
                    // driver.switchTo().frame();
                    // WebElement pre = driver.findElement(By.xpath("//div[@aria-label='按住']"));
                    // actions.clickAndHold(pre);
                }


                List<WebElement> btnElements = driver.findElements(By.xpath("//*[@class='see-more-button']"));
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                // actions.click(btnElement);
                // actions.clickAndHold();
                if (btnElements.size() > 0) {
                    executor.executeScript("arguments[0].click();",btnElements.get(0));
                } else {
                    break;
                }
                // break;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // executor.executeScript("document.querySelector('body > dialog').remove()");
            logger.warn("全部加载完成......");
        } finally {

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


        // options.addArguments("user-agent=Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10");
        // options.addArguments("--proxy-server=http://106.15.93.22:8080");
        ChromeDriver driver = new ChromeDriver(options);

        Map<String, Object> command = new HashMap<>();
        command.put("source", "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
        driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", command);
        driver.executeScript("");
        // String url = "https://www.fiverr.com/samratpro/create-ai-writing-automation-script-blog-with-openai-gpt3?context_referrer=subcategory_listing&ref_ctx_id=133ea6860f0c44700d02b008774cbde5&pckg_id=1&pos=4&context_type=rating&funnel=133ea6860f0c44700d02b008774cbde5&imp_id=cf74aaa2-ede6-437c-b952-eac92a3286db";
        driver.manage().window().maximize();

        try{
            Thread.sleep(2000);
            driver.get(link);
            Thread.sleep((12-(int)(Math.random()*10)) * 1000);
            // d(driver);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // driver.close();


        List<WebElement> elements = driver.findElements(By.xpath("//*[@class='gig-page-reviews']//ul/span"));
        List<DeveloperPo> developerPoList = new ArrayList<>();
        String dialogElement = "//div[@class='perimeterx-async-challenge']";
        while (!driver.findElements(By.xpath("//*[@id='px-captcha']")).isEmpty()) {
            logger.info("请求出错");
            driver.quit();
            seleniumStart(id, link);
            return;
        }
        String ratingCount = driver.findElement(By.xpath("//*[contains(@class, 'seller-card')]//span[@class='ratings-count']")).getText().replace("(", "").replace(")", "");
        String sellFrom = driver.findElement(By.xpath("//*[@class='user-stats']/li[1]/strong")).getText();
        String description = driver.findElement(By.xpath("//*[@class='description-content']")).getText().replace("\n", ".");
        String reviewCounts = driver.findElement(By.xpath("//*[@class='header-desktop']/span")).getText();
        String buildTool = driver.findElement(By.xpath("//div[contains(@class, 'gig-overview')]/nav/ul/li[last()]/span[1]")).getText();
        String sellerName = driver.findElement(By.xpath("//*[@class='seller-link']")).getText();
        String sellerScore = driver.findElement(By.xpath("//div[contains(@class,'seller-rating')]/b[@class='rating-score']")).getText();
        // driver.findElement(By.xpath(dialogElement)).get;
        List<ReviewPo> reviewPoList = new ArrayList<>();
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
            // String imgSrc = element.findElement(By.xpath("//label[@class='profile-pict']//img")).getAttribute("src");
            // String username = element.findElement(By.xpath("li/div[1]/div/div[2]/div[1]/div[2]/span/b")).getText();

            List<WebElement> countryListElements = element.findElements(By.className("country-name"));
            String countryName = countryListElements.size() == 0 ? "": countryListElements.get(0).getText();

                    countryList.add(countryName);
            // String ratingScore = element.findElement(By.xpath("li/div[2]/div[1]/div/b")).getText();
            // String time = element.findElement(By.xpath("li/div[2]/div[1]/time")).getText();
            //
            //
            //
            // List<WebElement> metaElements = driver.findElements(By.xpath("//*[@class='metadata-attribute']"));
            //
            // String websiteType = metaElements.get(0).findElement(By.xpath("ul")).getText();;
            // websiteType = websiteType.replace("\n", ",");
            // String platFormAndTool = metaElements.get(1).findElement(By.xpath("ul")).getText();
            // platFormAndTool = platFormAndTool.replace("\n", ",");
            // String websiteFeature = metaElements.get(2).findElement(By.xpath("ul")).getText();
            // websiteFeature = websiteFeature.replace("\n", ",");
            //
            // ReviewPo reviewPo = new ReviewPo();
            // List<WebElement> scoreElements = driver.findElements(By.xpath("//*[@class='rating-score']"));
            // if (scoreElements.size() > 0) {
            //     String score = scoreElements.get(0).getText();
            //     reviewPo.setScore(score);
            // }
            //
            // reviewPo.setFreelanceId(id);
            // reviewPo.setUrl(link);
            // reviewPo.setTime(time);
            // reviewPo.setImgSrc(imgSrc);
            //
            // reviewPo.setUsername(username);
            // reviewPo.setCountryName(countryName);
            // reviewPo.setRatingScore(ratingScore);
            // reviewPo.setWebsiteType(websiteType);
            // reviewPo.setWebsiteFeature(websiteFeature);
            // reviewPo.setPlatFormAndTool(platFormAndTool);
            // reviewPoList.add(reviewPo);
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

    public static void main(String[] args) {
        new FreelanceSiteProcessor().seleniumStart(0, null);

        // String author = driver.findElement(By.xpath("//*[@id='__ZONE__main']/div/div/div/div[3]/div[4]/div/div/div/div[1]/div/div[1]/div/div/div/div/a")).getText();
        // WebElement element = driver.findElement(By.xpath("//*[@id='__ZONE__main']/div/div/div/div[3]/div[4]/div/div/div/div[1]/div"));
        //
        // String introduce = driver.findElement(By.xpath("//*[@id='__ZONE__main']/div/div/div/div[3]/div[4]/div/div/div/div[1]/div/h3/a")).getText();
        // String href = driver.findElement(By.xpath("//*[@id='__ZONE__main']/div/div/div/div[3]/div[4]/div/div/div/div[1]/div/h3/a")).getAttribute("href");
        // String score = driver.findElement(By.xpath("//*[@id='__ZONE__main']/div/div/div/div[3]/div[4]/div/div/div/div[1]/div/div[2]/div")).getText();
        // String money = driver.findElement(By.xpath("//*[@id='__ZONE__main']/div/div/div/div[3]/div[4]/div/div/div/div[1]/div/footer/a/span")).getText();
        // String img_src = driver.findElement(By.xpath("//*[@id='__ZONE__main']/div/div/div/div[3]/div[4]/div/div/div/div[1]/div/a/div/div/div/img")).getAttribute("src");
        // // //*[@id="__ZONE__main"]/div/div/div/div[3]/div[4]/div/div/div/div[1]/div
        // System.out.println(author);
        // Spider.create(new FiverrSiteProcessor())
        //         .thread(1)
        //         .addUrl("https://www.fiverr.com/categories/programming-tech/website-development/shopify-development?source=category_filters")
        //         .addPipeline(new ConsolePipeline())
        //         .setDownloader(new MyHttpClientDownloader())
        //         .run();


    }

    private final Logger logger = LoggerFactory.getLogger(FreelanceSiteProcessor.class);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        logger.info("111");
    }

    @Override
    public Site getSite() {
        return site;
    }
}
