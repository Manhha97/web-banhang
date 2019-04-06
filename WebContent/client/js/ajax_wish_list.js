$(document).ready(function () {
    $('.alert_wish_list').hide();
    function create_filename() {
        var dt = new Date();
        var dmy = dt.getDate()+'_'+(dt.getMonth()+1)+'_'+dt.getFullYear();
        var hms = dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
        return 'wishlist_'+dmy+'_'+hms+'.xml';
    }
    function dowload_XML(data, filename) {
        var bb = new Blob([data], {type: 'text/plain'});
        var url = window.URL.createObjectURL(bb);
        //  window.location.assign(url); sử dụng thay thẻ a nhưng ko có tên file
        var a = document.createElement('a');
        a.download = filename;
        a.href = url;
        a.click();
        window.URL.revokeObjectURL(url);
    };
    //-------------------------------------------- carts -----------------------------------------------
    var wish_list = {
        add : function (product) {
            var arr3 = JSON.parse(localStorage.getItem("wish_list"));
            var check = true;
            arr3.forEach(function(item){
                if(item.product.code === product.code){
                    check = false;
                }
            });
            if(check === true){
                arr3.push({
                    'product' : product
                })
            }
            localStorage.setItem("wish_list", JSON.stringify(arr3));
            this.updatTotal();
            $('.alert_wish_list').show(300);
            $('html, body').animate({scrollTop: '10px'}, 500);
            setTimeout(function() {
                $('.alert_wish_list').hide(200);
            }, 2000);
        },
        remove : function (product) {
            var arr3 = JSON.parse(localStorage.getItem("wish_list"));
            arr3.forEach(function(item, index){
                if(item.product.code === product.code){
                    arr3.splice(index,1)
                }
            });
            localStorage.setItem("wish_list", JSON.stringify(arr3));
            this.updatTotal();
            this.updateContent();
        },
        updatTotal : function(){
            var arr = JSON.parse(localStorage.getItem("wish_list"));
            $('#count-wish-list').text(arr.length);
        },
        updateContent : function () {
            this.updatTotal();
            var content = $('#wishlist_table tbody');
            content.children().remove();
            $('#download_wishlist').hide();
            var cart_arr = JSON.parse(localStorage.getItem("wish_list"));
            if(cart_arr.length === 0){
                content.append('<h1>Danh sách hàng trống !!</h1>');
            }else {
                cart_arr.forEach(function (item) {
                    content.append('<tr>\n' +
                        '            <td class="text-center">\n' +
                        '                <a href=""><img width="49" height="49" src="admin/file?action=image&name='+item.product.image+'" alt="Aliquam volutpat" title="Aliquam volutpat"></a>\n' +
                        '            </td>\n' +
                        '            <td class="text-left"><a href="">'+item.product.name+'</a></td>\n' +
                        '            <td class="text-left">'+all.getAuthorName(item.product.authorId)+'</td>\n' +
                        '            <td class="text-right">'+item.product.sale+' %</td>\n' +
                        '            <td class="text-right">              <div class="price">\n' +
                        '                <b>'+item.product.price+'</b> ' +
                        '            </div>\n' +
                        '            </td>\n' +
                        '            <td class="text-right"><a product-code="'+item.product.code+'" title="Add to cart" class="btn btn-primary ajax_add_to_cart_button"><i class="fa fa-shopping-cart"></i></a>\n' +
                        '                <a product-code="'+item.product.code+'" href="" title="Remove" class="btn btn-danger remove_on_wishlist"><i class="fa fa-times"></i></a></td>\n' +
                        '        </tr>')
                })
                $('#download_wishlist').show();
            }
        },
        download : function () {
            var list = JSON.parse(localStorage.getItem("wish_list"));
            if(list.length > 0){
                var data = '<list>';
                var filename = 'wishlist.xml';
                list.forEach(function (item) {
                    var p = item.product;
                    data += '<product><product-name>'+p.name+'</product-name>'+'<product-price>'+p.price+'</product-price>';
                    data += '<product-author>'+all.getAuthorName(p.authorId)+'</product-author>'+'<product-sale>'+p.sale+'</product-sale></product>';
                });
                data+='</list>';
                dowload_XML(data, create_filename());
            }
        }
    };
    if(!localStorage.wish_list){
        localStorage.setItem("wish_list", JSON.stringify([]));
    }
    wish_list.updatTotal();
    $(document).on('click', '.wishlist_add', function (e) {
        e.preventDefault();
        $.ajax({
            url : 'admin/product?action=getByCode&code=' + $(this).attr('product-code'),
            type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success : function (res) {
                if(JSON.stringify(res) !== JSON.stringify({})){
                    wish_list.add(res);
                }
            },error : function () {
                alert('lỗi detail cart')
            }
        })
    });
    $(document).on('click', '.remove_on_wishlist', function (e) {
        e.preventDefault();
        var product = {
            code : $(this).attr('product-code')
        };
        wish_list.remove(product);
    });
    $(document).on('click', '#download_wishlist', function () {
        wish_list.download();
    })
    if($('#wishlist_table'))
        wish_list.updateContent();
});