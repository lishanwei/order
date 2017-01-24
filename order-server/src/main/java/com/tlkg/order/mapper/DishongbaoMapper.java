package com.tlkg.order.mapper;

import com.tlkg.order.model.Dishongbao;

public interface DishongbaoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Dishongbao record);

    int insertSelective(Dishongbao record);

    Dishongbao selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dishongbao record);

    int updateByPrimaryKey(Dishongbao record);
}