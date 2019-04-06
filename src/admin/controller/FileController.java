package admin.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/admin/file")
public class FileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("image")){
            try{
                String fileName = req.getParameter("name");
                FileInputStream fis = new FileInputStream(new File("C:\\Users\\Manh Ha\\Documents\\upload/"+fileName));
                BufferedInputStream bis = new BufferedInputStream(fis);
                resp.setContentType("image/*");
                BufferedOutputStream output = new BufferedOutputStream(resp.getOutputStream());
                for (int data; (data = bis.read()) > -1;) {
                    output.write(data);
                }
                bis.close();
                fis.close();
            }
            catch(IOException e){

            }
        }else if(action.equalsIgnoreCase("download")){
            String fileName = req.getParameter("name");
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-disposition", "attachment; filename=" + fileName);

            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("D:/upload/"+fileName)));
            // Ghi file ra response outputstream.
            OutputStream outStream = resp.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outStream.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}