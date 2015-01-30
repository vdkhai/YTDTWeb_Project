 package com.iesvn.yte.entity;

 import com.iesvn.yte.util.IConstantsRes;
 import java.util.ArrayList;
 import java.util.List;
 import javax.faces.model.SelectItem;

 public class DanhSachLoaiClsBangGia
 {
   private List<SelectItem> listLoaiCls;

   public DanhSachLoaiClsBangGia()
   {
     this.listLoaiCls = new ArrayList();
     this.listLoaiCls.add(new SelectItem("", "..."));

































































     this.listLoaiCls.add(new SelectItem("10", IConstantsRes.DS_LOAI_CLS_BANG_GIA_HUYET_HOC));
     this.listLoaiCls.add(new SelectItem("13", IConstantsRes.DS_LOAI_CLS_BANG_GIA_SINH_HOA));
     this.listLoaiCls.add(new SelectItem("11", IConstantsRes.DS_LOAI_CLS_BANG_GIA_VI_TRUNG));
     this.listLoaiCls.add(new SelectItem("12", IConstantsRes.DS_LOAI_CLS_BANG_GIA_KY_SINH_TRUNG));
     this.listLoaiCls.add(new SelectItem("14", IConstantsRes.DS_LOAI_CLS_BANG_GIA_HIV));
     this.listLoaiCls.add(new SelectItem("15", IConstantsRes.DS_LOAI_CLS_BANG_GIA_XNKHAC));


     this.listLoaiCls.add(new SelectItem("1", IConstantsRes.DS_LOAI_CLS_BANG_GIA_CHIEU_XQ));
     this.listLoaiCls.add(new SelectItem("2", IConstantsRes.DS_LOAI_CLS_BANG_GIA_CHUP_XQT));
     this.listLoaiCls.add(new SelectItem("3", IConstantsRes.DS_LOAI_CLS_BANG_GIA_CHUP_XQKT));
     this.listLoaiCls.add(new SelectItem("5", IConstantsRes.DS_LOAI_CLS_BANG_GIA_SIEU_AM));
     this.listLoaiCls.add(new SelectItem("7", IConstantsRes.DS_LOAI_CLS_BANG_GIA_CT_SCANNER));
     this.listLoaiCls.add(new SelectItem("28", IConstantsRes.DS_LOAI_CLS_BANG_GIA_CDHA_CHT));
     this.listLoaiCls.add(new SelectItem("29", IConstantsRes.DS_LOAI_CLS_BANG_GIA_CDHA_KHAC));

     this.listLoaiCls.add(new SelectItem("4", IConstantsRes.DS_LOAI_CLS_BANG_GIA_DIEN_TIM));
     this.listLoaiCls.add(new SelectItem("8", IConstantsRes.DS_LOAI_CLS_BANG_GIA_DIEN_NAO));
     this.listLoaiCls.add(new SelectItem("6", IConstantsRes.DS_LOAI_CLS_BANG_GIA_NOI_SOI));
     this.listLoaiCls.add(new SelectItem("9", IConstantsRes.DS_LOAI_CLS_BANG_GIA_TDCN_KHAC));

     this.listLoaiCls.add(new SelectItem("27", IConstantsRes.DS_LOAI_CLS_BANG_GIA_TRUYEN_MAU));

     this.listLoaiCls.add(new SelectItem("20", IConstantsRes.DS_LOAI_CLS_BANG_GIA_MO_DAI_THE));
     this.listLoaiCls.add(new SelectItem("21", IConstantsRes.DS_LOAI_CLS_BANG_GIA_XN_VI_THE));
     this.listLoaiCls.add(new SelectItem("22", IConstantsRes.DS_LOAI_CLS_BANG_GIA_GPB_KHAC));


     this.listLoaiCls.add(new SelectItem("16", IConstantsRes.DS_LOAI_CLS_BANG_GIA_KSTST_DT));
     this.listLoaiCls.add(new SelectItem("17", IConstantsRes.DS_LOAI_CLS_BANG_GIA_KSTST_FALCI));
     this.listLoaiCls.add(new SelectItem("18", IConstantsRes.DS_LOAI_CLS_BANG_GIA_KSTST_VIVAX));
     this.listLoaiCls.add(new SelectItem("19", IConstantsRes.DS_LOAI_CLS_BANG_GIA_KSTST_FV));
     this.listLoaiCls.add(new SelectItem("23", IConstantsRes.DS_LOAI_CLS_BANG_GIA_VLTL_CHIEU_HN));
     this.listLoaiCls.add(new SelectItem("24", IConstantsRes.DS_LOAI_CLS_BANG_GIA_VLTL_KEO_TA));
     this.listLoaiCls.add(new SelectItem("25", IConstantsRes.DS_LOAI_CLS_BANG_GIA_VLTL_TAP_VAN_DONG));
     this.listLoaiCls.add(new SelectItem("26", IConstantsRes.DS_LOAI_CLS_BANG_GIA_KHAC));
   }

   public List<SelectItem> getListLoaiCls()
   {
     return this.listLoaiCls;
   }

   public void setListLoaiCls(List<SelectItem> listLoaiCls)
   {
     this.listLoaiCls = listLoaiCls;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.entity.DanhSachLoaiClsBangGia

 * JD-Core Version:    0.7.0.1

 */