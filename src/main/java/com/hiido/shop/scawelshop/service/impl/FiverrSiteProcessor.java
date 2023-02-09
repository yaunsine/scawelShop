package com.hiido.shop.scawelshop.service.impl;

import com.beust.jcommander.internal.Lists;
import com.hiido.shop.scawelshop.config.MyHttpClientDownloader;
import com.hiido.shop.scawelshop.mapper.FreelanceMapper;
import com.hiido.shop.scawelshop.mapper.ProgramTechMapper;
import com.hiido.shop.scawelshop.po.DeveloperPo;
import com.hiido.shop.scawelshop.po.ProgramTechPo;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FiverrSiteProcessor implements PageProcessor {
    // ChromeOp

    ChromeOptions options = new ChromeOptions();


    @Value("${drive:C:\\Users\\JOYY\\Downloads\\chromedriver_win32\\chromedriver.exe}")
    private String chromeDriverPath;

    @Resource
    private FreelanceMapper freelanceMapper;
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

    public void seleniumStartBatch() {
        List<ProgramTechPo> programTechPos = programTechMapper.listProgramTechs();
        for (ProgramTechPo programTechPo: programTechPos) {
            int id = programTechPo.getId();
            String url = "https://www.fiverr.com" + programTechPo.getUrl();
            seleniumStart(id, url);
        }
    }

    private void optionsSpicify(ChromeOptions options) {
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
        // options.addArguments("--headless");
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

    public void seleniumStart(int id, String url) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();

        optionsSpicify(options);
        // options.addArguments("user-agent=Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10");
        // options.addArguments("--proxy-server=http://106.15.93.22:8080");
        WebDriver driver = new ChromeDriver(options);

        // String url = "https://www.fiverr.com/categories/programming-tech/website-development/shopify-development?source=category_filters";
        driver.manage().window().setSize(new Dimension(1920, 1080));
        try{
            Thread.sleep(2000);
            driver.get(url);
            Thread.sleep((12-(int)(Math.random()*10)) * 1000 + (int) (Math.random() * 100));
            // d(driver);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        while (!driver.findElements(By.xpath("//*[@id='px-captcha']")).isEmpty()) {
            logger.info("请求出错");
            driver.quit();
            seleniumStart(id, url);
            return;
        }

        // driver.close();

        List<WebElement> elements = driver.findElements(By.xpath("//*[@class='gig-card-layout']"));
        List<DeveloperPo> developerPoList = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            String author = elements.get(i).findElement(By.xpath("div/div[1]/div/div/div/div/a")).getText();
            String introduce = elements.get(i).findElement(By.xpath("div/h3/a")).getText();
            String score = elements.get(i).findElement(By.xpath("div/div[2]/div")).getText();
            String href = elements.get(i).findElement(By.xpath("div/h3/a")).getAttribute("href");
            String money = elements.get(i).findElement(By.xpath("div/footer/a/span")).getText();
            // div/a/div/div/div/div/img   //*[@id="__ZONE__main"]/div/div/div/div[3]/div[4]/div/div/div/div[1]/div/a/div/div/div/img
            String imgSrc = elements.get(i).findElement(By.xpath("//div[@class='slide-item active']//img")).getAttribute("src");
            DeveloperPo developerPo = new DeveloperPo();
            developerPo.setAuthor(author);
            developerPo.setIntroduction(introduce);
            developerPo.setScore(score);
            developerPo.setLink(href);
            developerPo.setImgSrc(imgSrc);
            developerPo.setPrice(money);
            developerPo.setProgramTechId(String.valueOf(id));
            developerPoList.add(developerPo);
        }
        freelanceMapper.batchSaveFreelance(developerPoList);
        driver.close();
        logger.info("执行完成");
    }

    public static void main(String[] args) {


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

    private final Logger logger = LoggerFactory.getLogger(FiverrSiteProcessor.class);

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
