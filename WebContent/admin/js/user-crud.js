$(document).ready(function () {
    $('.modal').modal();
    $('.form-search').slideUp();
    $('.display-form').click(function(){
        $(this).children().toggleClass('rote');
        $('.form-search').slideToggle();
    });

    $(document).on('click','.detailP', function(){
        $.ajax({
            url : 'user?action=getByCode&code=' + $(this).attr('user-code'),
            type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success : function (res) {
                if(JSON.stringify(res) !== JSON.stringify({})){
                    $.each(res, function(i, val) {
                        if(i === 'profile')
                            $('#modal_detail').find('img').attr('src','file?action=image&name=' + val);
                        $('#modal_detail').find('span[title="'+i+'"]').text(val);
                        if(i === 'role_id')
                            $('#modal_detail').find('span[title="'+i+'"]').text(getRoleName(val));
                    });
                    $('#modal_detail').modal('open');
                }
            },error : function () {
                alert('lỗi detail')
            }
        })
    });
//    --------------------------------- Tìm kiếm -----------------------------------
    var query = {
        currentPage : 1,
        pageSize : 5,
        user : {
            code : null,
            name : null,
            birthday : null,
            address : null,
            role_id:0,
            email: null,
            password: null,
        }
    };
    $(document).on('click', '.form-search button', function () {
        $(".form-search form :input").each(function(){
            var key = $(this).attr('name');
            var value = ($(this).val() === '') ? null : $(this).val();
            if(key !== undefined){
                query['user'][key] = value;
            }

        });
        getData(query);
    });
    getData(query);
    function getData(query) {
        $.ajax({
            url : 'user?action=search',
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
        $('tbody').children().remove();
        $('.pagination').hide();
        $('.pagination button').removeClass('disabled');

        if(res.list.length > 0 && res.total > 0){
            res.list.forEach(function (item) {
                $('tbody').append('<tr>\n' +
                    ' <td>'+item.code+'</td>\n' +

                    ' <td>'+item.name+'</td>\n' +
                    ' <td>'+item.email+'</td>\n' +
                    ' <td>'+item.address+'</td>\n' +
                    ' <td>\n' +
                    ' <a user-code="'+item.code+'" title="Info user"  class="detailP btn-icon-round btn-icon-xsm green waves-effect waves-light"><i class="fa fa-eye"></i></a>\n' +
                    ' <a title="Edit product" href="/admin/user?action=update&code='+item.code+'" class="editP btn-icon-round btn-icon-xsm cyan1 waves-effect waves-light"><i class="fa fa-edit"></i></a>\n' +
                    ' <a user-code="'+item.code+'" title="Delete user" href="javascript:;" class="removeP btn-icon-round btn-icon-xsm orangered waves-effect waves-light"><i class="fa fa-trash"></i></a>\n' +
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
            $('tbody').append('<h4>Danh sách trống !!</h4>');
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
//=============================================================---- Xóa ----==============================================
    $(document).on('click','.removeP', function(){
        var code = $(this).attr('user-code');
        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this imaginary file!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then(function(willDelete){
                if (willDelete) {
                    $.ajax({
                        url: 'user?action=delete',
                        type: 'POST',
                        data: {code : code},
                        cache: false,
                        success: function (res) {
                            console.log(res);
                            if(res === 'true'){
                                swal("Xóa thành công!", {
                                    icon: "success",
                                });
                                getData(query);
                            }else{
                                swal("Xóa thất bại!", {
                                    icon: "error",
                                });
                            }
                        },
                        error: function (res) {
                            alert('lỗi')
                        }
                    });
                }
            });
    });
});