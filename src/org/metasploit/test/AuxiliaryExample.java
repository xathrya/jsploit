/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.test;

import java.util.Map;
import java.util.Iterator;

import org.metasploit.simple.Console;
import org.metasploit.simple.Container;

import org.metasploit.Metasploit;
import org.metasploit.framework.Framework;
import org.metasploit.framework.Auxiliary;
import org.metasploit.framework.Workspace;
import org.metasploit.framework.OptBase;

/**
 *
 * @author hughneale
 */
public class AuxiliaryExample {

    public static void main(String args[]) throws Exception {

        Container cont = new Container();
        cont.setDirectory("/Users/hughneale/NetBeansProjects/netshark/lib/"); //
        cont.Reload();

        Framework mfw = new Metasploit(cont).Simple().Framework();

        mfw.banner(); // Print banner

        // Create a database connection

        if(!mfw.db().connect()) {
            Console.err("Failed to connect to the database");
        } else {
            Console.out("Connected.");
        }

        // Set payload and exploit
        // This example exploits an issue within XP SP0 SMB (CIFS).

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

        // Set exploit options

        aux.set("RHOSTS", "92.48.91.47");
        aux.set("PORTS", "80");
        aux.set("THREADS", "50");
        aux.set("ShowProgress", true);
        aux.set("ShowProgressPercent", 1);

        mfw.auxiliaries().run(aux);

        if(aux.db()) {

            Console.pls("Workspace");

            Workspace work = aux.myworkspace();
            Console.out("Using space: " + work.Name());

            Console.pls("Reading database.");

            mfw.events().db_event_subscribers();
            mfw.db().services();

        }

    }

}
