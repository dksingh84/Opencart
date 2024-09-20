package testCases;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CricTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.cricbuzz.com/");
		driver.manage().window().maximize();

		List<WebElement> titles = driver.findElements(By.xpath(
				"//div[@class='cb-mtch-crd-hdr cb-font-10']//div[@class='cb-col-90 cb-color-light-sec cb-ovr-flo']"));
		List<WebElement> matchTypes = driver.findElements(By.xpath(
				"//div[@class='cb-mtch-crd-hdr cb-font-10']//div[contains(@class,'cb-card-match-format text-center text-white')]"));

		Map<String, String> titleMatchMap = new HashMap<>();
		
		//if (titles.size() == matchTypes.size()) {
            for (int i = 0; i < titles.size(); i++) {
                String title = titles.get(i).getText();
                String matchType = matchTypes.get(i).getText();
                titleMatchMap.put(title, matchType);
            }
            System.out.println(titleMatchMap);
		}
	//}
}
