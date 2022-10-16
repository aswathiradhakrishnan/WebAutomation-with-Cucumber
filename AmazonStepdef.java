package Stepdef;


import static org.testng.AssertJUnit.assertTrue;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import PageObject.AmazonPO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonStepdef {

	WebDriver driver;
	public AmazonPO obj ;

	@Given("The Home page is visible")
	public void the_Home_page_is_visible() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(ChromeOptions.CAPABILITY,options);
		options.merge(cap);
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");	
		driver.manage().window().maximize();
	}

	@When("Select Electronics from the category displayed on the upper bar")
	public void select_Electronics_from_the_category_displayed_on_the_upper_bar() {
		obj= new AmazonPO(driver);
		obj.clickCategory();
	}

	@Then("Get the Description text displayed for any product you want to select\\/buy and save that in properties file")
	public void get_the_Description_text_displayed_for_any_product_you_want_to_select_buy_and_save_that_in_properties_file() {
		obj= new AmazonPO(driver);
		obj.clickBrand();
	}

	@Then("Get the description and price for all the product displayed on the page and write down them into an excel file , In the columns - Product Description and Product Price respectively")
	public void get_the_description_and_price_for_all_the_product_displayed_on_the_page_and_write_down_them_into_an_excel_file_In_the_columns_Product_Description_and_Product_Price_respectively() throws IOException {
		obj= new AmazonPO(driver);
		obj.getProductdesc();
	}



	@Then("Verify the options are displayed according the the filter")
	public void verify_the_options_are_displayed_according_the_the_filter() {

	}

	@When("Select the product you fetched the description for")
	public void select_the_product_you_fetched_the_description_for() {
		obj= new AmazonPO(driver);
		obj.selectProduct();
	}

	@Then("Verify the the description and price of the product opened in new tab should be same as the description and price you written in properties file")
	public void verify_the_the_description_and_price_of_the_product_opened_in_new_tab_should_be_same_as_the_description_and_price_you_written_in_properties_file() throws IOException {
		obj= new AmazonPO(driver);
		Assert.assertTrue(obj.verifyProductdesc());

	}

	@When("Click on Add to cart")
	public void click_on_Add_to_cart() {
		obj= new AmazonPO(driver);
		obj.selectAddtoCart();
	}

	@Then("Click on Cart icon")
	public void click_on_Cart_icon() throws InterruptedException {
		obj= new AmazonPO(driver);
		obj.selectCartbtn();

	}

	@Then("Verify that the right product is added in the cart , you can verify with description and price\"")
	public void verify_that_the_right_product_is_added_in_the_cart_you_can_verify_with_description_and_price() {
		obj= new AmazonPO(driver);
		Assert.assertTrue(obj.verifyCart());
	}

}
