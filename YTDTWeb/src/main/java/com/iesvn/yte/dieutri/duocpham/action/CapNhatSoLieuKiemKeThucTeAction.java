 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.KiemKe;
 import com.iesvn.yte.dieutri.entity.KiemKeKho;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFRichTextString;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.hssf.util.CellReference;
 import org.apache.poi.poifs.filesystem.POIFSFileSystem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;
 import org.jboss.seam.security.Identity;
 import org.richfaces.event.UploadEvent;
 import org.richfaces.model.UploadItem;

 @Scope(ScopeType.CONVERSATION)
 @Name("CapNhatSoLieuKiemKeThucTeAction")
 public class CapNhatSoLieuKiemKeThucTeAction
   implements Serializable
 {
   @Logger
   private Log log;
   @In(required=false)
   @Out(required=false)
   private KiemKe kiemkeOut;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   String dmKhoCapNhat = "";
   DmKhoa dmKho = new DmKhoa();
   private KiemKe kiemke;
   private DtDmNhanVien nv;
   DtDmNhanVienDelegate nvDelegate;
   String resetFormCapNhatKiemKeFile = "";
   KiemKeDelegate kiemkeDel;
   File file;
   private String message;

   public boolean isNumberic(String str)
   {
     try
     {
       str = str.trim();
       Integer foo = Integer.valueOf(Integer.parseInt(str));
       return true;
     }
     catch (Exception ex) {}
     return false;
   }

   @Factory("resetFormCapNhatKiemKeFile")
   public void resetFormCapNhatKiemKeFile()
   {
     this.log.info("resetFormCapNhatKiemKeFile()", new Object[0]);
     this.kiemke = this.kiemkeOut;
     this.dmKhoCapNhat = this.kiemke.getDmkhoaMaso().getDmkhoaMa();
     this.dmKho = this.kiemke.getDmkhoaMaso();
     this.kiemkeDel = KiemKeDelegate.getInstance();
     this.nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nv = this.nvDelegate.findByNd(this.identity.getUsername());
     this.message = "";
     this.resetFormCapNhatKiemKeFile = "";
   }

   @Begin(join=true)
   public String init(String kho)
     throws Exception
   {
     System.out.println("-------Begin Init()------");
     this.dmKhoCapNhat = kho;
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     if (this.dmKhoCapNhat.equals("KC")) {
       this.dmKho = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa"));
     } else if (this.dmKhoCapNhat.equals("TE")) {
       this.dmKho = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa"));
     } else if (this.dmKhoCapNhat.equals("BHYT")) {
       this.dmKho = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa"));
     } else {
       this.dmKho = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa"));
     }
     this.nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.kiemkeDel = KiemKeDelegate.getInstance();
     this.nv = this.nvDelegate.findByNd(this.identity.getUsername());
     this.message = "";
     System.out.println("-------End Init()------");
     return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTe";
   }

   @End
   public void end() {}

   public void listener(UploadEvent event)
     throws IOException
   {
     System.out.println("---------Called listener()--------");
     UploadItem item = event.getUploadItem();
     this.file = item.getFile();
     this.log.info("duong dan cua file " + this.file.getPath(), new Object[0]);
   }

   public void ghinhan()
   {
     this.log.info("Ghi nhan so lieu vao database" + this.file, new Object[0]);
     List<KiemKeKho> lstKiemKeKho = new ArrayList();
     if (this.nv == null) {
       return;
     }
     try
     {
       POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(this.file));
       HSSFWorkbook wb = new HSSFWorkbook(fs);
       HSSFSheet sheet = wb.getSheetAt(0);




       int rows = sheet.getPhysicalNumberOfRows();

       this.log.info("Tong so rows " + rows, new Object[0]);

       int cols = 0;
       int tmp = 0;
       for (int i = 0; (i < 10) || (i < rows); i++)
       {
         HSSFRow row = sheet.getRow(i);
         if (row != null)
         {
           tmp = sheet.getRow(i).getPhysicalNumberOfCells();
           if (tmp > cols) {
             cols = tmp;
           }
         }
       }
       this.log.info(" so Columm " + cols, new Object[0]);
       boolean end = false;
       double tonTT = 0.0D;
       int maKiemKe = 0;
       Date now = new Date();
       for (int i = 20; i < rows; i++)
       {
         HSSFRow row = sheet.getRow(i);
         tonTT = 0.0D;
         maKiemKe = 0;
         String lydo = "";
         if (row != null) {
           for (int j = 0; j < cols; j++)
           {
             HSSFCell cell = row.getCell(j);
             if (cell != null)
             {
               CellReference cellRef = new CellReference(row.getRowNum(), cell.getCellNum(), false, false);
               if ((cellRef.formatAsString().startsWith("M")) &&
                 (cell.getCellType() == 0)) {
                 tonTT = cell.getNumericCellValue();
               }
               if ((cellRef.formatAsString().startsWith("S")) &&
                 (cell.getCellType() == 1)) {
                 lydo = cell.getRichStringCellValue().getString();
               }
               if (cellRef.formatAsString().startsWith("T")) {
                 if (cell.getCellType() == 0) {
                   maKiemKe = (int)cell.getNumericCellValue();
                 } else if ((cell.getCellType() == 1) &&
                   (cell.getRichStringCellValue().getString().toLowerCase().equals("E".toLowerCase()))) {
                   end = true;
                 }
               }
             }
           }
         }
         if (maKiemKe != 0)
         {
           KiemKeKho kiemkeKho = new KiemKeKho();
           kiemkeKho.setKiemkeMa(Integer.valueOf(maKiemKe));
           kiemkeKho.setKiemkeNgaygiocn(now);
           kiemkeKho.setDtdmnhanvienCn(this.nv);
           kiemkeKho.setKiemkeTontt(Double.valueOf(tonTT));
           if (lydo != "") {
             kiemkeKho.setKiemkeLydo(lydo);
           }
           lstKiemKeKho.add(kiemkeKho);
         }
         if (end) {
           break;
         }
       }
       this.kiemke.setDtdmnhanvienCn(this.nv);
       this.kiemke.setNgaygiocn(now);
       this.kiemke.setTrangthai("CLOSE");

       String result = this.kiemkeDel.capnhatSoLieuKiemKe(this.kiemke, lstKiemKeKho);
       if (result.equals("SUCCESS")) {
         FacesMessages.instance().add("Bạn đã cập nhật số liệu thành công.", new Object[0]);
       } else {
         FacesMessages.instance().add("Bạn không cập nhật số liệu thành công bảng kiểm kê này." + result, new Object[0]);
       }
       this.file.delete();
     }
     catch (Exception ioe)
     {
       ioe.printStackTrace();
     }
   }

   public String getMessage()
   {
     return this.message;
   }

   public void setMessage(String message)
   {
     this.message = message;
   }

   public String refreshPage()
   {
     return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTe";
   }

   public String quayve()
   {
     this.resetFormCapNhatKiemKeFile = null;
     return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.CapNhatSoLieuKiemKeThucTeAction

 * JD-Core Version:    0.7.0.1

 */