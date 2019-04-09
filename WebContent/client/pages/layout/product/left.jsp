<%--
  Created by IntelliJ IDEA.
  User: HuyChu
  Date: 11/22/2018
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="left_column" class="column col-xs-12" style="width:23%;">
    
    <div id="layered_block_left" class="block">
        <p class="title_block"><span class="lang" key="category"></span></p>
        <div class="block_content">
            <form action="#" id="layered_form">
                <div>
                    <div class="layered_filter">
                        <div class="layered_subtitle_heading"><span class="layered_subtitle"><span class="lang" key="choose"></span></span></div>
                        <ul id="ul_layered_quantity_0" class="col-lg-12 layered_filter_ul">
                            <li class="nomargin hiddable col-lg-6">

                                <div class="checker">
                                    <span>
                                        <input type="checkbox" class="checkbox" name="sale" id="layered_quantity_1" value="1">
                                    </span>
                                </div>
                                <label for="layered_quantity_1"> Đang giảm giá<span> (20)</span> </label>
                            </li>
                        </ul>

                    </div>
                    <div class="layered_filter">
                        <div class="layered_subtitle_heading"><span class="layered_subtitle"><span class="lang" key="author"></span></span></div>
                        <ul id="p_authors" class="col-lg-12 layered_filter_ul">

                        </ul>
                    </div>
                    <div class="layered_filter">
                        <div class="layered_subtitle_heading"><span class="layered_subtitle"><span class="lang" key="type"></span></span></div>
                        <ul id="p_types" class="col-lg-12 layered_filter_ul">

                        </ul>
                    </div>
                    <div class="layered_filter">
                        <div class="layered_subtitle_heading"><span class="layered_subtitle"><span class="lang" key="nxb"></span></span></div>
                        <ul id="p_nxbs" class="col-lg-12 layered_filter_ul">

                        </ul>
                    </div>
                </div>
            </form>
        </div>
        <div id="layered_ajax_loader" style="display: none;">
            <p><img src="client/img/loader.gif" alt=""/> <br/>Loading...</p>
        </div>
        <div class="fb-page" data-href="https://www.facebook.com/News-360-601668583600066/" data-tabs="timeline" data-width="200" data-height="350" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true"><blockquote cite="https://www.facebook.com/News-360-601668583600066/" class="fb-xfbml-parse-ignore"><a href="https://www.facebook.com/News-360-601668583600066/">Fahasa</a></blockquote></div>
    </div>
    

<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v3.2"></script>
</div>
