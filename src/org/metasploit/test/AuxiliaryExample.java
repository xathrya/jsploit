/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.test;

import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;

import org.metasploit.simple.Console;

import org.metasploit.Metasploit;
import org.metasploit.framework.Framework;
import org.metasploit.framework.Auxiliary;
import org.metasploit.framework.Workspace;
import org.metasploit.framework.OptBase;
import org.metasploit.framework.types.Service;

/**
 *
 * @author hughneale
 */
public class AuxiliaryExample {

    public static void main(String args[]) throws Exception {

        Framework mfw = new Metasploit().Simple().Framework();

        mfw.banner(); // Print banner

        // Create a database connection

        if(!mfw.db().connect()) {
            Console.err("Failed to connect to the database");
        } else {
            Console.pls("Connected.");
        }

        // Set Auxiliary
        // This example will port scan a remote machine. Please note in order
        // to use the SYN Auxiliary module it must be compiled for JRuby from
        // The MSF base.

        //Auxiliary aux = mfw.auxiliaries().create("scanner/portscan/syn");
        Auxiliary aux = mfw.auxiliaries().create("scanner/portscan/tcp");

        Console.out("NAME: " + aux.Name());
        Console.out("FULL: " + aux.FullName());
        Console.out("SHRT: " + aux.ShortName());
        Console.out("TYPE: " + aux.Type());
        Console.out("REFN: " + aux.RefName());
        Console.out("RNKH: " + aux.RankToH());
        Console.out("COMP: " + aux.Compat());
        Console.out("DESC: " + aux.Description());


        Map<String, OptBase> options = aux.Options();
        Iterator it = options.entrySet().iterator();

        // Iterate over them

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            String name = (String) entry.getKey();
            OptBase option = (OptBase) entry.getValue();

            //System.out.println(name + "   " + option.Name());

            if (option.Required()) {
                if (!option.Advanced()) {
                    if (!option.Evasion()) {

                        // Print out only the absoluse required options.

                        Console.out(name + "   " + option.Type());
                    }

                }
            }

        }

        String rhost = "10.0.0.214";

        // Set exploit options

        aux.set("RHOSTS", rhost);
        aux.set("PORTS", "22-25,80,139.445");
        //aux.set("THREADS", "50");
        //aux.set("ShowProgress", true);
        //aux.set("ShowProgressPercent", 1);
        //aux.set("INTERFACE", "en1");

        mfw.auxiliaries().run(aux);

        if(aux.db()) {

            Console.pls("Workspace");

            Workspace work = aux.myworkspace();
            Console.out("Using space: " + work.Name());

            Console.out("TRUE/TCP/10.0.0.214");

            ArrayList services = mfw.db().services(aux.myworkspace(), true, "tcp", rhost);

            Console.pls(services.size() + " service(s) found, for host " + rhost + ".");

            Iterator sv = services.iterator();
            while(sv.hasNext()) {

                Service s = (Service) sv.next();

                Console.out("port " + s.getPort() + " (" + s.getProto() + ") " + s.getState() + " - " + s.getName());

            }
        }
    }
}
