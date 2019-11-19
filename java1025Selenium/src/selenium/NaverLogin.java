package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NaverLogin {

	public static void main(String[] args) {
		try {
			//드라이버 위치 등록
			System.setProperty(
					"webdriver.chrome.driver", 
					"/Users/tjoeun-304/Downloads/chromedriver");
			//크롬 실행
			WebDriver driver = new ChromeDriver();
			//네이버 로그인 페이지 접속 
			driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com");
			
			Thread.sleep(1000);
			//id란에 입력하기 
			driver.findElement(
				By.xpath("//*[@id=\"id\"]")).sendKeys("sinioinis");
			//pw란에 입력하기
			driver.findElement(By.xpath("//*[@id=\"pw\"]")).sendKeys("qwe123yingji");
			
			Thread.sleep(1000);
			//로그인 버튼 클릭 
			driver.findElement(By.xpath(
					"//*[@id=\"frmNIDLogin\"]/fieldset/input")).click();
			
			Thread.sleep(1000);
			//카페에 접속 
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"//*[@id=\"cafe-info-data\"]/div[3]/a")).click();
			
			Thread.sleep(1000);
			//프레임 전환 
			driver.switchTo().frame(
					driver.findElement(By.id("down")));
			
			
			
		}catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
		}

	}
}
