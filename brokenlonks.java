package testngex.brokenlinks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class brokenlonks {
	
	public static void main(String[] arg) throws InterruptedException, MalformedURLException, IOException
	{
		
		
	
		System.setProperty("webdriver.chrome.driver","E:\\chromedriver\\chromedriver.exe");
		 WebDriver dr1=new ChromeDriver();
		dr1.manage().window().maximize();
		dr1.manage().deleteAllCookies();
		dr1.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		dr1.get("https://www.flipkart.com");
		Thread.sleep(3000);
		dr1.switchTo().frame(0);
		List<WebElement> li=dr1.findElements(By.tagName("a"));
		li.addAll(dr1.findElements(By.tagName("img")));
		System.out.println("active links are  "+li.size());
		
		
		List<WebElement> lis=new ArrayList<WebElement>();
		for(int i=0;i<li.size();i++)
		{System.out.println(li.get(i).getAttribute("href"));
			if(li.get(i).getAttribute("href")!=null &&(!li.get(i).getAttribute("href").contains("javascript")));
			{
				lis.add(li.get(i));
			}
		}
		System.out.println("active links are  "+lis.size());
		
		for(int j=0;j<lis.size();j++)
		{
		HttpURLConnection con=(HttpURLConnection)new URL(lis.get(j).getAttribute("href")).openConnection();
		con.connect();
		String re=con.getResponseMessage();
		con.disconnect();
		System.out.println(lis.get(j).getAttribute("href")+"    "+re);
		}
	}

}
