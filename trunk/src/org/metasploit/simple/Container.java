/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.simple;

import org.jruby.embed.ScriptingContainer;
import org.jruby.CompatVersion;
import org.jruby.Ruby;
import org.jruby.RubyObject;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 *
 * @author hughneale
 */
public class Container extends ScriptingContainer {

    // Directory of the JRuby Base and Metasploit Framework base.
    private String DIR = "/opt/jsploit/";
    private String MSF_DIR = "msf";
    private String JRUBY_DIR = "jruby";
    private short HEAP_SIZE = 75;
    private char SEP = File.separatorChar;

    // @TODO need to create an output stream object to handle console pipes
    private RubyObject input_stream;
    private RubyObject output_stream;
    
    public Container() {
        Console.pls("JRuby about to start. ");
        this.Reload();
    }

    public void Reload() {
        
        if(!checkheap()) {
            Console.err("Java heap size is too small. Please restart");
            Console.err("with java -Xms80m -Xmx120m -jar metasploit.jar");
        }
        
        this.setCompatVersion(CompatVersion.RUBY1_9);
        this.setHomeDirectory(DIR + JRUBY_DIR + SEP);
        this.setCurrentDirectory(DIR + MSF_DIR + SEP);

        // Adds MSF libaries to JRuby
        List<String> paths = new ArrayList();
        paths.add("./lib");

        this.setLoadPaths(paths);

        System.out.println(this.getSupportedRubyVersion());
        System.out.println("MSF => " + this.getMetasploitDirectory());
        System.out.println("RBY => " + this.getJRubyDirectory());
        //System.out.println();
    }

    private boolean checkheap() {
        long size = ((Runtime.getRuntime().totalMemory()/1024)/1024);
        Console.pls(size + "MB in the JVM Heap");
        if(size > HEAP_SIZE) {
            return true;
        } else {
            return false;
        }
    }

    public Ruby ruby() {
        return Ruby.newInstance(this.getProvider().getRubyInstanceConfig());
    }

    public void setDirectory(String directory) {
        DIR = directory;
    }

    public void setJRubyDirectory(String directory) {
        if (JRUBY_DIR.indexOf(directory) != -1) {
            System.err.println("You are only required to specify the sub direcotry not the full path");
        } else {
            JRUBY_DIR = directory;
        }
        this.setHomeDirectory(JRUBY_DIR + SEP);
    }

    public void setMetasploitDirectory(String directory) {
        if (this.MSF_DIR.indexOf(directory) != -1) {
            System.err.println("You are only required to specify the sub direcotry not the full path");
        } else {
            this.MSF_DIR = directory;
        }
        this.setCurrentDirectory(MSF_DIR + SEP);
    }

    public String getDirectory() {
        return this.DIR;
    }

    public String getJRubyDirectory() {
        return this.getHomeDirectory();
    }

    public String getMetasploitDirectory() {
        return this.getCurrentDirectory();
    }
}
