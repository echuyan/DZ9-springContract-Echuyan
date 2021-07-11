package citrus.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;


public class UpdateUserTest extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient restClient;
    private TestContext context;



    @Test(description="Апдейт пользователя")
    @CitrusTest
    public void getTestsActions () {
    this.context=citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .put("users/2")
                .messageType(MessageType.JSON)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .payload("{ \"name\": \"morpheus\", \"job\": \"zion resident\"}"));



        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .validate("$.name","morpheus")
                .validate("$.job","zion resident")
                .validate("$.updatedAt", startsWith(simpleDate.format(date).toString()))
        );


    }

    }
