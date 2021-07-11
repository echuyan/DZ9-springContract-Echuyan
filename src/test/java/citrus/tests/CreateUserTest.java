package citrus.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.sun.istack.NotNull;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.notNullValue;


public class CreateUserTest extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient restClient;
    private TestContext context;



    @Test(description="Создание нового пользователя")
    @CitrusTest
    public void getTestsActions () {
    this.context=citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .post("users")
                .messageType(MessageType.JSON)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .payload("{ \"name\": \"morpheus\", \"job\": \"leader\"}"));




        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response(HttpStatus.CREATED)
                .messageType(MessageType.JSON)
                .validate("$.name","morpheus")
                .validate("$.job","leader")
                .validate("$.id", notNullValue())
        );


    }

    }
