package com.boar.mapper;

import com.boar.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;


public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    @Override
    public List<User> selectUser() {
         User user = new User(4, "麻子", "45632211");
         UserMapper mapper=getSqlSession().getMapper(UserMapper.class);
         mapper.addUser(user);
         mapper.deleteUser(1);
        return mapper.selectUser();
    }

    @Override
    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
