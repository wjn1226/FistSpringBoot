package com.examplemybatis.demo;

import com.examplemybatis.dao.IUserDAO;
import com.examplemybatis.sqlENtiyt.Film;
import com.examplemybatis.util.MyBatisUtil;
import com.sun.tools.javac.util.Convert;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@Slf4j
//声明这是一个总路由
@RequestMapping(value = "/hi")
public class helloController{
    //    打印日志-指定输出的类
    private static final Logger logger = LoggerFactory.getLogger(helloController.class);

    //http中get接口
    @RequestMapping(value = "/hell0")
    public String hell0(){
        return "hello World!";
    }
    //http中get接口url有参数
    @RequestMapping(value="/test/{name}/{age}")
    public String test(@PathVariable String name, @PathVariable Integer age){
        return name+age;
    }

    //这是一个post且url有参数【不要忘记/{name}】，接口中也带有参数的接口?cc=
    @PostMapping(value = "/save/{name}")
    public String save1(@PathVariable String name,@RequestParam(value = "cc") String ccc1){
        return name+ccc1;
    }

    //    接口中带有参数
    @PostMapping(value = "/happy")
    public String test1(@RequestParam(value = "one") String one1){
        return one1;
    }

    //    springboot中properties的全局参数、接口返回json数据
    @Value(value="${number}")
    public String number0;

    @Value(value = "${name}")
    public String name0;

    @RequestMapping(value = "/param")
    public Map<String,Object> param(){
        Map<String,Object> aa=new HashMap<>();
        aa.put("name",name0);
        aa.put("age",number0);

        //    打印日志
        logger.info("+++++++++++++++++++++++++P=+++++++++++++++++++++");
        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
        log.info("CliCLi");
        return aa;
    }

//    接口中调用数据库数据方法
    @RequestMapping(value = "/sql")
    public List<Map<String,Object>> sql(){
        List<Map<String,Object>> cc=new ArrayList<Map<String,Object>>();
        SqlSession session = MyBatisUtil.getSession();
        IUserDAO mapper1 = session.getMapper(IUserDAO.class);
//            List<Film> users = mapper1.findAll();
        List<Film> users=mapper1.getsql("wjn4");
        for (Film user:users
                ) {
            Map<String,Object> bb=new HashMap<>();
            System.out.println(user.getId() + "\t" + user.getName() + "\t" + user.getPrice() + "\t" + user.getSex() + user.getPrice() + "\t" + user.getYy());
            bb.put("sex", user.getSex());
            bb.put("price",user.getPrice());
            bb.put("yy",user.getYy());
            cc.add(bb);
        }

        return cc;
    }

    //接口中查询and插入数据库数据
@RequestMapping(value = "/getYYnoName")
    public List<Map<String,Object>> getYYnoName(){
        List<Map<String,Object>> rr=new ArrayList<>();
        SqlSession session = MyBatisUtil.getSession();
        IUserDAO mapper2=session.getMapper(IUserDAO.class);
        List<Film> users=mapper2.getYYnoName("no");
        for (Film user:users){
            Map<String,Object> vv=new HashMap<>();
            vv.put("name",user.getName());
            rr.add(vv);
        }
        return rr;
}
@Value(value = "${noid}")
public Integer nomid;
    @RequestMapping(value = "/insertsql0")
    public List<Map<String,Object>> insertsql0(){
        List<Map<String,Object>> rr=new ArrayList<>();
        SqlSession session = MyBatisUtil.getSession();
        IUserDAO mapper2=session.getMapper(IUserDAO.class);
        List<Film> users=mapper2.insertSql(nomid,number0);

        List<Film>  all=mapper2.getYYnoName("no");
        for (Film user:all){
            Map<String,Object> vv=new HashMap<>();
            vv.put("name",user.getName());
            rr.add(vv);
        }
//        session.commit();
        session.close();//插入数据必须加入否则无法生效并耗费资源。
        return rr;
    }




}

