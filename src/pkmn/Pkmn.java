package pkmn;

import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.TextArea;

public class Pkmn
{
	private static String auth = null;
	private static String user = null;
	private static String password = null;
	private static String location = null;
	private static int steps = 0;
	private static int refresh = 0;
	private static String ip = "127.0.0.1";
	private static int port = 5000;
	private static String gym = null;
	private static String lured = null;
	private static String path;
	private static String exec;
	private static final String gmapsKey = "AIzaSyCxpcyqb2YUz_Il-DC1H_eucylE64xIL0w" ;

	public Pkmn(String exec,String auth, String user, String password, String location, String steps, String refresh, String bGym, String bLured, TextArea console, String path)
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
		stdin.println("python "+exec+" -a "+ auth + " -u "+ user + " -p " + password + " -l " + "\""+ location + "\"" + " -st " + steps + " "+ gym+ " "+ lured + " -sd "+ refresh+" -H "+ip+" -P "+port+" -k " +gmapsKey);
		stdin.close(); 
	}	
}
