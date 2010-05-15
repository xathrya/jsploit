/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import java.util.List;
import java.util.Iterator;

import org.metasploit.simple.Console;

import org.jruby.RubyString;
import org.jruby.RubyObject;
import org.jruby.RubyHash;
import org.jruby.RubyBoolean;
import org.jruby.RubyArray;
import org.jruby.RubyNoMethodError;
import org.jruby.RubyException;
import org.jruby.RubyNil;

/**
 *
 * @author hughneale
 */
public class Auxiliaries extends ModuleManager {

    public Auxiliaries(Framework f) {
        super(f, "auxiliary");
    }

    public Auxiliary create(String mod) {

        if (!this.getModule().keySet().contains(mod)) {
            Console.err("Auxiliary \"" + mod + "\" is not in the database.");
            return null;
        }

        Console.out("Auxiliary " + mod);

        RubyString auxiliary_name = RubyString.newString(this.getFramework().ruby(), mod);

        return new Auxiliary(this.getFramework(), (RubyObject) this.getFramework().invoke(this.getModule(), "create", auxiliary_name));

        //return new ;

    }

    public void run(Auxiliary aux) {

        // Check database support

        if(!aux.db()) {
            Console.err("Database has not been configured. Results will not be logged.");
        }

        // Run auxiliary

        this.getFramework().invoke(aux.self(), "run");

        
    }

}
