import Models.Pets;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;


import java.util.Random;

import static io.restassured.RestAssured.given;

public class Pets_Test extends Base {


    public int CreatePet() {

        //Create random petID
        Random rand = new Random();
        int petID = rand.nextInt(1000);

        //Create request payload
        Pets payload = Pets.builder().id(petID).name("Pet_" + petID).build();

        //Create Request
        RequestSpecification request = given().spec(Base.createBaseRequest()).basePath("/pet").body(payload).log().body();

        //Get Response [POST]
        Response res = request.post();
        res.getBody().prettyPrint();

        //Response Validations
        ValidatableResponse resValidations = res.then();

        resValidations.assertThat().statusCode(200);

        return petID;
    }

    @Test
    @DisplayName("CreatePet_Success_200")
    public void verifyUserCreation_Success_200() {

        //Pre-Requisite
        int petID = CreatePet();

        //Create Request
        RequestSpecification request = given().spec(Base.createBaseRequest()).basePath("/pet/" + petID);

        //Get Response [GET]
        Response res = request.get();
        res.getBody().prettyPrint();

        //Response Validations
        ValidatableResponse resValidations = res.then();

        resValidations.assertThat().statusCode(200);
        resValidations.assertThat().body("id", Matchers.equalTo(petID));
        resValidations.assertThat().body("name", Matchers.equalTo("Pet_" + petID));

    }

       /* @Test
        @DisplayName("DeletePet_Success_200")
        public void verifyUserDeletion_Success_200(){

            //Pre-Requisite
            int petID = CreatePet();

            //Create Request
            RequestSpecification request = given().spec(Base.createBaseRequest()).basePath("/pet/" + petID);

            //Get Response [DELETE]
            Response res = request.delete();
            res.getBody().prettyPrint();

            //Response Validations
            ValidatableResponse resValidations = res.then();

            resValidations.assertThat().statusCode(200);
            resValidations.assertThat().body("message", Matchers.equalTo(userName));
        }*/

}