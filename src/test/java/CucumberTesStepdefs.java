import com.example.abstractionapp.webserviceabstractionapp.CucumberTest;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberTesStepdefs {
    CucumberTest cucumberTest;

    @Given("Create a test cucumber testing")
    public void createATestCucumberTesting() throws Throwable{
        cucumberTest = new CucumberTest();
    }

    @When("I play with number {int}")
    public void iPlayWithNumber(int arg0) throws Throwable {
        cucumberTest.testing(arg0);
    }

    @Then("The result was cool")
    public void theResultWasCool() throws Throwable{
        throw new PendingException();
    }
}
