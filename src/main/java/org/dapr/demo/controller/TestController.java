package org.dapr.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.dapr.demo.base.response.Result;
import org.dapr.demo.base.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.dapr.demo.base.HttpRequest;
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private HttpRequest httpRequest;

    @RequestMapping(value = "test")
    public Result<Object> test () {
        String arr = "this is string";
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("title", "title");
        Object[] response = {arr,json};
        return ResultResponse.getSuccessResult(response);
    }

}
