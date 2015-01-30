var C_KHONG = "không";	
var C_MOT = "một";
var C_HAI = "hai";
var C_BA = "ba";
var C_BON = "bốn";
var C_NAM = "năm";
var C_SAU = "sáu";
var C_BAY = "bảy";
var C_TAM = "tám";
var C_CHIN = "chín";
var C_MUOI = "mươi";
var C_MUOI_HUYEN = "mười";
var C_TRAM = "trăm";
var C_LE = "lẻ";		
var C_NGHIN = "nghìn";
var C_TRIEU = "triệu";
var C_TY = "tỷ";
			
			

	function trimLeftZeros(num) {
		try {
			return "" + parseFloat(num);//1234567891234567
		} catch(e) {
			alert("Vuot qua gioi han kieu long, hay khong phai kieu so");
		}
	}
	function readNumNotLess1000(num){
		/*
		num = num.replace(/[.]/g, "");
   		num = num.replace(",", ".");
   		*/
   		num = num.replace(/[,]/g, "");
		var list = phanDoan(num, 3);
		var rs = "";
		var str = "";
		
		for(var i = list.length - 1; i >= 0; i --) {
			
			str = readNumLess1000("" + list[i]);
			if (str.length > 0) {
				if (rs.length > 0){
					rs += " ";
				}
				if (i == 0) {
					if (str.indexOf(C_TRAM) < 0) {
						rs += C_KHONG + " " + C_TRAM + " " + readUnitLess1000(str, i);
					} else { 
						rs += readUnitLess1000(str, i);
					}	
				} else {
					rs += readUnitLess1000(str, i);
				}	
			} else {
				if (i == 3 && rs.length > 0){
					rs += " " + C_TY;
				}
			}
		} 
		rs = rs.substring(0,1).toUpperCase() + rs.substring(1,rs.length);
		return rs;
	}
	function readNumLess1000(num){
		var list = phanDoan(num, 1);
		var rs = "";
		var str = "";
		
		for(var i = list.length - 1; i >= 0; i --) {
			
			str = readNumLess10("" + list[i]);
			if (str.length > 0) {
				if (rs.length > 0){
					rs += " ";
					if (i == 0) {
						if(rs.indexOf(C_MUOI) < 0 && rs.indexOf(C_MUOI_HUYEN) < 0) {
							rs += C_LE + " ";
						}
					}
				}
				
				rs += readUnitLess10(str, i);
			}
		} 
		return rs;
	}
	function phanDoan(num, soPhantu){
		var list = new Array();
		var m = num.length;
		
		var n = m - soPhantu;
		while (n > -soPhantu) {
			list.push(num.substring(n , m));
			m = n;
			n -= soPhantu;
			if (n > -soPhantu && n < 0) n = 0;
		}
		return list;
	}
	
	function readNumLess10(num){
		var n = parseInt(num);
		switch (n) {
		case 0: return "";
		case 1: return C_MOT;
		case 2: return C_HAI;
		case 3: return C_BA;
		case 4: return C_BON;
		case 5: return C_NAM;
		case 6: return C_SAU;
		case 7: return C_BAY;
		case 8: return C_TAM;
		case 9: return C_CHIN;
		
		}
		return "";
	}
	function readUnitLess10(numName, at) {
		if (numName == "") return "";
		switch(at) {
			case 0: return numName;
			case 1: {
				if (numName == C_MOT) return C_MUOI_HUYEN;
				return numName + " " + C_MUOI;
			}
			case 2: return numName + " " + C_TRAM;
			default: return "";
		}
	}
	function readUnitLess1000(numName, at) {
		if (numName == "") return "";
		switch(at) {
			case 0: return numName;
			case 1: {				
				return numName + " " + C_NGHIN;
			}
			case 2: return numName + " " + C_TRIEU;
			case 3: return numName + " " + C_TY;
			case 4: return numName + " " + C_NGHIN;
			case 5: return numName + " " + C_TRIEU;
			default: return "";
		}
	}

