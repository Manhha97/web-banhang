$(document).ready(function () {
	$('.modal').modal();
	$('.form-search').slideUp();
	$('.display-form').click(function(){
		$(this).children().toggleClass('rote');
		$('.form-search').slideToggle();
	});
	$('.thongtin td').css({'padding':'2px 0', 'text-align':'left'});
    
    var orderdetail;
    var codebill;
    $(document).on('click','.detailP', function(){
    	codebill=$(this).attr('bill-code');
    	$(".product").empty();
    	$.ajax({
			url : 'bill?action=getByCode&code='+$(this).attr('bill-code'),
			type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
			success : function (res) {
                if(JSON.stringify(res) !== JSON.stringify({})){
                    $.each(res, function(i, val) {
                    	orderdetail=res.orderDetailId;
                        $('#modal_detail').find('span[title="'+i+'"]').text(val);
                    });
                    $('#modal_detail').modal('open');
                    getOderdetailId(orderdetail);
                    getProductDetail(codebill);
				}
            },error : function () {
				alert('lỗi detail')
            }
		})
		
    });
    var delivery;
    var products = {
    		bill:{
                bill_code: null,
                product_code:null,
                quantity:0
    		}
            
        };
   function getOderdetailId(orderdetail){
	   $.ajax({
	        url : 'bill?action=getAllOrderDetail&id='+ orderdetail,
	        type : 'POST',
	        dataType: 'json',
	        contentType: 'application/json',
	        success : function (r) {
	            orderdetail = r;
	            delivery= r.deliveryId;
	            $.each(r, function(i, val) {
                    $('#modal_detail').find('td[title="'+i+'"]').text(val);
                });
	            if(delivery===1){
                    $('#modal_detail').find('td[title=delivery_id]').text("Giao hàng tiêu chuẩn");
				}else
                    $('#modal_detail').find('td[title=delivery_id]').text("Giao hàng nhanh");
	        },error : function () {
	            alert('lỗi detail')
	        }
	    });
	   
   }

   var product_code;
   
   function getProductDetail(codebill){

   	var tong=0;
	   products['bill']['bill_code']=codebill;
	   $.ajax({
	        url : 'bill?action=getProductDetail',
	        type : 'POST',
	        data: products,
	        cache: false,
	        success : function (r) {
	        	var e=JSON.parse(r);
	        	var i=1;
	        	var sumqu=0;
	        	if(e.list.length > 0){
	        		var sum=0;
	                e.list.forEach(function (item) {
	                	product_code=item.productCode;
	                	var quantity=item.quantity;
	                	sumqu+=item.quantity;
	                	console.log(1)
	                	console.log(e.list[e.list.length-1].quantity)
	                	$.ajax({
	            			url : 'product?action=getByCode&code=' + product_code,
	            			type : 'POST',
	                       dataType: 'json',
	                       contentType: 'application/json',
	            			success : function (result) {
	            				console.log(result)
	            				$('.product').prepend('<tr>\n' +
                                   		' <td>'+i+'</td>\n' +
                                   		' <td>'+product_code+'</td>\n' +
               	                        ' <td>'+result.name +'</td>\n' +
               	                        ' <td>'+ quantity+'</td>\n' +
               	                        ' <td>'+ result.price+'</td>\n' +
               	                        ' <td>'+ result.price*quantity+'</td>\n' +
               	                        ' </tr>');
	            					sum += (result.price*quantity);
               	                    i++;
               	                    tong=sum;

    	            				console.log(tong)
								$('span[title="total_price"]').text(tong)
    	            				if(tong>0 && e.list[e.list.length-1].quantity==quantity)
    	    		                	$('.product').append('<tr>\n'+
    	    		                		'<td >Tổng </td>\n'+
    	    		                		' <td></td>\n' +
    	    		                        ' <td></td>\n' +
    	    		                		'<td>'+sumqu+'</td>\n'+
    	    		                		' <td></td>\n' +
    	    		                		'<td>'+sum+'</td>\n'+
    	    		                		
    	    		                		'</tr>')
    	    		                console.log(e);
	                       },error : function () {
	            				alert('lỗi detail')
	                       }
	            		})
	            		
	                });
	                
	                
		        }
	        	
        	},error : function () {
		            alert('lỗi detail');
		        }
		    });
	   }

//    --------------------------------- Tìm kiếm -----------------------------------
    var query = {
        currentPage : 1,
        pageSize : 5,
        bill : {
            code : null,
            create_at : null,
            order_detail_id : 0
        }
    };
    $(document).on('click', '.form-search button', function () {
        $(".form-search form :input").each(function(){
        	var key = $(this).attr('name');
        	var value = ($(this).val() === '') ? null : $(this).val();
        	if(key !== undefined){
        		query['bill'][key] = value;
			}

        });
        getData(query);
    });
    getData(query);
    function getData(query) {
        $.ajax({
            url : 'bill?action=search',
            type : 'POST',
            data : query,
            cache: false,
            success : function (res) {
                updateTable(JSON.parse(res));
            },
            error : function (res) {
                alert('lỗi')
            }
        });
    }
    function updateTable(res){
    	$('.general').children().remove();
        $('.pagination').hide();
        $('.pagination button').removeClass('disabled');

        if(res.list.length > 0 && res.total > 0){
            res.list.forEach(function (item) {
                $('.general').append('<tr>\n' +
                    ' <td>'+item.code+'</td>\n' +
                    ' <td>'+formatDate(new Date(item.createAt), '%d/%M/%Y %H:%m:%s')+'</td>\n' +
                    ' <td>\n' +
                    ' <a bill-code="'+item.code+'" title="Info product"  class="detailP btn-icon-round btn-icon-xsm green waves-effect waves-light"><i class="fa fa-eye"></i></a>\n' +
                    ' </td>\n' +
                    ' </tr>')
            });
            var length = Math.ceil(res.total / query.pageSize);
            $('#select-page').empty();
            for (var i = 0; i < length; i++){
                if(i+1 === parseInt(query.currentPage))
                    $('#select-page').append("<option selected value='"+(i+1)+"'>"+(i+1)+"</option>");
                else
                    $('#select-page').append("<option value='"+(i+1)+"'>"+(i+1)+"</option>");
            }
            $("#select-page").material_select();
            $('.pagination').show();
            if(parseInt(query.currentPage) === 1){
                $('.go-back').addClass('disabled');
            }else if(parseInt(query.currentPage) === length){
                $('.go-to').addClass('disabled');
            }
        }else {
            $('.general').append('<h4>Danh sách trống !!</h4>');
        }
	}

    $(document).on('change', '#select-page', function () {
        query.currentPage = $(this).val();
        getData(query);
    });
    $(document).on('click', '.go-to', function () {
        query.currentPage += 1;
        getData(query);
    });
    $(document).on('click', '.go-back', function () {
        query.currentPage -= 1;
        getData(query);
    });
    $(document).on('change', '#select-page-size', function () {
        query.pageSize = $(this).val();
        query.currentPage = 1;
        getData(query);
    });
    function formatDate(date, fmt) {
        function pad(value) {
            return (value.toString().length < 2) ? '0' + value : value;
        }
        return fmt.replace(/%([a-zA-Z])/g, function (_, fmtCode) {
            switch (fmtCode) {
                case 'Y':
                    return date.getUTCFullYear();
                case 'M':
                    return pad(date.getUTCMonth() + 1);
                case 'd':
                    return pad(date.getUTCDate());
                case 'H':
                    return pad(date.getUTCHours());
                case 'm':
                    return pad(date.getUTCMinutes());
                case 's':
                    return pad(date.getUTCSeconds());
                default:
                    throw new Error('Unsupported format code: ' + fmtCode);
            }
        });
    }
});