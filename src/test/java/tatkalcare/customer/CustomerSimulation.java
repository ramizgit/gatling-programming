package tatkalcare.customer;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import tatkalcare.input.InputParam;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CustomerSimulation extends Simulation {

    ChainBuilder edit =
            tryMax(2)
                    .on(
                            exec(
                                    http("login").
                                            //post("/customer/login?mobile=8884592118").
                                            post( x -> InputParam.getInputParam()).
                                            check(status().in(200, 201)).
                                            check(jsonPath("$.data.access_token").saveAs("tokenId"))
                            ).
                                    pause(1).
                                    exec(
                                            http("search-doctor-with-keyword")
                                                    .post("/searchdoctor?keyword=chak")
                                                    .check(
                                                            status().in(200, 201)
                                                    )
                                    ).
                                    pause(1).
                                    exec(
                                            http("search-doctor-by-department")
                                                    .post("/doctorwithdepartment?id=6")
                                                    .check(
                                                            status().in(200, 201)
                                                    )
                                    ).
                                    pause(1).
                                    exec(
                                            http("search-doctor-by-symptom")
                                                    .post("/doctorwithsymptomname?symptom=physician")
                                                    .check(
                                                            status().in(200, 201)
                                                    )
                                    ).
                                    pause(1).
                                    exec(
                                            http("search-doctor-by-symptom-and-pincode")
                                                    .post("/doctorwithsymptomnamepincode?symptom=hair&pincode=713325")
                                                    .check(
                                                            status().in(200, 201)
                                                    )
                                    )
                    )
                    .exitHereIfFailed();

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://tatkalcares.com/api/v1");

    ScenarioBuilder admins = scenario("Login").exec(edit);

    {
        System.out.println("customer api simulation !!!");
        setUp(
                admins.injectOpen(rampUsers(10).during(1))
        ).protocols(httpProtocol);
    }
}
