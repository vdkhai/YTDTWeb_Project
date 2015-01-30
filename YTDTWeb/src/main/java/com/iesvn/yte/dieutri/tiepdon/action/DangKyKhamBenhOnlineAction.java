 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.SESSION)
 @Name("B111_Dangkykhambenh")
 @Synchronized(timeout=6000000L)
 public class DangKyKhamBenhOnlineAction
   implements Serializable
 {
   private static final long serialVersionUID = 4201303779918089532L;
   private static Logger log = Logger.getLogger(DangKyKhamBenhOnlineAction.class);
   private static final String urlMySQL = IConstantsRes.DK_ONLINE_DB_URL;
   private static final String userName = IConstantsRes.DATABASE_USER;
   private static final String password = IConstantsRes.DATABASE_PASS;
   Connection conn;
   private String maTD_online = "";
   private String tenTD_online = "";
   private String gioi = "";
   private String tuoi_online = "";
   private String donvituoi = "";
   private String diachi_online = "";
   private String maBH_online = "";
   private String ngayTDDK = "";
   private String gioTDDK = "";
   private String ngaygioDK = "";
   private String doituong = "";

   @Create
   public void init()
   {
     log.info("### init() B111_Dangkykhambenh ###");
   }

   public void search_maTDonline()
   {
     if (!urlMySQL.startsWith("jdbc"))
     {
       log.info("### Khong co server Dang ky online");
       return;
     }
     if (this.maTD_online.length() < 5)
     {
       int add_zero = 5 - this.maTD_online.length();
       for (int i = 0; i < add_zero; i++) {
         this.maTD_online = ("0" + this.maTD_online);
       }
     }
     String query = "SELECT * FROM healthonline_db.regonline r WHERE r.regno LIKE '%" + this.maTD_online + "'" + " order by r.regid desc";
     try
     {
       try
       {
         Class.forName("com.mysql.jdbc.Driver");
       }
       catch (ClassNotFoundException e)
       {
         e.printStackTrace();
       }
       this.conn = DriverManager.getConnection(urlMySQL, userName, password);
       try
       {
         PreparedStatement stmt = this.conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery();
         if (rs.first())
         {
           this.tenTD_online = rs.getString("pname");
           this.maTD_online = rs.getString("regno");
           this.tuoi_online = rs.getString("page");
           this.donvituoi = rs.getString("agetype");
           this.diachi_online = rs.getString("paddress");
           this.maBH_online = rs.getString("pcardno");
           this.gioi = rs.getString("psex");
           this.ngayTDDK = rs.getString("regdate");
           this.gioTDDK = rs.getString("regtime");

           log.info("### SQL Query OK !! ### Ma: " + this.maTD_online + " <> Tuoi: " + this.tuoi_online + " <> DV Tuoi: " + this.donvituoi + " <> Dia chi: " + this.diachi_online + " <> Ma BH: " + this.maBH_online + " <> Ngay DK: " + this.ngayTDDK + " <> Gio DK: " + this.gioTDDK + " <> Gioi tinh: " + this.gioi);
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND, new Object[0]);
           log.info("### SQL Query OK !! ### No result !! ");
         }
       }
       catch (SQLException er)
       {
         log.info("### SQL Querry Error !! ### " + er.getMessage());
       }
     }
     catch (SQLException er)
     {
       log.info("### SQL Connection Error !! ### " + er.getMessage());
     }
     if (this.conn != null) {
       try
       {
         this.conn.close();
       }
       catch (SQLException e)
       {
         log.info("### SQL Connection Close Error !! ### " + e.getMessage());
       }
     }
     if (this.donvituoi.equals("DAY")) {
       this.donvituoi = "3";
     } else if (this.donvituoi.equals("MONTH")) {
       this.donvituoi = "2";
     } else {
       this.donvituoi = "1";
     }
     if ((this.gioi == null) || (this.gioi.equals(""))) {
       this.gioi = "1";
     }
     if ((this.maBH_online != null) && (!"".equals(this.maBH_online))) {
       this.doituong = "BH";
     } else {
       this.doituong = "TP";
     }
     if ((this.ngayTDDK != null) && (!"".equals(this.ngayTDDK)))
     {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       SimpleDateFormat df_view = new SimpleDateFormat("dd/MM/yyyy");
       String today = df.format(new Date());
       try
       {
         Date ngayDK = df.parse(this.ngayTDDK);
         Date ngayHT = df.parse(today);
         this.ngaygioDK = df_view.format(ngayDK);
         if (ngayDK.before(ngayHT)) {
           FacesMessages.instance().add(IConstantsRes.DANGKY_ONLINE_DENTRE, new Object[] { this.ngaygioDK });
         }
         if (ngayDK.after(ngayHT)) {
           FacesMessages.instance().add(IConstantsRes.DANGKY_ONLINE_DENSOM, new Object[] { this.ngaygioDK });
         }
         if ((this.gioTDDK != null) && (!"".equals(this.gioTDDK))) {
           this.ngaygioDK = (this.gioTDDK + ":00 " + IConstantsRes.NGAY + " " + this.ngaygioDK);
         }
       }
       catch (ParseException e)
       {
         e.printStackTrace();
       }
     }
   }

   public void resetValue()
   {
     log.info("### resetValue() ###");
     FacesMessages.instance().clear();
     this.tenTD_online = "";
     this.maTD_online = "";
     this.tuoi_online = "";
     this.donvituoi = "1";
     this.diachi_online = "";
     this.maBH_online = "";
     this.gioi = "1";
     this.ngayTDDK = "";
     this.gioTDDK = "";
     this.ngaygioDK = "";
     this.doituong = "";
   }

   public String getDonvituoi()
   {
     return this.donvituoi;
   }

   public void setDonvituoi(String donvituoi)
   {
     this.donvituoi = donvituoi;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getMaTD_online()
   {
     return this.maTD_online;
   }

   public void setMaTD_online(String maTDOnline)
   {
     this.maTD_online = maTDOnline;
   }

   public String getTenTD_online()
   {
     return this.tenTD_online;
   }

   public void setTenTD_online(String tenTDOnline)
   {
     this.tenTD_online = tenTDOnline;
   }

   public String getTuoi_online()
   {
     return this.tuoi_online;
   }

   public void setTuoi_online(String tuoiOnline)
   {
     this.tuoi_online = tuoiOnline;
   }

   public String getDiachi_online()
   {
     return this.diachi_online;
   }

   public void setDiachi_online(String diachiOnline)
   {
     this.diachi_online = diachiOnline;
   }

   public String getMaBH_online()
   {
     return this.maBH_online;
   }

   public void setMaBH_online(String maBHOnline)
   {
     this.maBH_online = maBHOnline;
   }

   public String getNgayTDDK()
   {
     return this.ngayTDDK;
   }

   public void setNgayTDDK(String ngayTDDK)
   {
     this.ngayTDDK = ngayTDDK;
   }

   public String getGioTDDK()
   {
     return this.gioTDDK;
   }

   public void setGioTDDK(String gioTDDK)
   {
     this.gioTDDK = gioTDDK;
   }

   public String getNgaygioDK()
   {
     return this.ngaygioDK;
   }

   public void setNgaygioDK(String ngaygioDK)
   {
     this.ngaygioDK = ngaygioDK;
   }

   public String getDoituong()
   {
     return this.doituong;
   }

   public void setDoituong(String doituong)
   {
     this.doituong = doituong;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.DangKyKhamBenhOnlineAction

 * JD-Core Version:    0.7.0.1

 */