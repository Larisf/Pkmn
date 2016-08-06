package pkmn;

import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.TextArea;

public class Pkmn
{
	private String auth = null;
	private String user = null;
	private String password = null;
	private String location = null;
	private int steps = 0;
	private int refresh = 0;
	private String ip = "127.0.0.1";
	private int port = 5000;
	private String gym = null;
	private String lured = null;
	private final String path;
	private final String exec;
	private static final String GMAPSKEY = "AIzaSyCxpcyqb2YUz_Il-DC1H_eucylE64xIL0w" ;

	public Pkmn(String exec,String auth, String user, String password, String location, String steps, String refresh, String bGym, String bLured, TextArea console, String path, String port, String ip)
	{
		this.auth = auth;
		this.user = user;
		this.password = password;
		this.location = location;
		this.steps = Integer.parseInt(steps);
		this.refresh = Integer.parseInt(refresh);
		this.gym = bGym;
		this.lured = bLured;
		this.path = path;
		this.exec = exec;
		this.ip = ip;
		this.port = Integer.parseInt(port);
	}
	
	public void run () throws IOException, InterruptedException
	{
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("cmd");
		Thread error = new Thread(new SyncPipe(p.getErrorStream(), System.out));
		error.start();
		Thread out = new Thread(new SyncPipe(p.getInputStream(), System.out));
		out.start();
	    PrintWriter stdin = new PrintWriter(p.getOutputStream());
		stdin.println("cd \""+path+"\"");
		stdin.println("python "+exec+" -a "+ auth + " -u "+ user + " -p " + password + " -l " + "\""+ location + "\"" + " -st " + steps + " "+ gym+ " "+ lured + " -sd "+ refresh+" -H "+ip+" -P "+port+" -k " +GMAPSKEY);
		stdin.close(); 
	}	
}
