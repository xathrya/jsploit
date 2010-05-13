/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.simple;

/**
 *
 * @author hughneale
 *
 */
public class Console {

    public static final int PLATFORM = os();
    public static final int WIN = 0;
    public static final int NIX = 1;

    private static final int os() {
        if (System.getProperty("os.name").toLowerCase().indexOf("win") != -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public static final void out(String out) {
        if (PLATFORM == NIX) {
            System.out.println("[*] " + out);
        }
    }

    public static final void err(String out) {
        if (PLATFORM == NIX) {
            System.out.println("\033[31m[-]\033[0m " + out);
        }
    }

    public static final void pls(String out) {
        if (PLATFORM == NIX) {
            System.out.println("\033[34m[+]\033[0m " + out);
        }
    }

    public static final void shl(String out) {
        System.out.println("\033[34m" + out + "\033[0m");
    }
}
