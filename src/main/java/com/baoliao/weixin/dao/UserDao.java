package com.baoliao.weixin.dao;
import com.baoliao.weixin.bean.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 获取所有用户
     * @return
     */
    //TODO
    //暂时获取所有用户 后续换成特定用户，例如：绑定的用户
    List<User> queryUserList();

    /**
     * 绑定微信
     *
     * @param user
     * @return
     */
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