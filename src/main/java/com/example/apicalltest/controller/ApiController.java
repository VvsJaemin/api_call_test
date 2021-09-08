package com.example.apicalltest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

@Controller
@Log4j2
public class ApiController {

    ResourceBundle resource = ResourceBundle.getBundle("config");
    String db_url = resource.getString("db_url");
    String db_id = resource.getString("db_id");
    String db_pwd = resource.getString("db_pwd");

    public static String numberGen(int len, int dupCd) {
        Random rand = new Random();
        String num = "";
        for (int i = 0; i < len; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            if (dupCd == 1) {
                if (!num.contains(ran)) {
                    num += ran;
                } else {
                    i -= 1;
                }
            }
        }
        return num;
    }

    @RequestMapping(value = "/api/getCoopEmpNo", method = RequestMethod.POST)
    @ResponseBody
    public Object apiPostCall(@RequestBody Map<String, Object> param) throws JsonProcessingException {
        Map<String, Object> responseMap = new HashMap<>();

        String[] key = { "bno", "depart", "applyer", "userName", "phoneNum", "uniqNum", "bsys_sort", "use_sort",
                "use_period" };

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String sql = null;

        String paramCode = (String) param.get(key[0]);
        paramCode = paramCode.replaceAll("\\D", "");

        String code = "";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_id, db_pwd);

            sql = "SELECT COMPANY_CODE FROM COOP_COMPANY_CODE WHERE BIZ_NO =? ";
            stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            stmt.setString(1, paramCode);

            rs = stmt.executeQuery();

            rs.last();

            int rowCount = rs.getRow();

            rs.beforeFirst();

            while (rs.next()) {
                code = rs.getString(1);
            }

            if (rowCount < 1) {
                String errorMsg = "";
                errorMsg = "No Search COMPANY_CODE";
                if (errorMsg.length() > 0) {
                    responseMap.put("result", "Fail");
                    responseMap.put("errorMsg", "Fault Data : " + errorMsg);
                }
                return responseMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, String> returnMap = new HashMap<>();

        String serialNum = numberGen(4, 1);

        returnMap.put("id", "K1" + code + serialNum);

        String uNo = (String) param.get(key[5]);
        returnMap.put("uniqNum", uNo);

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

        if (errorMsg.length() > 0) {
            responseMap.put("result", "Fail");
            responseMap.put("errorMsg", "No Data : " + errorMsg);
            return responseMap;
        } else {
            responseMap.put("result", "Success");
            responseMap.put("data", returnMap);
        }

        return responseMap;
    }

    @RequestMapping(value = "/api_get", method = RequestMethod.GET)
    @ResponseBody
    public Object apiGetCall(@RequestParam Map<String, Object> params) throws JsonProcessingException {
        Map<String, Object> responseMap = new HashMap<>();

        String[] key = { "bno", "depart", "applyer", "userName", "phoneNum", "uniqNum", "bsys_sort", "use_sort",
                "use_period" };

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
