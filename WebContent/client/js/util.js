
var all = {
    authors : function () {
        var data;
        $.ajax({
            async : false,
            url : 'admin/product?action=getAuthor',
            type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success : function (res) {
                $('#p_authors').children().remove();
                res.forEach(function (a) {
                    $('#p_authors').append(
                        '<li class="nomargin hiddable col-lg-6">'+
                        '<div class="checker"><span>' +
                        '<input type="checkbox" class="checkbox" name="authorId" id="layered_author_'+a.id+'" value="'+a.id+'"></span></div>'+
                        '<label for="layered_author_'+a.id+'"> '+a.name+'<span> (20)</span> </label>'+
                        '</li>');
                });
                data = res;
            },error : function () {
                alert('lỗi authors')
            }
        });
        return data;
    },
    getAuthorName(item) {
        var au;
        list_authors.forEach(function (a) {
            if(a.id === item)
                au = a.name;
        });
        return au;
    },
    types : function () {
        $.ajax({
            async : false,
            url : 'admin/product?action=getType',
            type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success : function (res) {
                $('#p_types').children().remove();
                res.forEach(function (a) {
                    $('#p_types').append(
                        '<li class="nomargin hiddable col-lg-6">'+
                        '<div class="checker"><span>' +
                        '<input type="checkbox" class="checkbox" name="typeId" id="layered_type_'+a.id+'" value="'+a.id+'"></span></div>'+
                        '<label for="layered_type_'+a.id+'"> '+a.name+'<span> (20)</span> </label>'+
                        '</li>');
                });

            },error : function () {
                alert('lỗi types')
            }
        });
    },
    nxb : function () {
        $.ajax({
            async : false,
            url : 'admin/product?action=getNxb',
            type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success : function (res) {
                $('#p_nxbs').children().remove();
                res.forEach(function (a) {
                    $('#p_nxbs').append(
                        '<li class="nomargin hiddable col-lg-6">'+
                        '<div class="checker"><span>' +
                        '<input type="checkbox" class="checkbox" name="nxbId" id="layered_nxb_'+a.id+'" value="'+a.id+'"></span></div>'+
                        '<label for="layered_nxb_'+a.id+'"> '+a.name+'<span> (20)</span> </label>'+
                        '</li>');
                });
            },error : function () {
                alert('lỗi nxb')
            }
        });
    }
};
var list_authors = all.authors();
all.types();
all.nxb();
var util = {
    getCookie : function (cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for(var i = 0; i <ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    },
};
$('a.dropdown-toggle').click(function (e) {
    e.preventDefault();
   $(this).parent().find('ul.dropdown-menu').toggle();
});
/*------------------------------------------------------- multil language ------------------------------*/
if(!localStorage.language){
    localStorage.setItem("language","vn");
}

setLang();
function setLang(){
    var lang = localStorage.language;
    $('select[name="language"]').val(lang);
    $.getJSON("client/i18n/"+lang+".json", function(res) {
        $('.lang').each(function(index, element){
            $(this).text(res[$(this).attr('key')])
        })
    });
}