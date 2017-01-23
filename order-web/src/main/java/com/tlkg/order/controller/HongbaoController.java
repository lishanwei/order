package com.tlkg.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tlkg.order.service.HongbaoService;

@RestController
@RequestMapping("/hb")
public class HongbaoController {

    private static Logger logger = LoggerFactory.getLogger(HongbaoController.class);
    
    @Autowired
    private HongbaoService hongbaoService;
    
    /** 接口区 **/
    /**
     * 获取红包状态
     * @param request
     * @return JSONObject
     */
    @RequestMapping(value = "/setHbStatus")
    public ResponseEntity<JSONObject> setHbStatus(HttpServletRequest request)
    {
        String flag = request.getParameter("flag");
        JSONObject result = new JSONObject();
        result.put("hbflag", hongbaoService.sethb(flag));
        return new ResponseEntity<JSONObject>(result, HttpStatus.OK);
    }
}
