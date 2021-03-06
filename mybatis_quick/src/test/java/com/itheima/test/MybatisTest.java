package com.itheima.test;

import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void test1() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获取session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数:namespace+id
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        //打印数据
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }


    @Test
    public void test2() throws IOException {
        //模拟user对象
        User user = new User();
        user.setUsername("Tom");
        user.setPassword("abc");
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获取session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数:namespace+id
        sqlSession.insert("userMapper.save",user);

        //mybatis执行更新操作 最终一定要提交事务
        sqlSession.commit();;

        //释放资源
        sqlSession.close();
    }
}
