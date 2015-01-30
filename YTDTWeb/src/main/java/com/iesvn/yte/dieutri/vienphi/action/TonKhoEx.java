 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import java.io.Serializable;

 public class TonKhoEx
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private TonKho tk;
   private ThuocNoiTru tnt;
   private Integer thutu;
   private Boolean isAlowedUpdate;

   public TonKho getTk()
   {
     return this.tk;
   }

   public TonKho getTk(Boolean create)
   {
     if ((create.booleanValue()) && (this.tk == null)) {
       this.tk = new TonKho();
     }
     return this.tk;
   }

   public void setTk(TonKho tk)
   {
     this.tk = tk;
   }

   public ThuocNoiTru getTnt()
   {
     return this.tnt;
   }

   public ThuocNoiTru getTnt(Boolean create)
   {
     if ((create.booleanValue()) && (this.tnt == null)) {
       this.tnt = new ThuocNoiTru();
     }
     return this.tnt;
   }

   public void setTnt(ThuocNoiTru tnt)
   {
     this.tnt = tnt;
   }

   public Integer getThutu()
   {
     return this.thutu;
   }

   public void setThutu(Integer thutu)
   {
     this.thutu = thutu;
   }

   public Boolean getIsAllowedUpdate()
   {
     return this.isAlowedUpdate;
   }

   public void setIsAllowedUpdate(Boolean isAlowedUpdate)
   {
     this.isAlowedUpdate = isAlowedUpdate;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.TonKhoEx

 * JD-Core Version:    0.7.0.1

 */