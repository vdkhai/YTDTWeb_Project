function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
        	onCompleteGetInfor();
        	var loaikhambenh = document.getElementById(prefix_component +"_loaikhambenh").value;
        	var strbankham = "getDtDmBanKhamCCL()";
        	if (loaikhambenh == "noccl") {
        		strbankham = "getDtDmBanKhamKoCCL()";
        	} 
        	setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM',strbankham,"","","");
               						
			setAttrForCombobox(prefix_component + "DM_DOI_TUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuongTP()","onSelectDoiTuongChange();","onSelectDoiTuongChange()","");				
			setAttrForCombobox(prefix_component + "DT_DM_KHOI_BHYT_MA","DT_DM_KHOI_BHYT_span","DT_DM_KHOI_BHYT","getDtDmKhoiBhyt()","","","");				
			timer.setTimeout(function(){InitSetInfor();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function onSelectDoiTuongChange()
{
	if((document.getElementById(prefix_component +"DM_DOI_TUONG_MA").value=="BH"))
	{
		
		document.getElementById(prefix_component + "DT_DM_KHOI_BHYT_MA").disabled = false;
		if (document.getElementById("DT_DM_KHOI_BHYT").disabled == true)
		{
			changeDisabledAttr("DT_DM_KHOI_BHYT");  
		}
	}
	else
	{
		
		document.getElementById(prefix_component + "DT_DM_KHOI_BHYT_MA").disabled = true;
		if (document.getElementById("DT_DM_KHOI_BHYT").disabled == false)
		{
			changeDisabledAttr("DT_DM_KHOI_BHYT");  
		}
	}
}


function onChangeSelection()
{
	if((document.getElementById(prefix_component +"__thutuin").value=="theonoidangkyKCBBD"))
	{
		    document.getElementById(prefix_component + "DM_DOI_TUONG_MA").value = "BH";
			document.getElementById(prefix_component + "DM_DOI_TUONG_MA").disabled = true;
			if (document.getElementById( "DM_DOI_TUONG").disabled == false){
        		changeDisabledAttr("DM_DOI_TUONG");  
    		}
			myOnblurTextbox(prefix_component + "DM_DOI_TUONG_MA", 'DM_DOI_TUONG');
	}
	else
	{
		document.getElementById(prefix_component + "DM_DOI_TUONG_MA").disabled = false;
		if (document.getElementById( "DM_DOI_TUONG").disabled == true){
    		changeDisabledAttr("DM_DOI_TUONG");  
		}
		 document.getElementById(prefix_component + "DM_DOI_TUONG_MA").value = "";
		 myOnblurTextbox(prefix_component + "DM_DOI_TUONG_MA", 'DM_DOI_TUONG');
	}
	if((document.getElementById(prefix_component +"__thutuin").value=="theodoituongBHYT"))
	{
		    document.getElementById(prefix_component + "DM_DOI_TUONG_MA").disabled = true;
			if (document.getElementById( "DM_DOI_TUONG").disabled == false){
        		changeDisabledAttr("DM_DOI_TUONG");  
    		}
			document.getElementById(prefix_component + "DM_DOI_TUONG_MA").value="BH";
			myOnblurTextbox(prefix_component + "DM_DOI_TUONG_MA", 'DM_DOI_TUONG');
	}
}

function onCompleteGetInfor(){
	try { 
	
		myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
}

function InitSetInfor()
{
	document.getElementById(prefix_component + "__thang").focus();
	
	var loaikhambenh = document.getElementById(prefix_component +"_loaikhambenh").value;	
	if (loaikhambenh == "ccl") {
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value = "CCL";
	}else{
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value = "";
	}
	//document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value = "CCL";
	myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
	
}


