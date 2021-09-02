package com.example.apicalltest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
public class ApiController {
    @RequestMapping("/postcall")
    @ResponseBody
    public Object apiCall(@RequestBody Map<String, Object> param) throws JsonProcessingException {
        Map<String, Object> responseMap = new HashMap<>();
        String[] key = {"bno", "depart", "applyer", "userName", "phoneNum", "uniqNum", "bsys_sort", "use_sort",
                "use_period"};

        String errorMsg = "";

        for (int i = 0; i < 8; i++) {
            String value = (String) param.get(key[i]);
            if ("".equals(value)) {
                if (errorMsg.length() > 0)
                errorMsg = errorMsg + ", ";
                errorMsg = errorMsg + key[i];
            }
        }

        // parameter를 보내면 아래의 값이 리턴, 하나라도 보내지 않으면 에러 메시지를 리턴
        Map<String, String> map = new HashMap<>();
        map.put("id", "210902");
        map.put("uno", "111122");

        if (errorMsg.length() > 0) {
            responseMap.put("result", "Fail");
            responseMap.put("errorMsg", errorMsg + " 없음");
            log.info(errorMsg + " 없음");
            return responseMap;

        } else {
            responseMap.put("result", "Success");
            responseMap.put("data", map);
            log.info(responseMap);
        }
        return responseMap;
    }
}
