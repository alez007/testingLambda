package lrucache;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void mainTest() {
        Main main = new Main(10, "http://qa15.timeout.com:9090");
        main.handleRequest(new APIGatewayProxyRequestEvent().withPath("/public/v1/sites?view=complete"), null);
        Assert.assertFalse(main.getLastCacheStatus());
        main.handleRequest(new APIGatewayProxyRequestEvent().withPath("/public/v1/sites?view=complete"), null);
        Assert.assertTrue(main.getLastCacheStatus());
        main.handleRequest(new APIGatewayProxyRequestEvent().withPath("/public/v1/sites?view=complete"), null);
        Assert.assertTrue(main.getLastCacheStatus());
        main.handleRequest(new APIGatewayProxyRequestEvent().withPath("/public/v1/sites?view=complete"), null);
        Assert.assertTrue(main.getLastCacheStatus());
        main.handleRequest(new APIGatewayProxyRequestEvent().withPath("/public/v1/sites?view=complete"), null);
        Assert.assertTrue(main.getLastCacheStatus());
        main.handleRequest(new APIGatewayProxyRequestEvent().withPath("/public/v1/sites?view=complete"), null);
        Assert.assertTrue(main.getLastCacheStatus());

    }
}
