package start_Selenium;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Apachi_Poi_Practice 
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arpith\\eclipse-workspace\\LanguageBinders\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//WebDriverWait W = new WebDriverWait(driver, 5);
		
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		
		//input[@value='Google Search']
		String[] Search = {"Java","Python","PHP","html",".Net","VBScript","C++","C#"};
		
		String PageTitle = driver.getTitle();
		
		for( int j = 0 ; j < Search.length ; j++ )
		{
			driver.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys(Search[j]);
			
			if( j==0 )
				driver.findElement(By.xpath("//input[@value='Google Search']")).click();
			else if( j>=1 )
				driver.findElement(By.xpath("//button[@aria-label='Google Search']")).click();

			List<WebElement> Links = driver.findElements(By.xpath("//div[@class='r']/a/h3"));
			
			for( int i = 0 ; i < Links.size() ; i++)
			{
				String LinkText = null;
				
				//W.until(ExpectedConditions.visibilityOf(Links.get(j-1)));

				if( j==0 && i==0 ) 
				{
//					i=0,j=0	i=0,j=1	i=0,j=2	i=0,j=3	i=0,j=4
//					i=1,j=0	i=1,j=1	i=1,j=2	i=1,j=3	i=1,j=4
//					i=2,j=0	i=2,j=1	i=2,j=2	i=2,j=3	i=2,j=4
//					i=3,j=0	i=3,j=1	i=3,j=2	i=3,j=3	i=3,j=4

					Apachi_Poi_Practice.writeData( "Search", "C:\\Users\\Arpith\\Desktop\\Arpit\\" , PageTitle, "Search Results", 0, 0 );
				}
				else if( j>=1 && i==1 )
				{
//					i=0,j=0

					Apachi_Poi_Practice.writeData( "LinkText["+j+"]", "C:\\Users\\Arpith\\Desktop\\Arpit\\" , PageTitle, "Search Results", i , j );
				}
				else if( i==1 )
				{
//					
//					i=1,j=0	i=1,j=1	i=1,j=2	i=1,j=3	i=1,j=4|
//					                                       |
//					                                       |
//                  ----------------------------------------
					Apachi_Poi_Practice.writeData( "", "C:\\Users\\Arpith\\Desktop\\Arpit\\" , PageTitle, "Search Results", i , j );
				}
				else if( j>=1 && i==0 )
				{
//					        i=0,j=1	i=0,j=2	i=0,j=3	i=0,j=4|
//					                                       |
//					                                       |
//					                                       |
//                  ----------------------------------------

					Apachi_Poi_Practice.writeData( Search[j-1], "C:\\Users\\Arpith\\Desktop\\Arpit\\" , PageTitle, "Search Results", i , j );
				}
				else if( j>=1 && i>2 )
				{
//					                                       |
//					                                       |
//					    	i=2,j=1	i=2,j=2	i=2,j=3	i=2,j=4|
//					    	i=3,j=1	i=3,j=2	i=3,j=3	i=3,j=4|
//                  ----------------------------------------

					LinkText = driver.findElement(By.xpath("//div[@class='srg']/div["+i+"]/div/div/div/a/h3")).getText();
					Apachi_Poi_Practice.writeData( LinkText, "C:\\Users\\Arpith\\Desktop\\Arpit\\" , PageTitle, "Search Results", i , j );
				}
				
			}

			driver.findElement(By.xpath("//input[@aria-label='Search']")).clear();
		}
		
		driver.close();
	}
	
	public static void writeData( String Value, String File, String Page, String Sheet, int row, int column ) throws EncryptedDocumentException, IOException 
	{
		FileOutputStream FO = new FileOutputStream(File+Page.replace(' ','_')+".xlsx");
		XSSFWorkbook W = new XSSFWorkbook();
		XSSFSheet S = W.createSheet(Sheet);
		
		Row R = S.createRow(row);
		Cell C = R.createCell(column);
		C.setCellValue(Value);

		W.write(FO);
		W.close();
		FO.close();
	}
}
