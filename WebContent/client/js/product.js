$(document).ready(function () {
    var owl = $('.owl-carousel');
    owl.owlCarousel({
        items: 1,
        loop: true,
        margin: 10,
        nav: true,
        autoplay: true,
        autoplayTimeout: 4000,
        autoplayHoverPause: true
    });

    function ring() {
        var el = $('.ring');
        el.attr('data-count',1);
        el.addClass('show-count');
        setInterval(function(){
            el.removeClass('notify');
            el.width(el.width());
            el.addClass('notify');
        }, 2000);
    }
    $(document).on('change', 'select[name="language"]', function(){
        localStorage.setItem("language", $(this).val());
        setLang();
    })
//-------------------------------------------- nofication new products ------------------------------
    var uid=util.getCookie('CustomUser').split('&')[0];
    if(!localStorage.getItem(uid)){
        localStorage.setItem(uid, []);
    }
    $.ajax({
        url : '/product?action=get-notification',
        type : 'POST',
        data : {
            arr_code : localStorage.getItem(uid)
        },
        dataType:'json',
        cache :false,
        success : function (res) {
            if(res === true){
                $('.notification').addClass("ring");
                ring();
            }
        },
        error : function (fail) {

        }
    });
    $(document).on('click', '.notification', function () {
        $.ajax({
            url : '/product?action=get-new-product',
            type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success : function (res) {
                $('#notificationModal .list-group').children().remove();
                if(res === null){
                    $('#notificationModal .list-group').append('<p>Chưa có thông báo gì!</p>');
                    $('#notificationModal').modal('show');
                    return;
                }
                if(res.length > 0){
                    var arr_code = [];
                    var arr_seen = localStorage.getItem(uid);
                    res.forEach(function (p) {
                        arr_code.push(p.code);
                        var op ='';
                        if(arr_seen.indexOf(p.code) >-1){
                            op = 'seen';
                        }
                        $('#notificationModal .list-group').append('<div class="list-group-item list-p '+op+'">\n' +
                            '                        <img width="60" height="60" src="/admin/file?action=image&name='+p.image+'" alt="">\n' +
                            '                        <p>\n' +
                            '                            <a href="/product?code='+p.code+'" class="sp">'+p.name+'</a><br>\n' +
                            '                            <span>Tác giả: <span class="sp">'+all.getAuthorName(p.authorId)+'</span> </span><br>\n' +
                            '                            <span>Giá: <span class="sp"></span> '+p.price+'</span> <br>\n' +
                            '                            <span>Sale: <span class="sp"></span> '+p.sale+'</span>\n' +
                            '                        </p>\n' +
                            '                    </div>');
                    })
                    localStorage.setItem(uid, arr_code);
                }else{
                    $('#notificationModal .list-group').append('<p>Chưa có thông báo gì!</p>');
                }
                $('#notificationModal').modal('show');
                $('.notification').removeClass("ring");
                ring();
            },
            error : function (fail) {

            }
        });
    });
// -------------------------------------------- all of products -------------------------------------
    $(document).on('click', '#layered_form input[type="checkbox"]', function () {
        product.query.product[$(this).attr('name')] = $(this).is(':checked') ? $(this).val() : 0;
        product.show();
    });
    $(document).on('change', '#selectProductSort', function () {
        var value_selected = $(this).val().split(':');
        product.query.product.price_sort = null;
        product.query.product.name_sort = null;
        product.query.product[value_selected[0]] = value_selected[1];
        product.show();
    });
    $(document).on('change', '#selectPageSize', function () {
        product.query.pageSize = $(this).val();
        product.show();
    });
    $(document).on('click', '.pagination li', function () {
        product.query.currentPage = $(this).attr('page');
        product.show();
    });
    $(document).on('click', 'button[name="submit_search"]', function () {
        product.query.product.name = $(this).prev().val();
        product.show();
    });
   var product = {
       query : {
           currentPage : 1,
           pageSize : 6,
           product : {
               code : null,
               name : null,
               sale : 0,
               typeId : 0,
               authorId : 0,
               nxbId : 0,
               price_sort : null,
               name_sort : null
           }
       },
       pagination : function (data) {
           $('ul.pagination').children().remove();
           var page = parseInt(product.query.currentPage);
           var htm = '';
           for(i=1; i<=parseInt(data); i++){
               htm += (i === page) ? '<li class="active current"><span> <span>'+i+'</span> </span></li>'
                   : '<li class="" page="'+i+'"><span> <span>'+i+'</span> </span></li>';
           }
           $('ul.pagination').append(htm);
       },
       show : function () {
           $.ajax({
               url     : 'admin/product?action=search',
               type    : 'POST',
               data    : this.query,
               cache   : false,
               beforeSend : function() {
                   $('#center_column').addClass('loading');
               },
               success : function (res) {
                   var products = JSON.parse(res);
                   var html='';
                   products.list.forEach(function (p) {
                       var lblSale = '';
                       if(p.sale !== 0){
                           lblSale += '<span class="sale-box"> <span class="sale-label lang" key="sale">Sale!</span> </span>'
                       }
                       html += '<li class="ajax_block_product col-xs-12 col-sm-6 col-md-4 last-item-of-tablet-line">\n' +
                           '<div class="product-container"><div class="left-block"><div class="product-image-container">\n' +
                           '<a class="product_img_link" href="/product?code='+p.code+'" title="Sem tristique tempus">\n' +
                           '<img class="replace-2x img-responsive" src="admin/file?action=image&name='+p.image+'" alt="Sem tristique tempus" title="Sem tristique tempus" width="220" height="200"/></a>\n' +
                           '<a class="quick-view" href="/product?code='+p.code+'" rel=""><span>Quick view</span></a>\n' +lblSale+
                           '<div class="content_price"><span class="price product-price"> '+p.price+'₫ </span><meta content="1"/></div>\n' +
                           '<span class="new-box"> <span class="new-label">New</span> </span></div></div>\n' +
                           '<div class="right-block"><h5 itemprop="name"><a class="product-name" href="/product?code='+p.code+'" title="'+p.name+'"> '+p.name+'</a></h5>\n' +
                           '<p class="product-desc" itemprop="description"> Lorem ipsum dolor sit amet, consectetur adipiscing\n' +
                           '                        elit. Sed at ante. Mauris eleifend, quam a vulputate dictum, massa quam dapibus leo.</p>\n' +
                           '<div class="color-list-container">\n' +
                           '<span>'+all.getAuthorName(p.authorId)+'</span></div>\n' +
                           '<div class="functional-buttons clearfix"><div class="wishlist">\n' +
                           '<a class="addToWishlist wishlistProd_2" href="/product?code='+p.code+'">Add to Wishlist </a></div></div>\n' +
                           '<div class="content_price"><span class="price product-price"> '+p.price+'₫ </span><meta content="1"/></div>\n' +
                           '<div class="button-container">\n' +
                           '<a class="button ajax_add_to_cart_button btn btn-default" href="" rel="nofollow" title="Add to cart" product-code="'+p.code+'">\n' +
                           '<span>Add to cart</span></a>\n' +
                           '<a class="button lnk_view btn btn-default" href="" title="View"><span>More</span> </a></div>\n' +
                           '<div class="product-flags"></div>\n' +
                           '</div></div></li>';
                   });
                   $('.product_list').children().remove().append(html);
                   $('.product_list').append(html);
                   $('#product-count').text(products.list.length);
                   //pagination
                   var length = Math.ceil(products.total / product.query.pageSize);
                   product.pagination(length);
                   $('#center_column').removeClass('loading');
                   $('html, body').animate({scrollTop: '500px'}, 500);
               },
               error   : function (res) {
                   alert('lỗi')
               }
           });
       }
   };
   product.show();



});