function init() {
	if (window.google && google.gears) {
		try {
			timer = google.gears.factory.create('beta.timer');            
			resetData();
            
            // truong hop ko bhyt thi` ko co'
            try{
	            setAttrForCombobox(prefix_component + "DMNHACUNGCAP_MA","DM_NHA_CUNG_CAP_span","DM_NHA_CUNG_CAP","getDmNhaCungCap()","","","");
	            setAttrForCombobox(prefix_component + "DMNCC_MA","DM_NHA_CUNG_CAP_span__1","DM_NHA_CUNG_CAP__1","getDmNhaCungCap()","","","");
            }catch(e){
            
            }
        	timer.setTimeout(function(){InitSetInfor();},100);
        } catch (e) {
        	alert("error in init(): " + e.description);
        }
    }
}

function resetPLThuoc(){
	document.getElementById(prefix_component +"DM_PHAN_LOAI_THUOC"+'comboboxField').value = "";
	document.getElementById(prefix_component +"PHANLOAI_MA").value = "";
}

function resetData(){				
	document.getElementById(prefix_component +"DM_LOAI_THUOC"+'comboboxField').value = "";
	document.getElementById(prefix_component +"LOAIPHIEU_MA").value = "";
	
	resetPLThuoc();
	document.getElementById(prefix_component +"DMKHO_MA").value = "";
	document.getElementById(prefix_component +"DMKHO_MASO").value = "";				
	document.getElementById(prefix_component +"DM_KHO"+'comboboxField').value = "";
	
	document.getElementById(prefix_component + "__chitietnhaphang:0").checked = true;
	document.getElementById(prefix_component + "coTra").value = false;
	document.getElementById(prefix_component + "coTraNCC").value = false;
	//TRA
	document.getElementById(prefix_component +"DMKHOATRA_MASO").value = "";
	document.getElementById(prefix_component +"DMKHOATRA_MA").value = "";
	document.getElementById(prefix_component +"DM_KHOATRA"+'comboboxField').value = "";
	//NHAN
	document.getElementById(prefix_component +"DMKHOA_MASO").value = "";
	document.getElementById(prefix_component +"DMKHOA_MA").value = "";
	document.getElementById(prefix_component +"DM_KHOA"+'comboboxField').value = "";								
}

function InitSetInfor(){
	document.getElementById(prefix_component + "__thang").focus();
	if (document.getElementById(prefix_component + "nhapXuatHang").value == "1") {
		document.getElementById(prefix_component + "__chitietnhaphang:1").checked = true;
	} else {
		document.getElementById(prefix_component + "__chitietnhaphang:0").checked = true;
	}
	
	if(document.getElementById(prefix_component + "DMKHO_MA").value == ""){
		document.getElementById("DMKHOA_kho").style.display = "none";	
		document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
		document.getElementById("DMKHOA_TRA_div").style.display = "none";
		document.getElementById("DMKHOA_TRA").style.display = "none";
	}
	document.getElementById(prefix_component + "coTra").value = false;
	if (document.getElementById(prefix_component + "coTra").value == false) {
		document.getElementById(prefix_component + "isTraKho").checked = false;
	} else {
		document.getElementById(prefix_component + "isTraKho").checked = true;
	}
	document.getElementById(prefix_component + "coTraNCC").value = false;
	if (document.getElementById(prefix_component + "coTraNCC").value == false) {
		document.getElementById(prefix_component + "isTraNCC").checked = false;
	} else {
		document.getElementById(prefix_component + "isTraNCC").checked = true;
	}
}

function onchangeDMKho(){
	var KhoMa = (document.getElementById(prefix_component + "DMKHO_MA").value).toUpperCase();
	if (document.getElementById(prefix_component + "__chitietnhaphang:0").checked) {
		loaiChitiet = "0";
		document.getElementById(prefix_component + "nhapXuatHang").value = '0';
	} else {
		loaiChitiet = "1";
		document.getElementById(prefix_component + "nhapXuatHang").value = '1';
	}
	
	if(KhoMa == ""){
		document.getElementById("DMKHOA_kho").style.display = "none";	
		document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
		document.getElementById("DMKHOA_TRA_div").style.display = "none";
		document.getElementById("DMNCC_XUAT").style.display = "none";
	}else if(KhoMa == "KCH"){
		document.getElementById(prefix_component + "DM_KHO_XUAT").value = "KCH";
			
		if(loaiChitiet == '0'){
			document.getElementById("DMKHOA_kho").style.display = "none";	
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "block";
			document.getElementById("DMKHOA_TRA_div").style.display = "block";
			document.getElementById("DMKHOA_TRA").style.display = "none";	
			document.getElementById("DMNCC_XUAT").style.display = "none";
		}else{
			document.getElementById("DMKHOA_kho").style.display = "block";
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
			document.getElementById("DMKHOA_TRA_div").style.display = "none";
			document.getElementById("DMNCC_XUAT").style.display = "block";			
		}
	}else if(KhoMa == "KNT"){
		document.getElementById(prefix_component + "DM_KHO_XUAT").value = "KNT";
		document.getElementById(prefix_component + "DMNHACUNGCAP_MA").value = "";
		myOnblurTextbox(prefix_component + "DMNHACUNGCAP_MA", "DM_NHA_CUNG_CAP");
		document.getElementById(prefix_component + "DMNCC_MA").value = "";
		myOnblurTextbox(prefix_component + "DMNCC_MA", "DM_NHA_CUNG_CAP__1");
		if(loaiChitiet == '0'){
			document.getElementById("DMKHOA_kho").style.display = "none";	
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
			document.getElementById("DMKHOA_TRA_div").style.display = "block";	
			document.getElementById("DMKHOA_TRA").style.display = "none";
			document.getElementById("DMNCC_XUAT").style.display = "none";
		}else{
			document.getElementById("DMKHOA_kho").style.display = "block";
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
			document.getElementById("DMKHOA_TRA_div").style.display = "none";
			document.getElementById("DMNCC_XUAT").style.display = "none";
		}
	}else if(KhoMa == "KTE"){
		document.getElementById(prefix_component + "DM_KHO_XUAT").value = "KTE";
		document.getElementById(prefix_component + "DMNHACUNGCAP_MA").value = "";
		myOnblurTextbox(prefix_component + "DMNHACUNGCAP_MA", "DM_NHA_CUNG_CAP");
		document.getElementById(prefix_component + "DMNCC_MA").value = "";
		myOnblurTextbox(prefix_component + "DMNCC_MA", "DM_NHA_CUNG_CAP__1");
		if(loaiChitiet == '0'){
			document.getElementById("DMKHOA_kho").style.display = "none";	
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
			document.getElementById("DMKHOA_TRA_div").style.display = "none";	
			document.getElementById("DMKHOA_TRA").style.display = "none";
			document.getElementById("DMNCC_XUAT").style.display = "none";
		}else{
			document.getElementById("DMKHOA_kho").style.display = "none";
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
			document.getElementById("DMKHOA_TRA_div").style.display = "none";
			document.getElementById("DMNCC_XUAT").style.display = "none";
		}
	}else if(KhoMa == "KBH"){
		document.getElementById(prefix_component + "DM_KHO_XUAT").value = "KBH";
		document.getElementById(prefix_component + "DMNHACUNGCAP_MA").value = "";
		myOnblurTextbox(prefix_component + "DMNHACUNGCAP_MA", "DM_NHA_CUNG_CAP");
		document.getElementById(prefix_component + "DMNCC_MA").value = "";
		myOnblurTextbox(prefix_component + "DMNCC_MA", "DM_NHA_CUNG_CAP__1");
		if(loaiChitiet == '0'){
			document.getElementById("DMKHOA_kho").style.display = "none";	
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
			document.getElementById("DMKHOA_TRA_div").style.display = "none";	
			document.getElementById("DMKHOA_TRA").style.display = "none";
			document.getElementById("DMNCC_XUAT").style.display = "none";
		}else{
			document.getElementById("DMKHOA_kho").style.display = "none";
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
			document.getElementById("DMKHOA_TRA_div").style.display = "none";
			document.getElementById("DMNCC_XUAT").style.display = "none";
		}
	}
	
	document.getElementById(prefix_component + "coTra").value = false;
	document.getElementById(prefix_component + "isTraKho").checked = false;
	enableTraKho(false);
	
	document.getElementById(prefix_component + "coTraNCC").value = false;
	document.getElementById(prefix_component + "isTraNCC").checked = false;
	enableTraNCC(false);
}

function disableHelp(loaiChitiet){	
	if (document.getElementById(prefix_component + "__chitietnhaphang:0").checked) {
		document.getElementById(prefix_component + "nhapXuatHang").value = '0';
	} else {
		document.getElementById(prefix_component + "nhapXuatHang").value = '1';
	}
	if(loaiChitiet == '0'){
		try{
			document.getElementById("DMKHOA_kho").style.display = "none";	
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "block";
			document.getElementById("DMKHOA_TRA_div").style.display = "block";
			document.getElementById("DMKHOA_TRA").style.display = "none";
   		}catch(e){
   		}	
	}
	else if(loaiChitiet == '1'){
		try{
			document.getElementById("DMKHOA_kho").style.display = "block";	
			document.getElementById("DMNHACUNGCAP_khochinh").style.display = "none";
			document.getElementById("DMKHOA_TRA_div").style.display = "none";
		}catch(e){
		}
	}
	onchangeDMKho();	
}
function enableTraKho(cotra){
	document.getElementById(prefix_component + "coTra").value = cotra;
	if (cotra){
		document.getElementById("DMKHOA_TRA").style.display = "block";
	}else{	
		document.getElementById("DMKHOA_TRA").style.display = "none";
	}
}

function enableTraNCC(cotra){
	document.getElementById(prefix_component + "coTraNCC").value = cotra;
	if (cotra){
		document.getElementById("DMNCC_TRA").style.display = "block";
	}else{	
		document.getElementById("DMNCC_TRA").style.display = "none";
	}
}
