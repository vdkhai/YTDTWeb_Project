 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.CtNhapKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuNhapKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtNhapKho;
 import com.iesvn.yte.dieutri.entity.DtDmKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuNhapKho;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmNhaCungCap;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;

 public class GetPhieuNhapKhoAction
   extends Action
 {
   private static Logger logger = Logger.getLogger(GetPhieuNhapKhoAction.class);

   public String performAction(String request)
   {
     logger.debug(request);
     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
     try
     {
       PhieuNhapKho pnk = pnkDelegate.findByPhieunhapkhoMa(request);

       logger.debug("pnk ma " + pnk.getPhieunhapkhoMa());
       if (pnk != null)
       {
         String loaiMa = pnk.getDmloaithuocMaso() == null ? " " : pnk.getDmloaithuocMaso().getDmloaithuocMa();
         String nguonMa = pnk.getDmnctMaso() == null ? " " : pnk.getDmnctMaso().getDmnctMa();
         String nvTL = pnk.getDtdmnhanvienTieplieu() == null ? " " : pnk.getDtdmnhanvienTieplieu().getDtdmnhanvienMa();
         String khoMa = pnk.getDtdmkhoMaso() == null ? " " : pnk.getDtdmkhoMaso().getDtdmkhoMa();
         String nbMa = pnk.getDtdmnoibanMa() == null ? " " : pnk.getDtdmnoibanMa().getDmnhacungcapMa();
         String kpMa = pnk.getDmnguonkinhphiMaso() == null ? " " : pnk.getDmnguonkinhphiMaso().getDmnguonkinhphiMa();
         String chungTu = pnk.getPhieunhapkhoChungtu() == null ? " " : pnk.getPhieunhapkhoChungtu();
         String soHD = pnk.getPhieunhapkhoSohoadon() == null ? " " : pnk.getPhieunhapkhoSohoadon();
         String tt = pnk.getPhieunhapkhoThanhtien() == null ? " " : pnk.getPhieunhapkhoThanhtien().toString();
         String thue = pnk.getPhieunhapkhoThue() == null ? " " : pnk.getPhieunhapkhoThue().toString();
         String mucThue = pnk.getPhieunhapkhoMucthue() == null ? " " : pnk.getPhieunhapkhoMucthue().toString();
         String nvCn = pnk.getDtdmnhanvienCn() == null ? " " : pnk.getDtdmnhanvienCn().getDtdmnhanvienMa();
         String ngayHD = " ";
         String ngaygioCn = "";
         if (pnk.getPhieunhapkhoNgayhoadon() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = pnk.getPhieunhapkhoNgayhoadon();
           ngayHD = df.format(Long.valueOf(cal.getTime()));
         }
         if (pnk.getPhieunhapkhoNgaygiocn() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = pnk.getPhieunhapkhoNgaygiocn();
           ngaygioCn = df.format(Long.valueOf(cal.getTime()));
         }
         buf.append("<PHIEU_NHAP_KHO PHIEU_NHAP_KHO_MAPHU='' PHIEUNHAPKHO_MA='" + pnk.getPhieunhapkhoMa() + "' DTDMLOAI_MA='" + loaiMa + "' DTDMNGUON_MA='" + nguonMa + "' PHIEUNHAPKHO_CHUNGTU='" + chungTu + "' DTDMNHANVIEN_TIEPLIEU='" + nvTL + "' PHIEUNHAPKHO_SOHOADON='" + soHD + "' PHIEUNHAPKHO_NGAYHOADON='" + ngayHD + "' DMKHO_MASO='" + khoMa + "' DTDMNOIBAN_MA='" + nbMa + "' PHIEUNHAPKHO_MUCTHUE='" + mucThue + "' PHIEUNHAPKHO_THUE='" + thue + "' DMKINHPHI_MASO='" + kpMa + "' PHIEUNHAPKHO_THANHTIEN='" + tt + "' DTDMNHANVIEN_CN='" + nvCn + "' PHIEUNHAPKHO_NGAYGIOCN='" + ngaygioCn + "'>");
















         CtNhapKhoDelegate ctNhapKhoDelegate = CtNhapKhoDelegate.getInstance();


         List<CtNhapKho> listCTNK = ctNhapKhoDelegate.findByPhieuNhapKho(pnk.getPhieunhapkhoMa());
         for (CtNhapKho ctnk : listCTNK)
         {
           String dvtMa = ctnk.getDmthuocMaso().getDmdonvitinhMaso() == null ? "" : ctnk.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMa();

           String quyCach = ctnk.getCtnhapkhoQuycach() == null ? "" : ctnk.getCtnhapkhoQuycach().toString();

           String lo = ctnk.getCtnhapkhoLo() == null ? "" : ctnk.getCtnhapkhoLo();

           String sodk = ctnk.getCtnhapkhoSodangky() == null ? "" : ctnk.getCtnhapkhoSodangky();


           String soLuong = "" + ctnk.getCtnhapkhoSoluong();
           String donGia = "" + ctnk.getCtnhapkhoDongia();

           String donGiaBan = ctnk.getCtnhapkhoDongiaban() == null ? "" : ctnk.getCtnhapkhoDongiaban().toString();

           String namNhap = ctnk.getCtnhapkhoNamnhap() == null ? "" : ctnk.getCtnhapkhoNamnhap().toString();


           String dmtMa = ctnk.getDmthuocMaso() == null ? "" : ctnk.getDmthuocMaso().getDmthuocMa();

           String qgMa = ctnk.getDmquocgiaMaso() == null ? "" : ctnk.getDmquocgiaMaso().getDmquocgiaMa();


           String hsxMa = ctnk.getDmnhasanxuatMaso() == null ? "" : ctnk.getDmnhasanxuatMaso().getDmnhasanxuatMa();

           String ngayNhap = " ";
           if (ctnk.getCtnhapkhoNgaygiocn() != null)
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             Date cal = ctnk.getCtnhapkhoNgaygiocn();
             ngayNhap = df.format(Long.valueOf(cal.getTime()));
           }
           String ngayHd = "";
           String thangHd = "";
           String namHd = "";
           if (!ctnk.getCtnhapkhoNgayhandung().equals(""))
           {
             ngayHd = ctnk.getCtnhapkhoNgayhandung();
             if (ngayHd.length() < 2) {
               ngayHd = "0" + ngayHd;
             }
           }
           if (!ctnk.getCtnhapkhoThanghandung().equals(""))
           {
             thangHd = ctnk.getCtnhapkhoThanghandung();
             if (thangHd.length() < 2) {
               thangHd = "0" + thangHd;
             }
           }
           if (!ctnk.getCtnhapkhoNamhandung().equals("")) {
             namHd = ctnk.getCtnhapkhoNamhandung();
           }
           buf.append("<CT_NHAP_KHO DONVITINH_MA='" + dvtMa + "' CTNHAPKHO_QUYCACH='" + quyCach + "' CTNHAPKHO_SOLUONG='" + soLuong + "' TONKHO_NGAYNHAP='" + ngayNhap + "' DMTHUOC_MASO='" + dmtMa + "' CTNHAPKHO_NGAYHANDUNG='" + ngayHd + "' CTNHAPKHO_THANGHANDUNG='" + thangHd + "' CTNHAPKHO_NAMHANDUNG='" + namHd + "' DMQUOCGIA_MASO='" + qgMa + "' DTDMHANGSX_MA='" + hsxMa + "' CTNHAPKHO_DONGIA='" + donGia + "' CTNHAPKHO_DONGIABAN='" + donGiaBan + "' CTNHAPKHO_NAMNHAP='" + namNhap + "' CTNHAPKHO_LO='" + lo + "' CTNHAPKHO_SODANGKY='" + sodk + "' CTNHAPKHO_MA='" + ctnk.getCtnhapkhoMa() + "' />");
         }
       }
       else
       {
         return "<list></list>";
       }
       buf.append("</PHIEU_NHAP_KHO>");
     }
     catch (Exception e)
     {
       logger.error(e);
       return "<list></list>";
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetPhieuNhapKhoAction

 * JD-Core Version:    0.7.0.1

 */