package top.chao.funding.manager.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TUser;
import top.chao.funding.bean.TUserExample;

public interface TUserMapper {
    long countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);
    
    TUser queryUserLogin(HashMap<String, Object> userMap);
}