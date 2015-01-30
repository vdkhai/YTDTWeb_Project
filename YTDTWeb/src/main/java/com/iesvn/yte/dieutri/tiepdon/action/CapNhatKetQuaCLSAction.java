 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKetQua;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.ResultSetMetaData;
 import java.sql.SQLException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;
 import org.richfaces.event.UploadEvent;
 import org.richfaces.model.UploadItem;

 @Scope(ScopeType.SESSION)
 @Name("B121_2_1_CapNhatKetQuaCLS")
 @Synchronized(timeout=6000000L)
 public class CapNhatKetQuaCLSAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String ngaySinh;
   @Logger
   private Log log;
   private String maBanKham;
   private String maTiepDon;
   private String tenBenhNhan;
   private Short donviTuoi;
   private String maBenhNhan;
   private Integer tuoi;
   private Integer clsmaOut;
   private String maCLS;
   private String tenCLS;
   private Boolean daThucHien;
   private String kqCLS;
   private String dulieuhinhanh;
   private String maKhoa;
   private String ghichu;
   private Integer loaiCLS = Integer.valueOf(0);
   private Boolean thatSuThucHien;
   public Boolean hasImage;
   private String fileanh;
   private byte[] byteImagSource;
   private byte[] byteImag;

   public String getDulieuhinhanh()
   {
     return this.dulieuhinhanh;
   }

   public void setDulieuhinhanh(String dulieuhinhanh)
   {
     this.dulieuhinhanh = dulieuhinhanh;
   }

   public String getKqCLS()
   {
     return this.kqCLS;
   }

   public void setKqCLS(String kqCLS)
   {
     this.kqCLS = kqCLS;
   }

   public void init()
   {
     this.log.info("begin comeFromclsphauuthuat", new Object[0]);

     this.ngaySinh = "";

     setDefaultValue();

     this.log.info("ma khoa " + this.maKhoa, new Object[0]);
     this.log.info("file anh " + this.fileanh, new Object[0]);
     this.log.info("end comeFromclsphauuthuat", new Object[0]);
   }

   public void connectLabconn()
   {
     try
     {
       if (IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES"))
       {
         this.log.info("ConnectLabconn", new Object[0]);
         String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";



         ClsKhamDelegate clskhamDel = ClsKhamDelegate.getInstance();
         ClsKham clsKham = clskhamDel.find(this.clsmaOut);
         ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
         ThamKham thamkham_temp = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKham, this.maTiepDon);
         String temp = "";
         String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + "\\" + IConstantsRes.LABCONN_DATABASE + ":" + IConstantsRes.LABCONN_PORT + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
         String username = IConstantsRes.LABCONN_USERNAME;
         String password = IConstantsRes.LABCONN_PASSWORD;
         try
         {
           Class.forName(driver);
           Connection conn = DriverManager.getConnection(url, username, password);
           List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen", clsKham.getClskhamMa() + "", "");
           for (int j = 0; j < listClsKetQua.size(); j++)
           {
             String query = " Select * from [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Result_Upload]";
             query = query + " where 1 = 1 ";
             query = query + " and OrderID = '" + thamkham_temp.getTiepdonMa().getTiepdonMa() + "' ";
             query = query + " and TestCodeHIS = '" + ((ClsKetQua)listClsKetQua.get(j)).getClskqMa() + "' ";
             PreparedStatement stmt = conn.prepareStatement(query, 1);
             ResultSet rs = stmt.executeQuery();
             if (rs != null) {
               while (rs.next())
               {
                 this.daThucHien = Boolean.valueOf(true);
                 ResultSetMetaData rsMetaData = rs.getMetaData();
                 temp = temp + ((ClsKetQua)listClsKetQua.get(j)).getClskqTen() + ": " + rs.getString("Result") + "\n";
                 ((ClsKetQua)listClsKetQua.get(j)).setResult(rs.getString("Result"));
                 DieuTriUtilDelegate.getInstance().edit(listClsKetQua.get(j));
               }
             }
           }
           this.ghichu = temp;
         }
         catch (ClassNotFoundException e)
         {
           e.printStackTrace();
         }
         catch (SQLException e)
         {
           e.printStackTrace();
         }
       }
     }
     catch (Exception e) {}
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public void setDefaultValue()
   {
     this.loaiCLS = Integer.valueOf(0);
     this.log.info("maBanKham:" + this.maBanKham, new Object[0]);
     this.log.info("maTiepDon:" + this.maTiepDon, new Object[0]);
     this.log.info("clsmaOut:" + this.clsmaOut, new Object[0]);
     if ((this.maBanKham == null) || (this.maBanKham.equals("")) || (this.maTiepDon == null) || (this.maTiepDon.equals("")))
     {
       resetValue();
       return;
     }
     if (this.clsmaOut == null)
     {
       resetValue();
       return;
     }
     ClsKhamDelegate clskhamDel = ClsKhamDelegate.getInstance();
     ClsKham clsKham = clskhamDel.find(this.clsmaOut);
     if (clsKham == null)
     {
       this.log.info("ko tim thay cls:" + this.clsmaOut, new Object[0]);
       resetValue();
       return;
     }
     this.maCLS = clsKham.getClskhamMahang(true).getDtdmclsbgMa();
     this.tenCLS = clsKham.getClskhamMahang(true).getDtdmclsbgDiengiai();
     this.daThucHien = clsKham.getClskhamTh();
     this.kqCLS = clsKham.getClskhamKetqua();
     this.dulieuhinhanh = clsKham.getClskhamDulieuhinhanh();

     this.thatSuThucHien = this.daThucHien;


     this.maKhoa = clsKham.getClskhamKhoa(true).getDmkhoaMa();
     this.ghichu = clsKham.getClskhamGhichu();
     if ((clsKham.getClskhamMahang(true).getDtdmclsbgXn() != null) && (clsKham.getClskhamMahang(true).getDtdmclsbgXn().booleanValue())) {
       this.loaiCLS = Integer.valueOf(1);
     } else if ((clsKham.getClskhamMahang(true).getDtdmclsbgCdha() != null) && (!clsKham.getClskhamMahang(true).getDtdmclsbgCdha().equals(""))) {
       this.loaiCLS = Integer.valueOf(2);
     }
     ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
     ThamKham thamkham_temp = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKham, this.maTiepDon);
     if (thamkham_temp == null)
     {
       resetValue();
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);
       return;
     }
     try
     {
       if ((this.daThucHien != null) && (this.daThucHien.booleanValue() == true))
       {
         this.byteImagSource = clsKham.getClskhamImg();
         viewImg();
         this.hasImage = Boolean.valueOf(true);
       }
       else
       {
         List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen", clsKham.getClskhamMa() + "", "");
         if (listClsKetQua.size() > 0) {
           connectLabconn();
         }
         this.hasImage = Boolean.valueOf(false);
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     setOtherValue(thamkham_temp);
   }

   public void resetValue() {}

   public String ghinhan()
     throws Exception
   {
     if ((this.clsmaOut == null) || (this.clsmaOut.equals("")))
     {
       this.log.info("clsKham" + this.clsmaOut, new Object[0]);
       return "";
     }
     ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
     ClsKham clsKham = clsKhamDel.find(this.clsmaOut);
     if (clsKham == null)
     {
       this.log.info("clsKham" + clsKham, new Object[0]);
       return "";
     }
     if (((this.ghichu != null) && (!this.ghichu.trim().equals(""))) || ((this.kqCLS != null) && (!this.kqCLS.trim().equals(""))) || ((this.byteImag != null) && (this.byteImag.length > 0)) || ((this.dulieuhinhanh != null) && (!this.dulieuhinhanh.trim().equals("")))) {
       this.daThucHien = Boolean.valueOf(true);
     }
     clsKham.setClskhamTh(this.daThucHien);
     clsKham.setClskhamKetqua(this.kqCLS);
     clsKham.setClskhamDulieuhinhanh(this.dulieuhinhanh);
     clsKham.setClskhamGhichu(this.ghichu);
     if ((this.daThucHien.booleanValue() == true) && (this.byteImag != null) && (this.byteImag.length > 0)) {
       clsKham.setClskhamImg(this.byteImag);
     } else {
       clsKham.setClskhamImg(null);
     }
     clsKhamDel.edit(clsKham);
     this.thatSuThucHien = this.daThucHien;
     FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
     return "";
   }

   public String quaylai()
     throws Exception
   {
     resetValue();

     return "clsthuthat";
   }

   private void setOtherValue(ThamKham thamkham)
   {
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     if ((thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     }
     this.tenBenhNhan = thamkham.getTiepdonMa(true).getBenhnhanMa(true).getBenhnhanHoten();
     this.maBenhNhan = thamkham.getTiepdonMa(true).getBenhnhanMa(true).getBenhnhanMa();
     this.donviTuoi = thamkham.getTiepdonMa(true).getBenhnhanMa(true).getBenhnhanDonvituoi();

     this.tuoi = thamkham.getTiepdonMa(true).getBenhnhanMa(true).getBenhnhanTuoi();
   }

   public String getFileanh()
   {
     return this.fileanh;
   }

   public void setFileanh(String fileanh)
   {
     this.fileanh = fileanh;
   }

   public void viewImg()
     throws Exception
   {
     if ((this.byteImagSource == null) || (this.byteImagSource.length == 0))
     {
       this.log.info("length ==0", new Object[0]);
       this.fileanh = null;
       return;
     }
     String ext = "jpg";
     if (this.maKhoa.equals(IConstantsRes.KHOA_VISINH_MA)) {
       ext = IConstantsRes.VISINH_UPLOAD;
     }
     String fileName = IConstantsRes.PATH_BASE + "/" + IConstantsRes.VI_TRI_THU_MUC + "/" + this.maBanKham + "_" + this.maTiepDon + "_" + this.maCLS;
     File file = new File(fileName + "." + ext);
     FileOutputStream fOS = new FileOutputStream(file);
     fOS.write(this.byteImagSource);
     fOS.close();

     this.fileanh = (this.maBanKham + "_" + this.maTiepDon + "_" + this.maCLS + "." + ext);
   }

   private int chieuDaiByte = 5242880;

   public void listener(UploadEvent event)
     throws IOException
   {
     UploadItem item = event.getUploadItem();
     File file = item.getFile();
     this.log.info("duong dan cua file " + file.getPath(), new Object[0]);
     try
     {
       FileInputStream fIS = new FileInputStream(file);

       this.byteImag = new byte[this.chieuDaiByte];

       byte[] imgData = new byte[1024];
       int endOfFile = fIS.read(imgData);
       int viTri = 0;
       while ((endOfFile != -1) && (viTri < this.chieuDaiByte))
       {
         for (int i = 0; i < endOfFile; i++) {
           this.byteImag[(viTri++)] = imgData[i];
         }
         endOfFile = fIS.read(imgData);
       }
       int chieudaithucte = viTri;
       byte[] imageThucTe = new byte[chieudaithucte];
       for (int i = 0; i < chieudaithucte; i++) {
         imageThucTe[i] = this.byteImag[i];
       }
       this.byteImag = imageThucTe;
     }
     catch (Exception ioe)
     {
       ioe.printStackTrace();
     }
     file.delete();
   }

   public String getMaBanKham()
   {
     return this.maBanKham;
   }

   public void setMaBanKham(String maBanKham)
   {
     this.maBanKham = maBanKham;
   }

   public String getMaTiepDon()
   {
     return this.maTiepDon;
   }

   public void setMaTiepDon(String maTiepDon)
   {
     this.maTiepDon = maTiepDon;
   }

   public Integer getClsmaOut()
   {
     return this.clsmaOut;
   }

   public void setClsmaOut(Integer clsmaOut)
   {
     this.clsmaOut = clsmaOut;
   }

   public String getTenBenhNhan()
   {
     return this.tenBenhNhan;
   }

   public void setTenBenhNhan(String tenBenhNhan)
   {
     this.tenBenhNhan = tenBenhNhan;
   }

   public Short getDonviTuoi()
   {
     return this.donviTuoi;
   }

   public void setDonviTuoi(Short donviTuoi)
   {
     this.donviTuoi = donviTuoi;
   }

   public String getMaBenhNhan()
   {
     return this.maBenhNhan;
   }

   public void setMaBenhNhan(String maBenhNhan)
   {
     this.maBenhNhan = maBenhNhan;
   }

   public Integer getTuoi()
   {
     return this.tuoi;
   }

   public void setTuoi(Integer tuoi)
   {
     this.tuoi = tuoi;
   }

   public String getMaCLS()
   {
     return this.maCLS;
   }

   public void setMaCLS(String maCLS)
   {
     this.maCLS = maCLS;
   }

   public String getTenCLS()
   {
     return this.tenCLS;
   }

   public void setTenCLS(String tenCLS)
   {
     this.tenCLS = tenCLS;
   }

   public Boolean getDaThucHien()
   {
     return this.daThucHien;
   }

   public void setDaThucHien(Boolean daThucHien)
   {
     this.daThucHien = daThucHien;
   }

   public Boolean getHasImage()
   {
     return this.hasImage;
   }

   public void setHasImage(Boolean hasImage)
   {
     this.hasImage = hasImage;
   }

   public Boolean getThatSuThucHien()
   {
     return this.thatSuThucHien;
   }

   public void setThatSuThucHien(Boolean thatSuThucHien)
   {
     this.thatSuThucHien = thatSuThucHien;
   }

   public String getMaKhoa()
   {
     return this.maKhoa;
   }

   public void setMaKhoa(String maKhoa)
   {
     this.maKhoa = maKhoa;
   }

   public String getGhichu()
   {
     return this.ghichu;
   }

   public void setGhichu(String ghichu)
   {
     this.ghichu = ghichu;
   }

   public Integer getLoaiCLS()
   {
     return this.loaiCLS;
   }

   public void setLoaiCLS(Integer loaiCLS)
   {
     this.loaiCLS = loaiCLS;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.CapNhatKetQuaCLSAction

 * JD-Core Version:    0.7.0.1

 */