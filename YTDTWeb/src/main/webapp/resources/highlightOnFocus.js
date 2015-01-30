var highlightOnFocus = function(){

  if(document.forms){ //find all forms
    var forms = document.forms; //build array of all forms
    for(y=0;y<forms.length;y++){
      var formEls = document.forms[y].elements; //find all elements within each form
      for(g=0;g<formEls.length;g++){
	       try{
		       if (document.getElementById(formEls[g].id).dojoType !=null){
					continue;
		       }
		   }catch(e){
		   
		   }    
	    
		   if ( formEls[g].type == null ){
		  		continue;
		   } 
	 
	   	   // vi tri 009
           if(formEls[g].type=="text" || formEls[g].type=="select" || formEls[g].type=="textarea" || 
			        formEls[g].type=="submit" || 
					formEls[g].type=="checkbox" || 
					formEls[g].type=="radio" || 
					formEls[g].type=="button" ||
			        formEls[g].type=="password"){
         
        
				     try{
				     
				       if ( document.getElementById(formEls[g].id).getAttribute("readonly") == true || document.getElementById(formEls[g].id).getAttribute("readonly") == "true"){
							
							continue;
				       }
				       
				   }catch(e){
				   
				   }   
         
				
				 formEls[g].onfocus = function(){ //add event when form field is focused on
				 
					  if ( this.type=="submit" || 
								this.type=="checkbox" || 
								this.type=="radio" || 
								this.type=="button" ){
						   	   
						   	    			this.alt=1;
					    			
						   	   }
						   	   
			         
			              if (this.id == "_form:__timkiem" || this.id=="__timkiem"){
			              	this.className = "mybton_search2";
			              }else if (this.id == "_form:__themchinhsau" || this.id == "_form:__add"){	
			              	this.className = "mybton_add2";
			              }else if (this.id == "_form:__themchinhsau2" || this.id == "_form:__add2"){	
			              	this.className = "mybton_add2";
			              }else if (this.id == "_form:__tonghop"){
			             	 this.className = "mybton_th2";			              
			              }		              
			              else{
			              	   if(!this.className.match(/focus/gi))
			             		 this.className += " focus";
			            	   else if(!this.className)
			              	     this.className += "focus";
			              }
			              
		          };            
		          
		          try{
				       if (g+1<formEls.length && document.getElementById(formEls[g+1].id).dojoType !=null){
				        	//alert(formEls[g+1].id);
				        	//alert(formEls[g].id);
							continue;
				       }
				  }catch(e){
					   
				  } 
			   
				  if ( formEls[g].onblur !=null){
			         	
			         	continue;
			      }
		         
		          formEls[g].onblur = function(){ //add event when form field is no longer focused
		              if(this.className.match(/focus/gi)) this.className = this.className.replace(/focus/gi,"");
		              
		              if ( this.type=="submit" || 
									this.type=="checkbox" || 
									this.type=="radio" || 
									this.type=="button" ){
				   	   
				   	    			this.alt=0;
			    			
				   	   }
				   	   
				   	   
				   	    
				   	   if (this.id == "_form:__timkiem"  || this.id=="__timkiem"){
			              	this.className = "mybton_search";
			           }else if (this.id == "_form:__themchinhsau"){	
			              	this.className = "mybton_add";
			           }else if (this.id == "_form:__tonghop"){
			             	 this.className = "mybton_th";			              
			              }
		              
		          };
		
		        } // ket thuc  vi tri 009
		      }
		    }
		  }
		 
		};

window.onload = highlightOnFocus;