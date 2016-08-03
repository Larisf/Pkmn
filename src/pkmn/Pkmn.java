package pkmn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private TextArea console;
	private String path;

	public Pkmn(String auth, String user, String password, String location, String steps, String refresh, String bGym, String bLured, TextArea console, String path)
	{
			this.auth = auth;
			this.user = user;
			this.password = password;
			this.location = location;
			this.steps = Integer.parseInt(steps);
			this.refresh = Integer.parseInt(refresh);
			this.gym = bGym;
			this.lured = bLured;
			this.console = console;
			this.path = path;
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

		if((auth.toUpperCase().equals("PTC"))||(auth.toUpperCase().equals("GOOGLE")))
			if(user != null)
				if(password != null)
					if(location != null)
						if(steps != 0)
							if(refresh != 0)
							{
								//stdin.println("cd \"C:\\Users\\Bambi^\\Downloads\\PokemonGo-Map-master\\\"");
								stdin.println("cd \""+path+"\"");
								stdin.println("python example.py -a "+ auth + " -u "+ user + " -p " + password + " -l " + "\""+ location + "\"" + " -st " + steps + " "+ gym+ " "+ lured + " -ar "+ refresh+" -H "+ip+" -P "+port);
								stdin.close();
							}
								else console.appendText("Bitte einen Wert größer 0 eingeben!\n");
							else console.appendText("Bitte einen Radius größer 0 eingeben!\n");
					else console.appendText("Bitte Region eingeben!\n");
				else console.appendText("Bitte Passwort eingeben!\n");
			else console.appendText("Bitte Account namen eingeben!\n");
		else console.appendText("Bitte google oder ptc eingeben!\n");
        stdin.close();
		}	
}
