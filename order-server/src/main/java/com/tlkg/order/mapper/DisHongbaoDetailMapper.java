package com.tlkg.order.mapper;

import com.tlkg.order.model.DisHongbaoDetail;

public interface DisHongbaoDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DisHongbaoDetail record);

    int insertSelective(DisHongbaoDetail record);

    DisHongbaoDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DisHongbaoDetail record);

    int updateByPrimaryKey(DisHongbaoDetail record);
}