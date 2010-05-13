/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.RubyFixnum;
import org.jruby.RubyProc;
import org.jruby.RubyHash;
import org.jruby.RubyArray;
import org.jruby.RubyThread;
import org.jruby.RubyTime;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author hughneale
 */

public class Job {

    private Framework framework;
    private RubyObject job;

    /*
     *	attr_writer   :name #:nodoc:
	attr_writer   :jid #:nodoc:
	attr_accessor :job_thread #:nodoc:
	attr_accessor :container #:nodoc:
	attr_accessor :run_proc #:nodoc:
	attr_accessor :clean_proc #:nodoc:
	attr_accessor :ctx #:nodoc:
	attr_writer   :start_time #:nodoc:*/

    public Job(Framework f, RubyObject j) {
        this.framework = f;
        this.job = j;
    }

    public RubyFixnum jid() {
        RubyFixnum str = (RubyFixnum) this.framework.invoke(this.job, "jid");
        return str;
    }

    public String name() {
        RubyString str = (RubyString) this.framework.invoke(this.job, "name");
        return str.asJavaString();
    }

    public RubyThread job_thread() {
        RubyThread str = (RubyThread) this.framework.invoke(this.job, "job_thread");
        return str;
    }

    public RubyHash container() {
        RubyHash str = (RubyHash) this.framework.invoke(this.job, "container");
        return str;
    }

    public RubyProc run_proc() {
        RubyProc str = (RubyProc) this.framework.invoke(this.job, "run_proc");
        return str;
    }

    public RubyProc clean_proc() {
        RubyProc str = (RubyProc) this.framework.invoke(this.job, "clean_proc");
        return str;
    }

    public RubyArray ctx() {
        RubyArray str = (RubyArray) this.framework.invoke(this.job, "ctx");
        return str;
    }

    public RubyTime start_time() {
        RubyTime str = (RubyTime) this.framework.invoke(this.job, "start_time");
        return str;
    }

}
