/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.Ruby;
import org.jruby.RubyNil;
import org.jruby.RubyHash;
import org.jruby.RubyClass;
import org.jruby.RubyString;
import org.jruby.RubyObject;
import org.jruby.RubyException;
import org.jruby.RubyBoolean;
import org.jruby.RubyNoMethodError;

import java.util.Iterator;
import java.util.Map;


/**
 *
 * @author hughneale
 */


public class ModuleManager {

    private final Framework framework;
    private final RubyHash module;

    public ModuleManager(Framework f, String name) {
        Console.out(name + " Module called.");
        this.framework = f;
        this.module = (RubyHash) this.framework.invoke(name);
    }

    public void error(RubyObject oexploit) {
        this.framework.exception((RubyObject) this.framework.invoke(oexploit, "error"));
    }

    public Framework getFramwork() {
        return this.framework;
    }

    public RubyHash list() {
        return module;
    }

    public Iterator get() {
        return module.keySet().iterator();
    }

    public RubyHash getModule() {
        return this.module;
    }

/*
    public Module get_module(String name) {
        Object exploit = module.get(name);
        if(exploit != null) {
            Console.out("Finidng " + name);
            return new Module(framework, exploit);
        } else {
            Console.err("Payload " + name + " does not exsist.");
            return null;
        }
    }
*/
    

     /*
     * Retuns the number of avaliable exploits loaded into the system.
     */
    public int avaliable() {
        if(this.module == null) {
            list();
        }
        return this.module.size();
    }

    @Override
    public String toString() {
        return Debug.HashDump(this.module);
    }

}
