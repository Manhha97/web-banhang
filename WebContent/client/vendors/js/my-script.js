$(document).ready(function () {
    $('.alert').alert();
    $(document).on('click', '#layered_form input[type=button], #layered_form label.layered_color', function(e) {
        if (!$('input[name=' + $(this).attr('name') + '][type=hidden]').length)
            $('<input />').attr('type', 'hidden').attr('name', $(this).attr('name')).val($(this).attr('rel')).appendTo('#layered_form');
        else
            $('input[name=' + $(this).attr('name') + '][type=hidden]').remove();
        alert('mau')
    });
    $('#layered_block_left label a').on({
        click: function(e) {
            e.preventDefault();
            var disable = $(this).parent().parent().find('input').attr('disabled');
            if (disable == '' || typeof(disable) == 'undefined' || disable == false) {
                $(this).parent().parent().find('input').click();
            }
        }
    });
    $(document).on('click', '#layered_form .select', function(e) {

    });
    $(document).on('click', '#layered_form input[type=checkbox], #layered_form input[type=radio]', function(e) {
        updateChecked(this);
    });
    function updateChecked(_this){

        $(_this).parents('ul').find('input[type="checkbox"]').not(_this).prop('checked', false);
        $(_this).parents('ul').find('input[type="checkbox"]').not(_this).parent().removeClass('checked');
        if ($(_this).is(':checked')){
            $(_this).parent().addClass('checked');
        }else{
            $(_this).parent().removeClass('checked');
        }
    }


    $(document).off('change').on('change', 'select[name=n]', function(e) {
        $('select[name=n]').val($(this).val());
    });
    $('#block_top_menu li').hover( function () {
            $(this).children('ul').slideDown();
        }, function () {
            $(this).children('ul').slideUp();
        }
    )
});