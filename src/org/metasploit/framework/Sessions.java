/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyHash;
import org.jruby.RubyFixnum;

import java.util.Iterator;

/**
 *
 * @author hughneale
 */
public class Sessions {

    Framework framework;
    RubyHash sessions;

    public Sessions(Framework f) {
        Console.out("Sessions Module called.");
        this.framework = f;
    }

    public RubyHash list() {
        return (sessions = (RubyHash) this.framework.invoke("sessions"));
    }

     /*
     * Retuns the number of avaliable exploits loaded into the system.
     */
    public int avaliable() {
        if(sessions == null) {
            list();
        }
        return sessions.size();
    }


    @Override
    public String toString() {
        if(sessions == null) {
            list();
        }

        Iterator it = sessions.directKeySet().iterator();

        while(it.hasNext()) {

            RubyFixnum s = (RubyFixnum) it.next();

           // System.out.println(s.toString());

            //py.callMethod("");

        }

        return "";
    }

    public void interact(int id) {

    }

}
