/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.test;

import org.metasploit.Metasploit;

/**
 *
 * @author hughneale
 */
public class SimpleExample {


    public static void main(String args[]) throws Exception {

        // MSF Framework JAVA API
        Metasploit mfs = new Metasploit();

        //mfs.cli(args); // starts the Metasploit CLI
        mfs.console(); // starts the Metasplot Console

        System.exit(0);
    }

}