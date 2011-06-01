/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.test;

import org.metasploit.Metasploit;
import org.metasploit.framework.Framework;
import org.metasploit.framework.Exploit;
import org.metasploit.framework.OptBase;
import org.metasploit.simple.Debug;

import org.jruby.RubyObject;

import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

/**
 *
 * @author hughneale
 */
public class AutoPwnExample {

    // This is a work in progress.

    public static void main(String args[]) throws Exception {

        Framework mfw = new Metasploit().Simple().Framework();

        mfw.banner(); // Print banner

        
       // mfw.exploits().mod_ranked();
       // mfw.exploits().mod_sorted();
        
       // mfw.exploits().each_module();

        ArrayList<String> exps = new ArrayList();

        Iterator itr = mfw.exploits().getEntrySet().iterator();

        while(itr.hasNext()) {

            Map.Entry obj = (Map.Entry) itr.next();

            String key = (String) obj.getKey();
            Object objd = (Object) obj.getValue();

            // @TODO Fix this.
            if(objd instanceof RubyObject) {
                
            } else {
                System.out.println("No support for: " + objd.getClass().getCanonicalName());
                continue;
            }
            
            Exploit exp = new Exploit(mfw, (RubyObject)objd).New();

           // System.err.println(exp.FullName());
           // System.err.println(exp.ShortName());
            
            Map<String, OptBase> opts = exp.Options();

            if(opts.containsKey("RPORT")) {

                Object ob = Debug.FromObject(opts.get("RPORT").Default());

                if(ob instanceof Long) {

                    // Look for services running SMB/CIFS

                    if((Long) ob == 445) {
                        exps.add(key);
                    }

                }

            }

            exp = null;

        }

        System.out.println("Exploits using port 445.");
        itr = exps.iterator();
        
        while(itr.hasNext()) {
            
            String expf = (String) itr.next();
            System.out.println("=> " + expf);
            
        }
    }
}
