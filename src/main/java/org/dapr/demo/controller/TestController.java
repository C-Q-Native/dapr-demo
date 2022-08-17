package org.dapr.demo.controller;

import org.dapr.demo.base.response.Result;
import org.dapr.demo.base.response.ResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "test")
    public Result<Object> test () {
        return ResultResponse.getSuccessResult("hello , test");
    }
}
