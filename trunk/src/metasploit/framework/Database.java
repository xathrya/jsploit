/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyArray;
import org.jruby.RubyNil;

import java.util.Iterator;
import java.util.Map;
/**
 *
 * @author hughneale
 */
public class Database {

    private Framework framework;
    RubyArray database;

    public Database(Framework f) {
        Console.out("Database Module called.");
        this.framework = f;
        database = (RubyArray) framework.invoke("plugins");
    }

    @Override
    public String toString() {

        if((Object)database instanceof RubyNil) {
            return "Database empty.";
        }

        Iterator it = database.iterator();

        System.out.println("database Log: " + database.size());

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey().getClass().toString());
            System.out.println(entry.getValue().getClass().toString());

            //value.callMethod("");
           // Map<Class, Map<String, Object>> obj = value.getClassAnnotations();
           // System.out.println(value.getName());

            //System.out.println(key);
            //System.out.println(value.getClass().getName() + " Signatures " + obj.size());

            //Iterator its = obj.entrySet().iterator();

           // while (its.hasNext()) {
//
               // Map.Entry entryd = (Map.Entry) its.next();
           //     Class keyd = (Class) entryd.getKey();
           //     Map<String, Object> valued = (Map) entryd.getValue();

           //     System.out.println("\t" + keyd.getName());

           // }

            //py.callMethod("");

        }
        return null;
    }

}

