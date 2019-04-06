$(document).ready(function () {
    function readURL(input, _this) {
        // debugger
         var filename = _this.val().split('\\').pop();
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {
                _this.parents('.file-upload').find('.file-name').text(filename);
                _this.parents('.file-upload').prev().attr('src', e.target.result);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
    $(document).on('change', '.file-upload input[type="file"]', function () {
        readURL(this, $(this));
    });
// ------------------------------------------detail-------------------------------------------
    if(code !== ''){
        $.ajax({
            url : 'product?action=getByCode&code=' + code,
            type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success : function (res) {
                console.log(res);
                if(JSON.stringify(res) !== JSON.stringify({})){
                    $.each(res, function(i, val) {
                        if(i === 'image'){
                            $('.form-add').find('img').attr('src','file?action=image&name=' + val);
                            $('.file-name').text(val);
                        }else if(i.indexOf('Id') > -1){
                            $('#select_'+i).val(val);
                            $('#select_'+i).material_select();
                        }else{
                            $('.form-add').find('input[name="'+i+'"]').val(val);
                            $('.form-add').find('label[key="'+i+'"]').addClass('active');
                        }
                    });
                }
            },error : function () {
                alert('lỗi detail')
            }
        })
    }else{

    }
//-------------------------------------------------submit form----------------------------------------
    function mySubmit(form, event) {
        event.preventDefault();
        var file = $(form).find('input[type="file"]')[0].files[0];
        // if(!file){
        //     alert("Vui lòng chọn file");
        //     return;
        // }
        $.ajax({
            url: 'product?action=update',
            type: 'POST',
            data: new FormData($(form)[0]),
            processData: false,
            contentType: false,
            success: function (res) {
                console.log(res);
                if(res === 'true'){
                    Materialize.toast('Update thành công!', 1000);
                    setTimeout(function(){
                        location.replace('/admin/product');
                    }, 1200);

                }else{
                    Materialize.toast('Update thất bại!', 1000);
                }
            },
            error: function (res) {
                alert('lỗi')
            }
        });
    };
// ----------------------------------------------- validate ------------------------------------------
    $.extend($.validator.messages, {
        required: " (*)"
    });

    $.validator.setDefaults({
        ignore: []
    });

    $('.form-add').validate({
        submitHandler: mySubmit,
        errorClass: "invalid form-error",
        errorElement: 'span',
        errorPlacement: function(error, element) {
            element.parents('.input-field').find('label').append(error);
        }
    });

});
