/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.framework;

import org.metasploit.simple.Debug;
import org.metasploit.simple.Console;

import org.jruby.RubyHash;
import org.jruby.RubyFixnum;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.RubyBoolean;
import org.jruby.RubyArray;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author hughneale
 */
public class ModuleManager {

    private final Framework framework;
    private final RubyHash modules;

    public ModuleManager(Framework f, String name) {
        Console.out(name + " Module called.");
        this.framework = f;
        this.modules = (RubyHash) this.framework.invoke(name);
    }

    public RubyHash self() {
        return this.modules;
    }

    public void error(RubyObject oexploit) {
        this.framework.exception((RubyObject) this.framework.invoke(oexploit, "error"));
    }

    public Framework getFramework() {
        return this.framework;
    }

    public RubyHash list() {
        return this.modules;
    }

    public Set getKeySet() {
        return this.modules.keySet();
    }

    public Set getEntrySet() {
        return this.modules.entrySet();
    }

    /*
     * Retuns the number of avaliable exploits loaded into the system.
     */
    public int avaliable() {
        return this.modules.size();
    }

    // @TODO

    public void each_module() {

        //RubyString payload_name = RubyString.newString(this.getFramework().ruby(), "opts");

        //  RubyArray block = RubyArray.newArray(this.getFramework().ruby());
      //  RubyHash send = RubyHash.newHash(this.getFramework().ruby());

       // send.put("Arch", "Windows");

        RubyFixnum send = RubyFixnum.newFixnum(this.getFramework().ruby(), 1);

        Object ob = this.framework.invoke(this.self(), "each_module", this.self());

        System.out.println(ob.getClass().getCanonicalName());
        //  System.out.println(block.size());

    }

    public void force_load_set() {

        RubyArray ob = (RubyArray) this.framework.invoke(this.self(), "force_load_set");

        System.out.println(ob.size());

        Iterator it = ob.iterator();

        while(it.hasNext()) {

            RubyArray kbk = (RubyArray) it.next();

            Iterator itd = kbk.iterator();

            while(itd.hasNext()) {

                Object od = itd.next();

                if(od instanceof String ) {
                    
                    System.out.println((String)od);

                } else if(od instanceof RubyObject) {

                    Exploit ex = new Exploit(this.framework, (RubyObject)od);

                    ex.datastore();

                    //System.out.println("=> " + );

                }

            }

        }

    }

    public boolean valid(String mod) {
        RubyString module = RubyString.newString(this.getFramework().ruby(), mod);
        return ((RubyBoolean) this.framework.invoke(this.self(), "valid", module)).isTrue();
    }

    public void mod_ranked() {
        Object od = this.framework.invoke(this.self(), "mod_ranked");
        System.out.println(od.getClass().getCanonicalName());
    }

    public void mod_sorted() {
        Object od = this.framework.invoke(this.self(), "mod_sorted");
        System.out.println(od.getClass().getCanonicalName());
    }

    @Override
    public String toString() {
        return Debug.HashDump(this.modules);
    }
}
