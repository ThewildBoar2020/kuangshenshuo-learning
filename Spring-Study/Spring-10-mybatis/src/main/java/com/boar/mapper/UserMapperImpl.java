package com.boar.mapper;

import com.boar.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper{
    //在原来我们所有的操作,都是用sqlSession来执行，现在使用SqlSessionTemplate;
    private SqlSessionTemplate sqlSession;
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> selectUser() {
         UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }



}
