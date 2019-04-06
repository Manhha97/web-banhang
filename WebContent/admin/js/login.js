$(document).ready(function () {
		
		 $(document).on('click','#login', function(e){
			 e.preventDefault();
			 var user={};
			 var data= $("form").serializeArray();
			 for(var i=0; i< data.length; i++){
				 var record = data[i];
			     user[record.name] = record.value;
			 }
			   $.ajax({
			        url : 'login?action=login',
			        type : 'POST',
			        data: user,
			        dataType:'json',
			        success : function (r) {
			        	if(r === 'true' || r ===true){
			        		location.href="/admin/home"
			        	}
			        	
			        },error : function () {
			            alert('lỗi đăng nhập ')
			        }
			    });
			   
		   });
	});