function init() {
    //alert(1);
	if (window.google && google.gears) {
	
            //---Load DB and set atrribute for combobox---
            setAttrForCombobox(prefix_component + "DM_KINH_PHI_MASO","DM_KINH_PHI_span", "DM_NGUON_KINH_PHI", "getDmNguonKinhPhi()", "", "", "");
            setAttrForCombobox(prefix_component + "DT_DM_NGUON_MA", "DT_DM_NGUON_span", "DM_NGUON_CHUONG_TRINH", "getDmNguonChuongTrinh()", "", "", "");
             
            //Thuc hien phim tat giup user thuan tien add thuoc hay chon thuoc
            initshorcut();
            
          	//setAttrForCombobox(prefix_component + "__listtonkho_duocpham_ma", "__listtonkho_span", "__listtonkho_duocpham", "getTonkho()", "", "", "");
          	setTimeout(function() {
          		setValueOnLoad();
          	}, 100);
		//} catch (e) {
		//	alert("init() error: " + e.description);
		//}
	}
	
}

function initshorcut(){
    shortcut.add("Ctrl+S", function() {
        document.getElementById(prefix_component + "DT_DM_LOAI_MA").focus();
    },{'type':'keydown',"propagate":false,'target':document});

    shortcut.add("Ctrl+T", function() {
    	document.getElementById(prefix_component + "__tonTT").focus();
    },{'type':'keydown',"propagate":false,'target':document});
}

function setValueOnLoad(){
	document.getElementById(prefix_component + "DT_DM_LOAI_MA").focus();
	
		myOnblurTextbox(prefix_component + "DM_KINH_PHI_MASO", "DM_NGUON_KINH_PHI");
		myOnblurTextbox(prefix_component + "DT_DM_NGUON_MA", "DM_NGUON_CHUONG_TRINH");
}

function onCompleteGetInfor() {
	try {
		
		myOnblurTextbox(prefix_component + "DM_KINH_PHI_MASO", "DM_NGUON_KINH_PHI");
		myOnblurTextbox(prefix_component + "DT_DM_NGUON_MA", "DM_NGUON_CHUONG_TRINH");
	} catch (e) {
		alert("onCompleteGetInfor error: " + e.description);
	}
}


function lockUnlockControl(form, type) {
 	var elem = form.elements; 
 	for (var i = 0; i < elem.length; i++) {  
  		var strID = elem[i].id;
  		//var ten = elem[i].name;
  		var strType = elem[i].type.toLowerCase();
  		if(strID!=''){ //Bat loi khi co dojo
   			if (type == 1) {
    			if (strType == 'text' || strType == 'textarea') {
     				try{
      					document.getElementById(strID).disabled = true;
      					dijit.byId(strID).disabled = true;
     				} catch(e) {
      					document.getElementById(strID).disabled = false;
      					document.getElementById(strID).readOnly = true;
     				}        
     
    			} else if (strType == 'checkbox' || strType == 'radio' || strType == 'select-one') {
     				document.getElementById(strID).disabled = true;
    			}
   			} else if (type == 0){
    			
     				if (strType == 'text' || strType == 'textarea') {
      
      					try{
       						document.getElementById(strID).disabled = false;
       						dijit.byId(strID).disabled = false;
      					} catch(e) {
       						document.getElementById(strID).readOnly = false;
      					}        
      
     				} else if (strType == 'checkbox' || strType == 'radio' || strType == 'select-one') {
      					document.getElementById(strID).disabled = false;
     				} 
   			}
  		}
 	} 
}



