package com.example.mapper;

import com.example.model.entity.Wxuser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

/**
 * @author L
 * &#064;description  针对表【wxuser】的数据库操作Mapper
 * &#064;createDate  2023-07-25 14:59:40
 * &#064;Entity  com.example.model.entity.Wxuser
 */
public interface WxuserMapper extends BaseMapper<Wxuser> {


    @Select("select * from wxuser where openid=#{openid}")
    Wxuser getOneByOpenidWxuser(@Param("openid") String openid);
}




