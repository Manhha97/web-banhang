$(document).ready(function () {
   function load() {
       var arr = JSON.parse(localStorage.carts);
       $('.cart .product').children().remove();
       var total = 0;
       var count = 0;
       arr.forEach(function (item) {
           total += item.product.price * item.quantity;
           count += item.quantity;
           $('.cart .product').append('<div class="item">' +
               '<input type="hidden" name="p-code" value="'+item.product.code+'">\n' +
               '<input type="hidden" name="p-quantity" value="'+item.quantity+'">\n' +
               '                            <p class="title">\n' +
               '                                <strong>'+item.quantity+' x</strong><a href="" target="_blank">'+item.product.name+'</a>\n' +
               '                                <span class="seller-by">\n' +
               '                                        Cung cấp bởi <strong class="firm">FS Trading</strong>\n' +
               '                                    </span> </p>\n' +
               '                            <p class="price text-right">\n' +
               '                                <span>'+item.product.price+'&nbsp;₫ </span>\n' +
               '                            </p>\n' +
               '                        </div>');
       });
       var ship = parseInt($('input[name="ship_pay"]').val());
       $('.cart .total_price1').text(total);
       $('.cart .total_price2').text(total+ship);
       $('.cart_length').text(count);
   }
   load();
   $(document).on('click', '.edit-address', function () {
       $('.edit-order-detail').show();
   });
   $(document).on('click', '.cancel-edit-address', function () {
       $('.edit-order-detail').hide();
   });
   $(document).on('click', '.save-order', function () {
       $.ajax({
           url : '/order?action=save-order',
           type : 'post',
           data : $('#order-detail').serialize(),
           cache : false,
           success : function (res) {
                if(res!=null){
                    localStorage.setItem("carts", JSON.stringify([]));
                    location.href='order?action=complete&code=' + res;
                }else {
                    alert('lôĩ')
                }
           }
       })
   });
   //Học viện Công nghệ Bưu chính Viễn thông - PTIT, KM9, Hà Đông, Hà Nội, Vietnam
//   ======================================----------------------------=====================================
    google.maps.event.addDomListener(window, 'load', function () {
        var options = {
            types: ['geocode'],
            componentRestrictions: {country: 'vn'}
        };
        var to_places = new google.maps.places.Autocomplete(document.getElementById('address'), options);

        google.maps.event.addListener(to_places, 'place_changed', function () {
            var to_place = to_places.getPlace();
            var to_address = to_place.formatted_address;
            $('#destination').val(to_address);
            calculateDistance();
        });

    });
    // calculate distance
    function calculateDistance() {
        var origin = 'KM9, P. Mộ Lao, Hà Đông, Hà Nội, Vietnam';
        var destination = $('#destination').val();
        var service = new google.maps.DistanceMatrixService();
        service.getDistanceMatrix(
            {
                origins: [origin],
                destinations: [destination],
                travelMode: google.maps.TravelMode.DRIVING,
                unitSystem: google.maps.UnitSystem.IMPERIAL, // miles and feet.
                // unitSystem: google.maps.UnitSystem.metric, // kilometers and meters.
                avoidHighways: false,
                avoidTolls: false
            }, callback);
    }
    // get distance results
    function callback(response, status) {
        if (status != google.maps.DistanceMatrixStatus.OK) {
            $('#result').html(err);
        } else {
            console.log(response);
            //var origin = response.originAddresses[0]; from
            var destination = response.destinationAddresses[0]; //to
            if (response.rows[0].elements[0].status === "ZERO_RESULTS") {
                $('#result').html("Đường chưa mở từ "  + origin + " đến " + destination);
            } else {
                var distance = response.rows[0].elements[0].distance;
                var distance_in_kilo = distance.value / 1000; // the kilom
                //$('#in_kilo').text(distance_in_kilo.toFixed(2));
                $('input[name="distance"]').val(distance_in_kilo);
            }
        }
    }
    // print results on submit the form
    $('#address-info').submit(function(e){
        e.preventDefault();
        var url1 = $(this).attr("action");
        $.ajax({
            url : url1,
            type :'post',
            data : $(this).serialize(),
            cache : false,
            success : function (res) {
                if(res === true || res === 'true'){
                    location.reload();
                }else {

                }
            }
        })
    });
});