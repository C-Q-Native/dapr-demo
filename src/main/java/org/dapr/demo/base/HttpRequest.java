package org.dapr.demo.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;

@Component
public class HttpRequest<T> {

    private Logger logger = LoggerFactory.getLogger(HttpRequest.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;

    /**
     * 解决部分get请求utf8编码乱码问题
     * @param url
     * @return
     */
    public JSONObject GETUTF8(String url) {
        try {
            URL url1 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection)url1.openConnection();
            // 将返回的输入流转换成字符串
            InputStream inputStream = urlConnection.getInputStream();
            // 指定编码格式
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader in = new BufferedReader(inputStreamReader);
            String jsonStr =in.readLine().toString();
            // 释放资源
            inputStream.close();
            inputStream = null;
            urlConnection.disconnect();
            JSONObject jsonUserObject = JSONObject.parseObject(jsonStr);
            return jsonUserObject;
        } catch(Exception e) {
            return null;
        }
    }


    /**
     * 不带参数的get请求
     * @param url
     * @return
     */
    public JSONObject GET(String url){
        HttpHeaders headers = new HttpHeaders();
        this.appendHeaders(headers);
        HttpEntity<String> httpEntity = new HttpEntity<>( headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        String response = exchange.getBody();
        return JSON.parseObject(response);
    }


    /**
     * POST请求
     * @param url
     * @param params
     * @return
     */
    public JSONObject POST(String url, JSONObject params) {
        return this.POST(url, params, new HashMap<String, String>());
    }

    /**
     * 以表单格式发送请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public JSONObject POST(String url, MultiValueMap<String, String> params, HttpHeaders headers){

        if (headers == null) {
            //设置Http Header
            headers = new HttpHeaders();
            //设置请求媒体数据类型
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            //设置返回媒体数据类型
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        }
        this.appendHeaders(headers);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        return JSON.parseObject(response.getBody());
    }

    /**
     * 以json格式发送数据
     * @param url
     * @param params
     * @param headerParams
     * @return
     */
    public JSONObject POST(String url, JSONObject params, Map<String, String> headerParams){
        if (params == null) {
            params = new JSONObject();
        }
        //设置Http Header
        HttpHeaders headers = new HttpHeaders();
        //设置请求媒体数据类型
        headers.setContentType(MediaType.APPLICATION_JSON);
        //设置返回媒体数据类型
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        if (headerParams != null) {
            for(String key:headerParams.keySet()) {
                headers.add(key, headerParams.get(key));
            }
        }
        this.appendHeaders(headers);
        try {
            HttpEntity<String> formEntity = new HttpEntity<String>(params.toString(), headers);
            logger.info(headers.toString());
            String resBody = restTemplate.postForObject(url, formEntity, String.class);
            return JSON.parseObject(resBody);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    /**
     * 扩展header头信息
     */
    private void appendHeaders(HttpHeaders headers){
        try {
            if (request.getHeader("x-request-id") != null && !request.getHeader("x-request-id").equals("")) {
                headers.add("x-request-id", request.getHeader("x-request-id"));
            }

            if (request.getHeader("x-b3-traceid") != null && !request.getHeader("x-b3-traceid").equals("")) {
                headers.add("x-b3-traceid", request.getHeader("x-b3-traceid"));
            }

            if (request.getHeader("x-b3-spanid") != null && !request.getHeader("x-b3-spanid").equals("")) {
                headers.add("x-b3-spanid", request.getHeader("x-b3-spanid"));
            }

            if (request.getHeader("x-b3-parentspanid") != null && !request.getHeader("x-b3-parentspanid").equals("")) {
                headers.add("x-b3-parentspanid", request.getHeader("x-b3-parentspanid"));
            }

            if (request.getHeader("x-b3-sampled") != null && !request.getHeader("x-b3-sampled").equals("")) {
                headers.add("x-b3-sampled", request.getHeader("x-b3-sampled"));
            }

            if (request.getHeader("x-b3-flags") != null && !request.getHeader("x-b3-flags").equals("")) {
                headers.add("x-b3-flags", request.getHeader("x-b3-flags"));
            }

            if (request.getHeader("x-ot-span-context") != null && !request.getHeader("x-ot-span-context").equals("")) {
                headers.add("x-ot-span-context", request.getHeader("x-ot-span-context"));
            }
        } catch (Exception e) {

        }
    }
}
