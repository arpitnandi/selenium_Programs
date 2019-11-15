package start_Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass 
{
	public String LanguageBinders = "D:\\Drivers\\";
	public WebDriver D = null ;
	public Actions A ;
	public WebDriverWait W ;
	
	public WebDriver initDriver(String Browser) 
	{
		String Driver = "";
		
		if( Browser.toLowerCase().contains( "rom" ) || Browser.toLowerCase().contains( "goog" ) )
		{
			Driver = "chromedriver";
			System.setProperty("webdriver.chrome.driver", LanguageBinders+Driver+".exe" );
			D = new ChromeDriver();
		}
			 
		if( Browser.toLowerCase().contains( "fir" ) || Browser.toLowerCase().contains( "ge" ) )
		{
			Driver = "geckodriver" ;
			System.setProperty("webdriver.firefox.driver", LanguageBinders+Driver+".exe" );
			D = new FirefoxDriver();
		}
		if( Browser.toLowerCase().contains( "int" ) )
		{
			Driver = "IEDriverserver" ;
			System.setProperty("webdriver.ie.driver", LanguageBinders+Driver+".exe" );
			D = new InternetExplorerDriver();
		}
		
		return D;
	}
	
	public void launchSession(String Application, long IWait, long WWait ) 
	{
		D.get( Application );
		D.manage().window().maximize();

		D.manage().timeouts().implicitlyWait(IWait, TimeUnit.SECONDS);
		
		W = new WebDriverWait( D, WWait );
		
		A = new Actions( D );
	}
	
	public void endSession() 
	{
		D.close();
	}

}
