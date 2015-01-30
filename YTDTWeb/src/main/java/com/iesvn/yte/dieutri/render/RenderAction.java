 package com.iesvn.yte.dieutri.render;

 import com.iesvn.yte.util.IConstantsRes;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.security.Identity;

 @Name("YTDT_renderAction")
 public class RenderAction
 {
   @In
   private Identity identity;

   public boolean hasRoleGeneral(String strRoles)
   {
     boolean myReturn = false;
     try
     {
       String[] roles = strRoles.split(",");
       for (int i = 0; i < roles.length; i++) {
         if (this.identity.hasRole(roles[i])) {
           myReturn = true;
         }
       }
     }
     catch (Exception e) {}
     return myReturn;
   }

   public boolean getTiepDon_TiepDonBenhNhan_DangKyKhamBenh()
   {
     String strRoles = IConstantsRes.TiepDon_TiepDonBenhNhan_DangKyKhamBenh;
     return hasRoleGeneral(strRoles);
   }

   public boolean getTiepDon_KhamBenh_ThamKhamXuTri()
   {
     String strRoles = IConstantsRes.TiepDon_KhamBenh_ThamKhamXuTri;
     return hasRoleGeneral(strRoles);
   }

   public boolean getTiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan()
   {
     String strRoles = IConstantsRes.TiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDieuTri_CapNhat_CapNhatThongTinChung()
   {
     String strRoles = IConstantsRes.DieuTri_CapNhat_CapNhatThongTinChung;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDieuTri_CapNhat_CapNhatThongTinMo()
   {
     String strRoles = IConstantsRes.DieuTri_CapNhat_CapNhatThongTinMo;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDieuTri_CapNhat_CapNhatThongTinNhapVien()
   {
     String strRoles = IConstantsRes.DieuTri_CapNhat_CapNhatThongTinNhapVien;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnDangCapNhat()
   {
     String strRoles = IConstantsRes.DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnDangCapNhat;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDieuTri_SoLieuBenhNhanSuDung_ThuocYDungCuSuDung()
   {
     String strRoles = IConstantsRes.DieuTri_SoLieuBenhNhanSuDung_ThuocYDungCuSuDung;
     return hasRoleGeneral(strRoles);
   }

   public boolean getVienPhi_SoLieuCanLamSanPhauThuat_LapPhieuDuTru()
   {
     String strRoles = IConstantsRes.VienPhi_SoLieuCanLamSanPhauThuat_LapPhieuDuTru;
     return hasRoleGeneral(strRoles);
   }

   public boolean getVienPhi_SoLieuKhamBenh_CanLamSanPhongKham()
   {
     String strRoles = IConstantsRes.VienPhi_SoLieuKhamBenh_CanLamSanPhongKham;
     return hasRoleGeneral(strRoles);
   }

   public boolean getVienPhi_SoLieuBenhNhanSuDung_ThuocYDungCuSuDung()
   {
     String strRoles = IConstantsRes.VienPhi_SoLieuBenhNhanSuDung_ThuocYDungCuSuDung;
     return hasRoleGeneral(strRoles);
   }

   public boolean getVienPhi_ThanhToanRaVien_ThanhToanRaVien()
   {
     String strRoles = IConstantsRes.VienPhi_ThanhToanRaVien_ThanhToanRaVien;
     return hasRoleGeneral(strRoles);
   }

   public boolean getVienPhi_HoSoBaoCao_SoLieuCanLamSanNoiTruNgoaiTru()
   {
     String strRoles = IConstantsRes.VienPhi_HoSoBaoCao_SoLieuCanLamSanNoiTruNgoaiTru;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDuocPham_NhapKho_NhapHangTuNhaCungCap()
   {
     String strRoles = IConstantsRes.DuocPham_NhapKho_NhapHangTuNhaCungCap;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDuocPham_XuatKho_XuatHangTheoPhieuDuTru()
   {
     String strRoles = IConstantsRes.DuocPham_XuatKho_XuatHangTheoPhieuDuTru;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDuocPham_XuatKho_XuatHangDenKhoKhacKhoBHYT()
   {
     String strRoles = IConstantsRes.DuocPham_XuatKho_XuatHangDenKhoKhacKhoBHYT;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDuocPham_XuatKho_XuatHangchoBenhNhanBHYT()
   {
     String strRoles = IConstantsRes.DuocPham_XuatKho_XuatHangchoBenhNhanBHYT;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDuocPham_BaoCaoKho_TinhTonKhoVaGiaTri()
   {
     String strRoles = IConstantsRes.DuocPham_BaoCaoKho_TinhTonKhoVaGiaTri;
     return hasRoleGeneral(strRoles);
   }

   public boolean getDuocPham_BaoCaoKho_BaoCaoNhapXuat()
   {
     String strRoles = IConstantsRes.DuocPham_BaoCaoKho_BaoCaoNhapXuat;
     return hasRoleGeneral(strRoles);
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.render.RenderAction

 * JD-Core Version:    0.7.0.1

 */