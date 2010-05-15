/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.RubyHash;
import org.jruby.RubyFixnum;
import org.jruby.RubyBoolean;

import org.metasploit.simple.Console;

/**
 *
 * @author hughneale
 */
public class DataStore {

    Framework framework;
    RubyHash datastore;
    
    public DataStore(Framework f) {
        Console.out("DataStore Module called.");
        this.framework = f;
        this.datastore = (RubyHash) framework.invoke("datastore");
    }

    private RubyObject toRuby(Object r) {

        if(r instanceof String) {
            return RubyString.newString(this.framework.ruby(), (String) r);
        } else if(r instanceof Boolean) {
            return RubyBoolean.newBoolean(this.framework.ruby(), (Boolean) r);
        } else if(r instanceof Long) {
            return RubyFixnum.newFixnum(this.framework.ruby(), (Long) r);
        } else if(r instanceof Integer) {
            long num = (Integer)r;
            return RubyFixnum.newFixnum(this.framework.ruby(), num);
        } else {
            return null;
        }

    }

    public void store(Object key, Object value) {

        Console.out("SET " + key + " => " + value);

        this.framework.invoke((RubyObject)this.datastore, "store", toRuby(key), toRuby(value));

    }


}
