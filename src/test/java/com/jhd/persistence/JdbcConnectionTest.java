package com.jhd.persistence;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnectionTest {
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void testConn(){
        try(Connection conn = DriverManager.getConnection(
                "jdbc:mysql://ec2-13-124-238-68.ap-northeast-2.compute.amazonaws.com:3306/dotime?useUniCode=yes&characterEncoding=UTF-8&serverTimezone=Asia/Seoul",
                "root",
                "1234")) {
            System.out.println("Connection -> " + conn);
        }catch(Exception e){

            System.out.println("Error Message :" + e.getMessage());
        }
    }
}
