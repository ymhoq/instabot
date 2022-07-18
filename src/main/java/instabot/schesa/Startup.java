package instabot.schesa;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
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
    static Logger log = Logger.getLogger(Startup.class);

    public static WebDriver driver;
    public static Actions action;
    // public static final String USER_DATA = "C:\\Users\\Professional\\AppData\\Local\\Google\\Chrome\\User Data - Selenium";
    public static final String USER_DATA = "C:\\Users\\Professional\\AppData\\Local\\Google\\Chrome\\User Data - benone";
    public static final String CHROME_DRIVER = "C:\\Users\\Professional\\Downloads\\chromedriver.exe";
    public static final String CHRONIUM_DRIVER = "C:\\Users\\Professional\\Downloads\\chromedriver.exe";
    //@formatter:off
    public static String[] hashTags = {
            "#malinois",
            "#graduation",
            "#gsdofinstagram", "#dogstagram",
            "Bucharest, Romania",
            "#puma",
            "#gym",
            "#scottishfold", 
            "#gymgirl",
            "timisoara", "drinking", "party", "vodka", "tgif", 
            "Dubova, Mehedin", "constanta",
            "#computerscience", "Buckingham Palace Garden road", 
            "#balkan", 
            "#catsofromania", "#cardiotime" ,
            "lake ghioroc", "#motivation", "#loveyourselffirst", "#rainylondon", "#hackathon", "#exporeromania", 
            "#lifeisgooduphere", "#developer", "cluj", "arad county",
            "#dogsoflondon", 
            "#painonlegsday",
            "Centru Timisoara", "Dubova, Mehedin",
            "#guarddogs", "#gsdofinstagram", "#k9dog",
            "#aggressiontraining", "#malinois", "#k9", 
            "#brittishshorthair", "#beagle", "#germanshepherd",
            "#romania", "timisoara", "#arad", "#starbucks", "#beach", "#developer", "#software", "#gymbeast",
        };
     //@formatter:on    
    public static final int EXPLORE_POSTS = 0;

    public static final int EXPLORE_HASHTAGS = 130;
    public static final int LIKE_PROBABILITY = 80;

    // public static final int EXPLORE_HASHTAGS = 50;
    // public static final int LIKE_PROBABILITY = 50;
    /**
     * Used to put a sleep between hashtag likes
     */
    public static final int[] INTERVAL = new int[] { 800, 1500 };
    // public static final int[] INTERVAL = new int[] { 3600, 5400 };
    // public static final int[] INTERVAL = new int[] { 3600, 10800 };
    // public static final int[] INTERVAL = new int[] { 1000, 10800 };

    public static final int UNFOLLOW_PEOPLE = 0;
    public static final String LIKED_TEXT = "Nu-mi mai place";

    public static final ArrayList<String> tags = new ArrayList<String>();
    static {
        tags.add("@coddrin");
        tags.add("@teodoraradu10");
        tags.add("@dianaacristina");
    }

    public static void main(String[] args) throws MalformedURLException {
        // Open chrome
        System.setProperty("webdriver.chrome.driver", CHRONIUM_DRIVER);

        //ChromeOptions options = new ChromeOptions();
        // options.setBinary(CHRONIUM_DRIVER);
        //options.addArguments("user-data-dir=" + USER_DATA);
        driver = new ChromeDriver();

        log.info("Going to instagram.com");
        driver.get("https://instagram.com");
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        addSleepSeconds(0, 1);
 logIn("","");


        // unfollow();

        // Go to explore
        // driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[3]/div/div[1]/a/span")).click();
         addSleepSeconds(2, 4);

         likeProfile("danincicau", 40);
        // commentProfile("carlabianca19", "⊂（♡⌂♡）⊃", 146);
        // commentProfile("andredumi", "hei cf ( ಠ‿ಠ)┘", 100);

        // URL tagUrl = new URL("https://www.instagram.com/p/B4R0CZQA80-/");
        // URL tagUrl = new URL("https://www.instagram.com/p/B4Ik4KgAzgX/");
        // tagInPost(tagUrl, tags, 10);

        // likeExpore();
        // likeHashtags();
        //likeRecentHashtags();
        log.info("Done");
    }

    private static void logIn(String username, String pass) {
        WebElement logname = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/div/label"));
        logname.sendKeys("fylend@gmail.com");

        WebElement logpass = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[2]/div/label"));
    logpass.sendKeys("pigamama1");

        WebElement logBtn = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[3]/button/div"));
logBtn.click();
      addSleepSeconds(0, 2);
        WebElement noSpov = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div/div/div/div/div/div/div[3]/button[2]"));
        noSpov.click();

    }


        @SuppressWarnings("unused")
    private static void tagInPost(URL tagUrl, ArrayList<String> tags, int times) {
        driver.get(tagUrl.toString());
        IntStream.range(0, times).forEach(i -> {
            addSleepSeconds(5, 11);

            WebElement we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/article/div[2]/section[3]/div/form/textarea"));
            we.click();
            WebElement input = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/article/div[2]/section[3]/div/form/textarea"));
            input.clear();
            addSleepSeconds(2, 4);
            tags.forEach(tag -> {
                input.sendKeys(tag);
                addSleepSeconds(0, 1);
                input.sendKeys(Keys.SPACE);
                addSleepSeconds(2, 4);
            });
            input.sendKeys(Keys.TAB);
            input.sendKeys(Keys.RETURN);
        });
    }

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    private static void likeProfile(String profile, int likes) {
        log.info("Entering profile " + profile);
        addSleepSeconds(0, 5);
        WebElement we = driver.findElement(By.xpath("//*[@id=\"mount_0_0_3T\"]/div/div[1]/div/div[1]/div/div/div/div[1]/div[1]/section/nav/div[2]/div/div/div[2]"));
        we.click();
        addSleepSeconds(0, 1);
        we = driver.findElement(By.xpath("//*[@id=\"mount_0_0_xK\"]/div/div[1]/div/div[1]/div/div/div/div[1]/div[1]/section/nav/div[2]/div/div/div[2]"));
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

    private static WebElement hoverRandomRecentPicture() {
        addSleepSeconds(2, 3);
        // Get random picture and hover
        // TODO get images that are actually posts
        List<WebElement> lwe = driver.findElements(By.tagName("img"));
        lwe = lwe.stream().filter(we -> we.getSize().width > 250 && we.getSize().height > 250).collect(Collectors.toList());
        // int index = getRandom(offset, lwe.size());
        int index = getRandom(lwe.size() - 7, lwe.size() - 2);
        WebElement we = lwe.get(index);
        action.moveToElement(we).build().perform();
        addSleepSeconds(2, 3);
        return we;
    }

    private static WebElement hoverRandomPicture(int offset) {
        addSleepSeconds(2, 3);
        // Get random picture and hover
        // TODO get images that are actually posts
        List<WebElement> lwe = driver.findElements(By.tagName("img"));
        lwe = lwe.stream().filter(we -> we.getSize().width > 200).collect(Collectors.toList());
        // int index = getRandom(offset, lwe.size());
        int index = getRandom(1, 1);
        WebElement we = lwe.get(index);
        action.moveToElement(we).build().perform();
        addSleepSeconds(2, 3);
        return we;
    }

    private static void likeRecentHashtags() {
        for (String hashtag : hashTags) {
            log.info("Starting likes for hashtag " + hashtag);
            addSleepSeconds(2, 3);
            WebElement we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/div/div"));
            we.click();
            addSleepSeconds(1, 2);
            we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/input"));
            we.sendKeys(hashtag);
            addSleepSeconds(2, 3);
            we.sendKeys(Keys.ARROW_DOWN);
            we.sendKeys(Keys.RETURN);
            addSleepSeconds(2, 4);
            likeRecentFeed();
            addSleepSeconds(INTERVAL[0], INTERVAL[1]);
        }
    }

    @SuppressWarnings("unused")
    private static void likeHashtags() {
        for (String hashtag : hashTags) {
            log.info("Likes for " + hashtag);
            addSleepSeconds(1, 2);
            WebElement we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/div/div"));
            we.click();
            addSleepSeconds(0, 1);
            we = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[2]/input"));
            we.sendKeys(hashtag);
            addSleepSeconds(2, 3);
            we.sendKeys(Keys.ARROW_DOWN);
            we.sendKeys(Keys.RETURN);
            addSleepSeconds(2, 4);
            likeFeed(1);
        }
    }

    private static void likeRecentFeed() {
        WebElement we = hoverRandomRecentPicture();
        // click picture
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", we);
        addSleepSeconds(4, 5);

        iteratePosts(EXPLORE_HASHTAGS);
        pressX();
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
            driver.findElement(By.xpath("/html/body/div[4]/div[3]/button")).click();
        } catch (Throwable t) {
            driver.findElement(By.xpath("/html/body/div[4]/divs[2]/button")).click();
        }
        addSleepSeconds(1, 2);
    }

    private static void iteratePosts(int posts) {
        int likes = 0, i = 0;
        try {
            for (i = 0; i < posts; i++) {
                addSleepSeconds(2, 4);
                addSleepSeconds(2, 4);
                if (getRandom(0, 100) > (100 - LIKE_PROBABILITY)) {
                    likePost();
                    likes++;
                }
                if (getRandom(0, 100) < 2) addSleepSeconds(1, 100);
                addSleepSeconds(2, 3);
                if (i != posts - 1) nextPost();
            }
        } catch (Throwable t) {
            return;
        } finally {
            log.info("Iterated over " + i + " posts");
            log.info("Liked " + likes + " posts");
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
            WebElement element = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/a[2]"));
            action.click(element).perform();
        } catch (Throwable t1) {
            try {
                // different xpath if it's the first post
                WebElement element = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/a"));
                action.click(element).perform();
            } catch (Throwable t2) {
                // last post
            }
        }
    }

    public static void likePost() {
        WebElement element;
        boolean liked = false;
        try {
            element = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/article/div[3]/section[1]/span[1]/button/div/span"));
            liked = element.getAttribute("innerHTML").contains(LIKED_TEXT);
            if (liked) return;
        } catch (Throwable t) {
            log.error("Could not get like info", t);
        }
        // try {
        // element = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/article/div[2]/div/div/div[1]/div[2]"));
        // action.doubleClick(element).perform();
        // } catch (Throwable t1) {
        // try {
        // element = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/article/div[2]/div/div/div[3]"));
        // action.doubleClick(element).perform();
        // action.doubleClick(element).perform();
        // } catch (Throwable t2) {
        element = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/article/div[3]/section[1]/span[1]/button"));
        action.click(element).perform();
        // }
        // }
    }

    public static int getRandom(int low, int high) {
        if (low == high) return low;
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    private static void addSleepSeconds(int s1, int s2) {
        try {
            int s = getRandom(s1 * 1000, s2 * 1000);
            if (s / 1000 > 5) log.info("Sleeping " + s / 1000 + "s");
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
