function init(again) {
	if (! again) {	
		return;
	}
    if (window.google && google.gears) {
    	
        try {
            db = google.gears.factory.create('beta.database');
            timer = google.gears.factory.create('beta.timer');
            
            if (db) {
                db.open('database-Quan_Ly_Y_Te');
                //---Create table in local DB---

				var tables = iesvn_TableDescVars();
				for (var i = 0; i < tables.length; i++) {
	                createTableDB(tables[i]); 
				}

                //---Function run at startup---
                //---Load catalog from server(At startup)---
		
                getCatalogFromServer('DM_TINH','GetTinhAction');

				var servlets = iesvn_ServletVars();
				for (var i = 0; i < servlets.length; i++) {
					getCatalogFromServerDiaChi(servlets[i][0],servlets[i][1]);
				}  

                 //---Load DB and set atrribute for combobox--- 
				var coms = iesvn_ComboboxVars();
				for (var i = 0; i < coms.length; i++) {
	                setAttrForComboboxJSF(coms[i][0], coms[i][1], coms[i][2], coms[i][3] );
				}                

                //---Set value on load---
                //displayErrorSendingResults('TIEP_DON');  						  
            }

        } catch (e) {
        	alert(1 + e);
        }
    }
    
}



