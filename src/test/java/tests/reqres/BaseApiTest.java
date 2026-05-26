package tests.reqres;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import tests.specifications.Specifications;

@Execution(ExecutionMode.SAME_THREAD)
public abstract class BaseApiTest {
    protected static final String URL = "https://reqres.in/";
    protected static final String API_KEY = APIReader.getApiKey();

    protected static RequestSpecification requestSpec() {
        return Specifications.requestSpecificationReqRes(URL, API_KEY);
    }
}
