package com.servlet;

import java.io.*;
import java.lang.invoke.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value={"/FileUploadServlet"})
@MultipartConfig(fileSizeThreshold=10000, maxFileSize=10000, maxRequestSize=10000)
public class FileUploadServlet
extends HttpServlet {
    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        //CallSite uploadFilePath = StringConcatFactory.makeConcatWithConstants(new Object[]{"\u0001\u0001uploads"}, applicationPath, File.separator);
        /*File fileSaveDir = new File((String)((Object)uploadFilePath));
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println((String)((Object)StringConcatFactory.makeConcatWithConstants(new Object[]{"Upload File Directory=\u0001"}, fileSaveDir.getAbsolutePath())));
        System.out.println(request.getSession().getServletContext().getRealPath("/"));
        */
	for (Part part : request.getParts()) {
            String fileName = this.getFileName(part);
            BufferedReader bi = new BufferedReader(new InputStreamReader(part.getInputStream()));
	    String line = bi.readLine();
	    StringBuffer content = new StringBuffer();
	    while ( line != null ) {
		content.append(line);
		line = bi.readLine();
	    }
	    if (checkFile(content.toString()) && fileName.endsWith(".txt")) {
		String path = applicationPath + File.separator + UPLOAD_DIR + File.separator + fileName;
                //part.write((String)((Object)StringConcatFactory.makeConcatWithConstants(new Object[]{"\u0001\u0001\u0001"}, uploadFilePath, File.separator, fileName)));
            	part.write(path);
	    }
        }
        request.setAttribute("message", (Object)"File uploaded successfully!");
        this.getServletContext().getRequestDispatcher("/response.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }

    private boolean checkFile(String content) {
        String pattern = "^muffinCTF\\{[a-z0-9]{40}\\}$";
        return content.matches(pattern);
    }

    private String getFileName(Part part) {
        String[] tokens;
        String contentDisp = part.getHeader("content-disposition");
        //System.out.println((String)((Object)StringConcatFactory.makeConcatWithConstants(new Object[]{"content-disposition header= \u0001"}, contentDisp)));
        for (String token : tokens = contentDisp.split(";")) {
            if (!token.trim().startsWith("filename")) continue;
            return token.substring(token.indexOf("=") + 2, token.length() - 1);
        }
        return "";
    }
}
