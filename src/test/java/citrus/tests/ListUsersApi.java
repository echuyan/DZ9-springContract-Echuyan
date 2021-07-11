package citrus.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.builder.HttpActionBuilder;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class ListUsersApi extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient restClient;
    private TestContext context;


     private Logger logger = LogManager.getLogger(ListUsersApi.class);


    @Test(description = "Получение информации о списке пользователей Вы борочные проверки полученного ответа")
    @CitrusTest
    public void getTestsActions() {
        this.context = citrus.createTestContext();


        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("users?page=2")
        );



       http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response(HttpStatus.OK)
                .name("ddd")
                .messageType(MessageType.JSON)
                .validate("$.page",2)
               .validate("$.data[2].email","tobias.funke@reqres.in")
        );
    }


}
