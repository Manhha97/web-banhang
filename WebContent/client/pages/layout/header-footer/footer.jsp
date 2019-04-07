<div class="footer-container">
    <footer id="footer" class="container">
        <div class="row">
            <div class="inner_container">
                <div class="inner_container_sub">
                    <section id="block_contact_infos" class="footer-block col-xs-12 col-sm-4">
                        <div>
                            <h4>Store Information</h4>
                            <ul class="toggle-footer">
                                <li><i class="icon-map-marker"></i>Km 10, Nguyen Trai, Thanh Xuan, Ha Noi
                                   
                                </li>
                                <li><i class="icon-phone"></i>Call us now: <span>0123-456-789</span></li>
                                <li><i class="icon-envelope-alt"></i>Email: <span><a href="">sudobo@gmail.com</a></span>
                                </li>
                            </ul>
                        </div>
                    </section>
                    <section class="footer-block col-xs-12 col-sm-3" id="tm_links_block1_footer">
                        <h4 class="title_block"><a href="#" title="Information">Information</a></h4>
                        <div class="block_content toggle-footer">
                            <ul class="bullet">
                                <li><a href="#" title="About Us">About Us</a></li>
                                <li><a href="#" title="Delivery Information">Delivery Information</a></li>
                                <li><a href="#" title="Privacy Policy">Privacy Policy</a></li>
                                <li><a href="#" title="Terms and Condition">Terms and Condition</a></li>
                            </ul>
                        </div>
                    </section>
                    <section class="footer-block col-xs-12 col-sm-3">
                        <h4><a href="" title="Manage my customer account" rel="nofollow">My account</a></h4>
                        <div class="block_content toggle-footer">
                            <ul class="bullet">
                                <li><a href="" title="My orders" rel="nofollow">My orders</a></li>
                                <li><a href="" title="My credit slips" rel="nofollow">My credit slips</a></li>
                                <li><a href="" title="My addresses" rel="nofollow">My addresses</a></li>
                                <li><a href="" title="Manage my personal information" rel="nofollow">My personal
                                    info</a></li>
                            </ul>
                        </div>
                    </section>
                    <section id="social_block">
                        <h4>Follow us</h4>
                        <ul>
                            <li class="facebook"><a href="https://www.facebook.com/messages/t/601668583600066"> <span>Facebook</span> </a></li>
                            <li class="twitter"><a target="_blank" href=""> <span>Twitter</span> </a></li>
                            <li class="rss"><a target="_blank" href=""> <span>RSS</span> </a></li>
                        </ul>
                    </section>
                    <div id="paiement_logo_block_left" class="paiement_logo_block"><a href=""> <img
                            src="client/img/logo_paiement_visa.jpg" alt="visa" width="33" height="21"/> <img
                            src="client/img/logo_paiement_mastercard.jpg" alt="mastercard" width="32" height="21"/> <img
                            src="client/img/logo_paiement_paypal.jpg" alt="paypal" width="61" height="21"/> </a></div>
                    <section class="footer-block col-xs-12 col-sm-2" id="block_various_links_footer">
                        <h4>Information</h4>
                        <ul class="toggle-footer">
                            <li class="item"><a href="" title="Specials"> Specials </a></li>
                            <li class="item"><a href="" title="New products"> New products </a></li>
                            <li class="item"><a href="" title="Best sellers"> Best sellers </a></li>
                            <li class="item"><a href="" title="Our stores"> Our stores </a></li>
                            <li class="item"><a href="" title="Contact us"> Contact us </a></li>
                            <li><a href="" title="Sitemap"> Sitemap </a></li>
                        </ul>
                    </section>
                    <section class="bottom-footer col-xs-12">
                        <div>
                            <h6>&copy; 2014 <a class="_blank" href="http://www.prestashop.com">Ecommerce software by
                                PrestaShop™</a></h6>
                        </div>
                    </section>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </footer>
</div>
</div>
<a class="top_button" href="#" style="display:none;">&nbsp;</a>
<script type="text/javascript" src="vendors/jquery/js/jquery.js"></script>
<script type="text/javascript" src="vendors/bootstrap/js/bootstrap.js"></script>
<script src="client/vendors/js/data-script.js"></script>
<script type="text/javascript" src="client/vendors/js/main.js"></script>

<script type="text/javascript" src="client/vendors/js/owl.carousel.js"></script>
<script type="text/javascript" src="client/vendors/js/custom.js"></script>
<script type="text/javascript" src="client/vendors/js/my-script.js"></script>
<script type="text/javascript" src="client/js/util.js"></script>
<script type="text/javascript" src="client/js/ajax_cart.js"></script>
<script type="text/javascript" src="client/js/ajax_wish_list.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
   
<script type="text/javascript">
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.0";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
	function fbLogout() {
		FB.getLoginStatus(function(response) {
	        if (response && response.status === 'connected') {
	            FB.logout(function(response) {
	            	location.replace('/customer?action=logout');
	            });
	        }
	    });
	}
	function loginfb() {
		FB.init({ 
		 	appId : '2034583493504662',
		 	cookie : true,
		 	xfbml : true,
		 	version : 'v2.2'
		 });
        FB.login(function(responses) {
            if(responses.authResponse)
            {
            	FB.api('/me?fields=name,email', function(response) {
            		$.ajax({
  			    	  url : '/customer?action=google-login',
  			     	  type : 'post',
  			     	  data : {
  			     		  id : "fb_"+response.id,
  			     		  email : response.email,
  			     		  name : response.name,
  			     		  type : 'facebook'
  			     	  },
  			     	  success : function(res) {
  			     		 var next_page = $('input[name="next_page"]').val();
  			     		 if(res === true || res === 'true'){
  		                   if(next_page === null || next_page === ''){
  		                       next_page = '/home';
  		                   }
  		                   location.href = next_page;
  		               	}
  			     	  }
  			      })
        		});
            }
        });

    }

///
function simpleLogout(){
	location.replace('/customer?action=logout');
}
/// GOOGLE LOGIN
		var clicked=false;//Global Variable
		function ClickLogin()
		{
		    clicked=true;
		}
		function onSignIn(googleUser) {
		    if (clicked) {
		    	var profile = googleUser.getBasicProfile();
			      $.ajax({
			    	  url : '/customer?action=google-login',
			     	  type : 'post',
			     	  data : {
			     		  id : "gg_"+profile.Eea,
			     		  email : profile.U3,
			     		  name : profile.ig,
			     		  type : 'google'
			     	  },
			     	  success : function(res) {
			     		 var next_page = $('input[name="next_page"]').val();
			     		 if(res === true || res === 'true'){
		                   if(next_page === null || next_page === ''){
		                       next_page = '/home';
		                   }
		                   location.href = next_page;
		               }
			     	  }
			      })
		    }
		};
      function init() {
    	  gapi.load('auth2', function() { // Ready. });
    	})
      }
      function googleLogout() {
    	  gapi.auth2.init()
          gapi.auth2.getAuthInstance().disconnect();
          location.replace('/customer?action=logout');
       }
   </script>
</body>
</html>
