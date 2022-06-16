package org.dapr.demo.controller;

import com.alibaba.fastjson.JSONObject;
//import com.ivydad.commonconf.base.response.Result;
//import com.ivydad.commonconf.base.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Async;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    //@Autowired
    //private HttpRequest httpRequest;

    @Value("${RUN_ENV:}")
    private String RUN_ENV;

    @Value("${FEATURE:}")
    private String FEATURE;

    @RequestMapping(value = "test")
    public Object[] test () {
        String arr = "this is string";
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("title", "title");
        Object[] response = {arr,json};
        return response;
        //return ResultResponse.getSuccessResult(user);
    }

}
