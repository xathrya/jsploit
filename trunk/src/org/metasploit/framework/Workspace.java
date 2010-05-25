/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.RubyArray;

/**
 *
 * @author hughneale
 */
public class Workspace {

    private Framework framework;
    private RubyObject workspace;

    public Workspace(Framework fw, RubyObject w) {

        this.framework = fw;
        this.workspace = w;
        
    }

    public RubyObject self() {
        return this.workspace;
    }

    private String invoke(String method) {
        RubyString name = (RubyString) this.framework.invoke(this.workspace, method);
        return name.asJavaString();
    }

    public String Name() {
        return invoke("name");
    }

    public String Created() {
        return invoke("created");
    }

    public String Updated() {
        return invoke("updated");
    }
    
    public void Services() {

        RubyObject services = (RubyObject) this.framework.invoke(this.workspace, "services");
        //Console.out("SIZE " + services.size());

    }



}
