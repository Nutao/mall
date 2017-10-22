import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {

    @Test
    public void doGet() throws IOException {
        //创建一个HTTP对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个GET对象
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        // 执行请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 获取响应结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity,"UTF-8"));
        // 关闭HttpClient
        response.close();
        httpClient.close();
    }

    @Test
    public void doGetWithParameter() throws IOException, URISyntaxException {
        //创建一个HTTP对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个uri对象
        URIBuilder uri = new URIBuilder("http://www.baidu.com/s");
        uri.addParameter("wd","java");
        HttpGet httpGet = new HttpGet(uri.build());
        // 执行请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 获取响应结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity,"UTF-8"));
        // 关闭HttpClient
        response.close();
        httpClient.close();
    }

    @Test
    public void doPostWithPara() throws IOException {
        //创建一个HTTP对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // Post对象
        HttpPost post = new HttpPost("http://localhost:8082/test/post.html");
        //创建一个Entity对象,模拟表单
        List<NameValuePair> kv = new ArrayList<>();
        kv.add(new BasicNameValuePair("name","aa"));
        kv.add(new BasicNameValuePair("passwd","2345"));

        StringEntity entity = new UrlEncodedFormEntity(kv);
        // 设置请求的内容
        post.setEntity(entity);
        //执行post
        CloseableHttpResponse response = httpClient.execute(post);
        String string = EntityUtils.toString(response.getEntity());
        System.out.println(string);
        response.close();
        httpClient.close();
    }
}
