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
public class Payloads extends ModuleManager {

    public Payloads(Framework f) {
        super(f, "payloads");
    }

     public Payload create(String mod) {

        if (!this.getModule().keySet().contains(mod)) {
            Console.err("Payload \"" + mod + "\" is not in the database.");
            return null;
        }

        Console.out("Payload " + mod);

        RubyString payload_name = RubyString.newString(this.getFramework().ruby(), mod);

        return new Payload(this.getFramework(), (RubyObject) this.getFramework().invoke(this.getModule(), "create", payload_name));

    }

}
