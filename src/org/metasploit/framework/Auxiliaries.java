/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyString;
import org.jruby.RubyObject;

/**
 *
 * @author hughneale
 */
public class Auxiliaries extends ModuleManager {

    public Auxiliaries(Framework f) {
        super(f, "auxiliary");
    }

    public Auxiliary create(String mod) {

        if (!this.self().keySet().contains(mod)) {
            Console.err("Auxiliary \"" + mod + "\" is not in the database.");
            return null;
        }

        Console.out("Auxiliary " + mod);

        RubyString auxiliary_name = RubyString.newString(this.getFramework().ruby(), mod);

        return new Auxiliary(this.getFramework(), (RubyObject) this.getFramework().invoke(this.self(), "create", auxiliary_name));

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
