import Models.Users;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class User_Test extends Base{

	public String CreateUser() {

		//Create random username
		String userName = "User_" + UUID.randomUUID().toString();

		//Create request payload
		Users payload = Users.builder().username(userName).build();

        //Create Request
        RequestSpecification request = given().spec(Base.createBaseRequest()).basePath("/user").body(payload).log().body();

        //Get Response [POST]
        Response res = request.post();
        res.getBody().prettyPrint();

        //Response Validations
        ValidatableResponse resValidations = res.then();

        resValidations.assertThat().statusCode(200);

		return userName;
	}

	@Test
    @DisplayName("CreateUser_Success_200")
	public void verifyUserCreation_Success_200() {

		//Pre-Requisite
	    String userName = CreateUser();

		//Create Request
        RequestSpecification request = given().spec(Base.createBaseRequest()).basePath("/user/" + userName);

        //Get Response [GET]
        Response res = request.get();
		res.getBody().prettyPrint();

		//Response Validations
        ValidatableResponse resValidations = res.then();

		resValidations.assertThat().statusCode(200);
        resValidations.assertThat().body("username", Matchers.equalTo(userName));

	}

	@Test
    @DisplayName("DeleteUser_Success_200")
	public void verifyUserDeletion_Success_200(){

        //Pre-Requisite
        String userName = CreateUser();

        //Create Request
        RequestSpecification request = given().spec(Base.createBaseRequest()).basePath("/user/" + userName);

        //Get Response [DELETE]
        Response res = request.delete();
        res.getBody().prettyPrint();

        //Response Validations
        ValidatableResponse resValidations = res.then();

        resValidations.assertThat().statusCode(200);
        resValidations.assertThat().body("message", Matchers.equalTo(userName));
	}

}
