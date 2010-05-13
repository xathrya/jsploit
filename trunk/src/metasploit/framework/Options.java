/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.framework;

import java.util.Map;
import java.util.HashMap;

import org.jruby.RubyString;
import org.jruby.RubyBoolean;
import org.jruby.RubyInteger;
import org.jruby.Ruby;

/**
 *
 * @author hughneale
 */

public class Options {

    private Map<Object, Object> options;
    private Ruby ruby;


    public Options(Ruby rby) {
        ruby = rby;
        options = new HashMap();
    }

    private Object cast(Object obj) {
        if (obj instanceof String) {
            return (Object) RubyString.newString(ruby, (String)obj);
        } else if (obj instanceof Boolean) {
           return (Object) RubyBoolean.newBoolean(ruby, (Boolean)obj);
        //} else if (obj instanceof Long) { // No clue.
        //    return null;
       // } else if (obj instanceof Integer) { // Integer cast already?  - check me!
       //     return (Object) RubyInteger.newNumeric(ruby);
        } else {
            return obj;
        }
    }

    public void add(Object key, Object value) {
        this.options.put(cast(key), cast(value));
    }

    public Map get() {
        return this.options;
    }

    @Override
    public String toString() {
        return "RARW";
    }
}
