package tatkalcare.customer;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static tatkalcare.input.InputParam.*;

public class TestScenario {

    public static ChainBuilder scenaio(String mobile)
    {
        return
                repeat(TIMES)
                        .on(
                                exec(
                                        http("login").
                                                post("/customer/login?mobile="+mobile).
                                                check(status().in(HTTP_OK, HTTP_CREATED)).
                                                check(jsonPath("$.data.access_token").saveAs("tokenId"))
                                ).
                                        exec(
                                                http("search-doctor-with-keyword").
                                                        post("/searchdoctor?keyword=chak").
                                                        check(status().in(HTTP_OK, HTTP_CREATED))
                                        ).
                                        exec(
                                                http("search-doctor-by-department").
                                                        post("/doctorwithdepartment?id=6").
                                                        check(status().in(HTTP_OK, HTTP_CREATED))
                                        ).
                                        exec(
                                                http("search-doctor-by-symptom").
                                                        post("/doctorwithsymptomname?symptom=physician").
                                                        check(status().in(HTTP_OK, HTTP_CREATED))
                                        ).
                                        exec(
                                                http("search-doctor-by-symptom-and-pincode").
                                                        post("/doctorwithsymptomnamepincode?symptom=hair&pincode=713325").
                                                        check(status().in(HTTP_OK, HTTP_CREATED))
                                        )
                        )
                        .exitHereIfFailed();
    }
}
