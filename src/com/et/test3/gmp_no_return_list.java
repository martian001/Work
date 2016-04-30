package com.et.test3;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

//-----------按照部門篩選出“發往部門”中“是否收回”選項是“否”的項目，-------//
//-----------顯示以下的內容，“需要收回文件的部門”和“發往部門”一樣。-------
// select * from doscoditm where doscod='2251865202';/
public class gmp_no_return_list extends JFrame {
   private ResultSet resultSet;
   public JTable table;
   private TableModel model;
   private JPanel topPanel = null;
   private JPanel topResult = null;
   private JPanel panel_2_1 = null;
   private JComboBox com_gmp_dept = null;
   private JComboBox com_gmp_isback = null;
   private JComboBox com_gmp_filestatus = null;
   private JComboBox com_gmp_filestype = null;
   private JScrollPane scroller = null;

   public gmp_no_return_list() // //构造器开始
   {
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setResizable(false);
      this.setTitle("文件发放查询");
      setBounds(10, 10, 1050, 600);
      topPanel = new JPanel();
      topPanel.setLayout(new BorderLayout());
      topPanel.setBounds(5, 150, 1050, 350);
      this.setContentPane(topPanel);
      panel_2_1 = new JPanel();
      panel_2_1.setLayout(null);
      panel_2_1.setBorder(BorderFactory.createTitledBorder("查询条件"));
      panel_2_1.setBounds(0, 0, 795, 140);
      topPanel.add(panel_2_1);

      JLabel lblNewLabel = new JLabel("发往部门:");
      lblNewLabel.setBounds(50, 30, 80, 21);
      panel_2_1.add(lblNewLabel);

      com_gmp_dept = new JComboBox(this.GetGMPdept("select 'ALL-ALL' from dual union all select name||'-'||ouid s1 from doscoditm where doscod='2251865202'"));
      com_gmp_dept.setBounds(140, 30, 200, 21);
      panel_2_1.add(com_gmp_dept);

      JLabel lblNewLabel_1 = new JLabel("是否收回:");
      lblNewLabel_1.setBounds(360, 30, 84, 21);
      panel_2_1.add(lblNewLabel_1);

      // com_gmp_isback=new
      // JComboBox(this.GetGMPdept(" select 'ALL-ALL' from dual union all select name||'-'||ouid from doscoditm where doscod='2251938032'"));
      // //tmp
      com_gmp_isback = new JComboBox(this.GetGMPdept(" select 'ALL-ALL' from dual union all select name||'-'||ouid from doscoditm where doscod='2251937257'")); // prd
      com_gmp_isback.setBounds(460, 30, 200, 21);
      panel_2_1.add(com_gmp_isback);

      JLabel lblNewLabe2 = new JLabel("文件状态:");
      lblNewLabe2.setBounds(50, 70, 200, 21);
      panel_2_1.add(lblNewLabe2);
      com_gmp_filestatus = new JComboBox(this.GetGMPdept("select 'ALL-ALL' from dual union all select name||'-'||ouid from doscoditm where doscod='2251861665'"));
      com_gmp_filestatus.setBounds(140, 70, 200, 21);
      panel_2_1.add(com_gmp_filestatus);

      JLabel lblNewLabe3 = new JLabel("文当类型:");
      lblNewLabe3.setBounds(360, 70, 84, 21);
      panel_2_1.add(lblNewLabe3);

      com_gmp_filestype = new JComboBox(new String[] { "SOP", "TEC" });
      com_gmp_filestype.setBounds(460, 70, 84, 21);
      panel_2_1.add(com_gmp_filestype);
      final JButton btnNewButton_5 = new JButton("查询");
      // //
      btnNewButton_5.setBounds(280, 100, 93, 23);
      panel_2_1.add(btnNewButton_5);
      JButton export = new JButton("导入EXCEL");

      // //
      export.setBounds(380, 100, 93, 23);
      panel_2_1.add(export);
      // exprot to excel
      export.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
//            try {
//               ExcelExporter exp = new ExcelExporter();
//               exp.exportTable(table, new File("results.xls"));
//            } catch (IOException ex) {
//               System.out.println(ex.getMessage());
//               ex.printStackTrace();
//            }
         }
      });

      // export to excel
      btnNewButton_5.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent arg0) {
            topResult = null;
            topResult = new JPanel();

            topResult.setLayout(new BorderLayout());
            topResult.setBounds(5, 140, 1000, 350);
            topPanel.add(topResult);
            if (table != null) {
               ((DefaultTableModel) table.getModel()).getDataVector().clear(); // 清除表格数据
               ((DefaultTableModel) table.getModel()).fireTableDataChanged();// 通知模型更新
            }

            table = new JTable();
            table.setRowSelectionAllowed(true);

            // table.setBounds(5, 120, 780, 300);
            // topResult.add(table);
            // topResult.validate();
            String crtsql = CreateWhereString();

            String[] sql = new String[] { crtsql };

            displayResultSet(sql);

         }
      });
   } // 构造器结束

   // 显示数据

   public String CreateWhereString() {
      String sql = "select fun_get_coditem_name(s1.d102) \"需要收回文件的部門\",vs3.md$number \"文件號碼/附件號碼\",vs3.md$desc1 \"文件名\",vs3.d106 \"版本號\",fun_get_coditem_name(vs3.d124) \"狀態\" ,obsoleted_date \"作廢時間\",cancelled_date \"取消時間\"  from GMP_TO_DEPT$sf s1,GMP_TO_DEPT_REL$AS s2,vew_gmp_sop_doc vs3 where s1.sf$ouid=s2.as$end2 and s2.as$end1=vs3.vf$ouid";
      String strdept = this.com_gmp_dept.getSelectedItem().toString().split("-")[1].replace("]", "");
      String strfstatus = this.com_gmp_filestatus.getSelectedItem().toString().split("-")[1].replace("]", "");
      String strfsisback = this.com_gmp_isback.getSelectedItem().toString().split("-")[1].replace("]", "");
      String strfstype = this.com_gmp_filestype.getSelectedItem().toString();
      if (strfstype.equals("SOP")) {
         sql = "select fun_get_coditem_name(s1.d102) \"需要收回文件的部門\",vs3.md$number \"文件號碼/附件號碼\",vs3.md$desc1 \"文件名\",vs3.d106 \"版本號\",fun_get_coditem_name(vs3.d124) \"狀態\" ,obsoleted_date \"作廢時間\", to_char(cancelled_date) \"取消時間\"  from GMP_TO_DEPT$sf s1,GMP_TO_DEPT_REL$AS s2,vew_gmp_sop_doc vs3 where s1.sf$ouid=s2.as$end2 and s2.as$end1=vs3.vf$ouid and vs3.soptype=2251877342";
      } else {
         sql = "select fun_get_coditem_name(s1.d102) \"需要收回文件的部門\",vs3.md$number \"文件號碼/附件號碼\",vs3.md$desc1 \"文件名\",vs3.d106 \"版本號\",fun_get_coditem_name(vs3.d124) \"狀態\" , 'obsoleted_date' \"作廢時間\",cancelled_date \"取消時間\"  from GMP_TO_DEPT$sf s1,GMP_TO_DEPT_REL$AS s2,vew_gmp_tec_doc vs3 where s1.sf$ouid=s2.as$end2 and s2.as$end1=vs3.vf$ouid";
      }
      if (strdept != null && strdept.trim().length() > 0 && !strdept.equals("ALL")) {
         sql = sql + " and s1.d102=" + strdept;
      }
      if (strfstatus != null && strfstatus.trim().length() > 0 && !strfstatus.equals("ALL")) {
         sql = sql + " and vs3.d124=" + strfstatus;
      }

      if (strfsisback != null && strfsisback.trim().length() > 0 && !strfsisback.equals("ALL")) {
         sql = sql + " and s1.d103=" + strfsisback;
      }
      return sql;
   }

   //

   // 显示数据结束

   private void displayResultSet(String[] sql) {
      try {
         DBprocess mdb = new DBprocess();
         Vector[] vresture;
         vresture = mdb.getTableForCommon(sql);
         if (vresture == null) {
            JOptionPane.showMessageDialog(this, "结果集中无记录");
            setTitle("无记录显示");
            return;
         }
         Vector columnHeads = new Vector();
         Vector rows = new Vector();
         columnHeads = vresture[0];
         rows = vresture[1];

         // 在表格中显示查询结果

         if (topResult.getComponentCount() >= 1)
            topResult.remove(1);

         model = new DefaultTableModel(rows, columnHeads) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
         };
         table = new JTable(model);

         table.setRowSelectionAllowed(true);
         table.setRowHeight(30);
         // table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
         TableColumn col4 = table.getColumn(columnHeads.get(1));
         col4.setPreferredWidth(200);

         TableColumn col0 = table.getColumn(columnHeads.get(0));
         col0.setPreferredWidth(40);
         if (scroller != null)
            scroller.removeAll();
         scroller = new JScrollPane(table);
         table.updateUI();// 刷新表格
         // scroller.validate();
         scroller.repaint();
         scroller.revalidate();
         topResult.add(scroller, BorderLayout.CENTER);
         topResult.repaint();
         topResult.validate();

      } catch (Exception localException) {
      }
   }

   // 显示表格数据结束
   private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException

   {
      Vector currentRow = new Vector();
      for (int i = 1; i <= rsmd.getColumnCount(); ++i)
         currentRow.addElement(rs.getString(i));

      // 返回一条记录
      return currentRow;
   }

   // 显示数据结束
   // getTable

   private Vector GetGMPdept(String sql) {

      DBprocess mdb = new DBprocess();
      return mdb.getTableValue(sql);

   }

   // endGetTable
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               gmp_no_return_list frame = new gmp_no_return_list();
               frame.setVisible(true);

            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
}
