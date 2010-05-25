/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework.types;

import org.metasploit.framework.Framework;

import org.jruby.RubyObject;
import org.jruby.RubyString;

/**
 *
 * @author hughneale
 */
public class Reference {

    private Framework framework;
    private RubyObject module;

    public Reference(Framework fw, RubyObject m) {
        this.framework = fw;
        this.module = m;
    }

    private String invoke(String name) {
        return ((RubyString) this.framework.invoke(this.module, name)).asJavaString();
    }

    public String getCTXid() {
        return invoke("ctx_id");
    }

    public String getCTXval() {
        return invoke("ctx_val");
    }

    public String getURL() {
        return invoke("to_s");
    }

}
