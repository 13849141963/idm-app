package com.fescotech.apps.idm.test.dialect;
import com.fescotech.common.ibatis.Dialect;
import com.fescotech.common.ibatis.dialect.MySQLDialect;
import com.fescotech.common.ibatis.dialect.OracleDialect;

public class TestDialect {

	public static void main(String[] args) {
		Dialect dialect=new MySQLDialect();
		String sql = dialect.getLimitString("select * from sys_user where user_id >10 order by user_name", 10, 20);
		System.out.println(sql);
		
		
		  dialect=new OracleDialect();
		  sql = dialect.getLimitString("select * from sys_menu t where URL is not null order by MENU_ID", 2, 3);
		System.out.println(sql);
	}

}
