var prefix_component = document.forms[0].id + ":";
var thueVAT = 0;
var nhaCc = null;
var nvCn = null;
var nguonCt = null;
var nguonKp = null;
var kho = null;
var loai = null;
var nguonCtMa = "";
var nguonKpMa = "";

function LoadDB_formMaPhu1(MAPHU) {
	//alert("LoadDB_formMaPhu1");
	try{
		var listPn = PhieuNhapKho.filter("PHIEU_NHAP_KHO_MAPHU = ?", MAPHU).toArray();
		for (var i = 0; i < listPn.length; i++) {
			var nhaCc = DmNhaCungCap.getByFieldValue("Ma", listPn[i].DTDMNOIBAN_MA);
			var nvCn = DtDmNhanVien.getByFieldValue("Ma", listPn[i].DTDMNHANVIEN_CN);
			var nguonCt = DmNguonChuongTrinh.getByFieldValue("Ma", listPn[i].DTDMNGUON_MA);
			var nguonKp = DmNguonKinhPhi.getByFieldValue("Ma", listPn[i].DMKINHPHI_MASO);
			var kho = DtDmKho.getByFieldValue("Ma", listPn[i].DMKHO_MASO);
			var loai = DmLoaiThuoc.getByFieldValue("Ma", listPn[i].DTDMLOAI_MA);
			
			var khoTen = kho.Ten;
			
			nguonCtMa = nguonCt.Ma;
			nguonKpMa = nguonKp.Ma;
			
			document.getElementById(prefix_component + "__ngaynhap").value = getDateSystem_dd_MM_yyyy();
			document.getElementById(prefix_component + "__noicungcap").value = nhaCc.Ten;
			document.getElementById(prefix_component + "__hoadon").value = listPn[i].PHIEUNHAPKHO_SOHOADON;
			//document.getElementById(prefix_component + "__nguoigiaohang").value = getNameById_Catalog(listPn[i].DTDMNHANVIEN_TIEPLIEU, DtDmNhanVien);
			document.getElementById(prefix_component + "__sudungvitinh").value = nvCn.Ten;
			document.getElementById(prefix_component + "__chuongtrinh").value = nguonCt.Ten;
			document.getElementById(prefix_component + "__chungtu").value = listPn[i].PHIEUNHAPKHO_CHUNGTU;
			document.getElementById(prefix_component + "__namnhap").value = new Date().getYear();
			document.getElementById(prefix_component + "__nhapkho").value = khoTen;
			document.getElementById(prefix_component + "__nguonKP").value = nguonKp.Ten;
			document.getElementById(prefix_component + "__thanhtien").value = listPn[i].PHIEUNHAPKHO_THANHTIEN;
			
			if (isNullValue(listPn[i].PHIEUNHAPKHO_MUCTHUE) || listPn[i].PHIEUNHAPKHO_MUCTHUE == "") {
				thueVAT = 0;
			} else {
				thueVAT = parseFloat(listPn[i].PHIEUNHAPKHO_MUCTHUE);
			}
			
			document.getElementById(prefix_component + "__VAT").value = thueVAT;
			document.getElementById(prefix_component + "__ngay").value = new Date().getDate();
			document.getElementById(prefix_component + "__thang").value = (new Date().getMonth()) + 1;
			document.getElementById(prefix_component + "__nam").value = new Date().getYear();
			var maPhieu = listPn[i].PHIEUNHAPKHO_MA;
			document.getElementById(prefix_component + "__maPhieu").innerHTML = "Phiếu số " + 
													maPhieu.substr(6, maPhieu.length) + " " + listPn[i].DTDMLOAI_MA;
			document.getElementById(prefix_component + "__loaiMa").innerHTML = loai.Ten;
		}
		
    }
    catch(e) {
    	alert(e.description);
    }					
}
            
function drawTable1(MAPHU){
	var tbl = document.getElementById(prefix_component + '__myTable');
	while(tbl.rows.length > 1){
		tbl.deleteRow(tbl.rows.length - 1);
	}
	
	var tt = 0;
	i = 1;
	var listCt = CtNhapKho.filter("PHIEUNHAPKHO_MAPHU = ?", MAPHU).toArray();
	for (var j = 0; j < listCt.length; j++) {
		countRows = tbl.insertRow(tbl.rows.length);
    	tbl.rows(i).style.height = "16px";
    	if(i%2 == 0)
    		tbl.rows(i).style.background = "#E8F2FE";
    	else
    		tbl.rows(i).style.background = "#ffffff";	
		
		Stt = countRows.insertCell(0);
		TenNhanHieu = countRows.insertCell(1);
		DonVi = countRows.insertCell(2);
		SoLuong = countRows.insertCell(3);
		DonGiaVAT = countRows.insertCell(4);
		ThanhTien = countRows.insertCell(5);
		                
		Stt.align = "right";
		Stt.style.padding = "3px";				                           
        Stt.innerHTML=i;
        
        TenNhanHieu.style.padding = "3px";
        //TenNhanHieu.innerHTML=getNameById_Catalog(listCt[j].DMTHUOC_MASO,DmThuoc);
        var tenThuoc = "";
        var hamLuong = "";
        var hangSx = listCt[j].DTDMHANGSX_MA;
        var hanDung = "";
        var dmt = DmThuoc.getByFieldValue("Ma", listCt[j].DMTHUOC_MASO);
        
        if (hangSx == null || hangSx == "null" || hangSx == "NULL") {
        	hangSx = "";
        }
        
        if (listCt[j].CTNHAPKHO_NGAYHANDUNG != "" && listCt[j].CTNHAPKHO_NGAYHANDUNG != null &&
        	listCt[j].CTNHAPKHO_NGAYHANDUNG != "null" && listCt[j].CTNHAPKHO_NGAYHANDUNG != "NULL") {
        	
        	hanDung = listCt[j].CTNHAPKHO_NGAYHANDUNG + "/" + 
        				listCt[j].CTNHAPKHO_THANGHANDUNG + "/" + 
        				listCt[j].CTNHAPKHO_NAMHANDUNG;
        	
        }
        
        TenNhanHieu.innerHTML = "<div>" + dmt.Ten + " " + dmt.DMTHUOC_HAMLUONG + " " + 
        						hangSx + " " + nguonCtMa + "</div>" +
        						"<br /> HD: " + hanDung + " " + " Lô: " + nguonKpMa;
        //alert(TenNhanHieu.innerHTML);
		 
		DonVi.style.padding = "3px";
		var dvt = DmDonViTinh.getByFieldValue("MaSo", dmt.DMDONVITINH_MASO);
		DonVi.innerHTML = dvt.Ten;
		
		SoLuong.align = "right";
		SoLuong.style.padding = "3px";
		SoLuong.innerHTML=listCt[j].CTNHAPKHO_SOLUONG;
		
		DonGiaVAT.align = "right";
		DonGiaVAT.style.padding = "3px";
		var dg = null;
		if (listCt[j].CTNHAPKHO_DONGIA == "" || 
			listCt[j].CTNHAPKHO_DONGIA == "null" || 
			listCt[j].CTNHAPKHO_DONGIA == "NULL" || 
			listCt[j].CTNHAPKHO_DONGIA == null) {
			dg = 0;
		} else {
			dg = parseFloat(listCt[j].CTNHAPKHO_DONGIA);
		}
		//var qc = parseFloat(listCt[j].CTNHAPKHO_QUYCACH);
		dg = dg + ((dg * thueVAT) / 100);
		//alert("dg: " + dg);
		DonGiaVAT.innerHTML = dg;
		//DonGiaVAT.innerHTML=(listCt[j].TONKHO_DONGIA)*(listCt[j].CTNHAPKHO_QUYCACH);
		
		ThanhTien.align = "right";
		ThanhTien.style.padding = "3px";
		ThanhTien.innerHTML= parseFloat(DonGiaVAT.innerHTML) * parseFloat(listCt[j].CTNHAPKHO_SOLUONG);
		tt = tt + parseInt(ThanhTien.innerHTML);
		
		//document.getElementById(prefix_component + '__sudungvitinh').value = getNameById_Catalog(listCt[j].DTDMNHANVIEN_CN, DtDmNhanVien);
        i++;
	}
	
    document.getElementById(prefix_component + "__khoan").value = i - 1;
    document.getElementById(prefix_component + "__thanhtien").value = tt;    
}

function init() {		    
	//alert("init...");
	try {
		//LoadDB_formMaPhu1("1231316523921");
		//drawTable1("1231316523921");	
		
    	if (opener.document.getElementById('__ma_in').value != 0) {
			LoadDB_formMaPhu1(opener.document.getElementById('__ma_in').value);
			drawTable1(opener.document.getElementById('__ma_in').value);	
		}
		
     } catch (e) {
     	alert("init error: " + e.description);
     }
}