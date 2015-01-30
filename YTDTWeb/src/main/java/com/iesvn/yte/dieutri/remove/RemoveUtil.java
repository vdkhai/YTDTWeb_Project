 package com.iesvn.yte.dieutri.remove;

 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.CtPhieuDt;
 import com.iesvn.yte.dieutri.entity.CtTraKho;
 import com.iesvn.yte.dieutri.entity.CtXuatKho;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCapCuuPhien;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
 import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.DtDmNoiSinh;
 import com.iesvn.yte.dieutri.entity.DtDmPhauThuat;
 import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmVoCam;
 import com.iesvn.yte.dieutri.entity.GiayCvNbBhyt;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuat;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietMat;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathai;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoai;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoaitruYhct;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNhikhoa;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNoi;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNoitruYhct;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietPhukhoa;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietRhm;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietSankhoa;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietSosinh;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietTmh;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChan;
 import com.iesvn.yte.dieutri.entity.HsbaMo;
 import com.iesvn.yte.dieutri.entity.HsbaNop;
 import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
 import com.iesvn.yte.dieutri.entity.PhieuDuTru;
 import com.iesvn.yte.dieutri.entity.PhieuTraKho;
 import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.entity.TuvongTruoc;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhTruyenNhiem;
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
 import com.iesvn.yte.entity.DmTang;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import org.apache.log4j.Logger;

 public class RemoveUtil
 {
   private static Logger log = Logger.getLogger(RemoveUtil.class);

   public static void removeIfNullForThuocPhongKham(ThuocPhongKham thuocPhongKham)
   {
     System.out.println("-----removeIfNullForThuocPhongKham");
     if ("".equals(Utils.reFactorString(thuocPhongKham.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso().getDmdonvitinhMa()))) {
       thuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(null);
     }
     if (thuocPhongKham.getThuocphongkhamMathuoc(true).getDmcachdungMaso() != null)
     {
       if ("".equals(Utils.reFactorString(thuocPhongKham.getThuocphongkhamMathuoc(true).getDmcachdungMaso(true).getDmcachdungthuocMa()))) {
         thuocPhongKham.getThuocphongkhamMathuoc().setDmcachdungMaso(null);
       }
     }
     else {
       thuocPhongKham.getThuocphongkhamMathuoc(true).setDmcachdungMaso(null);
     }
     if ("".equals(Utils.reFactorString(thuocPhongKham.getThuocphongkhamMathuoc(true).getDmthuocMa()))) {
       thuocPhongKham.setThuocphongkhamMathuoc(null);
     }
     log.info("thuocPhongKham.getThuocphongkhamQuocgia(true).getDmquocgiaMa():" + thuocPhongKham.getThuocphongkhamQuocgia(true).getDmquocgiaMa());
     if ("".equals(Utils.reFactorString(thuocPhongKham.getThuocphongkhamQuocgia(true).getDmquocgiaMa()))) {
       thuocPhongKham.setThuocphongkhamQuocgia(null);
     }
     if ("".equals(Utils.reFactorString(thuocPhongKham.getThuocphongkhamHangsx(true).getDmnhasanxuatMa()))) {
       thuocPhongKham.setThuocphongkhamHangsx(null);
     }
     if ("".equals(Utils.reFactorString(thuocPhongKham.getThuocphongkhamHangsx(true).getDmnhasanxuatMaso()))) {
       thuocPhongKham.setThuocphongkhamHangsx(null);
     }
     if (thuocPhongKham.getThuocphongkhamMadung() != null) {
       if ("".equals(Utils.reFactorString(thuocPhongKham.getThuocphongkhamMadung(true).getDmcachdungthuocMa()))) {
         thuocPhongKham.setThuocphongkhamMadung(null);
       }
     }
     if (thuocPhongKham.getDmnctMaso() != null) {
       if ("".equals(Utils.reFactorString(thuocPhongKham.getDmnctMaso(true).getDmnctMa()))) {
         thuocPhongKham.setDmnctMaso(null);
       }
     }
     if (thuocPhongKham.getDmnguonkinhphiMaso() != null) {
       if ("".equals(Utils.reFactorString(thuocPhongKham.getDmnguonkinhphiMaso(true).getDmnguonkinhphiMa()))) {
         thuocPhongKham.setDmnguonkinhphiMaso(null);
       }
     }
   }

   public static void removeIfNullForPhieuXuatKho(PhieuXuatKho phieuXuatKho2)
   {
     if ("".equals(Utils.reFactorString(phieuXuatKho2.getPhieudtMa(true).getPhieudtMa())))
     {
       phieuXuatKho2.setPhieudtMa(null);
       log.info("Khoa nhan null");
     }
     if ("".equals(Utils.reFactorString(phieuXuatKho2.getDmkhoaNhan(true).getDmkhoaMa())))
     {
       phieuXuatKho2.setDmkhoaNhan(null);
       log.info("Khoa nhan null");
     }
     if ("".equals(Utils.reFactorString(phieuXuatKho2.getDmkhoaXuat(true).getDmkhoaMa())))
     {
       phieuXuatKho2.setDmkhoaXuat(null);
       log.info("Khoa xuat null");
     }
     if ("".equals(Utils.reFactorString(phieuXuatKho2.getDmdoituongMaso(true).getDmdoituongMa())))
     {
       phieuXuatKho2.setDmdoituongMaso(null);
       log.info("Doi tuong null");
     }
     if ("".equals(Utils.reFactorString(phieuXuatKho2.getDmloaithuocMaso(true).getDmloaithuocMa())))
     {
       phieuXuatKho2.setDmloaithuocMaso(null);
       log.info("Nguon null");
     }
     if ("".equals(Utils.reFactorString(phieuXuatKho2.getDtdmnhanvienBacsi(true).getDtdmnhanvienMa())))
     {
       phieuXuatKho2.setDtdmnhanvienBacsi(null);
       log.info("Bac si null");
     }
     if ("".equals(Utils.reFactorString(phieuXuatKho2.getDtdmnhanvienCn(true).getDtdmnhanvienMa())))
     {
       phieuXuatKho2.setDtdmnhanvienCn(null);
       log.info("Nhan vien cap nhat null");
     }
     if ("".equals(Utils.reFactorString(phieuXuatKho2.getDtdmnhanvienNhan(true).getDtdmnhanvienMa())))
     {
       phieuXuatKho2.setDtdmnhanvienNhan(null);
       log.info("Nhan vien nhan null");
     }
     if ("".equals(Utils.reFactorString(phieuXuatKho2.getDtdmnhanvienPhat(true).getDtdmnhanvienMa())))
     {
       phieuXuatKho2.setDtdmnhanvienPhat(null);
       log.info("nhan vien phat  null");
     }
     log.info("End removeIfNullForPhieuXuatKho");
   }

   public static void setAllNullForGiayChuyenVien(HsbaChuyenVien chuyenvien)
   {
     if ("".equals(Utils.reFactorString(chuyenvien.getHsbacvLydochuyenv(true).getDtdmlydocvMa()))) {
       chuyenvien.setHsbacvLydochuyenv(null);
     }
     if ("".equals(Utils.reFactorString(chuyenvien.getHsbacvBschuyen(true).getDtdmnhanvienMa()))) {
       chuyenvien.setHsbacvBschuyen(null);
     }
     if ("".equals(Utils.reFactorString(chuyenvien.getHsbacvChvienden(true).getDmbenhvienMa()))) {
       chuyenvien.setHsbacvChvienden(null);
     }
     if ("".equals(Utils.reFactorString(chuyenvien.getHsbacvBsdieutri(true).getDtdmnhanvienMa()))) {
       chuyenvien.setHsbacvBsdieutri(null);
     }
   }

   public static void setAllNullForClsKham(ClsKham cls)
   {
     log.info("**********Begin setAllNullForClsKham()***********");
     if ("".equals(Utils.reFactorString(cls.getClskhamMahang(true).getDtdmclsbgMa()))) {
       cls.setClskhamMahang(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamKhoa(true).getDmkhoaMa()))) {
       cls.setClskhamKhoa(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamChedott(true).getDmdoituongMa()))) {
       cls.setClskhamChedott(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamKhoa2(true).getDmkhoaMa()))) {
       cls.setClskhamKhoa2(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamMabs(true).getDtdmnhanvienMa()))) {
       cls.setClskhamMabs(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamMaloai(true).getDtdmclsMa()))) {
       cls.setClskhamMaloai(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamNhanviencn(true).getDtdmnhanvienMa()))) {
       cls.setClskhamNhanviencn(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamThuchien(true).getDtdmnhanvienMa()))) {
       cls.setClskhamThuchien(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamThungan(true).getDtdmnhanvienMa()))) {
       cls.setClskhamThungan(null);
     }
     log.info("**********End setAllNullForClsKham()***********");
   }

   public static void setAllNullForClsMo(ClsMo cls)
   {
     log.info("**********Begin setAllNullForClsKham()***********");
     if ("".equals(Utils.reFactorString(cls.getClsmoMahang(true).getDtdmclsbgMa()))) {
       cls.setClsmoMahang(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClsmoKhoa(true).getDmkhoaMa()))) {
       cls.setClsmoKhoa(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClsmoKhoathuchien(true).getDmkhoaMa()))) {
       cls.setClsmoKhoathuchien(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClsmoBacsi(true).getDtdmnhanvienMa()))) {
       cls.setClsmoBacsi(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClsmoLoaicls(true).getDtdmclsMa()))) {
       cls.setClsmoLoaicls(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClsmoNhanviencn(true).getDtdmnhanvienMa()))) {
       cls.setClsmoNhanviencn(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClsmoThuchien(true).getDtdmnhanvienMa()))) {
       cls.setClsmoThuchien(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClsmoThungan(true).getDtdmnhanvienMa()))) {
       cls.setClsmoThungan(null);
     }
     log.info("**********End setAllNullForClsKham()***********");
   }

   public static void removeAllNullFromThamKham(ThamKham thamkham)
   {
     if ("".equals(Utils.reFactorString(thamkham.getThamkhamBankham(true).getDtdmbankhamMaso()))) {
       thamkham.setThamkhamBankham(null);
     }
     if ("".equals(Utils.reFactorString(thamkham.getThamkhamBacsi(true).getDtdmnhanvienMaso()))) {
       thamkham.setThamkhamBacsi(null);
     }
     if ("".equals(Utils.reFactorString(thamkham.getBenhicd10(true).getDmbenhicdMaso()))) {
       thamkham.setBenhicd10(null);
     }
     if ("".equals(Utils.reFactorString(thamkham.getThamkhamKetqua(true).getDmkqdtMaso()))) {
       thamkham.setThamkhamKetqua(null);
     }
     if ("".equals(Utils.reFactorString(thamkham.getThamkhamDieutri(true).getDmdieutriMaso()))) {
       thamkham.setThamkhamDieutri(null);
     }
     if ("".equals(Utils.reFactorString(thamkham.getThamkhamChbankham(true).getDtdmbankhamMaso()))) {
       thamkham.setThamkhamChbankham(null);
     }
     if ("".equals(Utils.reFactorString(thamkham.getThamkhamNhanviencn(true).getDtdmnhanvienMaso()))) {
       thamkham.setThamkhamNhanviencn(null);
     }
     if ((thamkham.getBenhicd10phu1() != null) && (("".equals(Utils.reFactorString(thamkham.getBenhicd10phu1().getDmbenhicdMa()))) || (thamkham.getBenhicd10phu1().getDmbenhicdMaso() == null))) {
       thamkham.setBenhicd10phu1(null);
     }
     if ((thamkham.getBenhicd10phu2() != null) && (("".equals(Utils.reFactorString(thamkham.getBenhicd10phu2().getDmbenhicdMa()))) || (thamkham.getBenhicd10phu2().getDmbenhicdMaso() == null))) {
       thamkham.setBenhicd10phu2(null);
     }
     if ((thamkham.getBenhicd10phu3() != null) && (("".equals(Utils.reFactorString(thamkham.getBenhicd10phu3().getDmbenhicdMa()))) || (thamkham.getBenhicd10phu3().getDmbenhicdMaso() == null))) {
       thamkham.setBenhicd10phu3(null);
     }
     if ((thamkham.getBenhicd10phu4() != null) && (("".equals(Utils.reFactorString(thamkham.getBenhicd10phu4().getDmbenhicdMa()))) || (thamkham.getBenhicd10phu4().getDmbenhicdMaso() == null))) {
       thamkham.setBenhicd10phu4(null);
     }
     if ((thamkham.getBenhicd10phu5() != null) && (("".equals(Utils.reFactorString(thamkham.getBenhicd10phu5().getDmbenhicdMa()))) || (thamkham.getBenhicd10phu5().getDmbenhicdMaso() == null))) {
       thamkham.setBenhicd10phu5(null);
     }
   }

   public static void removeIfNullForHsThtoan(HsThtoan hsThtoan2)
   {
     log.info("Begin removeIfNullForHsThtoan: " + hsThtoan2);
     try
     {
       if ("".equals(Utils.reFactorString(hsThtoan2.getHsthtoanKhoa(true).getDmkhoaMa())))
       {
         hsThtoan2.setHsthtoanKhoa(null);
         log.info("Khoa  null");
       }
       else
       {
         log.info("khoa khong null");
       }
       if ("".equals(Utils.reFactorString(hsThtoan2.getHsthtoanNhanviencn(true).getDtdmnhanvienMa())))
       {
         hsThtoan2.setHsthtoanNhanviencn(null);
         log.info("Nhan vien cn null");
       }
       else
       {
         log.info("Nhan vien cn khong null");
       }
       if ("".equals(Utils.reFactorString(hsThtoan2.getHsthtoanThungan(true).getDtdmnhanvienMa())))
       {
         hsThtoan2.setHsthtoanThungan(null);
         log.info("thu ngan null");
       }
       else
       {
         log.info("thu ngan khong null");
       }
     }
     catch (Exception e)
     {
       log.error("Loi trong removeIfNullForHsThtoan: " + e);
     }
     log.info("End removeIfNullForHsThtoan");
   }

   public static void removeAllNullFromTiepDon(TiepDon tiepdon)
   {
     if ("".equals(Utils.reFactorString(tiepdon.getTainanMa(true).getDmtainanMaso()))) {
       tiepdon.setTainanMa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getDmptgtnMaso(true).getDmptgtnMaso()))) {
       tiepdon.setDmptgtnMaso(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonDonvigoi(true).getDmbenhvienMaso()))) {
       tiepdon.setTiepdonDonvigoi(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonMachdoanb0(true).getDmbenhicdMaso()))) {
       tiepdon.setTiepdonMachdoanb0(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonTuvong(true).getDmbenhicdMaso()))) {
       tiepdon.setTiepdonTuvong(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonChvien(true).getDmbenhvienMaso()))) {
       tiepdon.setTiepdonChvien(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonBschuyen(true).getDtdmnhanvienMaso()))) {
       tiepdon.setTiepdonBschuyen(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonLydochvi(true).getDtdmlydocvMaso()))) {
       tiepdon.setTiepdonLydochvi(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getDiadiemMa(true).getDmdiadiemMaso()))) {
       tiepdon.setDiadiemMa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonBankham(true).getDtdmbankhamMaso()))) {
       tiepdon.setTiepdonBankham(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonChkhoa(true).getDmkhoaMaso()))) {
       tiepdon.setTiepdonChkhoa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonBacsi(true).getDtdmnhanvienMaso()))) {
       tiepdon.setTiepdonBacsi(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTinhbhytMa(true).getDmtinhMaso()))) {
       tiepdon.setTinhbhytMa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMaso()))) {
       tiepdon.setKhoibhytMa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getKcbbhytMa(true).getDmbenhvienMaso()))) {
       tiepdon.setKcbbhytMa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getDieutriMa(true).getDmdieutriMaso()))) {
       tiepdon.setDieutriMa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getKetquaMa(true).getDmkqdtMaso()))) {
       tiepdon.setKetquaMa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getKcbbhytMa(true).getDmbenhvienMaso()))) {
       tiepdon.setKcbbhytMa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonDoituongbhyt(true).getDtdmphloaibhytMaso()))) {
       tiepdon.setTiepdonDoituongbhyt(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getKcbbhytMa(true).getDmbenhvienMaso()))) {
       tiepdon.setKcbbhytMa(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonMachdoanbd(true).getDmbenhicdMaso()))) {
       tiepdon.setTiepdonMachdoanbd(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonMadich(true).getDmbtnMaso()))) {
       tiepdon.setTiepdonMadich(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonNguoiduyet(true).getDtdmnhanvienMaso()))) {
       tiepdon.setTiepdonNguoiduyet(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonNhanviencn(true).getDtdmnhanvienMaso()))) {
       tiepdon.setTiepdonNhanviencn(null);
     }
     if ("".equals(Utils.reFactorString(tiepdon.getTiepdonThungan(true).getDtdmnhanvienMaso()))) {
       tiepdon.setTiepdonThungan(null);
     }
   }

   public static void removeAllNullFromTuvongTruoc(TuvongTruoc tuvongTruoc)
   {
     if ("".equals(Utils.reFactorString(tuvongTruoc.getTvtBankham(true).getDtdmbankhamMa()))) {
       tuvongTruoc.setTvtBankham(null);
     }
     if ("".equals(Utils.reFactorString(tuvongTruoc.getTvtBenhnhan(true).getBenhnhanMa()))) {
       tuvongTruoc.setTvtBenhnhan(null);
     }
   }

   public static void removeIfNullForCTPhieuDuTru(CtPhieuDt ctPhieuDuTru)
   {
     log.debug("Begin removeIfNullForCTPhieuDuTru" + ctPhieuDuTru);
     if ("".equals(Utils.reFactorString(ctPhieuDuTru.getDmnguonkinhphiMaso(true).getDmnguonkinhphiMa())))
     {
       ctPhieuDuTru.setDmnguonkinhphiMaso(null);
       log.debug("nguon kinh phi null");
     }
     else
     {
       log.debug("nguon kinh phi khong null");
     }
     if ("".equals(Utils.reFactorString(ctPhieuDuTru.getDmquocgiaMaso(true).getDmquocgiaMa())))
     {
       ctPhieuDuTru.setDmquocgiaMaso(null);
       log.debug("Quoc gia null");
     }
     else
     {
       log.debug("quoc gia khong null");
     }
     if ("".equals(Utils.reFactorString(ctPhieuDuTru.getDmthuocMaso(true).getDmthuocMa())))
     {
       ctPhieuDuTru.setDmthuocMaso(null);
       log.debug("Danh muc thuoc null");
     }
     else
     {
       log.debug("danh muc thuoc khong null");
     }
     if ("".equals(Utils.reFactorString(ctPhieuDuTru.getDmdoituongMaso(true).getDmdoituongMa())))
     {
       ctPhieuDuTru.setDmdoituongMaso(null);
       log.debug("Doi tuong null");
     }
     else
     {
       log.debug("Doi tuong khong null");
     }
     System.out.println("ctPhieuDuTru.getDtdmhangsxMa()--1:" + ctPhieuDuTru.getDmnhasanxuatMaso());
     if ("".equals(Utils.reFactorString(ctPhieuDuTru.getDmnhasanxuatMaso(true).getDmnhasanxuatMa())))
     {
       ctPhieuDuTru.setDmnhasanxuatMaso(null);
       log.debug("Hang san xuat null");
     }
     else
     {
       log.debug("Hang san xuat khong null");
     }
     System.out.println("ctPhieuDuTru.getDtdmhangsxMa()--2:" + ctPhieuDuTru.getDmnhasanxuatMaso());
     if ("".equals(Utils.reFactorString(ctPhieuDuTru.getDmnctMaso(true).getDmnctMa())))
     {
       ctPhieuDuTru.setDmnctMaso(null);
       log.debug("dm nguon null");
     }
     else
     {
       log.debug("dm nguon khong null");
     }
     if ("".equals(Utils.reFactorString(ctPhieuDuTru.getDtdmnhanvienCn(true).getDtdmnhanvienMa())))
     {
       ctPhieuDuTru.setDtdmnhanvienCn(null);
       log.debug("Nhan vien cn null");
     }
     else
     {
       log.debug("nhan vien khong null");
     }
     if ("".equals(Utils.reFactorString(ctPhieuDuTru.getPhieudtMa(true).getPhieudtMa())))
     {
       ctPhieuDuTru.setPhieudtMa(null);
       log.debug("Phieu du tru cn null");
     }
     else
     {
       log.debug("phieu du tru khong null");
     }
     log.debug("End removeIfNullForCTPhieuDuTru");
   }

   public static void removeIfNullForCTPhieuXuatKho(CtXuatKho ctPhieu)
   {
     log.debug("Begin removeIfNullForCTPhieuDuTru" + ctPhieu);
     if ("".equals(Utils.reFactorString(ctPhieu.getPhieuxuatkhoMa(true).getPhieuxuatkhoMa())))
     {
       ctPhieu.setPhieuxuatkhoMa(null);
       log.debug("Phieu du tru cn null");
     }
     else
     {
       log.debug("phieu du tru khong null");
     }
     if ("".equals(Utils.reFactorString(ctPhieu.getDmnguonkinhphiMaso(true).getDmnguonkinhphiMa())))
     {
       ctPhieu.setDmnguonkinhphiMaso(null);
       log.debug("nguon kinh phi null");
     }
     else
     {
       log.debug("nguon kinh phi khong null");
     }
     if ("".equals(Utils.reFactorString(ctPhieu.getDmquocgiaMaso(true).getDmquocgiaMa())))
     {
       ctPhieu.setDmquocgiaMaso(null);
       log.debug("Quoc gia null");
     }
     else
     {
       log.debug("quoc gia khong null");
     }
     if ("".equals(Utils.reFactorString(ctPhieu.getDmthuocMaso(true).getDmthuocMa())))
     {
       ctPhieu.setDmthuocMaso(null);
       log.debug("Danh muc thuoc null");
     }
     else
     {
       log.debug("danh muc thuoc khong null");
     }
     System.out.println("ctPhieuDuTru.getDtdmhangsxMa()--1:" + ctPhieu.getDmnhasanxuatMaso());
     if ("".equals(Utils.reFactorString(ctPhieu.getDmnhasanxuatMaso(true).getDmnhasanxuatMa())))
     {
       ctPhieu.setDmnhasanxuatMaso(null);
       log.debug("Hang san xuat null");
     }
     else
     {
       log.debug("Hang san xuat khong null");
     }
     System.out.println("ctPhieuDuTru.getDtdmhangsxMa()--2:" + ctPhieu.getDmnhasanxuatMaso());
     if ("".equals(Utils.reFactorString(ctPhieu.getDmnctMaso(true).getDmnctMa())))
     {
       ctPhieu.setDmnctMaso(null);
       log.debug("dm nguon null");
     }
     else
     {
       log.debug("dm nguon khong null");
     }
     log.debug("End removeIfNullForCTPhieuDuTru");
   }

   public static void removeIfNullForCTPhieuTraKho(CtTraKho ctPhieu)
   {
     try
     {
       log.debug("Begin removeIfNullForCTPhieuTraKho" + ctPhieu);
       if ("".equals(Utils.reFactorString(ctPhieu.getPhieutrakhoMa(true).getPhieutrakhoMa())))
       {
         ctPhieu.setPhieutrakhoMa(null);
         log.debug("Phieu du tru cn null");
       }
       else
       {
         log.debug("phieu du tru khong null");
       }
       if ("".equals(Utils.reFactorString(ctPhieu.getDmnguonkinhphiMaso(true).getDmnguonkinhphiMa())))
       {
         ctPhieu.setDmnguonkinhphiMaso(null);
         log.debug("nguon kinh phi null");
       }
       else
       {
         log.debug("nguon kinh phi khong null");
       }
       if ("".equals(Utils.reFactorString(ctPhieu.getDmquocgiaMaso(true).getDmquocgiaMa())))
       {
         ctPhieu.setDmquocgiaMaso(null);
         log.debug("Quoc gia null");
       }
       else
       {
         log.debug("quoc gia khong null");
       }
       if ("".equals(Utils.reFactorString(ctPhieu.getDmthuocMaso(true).getDmthuocMa())))
       {
         ctPhieu.setDmthuocMaso(null);
         log.debug("Danh muc thuoc null");
       }
       else
       {
         log.debug("danh muc thuoc khong null");
       }
       System.out.println("ctPhieuDuTru.getDtdmhangsxMa()--1:" + ctPhieu.getDmnhasanxuatMaso());
       if ("".equals(Utils.reFactorString(ctPhieu.getDmnhasanxuatMaso(true).getDmnhasanxuatMa())))
       {
         ctPhieu.setDmnhasanxuatMaso(null);
         log.debug("Hang san xuat null");
       }
       else
       {
         log.debug("Hang san xuat khong null");
       }
       System.out.println("ctPhieuDuTru.getDtdmhangsxMa()--2:" + ctPhieu.getDmnhasanxuatMaso());
       if ("".equals(Utils.reFactorString(ctPhieu.getDmnctMaso(true).getDmnctMa())))
       {
         ctPhieu.setDmnctMaso(null);
         log.debug("dm nguon null");
       }
       else
       {
         log.debug("dm nguon khong null");
       }
       log.debug("End removeIfNullForCTPhieuTraKho");
     }
     catch (Exception ex)
     {
       log.info("****** Error; " + ex);
     }
   }

   public static void removeIfNullForPhieuDuTru(PhieuDuTru phieuDuTru)
   {
     log.info("Begin removeIfNullForPhieuDuTru" + phieuDuTru);
     if ("".equals(Utils.reFactorString(phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa())))
     {
       phieuDuTru.setDmkhoaMaso(null);
       log.info("Khoa nhan null");
     }
     if ("".equals(Utils.reFactorString(phieuDuTru.getDmdoituongMaso(true).getDmdoituongMa())))
     {
       phieuDuTru.setDmdoituongMaso(null);
       log.info("Doi tuong null");
     }
     if ("".equals(Utils.reFactorString(phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa())))
     {
       phieuDuTru.setDmloaithuocMaso(null);
       log.info("loai null");
     }
     if ("".equals(Utils.reFactorString(phieuDuTru.getDtdmnhanvienBacsiky(true).getDtdmnhanvienMa())))
     {
       phieuDuTru.setDtdmnhanvienBacsiky(null);
       log.info("Bac si ky null");
     }
     if ("".equals(Utils.reFactorString(phieuDuTru.getDtdmnhanvienCn(true).getDtdmnhanvienMa())))
     {
       phieuDuTru.setDtdmnhanvienCn(null);
       log.info("Nhan vien null");
     }
     if ("".equals(Utils.reFactorString(phieuDuTru.getDtdmnhanvienLapphieu(true).getDtdmnhanvienMa())))
     {
       phieuDuTru.setDtdmnhanvienLapphieu(null);
       log.info("Nhan vien lap phieu null");
     }
     if ("".equals(Utils.reFactorString(phieuDuTru.getPhieudtMakho(true).getDmkhoaMa())))
     {
       phieuDuTru.setPhieudtMakho(null);
       log.info("Ma kho null");
     }
     if ("".equals(Utils.reFactorString(phieuDuTru.getDmphanloaithuocMaso(true).getDmphanloaithuocMa())))
     {
       phieuDuTru.setDmphanloaithuocMaso(null);
       log.info("phan loai null");
     }
     if ("".equals(Utils.reFactorString(phieuDuTru.getPhieudtMakho(true).getDmkhoaMa())))
     {
       phieuDuTru.setPhieudtMakho(null);
       log.info("khoa null");
     }
     if ("".equals(Utils.reFactorString(phieuDuTru.getDmtangMaso(true).getDmtangMa())))
     {
       phieuDuTru.setDmtangMaso(null);
       log.info("Buong null");
     }
     log.info("End removeIfNullForPhieuDuTru");
   }

   public static void removeAllNullFromHSBA(Hsba hoSoBenhAn)
   {
     log.debug("removeAllNullFromHSBA()");
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaDonvigoi(true).getDmbenhvienMaso()))) {
       hoSoBenhAn.setHsbaDonvigoi(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaMachdoanbd(true).getDmbenhicdMaso()))) {
       hoSoBenhAn.setHsbaMachdoanbd(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaMachdoantuyent(true).getDmbenhicdMaso()))) {
       hoSoBenhAn.setHsbaMachdoantuyent(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getTainanMa(true).getDmtainanMaso()))) {
       hoSoBenhAn.setTainanMa(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getDmptgtnMaso(true).getDmptgtnMaso()))) {
       hoSoBenhAn.setDmptgtnMaso(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getDoituongMa(true).getDmdoituongMaso()))) {
       hoSoBenhAn.setDoituongMa(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaKhoavaov(true).getDmkhoaMaso()))) {
       hoSoBenhAn.setHsbaKhoavaov(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaDieutri(true).getDmdieutriMaso()))) {
       hoSoBenhAn.setHsbaDieutri(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaKhoadangdt(true).getDmkhoaMaso()))) {
       hoSoBenhAn.setHsbaKhoadangdt(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaKhoadangdtCm(true).getDmkhoaMaso()))) {
       hoSoBenhAn.setHsbaKhoadangdtCm(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaTuvong(true).getDmbenhicdMaso()))) {
       hoSoBenhAn.setHsbaTuvong(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getDiadiemMa(true).getDmdiadiemMaso()))) {
       hoSoBenhAn.setDiadiemMa(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaKhoarav(true).getDmkhoaMaso()))) {
       hoSoBenhAn.setHsbaKhoarav(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaMachdravien(true).getDmbenhicdMaso()))) {
       hoSoBenhAn.setHsbaMachdravien(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAn.getHsbaKetqua(true).getDmkqdtMaso()))) {
       hoSoBenhAn.setHsbaKetqua(null);
     }
   }

   public static void removeAllNullFromBN(BenhNhan benhNhan)
   {
     log.debug("removeAllNullFromBN");
     if ("".equals(Utils.reFactorString(benhNhan.getTinhMa(true).getDmtinhMaso()))) {
       benhNhan.setTinhMa(null);
     }
     if ("".equals(Utils.reFactorString(benhNhan.getHuyenMa(true).getDmhuyenMaso()))) {
       benhNhan.setHuyenMa(null);
     }
     if ("".equals(Utils.reFactorString(benhNhan.getXaMa(true).getDmxaMaso()))) {
       benhNhan.setXaMa(null);
     }
     if ("".equals(Utils.reFactorString(benhNhan.getBenhnhanNghe(true).getDmnghenghiepMaso()))) {
       benhNhan.setBenhnhanNghe(null);
     }
     if ("".equals(Utils.reFactorString(benhNhan.getDantocMa(true).getDmdantocMaso()))) {
       benhNhan.setDantocMa(null);
     }
     if ("".equals(Utils.reFactorString(benhNhan.getDmgtMaso(true).getDmgtMaso()))) {
       benhNhan.setDmgtMaso(null);
     }
     if ("".equals(Utils.reFactorString(benhNhan.getQuocgiaMa(true).getDmquocgiaMaso()))) {
       benhNhan.setQuocgiaMa(null);
     }
     if ("".equals(Utils.reFactorString(benhNhan.getNhanvienMa(true).getDtdmnhanvienMaso()))) {
       benhNhan.setNhanvienMa(null);
     }
   }

   public static void removeAllNullFromHSBABHYT(HsbaBhyt hsbaBHYT)
   {
     if ("".equals(Utils.reFactorString(hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMaso()))) {
       hsbaBHYT.setHsbabhytTinhbh(null);
     }
     if ("".equals(Utils.reFactorString(hsbaBHYT.getHsbabhytKhoibh(true).getDtdmkhoibhytMa()))) {
       hsbaBHYT.setHsbabhytKhoibh(null);
     }
     if ("".equals(Utils.reFactorString(hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa()))) {
       hsbaBHYT.setHsbabhytMakcb(null);
     }
   }

   public static void removeAllNullFromHSBANop(HsbaNop hoSoBenhAnNop) {}

   public static void removeAllNullFromHSBAMo(HsbaMo hsbamo)
   {
     if ("".equals(Utils.reFactorString(hsbamo.getVocamMaso(true).getDtdmvocamMa()))) {
       hsbamo.setVocamMaso(null);
     }
     if ("".equals(Utils.reFactorString(hsbamo.getHsbamoMamo(true).getDtdmphauthuatMa()))) {
       hsbamo.setHsbamoMamo(null);
     }
     if ("".equals(Utils.reFactorString(hsbamo.getHsbamoBacsi(true).getDtdmnhanvienMa()))) {
       hsbamo.setHsbamoBacsi(null);
     }
     if ("".equals(Utils.reFactorString(hsbamo.getHsbamoCcphien(true).getDtdmccpMaso()))) {
       hsbamo.setHsbamoCcphien(null);
     }
   }

   public static void removeAllNullFromHSBACM(HsbaChuyenMon hoSoBenhAnCM)
   {
     if ("".equals(Utils.reFactorString(hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMaso()))) {
       hoSoBenhAnCM.setKhoaMa(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCM.getHsbacmBenhchinh(true).getDmbenhicdMaso()))) {
       hoSoBenhAnCM.setHsbacmBenhchinh(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCM.getHsbacmTacnhan(true).getDmbenhicdMaso()))) {
       hoSoBenhAnCM.setHsbacmTacnhan(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCM.getNoisinhMa(true).getDtdmnoisinhMaso()))) {
       hoSoBenhAnCM.setNoisinhMa(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCM.getHsbacmBenhphu(true).getDmbenhicdMaso()))) {
       hoSoBenhAnCM.setHsbacmBenhphu(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCM.getKetquaMa(true).getDmkqdtMaso()))) {
       hoSoBenhAnCM.setKetquaMa(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCM.getHsbacmBacsi(true).getDtdmnhanvienMaso()))) {
       hoSoBenhAnCM.setHsbacmBacsi(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCM.getHsbacmChuyenkhoa(true).getDmkhoaMaso()))) {
       hoSoBenhAnCM.setHsbacmChuyenkhoa(null);
     }
   }

   public static void removeAllNullFromHSBACV(HsbaChuyenVien hoSoBenhAnCV)
   {
     if ("".equals(Utils.reFactorString(hoSoBenhAnCV.getHsbacvLydochuyenv(true).getDtdmlydocvMaso()))) {
       hoSoBenhAnCV.setHsbacvLydochuyenv(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCV.getHsbacvChvienden(true).getDmbenhvienMaso()))) {
       hoSoBenhAnCV.setHsbacvChvienden(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCV.getNhanvienMa(true).getDtdmnhanvienMaso()))) {
       hoSoBenhAnCV.setNhanvienMa(null);
     }
     if ("".equals(Utils.reFactorString(hoSoBenhAnCV.getHsbacvBschuyen(true).getDtdmnhanvienMaso()))) {
       hoSoBenhAnCV.setHsbacvBschuyen(null);
     }
   }

   public static void removeIfNullForPhieuTraKho(PhieuTraKho phieuTraKho2)
   {
     if ("".equals(Utils.reFactorString(phieuTraKho2.getPhieudtMa(true).getPhieudtMa())))
     {
       phieuTraKho2.setPhieudtMa(null);
       log.info("Phieu du tru null");
     }
     if ("".equals(Utils.reFactorString(phieuTraKho2.getDmkhoaNhan(true).getDmkhoaMa())))
     {
       phieuTraKho2.setDmkhoaNhan(null);
       log.info("Kho nhan null");
     }
     if ("".equals(Utils.reFactorString(phieuTraKho2.getDmkhoaTra(true).getDmkhoaMa())))
     {
       phieuTraKho2.setDmkhoaTra(null);
       log.info("Khoa tra null");
     }
     if ("".equals(Utils.reFactorString(phieuTraKho2.getDmdoituongMaso(true).getDmdoituongMa())))
     {
       phieuTraKho2.setDmdoituongMaso(null);

       log.info("Doi tuong null");
     }
     if ("".equals(Utils.reFactorString(phieuTraKho2.getDtdmnhanvienBacsi(true).getDtdmnhanvienMa())))
     {
       phieuTraKho2.setDtdmnhanvienBacsi(null);

       log.info("Bac si null");
     }
     if ("".equals(Utils.reFactorString(phieuTraKho2.getDtdmnhanvienCn(true).getDtdmnhanvienMa())))
     {
       phieuTraKho2.setDtdmnhanvienCn(null);
       log.info("Nhan vien cap nhat null");
     }
     if ("".equals(Utils.reFactorString(phieuTraKho2.getDtdmnhanvienLapphieu(true).getDtdmnhanvienMa())))
     {
       phieuTraKho2.setDtdmnhanvienLapphieu(null);
       log.info("Nhan vien nhan null");
     }
     if ("".equals(Utils.reFactorString(phieuTraKho2.getDtdmnhanvienPhat(true).getDtdmnhanvienMa())))
     {
       phieuTraKho2.setDtdmnhanvienPhat(null);
       log.info("nhan vien tra null");
     }
     log.info("End removeIfNullForPhieuTraKho");
   }

   public static void removeAllNullFromHSBACTNoi(HsbaChiTietNoi hsbaChiTietNoi)
   {
     if ((hsbaChiTietNoi.getHsbactnoiBSdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNoi.getHsbactnoiBSdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietNoi.getHsbactnoiBSdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNoi.setHsbactnoiBSdieutri(null);
     }
     if ((hsbaChiTietNoi.getHsbactnoiBSlamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNoi.getHsbactnoiBSlamba().getDtdmnhanvienMa()))) || (hsbaChiTietNoi.getHsbactnoiBSlamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNoi.setHsbactnoiBSlamba(null);
     }
     if ((hsbaChiTietNoi.getHsbactnoiNguoinhanba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNoi.getHsbactnoiNguoinhanba().getDtdmnhanvienMa()))) || (hsbaChiTietNoi.getHsbactnoiNguoinhanba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNoi.setHsbactnoiNguoinhanba(null);
     }
     if ((hsbaChiTietNoi.getHsbactnoiNguoigiaoba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNoi.getHsbactnoiNguoigiaoba().getDtdmnhanvienMa()))) || (hsbaChiTietNoi.getHsbactnoiNguoigiaoba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNoi.setHsbactnoiNguoigiaoba(null);
     }
     if ((hsbaChiTietNoi.getHsbactnoiTienluong() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNoi.getHsbactnoiTienluong().getDmthuocMa()))) || (hsbaChiTietNoi.getHsbactnoiTienluong().getDmthuocMaso() == null))) {
       hsbaChiTietNoi.setHsbactnoiTienluong(null);
     }
   }

   public static void removeAllNullFromHSBACTNgoai(HsbaChiTietNgoai hsbaChiTietNgoai)
   {
     if ((hsbaChiTietNgoai.getHsbactngoaiBSdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNgoai.getHsbactngoaiBSdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietNgoai.getHsbactngoaiBSdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNgoai.setHsbactngoaiBSdieutri(null);
     }
     if ((hsbaChiTietNgoai.getHsbactngoaiBSlamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNgoai.getHsbactngoaiBSlamba().getDtdmnhanvienMa()))) || (hsbaChiTietNgoai.getHsbactngoaiBSlamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNgoai.setHsbactngoaiBSlamba(null);
     }
     if ((hsbaChiTietNgoai.getHsbactngoaiNguoinhanba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNgoai.getHsbactngoaiNguoinhanba().getDtdmnhanvienMa()))) || (hsbaChiTietNgoai.getHsbactngoaiNguoinhanba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNgoai.setHsbactngoaiNguoinhanba(null);
     }
     if ((hsbaChiTietNgoai.getHsbactngoaiNguoigiaoba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNgoai.getHsbactngoaiNguoigiaoba().getDtdmnhanvienMa()))) || (hsbaChiTietNgoai.getHsbactngoaiNguoigiaoba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNgoai.setHsbactngoaiNguoigiaoba(null);
     }
     if ((hsbaChiTietNgoai.getHsbactngoaiTienluong() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNgoai.getHsbactngoaiTienluong().getDmthuocMa()))) || (hsbaChiTietNgoai.getHsbactngoaiTienluong().getDmthuocMaso() == null))) {
       hsbaChiTietNgoai.setHsbactngoaiTienluong(null);
     }
   }

   public static void removeAllNullFromHSBACTMat(HsbaChiTietMat hsbaChiTietMat)
   {
     if ((hsbaChiTietMat.getHsbactmatBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietMat.getHsbactmatBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietMat.getHsbactmatBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietMat.setHsbactmatBsdieutri(null);
     }
     if ((hsbaChiTietMat.getHsbactmatBslamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietMat.getHsbactmatBslamba().getDtdmnhanvienMa()))) || (hsbaChiTietMat.getHsbactmatBslamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietMat.setHsbactmatBslamba(null);
     }
     if ((hsbaChiTietMat.getHsbactmatNguoinhanba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietMat.getHsbactmatNguoinhanba().getDtdmnhanvienMa()))) || (hsbaChiTietMat.getHsbactmatNguoinhanba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietMat.setHsbactmatNguoinhanba(null);
     }
     if ((hsbaChiTietMat.getHsbactmatNguoigiaoba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietMat.getHsbactmatNguoigiaoba().getDtdmnhanvienMa()))) || (hsbaChiTietMat.getHsbactmatNguoigiaoba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietMat.setHsbactmatNguoigiaoba(null);
     }
     if ((hsbaChiTietMat.getHsbactmatTienluong() != null) && (("".equals(Utils.reFactorString(hsbaChiTietMat.getHsbactmatTienluong().getDmthuocMa()))) || (hsbaChiTietMat.getHsbactmatTienluong().getDmthuocMaso() == null))) {
       hsbaChiTietMat.setHsbactmatTienluong(null);
     }
   }

   public static void removeAllNullFromHSBACTRhm(HsbaChiTietRhm hsbaChiTietRhm)
   {
     if ((hsbaChiTietRhm.getHsbactrhmBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietRhm.getHsbactrhmBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietRhm.getHsbactrhmBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietRhm.setHsbactrhmBsdieutri(null);
     }
     if ((hsbaChiTietRhm.getHsbactrhmBslamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietRhm.getHsbactrhmBslamba().getDtdmnhanvienMa()))) || (hsbaChiTietRhm.getHsbactrhmBslamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietRhm.setHsbactrhmBslamba(null);
     }
     if ((hsbaChiTietRhm.getHsbactrhmNguoinhanba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietRhm.getHsbactrhmNguoinhanba().getDtdmnhanvienMa()))) || (hsbaChiTietRhm.getHsbactrhmNguoinhanba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietRhm.setHsbactrhmNguoinhanba(null);
     }
     if ((hsbaChiTietRhm.getHsbactrhmNguoigiaoba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietRhm.getHsbactrhmNguoigiaoba().getDtdmnhanvienMa()))) || (hsbaChiTietRhm.getHsbactrhmNguoigiaoba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietRhm.setHsbactrhmNguoigiaoba(null);
     }
   }

   public static void removeAllNullFromHSBACTTmh(HsbaChiTietTmh hsbaChiTietTmh)
   {
     if ((hsbaChiTietTmh.getHsbacttmhBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietTmh.getHsbacttmhBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietTmh.getHsbacttmhBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietTmh.setHsbacttmhBsdieutri(null);
     }
     if ((hsbaChiTietTmh.getHsbacttmhBslamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietTmh.getHsbacttmhBslamba().getDtdmnhanvienMa()))) || (hsbaChiTietTmh.getHsbacttmhBslamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietTmh.setHsbacttmhBslamba(null);
     }
     if ((hsbaChiTietTmh.getHsbacttmhNguoinhanba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietTmh.getHsbacttmhNguoinhanba().getDtdmnhanvienMa()))) || (hsbaChiTietTmh.getHsbacttmhNguoinhanba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietTmh.setHsbacttmhNguoinhanba(null);
     }
     if ((hsbaChiTietTmh.getHsbacttmhNguoigiaoba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietTmh.getHsbacttmhNguoigiaoba().getDtdmnhanvienMa()))) || (hsbaChiTietTmh.getHsbacttmhNguoigiaoba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietTmh.setHsbacttmhNguoigiaoba(null);
     }
   }

   public static void removeAllNullFromHSBACTNhikhoa(HsbaChiTietNhikhoa hsbaChiTietNhikhoa)
   {
     if ((hsbaChiTietNhikhoa.getHsbactnhikhoaBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNhikhoa.getHsbactnhikhoaBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietNhikhoa.getHsbactnhikhoaBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNhikhoa.setHsbactnhikhoaBsdieutri(null);
     }
     if ((hsbaChiTietNhikhoa.getHsbactnhikhoaBslamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNhikhoa.getHsbactnhikhoaBslamba().getDtdmnhanvienMa()))) || (hsbaChiTietNhikhoa.getHsbactnhikhoaBslamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNhikhoa.setHsbactnhikhoaBslamba(null);
     }
     if ((hsbaChiTietNhikhoa.getHsbactnhikhoaNguoinhanba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNhikhoa.getHsbactnhikhoaNguoinhanba().getDtdmnhanvienMa()))) || (hsbaChiTietNhikhoa.getHsbactnhikhoaNguoinhanba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNhikhoa.setHsbactnhikhoaNguoinhanba(null);
     }
     if ((hsbaChiTietNhikhoa.getHsbactnhikhoaNguoigiaoba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNhikhoa.getHsbactnhikhoaNguoigiaoba().getDtdmnhanvienMa()))) || (hsbaChiTietNhikhoa.getHsbactnhikhoaNguoigiaoba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNhikhoa.setHsbactnhikhoaNguoigiaoba(null);
     }
   }

   public static void removeAllNullFromHSBACTSosinh(HsbaChiTietSosinh hsbaChiTietSosinh)
   {
     if ((hsbaChiTietSosinh.getHsbactsosinhBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietSosinh.getHsbactsosinhBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietSosinh.getHsbactsosinhBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietSosinh.setHsbactsosinhBsdieutri(null);
     }
     if ((hsbaChiTietSosinh.getHsbactsosinhBslamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietSosinh.getHsbactsosinhBslamba().getDtdmnhanvienMa()))) || (hsbaChiTietSosinh.getHsbactsosinhBslamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietSosinh.setHsbactsosinhBslamba(null);
     }
     if ((hsbaChiTietSosinh.getHsbactsosinhNguoinhanba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietSosinh.getHsbactsosinhNguoinhanba().getDtdmnhanvienMa()))) || (hsbaChiTietSosinh.getHsbactsosinhNguoinhanba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietSosinh.setHsbactsosinhNguoinhanba(null);
     }
     if ((hsbaChiTietSosinh.getHsbactsosinhNguoigiaoba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietSosinh.getHsbactsosinhNguoigiaoba().getDtdmnhanvienMa()))) || (hsbaChiTietSosinh.getHsbactsosinhNguoigiaoba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietSosinh.setHsbactsosinhNguoigiaoba(null);
     }
   }

   public static void removeAllNullFromHSBACTPhukhoa(HsbaChiTietPhukhoa hsbaChiTietPhukhoa)
   {
     if ((hsbaChiTietPhukhoa.getHsbactphukhoaBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietPhukhoa.getHsbactphukhoaBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietPhukhoa.getHsbactphukhoaBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietPhukhoa.setHsbactphukhoaBsdieutri(null);
     }
     if ((hsbaChiTietPhukhoa.getHsbactphukhoaBslamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietPhukhoa.getHsbactphukhoaBslamba().getDtdmnhanvienMa()))) || (hsbaChiTietPhukhoa.getHsbactphukhoaBslamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietPhukhoa.setHsbactphukhoaBslamba(null);
     }
     if ((hsbaChiTietPhukhoa.getHsbactphukhoaNguoinhanba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietPhukhoa.getHsbactphukhoaNguoinhanba().getDtdmnhanvienMa()))) || (hsbaChiTietPhukhoa.getHsbactphukhoaNguoinhanba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietPhukhoa.setHsbactphukhoaNguoinhanba(null);
     }
     if ((hsbaChiTietPhukhoa.getHsbactphukhoaNguoigiaoba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietPhukhoa.getHsbactphukhoaNguoigiaoba().getDtdmnhanvienMa()))) || (hsbaChiTietPhukhoa.getHsbactphukhoaNguoigiaoba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietPhukhoa.setHsbactphukhoaNguoigiaoba(null);
     }
   }

   public static void removeAllNullFromHSBACTSankhoa(HsbaChiTietSankhoa hsbaChiTietSankhoa)
   {
     if ((hsbaChiTietSankhoa.getHsbactsankhoaBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietSankhoa.getHsbactsankhoaBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietSankhoa.getHsbactsankhoaBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietSankhoa.setHsbactsankhoaBsdieutri(null);
     }
     if ((hsbaChiTietSankhoa.getHsbactsankhoaBslamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietSankhoa.getHsbactsankhoaBslamba().getDtdmnhanvienMa()))) || (hsbaChiTietSankhoa.getHsbactsankhoaBslamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietSankhoa.setHsbactsankhoaBslamba(null);
     }
     if ((hsbaChiTietSankhoa.getHsbactsankhoaNguoinhanba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietSankhoa.getHsbactsankhoaNguoinhanba().getDtdmnhanvienMa()))) || (hsbaChiTietSankhoa.getHsbactsankhoaNguoinhanba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietSankhoa.setHsbactsankhoaNguoinhanba(null);
     }
     if ((hsbaChiTietSankhoa.getHsbactsankhoaNguoigiaoba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietSankhoa.getHsbactsankhoaNguoigiaoba().getDtdmnhanvienMa()))) || (hsbaChiTietSankhoa.getHsbactsankhoaNguoigiaoba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietSankhoa.setHsbactsankhoaNguoigiaoba(null);
     }
   }

   public static void removeAllNullFromHSBAPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat)
   {
     if ((hsbaPhieuPhauThuatThuThuat.getHsbapptttBacsi() != null) && (("".equals(Utils.reFactorString(hsbaPhieuPhauThuatThuThuat.getHsbapptttBacsi().getDtdmnhanvienMa()))) || (hsbaPhieuPhauThuatThuThuat.getHsbapptttBacsi().getDtdmnhanvienMaso() == null))) {
       hsbaPhieuPhauThuatThuThuat.setHsbapptttBacsi(null);
     }
     if ((hsbaPhieuPhauThuatThuThuat.getHsbapptttBacsiGmhs() != null) && (("".equals(Utils.reFactorString(hsbaPhieuPhauThuatThuThuat.getHsbapptttBacsiGmhs().getDtdmnhanvienMa()))) || (hsbaPhieuPhauThuatThuThuat.getHsbapptttBacsiGmhs().getDtdmnhanvienMaso() == null))) {
       hsbaPhieuPhauThuatThuThuat.setHsbapptttBacsiGmhs(null);
     }
     if ((hsbaPhieuPhauThuatThuThuat.getHsbapptttChuandoanmaSau() != null) && (("".equals(Utils.reFactorString(hsbaPhieuPhauThuatThuThuat.getHsbapptttChuandoanmaSau().getDmbenhicdMa()))) || (hsbaPhieuPhauThuatThuThuat.getHsbapptttChuandoanmaSau().getDmbenhicdMaso() == null))) {
       hsbaPhieuPhauThuatThuThuat.setHsbapptttChuandoanmaSau(null);
     }
     if ((hsbaPhieuPhauThuatThuThuat.getHsbapptttChuandoanmaTruoc() != null) && (("".equals(Utils.reFactorString(hsbaPhieuPhauThuatThuThuat.getHsbapptttChuandoanmaTruoc().getDmbenhicdMa()))) || (hsbaPhieuPhauThuatThuThuat.getHsbapptttChuandoanmaTruoc().getDmbenhicdMaso() == null))) {
       hsbaPhieuPhauThuatThuThuat.setHsbapptttChuandoanmaTruoc(null);
     }
     if ((hsbaPhieuPhauThuatThuThuat.getHsbapptttLoai() != null) && (("".equals(Utils.reFactorString(hsbaPhieuPhauThuatThuThuat.getHsbapptttLoai().getDtdmloaiptMa()))) || (hsbaPhieuPhauThuatThuThuat.getHsbapptttLoai().getDtdmloaiptMaso() == null))) {
       hsbaPhieuPhauThuatThuThuat.setHsbapptttLoai(null);
     }
     if ((hsbaPhieuPhauThuatThuThuat.getHsbapptttPhuongphap() != null) && (("".equals(Utils.reFactorString(hsbaPhieuPhauThuatThuThuat.getHsbapptttPhuongphap().getDtdmclsbgMa()))) || (hsbaPhieuPhauThuatThuThuat.getHsbapptttPhuongphap().getDtdmclsbgMa() == null))) {
       hsbaPhieuPhauThuatThuThuat.setHsbapptttPhuongphap(null);
     }
     if ((hsbaPhieuPhauThuatThuThuat.getHsbapptttPhuongphapVocam() != null) && (("".equals(Utils.reFactorString(hsbaPhieuPhauThuatThuThuat.getHsbapptttPhuongphapVocam().getDtdmvocamMa()))) || (hsbaPhieuPhauThuatThuThuat.getHsbapptttPhuongphapVocam().getDtdmvocamMaso() == null))) {
       hsbaPhieuPhauThuatThuThuat.setHsbapptttPhuongphapVocam(null);
     }
   }

   public static void removeAllNullFromHSBALapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan)
   {
     if ((hsbaLapTrichBienBanHoiChan.getHsbaltbbhcChutoa() != null) && (("".equals(Utils.reFactorString(hsbaLapTrichBienBanHoiChan.getHsbaltbbhcChutoa().getDtdmnhanvienMa()))) || (hsbaLapTrichBienBanHoiChan.getHsbaltbbhcChutoa().getDtdmnhanvienMaso() == null))) {
       hsbaLapTrichBienBanHoiChan.setHsbaltbbhcChutoa(null);
     }
     if ((hsbaLapTrichBienBanHoiChan.getHsbaltbbhcThuky() != null) && (("".equals(Utils.reFactorString(hsbaLapTrichBienBanHoiChan.getHsbaltbbhcThuky().getDtdmnhanvienMa()))) || (hsbaLapTrichBienBanHoiChan.getHsbaltbbhcThuky().getDtdmnhanvienMaso() == null))) {
       hsbaLapTrichBienBanHoiChan.setHsbaltbbhcThuky(null);
     }
     if ((hsbaLapTrichBienBanHoiChan.getHsbaltbbhcChuandoanma() != null) && (("".equals(Utils.reFactorString(hsbaLapTrichBienBanHoiChan.getHsbaltbbhcChuandoanma().getDmbenhicdMa()))) || (hsbaLapTrichBienBanHoiChan.getHsbaltbbhcChuandoanma().getDmbenhicdMaso() == null))) {
       hsbaLapTrichBienBanHoiChan.setHsbaltbbhcChuandoanma(null);
     }
   }

   public static void removeAllNullFromHSBABienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat)
   {
     if ((hsbaBienBanHoiChanPhauThuat.getHsbabbhcptThuky() != null) && (("".equals(Utils.reFactorString(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptThuky().getDtdmnhanvienMa()))) || (hsbaBienBanHoiChanPhauThuat.getHsbabbhcptThuky().getDtdmnhanvienMaso() == null))) {
       hsbaBienBanHoiChanPhauThuat.setHsbabbhcptThuky(null);
     }
     if ((hsbaBienBanHoiChanPhauThuat.getHsbabbhcptChuandoanma() != null) && (("".equals(Utils.reFactorString(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptChuandoanma().getDmbenhicdMa()))) || (hsbaBienBanHoiChanPhauThuat.getHsbabbhcptChuandoanma().getDmbenhicdMaso() == null))) {
       hsbaBienBanHoiChanPhauThuat.setHsbabbhcptChuandoanma(null);
     }
     if ((hsbaBienBanHoiChanPhauThuat.getHsbabbhcptKhoahoichan() != null) && (("".equals(Utils.reFactorString(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptKhoahoichan().getDmkhoaMa()))) || (hsbaBienBanHoiChanPhauThuat.getHsbabbhcptKhoahoichan().getDmkhoaMaso() == null))) {
       hsbaBienBanHoiChanPhauThuat.setHsbabbhcptKhoahoichan(null);
     }
     if ((hsbaBienBanHoiChanPhauThuat.getHsbabbhcptPhuongphap() != null) && (("".equals(Utils.reFactorString(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptPhuongphap().getDtdmclsbgMa()))) || (hsbaBienBanHoiChanPhauThuat.getHsbabbhcptPhuongphap().getDtdmclsbgMaso() == null))) {
       hsbaBienBanHoiChanPhauThuat.setHsbabbhcptPhuongphap(null);
     }
     if ((hsbaBienBanHoiChanPhauThuat.getHsbabbhcptPhuongphapVocam() != null) && (("".equals(Utils.reFactorString(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptPhuongphapVocam().getDtdmvocamMa()))) || (hsbaBienBanHoiChanPhauThuat.getHsbabbhcptPhuongphapVocam().getDtdmvocamMa() == null))) {
       hsbaBienBanHoiChanPhauThuat.setHsbabbhcptKhoahoichan(null);
     }
   }

   public static void removeAllNullFromHSBACTNgoaitruYhct(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct)
   {
     if ((hsbaChiTietNgoaitruYhct.getHsbactngoaitruYhctBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNgoaitruYhct.getHsbactngoaitruYhctBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietNgoaitruYhct.getHsbactngoaitruYhctBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNgoaitruYhct.setHsbactngoaitruYhctBsdieutri(null);
     }
   }

   public static void removeAllNullFromHSBACTNaophathai(HsbaChiTietNaophathai hsbaChiTietNaophathai)
   {
     if ((hsbaChiTietNaophathai.getHsbactnaophathaiBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNaophathai.getHsbactnaophathaiBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietNaophathai.getHsbactnaophathaiBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNaophathai.setHsbactnaophathaiBsdieutri(null);
     }
     if ((hsbaChiTietNaophathai.getHsbactnaophathaiBslamba() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNaophathai.getHsbactnaophathaiBslamba().getDtdmnhanvienMa()))) || (hsbaChiTietNaophathai.getHsbactnaophathaiBslamba().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNaophathai.setHsbactnaophathaiBslamba(null);
     }
   }

   public static void removeAllNullFromHSBACTNoitruYhct(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct)
   {
     if ((hsbaChiTietNoitruYhct.getHsbactnoitruYhctBsdieutri() != null) && (("".equals(Utils.reFactorString(hsbaChiTietNoitruYhct.getHsbactnoitruYhctBsdieutri().getDtdmnhanvienMa()))) || (hsbaChiTietNoitruYhct.getHsbactnoitruYhctBsdieutri().getDtdmnhanvienMaso() == null))) {
       hsbaChiTietNoitruYhct.setHsbactnoitruYhctBsdieutri(null);
     }
   }

   public static void removeIfNullForGiayCvNbBhyt(GiayCvNbBhyt obj)
   {
     if ("".equals(Utils.reFactorString(obj.getGcvbhytBskham(true).getDtdmnhanvienMaso()))) {
       obj.setGcvbhytBskham(null);
     }
     if ("".equals(Utils.reFactorString(obj.getGcvbhytGiamdoc(true).getDtdmnhanvienMaso())))
     {
       obj.setGcvbhytGiamdoc(null);
       log.info("Giam doc null");
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.remove.RemoveUtil

 * JD-Core Version:    0.7.0.1

 */