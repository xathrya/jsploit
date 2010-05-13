/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.framework;

import org.metasploit.simple.Console;
import org.metasploit.simple.Container;
import org.metasploit.simple.Simple;

import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyException;

/**
 *
 * @author hughneale
 */
public class Framework {

    private RubyObject framework;
    private Container container;
    private Encoders encoders;
    private Exploits exploits;
    private Payloads payloads;
    private Nops nops;
    private Auxiliary auxiliary;
    private Sessions sessions;
    private Auxmgr logging;
    private Plugins plugins;
    private Events events;
    private Database database;
    private Jobs jobs;

    public Framework(Simple sim) {
        framework = (RubyObject) sim.framework;
        container = sim.container;
        //System.out.println(framework.);
    }

    public Ruby ruby() {
        return this.container.ruby();
    }

    public void exception(RubyObject error) {
        if (error instanceof RubyException) {
            print_stack((RubyException) error);
        }
    }

    private void print_stack(RubyException error) {
        ThreadContext.RubyStackTraceElement[] stack = error.getBacktraceFrames();
        for (int fail = 0; fail < stack.length; fail++) {
            Console.err("@ [" + stack[fail].getLineNumber() + "] " + stack[fail].getFileName());
        }
    }

    public Object invoke(String method) {
        return this.framework.callMethod(method);
    }

    public Object invoke(String method, RubyObject... argv) {
        return this.framework.callMethod(method, argv);
    }

    public Object invoke(RubyObject robj, String method) {
        return robj.callMethod(method);
    }

    public Object invoke(RubyObject robj, String method, RubyObject... argv) {
        return robj.callMethod(method, argv);
    }

    public RubyObject scriptlet(String script) {
        return (RubyObject) this.container.runScriptlet(script);
    }

    public String version() {
        RubyString str = (RubyString) invoke("version");
        return str.asJavaString();
    }

    public String svn_revision() {
        RubyString str = (RubyString) invoke("Revision");
        return str.asJavaString();
    }

    public String inspect() {
        RubyString str = (RubyString) invoke("inspect");
        return str.asJavaString();
    }

    public void banner() {
        Console.out("MSF Version " + version());
        Console.out("Exploits: " + exploits().avaliable() + ", Payloads: " + payloads().avaliable());
    }

    public Object encoders() {
        if (encoders == null) {
            encoders = new Encoders(this);
        }
        return encoders;
    }

    public Exploits exploits() {
        if (exploits == null) {
            exploits = new Exploits(this);
        }
        return exploits;
    }

    public Object nops() {
        if (nops == null) {
            nops = new Nops(this);
        }
        return nops;
    }

    public Payloads payloads() {
        if (payloads == null) {
            payloads = new Payloads(this);
        }
        return payloads;
    }

    public Object auxiliary() {
        if (auxiliary == null) {
            auxiliary = new Auxiliary(this);
        }
        return auxiliary;
    }

    public Sessions sessions() {
        if (sessions == null) {
            sessions = new Sessions(this);
        }
        return sessions;
    }

    public void save_config() {
        Object test = invoke("save_config");
        System.out.println(test.getClass().toString());
    }

    public Auxmgr logging() {
        if (logging == null) {
            logging = new Auxmgr(this);
        }
        return logging;
    }

    public Jobs jobs() {
        if (jobs == null) {
            jobs = new Jobs(this);
        }
        return jobs;
    }

    public Plugins plugins() {
        if (plugins == null) {
            plugins = new Plugins(this);
        }
        return plugins;
    }

    public Events events() {
        if (events == null) {
            events = new Events(this);
        }
        return events;
    }

    public Database database() {
        if (database == null) {
            database = new Database(this);
        }
        return database;
    }

    public Options options() {
        return new Options(this.ruby());
    }
}
