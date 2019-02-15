package com.examplemybatis.util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

    public class MyBatisUtil {
        private static InputStream is;
        private static SqlSessionFactory sqlSessionFactory;
        static {
            try {
                is=Resources.getResourceAsStream("mybatis-config.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlSessionFactory= new SqlSessionFactoryBuilder().build(is);
        }
        private MyBatisUtil(){}
        public static SqlSession getSession(){
            return sqlSessionFactory.openSession();
        }
    }
