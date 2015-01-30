        
function init() {
	
    if (window.google && google.gears) {
    	
        try {
                setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM',"getDtDmBanKham()","","","");
                setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmBacSi()","","","");
                caculateTotalDocument();
                timer.setTimeout(function(){setOnload();},1000);         	            							  
        } catch (e) {
           alert("error at init");
        }
    }
    
}
function onCompleteGetInfor(){
	try { 
		//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
}
function setOnload(){


  myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
  myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	
  
 var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	//alert(donViTuoi);
	if (donViTuoi == "1"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}else if (donViTuoi == "2"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	}else if (donViTuoi == "3"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
		//alert("3");
	}else{
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}
		
}

function caculateTotalDocument()
{
	var xquang = document.getElementById(prefix_component + "__xquang").value;
	var scanner = document.getElementById(prefix_component + "__scanner").value;
	var sotosieuam = document.getElementById(prefix_component + "__sotosieuam").value;
	var sotoxn = document.getElementById(prefix_component + "__sotoxn").value;
	var sotokhac = document.getElementById(prefix_component + "__sotokhac").value;
	
	var isotoxquang =0;
	if(xquang!=null && xquang!="")
		isotoxquang=parseInt(xquang,10);
		
	var isotoscanner =0;
	if(scanner!=null && scanner!="")
		isotoscanner=parseInt(scanner,10);
		
	var isotosieuam =0;
	if(sotosieuam!=null && sotosieuam!="")
		isotosieuam=parseInt(sotosieuam,10);
		
	var isotoxetnghiem=0;
	if(sotoxn!=null && sotoxn!="")
		isotoxetnghiem=parseInt(sotoxn,10);
		
	var isotokhac=0;
	if(sotokhac!=null && sotokhac!="")
		isotokhac=parseInt(sotokhac,10);
		
		
	var total = isotoxquang + isotoscanner +isotosieuam + isotoxetnghiem + isotokhac;
	document.getElementById(prefix_component + "__tongsoto").value = total;
} 
