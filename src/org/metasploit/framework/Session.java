/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.RubyFixnum;
import org.jruby.RubyNil;
import org.jruby.RubyBoolean;
import org.jruby.RubyNumeric;

/**
 *
 * @author hughneale
 */
public class Session {

    Framework framework;
    RubyObject session;

    public Session(Framework f, RubyObject s) {
        this.framework = f;
        this.session = s;
    }

    private String invoke(String name) {
        return ((RubyString) this.framework.invoke(this.session, name)).asJavaString();
    }

    public long Name() {
        RubyFixnum str = (RubyFixnum) this.framework.invoke(this.session, "name");
        return str.getLongValue();
    }

    public void Name(String n) {
        RubyString name = RubyString.newString(this.framework.ruby(), n);
        this.framework.invoke(this.session, "name", name);
    }

    public String Inspect() {
        RubyString str = (RubyString) this.framework.invoke(this.session, "inspect");
        return str.asJavaString();
    }

    public String Description() {
        return invoke("desc");
    }

    public String Type() {
        return invoke("type");
    }

    public void TunnelLocal() {

    }

    public void TunnelPeer() {

    }

    public String Tunnel() {
        RubyString str = (RubyString) this.framework.invoke(this.session, "tunnel_to_s");
        return str.asJavaString();
    }

    public String LogFileName() {
        return invoke("log_file_name");
    }

    public String LogSource() {
        return invoke("log_source");
    }

    // ....
    // @ TODO
    // ....

    public boolean Interactive() {
        return ((RubyBoolean) this.framework.invoke(this.session, "interactive")).isTrue();
    }

    public void Kill() {
        Object od = this.framework.invoke(this.session, "kill");
        System.out.println(od.getClass().getCanonicalName());
    }

    public boolean Dead() {
        return ((RubyBoolean) this.framework.invoke(this.session, "dead")).isTrue();
    }

    public boolean Alive() {
        return ((RubyBoolean) this.framework.invoke(this.session, "alive")).isTrue();
    }

    // Shell
    
    public String RunCommand(String cmd) {
        Console.pls("Executing " + cmd);
        RubyString command = RubyString.newString(this.framework.ruby(), cmd);
        Object ss = this.framework.invoke(this.session, "run_cmd", command);
        if (ss instanceof RubyString) {
            String ex =  ((RubyString) ss).asJavaString();
            Console.shl(ex);
            return ex;
        } else if(ss instanceof RubyNil) {
            return null;
        } else {
            System.out.println("WHAT WE HAVE HERE:: " + ss.getClass().toString());
            return null;
        }
    }

    public String ShellRead(int buf) {
        RubyNumeric sd = RubyNumeric.int2fix(this.framework.ruby(), buf);
        Object ss = this.framework.invoke(this.session, "shell_read", sd);
        if (ss instanceof RubyString) {
            return ((RubyString) ss).asJavaString();
        } else if(ss instanceof RubyNil) {
            return null;
        } else {
            System.out.println("WHAT WE HAVE HERE:: " + ss.getClass().toString());
            return "";
        }
    }
}
