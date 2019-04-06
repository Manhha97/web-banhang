$('.collapsible li').each(function () {
    $(this).removeClass('active');
    if($(this).attr('title') === pageName){
        $(this).addClass('active');
    }
});
    //	----------------------------------------------- get tác giả ----------------------------------------
    var role ;
    $.ajax({
        async : false,
        url : 'user?action=getRole',
        type : 'POST',
        dataType: 'json',
        contentType: 'application/json',
        success : function (res) {
            role = res;
            console.log(res)
            $('#select_role_id').empty();
            $('#select_role_id').append("<option value='0'>Chọn vị trí</option>");
            $.each(res, function(i, val){
                $('#select_role_id').append("<option value='"+val.id+"'>"+val.name+"</option>");
            });
            $("#select_role_id").material_select();
        },error : function () {
            alert('lỗi detail')
        }
    });
    function getRoleName(item) {
        var au;
        $.each(role, function(i, val){
            if(val.id === item)
                au = val.name;
        });
        return au;
    }
   