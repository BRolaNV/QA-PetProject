package tests.demoqa.tests.bookStoreApplication.baseTest;

import io.restassured.specification.RequestSpecification;
import tests.specifications.Specifications;

public abstract class BaseApiTest {
    protected static final String URL = "https://demoqa.com/";

    protected static RequestSpecification requestSpec() {
        return Specifications.requestSpecificationDemoQA(URL);
    }
}
