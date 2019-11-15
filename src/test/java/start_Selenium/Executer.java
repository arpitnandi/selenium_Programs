package start_Selenium;

import java.io.IOException;

public class Executer 
{
	
	public static void main(String[] args) throws IOException 
	{
		Tests T = new Tests();
		
		T.open_Browser_load_URL_and_verify_the_page("chrome", "http://www.google.com");
		T.type_Search_and_proceed_for_search("java");
		T.verify_the_page_header_area_changes();
		T.verify_the_market_information_are_for_related_search();
		T.verify_the_entire_descriptive_area_for_related_search();
		T.collect_all_the_links_from_the_main_search_results();
		T.collect_the_header_text_on_those_links();
		T.collect_the_URL_under_those_links();
		T.collect_all_the_links_from_the_website_search_results();
		T.collect_all_the_links_from_the_related_search_results();
		T.verify_all_ther_common_sideway_areas();
		T.verify_the_whole_Google_pagination_area_design();
		T.verify_the_page_footer_area_changes();
		T.click_on_the_Next_till_last_page();
	}
}
