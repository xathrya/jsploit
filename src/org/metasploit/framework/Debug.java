/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import java.util.Iterator;
import java.util.Map;

import org.jruby.RubyHash;
import org.jruby.RubyString;
import org.jruby.RubyObject;
import org.jruby.RubyBoolean;
import org.jruby.RubyNil;
import org.jruby.RubyFixnum;

/**
 *
 * @author hughneale
 */
public class Debug {

    public static String fromObject(Object od) {
        if (od instanceof RubyString) {
            return ((RubyString) od).asJavaString();
        } else if (od instanceof RubyBoolean) {
            if (((RubyBoolean) od).isTrue()) {
                return "true";
            } else {
                return "false";
            }
        } else if (od instanceof RubyNil) {
            return "";
        } else if (od instanceof RubyFixnum) {
            return "" + ((RubyFixnum) od).getLongValue();
        } else if (od instanceof RubyHash) {
            return "HASH(" + HashDump((RubyHash) od) + ")";
        } else if (od instanceof RubyObject) {
            return "RubyObject";
        } else {
            return "Unknown " + od.getClass().getCanonicalName();
        }
    }

    public static String HashDump(RubyHash ds) {

        String out = "";
        Iterator it = ds.directEntrySet().iterator();

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();

            out += "KEY: " + fromObject(key) + ", VAL: " + fromObject(value) + "\n";

        }

        return out;

    }

}
