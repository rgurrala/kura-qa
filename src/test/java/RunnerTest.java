import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/Resources/FeatureFiles",plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"}

        , tags = {"@test"})
public class RunnerTest {
}