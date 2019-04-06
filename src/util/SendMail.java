package util;

import admin.model.Bill;
import admin.model.BillDetail;
import admin.model.Customer;
import admin.model.OrderDetail;
import admin.service.OrderService;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SendMail {
    public boolean send(String to, Bill bill, OrderDetail orderDetail, List<BillDetail> list) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("huychu9977@gmail.com", "*******");
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            message.setFrom(new InternetAddress("langphan2@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Xác nhận đơn hàng "+ bill.getCode()+" ngày "+ bill.getCreateAt());
            String body = "";
            Double total = 0d;
            for (BillDetail bd : list){
                total+= bd.getProduct().getPrice() * bd.getQuantity();
                body +="<tr><td align=\"left\" valign=\"top\" style=\"padding:3px 9px" +
                        "                   <span>"+bd.getProduct().getName()+"</span> <br></td>" +
                        "             <td align=\"left\" valign=\"top\" style=\"padding:3px 9px\">" +
                        "                 <span>"+bd.getProduct().getPrice()+"&nbsp;₫</span> </td>" +
                        "                            <td align=\"left\" valign=\"top\" style=\"padding:3px 9px\">2</td>" +
                        "                         <td align=\"left\" valign=\"top\" style=\"padding:3px 9px\">" +
                        "                                   <span>0&nbsp;₫</span></td>" +
                        "                  <td align=\"right\" valign=\"top\" style=\"padding:3px 9px\">" +
                        "                    <span>"+bd.getProduct().getPrice() * bd.getQuantity()+"&nbsp;₫</span>" +
                        "                    </td></tr>" ;
            }
            String text = "<table><tbody><tr id=\"order-info\">\n" +
                    "                                <td style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px\">\n" +
                    "                                    <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
                    "                                        <thead>\n" +
                    "                                        <tr>\n" +
                    "                                            <th align=\"left\" width=\"50%\" style=\"padding:6px 9px 0px 9px;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;font-weight:bold\">\n" +
                    "                                                Thông tin thanh toán đơn hàng "+bill.getCode()+"\n" +
                    "                                            </th>\n" +
                    "                                            <th align=\"left\" width=\"50%\" style=\"padding:6px 9px 0px 9px;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;font-weight:bold\">\n" +
                    "                                                Địa chỉ giao hàng                                                             </th>\n" +
                    "                                        </tr>\n" +
                    "                                        </thead>\n" +
                    "                                        <tbody>\n" +
                    "                                        <tr>\n" +
                    "                                            <td valign=\"top\" style=\"padding:3px 9px 9px 9px;border-top:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\">\n" +
                    "                                                <span style=\"text-transform:capitalize\">"+orderDetail.getName()+"</span>\n" +
                    "                                                <br> "+orderDetail.getPhone()+"\n" +
                    "                                            </td>\n" +
                    "                                            <td valign=\"top\" style=\"padding:3px 9px 9px 9px;border-top:0;border-left:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\">\n" +
                    "                                                                <span style=\"text-transform:capitalize\">"+orderDetail.getName()+"</span>\n" +
                    "                                                <br> "+orderDetail.getAddress()+"\n" +
                    "                                                <br>  T: "+orderDetail.getPhone()+"\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td valign=\"top\" style=\"padding:7px 9px 0px 9px;border-top:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444\" colspan=\"2\">\n" +
                    "                                                <p style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\">\n" +
                    "\n" +
                    "                                                    <strong>Phương thức thanh toán: </strong>\n" +
                    "                                                    Thanh toán tiền mặt khi nhận hàng\n" +
                    "                                                    <br>                                                                      <strong>Thời gian giao hàng dự kiến:</strong>\n" +
                    "                                                    dự kiến giao hàng vào Thứ tư, 30/01/2019 - không giao ngày lễ                                                                      <br>\n" +
                    "                                                    <strong>Phí vận chuyển: </strong>\n" +
                    "                                                    "+orderDetail.getAmount()* CONSTANT.PRICE_PER_KM+" &nbsp;₫\n" +
                    "                                                    <br>\n" +
                    "                                                </p>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        </tbody>\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <tr id=\"order-detail\">\n" +
                    "                                <td>\n" +
                    "                                    <h2 style=\"text-align:left;margin:10px 0;border-bottom:1px solid #ddd;padding-bottom:5px;font-size:13px;color:#02acea\">\n" +
                    "                                        CHI TIẾT ĐƠN HÀNG</h2>\n" +
                    "\n" +
                    "                                    <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"background:#f5f5f5\">\n" +
                    "                                        <thead>\n" +
                    "                                        <tr>\n" +
                    "                                            <th align=\"left\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">Sản phẩm</th>\n" +
                    "                                            <th align=\"left\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">\n" +
                    "                                                Đơn giá</th>\n" +
                    "                                            <th align=\"left\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">Số lượng</th>\n" +
                    "                                            <th align=\"left\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">Giảm giá</th>\n" +
                    "                                            <th align=\"right\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">Tổng tạm</th>\n" +
                    "                                        </tr>\n" +
                    "                                        </thead>\n" +
                    "                                        <tbody bgcolor=\"#eee\" style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px\">\n" +
                    body+
                    "                                        </tbody>\n" +
                    "                                        <tfoot style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td colspan=\"4\" align=\"right\" style=\"padding:5px 9px\">Tổng tạm tính</td>\n" +
                    "                                            <td align=\"right\" style=\"padding:5px 9px\">\n" +
                    "                                                <span>"+total+"&nbsp;₫</span>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td colspan=\"4\" align=\"right\" style=\"padding:5px 9px\">Phí vận chuyển</td>\n" +
                    "                                            <td align=\"right\" style=\"padding:5px 9px\">\n" +
                    "                                                <span>"+orderDetail.getAmount()* CONSTANT.PRICE_PER_KM+"&nbsp;₫</span>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "\n" +
                    "                                        <tr bgcolor=\"#eee\">\n" +
                    "                                            <td colspan=\"4\" align=\"right\" style=\"padding:7px 9px\">\n" +
                    "                                                <strong>\n" +
                    "                                                    <big>Tổng giá trị đơn hàng</big>\n" +
                    "                                                </strong>\n" +
                    "                                            </td>\n" +
                    "                                            <td align=\"right\" style=\"padding:7px 9px\">\n" +
                    "                                                <strong>\n" +
                    "                                                    <big>\n" +
                    "                                                        <span>"+(total+orderDetail.getAmount()* CONSTANT.PRICE_PER_KM)+"&nbsp;₫</span>\n" +
                    "                                                    </big>\n" +
                    "                                                </strong>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        </tfoot>\n" +
                    "                                    </table> </td> </tr></tbody></table>";
            message.setContent(text, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }


}
