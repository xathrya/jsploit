/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit;

import org.jruby.Ruby;
import java.io.FileInputStream;

import java.io.File;
import org.metasploit.simple.Container;
import org.metasploit.simple.Simple;
import java.util.Map;

/**
 *
 * @author Hugh Neale
 *
 * @version 1.0
 *
 * License GPL
 *
 */
public class Metasploit {

    //private RubyInstanceConfig cfg;
    private Container msfc;
    //private Map<String, String>globals;

    public Metasploit() {
       msfc = new Container();
    }

    // so we can create multiple instaces to MSF using 1 container
    // isnt threading cool.
    public Metasploit(Container c) {
        msfc = c;
        //globals = new TreeMap();
    }

    public Container getContainer() {
        return msfc;
    }

    public void setg(String key, Object value) {
        this.msfc.put(key, value);
    }

    public Map setg() {
        return this.msfc.getAttributeMap();
    }

    // initalizes MSF console.
    public void console() throws Exception {
        // Using core
        Ruby jrun = msfc.ruby();
        jrun.loadFile("msfconsole", new FileInputStream(this.msfc.getMetasploitDirectory() + "msfconsole"), false);
    }

    // initalizes MSF cli
    public boolean cli(String[] argv) {
        Object receiver = new Object();
        try {
            File f = new File(this.msfc.getMetasploitDirectory() + "msfcli");
            FileInputStream fr = new FileInputStream(f);
            this.msfc.setArgv(argv);
            receiver = this.msfc.runScriptlet(fr, f.getName());
            return true;
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage() + "\n" + receiver);
            return false;
        }
    }

    public boolean automate(File autocfg) {
        if (!autocfg.getName().endsWith(".rc")) {
            System.err.println("Invalid file format please use .rc");
            return false;
        }
        String[] argv = {"-r " + autocfg.toString()};
        return cli(argv);
    }

    public Simple Simple() throws Exception {
        return new Simple(msfc);
    }

}
