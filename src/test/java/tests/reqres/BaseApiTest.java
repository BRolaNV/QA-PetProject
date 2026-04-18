package tests.reqres;

import io.restassured.specification.RequestSpecification;
import tests.specifications.Specifications;

public abstract class BaseApiTest {
    protected static final String URL = "https://reqres.in/";
    protected static final String API_KEY = APIReader.getApiKey();

    protected static RequestSpecification requestSpec() {
        return Specifications.requestSpecificationReqRes(URL, API_KEY);
    }
}
