package com.baoliao.weixin.dao;

import com.baoliao.weixin.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserDao {
    List<User> queryUserList();

    int bindWeChatUser(User user);

    /**
     * 关注
     *
     * @param openId
     * @return
     */
    int insertFollowInfo(String openId);

    /**
     * 取消关注，将关注字段从1->0
     *
     * @param fromUserName
     * @return
     * @throws Exception
     */
    int updateFollowInfo(String fromUserName);
}
