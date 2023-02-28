# scawelShop
![作者](https://img.shields.io/badge/Author-yaunsine-orange.svg)
![爬虫框架](https://img.shields.io/badge/技术栈-Springboot+webmagic-red.svg)
![存储](https://img.shields.io/badge/存储-MySQL+Excel-green.svg)

#### 说明

:construction_worker:拉跨的爬虫项目

:poop:基于Webmagic和多线程 


[> 项目目录结构](project_tree_banner.txt)


#### 技术栈

1、SpringBoot

2、WebMagic + HttpClient

3、MyBatis

4、Swagger

5、ThreadPoolTaskExecutor

#### 使用指南

在`application.yml`同级目录下，创建`application-develop.yml`文件,配置数据库账号和密码，内容如下:

```yaml
mysql:
  host: 数据库IP
  username: 数据库名称
  password: 数据库密码

instagram:
  username: ins账号
  password: ins密码
```

若需要使用打开的`chrome.exe`，代替`chromedriver.exe`，可以配置`ChromeOptions`：
```
ChromeOptions options = new ChromeOptions();
options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
```

并且在控制台打开
```shell
chrome.exe --remote-debugging-port=9222
```

#### 参考链接：

[1] https://easyexcel.opensource.alibaba.com/

[2] http://webmagic.io/

[3] https://swagger.io/

[4] https://spring.io/
