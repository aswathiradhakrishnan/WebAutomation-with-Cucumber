package PageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonPO {
	public WebDriver driver;

	By category = By.xpath("//a[text()=' Electronics ']");
	By brand = By.xpath("//*[text()='OnePlus']");
	By product = By.xpath("//*[contains(text(),'OnePlus Nord CE 2 Lite 5G (Blue Tide')]");
	By allproducts = By.xpath("//*[@class='a-price-whole']");
	By producttitle = By.xpath("//*[@id='productTitle'][contains(text(),'OnePlus Nord CE 2 Lite 5G')]");
	By addtocart = By.xpath("//*[@id='add-to-cart-button']");
	By cart =By.linkText("Cart");
	By productinCart = By.xpath("//*[contains(text(),'OnePlus Nord CE 2 Lite 5G (Blue Tide')]");
	
	
	//Constructor
	public AmazonPO(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getCategory() {
		return driver.findElement(category);
	}
	public WebElement getBrand() {
		return driver.findElement(brand);
	}

	public WebElement getProduct() {
		return driver.findElement(product);
		
	}

	public List<WebElement> getProducts() {
		return driver.findElements(allproducts);
	}
	public WebElement getproductTitle() {
		return driver.findElement(producttitle);
	}

	public WebElement getAddtoCart() {
		return driver.findElement(addtocart);
	}
	public WebElement getCartbtn() {
		return driver.findElement(cart);
	}
	public WebElement getProductinCart() {
		return driver.findElement(productinCart);
	}
	
	
	
	public void clickCategory() {
		AmazonPO obj  = new AmazonPO(driver);
		obj.getCategory().click();
	}

	public void clickBrand() {
		AmazonPO obj = new AmazonPO(driver);
		obj.getBrand().click();
	}
	public void clickAddtoCart() {
		AmazonPO obj = new AmazonPO(driver);
		obj.getAddtoCart();
	}
	
	public void getProductdesc() throws IOException {
		AmazonPO obj = new AmazonPO(driver);
		String getdesc = obj.getProduct().getText();
		System.out.println(getdesc);
		//Create an object of FileWriter class
		FileWriter fr = new FileWriter("C:\\Users\\gsjij\\Eclipse_Appzlogic\\CucumberBDD\\target\\application.properties");
		//Create object of property class
		Properties prop = new Properties();
		//Use Set property to store
		prop.setProperty("productdescription", getdesc);
		prop.store(fr,"comments");
	}
	/*public void getProductsList() throws IOException {
		AmazonPO obj = new AmazonPO(driver);
		java.util.List<WebElement> rows = obj.getProducts();
		int rows_count = rows.size();
		for (int i= 0; i<rows_count; i++) {
			java.util.List<WebElement> cols = (List<WebElement>) rows.get(i);
		}

		}
				HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		sheet.createRow(0);
		sheet.getRow(0).createCell(0);
		File file = new File("C:\\Users\\gsjij\\Eclipse_Appzlogic\\CucumberBDD\\ExcelFile\\Test.xls");
		FileOutputStream fos = new FileOutputStream(file)
		workbook.write(fos);
		workbook.close();
	}*/

	public void selectProduct() {
		AmazonPO obj = new AmazonPO(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		obj.getProduct().click();
	}
	public boolean verifyProductdesc() throws IOException {
		AmazonPO obj = new AmazonPO(driver);
		obj.getProductdesc();
		String productdesc = obj.getproductTitle().getText();
		String getdesc = obj.getProduct().getText();
		boolean value = productdesc.contains(getdesc);
		return value;
	}

	public void selectAddtoCart() {
		AmazonPO obj = new AmazonPO(driver);
		obj.getAddtoCart().click();
	}


	public void selectCartbtn() {
		AmazonPO obj = new AmazonPO(driver);
		obj.getCartbtn().click();
	}

	public boolean verifyCart() {
		AmazonPO obj = new AmazonPO(driver);
		String productdesc = obj.getproductTitle().getText();
		String productinCart = obj.getProductinCart().getText();
		boolean visible = productdesc.contains(productinCart);
		return visible;
	}
}
