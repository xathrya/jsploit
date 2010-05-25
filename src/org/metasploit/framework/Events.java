/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyObject;
import org.jruby.RubyArray;

/**
 *
 * @author hughneale
 */
public class Events {

    private Framework framework;
    RubyObject events;

    public Events(Framework f) {
        Console.out("Events Module called.");
        this.framework = f;
        events = (RubyObject) framework.invoke("events");
    }

    public void db_event_subscribers() {

       RubyArray db_event_subscribers =  (RubyArray) this.framework.invoke(this.events, "db_event_subscribers");

       System.out.println("db_event_subscribers size " + db_event_subscribers.size());

    }

}
