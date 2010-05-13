/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyHash;
import org.jruby.RubyNil;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author hughneale
 */
public class Auxmgr extends ModuleManager {

    //private Framework framework;
    //RubyHash exploits;
    //Ruby ruby;

    public Auxmgr(Framework f) {
        super(f, "auxmgr");
    }
    
}
