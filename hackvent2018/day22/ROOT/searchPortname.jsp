<%@ page import="java.util.*,java.security.*,java.io.*"%>


<%
    if (request.getParameter("port") != null) {
	String port = request.getParameter("port");
	if (port.contains(";")
	||  port.contains("&")
	||  port.contains("|"))
	{
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
		return;
	}

	String[] command = {"nslookup", port};
	Process p = Runtime.getRuntime().exec(command);
	BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        p.waitFor();
	String disr = in.readLine();
        while ( disr != null ) {
            out.println(disr);
            disr = in.readLine();
        }
    }
%>
