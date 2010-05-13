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
public class Statistics {

    Framework framework;
    RubyObject statistics;

    public Statistics(Framework f) {
        Console.out("Payloads Module called.");
        this.framework = f;
        get();
    }

    private RubyObject get() {
        return (statistics = (RubyObject) this.framework.invoke("statistics"));
    }

    private int invoke(String name) {
        return (Integer) this.framework.invoke(this.statistics, name);
    }

    // Returns the number of auxiliary modules in the framework.
    public int num_auxiliary() {
        return invoke("num_auxiliary");
    }

// Returns the number of encoders in the framework.
    public int num_encoders() {
        return invoke("num_encoders");
    }

// Returns the number of exploits in the framework.
    public int num_exploits() {
        return invoke("num_exploits");
    }

// Returns the number of NOP generators in the framework.
    public int num_nops() {
        return invoke("num_nops");
    }

// Returns the number of singles in the framework.
    public int num_payload_singles() {
        return invoke("num_payload_singles");
    }

// Returns the number of stagers in the framework.
    public int num_payload_stagers() {
        return invoke("num_payload_stagers");
    }

// Returns the number of stages in the framework.
    public int num_payload_stages() {
        return invoke("num_payload_stages");
    }

// Returns the number of payloads in the framework.
    public int num_payloads() {
        return invoke("num_payloads");
    }
}
