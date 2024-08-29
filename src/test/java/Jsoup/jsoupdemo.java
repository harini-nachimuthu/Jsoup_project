package Jsoup;

import java.net.HttpURLConnection;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class jsoupdemo {
	
	protected String url="https://www.demoblaze.com/";
	WebDriver driver=null;
	
  @Test
  public void jsoupdemomethod() {
	  try {
		 Document doc=Jsoup.connect(url).get(); 
		 String title=doc.title();
		 System.out.println(title);
		 
		 Elements links=doc.select("a[href]");
		 for(Element link:links) {
			 String linkHref = link.attr("href");
             String linkText = link.text();
			 System.out.println("Link : "+link.attr("href")+" text : "+link.text());
		 
		 Connection.Response response = Jsoup.connect(linkHref).execute();
         if (response.statusCode() == HttpURLConnection.HTTP_OK) {
             System.out.println("Link is valid: " + linkHref);
         } else {
             System.out.println("Link is broken: " + linkHref + " (Status code: " + response.statusCode() + ")");
         }
     }
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
  }
  @BeforeSuite
  public void beforesuit() {
	  WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
  }
  @BeforeClass
  public void beforeclas() {
	  driver.get(url);
  }
  @AfterSuite
  public void afterclas() {
	  if(driver!=null) {
		  driver.close();
	  }
  }
}
