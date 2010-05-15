/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyObject;
import org.jruby.RubyArray;
import org.jruby.RubyBoolean;
import org.jruby.RubyHash;
import org.jruby.RubyFixnum;
import org.jruby.RubyString;
import org.jruby.RubyNil;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author hughneale
 */
public class DBManager {

    private Framework framework;
    RubyObject database;

    public DBManager(Framework f) {
        Console.out("Database Module called.");
        this.framework = f;
        database = (RubyObject) framework.invoke("db");
    }

    /*
     *
     * Please ensure you have installed the following JRUBY gems!
     *
     * sudo jruby -S gem install jdbc-sqlite3
     * sudo jruby -S gem install activerecord-jdbcsqlite3-adapter
     *
     */
    public boolean connect() {

        /*
         * MySQL
         *
         * :adapter  => "mysql",
         * :host     => "localhost",
         * :username => "myuser",
         * :password => "mypass",
         * :database => "somedatabase"
         *
         * SQLite3
         *
         * :adapter => "sqlite3",
         * :database  => "path/to/dbfile"
         *
         */

        RubyHash send = RubyHash.newHash(this.framework.ruby());

        //send.put(:adapter, "mysql");
        //send.put(:host, "127.0.0.1");
        //send.put(:username, "hugh");
        //send.put(:password, "password");
        //send.put(:database, "msf");

        //send.put(:adapter, "jdbcsqlite3"); <- for JRuby
        //send.put(:database, "msf.db");

        Object connect = this.framework.invoke(this.database, "connect", send);

        if (connect instanceof RubyBoolean) {
            return ((RubyBoolean) connect).isTrue();
        } else {
            System.out.println(connect.getClass().getCanonicalName());
            return false;
        }

    }

    public void disconnect() {

        Object disconnect = this.framework.invoke(this.database, "disconnect");
        System.out.println("disconnect " + disconnect.getClass().getCanonicalName());

    }

    public void check() {

        Object work = this.framework.invoke(this.database, "check");
        System.out.println("CHECK " + work.getClass().getCanonicalName());

    }

    public void services() {

        RubyArray services = (RubyArray) this.framework.invoke(this.database, "services");

        System.out.println("Found " + services.size() + " services.");

        Iterator it = services.iterator();

        while (it.hasNext()) {

            RubyObject od = (RubyObject) it.next();

            Object rname = this.framework.invoke(od, "name");

            String name = "";

            if (rname instanceof RubyNil) {
                name = "Unknown";
            } else {
                System.out.println(rname.getClass().getCanonicalName());
            }

            RubyFixnum port = (RubyFixnum) this.framework.invoke(od, "port");
            String state = ((RubyString) this.framework.invoke(od, "state")).asJavaString();
            String proto = ((RubyString) this.framework.invoke(od, "proto")).asJavaString();

            Console.out("port " + port + " (" + proto + ") " + state + " - " + name);

            // ss =  this.framework.invoke(od, "addresses");

            //System.out.println(ss.getClass().getCanonicalName());

        }


    }
}

