package instabot.schesa;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

/**
 * @author SChesa
 *
 */
public class Startup {
    static Logger log = Logger.getGlobal();

    public static WebDriver driver;
    public static Actions action;
    // public static final String USER_DATA = "C:\\Users\\CristianP\\AppData\\Local\\Google\\Chrome\\User Data - Selenium";
    public static final String USER_DATA = "C:\\Users\\CristianP\\AppData\\Local\\Google\\Chrome\\User Data - benone";
    public static final String CHROME_DRIVER = "C:\\Users\\CristianP\\Downloads\\chromedriver_win32\\chromedriver.exe";
    public static String[] hashTags = {
            // { "#romania", "timisoara", "#arad", "#starbucks", "#beach", "#developer", "#software", "#gymbeast"};
             "#software", "#caseofthemondays", "#photooftheday", "#love", "#instagood", "#nofilter", "#tbt", "#picoftheday", "#instapic", "#nature", "#swag", "#lifeisgood", "#developer", "cluj", "arad county" };
            // "epic vara"};
            // "timisoara", "drinking", "party", "vodka", "tgif" };
//            "Dubova, Mehedin", "constanta" };
    public static final int EXPLORE_POSTS = 0;
    public static final int EXPLORE_HASHTAGS = 100;
    public static final int UNFOLLOW_PEOPLE = 10;

    public static void main(String[] args) {
        // Open chrome
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + USER_DATA);
        driver = new ChromeDriver(options);
        log.info("Going to instagram.com");
        driver.get("https://instagram.com");
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        addSleepSeconds(0, 1);

        // unfollow();

        // Go to explore
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[3]/div/div[1]/a/span")).click();
        addSleepSeconds(2, 4);

        // likeProfile("danincicau", 40);
//         commentProfile("carlabianca19", "⊂（♡⌂♡）⊃", 146);
//         commentProfile("andredumi", "hei cf ( ಠ‿ಠ)┘", 100);

//        likeExpore();
//
        likeHashtags();

        log.info("Done");
    }

    private static void commentProfile(String profile, String comment, int posts) {
        log.info("Entering profile " + profile);
        addSleepSeconds(0, 2);
        WebElement we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/div/div"));
        we.click();
        addSleepSeconds(0, 1);
        we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/input"));
        we.sendKeys(profile);
        addSleepSeconds(1, 2);
        we.sendKeys(Keys.RETURN);
        we.sendKeys(Keys.RETURN);
        addSleepSeconds(3, 5);
        // hover first post
        we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div[3]/article/div[1]/div/div[1]/div[1]/a/div"));
        action.moveToElement(we).build().perform();
        addSleepSeconds(2, 4);
        // click picture
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", we);
        addSleepSeconds(3, 6);

        for (int i = 0; i < posts; i++) {
            we = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/article/div[2]/section[3]/div/form/textarea"));
            we.click();
            we = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/article/div[2]/section[3]/div/form/textarea"));
            addSleepSeconds(2, 3);
            we.sendKeys(comment);
            we.sendKeys(Keys.RETURN);
            addSleepSeconds(2, 3);
            nextPost();
            addSleepSeconds(2, 3);
        }
    }

    private static void unfollow() {
        // Go to profile
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[3]/div/div[3]/a/span")).click();
        addSleepSeconds(4, 4);
        // see followers
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[3]")).click();
        addSleepSeconds(2, 3);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        for (int i = 6; i < UNFOLLOW_PEOPLE + 6; i++) {
            try {
                if (buttons.get(i).getText().equals("Urmăreşti")) {
                    buttons.get(i).click();
                    addSleepSeconds(1, 5);
                    driver.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/button[1]")).click();
                    addSleepSeconds(1, 5);
                }
            } catch (Throwable t) {
                i--;
                js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,1000)");
                addSleepSeconds(1, 1);
                buttons = driver.findElements(By.tagName("button"));
            }
        }
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/button")).click();
    }

    private static void likeProfile(String profile, int likes) {
        log.info("Entering profile " + profile);
        addSleepSeconds(0, 2);
        WebElement we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/div/div"));
        we.click();
        addSleepSeconds(0, 1);
        we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/input"));
        we.sendKeys(profile);
        addSleepSeconds(1, 2);
        we.sendKeys(Keys.RETURN);
        we.sendKeys(Keys.RETURN);
        addSleepSeconds(3, 5);
        // hover first post
        we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div[3]/article/div[1]/div/div[1]/div[1]/a/div"));
        action.moveToElement(we).build().perform();
        addSleepSeconds(2, 4);
        // click picture
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", we);
        addSleepSeconds(3, 6);

        for (int i = 0; i < likes; i++) {
            addSleepSeconds(1, 3);
            likePost();
            addSleepSeconds(0, 2);
            nextPost();
        }
    }

    @SuppressWarnings("unused")
    private static void likeExpore() {
        if (EXPLORE_POSTS > 0) {
            WebElement we = hoverRandomPicture(6);

            // click picture
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", we);
            addSleepSeconds(3, 6);

            iteratePosts(EXPLORE_POSTS);
            pressX();
        }
    }

    private static WebElement hoverRandomPicture(int offset) {
        addSleepSeconds(2, 3);
        // Get random picture and hover
        // TODO get images that are actually posts
        List<WebElement> lwe = driver.findElements(By.tagName("img"));
        // int index = getRandom(offset, lwe.size());
        int index = getRandom(10, 15);
        WebElement we = lwe.get(index);
        action.moveToElement(we).build().perform();
        addSleepSeconds(2, 3);
        return we;
    }

    private static void likeHashtags() {
        for (String hashtag : hashTags) {
            log.info("Likes for " + hashtag);
            addSleepSeconds(1, 2);
            WebElement we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/div/div"));
            we.click();
            addSleepSeconds(0, 1);
            we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/input"));
            we.sendKeys(hashtag);
            addSleepSeconds(1, 2);
            we.sendKeys(Keys.ARROW_DOWN);
            we.sendKeys(Keys.RETURN);
            addSleepSeconds(2, 4);
            likeFeed(90);
        }
    }

    private static void likeFeed(int offset) {
        WebElement we = hoverRandomPicture(offset);
        // click picture
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", we);
        addSleepSeconds(4, 5);

        iteratePosts(EXPLORE_HASHTAGS);
        pressX();
    }

    private static void pressX() {
        addSleepSeconds(1, 1);
        try {
        driver.findElement(By.xpath("/html/body/div[3]/button[1]")).click();
        } catch(Throwable t) {
            driver.findElement(By.xpath("/html/body/div[2]/button[1]")).click();
        }
        addSleepSeconds(1, 2);
    }

    private static void iteratePosts(int posts) {
        try {
            for (int i = 0; i < posts; i++) {
                addSleepSeconds(2, 4);
                if (getRandom(0, 100) > 10)
                    likePost();
                if (getRandom(0, 100) < 2)
                    addSleepSeconds(1, 100);
                addSleepSeconds(2, 3);
                if (i != posts - 1)
                    nextPost();
            }
        } catch (Throwable t) {
            System.out.println("iteratePosts-  " + t);
            return;
        }
    }

    public static void skipPosts() {
        for (int i = 0; i >= getRandom(1, 6); i++) {
            nextPost();
            addSleepSeconds(0, 5);
        }
    }

    public static void nextPost() {
        try {
            WebElement element = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/a[2]"));
            action.click(element).perform();
        } catch (Throwable t1) {
            try {
                // different xpath if it's the first post
                WebElement element = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/a"));
                action.click(element).perform();
            } catch (Throwable t2) {
                // last post
            }
        }
    }

    public static void likePost() {
        WebElement element = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/article/div[1]/div"));
        action.doubleClick(element).perform();
    }

    public static int getRandom(int low, int high) {
        if (low == high)
            return low;
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    private static void addSleepSeconds(int s1, int s2) {
        try {
            Thread.sleep(getRandom(s1 * 1000, s2 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
