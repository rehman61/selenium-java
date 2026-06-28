package tests.api.github;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.api.githubModel.RepoModel;
import utils.setup.BaseApiClass;


import static org.hamcrest.Matchers.equalTo;

public class GithubApiTest extends BaseApiClass {
    @Test
    public void getUserData() {
        RestAssured.baseURI = configReader.getProperty("githubApiUrl");
        Response response = (Response) RestAssured
                .given()
                .when()
                .get("/users/octocat")
                .then()
                .statusCode(200)
                .body("login", equalTo("octocat"))
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void createNewRepo() {
        RestAssured.baseURI = configReader.getProperty("githubApiUrl");
//      github api token parts
        String token =
                        configReader.getProperty("t1")+configReader.getProperty("t2")
                        +configReader.getProperty("t3")+configReader.getProperty("t4")
                        +configReader.getProperty("t5");
//      Gson payload body format
        RepoModel repo = new RepoModel(
                "repo-1",
                "create repo with rest assured",
                false);
//      convert Gson payload body to Json payload body format
        Gson gson = new Gson();
        String jsonPayload = gson.toJson(repo);

//      POST
        RestAssured
                .given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post("/user/repos")
                .then()
                .statusCode(201)
                .body("name", equalTo("repo-1"))
                .body("description", equalTo("create repo with rest assured"))
                .body("private", equalTo(false))
                .extract()
                .response();
    }

    @Test
    public void updateRepo() {
        RestAssured.baseURI = configReader.getProperty("githubApiUrl");
//      github api token parts
        String token = configReader.getProperty("t1")+configReader.getProperty("t2")
                +configReader.getProperty("t3")+configReader.getProperty("t4")
                +configReader.getProperty("t5");
//      Gson payload body format
        RepoModel repo = new RepoModel(
                "repo-1-update",
                "update repo with rest assured",
                false);
//      convert Gson payload body to Json payload body format
        Gson gson = new Gson();
        String jsonPayload = gson.toJson(repo);

//      PATCH
        RestAssured
                .given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .log().all()
                .when()
                .patch("/repos/rehman61/repo-1-update4")
                .then()
                .statusCode(200)
                .body("name", equalTo("repo-1-update"))
                .body("description", equalTo("update repo with rest assured"))
                .body("private", equalTo(false));


    }
}
