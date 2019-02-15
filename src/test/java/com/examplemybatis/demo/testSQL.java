package com.examplemybatis.demo;

import com.examplemybatis.dao.IUserDAO;
import com.examplemybatis.sqlENtiyt.Film;
import com.examplemybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
        public class testSQL {
        @Test
        public void findAll(){
            //创建能执行映射文件中sql的sqlSession
            SqlSession session = MyBatisUtil.getSession();
            // 用getMapper方法HIbernate帮我们在内存中代理出一个接口的实现类======相当于强类型
            //mapper1是一个实现类对象
            IUserDAO mapper1 = session.getMapper(IUserDAO.class);
//            List<Film> users = mapper1.findAll();
            List<Film> users=mapper1.getName(22);
            System.out.println(users);
//            System.out.println(users);
            for (Film user:users
                    ) {
                System.out.println(user.getId()+"\t"+user.getName()+"\t"+user.getPrice()+"\t"+user.getSex()+user.getPrice()+"\t"+user.getYy());
            }
        }
    }
