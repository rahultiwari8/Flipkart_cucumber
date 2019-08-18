package runnerPackage;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		features= "src/test/resources/",
		glue= {"stepDefinition"},
		dryRun=false,
		monochrome=true,
				 plugin = { "pretty", "html:target/cucumber-reports" }
		)

public class TestRunenr {

}
