package org.dapr.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.dapr.demo.base.response.Result;
import org.dapr.demo.base.response.ResultResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "test")
    public Result<Object> test (@RequestBody JSONObject data) {

        return ResultResponse.getSuccessResult(data);
    }

    @RequestMapping(value = "testConfirm")
    public Result<Object> testConfirm (@RequestBody JSONObject data) {
        System.out.println(data);
        return ResultResponse.getSuccessResult(data);
    }
}
