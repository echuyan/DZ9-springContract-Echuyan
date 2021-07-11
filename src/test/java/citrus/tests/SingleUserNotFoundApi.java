package citrus.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

public class SingleUserNotFoundApi extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient restClient;
    private TestContext context;


     private Logger logger = LogManager.getLogger(SingleUserNotFoundApi.class);


    @Test(description = "Отправка запроса на неверный endpoint")
    @CitrusTest
    public void getTestsActions() {
        this.context = citrus.createTestContext();


        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("users/23")
        );



       http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response(HttpStatus.NOT_FOUND)

        );
    }


}
