/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.jruby.RubyArray;
import org.jruby.RubyObject;
import org.jruby.RubyBoolean;

/**
 *
 * @author hughneale
 */
public class Auxiliary extends Module {

    public Auxiliary(Framework fw, RubyObject m) {
        super(fw, m);
    }

    public long check() {
        return (Long)((RubyArray) this.getFramework().invoke(this.self(), "check")).get(0);
    }

    public boolean db() {
        return ((RubyBoolean) this.getFramework().invoke(this.self(), "db")).isTrue();
    }
    
    public Workspace myworkspace() {
        return new Workspace(this.getFramework(), (RubyObject) this.getFramework().invoke(this.self(), "myworkspace"));
    }

}