/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkmn;


import java.io.PrintWriter;

/**
 *
 * @author Bambi^
 */
public class Pkmn {

	/**
	 * @param args the command line arguments
	 */
	private String auth = null;
	private String user = null;
	private String password = null;
	private String location = null;
	private String steps = null;
Pkmn(String auth, String user, String password, String location, String steps)
	{
		this.auth = auth;
		this.user = user;
		this.password = password;
		this.location = location;
		this.steps = steps;
	}	
public void run () throws Exception
{
    Process p = Runtime.getRuntime().exec("cmd /K");
	new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
    PrintWriter stdin = new PrintWriter(p.getOutputStream());
    stdin.println("cd \"C:\\Users\\Bambi^\\Downloads\\PokemonGo-Map-master\\\"");
	stdin.println("python example.py -a "+ auth + " -u "+ user + " -p " + password + " -l \"" + location + "\" -st " + steps + " -dg -ol -ar 3");
    stdin.close();

  //  int returnCode = p.waitFor();
  //  System.out.println("Return code = " + returnCode);
}
	
}
