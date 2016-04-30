package com.et.test3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBprocess {
   public Connection con;
   public Statement st;
   public PreparedStatement pst;
   public CallableStatement cst;
   public ResultSet rs;

   public DBprocess() {
      try {
         // String str1 = "jdbc:oracle:thin:@192.168.233.128:1521:orcl";
         String str1 = "jdbc:mysql://localhost:3306/user";
         String str2 = "root";
         String str3 = "root";
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         this.con = DriverManager.getConnection(str1, str2, str3);
         System.out.println("open db sucess");
      } catch (Exception localException) {
         System.out.println("err for open db11222333");
         localException.printStackTrace();
      }
   }

   public ResultSet query(String paramString) {
      try {
         this.st = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         this.st.setFetchSize(2);

         this.rs = this.st.executeQuery(paramString);
      } catch (Exception localException) {
         System.out.println(localException.toString());
      }
      return this.rs;
   }

   public int update(String paramString) {
      int i = -1;
      try {
         this.st = this.con.createStatement();
         i = this.st.executeUpdate(paramString);
      } catch (Exception localException) {
      }
      return i;
   }

   public boolean executeB(String[] paramArrayOfString) {

      try {
         this.con.setAutoCommit(false);
         this.st = this.con.createStatement();
         for (int i = 0; i < paramArrayOfString.length; i++) {
            this.st.addBatch(paramArrayOfString[i]);
         }
         this.st.executeBatch();
         this.con.commit();
         return true;
      } catch (Exception localException) {
         return false;
      }
   }

   public void prepareQ(String paramString1, String paramString2) {
      try {
         String str = "insert into test(id,name,address) values(seql.nextval,?,?)";
         this.pst = this.con.prepareStatement(str);
         this.pst.setString(1, paramString1);
         this.pst.setString(2, paramString2);
         this.pst.execute();
      } catch (Exception localException) {
      }
   }

   // 根据一组 SQL,得到一个 表 专用于奖金计算
   public Vector[] getTable(String[] sql) {
      ResultSet resultSet;
      Vector columnHeads = new Vector();
      Vector rows = new Vector();
      Vector[] vresture = new Vector[2];
      boolean isFirst = true;
      boolean moreRecords = false;
      try {

         // 执行SQL语句

         for (int icount = 0; icount < sql.length; icount++) {
            if (sql[icount] == null || sql[icount].length() < 10)
               continue;
            resultSet = this.query(sql[icount]);
            moreRecords = rs.next();
            if (!moreRecords)
               continue;
            float a = 0;
            do {
               a = a + (float) rs.getFloat(6);

            } while (rs.next());
            rs.last();
            int b = resultSet.getRow();
            a = a / (float) b;
            rs.first();
            ResultSetMetaData rsmd = rs.getMetaData();
            if (isFirst) {
               for (int i = 1; i <= rsmd.getColumnCount(); ++i)
                  columnHeads.addElement(rsmd.getColumnName(i));
               isFirst = false;
            }
            do {
               rows.addElement(getNextRow(rs, rsmd, " " + a, 7));
               // rows.addElement( getNextRow( rs, rsmd) );
            } while (rs.next());
         }
         vresture[0] = columnHeads;
         vresture[1] = rows;
         return vresture;
      } catch (SQLException sqlex) {
         sqlex.printStackTrace();
         return null;
      }
   }

   // 得到一个表结束
   // ------xusl-------得到一个表通用，

   public Vector[] getTableForCommon(String[] sql) {
      ResultSet resultSet;
      Vector columnHeads = new Vector();
      Vector rows = new Vector();
      Vector[] vresture = new Vector[2];
      boolean isFirst = true;
      boolean moreRecords = false;
      try {

         // 执行SQL语句

         for (int icount = 0; icount < sql.length; icount++) {
            if (sql[icount] == null || sql[icount].length() < 10)
               continue;
            resultSet = this.query(sql[icount]);
            moreRecords = rs.next();
            if (!moreRecords)
               continue;
            rs.last();
            int b = resultSet.getRow();
            rs.first();
            ResultSetMetaData rsmd = rs.getMetaData();
            if (isFirst) {
               for (int i = 1; i <= rsmd.getColumnCount(); ++i)
                  columnHeads.addElement(rsmd.getColumnName(i));
               isFirst = false;
            }
            do {
               // rows.addElement( getNextRow( rs, rsmd," "+a,7 ) );
               rows.addElement(getNextRow(rs, rsmd));
            } while (rs.next());
         }
         vresture[0] = columnHeads;
         vresture[1] = rows;
         return vresture;
      } catch (SQLException sqlex) {
         sqlex.printStackTrace();
         return null;
      }
   }

   // ------xusl-------得到一个表 通用

   // ----根据一个 sql语句得到得到语句的值，拥有绑定jcombobox
   public Vector getTableValue(String sql) {
      ResultSet resultSet;
      Vector rows = new Vector();
      boolean isFirst = true;
      boolean moreRecords = false;
      try {

         // 执行SQL语句
         resultSet = this.query(sql);
         moreRecords = rs.next();
         rs.first();
         ResultSetMetaData rsmd = rs.getMetaData();
         do {
            rows.addElement(getNextRow(rs, rsmd));
         } while (rs.next());

         return rows;
      } catch (SQLException sqlex) {
         sqlex.printStackTrace();
         return null;
      }
   }

   // 得到一个表结束
   // 表记录下一行

   private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException

   {
      Vector currentRow = new Vector();
      for (int i = 1; i <= rsmd.getColumnCount(); ++i)
         currentRow.addElement(rs.getString(i));

      // 返回一条记录
      return currentRow;
   }

   private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd, String a, int icol) throws SQLException

   {
      Vector currentRow = new Vector();
      for (int i = 1; i <= rsmd.getColumnCount(); ++i)
         if (i == icol)
            currentRow.addElement(a);
         else
            currentRow.addElement(rs.getString(i));

      // 返回一条记录
      return currentRow;
   }

   // 表记录下一行结束
   public boolean callQ(String paramString) {
      boolean bool = false;
      try {
         this.cst = this.con.prepareCall(paramString);
         bool = this.cst.execute();
      } catch (Exception localException) {
      }
      return bool;
   }
}