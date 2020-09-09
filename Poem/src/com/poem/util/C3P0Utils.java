package com.poem.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * c3p0工具类（数据库连接池工具），用于管理多个数据库连接对象。
 */
public class C3P0Utils {

    private static ComboPooledDataSource ds = null;

    static {
        // 新建数据库连接池，注意这里的参数必须同c3p0-config.xml中的named-config标签中的name的值相同
        // 如果不写，则默认调用default-config
        ds = new ComboPooledDataSource();
    }

    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 得到数据库连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection conn;
        try {
            conn = ds.getConnection();
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("服务器忙");
        }
    }

    /**
     * 关闭所有资源连接
     * @param conn
     * @param ps
     * @param rs
     */
    public static void releaseAll(Connection conn, Statement ps, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ps = null;
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
    }

}
