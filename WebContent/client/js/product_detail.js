$(document).ready(function () {
    $('.stars li').on('mouseover', function(){
        var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on

        // Now highlight all the stars that's not after the current hovered star
        $(this).parent().children('li.star').each(function(e){
            if (e < onStar) {
                $(this).addClass('hover');
            }
            else {
                $(this).removeClass('hover');
            }
        });

    }).on('mouseout', function(){
        $(this).parent().children('li.star').each(function(e){
            $(this).removeClass('hover');
        });
    });


    /* 2. Action to perform on click */
    $('.stars li').on('click', function(){
        var onStar = parseInt($(this).data('value'), 10); // The star currently selected
        var stars = $(this).parent().children('li.star');

        for (i = 0; i < stars.length; i++) {
            $(stars[i]).removeClass('selected');
        }
        for (i = 0; i < onStar; i++) {
            $(stars[i]).addClass('selected');
        }
        // JUST RESPONSE (Not needed)
        var ratingValue = parseInt($('.stars li.selected').last().data('value'), 10);
        var msg = "";
        $('input[name="star-vote"]').val(ratingValue);
        responseMessage(msg);

    });


    function responseMessage(msg) {
        $('.success-box').fadeIn(200);
        $('.success-box div.text-message').html("<span>" + msg + "</span>");
    }
    $(document).on('submit','#add-comment', function (e) {
        e.preventDefault();
        var _this = $(this);
        $.ajax({
            url : '/product?action=add-comment',
            type : 'post',
            data : _this.serialize(),
            cache : false,
            dataType:'json',
            success : function (res) {
                if(res !== false){
                    var s = '';
                    for (var i=0;i<parseInt(res.voteStar);i++){
                        s+='<li class=\'star selected\'>' +
                            '   <i class=\'fa fa-star\'></i></li>';
                    }
                    $('.list-comment').append('<li class="list-group-item">\n' +
                        '                <p class="image">\n' +
                        '                    <img src="/client/img/avatar.png" height="45" width="45" alt="">\n' +
                        '                    <span>\n' +
                        '                            <span>'+res.customer.name+'</span><br>\n' +
                        '                            <span>'+res.createAt+'</span>\n' +
                        '                        </span>\n' +
                        '                </p>\n' +
                        '                <p>'+res.comment+'</p>\n' +
                        '<div class=\'rating-stars text-center\'>\n' +
                        ' <ul class=\'stars\'>\n' +s+
                        '</ul></div></li>');
                    _this.find('input[name="comment"]').val('');
                }
            }
        })
    })
});