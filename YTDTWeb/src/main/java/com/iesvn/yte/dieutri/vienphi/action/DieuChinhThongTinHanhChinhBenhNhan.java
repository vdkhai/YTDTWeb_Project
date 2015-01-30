package com.iesvn.yte.dieutri.vienphi.action;

import com.iesvn.yte.dieutri.entity.Hsba;
import java.io.Serializable;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

@Scope(ScopeType.CONVERSATION)
@Name("B3243_Dieuchinhthongtinhanhchinhbenhnhan")
@Synchronized(timeout=6000000L)
public class DieuChinhThongTinHanhChinhBenhNhan
  implements Serializable
{
  private Hsba hsba;
  private String ngaySinh;
  
  public void displayInfor() {}
}


/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\
 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.DieuChinhThongTinHanhChinhBenhNhan
 * JD-Core Version:    0.7.0.1
 */