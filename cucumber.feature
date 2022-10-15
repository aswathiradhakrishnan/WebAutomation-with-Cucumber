Feature: Amazon add to cart
@Amazon
Scenario: Verify right products added to amazon cart
	Given The Home page is visible
  	Then Select Electronics from the category displayed on the upper bar
    Then Get the Description text displayed for any product you want to select/buy and save that in properties file
    And Get the description and price for all the product displayed on the page and write down them into an excel file , In the columns - Product Description and Product Price respectively
    Then Verify the options are displayed according the the filter
    When Select the product you fetched the description for 
    Then Verify the the description and price of the product opened in new tab should be same as the description and price you written in properties file
    When Click on Add to cart
    Then Click on Cart icon
    And Verify that the right product is added in the cart , you can verify with description and price"
    
    
    