import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class DiscountTest {

    @Test
    public void test() {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=2&zip_code=111111&first_name=Test&" +
                "last_name=Test&email=test%40test.com&password1=Test&password2=Test");
        String email = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("1111");
        driver.findElement(By.cssSelector("[value=Login]")).click();
        driver.get("https://www.sharelane.com/cgi-bin/add_to_cart.py?book_id=2");
        driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("21");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html[1]/body[1]/center[1]/table[1]" +
                "/tbody[1]/tr[6]/td[1]/table[1]/tbody[1]/tr[2]/td[5]/p[1]/b[1]")).getText();
        softAssert.assertEquals(discount, "2");
        String discountSum = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/" +
                "table/tbody/tr[2]/td[6]")).getText();
        softAssert.assertEquals(discountSum, "4.2");
        String totalSum = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table" +
                "/tbody/tr[2]/td[7]")).getText();
        softAssert.assertEquals(totalSum, "205.8");
        driver.quit();
        softAssert.assertAll();
    }
}
