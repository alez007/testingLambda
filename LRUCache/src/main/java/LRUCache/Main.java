package LRUCache;

import LRUCache.model.Env;
import LRUCache.model.LRU;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class Main implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private LRU<String, String> lru;
    private String graffitiBasePath;
    private boolean lastCacheStatus = false;
    public Main() {
        this.lru = new LRU<>(Integer.valueOf(Env.get("LRU_MAX_ENTRIES")));
        this.graffitiBasePath = Env.get("GRAFFITI_BASE_PATH");
    }

    public Main(int maxEntries, String graffitiBasePath) {
        this.lru = new LRU<>(maxEntries);
        this.graffitiBasePath = graffitiBasePath;
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        String path = apiGatewayProxyRequestEvent.getPath();
        String content;
        if (this.lru.containsKey(path)) {
            this.lastCacheStatus = true;
            content = this.lru.get(path);
        } else {
            this.lastCacheStatus = false;
            content = this.getContentFromGraffiti(path);
            this.lru.put(path, content);
        }

        return (new APIGatewayProxyResponseEvent()).withBody(content);
    }

    private String getContentFromGraffiti(String path) {
        String graffitiUri = this.graffitiBasePath + path;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL graffitiUrl = new URL(graffitiUri);
            URLConnection urlConnection = graffitiUrl.openConnection();

            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }

    public boolean getLastCacheStatus() {
        return lastCacheStatus;
    }
}
