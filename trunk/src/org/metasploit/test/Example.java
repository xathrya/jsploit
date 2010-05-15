/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.test;

import org.metasploit.simple.Container;
import org.metasploit.Metasploit;

/**
 *
 * @author hughneale
 */
public class Example {

    public static void main(String args[]) throws Exception {

        /*
         * Sets the direcoty for Metasploit and JRuby within a Container
         *
         * Container cont = new Container();
         * cont.setDirectory("..."); //
         * Metasploit mfs = new Metasploit(cont);
         *
         * Otherwise ...
         *
         */

        // MSF Framework JAVA API
        Metasploit mfs = new Metasploit();

        //mfs.cli(args); // starts the Metasploit CLI
        mfs.console(); // starts the Metasplot Console

        System.exit(0);
    }
}
