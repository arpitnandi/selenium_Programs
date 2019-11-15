package start_Selenium;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.*;

public class Tests 
{
	BaseClass Base = new BaseClass();
	PoiUtils Poi = new PoiUtils();
		
	static String ResourcePath = "C:\\Users\\Arpith\\eclipse-workspace\\selenium_Programs\\src\\test\\java\\start_Selenium";
		
	static String VerifiedComponents = ResourcePath+"Verified_Components.xlsx";
		
	public void open_Browser_load_URL_and_verify_the_page(String Browser, String URL) throws IOException 
	{
		Base.initDriver(Browser);
		Base.launchSession(URL, 60, 3);
		WebElement Logo = Base.D.findElement(By.xpath("//img[contains(@id,'hplogo')]"));
			
		String Title = Logo.getAttribute("title");
		String Border = Logo.getAttribute("border");
		String Height = Logo.getAttribute("height");
		String Width = Logo.getAttribute("width");
		String Src = Logo.getAttribute("src");
		
		//Base.W.until( ExpectedConditions.elementToBeClickable(Logo) );
		
		Poi.writeExcel( VerifiedComponents, "Google_Logo", 1, 0, Title );
		Poi.writeExcel( VerifiedComponents, "Google_Logo", 1, 1, Border );
		Poi.writeExcel( VerifiedComponents, "Google_Logo", 1, 2, Height );
		Poi.writeExcel( VerifiedComponents, "Google_Logo", 1, 3, Width );
		Poi.writeExcel( VerifiedComponents, "Google_Logo", 1, 4, Src );	
	}

	String SearchKeyword = "";
		
	public void type_Search_and_proceed_for_search(String Search)
	{
		SearchKeyword = Search; 
		Base.D.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys(Search);
		WebElement SearchButton = Base.D.findElement(By.xpath("//input[@type='submit' and @aria-label='Google Search']"));
			
		//Base.W.until( ExpectedConditions.elementToBeClickable(SearchButton) );
			
		SearchButton.click();
	}

	public void verify_the_page_header_area_changes() throws IOException 
	{
		String Title = Poi.readExcel(VerifiedComponents, "Google_Logo", 1, 0);
		WebElement Logo = Base.D.findElement(By.xpath("//div[contains(@class,'logo')]/a/img"));
		
		assertEquals( Logo.getAttribute("alt"), Title );
		assertTrue( Integer.parseInt( Logo.getAttribute("height") ) < 35 );
		assertTrue( Integer.parseInt( Logo.getAttribute("width") ) < 100 );

		WebElement SearchEdit = Base.D.findElement(By.xpath("//div[@class='logo doodle']/../div[2]/div/div[2]/input"));
		assertEquals( SearchEdit.getAttribute("value"), SearchKeyword);
			
		WebElement All = Base.D.findElement(By.xpath("//div[@id='hdtb-msb']/div[1]/div[1]/div[1]"));
		assertEquals( All.getText(), "All");
	}

	public void verify_the_market_information_are_for_related_search() throws IOException
	{
		WebElement AvailableInMarket = Base.D.findElement(By.xpath("//span[text()='Shop on Google']/../../div[4]/div/div[1]"));
		String Product1 = AvailableInMarket.getAttribute("aria label");
		
		Poi.writeExcel(VerifiedComponents, "Related_Products", 0, 0, "ProductS");
		Poi.writeExcel(VerifiedComponents, "Related_Products", 1, 0, Product1);
	}

	public void verify_the_entire_descriptive_area_for_related_search() throws IOException
	{
		WebElement ComplementaryHeader = Base.D.findElement(By.xpath("//h1[text()='Complementary results']/../div[2]//div[@data-attrid='title']/span"));
		String Header = ComplementaryHeader.getAttribute("text");

		assertTrue(Header.contains( SearchKeyword ));
			
		Poi.writeExcel(VerifiedComponents, Header, 0, 0, "Atributes");
		Poi.writeExcel(VerifiedComponents, Header, 0, 1, "Values");
	}
		
	String SearchResults = "//h1[text()='Search Results']/../div/div";

	public void collect_all_the_links_from_the_main_search_results() throws IOException
	{
		List<WebElement> SearchedLinks = Base.D.findElements(By.xpath(SearchResults+"//div[@class='r']/a"));
		String Link1 = SearchedLinks.get(0).getAttribute("href").toString();
			
		Poi.writeExcel(VerifiedComponents, "SearchedLinks", 0, 0, "Links");
		Poi.writeExcel(VerifiedComponents, "SearchedLinks", 1, 0, Link1);
	}

	public void collect_the_header_text_on_those_links() throws IOException
	{
		List<WebElement> SearchedHeaders = Base.D.findElements(By.xpath(SearchResults+"//div[@class='r']/a"+"/h3/span"));
		String Header1 = SearchedHeaders.get(0).getAttribute("text").toString();

		Poi.writeExcel(VerifiedComponents, "SearchedCites", 0, 0, "Headers");
		Poi.writeExcel(VerifiedComponents, "SearchedCites", 1, 0, Header1);
	}

	public void collect_the_URL_under_those_links() throws IOException
	{
		List<WebElement> SearchedCites = Base.D.findElements(By.xpath(SearchResults+"//div[@class='r']/a"+"/div/cite"));
		String Cite1 = SearchedCites.get(0).getAttribute("aria label").toString();

		Poi.writeExcel(VerifiedComponents, "SearchedCites", 0, 0, "Cites");
		Poi.writeExcel(VerifiedComponents, "SearchedCites", 1, 0, Cite1);
	}
		
	String PeopleAlsoAsked = "//h2[text()='People also ask']/../div/div/g-accordion-expander/div[2]//div[@class='r']/a";

	public void collect_all_the_links_from_the_website_search_results() throws IOException
	{
		List<WebElement> AlsoAsked = Base.D.findElements(By.xpath(PeopleAlsoAsked));
		String AlsoAskedLink1 = AlsoAsked.get(0).getAttribute("href");

		Poi.writeExcel(VerifiedComponents, "AlsoAsked", 0, 0, "Links");
		Poi.writeExcel(VerifiedComponents, "AlsoAskeds", 1, 0, AlsoAskedLink1);
	}
		
	String RelatedSearches = "//h3[text()='Searches related to word']/../../div[2]//a";

	public void collect_all_the_links_from_the_related_search_results() throws IOException
	{
		List<WebElement> RelatedSearcheLinks = Base.D.findElements(By.xpath(RelatedSearches));
		String RelatedSearcheLink = RelatedSearcheLinks.get(0).getAttribute("href");

		Poi.writeExcel(VerifiedComponents, "RelatedSearches", 0, 0, "Links");
		Poi.writeExcel(VerifiedComponents, "RelatedSearches", 1, 0, RelatedSearcheLink);
	}
		
	String SearchedSideWays = "//div[@id='extrares']/div/div/div[2]/div[1]//div[@data-reltype='sideways']/a/div[2]";

	public void verify_all_ther_common_sideway_areas() throws IOException
	{
		List<WebElement> SearchedSideWayItems = Base.D.findElements(By.xpath( SearchedSideWays ));
		String Item1 = SearchedSideWayItems.get(0).getAttribute("");

		Poi.writeExcel(VerifiedComponents, "SideWayItems", 0, 0, "Items");
		Poi.writeExcel(VerifiedComponents, "SideWayItems", 1, 0, Item1);
	}
		
	String PaginationArea = "//h1[text()='Page navigation']/../table/tbody/tr/td";

	public void verify_the_whole_Google_pagination_area_design() throws IOException
	{
		List<WebElement> Paginations = Base.D.findElements(By.xpath( PaginationArea ));
		assertEquals(Paginations.size(),12);
	}

	public void verify_the_page_footer_area_changes() throws IOException
	{
		WebElement IdentefiedLocation = Base.D.findElement(By.xpath( "//span[text()='India']/following-sibling::div/span[2]" ));
		assertEquals( IdentefiedLocation.getAttribute("text"), "Basavanagudi, Bengaluru, Karnataka" );
	}

	public void click_on_the_Next_till_last_page() throws IOException
	{
		WebElement Next = Base.D.findElement(By.xpath( PaginationArea+"[12]" ));
		while( Next.isDisplayed() )
		{
			Base.W.until( ExpectedConditions.elementToBeClickable( Next ) );
			Next.click();
		}
		if( !Next.isDisplayed() )
			Base.D.close();
	}
}
