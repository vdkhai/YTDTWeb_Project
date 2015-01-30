function iesvn_isBelongToRangeChars(str,rangeChars){

	var result = true;
	var ch = '';
	

	for (var i = 0; i < str.length; i++) {
		ch = str.slice(i,i+1);
	
	    if (rangeChars.indexOf(ch) == -1) {
			result = false;
		}
		
		if(!result) break;
	}
	return result;	
}




function iesvn_ValidateBelongToSpecialChar(form) {
    var isValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    obj = new iesvn_InputValidateBelongToSpecialChars();
    for (x in obj) {
        var field = form[obj[x][0]];
        
        if (field.type == 'text' ||
            field.type == 'textarea') {
            
            val = field.value;
            if (val != '') {
            
	            var specialChars = obj[x][2];

	            if (!iesvn_isBelongToRangeChars(val,specialChars)) {
	                if (i == 0) {
	                    focusField = field;
	                }
	                fields[i++] = obj[x][1];
	                isValid = false;
	            }
            
            }
        }
    }
    if (fields.length > 0) {
       try{
           focusField.focus();
       }catch(e){}    
       alert(fields.join('\n'));
    }
    return isValid;
}  

function iesvn_NotExistSpace(str) {
	if(str.indexOf(" ") >= 0) return false;
	return true;
}
function iesvn_CheckISSN(str) {
  var hyphenNum = 0;
  var hyphenIndex = 4;
  var len = str.length;
  if(iesvn_Trim(str)=='') return true;
   if(str.charAt(hyphenIndex) != '-'){
    return false;
  }
	for(i = 0; i < len; i++) {		
		if ((str.charAt(i) < '0' || str.charAt(i) > '9') && str.charAt(i) != '-') {		
			return false;
		}else{
      if (str.charAt(i) == '-') {		
        hyphenNum++;
      }
    }	
	}
  if(hyphenNum != 1) {
    return false;
  }  
  return true;
}


function iesvn_IsHTTP(str) {
	str = iesvn_Trim(str);
	var len = str.length;
	if(len < 8) return false;
	if(str.toUpperCase().indexOf('HTTP://') < 0) return false;
	return true;
}  
function iesvn_IsNum(str) {
	var len = str.length;
	for(i = 0; i < len; i++) {		
		if (str.charAt(i) < '0' || str.charAt(i) > '9') {		
			return false;
		}	
	}
	return true;
} 
//incremental  the row of numbers
function iesvn_CheckIncrNumsRow(str) {
	var delim = '-';
	var at = str.indexOf(delim);
	if( at == 0 || at == str.length -1) {
		return false;
	}
	var splitArray = str.split(delim);
	if(splitArray.length > 2) {
		return false;
	}
	if(splitArray.length == 2) {
		if(iesvn_IsAllDigits(splitArray[0]) == false || iesvn_IsAllDigits(splitArray[1]) == false) {
			return false;
		}
		if(parseInt(splitArray[0]) >=  parseInt(splitArray[1])) {
			return false;
		}
		if(parseInt(splitArray[0]) == 0){
			return false;
		}
	}else if(splitArray.length == 1) {
		if(iesvn_IsAllDigits(splitArray[0]) == false) {
			return false;
		}
		if(parseInt(splitArray[0]) == 0){
			return false;
		}
	}
	return true;
} 
function iesvn_IsTelephone(str) {
	var valid = true;
	var len = str.length;
	for(i = 0; i < len; i++) {		
		if (str.charAt(i) < '0' || str.charAt(i) > '9' ) {		
			valid = false;
		}
		if(! valid){
			// if str.charAt(i) == . ,or == - ,or  == ( ,or == ) then set valid again to true
			if(str.charAt(i) != '.' && str.charAt(i) != '-' && str.charAt(i) != '(' && str.charAt(i) != ')'){
				valid = false;
			} else{
				valid = true;
			}
		}	
		if(! valid) return false;
	}
	return true;
}   
function iesvn_IsAllDigits(argvalue) {
    argvalue = argvalue.toString();
    var validChars = "0123456789";
    var startFrom = 0;
    /*
    if (argvalue.substring(0, 2) == "0x") {
       validChars = "0123456789abcdefABCDEF";
       startFrom = 2;
    } else if (argvalue.charAt(0) == "0") {
       validChars = "01234567";
       startFrom = 1;
    } else if (argvalue.charAt(0) == "-") {
        startFrom = 1;
    }
    */
    for (var n = startFrom; n < argvalue.length; n++) {
        if (validChars.indexOf(argvalue.substring(n, n+1)) == -1) return false;
    }
    return true;
}
function iesvn_IsDigitsOrAlphabet(str) {
  	var reg = /\w/;
	var valid = true;
	for (var i = 0; i < str.length; i++) {
  		if (!reg.test(str.substring(i, i+1))) {
 			valid = false;
		}
	}
	return valid;
	
}
function iesvn_ValidateNotExistSpace(form) {
    	var bValid = true;
      var focusField = null;
      var i = 0;
      var fields = new Array();
      oList = new iesvn_NotExistSpaceFields();
      for (x in oList) {
        var field = form[oList[x][0]];		
        if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio' || field.type == 'password') {
          var value;
          // get field's value
          if (field.type == "select-one") {
            var si = field.selectedIndex;
            value = field.options[si].value;
          } else {
            value = field.value;
          }
          if (! iesvn_NotExistSpace(value)) {
            if (i == 0) {
              focusField = field;
            }
            fields[i++] = oList[x][1];
            bValid = false;
          }
        }
      }
      if (fields.length > 0) {
        try{
           focusField.focus();
        }catch(e){}    
        alert( fields.join('\n'));
      }
        
      return bValid;
  }

function iesvn_ValidateHTTP(form) {
	var bValid = true;
      var focusField = null;
      var i = 0;
      var fields = new Array();
      oList = new iesvn_HTTP();
      for (x in oList) {
        var field = form[oList[x][0]];		
        if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio' || field.type == 'password') {
          var value;
          // get field's value
          if (field.type == "select-one") {
            var si = field.selectedIndex;
            value = field.options[si].value;
          } else {
            value = field.value;
          }
          if (! iesvn_IsHTTP(value)) {
            if (i == 0) {
              focusField = field;
            }
            fields[i++] = oList[x][1];
            bValid = false;
          }
        }
      }
      if (fields.length > 0) {
        try{
           focusField.focus();
        }catch(e){}    
        alert( fields.join('\n'));
      }
        
      return bValid;
}

function iesvn_ValidateComparedNumbers(form) {
    	var bValid = true;
      var focusField = null;
      var i = 0;
      var fields = new Array();
      oList = new iesvn_comparedNumbers();
      for (x in oList) {
	    var fromId = oList[x][0][0];
		var toId = oList[x][0][1];
          if (iesvn_compareNumber(form[fromId].value, form[toId].value) > 0) {
            fields[i++] = oList[x][1];
            bValid = false;
          }
      }
      if (fields.length > 0) {
        alert( fields.join('\n'));
      }
        
      return bValid;
  }  
function iesvn_ValidateKeyValue(form) {
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oList = new iesvn_KeyValue();
    for (x in oList) {
	   var key = oList[x][0][0];
	   var val = oList[x][0][1];
       if (iesvn_Trim(form[key].value) == "" || iesvn_Trim(form[val].value) == "") {
            fields[i++] = oList[x][1];
            bValid = false;
        }
      }
      if (fields.length > 0) {
        alert( fields.join('\n'));
      }
        
      return bValid;
 }    
function iesvn_ValidateISSN(form) {
    	var bValid = true;
      var focusField = null;
      var i = 0;
      var fields = new Array();
      oList = new iesvn_ISSN();
      for (x in oList) {
        var field = form[oList[x][0]];		
        if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio' || field.type == 'password') {
          var value;
          // get field's value
          if (field.type == "select-one") {
            var si = field.selectedIndex;
            value = field.options[si].value;
          } else {
            value = field.value;
          }
          if (! iesvn_CheckISSN(value)) {
            if (i == 0) {
              focusField = field;
            }
            fields[i++] = oList[x][1];
            bValid = false;
          }
        }
      }
      if (fields.length > 0) {
        try{
           focusField.focus();
       }catch(e){}    
        alert( fields.join('\n'));
      }
        
      return bValid;
  }
function iesvn_ValidateNum(form) {
	return iesvn_CheckNum(form);
}  
/**contains all field (name or id)  need to check number
*/
function iesvn_CheckNum(form) {
    	var bValid = true;
      var focusField = null;
      var i = 0;
      var fields = new Array();
      oList = new iesvn_Nums();
      for (x in oList) {
        var field = form[oList[x][0]];		
        if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio' || field.type == 'password') {
          var value;
          // get field's value
          if (field.type == "select-one") {
            var si = field.selectedIndex;
            value = field.options[si].value;
          } else {
            value = field.value;
          }
          if (! iesvn_IsNum(value)) {
            if (i == 0) {
              focusField = field;
            }
            fields[i++] = oList[x][1];
            bValid = false;
          }
        }
      }
      if (fields.length > 0) {
        try{
           focusField.focus();
        }catch(e){}    
        alert( fields.join('\n'));
      }
        
      return bValid;
}
function iesvn_CheckDate(form,fields,i,focusFields,value,datePattern,bValid){
 var MONTH = "MM";
   var DAY = "dd";
   var YEAR = "yyyy";
   var orderMonth = datePattern.indexOf(MONTH);
   var orderDay = datePattern.indexOf(DAY);
   var orderYear = datePattern.indexOf(YEAR);
   var focusField = null;
   if ((orderDay < orderYear && orderDay > orderMonth)) {
       var iDelim1 = orderMonth + MONTH.length;
       var iDelim2 = orderDay + DAY.length;
       var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
       var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
       if (iDelim1 == orderDay && iDelim2 == orderYear) {
       	 //var dateRegexp; 
          dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
       } else if (iDelim1 == orderDay) {
          dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
       } else if (iDelim2 == orderYear) {
          dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
       } else {
          dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
       }
       var matched = dateRegexp.exec(value);
       if(matched != null) {
          if (!iesvn_IsValidDate(matched[2], matched[1], matched[3])) {
             if (i == 0) {
                 focusField = form[oDate[x][0]];
             }
             fields[i++] = oDate[x][1];
             bValid =  false;
          }
       } else {
          if (i == 0) {
              focusField = form[oDate[x][0]];
          }
          fields[i++] = oDate[x][1];
          bValid =  false;
       }
   } else if ((orderMonth < orderYear && orderMonth > orderDay)) {
       var iDelim1 = orderDay + DAY.length;
       var iDelim2 = orderMonth + MONTH.length;
       var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
       var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
       if (iDelim1 == orderMonth && iDelim2 == orderYear) {
           dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
       } else if (iDelim1 == orderMonth) {
           dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
       } else if (iDelim2 == orderYear) {
           dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
       } else {
           dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
       }
       var matched = dateRegexp.exec(value);
       if(matched != null) {
           if (!iesvn_IsValidDate(matched[1], matched[2], matched[3])) {
               if (i == 0) {
                   focusField = form[oDate[x][0]];
               }
               fields[i++] = oDate[x][1];
               bValid =  false;
            }
       } else {
           if (i == 0) {
               focusField = form[oDate[x][0]];
           }
           fields[i++] = oDate[x][1];
           bValid =  false;
       }
   } else if ((orderMonth > orderYear && orderMonth < orderDay)) {
       var iDelim1 = orderYear + YEAR.length;
       var iDelim2 = orderMonth + MONTH.length;
       var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
       var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
       if (iDelim1 == orderMonth && iDelim2 == orderDay) {
           dateRegexp = new RegExp("^(\\d{4})(\\d{2})(\\d{2})$");
       } else if (iDelim1 == orderMonth) {
           dateRegexp = new RegExp("^(\\d{4})(\\d{2})[" + delim2 + "](\\d{2})$");
       } else if (iDelim2 == orderDay) {
           dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})(\\d{2})$");
       } else {
           dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{2})$");
       }
       var matched = dateRegexp.exec(value);
       if(matched != null) {
           if (!iesvn_IsValidDate(matched[3], matched[2], matched[1])) {
               if (i == 0) {
                   focusField = form[oDate[x][0]];
                }
                fields[i++] = oDate[x][1];
                bValid =  false;
            }
        } else {
            if (i == 0) {
                focusField = form[oDate[x][0]];
            }
            fields[i++] = oDate[x][1];
            bValid =  false;
        }
   } else {
       if (i == 0) {
           focusField = form[oDate[x][0]];
       }
       fields[i++] = oDate[x][1];
       bValid =  false;
   }
   if(focusField  != null) {
   	focusFields[0] = focusField;
   }
   return bValid;
}
function iesvn_ValidateTelephone(form) {
	var bValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oList = new iesvn_Telephone();
	for (x in oList) {
		var field = form[oList[x][0]];		
		if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio' || field.type == 'password') {
			var value;
			// get field's value
			if (field.type == "select-one") {
				var si = field.selectedIndex;
				if(si >= 0) {
					value = field.options[si].value;
				}else{
					value = '';
				}	
			} else {
				value = field.value;
			}
			if (iesvn_IsTelephone(value) == '') {
				if (i == 0) {
					focusField = field;
				}
				fields[i++] = oList[x][1];
				bValid = false;
			}
		}
	}
	if (fields.length > 0) {
       try{
           focusField.focus();
       }catch(e){}    
		alert( fields.join('\n'));
	}
		
	return bValid;
}  

function iesvn_ValidateIncrNumsRow(form) {
	var bValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oList = new iesvn_IncrNumsRow();
	for (x in oList) {
		var field = form[oList[x][0]];		
		if (field.type == 'hidden' || field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' 
			|| field.type == 'radio' || field.type == 'password' || field.type == 'file') {
			var value;
			// get field's value
			if (field.type == "select-one") {
				var si = field.selectedIndex;
				if(si >= 0) {
					value = field.options[si].value;
				}else{
					value = '';
				}	
			} else {
				value = field.value;
			}
			if (iesvn_CheckIncrNumsRow(value)) {
				if (i == 0) {
					focusField = field;
				}
				fields[i++] = oList[x][1];
				bValid = false;
			}
		}

	}
	if (fields.length > 0) {
		if (focusField.type != 'hidden'){
		   try{
              focusField.focus();
           }catch(e){}    
		}
		alert( fields.join('\n'));
	}
		
	return bValid;
}
/**oRequired  contains all field (name or id)  required
*/
function iesvn_ValidateRequired(form) {
	var bValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oRequired = new iesvn_Required();
	for (x in oRequired) {
		var field = form[oRequired[x][0]];		
		if (field.type == 'hidden' || field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' 
			|| field.type == 'radio' || field.type == 'password' || field.type == 'file') {
			var value;
			// get field's value
			if (field.type == "select-one") {
				var si = field.selectedIndex;
				if(si >= 0) {
					value = field.options[si].value;
				}else{
					value = '';
				}	
			} else {
				value = field.value;
			}
			if (iesvn_Trim(value) == '' || value == '0') {
				if (i == 0) {
					focusField = field;
				}
				fields[i++] = oRequired[x][1];
				bValid = false;
			}
		}
		if (field.type == 'select-multiple'){
			if(field.length == 0) {
				if (i == 0) {
					focusField = field;
				}
				fields[i++] = oRequired[x][1];
				bValid = false;
			}	
		}
	}
	if (fields.length > 0) {
		if (focusField.type != 'hidden'){
		  try{
              focusField.focus();
	       }catch(e){}    
		}
		alert( fields.join('\n'));
	}
		
	return bValid;
}
function iesvn_ValidateMaxLength(form) {
    var isValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oMaxLength = new iesvn_Maxlength();
    for (x in oMaxLength) {
        var field = form[oMaxLength[x][0]];
        
        if (field.type == 'text' ||
            field.type == 'textarea') {
            
            var iMax = parseInt(oMaxLength[x][2]("maxlength"));
            if (field.value.length > iMax) {
                if (i == 0) {
                    focusField = field;
                }
                fields[i++] = oMaxLength[x][1];
                isValid = false;
            }
        }
    }
    if (fields.length > 0) {
       try{
           focusField.focus();
       }catch(e){}    
       alert(fields.join('\n'));
    }
    return isValid;
}  
function iesvn_ValidateStringDate(form) {
     var bValid = true;
     var focusField = null;
     var i = 0;
     var fields = new Array();
     var focusFields = new Array();
     oDate = new iesvn_StringDateValidations();
     for (x in oDate) {
         var value = form[oDate[x][0]].value;
         var n = 0;
         for(var j = 0; j < value.length; j++) {
         	if(value.substring(j, j+1) == '/') {
         		n = n +1;
         	}
         }
         if(n <= 2) {
	         if(value.length > 0) {
		         if(n ==0) {
		         	value = "01" + "/" + "01" + "/" + value;
		         }else if(n == 1){
		         	if(value.indexOf('/') != 0 ) {
		         		value = "01" + "/" + value;
		         	}
		         }else {
			        
		         }
		         if(value.length > 10 || value.indexOf('/') == 0) {
		         	if (i == 0) {
		                 focusField = form[oDate[x][0]];
		             }
		             fields[i++] = oDate[x][1];
		             bValid =  false;
		         }
	         }
	     }else{
	     	 if (i == 0) {
                 focusField = form[oDate[x][0]];
             }
             fields[i++] = oDate[x][1];
             bValid =  false;
	     }    
	     if(bValid) {
	         var datePattern = oDate[x][2]("datePatternStrict");
	         if ((form[oDate[x][0]].type == 'text' ||
	              form[oDate[x][0]].type == 'textarea') &&
	             (value.length > 0) &&
	             (datePattern.length > 0)) {
	          bValid = iesvn_CheckDate(form,fields,fields.length,focusFields,value,datePattern,bValid);
	        }
	      } else {
		      
	      } 
	 }
     if (fields.length > 0) {
     	if(focusField == null) {
	     	if(focusFields.length > 0) {
	     		focusField = focusFields[0];
		    }
	    }    
	    if(focusField != null) {
		   try{
              focusField.focus();
           }catch(e){}    
		}	
        alert(fields.join('\n'));
     }
     return bValid;
}
function iesvn_ValidateDate(form) {
     var bValid = true;
     var focusField = null;
     var i = -1;
     var fields = new Array();
     var focusFields = new Array();
     oDate = new iesvn_DateValidations();
     for (x in oDate) {
         var value = form[oDate[x][0]].value;
         var datePattern = oDate[x][2]("datePatternStrict");
         if ((form[oDate[x][0]].type == 'hidden' ||
         		form[oDate[x][0]].type == 'text' ||
              form[oDate[x][0]].type == 'textarea') &&
             (value.length > 0) &&
             (datePattern.length > 0)) {
          bValid = iesvn_CheckDate(form,fields,fields.length,focusFields,value,datePattern,bValid);
        }
     }
     if (fields.length > 0) {
     	if(focusFields.length > 0) {
     		focusField = focusFields[0];
	       try{
               focusField.focus();
           }catch(e){}    
        }
        alert(fields.join('\n'));
     }
     return bValid;
}

function iesvn_IsValidDate(day, month, year) {
  if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) &&
            (day == 31)) {
            return false;
        }
        if (month == 2) {
            var leap = (year % 4 == 0 &&
                       (year % 100 != 0 || year % 400 == 0));
            if (day>29 || (day == 29 && !leap)) {
                return false;
            }
        }
        return true;
    }  

function iesvn_ValidateEmail(form) {
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oEmail = new iesvn_Email();
    for (x in oEmail) {
        if ((form[oEmail[x][0]].type == 'text' ||
             form[oEmail[x][0]].type == 'textarea') &&
            (form[oEmail[x][0]].value.length > 0)) {
            if (! iesvn_CheckEmail(form[oEmail[x][0]].value)) {
                if (i == 0) {
                    focusField = form[oEmail[x][0]];
                }
                fields[i++] = oEmail[x][1];
                bValid = false;
            }
        }
    }
    if (fields.length > 0) {
       try{
           focusField.focus();
       }catch(e){}    
        alert(fields.join('\n'));
    }
    return bValid;
}

/**
 * Reference: Sandeep V. Tamhankar (stamhankar@hotmail.com),
 * http://javascript.internet.com
 */
function iesvn_CheckEmail(emailStr) {
   if (emailStr.length == 0) {
       return true;
   }
   var emailPat=/^(.+)@(.+)$/;
   var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
   var validChars="\[^\\s" + specialChars + "\]";
   var quotedUser="(\"[^\"]*\")";
   var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
   var atom=validChars + '+';
   var word="(" + atom + "|" + quotedUser + ")";
   var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
   var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
   var matchArray=emailStr.match(emailPat);
   if (matchArray == null) {
       return false;
   }
   var user=matchArray[1];
   var domain=matchArray[2];
   if (user.match(userPat) == null) {
       return false;
   }
   var IPArray = domain.match(ipDomainPat);
   if (IPArray != null) {
       for (var i = 1; i <= 4; i++) {
          if (IPArray[i] > 255) {
             return false;
          }
       }
       return true;
   }
   var domainArray=domain.match(domainPat);
   if (domainArray == null) {
       return false;
   }
   var atomPat=new RegExp(atom,"g");
   var domArr=domain.match(atomPat);
   var len=domArr.length;
   if ((domArr[domArr.length-1].length < 2) ||
       (domArr[domArr.length-1].length > 3)) {
       return false;
   }
   if (len < 2) {
       return false;
   }
   return true;
}

function iesvn_ValidateFloat(form) {
  var bValid = true;
  var focusField = null;
  var i = 0;
  var fields = new Array();
  oFloat = new iesvn_FloatValidations();
  for (x in oFloat) {
    var field = form[oFloat[x][0]];
    
      if (field.type == 'text' ||
          field.type == 'textarea' ||
          field.type == 'select-one' ||
          field.type == 'radio') {
          
        var value = '';
        // get field's value
        if (field.type == "select-one") {
          var si = field.selectedIndex;
          if (si >= 0) {
              value = field.options[si].value;
          }
        } else {
          value = field.value;
        }
              
            if (value.length > 0) {
                // remove '.' before checking digits
                value = "" + value;
                /*
                value = value.replace(/[.]/g, "");
   				value = value.replace(",", ".");
   				*/
   				value = value.replace(/[,]/g, "");
   				var tempArray = value.split('.');
               // alert("tempArray.length = " +tempArray.length)
                if (tempArray.length > 2) {
                	bValid = false;
                	//alert("Nh?p sai nh?p l?i.");
                } else {
                var joinedString= tempArray.join('');

                if (! iesvn_IsAllDigits(joinedString)) {
                    bValid = false;
                    if (i == 0) {
                        focusField = field;
                    }
                    fields[i++] = oFloat[x][1];

                } else {
                  var iValue = parseFloat(value);
                  if (isNaN(iValue)) {
                      if (i == 0) {
                          focusField = field;
                      }
                      fields[i++] = oFloat[x][1];
                      bValid = false;
                  }
                }
                }
            }
        }
    }
    if (fields.length > 0) {
       try{
           focusField.focus();
       }catch(e){}    
       alert(fields.join('\n'));
    }
    return bValid;
}

//thanhdo add here
function iesvn_ValidateTwoDatesWithHours(){
	//alert(1);
	oDates = new  iesvn_DateWithHoursValidations();
	var fields = new Array();
	var i = 0;
	//alert(2);
	var bValid = true;
	for (x in oDates) {
		//alert("x:"+x);
	   
		var gioVaoId = oDates[x][0];
		var gioRaId = oDates[x][1];
		var ngayVaoId = oDates[x][2];
		var ngayRaId = oDates[x][3];
		var msg = oDates[x][4];
//		alert("gioVaoId:"+gioVaoId + " ngayVaoId:"+ngayVaoId+" gioRaId:"+gioRaId + " ngayRaId:"+ngayRaId);
		//alert("gioRaId:"+gioRaId);
		//alert("ngayVaoId:"+ngayVaoId);
		//alert("ngayRaId:"+ngayRaId);
		
   		var ngayVao = document.getElementById(ngayVaoId).value;
   		var ngayRa = document.getElementById( ngayRaId).value;
   		var gioVao = document.getElementById( gioVaoId).value;
   		var gioRa = document.getElementById( gioRaId).value;
   		//alert("gioVaoId:"+gioVao + " ngayVaoId:"+ngayVao+" gioRaId:"+gioRa + " ngayRaId:"+ngayRa);
   		//alert("ngayVaoVien:"+ngayVaoVien);
   		if (ngayVao == null || ngayVao == "" ||
    		ngayRa == null || ngayRa == "" ||
    		gioVao == null || gioVao == "" ||
    		gioRa == null || gioRa == "" ){	
    		// alert(4);	    		
   			 return true;
   		}	
   		var returnValue = iesvn_CompareDate(ngayVao, ngayRa);
   		//alert(returnValue);
   		if (parseInt(returnValue) == 0){
   		    //alert(5);
   			var gioPhutVao;
   			var gioPhutRa;
   			var _gioVao;
   			var _gioRa;
   			if (gioVao.length > 4 && gioRa.length > 4) {
	   			gioPhutVao = gioVao.substr(0,2) + gioVao.substr(3,2);
	   			gioPhutRa = gioRa.substr(0,2) + gioRa.substr(3,2);
	   			_gioVao  = parseInt(gioPhutVao,10);
	   		    _gioRa  = parseInt(gioPhutRa,10);
   			} else {
   				_gioVao  = parseInt(gioVao,10);
   	   		    _gioRa  = parseInt(gioRa,10);
   			}
   		    
   		    if (_gioRa < _gioVao){
   		    	//alert(6);
   		    	bValid = false;
   		    }
   		    //alert(7);
   		}
   		if (bValid == false){
   			fields[i++] = msg;	
   		}
   		//alert(8);
	}
	if (fields.length > 0) {                  
		alert( fields.join('\n'));
		document.getElementById(gioRaId).focus();
	}
	//alert("bValid:"+bValid);
	
	return  bValid;  		
}  

//value=   1 if fromDate is greater than toDate
//   0 if they are the same
//   -1 if fromDate is less than toDate
function iesvn_ValidateComparedDates(form) {          
	var bValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oDates = new iesvn_ComparedDates();
	for (x in oDates) {
		var fromId = oDates[x][0][0];
		var toId = oDates[x][0][1];
		var equal = oDates[x][0][2];
		var value = -999;
		
		if(fromId != null && fromId != '' && toId != null && toId != '')
		{
			var fromDate = form[fromId].value;
			var toDate = form[toId].value;
			if ((fromDate == "") || (toDate == "")) {
				// return true
			} else {
				value = iesvn_CompareDate(fromDate, toDate); // fromDate - toDate, this method inside common.js 
			}
		}else if(fromId != null && fromId != '' && (toId == null || toId == '')) {
			var fromDate = form[fromId].value;
			if(fromDate != ""){
				value = iesvn_TodayGreater(fromDate); // fromDate - today
			}	
		} else if((fromId == null || fromId == '') && toId != null && toId != '') {
				var toDate = form[toId].value;
				if(toDate != ""){
					value = iesvn_TodayLessThan(toDate); // today - toDate
				}
		}
		
		if (value == null) {
			bValid = false;
		}
		if(equal == '==') {
			if (value >0) {
				fields[i++] = oDates[x][1];	                        
				bValid = false;
			}
		}else {
			if (value >=0) {
				fields[i++] = oDates[x][1];	                        
				bValid = false;
			}
		}
		if (bValid == false && focusField == null) {
			if (form[fromId].type != 'hidden'){
				focusField = form[fromId];
			} else if (form[toId].type != 'hidden') {
				focusField = form[toId];
			}
		}
	}
	if (fields.length > 0) { 
		if (focusField != null){
			  try{
	              focusField.focus();
		       }catch(e){}    
			}
		alert( fields.join('\n'));
	}
	return bValid;
} 
function iesvn_ValidateIntRange(form) {
    var isValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oRange = new iesvn_IntRange();
    for (x in oRange) {
        var field = form[oRange[x][0]];
        
        if ((field.type == 'text' ||
             field.type == 'textarea') &&
            (field.value.length > 0)) {
            
            var iMin = parseInt(oRange[x][2]("min"));
            var iMax = parseInt(oRange[x][2]("max"));
            var iValue = parseInt(field.value);
            if (!(iValue >= iMin && iValue <= iMax)) {
                if (i == 0) {
                    focusField = field;
                }
                fields[i++] = oRange[x][1];
                isValid = false;
            }
        }
    }
    if (fields.length > 0) {
       try{
           focusField.focus();
       }catch(e){}    
        alert(fields.join('\n'));
    }
    return isValid;
}  

function iesvn_ValidateInteger(form) {
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oInteger = new iesvn_IntegerValidations();
    for (x in oInteger) {
    	var field = form[oInteger[x][0]];

        if (field.type == 'text' ||
            field.type == 'textarea' ||
            field.type == 'select-one' ||
            field.type == 'radio') {
            
            var value = '';
			// get field's value
			if (field.type == "select-one") {
				var si = field.selectedIndex;
			    if (si >= 0) {
				    value = field.options[si].value;
			    }
			} else {
				value = field.value;
			}
            
            if (value.length > 0) {
            
                if (! iesvn_IsAllDigits(value)) {
                    bValid = false;
                    if (i == 0) {
                        focusField = field;
                    }
			        fields[i++] = oInteger[x][1];
			        
                } else {
                    var iValue = parseInt(value);
                    if (isNaN(iValue) || !(iValue >= -2147483648 && iValue <= 2147483647)) {
                        if (i == 0) {
                            focusField = field;
                        }
                        fields[i++] = oInteger[x][1];
                        bValid = false;
                   }
               }
           }
        }
    }
    if (fields.length > 0) {
       try{
           focusField.focus();
       }catch(e){}    
       alert(fields.join('\n'));
    }
    return bValid;
}

/*
function iesvn_ValidateTodayGreaterEqual(form) {          
	var bValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oDates = new  iesvn_TodayGreaterEqual();
	for (x in oDates) {                   
		var fromDate = form[oDates[x][0]].value;
		if (fromDate == "") {
			// return true
		} else {
			var value = iesvn_TodayGreater(fromDate); // fromDate - today
			if (value == null) {
				bValid = false;
			}
			if (value >0) {
				fields[i++] = oDates[x][1];	                        
				bValid = false;
			} else {				
			}       
		}  
	}
	if (fields.length > 0) {                  
		alert( fields.join('\n'));
	}
	return bValid;
}   

function iesvn_ValidateTodayLessEqual(form) {          
	var bValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oDates = new  iesvn_TodayLessEqual();
	for (x in oDates) {                   
		var toDate = form[oDates[x][0]].value;
		if (toDate == "") {
			// return true
		} else {
			var value = iesvn_TodayLessThan(toDate); // today - toDate
			if (value == null) {
				bValid = false;
			}
			if (value >0) {
				fields[i++] = oDates[x][1];	                        
				bValid = false;
			} else {				
			}       
		}  
	}
	if (fields.length > 0) {                  
		alert( fields.join('\n'));
	}
	return bValid;
}   
*/
function  iesvn_lockUnlockControl(form, type) {    
 	var prefix = "defrol";
 	var elem = form.elements; 
 	for (var i = 0; i < elem.length; i++) {  
  		var strID = elem[i].id;  		
  		var strType = elem[i].type.toLowerCase();
  
  
  		if(strID!=''){ //Bat loi khi co dojo
   			if (type == 1) {
    			if (strType == 'text' || strType == 'textarea') {
	     			try{
	      				document.getElementById(strID).disabled = true;
	      				dijit.byId(strID).disabled = true;
	     			}catch(e){
	      				document.getElementById(strID).disabled = false;
	      				document.getElementById(strID).readOnly = true;
	     			}        
     
    			} else if (strType == 'checkbox' || strType == 'radio' || strType == 'select-one') {
     				document.getElementById(strID).disabled = true;
    			}
   			} else if (type == 0){
    			if (strID.indexOf(prefix) < 0) {
     				if (strType == 'text' || strType == 'textarea') {
      
				      	try{
				       		document.getElementById(strID).disabled = false;
				      		dijit.byId(strID).disabled = false;
				      	}catch(e){
				       		document.getElementById(strID).readOnly = false;
				      	}        
      
     				} else if (strType == 'checkbox' || strType == 'radio' || strType == 'select-one') {
      					document.getElementById(strID).disabled = false;
     				} 
    			}   
   			}   
  
  		}
 	} 
}	
//////////	
var  alreadyAlert = false; //use for  checkHour and checkHourBlur
function checkHour(field){
	var bValid = true;
	var text=field.value;
	var n = 0;
	for(i=0;i<text.length;i++){
		if(text.charAt(i) == ':') n++;
		if(((text.charAt(i)<'0'||text.charAt(i)>'9') && text.charAt(i) != ':') || (text.charAt(i) == ':' && n > 1) || (text.charAt(i) == ':' &&  i !=2)){
			bValid = false;
			alreadyAlert = true;
			alert("Xin vui lòng nhập giờ theo dạng hh:mm. Ví dụ: 14:05.");
			alreadyAlert = false;
			field.value='';				
			field.setActive();
			break;			
		}
		text = field.value;
		if(text.length == 2 && text.charAt(0) != ':' && text.charAt(1) != ':'){
			field.value = text + ":";
			text = field.value;
		}
	}
	if(bValid) {
		var items = text.split(':');
		if(items[0] > 23 || items[1] > 59) {
			bValid = false;
			alreadyAlert = true;
			alert("Giờ không hợp lệ.");
			alreadyAlert = false;
			if(items[0] > 23) field.value='';				
			if(items[1] > 59) field.value=items[0];
			field.setActive();
		}
	}
	return  bValid;	
}

function checkHourBlur(field){
	var text=field.value;
	//alert('text' + text);
	if(alreadyAlert) return;
	if(text !='') {
		var items = text.split(':');
		if(text.indexOf(':') < 0  || items[1].length != 2 || text.length != 5){
		//if(text.indexOf(':') < 0 ){
			alert("Xin vui lòng nhập giờ theo dạng hh:mm. Ví dụ: 14:05.");
			field.value='';	
			field.setActive();
		}
	}
}

function iesvn_ValidateFloatRange(form) {
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oRange = new iesvn_FloatRange();
                for (x in oRange) {
                    var field = form[oRange[x][0]];
                    
                    if ((field.type == 'text' ||
                         field.type == 'textarea') &&
                        (field.value.length > 0)) {
                        var num = "" + field.value;
                        /*
			              num = num.replace(/[.]/g, "");
			   			  num = num.replace(",", ".");
			   			 */
			   			 num = num.replace(/[,]/g, "");
                        var fMin = parseFloat(oRange[x][2]("min"));
                        var fMax = parseFloat(oRange[x][2]("max"));
                        var fValue = parseFloat(num);
                        if (!(fValue >= fMin && fValue <= fMax)) {
                            if (i == 0) {
                                focusField = field;
                            }
                            fields[i++] = oRange[x][1];
                            isValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return isValid;
            }
            
function iesvn_ValidateFloatMin(form) {
      var isValid = true;
      var focusField = null;
      var i = 0;
      var fields = new Array();
      oRange = new iesvn_FloatMin();
      for (x in oRange) {
          var field = form[oRange[x][0]];
          
          if ((field.type == 'text' ||
               field.type == 'textarea') &&
              (field.value.length > 0)) {
              
              var fMin = parseFloat(oRange[x][2]("min"));
              var num = "" + field.value;
              /*
              num = num.replace(/[.]/g, "");
   			  num = num.replace(",", ".");
   			  */
   			  num = num.replace(/[,]/g, "");
              var fValue = parseFloat(num);
              if (!(fValue >= fMin)) {
                  if (i == 0) {
                      focusField = field;
                  }
                  fields[i++] = oRange[x][1];
                  isValid = false;
              }
          }
      }
      if (fields.length > 0) {
          focusField.focus();
          alert(fields.join('\n'));
      }
      return isValid;
  }

            
function iesvn_ValidateMaxLength(form) {
    var isValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oMaxLength = new iesvn_InputMaxLengths();
    for (x in oMaxLength) {
        var field = form[oMaxLength[x][0]];
        
        if (field.type == 'text' ||
            field.type == 'textarea') {
            
            val = field.value;
            if (val != '') {
            
	            var iMax = parseInt(oMaxLength[x][2]);

	            if (field.value.length > iMax) {
	                if (i == 0) {
	                    focusField = field;
	                }
	                fields[i++] = oMaxLength[x][1];
	                isValid = false;
	            }
            
            }
        }
    }
    if (fields.length > 0) {
       try{
           focusField.focus();
       }catch(e){}    
       alert(fields.join('\n'));
    }
    return isValid;
}  
        
            
            
            
            
            
            
            
            

