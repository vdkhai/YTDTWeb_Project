 package com.iesvn.yte.dieutri.setinfor;

 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.CtPhieuDt;
 import com.iesvn.yte.dieutri.entity.CtPhieuGiaoBan;
 import com.iesvn.yte.dieutri.entity.CtTraKho;
 import com.iesvn.yte.dieutri.entity.CtXuatKho;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCapCuuPhien;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.DtDmNoiSinh;
 import com.iesvn.yte.dieutri.entity.DtDmPhauThuat;
 import com.iesvn.yte.dieutri.entity.DtDmVoCam;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.HsbaMo;
 import com.iesvn.yte.dieutri.entity.HsbaNop;
 import com.iesvn.yte.dieutri.entity.PhieuBaoAn;
 import com.iesvn.yte.dieutri.entity.PhieuDuTru;
 import com.iesvn.yte.dieutri.entity.PhieuGiaoBan;
 import com.iesvn.yte.dieutri.entity.PhieuGiaoBanThanhPhanThamDu;
 import com.iesvn.yte.dieutri.entity.PhieuTraKho;
 import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.entity.TuvongTruoc;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmCachDungThuoc;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDiaDiem;
 import com.iesvn.yte.entity.DmDieuTri;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmTaiNan;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import java.io.PrintStream;
 import org.apache.log4j.Logger;

 public class SetInforUtil
 {
   private static Logger log = Logger.getLogger(SetInforUtil.class);

   public static void setInforIfNullForTonKho(TonKho tonkho)
   {
     if (tonkho.getDmthuocMaso() == null) {
       tonkho.setDmthuocMaso(new DmThuoc());
     }
   }

   public static void setInforIfNullForThuocPhongKham(ThuocPhongKham thuocPhongKham)
   {
     System.out.println("-----setInforIfNullForThuocPhongKham");
     if (thuocPhongKham.getThuocphongkhamMathuoc() == null) {
       thuocPhongKham.setThuocphongkhamMathuoc(new DmThuoc());
     }
     if (thuocPhongKham.getThuocphongkhamMathuoc().getDmdonvitinhMaso() == null) {
       thuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(new DmDonViTinh());
     }
     if (thuocPhongKham.getThuocphongkhamMathuoc().getDmcachdungMaso() == null)
     {
       thuocPhongKham.getThuocphongkhamMathuoc().setDmcachdungMaso(new DmCachDungThuoc());
       System.out.println("-----cach dung1: " + thuocPhongKham.getThuocphongkhamMathuoc().getDmcachdungMaso());
     }
     else
     {
       System.out.println("-----else cach dung1: " + thuocPhongKham.getThuocphongkhamMathuoc().getDmcachdungMaso());
     }
     if (thuocPhongKham.getThuocphongkhamQuocgia() == null) {
       thuocPhongKham.setThuocphongkhamQuocgia(new DmQuocGia());
     }
     if (thuocPhongKham.getThuocphongkhamHangsx() == null) {
       thuocPhongKham.setThuocphongkhamHangsx(new DmNhaSanXuat());
     }
     if (thuocPhongKham.getDmnctMaso() == null) {
       thuocPhongKham.setDmnctMaso(new DmNguonChuongTrinh());
     }
     if (thuocPhongKham.getDmnguonkinhphiMaso() == null) {
       thuocPhongKham.setDmnguonkinhphiMaso(new DmNguonKinhPhi());
     }
     if (thuocPhongKham.getThuocphongkhamMadung() == null) {
       thuocPhongKham.setThuocphongkhamMadung(new DmCachDungThuoc());
     }
   }

   public static void setInforIfNullForTiepDon(TiepDon tiepdon)
   {
     if (tiepdon.getDoituongMa() == null) {
       tiepdon.setDoituongMa(new DmDoiTuong());
     }
     if (tiepdon.getTainanMa() == null) {
       tiepdon.setTainanMa(new DmTaiNan());
     }
     if (tiepdon.getDmptgtnMaso() == null) {
       tiepdon.setDmptgtnMaso(new DmPhuongThucGayTaiNan());
     }
     if (tiepdon.getTiepdonDonvigoi() == null) {
       tiepdon.setTiepdonDonvigoi(new DmBenhVien());
     }
     if (tiepdon.getTiepdonMachdoanb0() == null) {
       tiepdon.setTiepdonMachdoanb0(new DmBenhIcd());
     }
     if (tiepdon.getTiepdonTuvong() == null) {
       tiepdon.setTiepdonTuvong(new DmBenhIcd());
     }
     if (tiepdon.getTiepdonChvien() == null) {
       tiepdon.setTiepdonChvien(new DmBenhVien());
     }
     if (tiepdon.getTiepdonBschuyen() == null) {
       tiepdon.setTiepdonBschuyen(new DtDmNhanVien());
     }
     if (tiepdon.getTiepdonLydochvi() == null) {
       tiepdon.setTiepdonLydochvi(new DtDmLyDoCv());
     }
     if (tiepdon.getTiepdonChkhoa() == null) {
       tiepdon.setTiepdonChkhoa(new DmKhoa());
     }
     if (tiepdon.getDiadiemMa() == null) {
       tiepdon.setDiadiemMa(new DmDiaDiem());
     }
     if (tiepdon.getTiepdonBankham() == null) {
       tiepdon.setTiepdonBankham(new DtDmBanKham());
     }
     if (tiepdon.getTiepdonBacsi() == null) {
       tiepdon.setTiepdonBacsi(new DtDmNhanVien());
     }
     if (tiepdon.getTinhbhytMa() == null) {
       tiepdon.setTinhbhytMa(new DmTinh());
     }
     if (tiepdon.getKhoibhytMa() == null) {
       tiepdon.setKhoibhytMa(new DtDmKhoiBhyt());
     }
     if (tiepdon.getKcbbhytMa() == null) {
       tiepdon.setKcbbhytMa(new DmBenhVien());
     }
     if (tiepdon.getDiadiemMa() == null) {
       tiepdon.setDiadiemMa(new DmDiaDiem());
     }
     if (tiepdon.getTiepdonMachdoanb0() == null) {
       tiepdon.setTiepdonMachdoanb0(new DmBenhIcd());
     }
   }

   public static void setInforIfNullForHsThtoan(HsThtoan hsThtoan2)
   {
     log.info("Begin setInforIfNullForHsba():");
     try
     {
       if (hsThtoan2.getHsthtoanKhoa() == null) {
         hsThtoan2.setHsthtoanKhoa(new DmKhoa());
       }
       if (hsThtoan2.getHsthtoanNhanviencn() == null) {
         hsThtoan2.setHsthtoanNhanviencn(new DtDmNhanVien());
       }
       if (hsThtoan2.getHsthtoanThungan() == null) {
         hsThtoan2.setHsthtoanThungan(new DtDmNhanVien());
       }
     }
     catch (Exception ex)
     {
       log.error("Loi trong qua trinh set null object cho HsbaChuyenMon");
     }
     log.info("End setInforIfNullForHsba():");
   }

   public static void setInforIfNullForHsbaChuyenVien(HsbaChuyenVien hsbaChuyenvien2)
   {
     log.info("Begin setInforIfNullForHsba():");
     try
     {
       if (hsbaChuyenvien2.getHsbacvBschuyen() == null) {
         hsbaChuyenvien2.setHsbacvBschuyen(new DtDmNhanVien());
       }
       if (hsbaChuyenvien2.getHsbacvChvienden() == null) {
         hsbaChuyenvien2.setHsbacvChvienden(new DmBenhVien());
       }
       if (hsbaChuyenvien2.getHsbacvLydochuyenv() == null) {
         hsbaChuyenvien2.setHsbacvLydochuyenv(new DtDmLyDoCv());
       }
       if (hsbaChuyenvien2.getNhanvienMa() == null) {
         hsbaChuyenvien2.setNhanvienMa(new DtDmNhanVien());
       }
     }
     catch (Exception ex)
     {
       log.error("Loi trong qua trinh set null object cho HsbaChuyenVien");
     }
     log.info("End setInforIfNullForHsba():");
   }

   public static void setInforIfNullForThamKham(ThamKham thamkham)
   {
     if (thamkham.getThamkhamBankham() == null) {
       thamkham.setThamkhamBankham(new DtDmBanKham());
     }
     if (thamkham.getThamkhamBacsi() == null) {
       thamkham.setThamkhamBacsi(new DtDmNhanVien());
     }
     if (thamkham.getBenhicd10() == null) {
       thamkham.setBenhicd10(new DmBenhIcd());
     }
     if (thamkham.getThamkhamKetqua() == null) {
       thamkham.setThamkhamKetqua(new DmKetQuaDieuTri());
     }
     if (thamkham.getThamkhamDieutri() == null) {
       thamkham.setThamkhamDieutri(new DmDieuTri());
     }
     if (thamkham.getThamkhamChbankham() == null) {
       thamkham.setThamkhamChbankham(new DtDmBanKham());
     }
   }

   public static void setInforIfNullForCTPhieuDuTru(CtPhieuDt ctPhieuDuTru)
   {
     log.debug("begin setInforIfNullForCTPhieuDuTru()" + ctPhieuDuTru);
     if (ctPhieuDuTru.getDmthuocMaso() == null)
     {
       DmThuoc dmThuoc = new DmThuoc();
       DmDonViTinh dtDmDonViTinh = new DmDonViTinh();
       dmThuoc.setDmdonvitinhMaso(dtDmDonViTinh);
       ctPhieuDuTru.setDmthuocMaso(dmThuoc);
     }
     if (ctPhieuDuTru.getDmnguonkinhphiMaso() == null) {
       ctPhieuDuTru.setDmnguonkinhphiMaso(new DmNguonKinhPhi());
     }
     if (ctPhieuDuTru.getDmquocgiaMaso() == null) {
       ctPhieuDuTru.setDmquocgiaMaso(new DmQuocGia());
     }
     if (ctPhieuDuTru.getDmdoituongMaso() == null) {
       ctPhieuDuTru.setDmdoituongMaso(new DmDoiTuong());
     }
     if (ctPhieuDuTru.getDmnhasanxuatMaso() == null) {
       ctPhieuDuTru.setDmnhasanxuatMaso(new DmNhaSanXuat());
     }
     if (ctPhieuDuTru.getDmnctMaso() == null) {
       ctPhieuDuTru.setDmnctMaso(new DmNguonChuongTrinh());
     }
     if (ctPhieuDuTru.getDtdmnhanvienCn() == null) {
       ctPhieuDuTru.setDtdmnhanvienCn(new DtDmNhanVien());
     }
     log.debug("End setInforIfNullForCTPhieuDuTru()");
   }

   public static void setInforIfNullForCTPhieuXuatKho(CtXuatKho ctPhieu)
   {
     log.debug("begin setInforIfNullForCTPhieuDuTru()" + ctPhieu);
     if (ctPhieu.getPhieuxuatkhoMa() == null) {
       ctPhieu.setPhieuxuatkhoMa(new PhieuXuatKho());
     }
     if (ctPhieu.getDmthuocMaso() == null)
     {
       DmThuoc dmThuoc = new DmThuoc();
       DmDonViTinh dtDmDonViTinh = new DmDonViTinh();
       dmThuoc.setDmdonvitinhMaso(dtDmDonViTinh);
       ctPhieu.setDmthuocMaso(dmThuoc);
     }
     if (ctPhieu.getDmnguonkinhphiMaso() == null) {
       ctPhieu.setDmnguonkinhphiMaso(new DmNguonKinhPhi());
     }
     if (ctPhieu.getDmquocgiaMaso() == null) {
       ctPhieu.setDmquocgiaMaso(new DmQuocGia());
     }
     if (ctPhieu.getDmnhasanxuatMaso() == null) {
       ctPhieu.setDmnhasanxuatMaso(new DmNhaSanXuat());
     }
     if (ctPhieu.getDmnctMaso() == null) {
       ctPhieu.setDmnctMaso(new DmNguonChuongTrinh());
     }
     log.debug("End setInforIfNullForCTPhieuDuTru()");
   }

   public static void setInforIfNullForClsMo(ClsMo cls)
   {
     if (cls.getClsmoMahang() == null) {
       cls.setClsmoMahang(new DtDmClsBangGia());
     }
     if (cls.getClsmoKhoa() == null) {
       cls.setClsmoKhoa(new DmKhoa());
     }
     if (cls.getClsmoKhoathuchien() == null) {
       cls.setClsmoKhoathuchien(new DmKhoa());
     }
     if (cls.getClsmoBacsi() == null) {
       cls.setClsmoBacsi(new DtDmNhanVien());
     }
     if (cls.getClsmoLoaicls() == null) {
       cls.setClsmoLoaicls(new DtDmCls());
     }
     if (cls.getClsmoNhanviencn() == null) {
       cls.setClsmoNhanviencn(new DtDmNhanVien());
     }
     if (cls.getClsmoThuchien() == null) {
       cls.setClsmoThuchien(new DtDmNhanVien());
     }
     if (cls.getClsmoThungan() == null) {
       cls.setClsmoThungan(new DtDmNhanVien());
     }
   }

   public static void setInforIfNullForClsKham(ClsKham cls)
   {
     if (cls.getClskhamMahang() == null) {
       cls.setClskhamMahang(new DtDmClsBangGia());
     }
     if (cls.getClskhamKhoa() == null) {
       cls.setClskhamKhoa(new DmKhoa());
     }
     if (cls.getClskhamChedott() == null) {
       cls.setClskhamChedott(new DmDoiTuong());
     }
     if (cls.getClskhamKhoa2() == null) {
       cls.setClskhamKhoa2(new DmKhoa());
     }
     if (cls.getClskhamMabs() == null) {
       cls.setClskhamMabs(new DtDmNhanVien());
     }
     if (cls.getClskhamMaloai() == null) {
       cls.setClskhamMaloai(new DtDmCls());
     }
     if (cls.getClskhamNhanviencn() == null) {
       cls.setClskhamNhanviencn(new DtDmNhanVien());
     }
     if (cls.getClskhamThuchien() == null) {
       cls.setClskhamThuchien(new DtDmNhanVien());
     }
     if (cls.getClskhamThungan() == null) {
       cls.setClskhamThungan(new DtDmNhanVien());
     }
   }

   public static void setInforIfNullForPhieuDuTru(PhieuDuTru phieuDuTru)
   {
     log.debug("Begin setInforIfNullForPhieuDuTru()" + phieuDuTru);
     try
     {
       if (phieuDuTru.getDmkhoaMaso() == null) {
         phieuDuTru.setDmkhoaMaso(new DmKhoa());
       }
       if (phieuDuTru.getDmdoituongMaso() == null) {
         phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
       }
       if (phieuDuTru.getDmloaithuocMaso() == null) {
         phieuDuTru.setDmloaithuocMaso(new DmLoaiThuoc());
       }
       if (phieuDuTru.getDtdmnhanvienBacsiky() == null) {
         phieuDuTru.setDtdmnhanvienBacsiky(new DtDmNhanVien());
       }
       if (phieuDuTru.getDtdmnhanvienCn() == null) {
         phieuDuTru.setDtdmnhanvienCn(new DtDmNhanVien());
       }
       if (phieuDuTru.getDtdmnhanvienLapphieu() == null) {
         phieuDuTru.setDtdmnhanvienLapphieu(new DtDmNhanVien());
       }
       if (phieuDuTru.getDmphanloaithuocMaso() == null) {
         phieuDuTru.setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
       }
       if (phieuDuTru.getPhieudtMakho() == null) {
         phieuDuTru.setPhieudtMakho(new DmKhoa());
       }
     }
     catch (Exception ex)
     {
       log.debug("Loi trong ham setInforIfNullForPhieuDuTru()" + ex);
     }
     log.debug("End setInforIfNullForPhieuDuTru()");
   }

   public static void setInforIfNullForHSBA(Hsba hoSoBenhAn)
   {
     log.debug("setInforIfNullForHSBA() ");
     if (hoSoBenhAn.getHsbaDonvigoi() == null) {
       hoSoBenhAn.setHsbaDonvigoi(new DmBenhVien());
     }
     if (hoSoBenhAn.getHsbaMachdoanbd() == null) {
       hoSoBenhAn.setHsbaMachdoanbd(new DmBenhIcd());
     }
     if (hoSoBenhAn.getHsbaMachdoantuyent() == null) {
       hoSoBenhAn.setHsbaMachdoantuyent(new DmBenhIcd());
     }
     if (hoSoBenhAn.getTainanMa() == null) {
       hoSoBenhAn.setTainanMa(new DmTaiNan());
     }
     if (hoSoBenhAn.getDmptgtnMaso() == null) {
       hoSoBenhAn.setDmptgtnMaso(new DmPhuongThucGayTaiNan());
     }
     if (hoSoBenhAn.getDoituongMa() == null) {
       hoSoBenhAn.setDoituongMa(new DmDoiTuong());
     }
     if (hoSoBenhAn.getHsbaKhoavaov() == null) {
       hoSoBenhAn.setHsbaKhoavaov(new DmKhoa());
     }
     if (hoSoBenhAn.getHsbaDieutri() == null) {
       hoSoBenhAn.setHsbaDieutri(new DmDieuTri());
     }
     if (hoSoBenhAn.getHsbaKhoadangdt() == null) {
       hoSoBenhAn.setHsbaKhoadangdt(new DmKhoa());
     }
     if (hoSoBenhAn.getHsbaKhoadangdtCm() == null) {
       hoSoBenhAn.setHsbaKhoadangdtCm(new DmKhoa());
     }
     if (hoSoBenhAn.getHsbaTuvong() == null) {
       hoSoBenhAn.setHsbaTuvong(new DmBenhIcd());
     }
     if (hoSoBenhAn.getDiadiemMa() == null) {
       hoSoBenhAn.setDiadiemMa(new DmDiaDiem());
     }
     if (hoSoBenhAn.getHsbaKhoarav() == null) {
       hoSoBenhAn.setHsbaKhoarav(new DmKhoa());
     }
     if (hoSoBenhAn.getHsbaMachdravien() == null) {
       hoSoBenhAn.setHsbaMachdravien(new DmBenhIcd());
     }
     if (hoSoBenhAn.getHsbaKetqua() == null) {
       hoSoBenhAn.setHsbaKetqua(new DmKetQuaDieuTri());
     }
   }

   public static void setInforIfNullForTuvongTruoc(TuvongTruoc tuvongTruoc)
   {
     log.debug("setInforIfNullForHSBA() ");
     if (tuvongTruoc.getTvtBenhnhan() == null) {
       tuvongTruoc.setTvtBenhnhan(new BenhNhan());
     }
     if (tuvongTruoc.getTvtThamkham() == null) {
       tuvongTruoc.setTvtThamkham(new ThamKham());
     }
     if (tuvongTruoc.getTvtBankham() == null) {
       tuvongTruoc.setTvtBankham(new DtDmBanKham());
     }
   }

   public static void setInforIfNullForBN(BenhNhan benhNhan)
   {
     log.debug("setInforIfNullForBN() ");
     if (benhNhan.getTinhMa() == null) {
       benhNhan.setTinhMa(new DmTinh());
     }
     if (benhNhan.getHuyenMa() == null) {
       benhNhan.setHuyenMa(new DmHuyen());
     }
     if (benhNhan.getXaMa() == null) {
       benhNhan.setXaMa(new DmXa());
     }
     if (benhNhan.getBenhnhanNghe() == null) {
       benhNhan.setBenhnhanNghe(new DmNgheNghiep());
     }
     if (benhNhan.getDantocMa() == null) {
       benhNhan.setDantocMa(new DmDanToc());
     }
     if (benhNhan.getDmgtMaso() == null) {
       benhNhan.setDmgtMaso(new DmGioiTinh());
     }
   }

   public static void setInforIfNullForHSBABHYT(HsbaBhyt hsbaBHYT)
   {
     if (hsbaBHYT.getHsbabhytTinhbh() == null) {
       hsbaBHYT.setHsbabhytTinhbh(new DmTinh());
     }
     if (hsbaBHYT.getHsbabhytKhoibh() == null) {
       hsbaBHYT.setHsbabhytKhoibh(new DtDmKhoiBhyt());
     }
   }

   public static void setInforIfNullForHSBACM(HsbaChuyenMon hoSoBenhAnCM)
   {
     if (hoSoBenhAnCM.getKhoaMa() == null) {
       hoSoBenhAnCM.setKhoaMa(new DmKhoa());
     }
     if (hoSoBenhAnCM.getHsbacmBenhchinh() == null) {
       hoSoBenhAnCM.setHsbacmBenhchinh(new DmBenhIcd());
     }
     if (hoSoBenhAnCM.getHsbacmTacnhan() == null) {
       hoSoBenhAnCM.setHsbacmTacnhan(new DmBenhIcd());
     }
     if (hoSoBenhAnCM.getNoisinhMa() == null) {
       hoSoBenhAnCM.setNoisinhMa(new DtDmNoiSinh());
     }
     if (hoSoBenhAnCM.getHsbacmBenhphu() == null) {
       hoSoBenhAnCM.setHsbacmBenhphu(new DmBenhIcd());
     }
     if (hoSoBenhAnCM.getKetquaMa() == null) {
       hoSoBenhAnCM.setKetquaMa(new DmKetQuaDieuTri());
     }
     if (hoSoBenhAnCM.getHsbacmBacsi() == null) {
       hoSoBenhAnCM.setHsbacmBacsi(new DtDmNhanVien());
     }
     if (hoSoBenhAnCM.getHsbacmChuyenkhoa() == null) {
       hoSoBenhAnCM.setHsbacmChuyenkhoa(new DmKhoa());
     }
   }

   public static void setInforIfNullForHSBANop(HsbaNop hsbaNop) {}

   public static void setInforIfNullForHSBAMo(HsbaMo hsbamo)
   {
     if (hsbamo.getVocamMaso() == null) {
       hsbamo.setVocamMaso(new DtDmVoCam());
     }
     if (hsbamo.getHsbamoMamo() == null) {
       hsbamo.setHsbamoMamo(new DtDmPhauThuat());
     }
     if (hsbamo.getHsbamoBacsi() == null) {
       hsbamo.setHsbamoBacsi(new DtDmNhanVien());
     }
     if (hsbamo.getHsbamoCcphien() == null) {
       hsbamo.setHsbamoCcphien(new DtDmCapCuuPhien());
     }
   }

   public static void setInforIfNullForPhieuXuatKho(PhieuXuatKho pxk)
   {
     if (pxk.getPhieudtMa() == null) {
       pxk.setPhieudtMa(new PhieuDuTru());
     }
     if (pxk.getDtdmnhanvienCn() == null) {
       pxk.setDtdmnhanvienCn(new DtDmNhanVien());
     }
     if (pxk.getDmloaithuocMaso() == null) {
       pxk.setDmloaithuocMaso(new DmLoaiThuoc());
     }
     if (pxk.getDmkhoaNhan() == null) {
       pxk.setDmkhoaNhan(new DmKhoa());
     }
     if (pxk.getDmkhoaXuat() == null) {
       pxk.setDmkhoaXuat(new DmKhoa());
     }
     if (pxk.getDtdmnhanvienNhan() == null) {
       pxk.setDtdmnhanvienNhan(new DtDmNhanVien());
     }
     if (pxk.getDtdmnhanvienBacsi() == null) {
       pxk.setDtdmnhanvienBacsi(new DtDmNhanVien());
     }
     if (pxk.getDtdmnhanvienPhat() == null) {
       pxk.setDtdmnhanvienPhat(new DtDmNhanVien());
     }
     if (pxk.getDmdoituongMaso() == null) {
       pxk.setDmdoituongMaso(new DmDoiTuong());
     }
   }

   public static void setInforIfNullForPhieuTraKho(PhieuTraKho ptk)
   {
     log.info("***** setInforIfNullForPhieuTraKho *****");
     if (ptk.getPhieudtMa() == null) {
       ptk.setPhieudtMa(new PhieuDuTru());
     }
     if (ptk.getDtdmnhanvienCn() == null) {
       ptk.setDtdmnhanvienCn(new DtDmNhanVien());
     }
     if (ptk.getDmkhoaNhan() == null) {
       ptk.setDmkhoaNhan(new DmKhoa());
     }
     if (ptk.getDmkhoaTra() == null) {
       ptk.setDmkhoaTra(new DmKhoa());
     }
     if (ptk.getDtdmnhanvienLapphieu() == null) {
       ptk.setDtdmnhanvienLapphieu(new DtDmNhanVien());
     }
     if (ptk.getDtdmnhanvienBacsi() == null) {
       ptk.setDtdmnhanvienBacsi(new DtDmNhanVien());
     }
     if (ptk.getDtdmnhanvienPhat() == null) {
       ptk.setDtdmnhanvienPhat(new DtDmNhanVien());
     }
     if (ptk.getDmdoituongMaso() == null) {
       ptk.setDmdoituongMaso(new DmDoiTuong());
     }
     log.info("***** setInforIfNullForPhieuTraKho *****");
   }

   public static void setInforIfNullForCTPhieuTraKho(CtTraKho ctPhieu)
   {
     if (ctPhieu.getPhieutrakhoMa() == null) {
       ctPhieu.setPhieutrakhoMa(new PhieuTraKho());
     }
     if (ctPhieu.getDmthuocMaso() == null)
     {
       DmThuoc dmThuoc = new DmThuoc();
       DmDonViTinh dtDmDonViTinh = new DmDonViTinh();
       dmThuoc.setDmdonvitinhMaso(dtDmDonViTinh);
       ctPhieu.setDmthuocMaso(dmThuoc);
     }
     if (ctPhieu.getDmnguonkinhphiMaso() == null) {
       ctPhieu.setDmnguonkinhphiMaso(new DmNguonKinhPhi());
     }
     if (ctPhieu.getDmquocgiaMaso() == null) {
       ctPhieu.setDmquocgiaMaso(new DmQuocGia());
     }
     if (ctPhieu.getDmnhasanxuatMaso() == null) {
       ctPhieu.setDmnhasanxuatMaso(new DmNhaSanXuat());
     }
     if (ctPhieu.getDmnctMaso() == null) {
       ctPhieu.setDmnctMaso(new DmNguonChuongTrinh());
     }
   }

   public static void setInforIfNullForPhieuGiaoBan(PhieuGiaoBan object)
   {
     if (object.getKhoaMaso() == null) {
       object.setKhoaMaso(new DmKhoa());
     }
     if (object.getPgbChutri() == null) {
       object.setPgbChutri(new DtDmNhanVien());
     }
     if (object.getPgbNguoilap() == null) {
       object.setPgbNguoilap(new DtDmNhanVien());
     }
     if (object.getPgbThuki() == null) {
       object.setPgbThuki(new DtDmNhanVien());
     }
   }

   public static void setInforIfNullForPhieuGiaoBanTPTD(PhieuGiaoBanThanhPhanThamDu object)
   {
     if (object.getDtdmnhanvienMaso() == null) {
       object.setDtdmnhanvienMaso(new DtDmNhanVien());
     }
     if (object.getPgbMa() == null) {
       object.setPgbMa(new PhieuGiaoBan());
     }
   }

   public static void setInforIfNullForCtPhieuGiaoBan(CtPhieuGiaoBan object)
   {
     if (object.getCtpgbPgbMa() == null) {
       object.setCtpgbPgbMa(new PhieuGiaoBan());
     }
     if (object.getPbaMaso() == null) {
       object.setPbaMaso(new PhieuBaoAn());
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.setinfor.SetInforUtil

 * JD-Core Version:    0.7.0.1

 */