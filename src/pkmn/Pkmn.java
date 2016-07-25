/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkmn;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

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
	private int steps = 5;
	public static void main(String[] args) throws IOException, InterruptedException {
		JOptionPane.showInputDialog(null,"Authentifikationsmethode","ptc order google: ", JOptionPane.QUESTION_MESSAGE);
		String[] command =
    {
        "cmd",
    };
    Process p = Runtime.getRuntime().exec(command);
	new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
    PrintWriter stdin = new PrintWriter(p.getOutputStream());
    stdin.println("cd \"C:\\Users\\Bambi^\\Downloads\\PokemonGo-Map-master\\\"");
	//stdin.println("python example.py -a "+ auth + " -u "+ user + " -p " + password + " -l \"" + location + "\" -st " + steps + " -dg -ol -ar 3");
    stdin.close();
    int returnCode = p.waitFor();
    System.out.println("Return code = " + returnCode);
	}
	
}
