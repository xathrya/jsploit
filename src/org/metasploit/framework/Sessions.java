/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyHash;
import org.jruby.RubyFixnum;
import org.jruby.RubyObject;

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
        sessions = (RubyHash) this.framework.invoke("sessions");
    }

    public RubyHash list() {
        return this.sessions;
    }

    /*
     * Retuns the number of avaliable sessions loaded into the system.
     */
    public int avaliable() {
        return sessions.size();
    }

    @Override
    public String toString() {

        Iterator it = sessions.directKeySet().iterator();

        while (it.hasNext()) {

            RubyFixnum s = (RubyFixnum) it.next();

            // System.out.println(s.toString());

            //py.callMethod("");

        }

        return "";
    }

    public Session interact(long id) {

        RubyObject obj = (RubyObject) sessions.get(id);

        if (obj != null) {

            Session sesh = new Session(this.framework, obj);

            if (sesh != null) {
                return sesh;
            } else {
                Console.err("Session closed.");
            }

        } else {
            Console.err("Session does not exsist.");
        }

        return null;
    }
}
