 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.PAGE)
 @Name("B3217_CapnhatsotheBHYT")
 @Synchronized(timeout=6000000L)
 public class CapnhatsotheBHYT
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private String msgFail = "";
   private String msgSuccess = "";
   private static Logger log = Logger.getLogger(CapnhatsotheBHYT.class);
   private static final long serialVersionUID = 10L;
   private String ngayhientai = "";
   private BenhNhan benhNhan;
   private Hsba hoSoBenhAn;
   private HsbaBhyt hsbaBHYT;
   private String ngaySinh = null;
   private String gioi = "";
   private String ngayvaovien = null;
   private String giatri0 = null;
   private String giatri1 = null;
   private String giatri2 = null;
   private String giatri3 = null;
   private String moc1 = null;
   private String moc2 = null;
   private String moc3 = null;
   private String listMaTinhBhyt;
   private Boolean coGiayChuyenVien;
   private boolean disabledGhinhan = false;

   @Create
   public void init()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayhientai = formatter.format(new Date());
     resetValue();
   }

   public void resetValue()
   {
     this.benhNhan = new BenhNhan();
     this.hoSoBenhAn = new Hsba();
     this.hsbaBHYT = new HsbaBhyt();
     this.hoSoBenhAn.setBenhnhanMa(this.benhNhan);
     setGiatri0("");
     setGiatri1("");
     setGiatri2("");
     setGiatri3("");
     setMoc1("");
     setMoc2("");
     setMoc3("");
     setNgayvaovien("");
     setGioi(null);
     setNgaySinh("");
     this.disabledGhinhan = false;
     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

     List<DmTinh> listDmTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh");
     this.listMaTinhBhyt = "";
     for (DmTinh each : listDmTinh) {
       this.listMaTinhBhyt = (this.listMaTinhBhyt + each.getDmtinhBHYT() + ",");
     }
   }

   public void displayInfor()
     throws Exception
   {
     log.info("begin displayInfo=======");

     this.hsbaBHYT = new HsbaBhyt();

     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     try
     {
       this.disabledGhinhan = false;
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
       String sba = this.hoSoBenhAn.getHsbaSovaovien();
       if ((sba != null) && (!sba.trim().equals("")))
       {
         Hsba hoSoBenhAn_temp = hsbaDelegate.find(sba);
         if (hoSoBenhAn_temp != null)
         {
           setHoSoBenhAn(hoSoBenhAn_temp);
           SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

           String doiTuong = this.hoSoBenhAn.getDoituongMa().getDmdoituongMa();
           if (doiTuong.equals("TP"))
           {
             FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI_CNBHYT, new Object[0]);
             this.disabledGhinhan = true;
             return;
           }
           HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();
           HsThtoan hsbaHsThtoan_temp = hsttDelegate.findBySovaovien(this.hoSoBenhAn.getHsbaSovaovien());
           if ((hsbaHsThtoan_temp != null) &&
             (hsbaHsThtoan_temp.getHsthtoanDatt() != null) && (hsbaHsThtoan_temp.getHsthtoanDatt().booleanValue()))
           {
             FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
             this.disabledGhinhan = true;
             return;
           }
           this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();
           formatter = new SimpleDateFormat(FORMAT_DATE);
           if ((this.benhNhan.getBenhnhanNgaysinh() != null) && (!this.benhNhan.getBenhnhanNgaysinh().equals(""))) {
             this.ngaySinh = formatter.format(Long.valueOf(this.benhNhan.getBenhnhanNgaysinh().getTime()));
           }
           SetInforUtil.setInforIfNullForBN(this.benhNhan);

           log.info("-------Set ngay vao vien--------");
           if ((this.hoSoBenhAn.getHsbaNgaygiovaov() != null) && (!this.hoSoBenhAn.getHsbaNgaygiovaov().equals(""))) {
             this.ngayvaovien = formatter.format(Long.valueOf(this.hoSoBenhAn.getHsbaNgaygiovaov().getTime()));
           } else {
             log.info("ngay vao vien null");
           }
           log.info("-------Set gioi tinh--------");
           if (this.benhNhan.getDmgtMaso() != null)
           {
             log.info("bat dau vao ham set gioi tinh");
             if ("1".equals(this.benhNhan.getDmgtMaso().getDmgtMa())) {
               setGioi("r1");
             } else {
               setGioi("r2");
             }
           }
           else
           {
             setGioi(null);
           }
           log.info("gioi tinh :" + this.gioi);

           log.info("-------Tim ho so bao hiem y te--------");
           HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
           HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hoSoBenhAn.getHsbaSovaovien());
           if (hsbaBhytLast != null)
           {
             this.hsbaBHYT = hsbaBhytLast;
             log.info("hs benh nhan BHYT " + this.hsbaBHYT);

             log.info("-------Set gia tri 0--------");
             if ((this.hsbaBHYT.getHsbabhytGiatri0() != null) && (!this.hsbaBHYT.getHsbabhytGiatri0().equals(""))) {
               this.giatri0 = formatter.format(Long.valueOf(this.hsbaBHYT.getHsbabhytGiatri0().getTime()));
             } else {
               log.info("gia tri 0 null");
             }
             log.info("-------Set gia tri 1--------");
             if ((this.hsbaBHYT.getHsbabhytGiatri1() != null) && (!this.hsbaBHYT.getHsbabhytGiatri1().equals(""))) {
               this.giatri1 = formatter.format(Long.valueOf(this.hsbaBHYT.getHsbabhytGiatri1().getTime()));
             } else {
               log.info("gia tri 1 null");
             }
             log.info("-------Set gia tri 2--------");
             if ((this.hsbaBHYT.getHsbabhytGiatri2() != null) && (!this.hsbaBHYT.getHsbabhytGiatri2().equals(""))) {
               this.giatri2 = formatter.format(Long.valueOf(this.hsbaBHYT.getHsbabhytGiatri2().getTime()));
             } else {
               log.info("gia tri 2 null");
             }
             log.info("-------Set gia tri 3--------");
             if ((this.hsbaBHYT.getHsbabhytGiatri3() != null) && (!this.hsbaBHYT.getHsbabhytGiatri3().equals(""))) {
               this.giatri3 = formatter.format(Long.valueOf(this.hsbaBHYT.getHsbabhytGiatri3().getTime()));
             } else {
               log.info("gia tri 3 null");
             }
             log.info("-------Set moc 1--------");
             if ((this.hsbaBHYT.getHsbabhytMoc1() != null) && (!this.hsbaBHYT.getHsbabhytMoc1().equals(""))) {
               this.moc1 = formatter.format(Long.valueOf(this.hsbaBHYT.getHsbabhytMoc1().getTime()));
             } else {
               log.info("moc 1 null");
             }
             log.info("-------Set moc 2--------");
             if ((this.hsbaBHYT.getHsbabhytMoc2() != null) && (!this.hsbaBHYT.getHsbabhytMoc2().equals(""))) {
               this.moc2 = formatter.format(Long.valueOf(this.hsbaBHYT.getHsbabhytMoc2().getTime()));
             } else {
               log.info("moc 2 null");
             }
             log.info("-------Set moc 3--------");
             if ((this.hsbaBHYT.getHsbabhytMoc3() != null) && (!this.hsbaBHYT.getHsbabhytMoc3().equals(""))) {
               this.moc3 = formatter.format(Long.valueOf(this.hsbaBHYT.getHsbabhytMoc3().getTime()));
             } else {
               log.info("moc 2 null");
             }
           }
         }
         else
         {
           this.hoSoBenhAn.setHsbaSovaovien("");
           FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND, new Object[0]);
           log.info("khong tim thay sobenhan");
         }
         log.info("----hoSoBenhAn_temp-:" + hoSoBenhAn_temp);
       }
     }
     catch (Exception e)
     {
       log.info("ERROR displayInfo=======" + e);
       e.printStackTrace();
     }
     log.info("End displayInfo=======");
   }

   public void ghiNhan()
     throws Exception
   {
     log.info("--------------Bat dau ghi nhan---------------");
     try
     {
       log.info("lay gia tri 0" + this.giatri0);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       if (!this.giatri0.trim().equals("")) {
         this.hsbaBHYT.setHsbabhytGiatri0(sdf.parse(this.giatri0));
       }
       log.info("lay gia tri 1" + this.giatri1);
       if (!this.giatri1.trim().equals("")) {
         this.hsbaBHYT.setHsbabhytGiatri1(sdf.parse(this.giatri1));
       }
       log.info("lay gia tri 2" + this.giatri2);
       if (!this.giatri2.trim().equals("")) {
         this.hsbaBHYT.setHsbabhytGiatri2(sdf.parse(this.giatri2));
       }
       log.info("lay gia tri 3" + this.giatri3);
       if (!this.giatri3.trim().equals("")) {
         this.hsbaBHYT.setHsbabhytGiatri3(sdf.parse(this.giatri3));
       }
       log.info("Moc 1" + this.moc1);
       if (!this.moc1.trim().equals("")) {
         this.hsbaBHYT.setHsbabhytMoc1(sdf.parse(this.moc1));
       }
       log.info("Moc 2" + this.moc2);
       if (!this.moc2.trim().equals("")) {
         this.hsbaBHYT.setHsbabhytMoc2(sdf.parse(this.moc2));
       }
       log.info("Moc 3" + this.moc3);
       if (!this.moc3.trim().equals("")) {
         this.hsbaBHYT.setHsbabhytMoc3(sdf.parse(this.moc3));
       }
       log.info("nam tham gia" + this.hsbaBHYT.getHsbabhytNamtg());
       log.info("thanh toan " + this.hoSoBenhAn.getHsbaThanhtoantn());
       log.info("ma so HSBA " + this.hsbaBHYT.getHsbabhytMa());

       HsbaBhytDelegate hsbcDel = HsbaBhytDelegate.getInstance();
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();

       TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
       TiepDon td = tdDelegate.find(this.hsbaBHYT.getHsbaSovaovien().getTiepdonMa());

       DieuTriUtilDelegate dieutriUtil = DieuTriUtilDelegate.getInstance();
       Object obj = dieutriUtil.findByMa("BH", "DmDoiTuong", "dmdoituongMa");
       if (obj != null)
       {
         DmDoiTuong doiTuong = (DmDoiTuong)obj;
         this.hoSoBenhAn.setDoituongMa(doiTuong);
         td.setDoituongMa(doiTuong);
       }
       if (!"".equals(this.hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhBHYT()))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmTinh tinhbh = (DmTinh)ws.findByMa(this.hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhBHYT(), "DmTinh", "dmtinhBHYT");




         this.hsbaBHYT.setHsbabhytTinhbh(tinhbh);
         td.setTinhbhytMa(tinhbh);
       }
       td.setTiepdonSothebh(this.hsbaBHYT.getHsbabhytSothebh());
       td.setKhoibhytMa(this.hsbaBHYT.getHsbabhytKhoibh());
       td.setTinhbhytMa(this.hsbaBHYT.getHsbabhytTinhbh());
       td.setKcbbhytMa(this.hsbaBHYT.getHsbabhytMakcb());
       td.setTiepdonGiatri1(sdf.parse(this.giatri0));
       td.setTiepdonGiatri2(sdf.parse(this.giatri1));
       td.setTiepdonGiatri3(sdf.parse(this.giatri2));
       td.setTiepdonGiatri4(sdf.parse(this.giatri3));
       td.setTiepdonMoc1(sdf.parse(this.moc1));
       td.setTiepdonMoc2(sdf.parse(this.moc2));
       td.setTiepdonMoc3(sdf.parse(this.moc3));
       td.setTiepdonMacoquan(this.hsbaBHYT.getHsbabhytCoquanbh());
       td.setTiepdonCoGiayGioiThieu(this.hsbaBHYT.getHsbabhytCoGiayChuyenVien());


       DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
       if (this.hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMaso() != null)
       {
         DmTinh tinh = (DmTinh)dele.findByMaSo(this.hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMaso(), "DmTinh", "dmtinhMaso");
         if (tinh != null)
         {
           this.hsbaBHYT.setHsbabhytTinhbh(tinh);
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.NOT_FOUND, new Object[] { IConstantsRes.TINH_CAP_BHYT, this.hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhBHYT() });
           return;
         }
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, new Object[] { IConstantsRes.TINH_CAP_BHYT });
         return;
       }
       if (this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMaso() != null)
       {
         DmBenhVien noiKCB = (DmBenhVien)dele.findByMa(this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa(), "DmBenhVien", "dmbenhvienMa");
         if (noiKCB == null)
         {
           FacesMessages.instance().add(IConstantsRes.NOT_FOUND, new Object[] { IConstantsRes.NOI_DK_KCB, this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa() });
           return;
         }
         this.hsbaBHYT.setHsbabhytMakcb(noiKCB);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, new Object[] { IConstantsRes.NOI_DK_KCB }); return;
       }
       Short tuyenBH;

       if (this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa().equalsIgnoreCase(IConstantsRes.MA_BENH_VIEN))
       {
         tuyenBH = new Short("1");
       }
       else
       {

         if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("TINH"))
         {

           if (this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa().startsWith(IConstantsRes.MA_TINH_BHYT)) {
             tuyenBH = new Short("2");
           } else {
             tuyenBH = new Short("3");
           }
         }
         else
         {

           if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("HUYEN"))
           {

             if ((this.hsbaBHYT.getHsbabhytMakcb(true).getDmhuyenMaso() != null) && (this.hsbaBHYT.getHsbabhytMakcb(true).getDmhuyenMaso(true).getDmhuyenMaso().toString().equals(IConstantsRes.MASO_HUYEN_TRIEN_KHAI))) {
               tuyenBH = new Short("2");
             } else {
               tuyenBH = new Short("3");
             }
           }
           else
           {
             tuyenBH = new Short("3");
           }
         }
       }
       td.setTiepdonTuyen(tuyenBH);
       RemoveUtil.removeAllNullFromHSBA(this.hoSoBenhAn);
       RemoveUtil.removeAllNullFromHSBABHYT(this.hsbaBHYT);


       hsbaDelegate.edit(this.hoSoBenhAn);
       if (this.hoSoBenhAn.getHsbaType() == null) {
         tdDelegate.edit(td);
       }
       log.info("hsba dieu tri" + this.hoSoBenhAn.getHsbaDieutri());
       this.hsbaBHYT.setHsbabhytTuyen(tuyenBH);
       if (this.hsbaBHYT.getHsbabhytMa() == null)
       {
         log.info("=========Truong hop them moi HS BHYT=========");
         this.hsbaBHYT.setHsbaSovaovien(this.hoSoBenhAn);
         hsbcDel.create(this.hsbaBHYT);
         FacesMessages.instance().add("Hồ sơ BHYT đã lưu thành công !", new Object[0]);
       }
       else
       {
         log.info("=========Truong hop cap nhat HS BHYT=========");
         hsbcDel.edit(this.hsbaBHYT);

         FacesMessages.instance().add("Hồ sơ BHYT đã cập nhật thành công !", new Object[0]);
       }
       hsbcDel.capnhatGiaClsTheoThoiGianBaoHiem(this.hsbaBHYT);
       if (ChuyenVaoNoiTruDelegate.getInstance().findByMaTiepDon(td.getTiepdonMa()) != null) {
         tdDelegate.capnhatGiaClsTheoThoiGianBaoHiem(td);
       }
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     log.info("--------------ket thuc ghi nhan---------------");
   }

   public String getMsgFail()
   {
     return this.msgFail;
   }

   public void setMsgFail(String msgFail)
   {
     this.msgFail = msgFail;
   }

   public String getMsgSuccess()
   {
     return this.msgSuccess;
   }

   public void setMsgSuccess(String msgSuccess)
   {
     this.msgSuccess = msgSuccess;
   }

   public void nhaplai()
     throws Exception
   {
     log.debug("nhaplai()");
     resetValue();
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public void setHoSoBenhAn(Hsba hoSoBenhAn)
   {
     this.hoSoBenhAn = hoSoBenhAn;
   }

   public Hsba getHoSoBenhAn()
   {
     return this.hoSoBenhAn;
   }

   public void setHsbaBHYT(HsbaBhyt hsbaBHYT)
   {
     this.hsbaBHYT = hsbaBHYT;
   }

   public HsbaBhyt getHsbaBHYT()
   {
     return this.hsbaBHYT;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setNgayvaovien(String ngayvaovien)
   {
     this.ngayvaovien = ngayvaovien;
   }

   public String getNgayvaovien()
   {
     return this.ngayvaovien;
   }

   public String getGiatri0()
   {
     return this.giatri0;
   }

   public void setGiatri0(String giatri0)
   {
     this.giatri0 = giatri0;
   }

   public String getGiatri1()
   {
     return this.giatri1;
   }

   public void setGiatri1(String giatri1)
   {
     this.giatri1 = giatri1;
   }

   public String getGiatri2()
   {
     return this.giatri2;
   }

   public void setGiatri2(String giatri2)
   {
     this.giatri2 = giatri2;
   }

   public String getGiatri3()
   {
     return this.giatri3;
   }

   public void setGiatri3(String giatri3)
   {
     this.giatri3 = giatri3;
   }

   public void setMoc1(String moc1)
   {
     this.moc1 = moc1;
   }

   public String getMoc1()
   {
     return this.moc1;
   }

   public void setMoc2(String moc2)
   {
     this.moc2 = moc2;
   }

   public String getMoc2()
   {
     return this.moc2;
   }

   public String getMoc3()
   {
     return this.moc3;
   }

   public void setMoc3(String moc3)
   {
     this.moc3 = moc3;
   }

   public Boolean getCoGiayChuyenVien()
   {
     return this.coGiayChuyenVien;
   }

   public void setCoGiayChuyenVien(Boolean coGiayChuyenVien)
   {
     this.coGiayChuyenVien = coGiayChuyenVien;
   }

   public boolean isDisabledGhinhan()
   {
     return this.disabledGhinhan;
   }

   public void setDisabledGhinhan(boolean disabledGhinhan)
   {
     this.disabledGhinhan = disabledGhinhan;
   }

   public String getListMaTinhBhyt()
   {
     return this.listMaTinhBhyt;
   }

   public void setListMaTinhBhyt(String listMaTinhBhyt)
   {
     this.listMaTinhBhyt = listMaTinhBhyt;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.CapnhatsotheBHYT

 * JD-Core Version:    0.7.0.1

 */