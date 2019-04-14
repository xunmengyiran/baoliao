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

    int updateUserInfo(User user) throws Exception;
    /**
     * 绑定微信
     *
     * @param user
     * @return
     */
    int bindWeChatUser(User user) throws Exception;

    /**
     * 关注
     *
     * @param openId
     * @return
     */
    int insertFollowInfo(String openId) throws Exception;

    /**
     * 取消关注，将关注字段从1->0
     *
     * @param fromUserName
     * @return
     * @throws Exception
     */
    int updateFollowInfo(String fromUserName) throws Exception;

    User getUserInfoByOpenId(String openId) throws Exception;

    int getSubscribeUserByOpenId(String openId) throws Exception;

    int deleteBuyRecordById(String id) throws Exception;
}