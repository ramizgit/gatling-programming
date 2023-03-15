package tatkalcare.customer;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class CustomerSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://tatkalcares.com/api/v1");

    ScenarioBuilder scenario1 = scenario("Login1").exec(TestScenario.scenaio("8884592118"));
    ScenarioBuilder scenario2 = scenario("Login2").exec(TestScenario.scenaio("8073116488"));
    ScenarioBuilder scenario3 = scenario("Login3").exec(TestScenario.scenaio("6295490736"));
    ScenarioBuilder scenario4 = scenario("Login4").exec(TestScenario.scenaio("9333167446"));
    ScenarioBuilder scenario5 = scenario("Login5").exec(TestScenario.scenaio("7001449030"));
    ScenarioBuilder scenario6 = scenario("Login6").exec(TestScenario.scenaio("7676139621"));
    ScenarioBuilder scenario7 = scenario("Login7").exec(TestScenario.scenaio("8296887274"));
    ScenarioBuilder scenario8 = scenario("Login8").exec(TestScenario.scenaio("8588829759"));
    ScenarioBuilder scenario9 = scenario("Login9").exec(TestScenario.scenaio("8660411237"));
    ScenarioBuilder scenario10 = scenario("Login10").exec(TestScenario.scenaio("8759689412"));

    {
        System.out.println("Customer api simulation !!!");
        setUp(
                scenario1.injectOpen(rampUsers(1).during(1)),
                scenario2.injectOpen(rampUsers(1).during(1)),
                scenario3.injectOpen(rampUsers(1).during(1)),
                scenario4.injectOpen(rampUsers(1).during(1)),
                scenario5.injectOpen(rampUsers(1).during(1)),
                scenario6.injectOpen(rampUsers(1).during(1)),
                scenario7.injectOpen(rampUsers(1).during(1)),
                scenario8.injectOpen(rampUsers(1).during(1)),
                scenario9.injectOpen(rampUsers(1).during(1)),
                scenario10.injectOpen(rampUsers(1).during(1))
        ).protocols(httpProtocol);
    }
}
