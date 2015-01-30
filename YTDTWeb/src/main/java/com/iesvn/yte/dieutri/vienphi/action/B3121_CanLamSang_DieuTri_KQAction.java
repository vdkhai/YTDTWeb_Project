 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Date;
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
 @Name("B3121_CanLamSang_DieuTri_KQAction")
 @Synchronized(timeout=6000000L)
 public class B3121_CanLamSang_DieuTri_KQAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String ngaySinh;
   @Logger
   private Log log;
   private String maKhoaKham;
   private String soBenhAn;
   private String tenBenhNhan;
   private Short donviTuoi;
   private String maBenhNhan;
   private Integer tuoi;
   private Integer clsmaOut;
   private String maCLS;
   private String tenCLS;
   private Boolean daThucHien;
   private String kqCLS;
   private String maKhoa;
   private String ghichu;
   private Integer loaiCLS = Integer.valueOf(0);
   private Boolean thatSuThucHien;
   public Boolean hasImage;
   private String fileanh;
   private byte[] byteImagSource;
   private byte[] byteImag;

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
     this.log.info("maKhoaKham:" + this.maKhoaKham, new Object[0]);
     this.log.info("soBenhAn:" + this.soBenhAn, new Object[0]);
     this.log.info("clsmaOut:" + this.clsmaOut, new Object[0]);
     if ((this.maKhoaKham == null) || (this.maKhoaKham.equals("")) || (this.soBenhAn == null) || (this.soBenhAn.equals("")))
     {
       resetValue();
       return;
     }
     if (this.clsmaOut == null)
     {
       resetValue();
       return;
     }
     ClsMoDelegate clsmoDel = ClsMoDelegate.getInstance();
     ClsMo clsMo = clsmoDel.find(this.clsmaOut);
     if (clsMo == null)
     {
       this.log.info("ko tim thay cls:" + this.clsmaOut, new Object[0]);
       resetValue();
       return;
     }
     this.maCLS = clsMo.getClsmoMahang(true).getDtdmclsbgMa();
     this.tenCLS = clsMo.getClsmoMahang(true).getDtdmclsbgDiengiai();
     this.daThucHien = clsMo.getClsmoTh();
     this.kqCLS = clsMo.getClsmoKetqua();


     this.thatSuThucHien = this.daThucHien;


     this.maKhoa = clsMo.getClsmoKhoa(true).getDmkhoaMa();
     this.ghichu = clsMo.getClsmoGhichu();
     if ((clsMo.getClsmoMahang(true).getDtdmclsbgXn() != null) && (clsMo.getClsmoMahang(true).getDtdmclsbgXn().booleanValue())) {
       this.loaiCLS = Integer.valueOf(1);
     } else if ((clsMo.getClsmoMahang(true).getDtdmclsbgCdha() != null) && (!clsMo.getClsmoMahang(true).getDtdmclsbgCdha().equals(""))) {
       this.loaiCLS = Integer.valueOf(2);
     }
     HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
     Hsba hsba = hsbaDelegate.find(this.soBenhAn);
     if (hsba == null)
     {
       resetValue();
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);
       return;
     }
     try
     {
       if ((this.daThucHien != null) && (this.daThucHien.booleanValue() == true))
       {
         viewImg();
         this.hasImage = Boolean.valueOf(true);
       }
       else
       {
         this.hasImage = Boolean.valueOf(false);
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     setOtherValue(hsba);
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
     ClsMoDelegate clsMoDel = ClsMoDelegate.getInstance();
     ClsMo clsMo = clsMoDel.find(this.clsmaOut);
     if (clsMo == null)
     {
       this.log.info("clsKham" + clsMo, new Object[0]);
       return "";
     }
     if (((this.ghichu != null) && (!this.ghichu.trim().equals(""))) || ((this.kqCLS != null) && (!this.kqCLS.trim().equals(""))) || ((this.byteImag != null) && (this.byteImag.length > 0))) {
       this.daThucHien = Boolean.valueOf(true);
     }
     clsMo.setClsmoTh(this.daThucHien);
     clsMo.setClsmoKetqua(this.kqCLS);
     clsMo.setClsmoGhichu(this.ghichu);
     if ((this.daThucHien.booleanValue() == true) && (this.byteImag != null) && (this.byteImag.length > 0)) {}
     clsMoDel.edit(clsMo);
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

   private void setOtherValue(Hsba hsba)
   {
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     if ((hsba.getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!hsba.getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(hsba.getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     }
     this.tenBenhNhan = hsba.getBenhnhanMa(true).getBenhnhanHoten();
     this.maBenhNhan = hsba.getBenhnhanMa(true).getBenhnhanMa();
     this.donviTuoi = hsba.getBenhnhanMa(true).getBenhnhanDonvituoi();

     this.tuoi = hsba.getBenhnhanMa(true).getBenhnhanTuoi();
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
     String fileName = IConstantsRes.PATH_BASE + "/" + IConstantsRes.VI_TRI_THU_MUC + "/" + this.maKhoaKham + "_" + this.soBenhAn + "_" + this.maCLS;
     File file = new File(fileName + "." + ext);
     FileOutputStream fOS = new FileOutputStream(file);
     fOS.write(this.byteImagSource);
     fOS.close();

     this.fileanh = (this.maKhoaKham + "_" + this.soBenhAn + "_" + this.maCLS + "." + ext);
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

   public String getMaKhoaKham()
   {
     return this.maKhoaKham;
   }

   public void setMaKhoaKham(String maKhoaKham)
   {
     this.maKhoaKham = maKhoaKham;
   }

   public String getSoBenhAn()
   {
     return this.soBenhAn;
   }

   public void setSoBenhAn(String soBenhAn)
   {
     this.soBenhAn = soBenhAn;
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

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3121_CanLamSang_DieuTri_KQAction

 * JD-Core Version:    0.7.0.1

 */