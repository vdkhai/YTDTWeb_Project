function init() {
	
    if (window.google && google.gears) {
    	
        try {
        		setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span',   'DM_KHOA',"getDmKhoa()","","","");
	 			
	 			setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA1','DT_DM_NHAN_VIEN1_span', 'DT_DM_NHAN_VIEN__1',"getDtDmBacSi()","","","");	
				setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA2','DT_DM_NHAN_VIEN2_span', 'DT_DM_NHAN_VIEN__2',"getDtDmNhanVien()","","","");	
				setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA3','DT_DM_NHAN_VIEN3_span', 'DT_DM_NHAN_VIEN__3',"getDtDmNhanVien()","","","");	
				setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA4','DT_DM_NHAN_VIEN4_span', 'DT_DM_NHAN_VIEN__4',"getDtDmBacSi()","","","");	
	 			
				timer.setTimeout(function(){onCompleteGetInfor();},200); 
				
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}
function onCompleteGetInfor(){

	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA1', 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA2', 'DT_DM_NHAN_VIEN__2');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA3', 'DT_DM_NHAN_VIEN__3');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA4', 'DT_DM_NHAN_VIEN__4');
	
 	document.getElementById(prefix_component + "DM_KHOA_MA").disabled = true; 
//    if (document.getElementById(prefix_component +  "DM_KHOA").disabled == true){
//       	changeDisabledAttr("DM_KHOA");  
//    }
    myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
    
}

initShortcut();
function initShortcut() {
	shortcut.add('Ctrl+Shift+k',function() {document.getElementById(prefix_component + '__ghinhan').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__nhapmoi').click();});	
}
function caculateTotalDocument()
{
	var xquang = document.getElementById(prefix_component + "__xquang").value;
	var scanner = document.getElementById(prefix_component + "__scanner").value;
	var sotosieuam = document.getElementById(prefix_component + "__sotosieuam").value;
//	var sotoxn = document.getElementById(prefix_component + "__sotoxn").value;
	var sotokhac = document.getElementById(prefix_component + "__sotokhac").value;
	
	var isotoxquang =0;
	if(xquang!=null && xquang!="")
		isotoxquang=parseInt(xquang);
		
	var isotoscanner =0;
	if(scanner!=null && scanner!="")
		isotoscanner=parseInt(scanner);
		
	var isotosieuam =0;
	if(sotosieuam!=null && sotosieuam!="")
		isotosieuam=parseInt(sotosieuam);
		
//	var isotoxetnghiem=0;
//	if(sotoxn!=null && sotoxn!="")
//		isotoxetnghiem=parseInt(sotoxn);
		
	var isotokhac=0;
	if(sotokhac!=null && sotokhac!="")
		isotokhac=parseInt(sotokhac);
		
		
//	var total = isotoxquang + isotoscanner +isotosieuam + isotoxetnghiem + isotokhac;
	var total = isotoxquang + isotoscanner +isotosieuam  + isotokhac;
	document.getElementById(prefix_component + "__tongsoto").value = total;
} 