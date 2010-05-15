/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyHash;
import org.jruby.RubyObject;

import java.util.Iterator;
import java.util.Set;


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

    public Framework getFramework() {
        return this.framework;
    }

    public RubyHash list() {
        return this.module;
    }

    public Iterator get() {
        return this.module.keySet().iterator();
    }

    public Set getKeys() {
        return this.module.keySet();
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
