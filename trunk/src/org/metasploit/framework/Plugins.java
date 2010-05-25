/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyObject;

/**
 *
 * @author hughneale
 */
public class Plugins {

    private Framework framework;
    RubyObject exploits;

    public Plugins(Framework f) {
        Console.out("Plugins Module called.");
        this.framework = f;
        exploits = (RubyObject) framework.invoke("plugins");
    }
    
}
