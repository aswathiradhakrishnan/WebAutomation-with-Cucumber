import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\gsjij\\Eclipse_Appzlogic\\CucumberBDD\\Feature\\cucumber.feature",
				glue= {"AmazonStepdef"},
				monochrome=true,
				tags="@Amazon",
				plugin={"pretty","html:target/HtmlReports"}
		)

public class TestRunner {

public static void main(String[] args)
	{
		

	}
}