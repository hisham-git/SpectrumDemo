package spectrumtests;

import io.restassured.response.*;

import org.junit.runner.RunWith;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(Spectrum.class)
public class SpectrumTestsGherkin {{

	feature("2017 driver list verification", () -> {

		scenario("Verify that max_verstappen is in the list of 2017 drivers", () -> {

			final Variable<String> endpoint = new Variable<>();
			final Variable<Response> response = new Variable<>();

			given("we have an endpoint that gives us the list of 2017 drivers", () -> {
				
				endpoint.set("http://ergast.com/api/f1/2017/drivers.json");
			});

			when("we retrieve the list from that endpoint", () -> {
				
				response.set(get(endpoint.get()));
			});
			then("max_verstappen is in the driver list", () -> {
				
				response.get().then().assertThat().body("MRData.DriverTable.Drivers.driverId", hasItem("max_verstappen"));
			});
		});

		scenarioOutline("Verify that there are also some other people in the list of 2017 drivers", (driver) -> {

			final Variable<String> endpoint = new Variable<>();
			final Variable<Response> response = new Variable<>();

			given("We have an endpoint that gives us the list of 2017 drivers", () -> {
				
				endpoint.set("http://ergast.com/api/f1/2017/drivers.json");
			});

			when("we retrieve the list from that endpoint", () -> {
				
				response.set(get(endpoint.get()));
			});
			then(driver + " is in the driver list", () -> {
				
				response.get().then().assertThat().body("MRData.DriverTable.Drivers.driverId", hasItem(driver));
			});
		},

		withExamples(
				example("hamilton"),
				example("alonso"),
				example("vettel")
			)
		);
	});
}}