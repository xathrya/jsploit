/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.simple;

import org.metasploit.framework.Framework;

import java.io.InputStream;
import java.util.Map;

/**
 *
 * @author hughneale
 */
public class Simple {

    public Object framework;
    public Container container;
    //private String framework_rb = "org/metasploit/ruby/framework.rb";
    //private String framework_dev_rb = "org/metasploit/rubyframework.dev.rb";
    private String framework_min_rb = "org/metasploit/ruby/framework.min.rb";

    public Simple(Container c) throws Exception {

        this.container = c;
        Console.out("MSF Framework starting...");
        
        InputStream is = getClass().getClassLoader().getResourceAsStream(framework_min_rb);
        this.framework = this.container.runScriptlet(is, "framework.rb");

        if(this.framework == null) {
            Console.err("Failed to initalized MSF");
        } else {
            Console.pls("MSF Framework initalized.");
        }

    }

    public void setg(String key, Object value) {
        this.container.put(key, value);
    }

    public Map getg() {
        return this.container.getAttributeMap();
    }

    public Framework Framework() {
        return new Framework(this);
    }

}
