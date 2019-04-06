$(document).ready(function () {
	$('.modal').modal();
	$('.form-search').slideUp();
	$('.display-form').click(function(){
		$(this).children().toggleClass('rote');
		$('.form-search').slideToggle();
	});

    $(document).on('click','.detailP', function(){
    	$.ajax({
			url : 'product?action=getByCode&code=' + $(this).attr('product-code'),
			type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
			success : function (res) {
                if(JSON.stringify(res) !== JSON.stringify({})){
                    $.each(res, function(i, val) {
                        if(i === 'image')
                            $('#modal_detail').find('img').attr('src','file?action=image&name=' + val);
                        $('#modal_detail').find('span[title="'+i+'"]').text(val);
                        if(i === 'authorId')
                            $('#modal_detail').find('span[title="'+i+'"]').text(getAuthorName(val));
                        if(i === 'typeId')
                            $('#modal_detail').find('span[title="'+i+'"]').text(getTypeName(val));
                        if(i === 'nxbId')
                            $('#modal_detail').find('span[title="'+i+'"]').text(getNxbName(val));
                    });
                    $('#modal_detail').modal('open');
				}
            },error : function () {
				alert('lỗi detail')
            }
		})
    });
    var ctx = document.getElementById("myChart").getContext('2d');
    var ctx1 = document.getElementById("myChart1").getContext('2d');
    var product = {
            month: null,
            sum:0,
            price:0,
            produce_code:null,
    
    };
    
    getData(product);
    function getData(product) {
        $.ajax({
            url : 'bill?action=getStatisticMonth',
            type : 'POST',
            data : product,
            cache: false,
            success : function (res) {
            	var e=JSON.parse(res);
            	var month = new Array();
            	var sum = new Array();
                console.log(JSON.parse(res));
                if(e.list.length>0){
                	e.list.forEach(function (item) {
                		month.push(item.month);
                		sum.push(item.sum);
                	});
                }
                
                var myChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: month,
                        datasets: [{
                            label: 'Month',
                            data: sum,
                            backgroundColor: 'rgba(54, 162, 235, 0.2)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 0.5
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero:true
                                }
                            }]
                        }
                    }
                });
                
                
            },
            error : function (res) {
                alert('lỗi')
            }
        });
    }
    getDate(product);
    function getDate(product) {
        $.ajax({
            url : 'bill?action=getStatisticDate',
            type : 'POST',
            data : product,
            cache: false,
            success : function (res) {
            	var e=JSON.parse(res);
            	var month = new Array();
            	var sum = new Array();
                console.log(JSON.parse(res));
                if(e.list.length>0){
                	e.list.forEach(function (item) {
                		month.push(item.month);
                		sum.push(item.sum);
                	});
                }
                new Chart(document.getElementById("chartjs-0"),{
                	"type":"line",
                	"data":{
                		"labels":month,
                		"datasets":[{
                			"label":"Tiền",
                			"data":sum,
                			"fill":false,
                			"borderColor":"rgb(75, 192, 192)",
                			"lineTension":0.1
                			}]},
                	"options":{}});
                
            },error : function (res) {
                alert('lỗi')
            }
        });
    }
        getProduct(product);
        function getProduct(product) {
            $.ajax({
                url : 'bill?action=getStatisticProductMonth',
                type : 'POST',
                data : product,
                cache: false,
                success : function (res) {
                	var e=JSON.parse(res);
                	var month = new Array();
                	var sum = new Array();
                	var price= new Array();
                	var product_code= new Array();
                    console.log(JSON.parse(res));
                    if(e.list.length>0){
                    	e.list.forEach(function (item) {
                    		month.push(item.month+" | "+item.product_code);
                    		sum.push(item.sum);
                    		price.push(item.price);
                    	});
                    }
                    var myChart = new Chart(ctx1, {
                        type: 'bar',
                        data: {
                            labels:month,
                            datasets: [
                            	{
	                                label: 'Số lượng bán',
	                                data: sum,
	                                backgroundColor: 'rgba(255, 99, 132, 0.2)',
	                                borderColor: 'rgba(255,99,132,1)',
	                                borderWidth: 0.5,
                            	},
                            	{
	                                label: 'Tổng tiền',
	                                data: price,
	                                backgroundColor: 'rgba(255, 205, 86, 0.2)',
	                                borderColor: 'rgba(255, 205, 86, 1)',
	                                borderWidth: 0.5,
                            	},
                            	
                        	]
                        },
                        options: {
                        	scales: {
                                xAxes: [{
                                	ticks: {
                                        beginAtZero:true
                                    },
                                    gridLines: {
                                        offsetGridLines: true
                                    }
                                }],
                                yAxes: [{
                                	ticks: {
                                        beginAtZero:true
                                    },
                                    gridLines: {
                                        offsetGridLines: true
                                    }
                                }]
                            }
                            
                        }
                    });
                    
                },error : function (res) {
                    alert('lỗi')
                }
            });
    }
});