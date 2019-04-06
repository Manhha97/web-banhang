/*! Customized Jquery from Mahesh Vaghani.  mahesh@templatemela.com  : www.templatemela.com
Authors & copyright (c) 2013: TemplateMela - Megnor Computer Private Limited. */
// Megnor Start
$(document).ready(function () {

    //=================== Show or hide Go Top button ========================//
    $(window).scroll(function() {
        if ($(this).scrollTop() > 500) {
            $('.top_button').fadeIn(500);
        } else {
            $('.top_button').fadeOut(500);
        }
    });
    $('.top_button').click(function(event) {
        event.preventDefault();
        $('html, body').animate({scrollTop: 0}, 800);
    });

    /*======  curosol For Manufacture ==== */
    var tmbrand = $("#manufacturer-carousel");
    tmbrand.owlCarousel({
        items : 4, //10 items above 1000px browser width
        itemsDesktop : [1000,3],
        itemsDesktopSmall : [767,2],
        itemsTablet: [479,1],
        itemsMobile : [319,1]
    });
    // Custom Navigation Events
    $(".manufacturer_next").click(function(){
        tmbrand.trigger('owl.next');
    })
    $(".manufacturer_prev").click(function(){
        tmbrand.trigger('owl.prev');
    })

    /*======  curosol For Feature Product ==== */
    var tmfeature = $("#feature-carousel");
    tmfeature.owlCarousel({
        items : 3, //10 items above 1000px browser width
        itemsDesktop : [1000,2],
        itemsDesktopSmall : [767,2],
        itemsTablet: [479,1],
        itemsMobile : [319,1]
    });
    // Custom Navigation Events
    $(".feature_next").click(function(){
        tmfeature.trigger('owl.next');
    })
    $(".feature_prev").click(function(){
        tmfeature.trigger('owl.prev');
    })


    /*======  Curosol For New PRoduct ==== */
    var tmnewProduct = $("#newproduct-carousel");
    tmnewProduct.owlCarousel({
        items : 3, //10 items above 1000px browser width
        itemsDesktop : [1000,2],
        itemsDesktopSmall : [767,2],
        itemsTablet: [479,1],
        itemsMobile : [319,1]
    });
    // Custom Navigation Events
    $(".newproduct_next").click(function(){
        tmnewProduct.trigger('owl.next');
    })
    $(".newproduct_prev").click(function(){
        tmnewProduct.trigger('owl.prev');
    })


    /*======  curosol For Accessories Product ==== */
    var tmaccessories = $("#accessories-carousel");
    tmaccessories.owlCarousel({
        items : 3, //10 items above 1000px browser width
        itemsDesktop : [1000,2],
        itemsDesktopSmall : [767,2],
        itemsTablet: [479,1],
        itemsMobile : [319,1]
    });
    // Custom Navigation Events
    $(".accessories_next").click(function(){
        tmaccessories.trigger('owl.next');
    })
    $(".accessories_prev").click(function(){
        tmaccessories.trigger('owl.prev');
    })

    /*======  curosol For Category Product ==== */
    var tmproductcategory = $("#productcategory-carousel");
    tmproductcategory.owlCarousel({
        items : 3, //10 items above 1000px browser width
        itemsDesktop : [1000,3],
        itemsDesktopSmall : [767,3],
        itemsTablet: [479,3],
        itemsMobile : [319,2]
    });
    // Custom Navigation Events
    $(".productcategory_next").click(function(){
        tmproductcategory.trigger('owl.next');
    })
    $(".productcategory_prev").click(function(){
        tmproductcategory.trigger('owl.prev');
    })

    /*======  curosol For Crosssel Product ==== */
    var tmcrossselling = $("#crossselling-carousel");
    tmcrossselling.owlCarousel({
        items : 3, //10 items above 1000px browser width
        itemsDesktop : [1000,3],
        itemsDesktopSmall : [767,3],
        itemsTablet: [479,3],
        itemsMobile : [319,2]
    });
    // Custom Navigation Events
    $(".crossselling_next").click(function(){
        tmcrossselling.trigger('owl.next');
    })
    $(".crossselling_prev").click(function(){
        tmcrossselling.trigger('owl.prev');
    })

});
// Megnor End

function responsivecolumn()
{
    if ($(document).width() <= 767)
    {
        $('.container #columns_inner #left_column').appendTo('.container #columns_inner');
    }
    else if($(document).width() >= 768)
    {
        $('.container #columns_inner #left_column').prependTo('.container #columns_inner');
    }
}
$(document).ready(function(){responsivecolumn();});
$(window).resize(function(){responsivecolumn();});
