package com.example.apicalltest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
public class ApiController {
    @RequestMapping(value = "/api/post", method = RequestMethod.POST)
    @ResponseBody
    public Object apiPostCall(@RequestBody Map<String, Object> param) throws JsonProcessingException {
        Map<String, Object> responseMap = new HashMap<>();

        String[] key = {"bno", "depart", "applicant", "userName", "phoneNum", "uniqNum", "bsys_sort", "use_sort",
                "use_period"};

        String errorMsg = "";

        for (int i = 0; i < 9; i++) {
            String val = (String) param.get(key[i]);
            if ("".equals(val)) {
                if (errorMsg.length() > 0) {
                    errorMsg = errorMsg + ", ";
                }
                errorMsg = errorMsg + key[i];
            }
        }

        String uno = (String) param.get(key[5]);

        Map<String, String> map = new HashMap<>();
        map.put("id", "K1AA0001");
        map.put("uniqNum", uno);

        if (errorMsg.length() > 0) {
            responseMap.put("result", "Fail");
            responseMap.put("errorMsg", errorMsg + " 없음");
            return responseMap;
        } else {
            responseMap.put("result", "Success");
            responseMap.put("data", map);
        }

        log.info(responseMap);

        return responseMap;
    }

    @RequestMapping(value = "/api/get", method = RequestMethod.GET)
    @ResponseBody
    public Object apiGetCall(@RequestParam Map<String, Object> params) throws JsonProcessingException {
        Map<String, Object> responseMap = new HashMap<>();

        String[] key = {"bno", "depart", "applicant", "userName", "phoneNum", "uniqNum", "bsys_sort", "use_sort",
                "use_period"};

        String errorMsg = "";

        for (int i = 0; i < 9; i++) {
            String val = (String) params.get(key[i]);
            if ("".equals(val)) {
                if (errorMsg.length() > 0) {
                    errorMsg = errorMsg + ", ";
                }
                errorMsg = errorMsg + key[i];
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("id", "210901");
        map.put("uno", "122111");

        if (errorMsg.length() > 0) {
            responseMap.put("result", "Fail!!");
            responseMap.put("errorMsg", errorMsg + " 없음");
            return responseMap;
        } else {
            responseMap.put("result", "Success!!");
            responseMap.put("data", map);
        }


        log.info(responseMap);

        return responseMap;
    }

}
