package PageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
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
	By allprodDesc = By.xpath("//*[@class='a-price-whole']//preceding::*[@class='a-size-base-plus a-color-base a-text-normal']");
	By allproductsprice = By.xpath("//*[@class='a-price-whole']");
	By producttitle = By.xpath("//*[@id='productTitle']");
	By addtocart = By.xpath("//input[@id='add-to-cart-button']");
	By cart =By.xpath("//*[@id='attach-sidesheet-view-cart-button']/span/input");
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

	/*public List<WebElement> getProducts() {
		return driver.findElements(allprodDesc);
	}
	public List<WebElement> getPrices() {
		return driver.findElements(allproductsprice); 
	} */

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
	public void getProductsList() throws IOException {
		//AmazonPO obj = new AmazonPO(driver);
		//obj.getProducts();
		//obj.getPrices();
		List<WebElement> allproddesc = driver.findElements(allprodDesc);
		List<WebElement> allprodprice = driver.findElements(allproductsprice);
		int rowprodsize  = allproddesc.size();
		int rowpricesize = allprodprice.size();

		File file = new File("C:\\Users\\gsjij\\Eclipse_Appzlogic\\CucumberBDD\\ExcelFile\\Test.xls");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.getSheet("ProductDetails");
		for (WebElement currentdesc : allproddesc) {
			String desc = currentdesc.getText();
			for (int i = 0; i<rowprodsize-1; i++)
			{
				sheet.getRow(rowprodsize).createCell(0).setCellValue(desc);

			}
		}
		for (WebElement currentprice : allprodprice) {
			String price = currentprice.getText();
			for (int i = 0; i<rowpricesize-1; i++)
			{
				sheet.getRow(rowpricesize).createCell(1).setCellValue(price);

			}

		}

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();
	}

	public void selectProduct() {
		AmazonPO obj = new AmazonPO(driver);
		obj.getProduct().click();
	}
	public boolean verifyProductdesc() throws IOException {
		AmazonPO obj = new AmazonPO(driver);
		boolean status = false;
		String parenthandle = driver.getWindowHandle();
		Set <String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(parenthandle)) {
				driver.switchTo().window(handle);
				String  productdesc = obj.getproductTitle().getText();
				System.out.println("The description of product is same as the select product's description "+productdesc);
				obj.getProductdesc();
				String getdesc = obj.getProduct().getText();
				if (productdesc.contains(getdesc)){
					System.out.println("The description of product is same as the select product's description");
					status = true;
				}
			}

		}
		return status;
	}

	public void selectAddtoCart() {
		AmazonPO obj = new AmazonPO(driver);
		obj.getAddtoCart().click();
	}


	public void selectCartbtn() throws InterruptedException {
		AmazonPO obj = new AmazonPO(driver);
		Thread.sleep(3000);
				//driver.switchTo().frame(2);
				obj.getCartbtn().click();
			
		
	}
	/*public boolean prodinCart() {
		AmazonPO obj = new AmazonPO(driver);
		boolean product = obj.getProductinCart().isEnabled();
		return product;
	} */

	public boolean verifyCart() {
		AmazonPO obj = new AmazonPO(driver);
		boolean status = false;
		String getdesc = obj.getProduct().getText();
		String productinCart = obj.getProductinCart().getText();
		if (productinCart.contains(getdesc)){
			System.out.println("The description of product is same as the select product's description");
			status = true;
		}
		return status;
	}
}
