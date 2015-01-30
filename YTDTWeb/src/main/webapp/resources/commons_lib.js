/**
 * http://www.openjs.com/scripts/events/keyboard_shortcuts/
 * Version : 2.01.A
 * By Binny V A
 * License : BSD
 */ 
 
 
shortcut = {
	'all_shortcuts':{},//All the shortcuts are stored in this array
	'add': function(shortcut_combination,callback,opt) {
		//Provide a set of default options
		var default_options = {
			'type':'keydown',
			'propagate':false,
			'disable_in_input':false,
			'target':document,
			'keycode':false
		}
		if(!opt) opt = default_options;
		else {
			for(var dfo in default_options) {
				if(typeof opt[dfo] == 'undefined') opt[dfo] = default_options[dfo];
			}
		}

		var ele = opt.target
		if(typeof opt.target == 'string') ele = document.getElementById(opt.target);
		var ths = this;
		shortcut_combination = shortcut_combination.toLowerCase();

		//The function to be called at keypress
		var func = function(e) {
			e = e || window.event;
			
			if(opt['disable_in_input']) { //Don't enable shortcut keys in Input, Textarea fields
				var element;
				if(e.target) element=e.target;
				else if(e.srcElement) element=e.srcElement;
				if(element.nodeType==3) element=element.parentNode;

				if(element.tagName == 'INPUT' || element.tagName == 'TEXTAREA') return;
			}
	
			//Find Which key is pressed
			if (e.keyCode) code = e.keyCode;
			else if (e.which) code = e.which;
			var character = String.fromCharCode(code).toLowerCase();
			
			if(code == 188) character=","; //If the user presses , when the type is onkeydown
			if(code == 190) character="."; //If the user presses , when the type is onkeydown
	
			var keys = shortcut_combination.split("+");
			//Key Pressed - counts the number of valid keypresses - if it is same as the number of keys, the shortcut function is invoked
			var kp = 0;
			
			//Work around for stupid Shift key bug created by using lowercase - as a result the shift+num combination was broken
			var shift_nums = {
				"`":"~",
				"1":"!",
				"2":"@",
				"3":"#",
				"4":"$",
				"5":"%",
				"6":"^",
				"7":"&",
				"8":"*",
				"9":"(",
				"0":")",
				"-":"_",
				"=":"+",
				";":":",
				"'":"\"",
				",":"<",
				".":">",
				"/":"?",
				"\\":"|"
			}
			//Special Keys - and their codes
			var special_keys = {
				'esc':27,
				'escape':27,
				'tab':9,
				'space':32,
				'return':13,
				'enter':13,
				'backspace':8,
	
				'scrolllock':145,
				'scroll_lock':145,
				'scroll':145,
				'capslock':20,
				'caps_lock':20,
				'caps':20,
				'numlock':144,
				'num_lock':144,
				'num':144,
				
				'pause':19,
				'break':19,
				
				'insert':45,
				'home':36,
				'delete':46,
				'end':35,
				
				'pageup':33,
				'page_up':33,
				'pu':33,
	
				'pagedown':34,
				'page_down':34,
				'pd':34,
	
				'left':37,
				'up':38,
				'right':39,
				'down':40,
	
				'f1':112,
				'f2':113,
				'f3':114,
				'f4':115,
				'f5':116,
				'f6':117,
				'f7':118,
				'f8':119,
				'f9':120,
				'f10':121,
				'f11':122,
				'f12':123
			}
	
			var modifiers = { 
				shift: { wanted:false, pressed:false},
				ctrl : { wanted:false, pressed:false},
				alt  : { wanted:false, pressed:false},
				meta : { wanted:false, pressed:false}	//Meta is Mac specific
			};
                        
			if(e.ctrlKey)	modifiers.ctrl.pressed = true;
			if(e.shiftKey)	modifiers.shift.pressed = true;
			if(e.altKey)	modifiers.alt.pressed = true;
			if(e.metaKey)   modifiers.meta.pressed = true;
                        
			for(var i=0; k=keys[i],i<keys.length; i++) {
				//Modifiers
				if(k == 'ctrl' || k == 'control') {
					kp++;
					modifiers.ctrl.wanted = true;

				} else if(k == 'shift') {
					kp++;
					modifiers.shift.wanted = true;

				} else if(k == 'alt') {
					kp++;
					modifiers.alt.wanted = true;
				} else if(k == 'meta') {
					kp++;
					modifiers.meta.wanted = true;
				} else if(k.length > 1) { //If it is a special key
					if(special_keys[k] == code) kp++;
					
				} else if(opt['keycode']) {
					if(opt['keycode'] == code) kp++;

				} else { //The special keys did not match
					if(character == k) kp++;
					else {
						if(shift_nums[character] && e.shiftKey) { //Stupid Shift key bug created by using lowercase
							character = shift_nums[character]; 
							if(character == k) kp++;
						}
					}
				}
			}

			if(kp == keys.length && 
						modifiers.ctrl.pressed == modifiers.ctrl.wanted &&
						modifiers.shift.pressed == modifiers.shift.wanted &&
						modifiers.alt.pressed == modifiers.alt.wanted &&
						modifiers.meta.pressed == modifiers.meta.wanted) {
				callback(e);
	
				if(!opt['propagate']) { //Stop the event
					//e.cancelBubble is supported by IE - this will kill the bubbling process.
					e.cancelBubble = true;
					e.returnValue = false;
	
					//e.stopPropagation works in Firefox.
					if (e.stopPropagation) {
						e.stopPropagation();
						e.preventDefault();
					}
					return false;
				}
			}
		}
		this.all_shortcuts[shortcut_combination] = {
			'callback':func, 
			'target':ele, 
			'event': opt['type']
		};
		//Attach the function with the event
		if(ele.addEventListener) ele.addEventListener(opt['type'], func, false);
		else if(ele.attachEvent) ele.attachEvent('on'+opt['type'], func);
		else ele['on'+opt['type']] = func;
	},

	//Remove the shortcut - just specify the shortcut and I will remove the binding
	'remove':function(shortcut_combination) {
		shortcut_combination = shortcut_combination.toLowerCase();
		var binding = this.all_shortcuts[shortcut_combination];
		delete(this.all_shortcuts[shortcut_combination])
		if(!binding) return;
		var type = binding['event'];
		var ele = binding['target'];
		var callback = binding['callback'];

		if(ele.detachEvent) ele.detachEvent('on'+type, callback);
		else if(ele.removeEventListener) ele.removeEventListener(type, callback, false);
		else ele['on'+type] = false;
	}
}

// implement f-interaction rules on date format: DD-MM-YYYY and automatically fills '-' characters.
// checks if DD is between [01-30]
// checks if MM is between [01-12]
// checks if YYYY is between 1908 and 2018
function being_wellformed_Date(object, event) {
    var unicode = event.charCode ? event.charCode : event.keyCode;
    if (unicode != 8) { //the backspace key
        if (unicode < 48 || unicode > 57) { //if not a number
            return false; //disable key press
        }
        if (object.value.length == 2) {
            var day = object.value.slice(0,2);
            if ((day.valueOf() < 1) || (day.valueOf() > 31)) { 
                return false; 
            }
            object.value += "/"; // add "/" to conform "dd/MM/yyyy" format
        } else if (object.value.length == 5) {
        	day = object.value.slice(0,2);
        	var month = object.value.slice(3,5);
            if ((month.valueOf() < 1) || (month.valueOf() > 12)) {
            	return false; 
            }
            else {
            	if ((month.valueOf() == 2) && (day.valueOf() > 29))
            		return false;
            	if ((month.valueOf() == 4) || (month.valueOf() == 6) || (month.valueOf() == 9) || (month.valueOf() == 11)){
            		if (day.valueOf() == 31)
            			return false;
            	}                 
            }            
            object.value += "/"; // add "/" to conform "dd/MM/yyyy" format
        } else if (object.value.length == 10) {
            var year = object.value.slice(6);
            
            if(year.slice(0,2) == "00"){
				return false;
			}            

            if ((year.valueOf() < 1900) || (year.valueOf() > 2300)) { 
                return false; 
            }
        }
    }
    return true;
}

// implement f-validate rule for both birthDate and issuanceDate
function is_wellformed_Date(object){
	var returnvalue = true;
	myInput = object.value;
	if (myInput.length == 5){
		myInput = myInput + "/" + new Date().getFullYear();
	}
	if (myInput.length == 8){
		myInput = myInput.substring(0,6)+ (new Date().getFullYear() + "").substring(0,2) + myInput.substring(6,8);
	}
	object.value = myInput;
	
	if (myInput==""){
		returnvalue = false;
		return returnvalue;	
	}	
    if (myInput.length==10) {
    	if (/^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/.test(myInput)) {
        	if(iesvn_IsInteger(myInput.slice(0,2)) == true && iesvn_IsInteger(myInput.slice(3,5)) == true && iesvn_IsInteger(myInput.slice(6)) == true && myInput.slice(2,3) == "/" && myInput.slice(5,6) == "/"){
	        	var day = myInput.slice(0,2);
	            var month = myInput.slice(3,5);
	            var year = myInput.slice(6);

	            if ((day >= 1) && (day <= 31)) {	            
	            	if ((month >= 1) && (month <= 12)) {
	            		if ((month == 2) && (day > 29) && (Leap(year) == 1)){
			            			alert("Nhập ngày sai");
		           					object.select();
			            			object.focus();
			            			return false;
		            			}
		            			else if ((month == 2) && (day > 28) && (Leap(year) == 0)){
			            			alert("Nhập ngày sai");
		           					object.select();
			            			object.focus();
			            			return false;
		            			}
            			else {
            			
            				if (((month == 4) || (month == 6) || (month == 9) || (month == 11)) && (day == 31)){
            					alert("Nhập ngày sai");
            					returnvalue = false;
         						object.select();
            					object.focus();
           					}
           					else {
           						if(year.slice(0,2) != "00"){
						            var date = new Date(year, month, day);
						            var minInclusive = new Date("01/01/1900");
						            var maxInclusive = new Date("01/01/2300");
						            var value =(date.getTime() >= minInclusive.getTime()) &
						                (date.getTime() <= maxInclusive.getTime());
						            if (!value) {
										alert("Chỉ được nhập trong khoảng từ 01/01/1900 đến 01/01/2300");
						            	object.select();
						            	object.focus();
						            	returnvalue = false;
						            }
					            }
					            else {
					            	alert("Chỉ được nhập trong khoảng từ 01/01/1900 đến 01/01/2300");
					            	object.select();
						            object.focus();
						            returnvalue = false;
					            }
           					}
            			}
            		}
            		else {
                		alert("Nhập tháng sai");
           				object.select();
	            		object.focus();
	            		returnvalue = false;
                	}
                }
                else {
                	alert("Nhập ngày sai");
           			object.select();
	            	object.focus();
	            	returnvalue = false;
                }
           	}
           	else {
           		alert("Định dạng ngày dd/MM/yyyy");
           		object.select();
	            object.focus();
	            returnvalue = false;
           	}
        }
		else { 
            alert("Định dạng ngày dd/MM/yyyy");
            object.select();
	        object.focus();         
	        returnvalue = false;   
        }  
    }
    else {
    	alert("Định dạng ngày dd/MM/yyyy");
    	object.select();
        object.focus();
        returnvalue = false;
    }
    return returnvalue;	
}

function myReset(myQuantity) {
	try {
	    for (i=0; i<myQuantity; i++){
	        if(document.forms[0].elements[i].type=="text") {
	            document.forms[0].elements[i].value = "";	            
	        }    
	    }
	}	
	catch(e){}
}

function myResetReverse(myQuantity, pointStopId) {
	for (i=myQuantity; i>=0; i--){
	    try {
	    	if(document.forms[0].elements[i].type=="text"){
	    		document.forms[0].elements[i].value = "";	            
	            if(document.forms[0].elements[i].id == pointStopId){
	            	document.forms[0].elements[i].focus();
	            	return;
	            }	            				
	        }	
	    }	
		catch(e){}            	
    }	
}
//---shortcut---
//function initShortcut() {
	//---example---
	//shortcut.add('Ctrl+Shift+s',function() {document.getElementById('Send').click();});
	//shortcut.add('Enter',function() {document.getElementById('_form:Receive').click();});
//}

function myTab(myTextField,myTabNext) {
	if(document.getElementById(myTextField).value !="") {
		document.getElementById(myTabNext).focus();
	}
}
function getDateSystem_dd_MM_yyyy(){
	today = new Date();
	date = today.getDate();
	if(date.toString().length == 1)
		date = "0" + date;
	month = (today.getMonth()) + 1;
	if(month.toString().length == 1)
		month = "0" + month;
	year = today.getYear();
	strToday = date + "/" + month + "/" + year;
	return strToday;
}

//------------------------------------------


var ENTER_KEYCODE = 13;
var TAB_KEYCODE = 9;

function  iesvn_DayOfMonth() {
	var mydate=new Date();
	return daym=mydate.getDate();	
}	
function  iesvn_Month() {
	var mydate=new Date();
	return (1 + mydate.getMonth());
}	
function  iesvn_Year() {
	var mydate=new Date();
	var year=mydate.getFullYear();
	
	return year;
}	

function iesvn_DisplayNone(id){
	document.getElementById(id).style.display = "none"
}
function iesvn_DisplayBlock(id){
	document.getElementById(id).style.display = "block";
}
//#anchorName, for <a name="anchorName" external="yes"/>
function iesvn_JumpToAnchor(anchor) {
  location.hash =  anchor;
}
function iesvn_IgnoreBackSpace(e){
	var unicode=e.keyCode? e.keyCode : e.charCode;
	if(unicode == 8){
		e.keyCode = 0;
	}
}
function iesvn_WindowOpen(path, width, height) {
    var win = window.open(path, null, "toolbar=no,status=no,menubar=0,toolbar=0,scrollbars=1,resizable=1,width=" + width + " ,height=" + height);
    win.focus();
}

function iesvn_GetKeyCode(){
    return code;
}

function iesvn_Tab2Id(id){
	if (iesvn_GetKeyCode() == TAB_KEYCODE) { // TAB
		document.getElementById(id).setActive();
	}
}
function iesvn_InputInKeys(argvalue) {
    var key;
    var keychar;
    var reg;
    var e = event;
    if (window.event) {
		// for IE, e.keyCode or window.event.keyCode can be used
        key = e.keyCode;
    } else {
        if (e.which) {
		// netscape
            key = e.which;
        } else {
		// no event, so pass through
            event.keyCode = 0;
            return false;
        }
    }
    if (key == ENTER_KEYCODE) {
        return true;
    }
    keychar = String.fromCharCode(key);
    /*
	reg = /d/;
	if(!reg.test(keychar)) {
		event.keyCode = 0;
	}
*/
    if (argvalue.indexOf(keychar) == -1) {
        event.keyCode = 0;
        return false;
    }
}
function iesvn_InputInPattern(reg) { //reg in pattern
    var key;
    var keychar;
    var reg;
    var e = event;
    if (window.event) {
		// for IE, e.keyCode or window.event.keyCode can be used
        key = e.keyCode;
    } else {
        if (e.which) {
		// netscape
            key = e.which;
        } else {
		// no event, so pass through
            event.keyCode = 0;
            return false;
        }
    }
    if (key == ENTER_KEYCODE) {
        return true;
    }
    keychar = String.fromCharCode(key);

	//reg = /d/;
    if (!reg.test(keychar)) {
        event.keyCode = 0;
        return false;
    }
}
function iesvn_InputOutKeys(argvalue) {
    var key;
    var keychar;
    var reg;
    var e = event;
    if (window.event) {
		// for IE, e.keyCode or window.event.keyCode can be used
        key = e.keyCode;
    } else {
        if (e.which) {
		// netscape
            key = e.which;
        } else {
		// no event, so pass through
            event.keyCode = 0;
            return false;
        }
    }
    if (key == ENTER_KEYCODE) {
        return true;
    }
    keychar = String.fromCharCode(key);
    /*
	reg = /d/;
	if(!reg.test(keychar)) {
		event.keyCode = 0;
	}
*/
    if (argvalue.indexOf(keychar) >= 0) {
        event.keyCode = 0;
        return false;
    }
}
function iesvn_compareNumber(num1, num2) {
    return parseInt(num1, 10) - parseInt(num2, 10);
}
function iesvn_SelectAll(sel) {
    for (var i = 0; i < sel.options.length; i++) {
        sel.options[i].selected = true;
    }
}
function iesvn_LeftTrim(str) {
    var tmp = str;
    while ("" + tmp.charAt(0) == " ") {
        tmp = tmp.substring(1);
    }
    return tmp;
}
function iesvn_RightTrim(str) {
    var tmp = str;
    while ("" + tmp.charAt(tmp.length - 1) == " ") {
        tmp = tmp.substring(0, tmp.length - 1);
    }
    return tmp;
}
function iesvn_Trim(str) {
    var tmp = str;
    tmp = iesvn_LeftTrim(tmp);
    tmp = iesvn_RightTrim(tmp);
    return tmp;
}
function iesvn_OneSpaceBetweenWords(txtId) {
    var txt = document.getElementById(txtId);
    var tmp = iesvn_Trim(txt.value);
    var twoSpace = new RegExp("  ");
    while (tmp.match(twoSpace)) {
        tmp = tmp.replace(twoSpace, " ");
    }
    return tmp;
}
function iesvn_SetFocus(field) {
    var obj = document.getElementById(field);
    obj.setActive();
}
function iesvn_TrimFormInputs(form, prefix) {
    for (var i = 0; i < form.elements.length; i++) {
        if (form.elements[i].id.indexOf(prefix) == 0) {
            if (form.elements[i].type == "text") {
                var textbox = form.elements[i];
                textbox.value = iesvn_Trim(textbox.value);
            }
        }
    }
}
function iesvn_EmptyFormInputs(form, prefix) {
    for (var i = 0; i < form.elements.length; i++) {
        if (form.elements[i].id.indexOf(prefix) == 0) {
            if (form.elements[i].type == "text" || form.elements[i].type == "hidden" || form.elements[i].type == "textarea") {
                form.elements[i].value = "";
            }
            if (form.elements[i].type == "select-one") {
                form.elements[i].selectedIndex = 0;
            }
            if (form.elements[i].type == "checkbox" || form.elements[i].type == "radio") {
                form.elements[i].checked = false;
            }
        }
    }
}
function iesvn_CopyFromThis(toId, item) {
    var to = document.getElementById(toId);
    to.value = item.value;
}
function iesvn_SpecifyImageSize(imgPath, id, width, height) {
   try{	
    var imgSrc = new Image();
    imgSrc.src = imgPath;
    var img = document.getElementById(id);
    var newWidth = imgSrc.width; //the real width of image
    var newHeight = imgSrc.height;
    if (newHeight > height) {
	    //newWidth = width * (height / newHeight) ;//giam cung ty le voi height
        newHeight = height; 
    }
    if (newWidth > width) {
        newWidth = width;
    }
   img.width = newWidth;
   img.height = newHeight;
  }catch(e){} 
}
function iesvn_CheckAll(formName) {
	// name: name of checkboxes, all same
	// formName: the name of form contains checkboxes
    for (var i = 0; i < document.forms[formName].elements.length; i++) {
        if (document.forms[formName].elements[i].type == "checkbox") {
            document.forms[formName].elements[i].checked = true;
        }
    }
}
function iesvn_UnCheckAll(formName) {
	// name: name of checkboxes, all same
	// formName: the name of form contains checkboxes
    for (var i = 0; i < document.forms[formName].elements.length; i++) {
        if (document.forms[formName].elements[i].type == "checkbox") {
            document.forms[formName].elements[i].checked = false;
        }
    }
}
function iesvn_ResetRadio(formName, checkRadio, unCheckRadio) {
    document.forms[formName].elements[checkRadio].checked = false;
    document.forms[formName].elements[unCheckRadio].checked = false;
}

//Returns:
//   1 if today is greater than toDate
//   0 if they are the same
//   -1 if today is less than toDate
//  null  if either of the dates is in an invalid format
function iesvn_TodayLessThan(toDate) {
    if (toDate == "") {
        return null;
    }
    var arrToDate = toDate.split("/");
    if (arrToDate.length != 3) {
        return null;
    }    
    //var d1=(new Date()).getTime();
	var strD1 = iesvn_DayOfMonth() + "/" + iesvn_Month() + "/" + iesvn_Year();
	var d1 = iesvn_GetDateFromFormat(strD1,'d/M/yyyy');    
    var d2 = iesvn_GetDateFromFormat(toDate, "d/M/yyyy");
    if (d1 == 0 || d2 == 0) {
        return null;
    } else {
        if (d1 > d2) {
            return 1;
        } else {
            if (d1 == d2) {
                return 0;
            }
        }
    }
    return -1;
}
//Returns:
//   1 if fromDate is greater than today
//   0 if they are the same
//   -1 if fromDate is less than today
//  null  if either of the dates is in an invalid format
function iesvn_TodayGreater(fromDate) {
    if (fromDate == "") {
        return null;
    }
    var arrFromDate = fromDate.split("/");
    if (arrFromDate.length != 3) {
        return null;
    }
    var d1 = iesvn_GetDateFromFormat(fromDate, "d/M/yyyy");
    //var d2=(new Date()).getTime();
	var strD2 = iesvn_DayOfMonth() + "/" + iesvn_Month() + "/" + iesvn_Year();
	var d2 = iesvn_GetDateFromFormat(strD2,'d/M/yyyy');
    
    if (d1 == 0 || d2 == 0) {
        return null;
    } else {
        if (d1 > d2) {
            return 1;
        } else {
            if (d1 == d2) {
                return 0;
            }
        }
    }
    return -1;
}
//test from=31/12/2004 and to=01/01/2005
//Returns:
//   1 if fromDate is greater than toDate
//   0 if they are the same
//   -1 if fromDate is less than toDate
//  null  if either of the dates is in an invalid format
function iesvn_CompareDate(fromDate, toDate) {
    if ((fromDate == "") || (toDate == "")) {
        return null;
    }
    var arrFromDate = fromDate.split("/");
    var arrToDate = toDate.split("/");
    if (arrFromDate.length != 3 || arrToDate.length != 3) {
	 	//alert("Error, common.js, method compareDate() was not correct!");
        return null;
    }
    /* 
	var d1=iesvn_GetDateFromFormat(fromDate,'dd/MM/yyyy');
	var d2=iesvn_GetDateFromFormat(toDate,'dd/MM/yyyy');
	*/
    var d1 = iesvn_GetDateFromFormat(fromDate, "d/M/yyyy");
    var d2 = iesvn_GetDateFromFormat(toDate, "d/M/yyyy");
    if (d1 == 0 || d2 == 0) {
        return null;
    } else {
        if (d1 > d2) {
            return 1;
        } else {
            if (d1 == d2) {
                return 0;
            }
        }
    }
    return -1;
}
// ------------------------------------------------------------------
// getDateFromFormat( date_string , format_string )
//
// This function takes a date string and a format string. It matches
// If the date string matches the format string, it returns the
// getTime() of the date. If it does not match, it returns 0.
// ------------------------------------------------------------------
function iesvn_GetDateFromFormat(val, format) {
    val = val + "";
    format = format + "";
    var i_val = 0;
    var i_format = 0;
    var c = "";
    var token = "";
    var token2 = "";
    var x, y;
    var now = new Date();
    var year = now.getYear();
    var month = now.getMonth() + 1;
    var date = 1;
    var hh = now.getHours();
    var mm = now.getMinutes();
    var ss = now.getSeconds();
    var ampm = "";
    while (i_format < format.length) {
		// Get next token from format string
        c = format.charAt(i_format);
        token = "";
        while ((format.charAt(i_format) == c) && (i_format < format.length)) {
            token += format.charAt(i_format++);
        }
		// Extract contents of value based on format token
        if (token == "yyyy" || token == "yy" || token == "y") {
            if (token == "yyyy") {
                x = 4;
                y = 4;
            }
            if (token == "yy") {
                x = 2;
                y = 2;
            }
            if (token == "y") {
                x = 2;
                y = 4;
            }
            year = iesvn_GetInt(val, i_val, x, y);
            if (year == null) {
                return 0;
            }
            i_val += year.length;
            if (year.length == 2) {
                if (year > 70) {
                    year = 1900 + (year - 0);
                } else {
                    year = 2000 + (year - 0);
                }
            }
        } else {
            if (token == "MMM" || token == "NNN") {
                month = 0;
                for (var i = 0; i < MONTH_NAMES.length; i++) {
                    var month_name = MONTH_NAMES[i];
                    if (val.substring(i_val, i_val + month_name.length).toLowerCase() == month_name.toLowerCase()) {
                        if (token == "MMM" || (token == "NNN" && i > 11)) {
                            month = i + 1;
                            if (month > 12) {
                                month -= 12;
                            }
                            i_val += month_name.length;
                            break;
                        }
                    }
                }
                if ((month < 1) || (month > 12)) {
                    return 0;
                }
            } else {
                if (token == "EE" || token == "E") {
                    for (var i = 0; i < DAY_NAMES.length; i++) {
                        var day_name = DAY_NAMES[i];
                        if (val.substring(i_val, i_val + day_name.length).toLowerCase() == day_name.toLowerCase()) {
                            i_val += day_name.length;
                            break;
                        }
                    }
                } else {
                    if (token == "MM" || token == "M") {
                        month = iesvn_GetInt(val, i_val, token.length, 2);
                        if (month == null || (month < 1) || (month > 12)) {
                            return 0;
                        }
                        i_val += month.length;
                    } else {
                        if (token == "dd" || token == "d") {
                            date = iesvn_GetInt(val, i_val, token.length, 2);
                            if (date == null || (date < 1) || (date > 31)) {
                                return 0;
                            }
                            i_val += date.length;
                        } else {
                            if (token == "hh" || token == "h") {
                                hh = iesvn_GetInt(val, i_val, token.length, 2);
                                if (hh == null || (hh < 1) || (hh > 12)) {
                                    return 0;
                                }
                                i_val += hh.length;
                            } else {
                                if (token == "HH" || token == "H") {
                                    hh = iesvn_GetInt(val, i_val, token.length, 2);
                                    if (hh == null || (hh < 0) || (hh > 23)) {
                                        return 0;
                                    }
                                    i_val += hh.length;
                                } else {
                                    if (token == "KK" || token == "K") {
                                        hh = iesvn_GetInt(val, i_val, token.length, 2);
                                        if (hh == null || (hh < 0) || (hh > 11)) {
                                            return 0;
                                        }
                                        i_val += hh.length;
                                    } else {
                                        if (token == "kk" || token == "k") {
                                            hh = iesvn_GetInt(val, i_val, token.length, 2);
                                            if (hh == null || (hh < 1) || (hh > 24)) {
                                                return 0;
                                            }
                                            i_val += hh.length;
                                            hh--;
                                        } else {
                                            if (token == "mm" || token == "m") {
                                                mm = iesvn_GetInt(val, i_val, token.length, 2);
                                                if (mm == null || (mm < 0) || (mm > 59)) {
                                                    return 0;
                                                }
                                                i_val += mm.length;
                                            } else {
                                                if (token == "ss" || token == "s") {
                                                    ss = iesvn_GetInt(val, i_val, token.length, 2);
                                                    if (ss == null || (ss < 0) || (ss > 59)) {
                                                        return 0;
                                                    }
                                                    i_val += ss.length;
                                                } else {
                                                    if (token == "a") {
                                                        if (val.substring(i_val, i_val + 2).toLowerCase() == "am") {
                                                            ampm = "AM";
                                                        } else {
                                                            if (val.substring(i_val, i_val + 2).toLowerCase() == "pm") {
                                                                ampm = "PM";
                                                            } else {
                                                                return 0;
                                                            }
                                                        }
                                                        i_val += 2;
                                                    } else {
                                                        if (val.substring(i_val, i_val + token.length) != token) {
                                                            return 0;
                                                        } else {
                                                            i_val += token.length;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
	// If there are any trailing characters left in the value, it doesn't match
    if (i_val != val.length) {
        return 0;
    }
	// Is date valid for month?
    if (month == 2) {
		// Check for leap year
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) { // leap year
            if (date > 29) {
                return 0;
            }
        } else {
            if (date > 28) {
                return 0;
            }
        }
    }
    if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
        if (date > 30) {
            return 0;
        }
    }
	// Correct hours value
    if (hh < 12 && ampm == "PM") {
        hh = hh - 0 + 12;
    } else {
        if (hh > 11 && ampm == "AM") {
            hh -= 12;
        }
    }
    var newdate = new Date(year, month - 1, date, hh, mm, ss);
    return newdate.getTime();
}
// ------------------------------------------------------------------
// Utility functions for parsing in getDateFromFormat()
// ------------------------------------------------------------------
function iesvn_IsInteger(val) {
    var digits = "1234567890";
    for (var i = 0; i < val.length; i++) {
        if (digits.indexOf(val.charAt(i)) == -1) {
            return false;
        }
    }
    return true;
}
function iesvn_GetInt(str, i, minlength, maxlength) {
    for (var x = maxlength; x >= minlength; x--) {
        var token = str.substring(i, i + x);
        if (token.length < minlength) {
            return null;
        }
        if (iesvn_IsInteger(token)) {
            return token;
        }
    }
    return null;
}
// -------------Manh added-----------------------------------------------------
function is_wellformed_Date1(object){
			myInput = object.value;
			if (myInput=="")
				return true;	
		    if (myInput.length==10) {
		    	if (/^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/.test(myInput)) {
		        	if(iesvn_IsInteger(myInput.slice(0,2)) == true && iesvn_IsInteger(myInput.slice(3,5)) == true && iesvn_IsInteger(myInput.slice(6)) == true && myInput.slice(2,3) == "/" && myInput.slice(5,6) == "/"){
			        	var day = myInput.slice(0,2);
			            var month = myInput.slice(3,5);
			            var year = myInput.slice(6);
		
			            if ((day >= 1) && (day <= 31)) {
			                 	            
			            	if ((month >= 1) && (month <= 12)) {	
			            	    //alert(month);	
			            	   //  alert(day);
			            	    //  alert(year);	 
			            	   //   alert(Leap(year));	           		
			            		if ((month == 2) && (day > 29) && (Leap(year) == 1)){
			            			alert("Nhập ngày sai");
		           					object.select();
			            			object.focus();
			            			return false;
		            			}
		            			else if ((month == 2) && (day > 28) && (Leap(year) == 0)){
			            			alert("Nhập ngày sai");
		           					object.select();
			            			object.focus();
			            			return false;
		            			}
		            			else {
		            			
		            				if (((month == 4) || (month == 6) || (month == 9) || (month == 11)) && (day == 31)){
		            					alert("Nhập ngày sai");
		         						object.select();
		            					object.focus();
		            					return false;
		           					}
		           					else {
		           						if(year.slice(0,2) != "00"){
								            var date = new Date(year, month, day);
								            var minInclusive = new Date("01/01/1900");
								            var maxInclusive = new Date("01/01/2300");
								            var value =(date.getTime() >= minInclusive.getTime()) &
								                (date.getTime() <= maxInclusive.getTime());
								            if (!value) {
												alert("Chỉ được nhập trong khoảng từ 01/01/1900 đến 01/01/2300");
								            	object.select();
								            	object.focus();
								            	return false;
								            }
							            }
							            else {
							            	alert("Chỉ được nhập trong khoảng từ 01/01/1900 đến 01/01/2300");
							            	object.select();
								            object.focus();
								            return false;
							            }
		           					}
		            			}
		            		}
		            		else {
		                		alert("Nhập tháng sai");
		           				object.select();
			            		object.focus();
			            		return false;
		                	}
		                }
		                else {
		                	alert("Nhập ngày sai");
		           			object.select();
			            	object.focus();
			            	return false;
		                }
		           	}
		           	else {
		           		alert("Định dạng ngày dd/MM/yyyy");
		           		object.select();
			            object.focus();
			            return false;
		           	}
		        }
				else { 
		            alert("Định dạng ngày dd/MM/yyyy");
		            object.select();
			        object.focus();  
			        return false;          
		        }  
		    }
		    else {
		    	alert("Định dạng ngày dd/MM/yyyy");
		    	object.select();
		        object.focus();
		        return false;
		    }
		    return true;
		}
//--------Manh end-------------
function groupingNumber(strNumber) {
	//alert("begin:"+strNumber);
	
	strNumber = strNumber + "";
	strNumber = strNumber.replace(/[,]/g, "");
	
	var end = strNumber.length;
	var at = strNumber.indexOf(".");
	if (at > 0) {
		end = at;	
	}
	var i = end - 3;
	
	while(i > 0) {
		strNumber = strNumber.substring(0, i) + "," + strNumber.substring(i);
		i = i - 3;
	}
	//alert("end:"+strNumber);
	return strNumber;
}

//var value_public = "";

function formatRealNumberStr(str){
	var value = "" +str;	
	value = value.replace(/[,]/g, "");	
	//alert("value:"+value);
	if (value == ""){
		return 0;
	}
	return value; 

}


function formatRealNumber(input){
	var value = "" + input.value;	
	value = value.replace(/[,]/g, "");	
	//alert("value:"+value);
	if (value == ""){
		return 0;
	}
	return value; 
}

function numberFormatFocus(input) {

	  if(!input.className.match(/focus/gi))
              input.className += " focus";
            else if(!input.className)
              input.className += "focus";
	
    //input.style.textAlign='left';
	var value = "" + input.value;
	value = value.replace(/[,]/g, "");
	input.value = value; 	
	
	input.select();
	
}

function numberFormatBlur(input) {

	if(input.className.match(/focus/gi))    input.className = input.className.replace(/focus/gi,"");  
	//input.style.textAlign='right';
	//alert(input.value);
	input.value = groupingNumber(input.value);
}
function Leap(Year) {
	if ( (Year % 4) == 0) {
		// It is exactly divisible by 4
		if ( (Year % 100) == 0) {
			// It is exactly divisible by 100
			// Is it also exactly divisible by 400?
			Result = ( (Year % 400) == 0);
		}
		else{
		Result = 1;
		}
	}
	else {
		// It is not exactly divisible by 4
		// It is not a leap year
		Result = 0;
	}
	//alert(Result);
	return (Result);
}

/*function createXmlHttpRequest() {
    var xmlHttp;
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    return xmlHttp;
}*/

//Ham nay dung de huy su kien nhan phim Backspace tren cac readonly field
//Mac dinh, khi focus dang nam tren readonly field, khi nhan phim Backspace thi trinh duyet se quay ve trang truoc do
//khi muon bo su mac dinh nay, thi su dung ham duoi day trong readonly field nhu sau : onkeydown="return disabledBackSpace();"

function disabledBackSpace() { 
	var keyCode = (event.which) ? event.which : event.keyCode;
	if ((keyCode == 8) || (keyCode == 46)) 
	    event.returnValue = false; 
}
// Ham kiem tra thoi gian bao hiem so voi ngay hien tai
// param fromDate : ngay bat dau bao hiem
// param toDate : ngay ket thuc bao hiem
function checkHetHanBH(fromDate,toDate) {	
	if (fromDate == null || toDate == null
		|| fromDate == '' || toDate == ''	) {
		return true;
	} else {
		var dt1  = parseInt(fromDate.substring(0,2),10);
	    var mon1 = parseInt(fromDate.substring(3,5),10);
	    var yr1  = parseInt(fromDate.substring(6,10),10);
	    
	    var dt2  = parseInt(toDate.substring(0,2),10);
	    var mon2 = parseInt(toDate.substring(3,5),10);
	    var yr2  = parseInt(toDate.substring(6,10),10);
	    
	    var date1 = new Date(yr1, (mon1-1), dt1);
	    var date2 = new Date(yr2, (mon2-1), dt2);
	    var curDate = new Date();
	    curDate = new Date(curDate.getFullYear(),curDate.getMonth(),curDate.getDate());	    
	    return (curDate < date1 || curDate > date2);
	    
	}
	return false;
}