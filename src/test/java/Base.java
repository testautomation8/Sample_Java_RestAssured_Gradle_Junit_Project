import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public class Base {

    @BeforeClass
    public static RequestSpecification createBaseRequest(){

        RequestSpecification baseRequest = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .setContentType(ContentType.JSON)
                .build();

        return baseRequest;
    }

}
