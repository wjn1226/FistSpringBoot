package com.examplemybatis.dao;

import com.examplemybatis.sqlENtiyt.Film;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDAO {
    List<Film> findAll();
    List<Film> getName(int a);
    List<Film> getsql(String name);
    List<Film> getYYnoName(String yy);
//    parametertype 多个参数时，用@Param("xxx")来处理多个参数
    List<Film> insertSql(@Param("intID") int intID, @Param("num") String num);
}
