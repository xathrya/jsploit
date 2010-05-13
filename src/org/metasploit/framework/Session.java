/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.RubyFixnum;
import org.jruby.RubyNumeric;

/**
 *
 * @author hughneale
 */
public class Session {

    Framework framework;
    RubyObject session;

    public Session (Framework f, RubyObject s) {
        this.framework = f;
        this.session = s;
    }

    public long name() {
        RubyFixnum str = (RubyFixnum) this.framework.invoke(this.session, "name");
        return str.getLongValue();
    }

    public String inspect() {
        RubyString str = (RubyString) this.framework.invoke(this.session, "inspect");
        return str.asJavaString();
    }

    public String tunnel_to_s() {
        RubyString str = (RubyString) this.framework.invoke(this.session, "tunnel_to_s");
        return str.asJavaString();
    }

    public void run_cmd(String cmd) {
        RubyString command = RubyString.newString(this.framework.ruby(), cmd);
        Object ss = this.framework.invoke(this.session, "run_cmd", command);
        if(ss instanceof RubyString) {
            Console.shl(((RubyString) ss).asJavaString());
        } else {
        System.out.println("WHAT WE HAVE HERE:: " + ss.getClass().toString());
        }
    }

    public void shell_read(int buf) {
        RubyNumeric sd = RubyNumeric.int2fix(this.framework.ruby(), buf);
        Object ss = this.framework.invoke(this.session, "shell_read", sd);
        if(ss instanceof RubyString) {
            Console.shl("$ " + ((RubyString) ss).asJavaString());
        } else {
            System.out.println("WHAT WE HAVE HERE:: " + ss.getClass().toString());
        }
    }

    public void interact() {

    }

}
