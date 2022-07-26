package org.dapr.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.dapr.demo.base.response.Result;
import org.dapr.demo.base.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.dapr.demo.service.Dapr.ConfigurationService;
import io.dapr.client.domain.ConfigurationItem;

@RestController
@RequestMapping(value = "/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping(value = "configuration")
    public Result<Object> configuration () {
        String key = "myconfig1";
        ConfigurationItem configItem = configurationService.getConfigurationForaSingleKey(key);
        JSONObject data = new JSONObject();
        data.put("version", configItem.getVersion());
        data.put("key", configItem.getKey());
        data.put("value", configItem.getValue());
        return ResultResponse.getSuccessResult(data);
    }
}
