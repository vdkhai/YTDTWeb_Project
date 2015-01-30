 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.KiemKeKhoDelegate;
 import com.iesvn.yte.dieutri.entity.KiemKeKho;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmNhaCungCap;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.SESSION)
 @Name("B4144_capnhatsolieuthucte")
 @Synchronized(timeout=6000000L)
 public class B4144_CapNhatSoLieuThucTe
   implements Serializable
 {
   private static final long serialVersionUID = -2130703730903444169L;
   private static Logger log = Logger.getLogger(B4144_CapNhatSoLieuThucTe.class);
   private KiemKeKho kiemkekho;
   private String ngayKiem;
   private String hanDung;

   @Create
   public void init()
     throws Exception
   {
     resetValue();
   }

   public void resetValue()
   {
     log.info("---resetValue");
     this.ngayKiem = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
     this.hanDung = "";
     this.kiemkekho = new KiemKeKho();
   }

   public void ghiNhan()
   {
     log.info("---ghiNhan");
     String[] ntnHanDung = this.hanDung.split("/");
     String maLk = this.kiemkekho.getDmthuocMaso().getDmthuocMa() + this.kiemkekho.getDmnctMaso().getDmnctMa() + this.kiemkekho.getDmnguonkinhphiMaso().getDmnguonkinhphiMa() + this.kiemkekho.getDmquocgiaMaso().getDmquocgiaMa() + this.kiemkekho.getDmnhasanxuatMaso().getDmnhasanxuatMa() + this.kiemkekho.getKiemkeNoiban().getDmnhacungcapMa() + this.kiemkekho.getKiemkeNamnhap() + ntnHanDung[0] + ntnHanDung[1] + ntnHanDung[2] + this.kiemkekho.getKiemkeDongia();










     KiemKeKhoDelegate delegate = KiemKeKhoDelegate.getInstance();
     log.info("khoa: " + this.kiemkekho.getDmkhoaMaso().getDmkhoaMa());
     log.info("malienket " + maLk);
     KiemKeKho kkk_tmp = delegate.findByKhoAndMaLienKet(this.kiemkekho.getDmkhoaMaso().getDmkhoaMa(), maLk);
     if (kkk_tmp != null)
     {
       this.kiemkekho = kkk_tmp;
       this.kiemkekho.setKiemkeMalienket(maLk);
       this.kiemkekho.setKiemkeNgaygiocn(new Date());
       this.kiemkekho.setKiemkeNgaykiem(Utils.getDBDate("00:00", this.ngayKiem).getTime());
       String result = delegate.updateAndEditTonKhoDau(this.kiemkekho);
       if ((result == null) || (result.equals(""))) {
         FacesMessages.instance().add(IConstantsRes.KKK_CN_THATBAI, new Object[0]);
       } else {
         FacesMessages.instance().add(IConstantsRes.KKK_CN_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.KKK_NULL, new Object[0]);
     }
   }

   public String getNgayKiem()
   {
     return this.ngayKiem;
   }

   public void setNgayKiem(String ngayKiem)
   {
     this.ngayKiem = ngayKiem;
   }

   public KiemKeKho getKiemkekho()
   {
     return this.kiemkekho;
   }

   public void setKiemkekho(KiemKeKho kiemkekho)
   {
     this.kiemkekho = kiemkekho;
   }

   public String getHanDung()
   {
     return this.hanDung;
   }

   public void setHanDung(String hanDung)
   {
     this.hanDung = hanDung;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B4144_CapNhatSoLieuThucTe

 * JD-Core Version:    0.7.0.1

 */