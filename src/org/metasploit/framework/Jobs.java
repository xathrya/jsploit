/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework;

import org.metasploit.simple.Console;

import org.jruby.RubyObject;
import org.jruby.RubyHash;
import org.jruby.RubyNil;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author hughneale
 */
public class Jobs {

    Framework framework;
    RubyHash jobs;

    public Jobs(Framework f) {
        Console.out("Jobs Module called.");
        this.framework = f;
        jobs = (RubyHash) this.framework.invoke("jobs");
    }

    @Override
    public String toString() {

        if((Object)jobs instanceof RubyNil) {
            return "Jobs empty.";
        }

        Iterator it = jobs.entrySet().iterator();

        System.out.println("database Log: " + jobs.size());

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            
            //System.out.println();

            String id = (String)entry.getKey();
            RubyObject job = (RubyObject)entry.getValue();
            System.out.println(entry.getValue().getClass().toString());

            Job j = new Job(this.framework, job);

            Console.pls("JOB ++ " + id);
            //Console.out("JOB ID " + j.jid());
            Console.out("JOB Name " + j.name());
            //Console.out("JOB Clean Proc " + j.clean_proc());
            //Console.out("JOB Container " + j.container());
            //Console.out("JOB CTX " + j.ctx());
            //Console.out("JOB Thread " + j.job_thread());
            //Console.out("JOB Run Proc " + j.run_proc());
            Console.out("JOB Start Time " + j.start_time().asctime().asJavaString());
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
