$('.collapsible li').each(function () {
    $(this).removeClass('active');
    if($(this).attr('title') === pageName){
        $(this).addClass('active');
        document.title = $(this).find('span').text();
    }
});
    //	----------------------------------------------- get tác giả ----------------------------------------
    var authors ;
    $.ajax({
        async : false,
        url : 'product?action=getAuthor',
        type : 'POST',
        dataType: 'json',
        contentType: 'application/json',
        success : function (res) {
            authors = res;
            $('#select_authorId').empty();
            $('#select_authorId').append("<option value='0'>Chọn tác giả</option>");
            $.each(res, function(i, val){
                $('#select_authorId').append("<option value='"+val.id+"'>"+val.name+"</option>");
            });
            $("#select_authorId").material_select();
        },error : function () {
            alert('lỗi detail')
        }
    });
    function getAuthorName(item) {
        var au;
        $.each(authors, function(i, val){
            if(val.id === item)
                au = val.name;
        });
        return au;
    }
    //	----------------------------------------------- get thể loại ----------------------------------------
    var types;
    $.ajax({
        async : false,
        url : 'product?action=getType',
        type : 'POST',
        dataType: 'json',
        contentType: 'application/json',
        success : function (res) {
            types = res;
            $('#select_typeId').empty();
            $('#select_typeId').append("<option required value='0'>Chọn thể loại</option>");
            $.each(res, function(i, val){
                $('#select_typeId').append("<option value='"+val.id+"'>"+val.name+"</option>");
            });
            $("#select_typeId").material_select();

        },error : function () {
            alert('lỗi detail')
        }
    });
    function getTypeName(item) {
        var au;
        $.each(types, function(i, val){
            if(val.id === item)
                au = val.name;
        });
        return au;
    }
    //	----------------------------------------------- get nhà xuất bản ----------------------------------------
    var nxbs;
    $.ajax({
        async : false,
        url : 'product?action=getNxb',
        type : 'POST',
        dataType: 'json',
        contentType: 'application/json',
        success : function (res) {
            nxbs = res;
            $('#select_nxbId').empty();
            $('#select_nxbId').append("<option value='0'>Chọn nhà xuất bản</option>");
            $.each(res, function(i, val){
                $('#select_nxbId').append("<option value='"+val.id+"'>"+val.name+"</option>");
            });
            $("#select_nxbId").material_select();

        },error : function () {
            alert('lỗi detail')
        }
    });
    function getNxbName(item) {
        var au;
        $.each(nxbs, function(i, val){
            if(val.id === item)
                au = val.name;
        });
        return au;
    }
