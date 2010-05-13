/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.framework;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.RubyHash;
import org.jruby.RubyNil;
import org.jruby.RubyBoolean;
import org.jruby.RubyFixnum;

/**
 *
 * @author hughneale
 */
public class Module<E> {

    private Framework framework;
    private RubyObject module;
    private Map<String, OptBase> options;

    public Module(Framework fw, RubyObject m) {
        this.framework = fw;
        this.module = m;
        this.options = new HashMap();
    }

    public RubyObject self() {
        return this.module;
    }

    private String invoke(String method) {
        RubyString name = (RubyString) this.framework.invoke(this.module, method);
        return name.asJavaString();
    }

    public String FullName() {
        return this.invoke("fullname");
    }

    public String RefName() {
        return this.invoke("refname");
    }

    public Object Rank() {
        return this.invoke("rank");
    }

    public String RankToS() {
        return this.invoke("rank_to_s");
    }

    public String RankToH() {
        return this.invoke("rank_to_h");
    }

    public String ShortName() {
        return this.invoke("shortname");
    }

    public Object OrigClass() {
        return this.invoke("orig_cls");
    }

    // public File FilePath() {
    //     return this.invoke("file_path");
    // }
    public String Name() {
        return this.invoke("name");
    }

    public String Description() {
        return this.invoke("description");
    }

    public String Alias() {
        return this.invoke("alias");
    }

    public String Version() {
        return this.invoke("version");
    }

    public String Compat() {
        RubyHash ds = (RubyHash) this.framework.invoke(this.module, "compat");
        return Debug.HashDump(ds);
    }

    // public Target TargetHose() {
    //     return this.invoke("target_host");
    // }
    public Object WorkSpace() {
        return this.invoke("workspace");
    }

    public Object Owner() {
        return this.invoke("owner");
    }

    //  public boolean Compatible(Module m) {
    //      return this.invoke("compatible");
    // }
    public String Type() {
        return this.invoke("type");
    }

    public String AuthorToS() {
        return this.invoke("author_to_s");
    }

    // public List EachAuthor() {
    //     return this.invoke("fullname");
    //  }
    public String Arch() {
        return this.invoke("arch_to_s");
    }

    public Map<String, OptBase> Options() {

        RubyHash ds = (RubyHash) this.framework.invoke(this.module, "options");
        Iterator it = ds.directEntrySet().iterator();

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            String key = ((RubyString) entry.getKey()).asJavaString();
            Object value = entry.getValue();
            
            if(value instanceof RubyObject) {
                
                OptBase op = new OptBase(this.framework, (RubyObject)value);
                this.options.put(key, op);

                //System.out.println("KEY: " + key + ", VAL: " + fromObject(value));
                
            } else {
                // @TODO
            }

        }
        return this.options;
    }

    public Map<String, OptBase> AdvancedOptions() {
        return this.options;
    }

    public Map<String, OptBase> DefaultOptions() {
        return this.options;
    }

    public String Share_DataStore() {
        Object ds = this.framework.invoke(this.module, "share_datastore");
        System.out.println(ds.getClass().getCanonicalName());
        return "";
    }

    public String Validate() {
        // Throws an exception?
        Object ds = this.framework.invoke(this.module, "validate");
        System.out.println(ds.getClass().getCanonicalName());
        return "";
    }

    public Framework getFramework() {
        return this.framework;
    }

    public String fromObject(Object od) {
        if (od instanceof RubyString) {
            return ((RubyString) od).asJavaString();
        } else if (od instanceof RubyBoolean) {
            if (((RubyBoolean) od).isTrue()) {
                return "true";
            } else {
                return "false";
            }
        } else if (od instanceof RubyNil) {
            return "null";
        } else if (od instanceof RubyFixnum) {
            return "" + ((RubyFixnum) od).getLongValue();
        } else if (od instanceof RubyHash) {
            return "HASH(" + HashDump((RubyHash) od) + ")";
        } else if (od instanceof RubyObject) {
            Object ff = this.framework.invoke((RubyObject) od, "name");
            System.out.println(((RubyString) ff).asJavaString());

            Object ds = this.framework.invoke((RubyObject) od, "type");
            System.out.println("\tTYP: " + ((RubyString) ds).asJavaString());

            Object adv = this.framework.invoke((RubyObject) od, "advanced");
            System.out.println("\tADV: " + ((RubyBoolean) adv).isTrue());

            Object req = this.framework.invoke((RubyObject) od, "required");
            System.out.println("\tREQ: " + ((RubyBoolean) req).isTrue());

            Object reqdf = this.framework.invoke((RubyObject) od, "desc");
            System.out.println("\tDEC: " + fromObject(reqdf) + "  (" + reqdf.getClass().getCanonicalName() + ")");

            Object reqd = this.framework.invoke((RubyObject) od, "default");
            System.out.println("\tDEF: " + fromObject(reqd) + "  (" + reqd.getClass().getCanonicalName() + ")");


            return "RubyObject";
        } else {
            return "Unknown " + od.getClass().getCanonicalName();
        }
    }

    public String HashDump(RubyHash ds) {

        String out = "";
        Iterator it = ds.directEntrySet().iterator();

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();

            out += "KEY: " + fromObject(key) + ", VAL: " + fromObject(value) + "\n";

        }

        return out;

    }
}
