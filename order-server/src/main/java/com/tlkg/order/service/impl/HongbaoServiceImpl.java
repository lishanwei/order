/**
 * Project Name:tian File Name:HongbaoServiceImpl.java Package Name:cn.com.audiocn.service.impl
 * Date:2016年11月25日上午11:31:31
 */
package com.tlkg.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tlkg.order.commons.RedisCacheManager;
import com.tlkg.order.constants.Constants;
import com.tlkg.order.service.HongbaoService;

/**
 * ClassName:HongbaoServiceImpl <br/>
 * Date: 2016年11月25日 上午11:31:31 <br/>
 * @author lishanwei
 */
@Component("hongbaoService")
public class HongbaoServiceImpl implements HongbaoService
{
    
    /** 返回红包日志. */
    private static Logger hbLog = LoggerFactory.getLogger("hongbaoService");
    
    @Autowired
    private RedisCacheManager redisCacheManager;
    
    @Override
    public String sethb(String flag)
    {
        redisCacheManager.set(Constants.DISHB_FLAG, flag);
        return String.valueOf(redisCacheManager.getObject(Constants.DISHB_FLAG));
    }
}
