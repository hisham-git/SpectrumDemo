package spectrumtests;

import io.restassured.response.*;
import org.junit.runner.RunWith;
import com.greghaskins.spectrum.Spectrum;
import static com.greghaskins.spectrum.dsl.specification.Specification.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(Spectrum.class)
public class SpectrumTests {{

	describe("The list of drivers for the 2017 season", () -> {

		ValidatableResponse response = get("http://ergast.com/api/f1/2017/drivers.json").then();

		String listOfDriverIds = "MRData.DriverTable.Drivers.driverId";

		it("includes Max Verstappen", () -> {

			response.assertThat().body(listOfDriverIds, hasItem("max_verstappen"));
		});

		it("also includes Fernando Alonso and Lewis Hamilton", () -> {

			response.assertThat().body(listOfDriverIds, hasItems("alonso","hamilton"));
		});
	});
}}