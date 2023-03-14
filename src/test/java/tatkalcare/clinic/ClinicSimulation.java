package tatkalcare.clinic;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ClinicSimulation /*extends Simulation*/ {
    ChainBuilder edit =
            // let's try at max 2 times
            tryMax(2)
                    .on(
                            exec(
                                    http("post")
                                            //.post("/login?mobile=8884592118&sponsor=123456")
                                            .post("/clinicwisedoctor-for-clinic")
                                            .header("token", "Bearer 1229|aXeNxqymqffVhK8XZhpAwrmQIqqzX24u9GQYdNlw")
                                            .check(
                                                    status().in(200, 201)
                                            )
                            ).pause(1).exec(
                                    http("post")
                                            //.post("/login?mobile=8884592118&sponsor=123456")
                                            .post("/clinicwisedoctor-for-clinic")
                                            .header("token", "Bearer 1229|aXeNxqymqffVhK8XZhpAwrmQIqqzX24u9GQYdNlw")
                                            .check(
                                                    status().in(200, 201)
                                            )
                            )
                    )
                    // if the chain didn't finally succeed, have the user exit the whole scenario
                    .exitHereIfFailed();

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://tatkalcares.com/api/v1/clinic");

    ScenarioBuilder admins = scenario("Login").exec(edit);

    {
        System.out.println("clinic api simulation !!!");
        /*setUp(
                admins.injectOpen(rampUsers(10).during(1))
        ).protocols(httpProtocol);*/
    }
}
