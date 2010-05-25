/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metasploit.framework;

import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.RubyBoolean;

/**
 *
 * @author hughneale
 */
class OptString extends OptBase {

    public OptString(Framework fw, RubyObject m) {
        super(fw, m);
    }
}

class OptRaw extends OptBase {

    public OptRaw(Framework fw, RubyObject m) {
        super(fw, m);
    }
}

class OptBool extends OptBase {

    public OptBool(Framework fw, RubyObject m) {
        super(fw, m);
    }
}

class OptEnum extends OptBase {

    public OptEnum(Framework fw, RubyObject m) {
        super(fw, m);
    }
}

class OptPort extends OptBase {

    public OptPort(Framework fw, RubyObject m) {
        super(fw, m);
    }
}

class OptAddress extends OptBase {

    public OptAddress(Framework fw, RubyObject m) {
        super(fw, m);
    }
}

class OptAddressRange extends OptBase {

    public OptAddressRange(Framework fw, RubyObject m) {
        super(fw, m);
    }
}

class OptPath extends OptBase {

    public OptPath(Framework fw, RubyObject m) {
        super(fw, m);
    }
}

class OptInt extends OptBase {

    public OptInt(Framework fw, RubyObject m) {
        super(fw, m);
    }
}

public class OptBase {

    private Framework framework;
    private RubyObject opt;

    public OptBase(Framework fw, RubyObject m) {
        this.framework = fw;
        this.opt = m;
    }

    public RubyObject self() {
        return this.opt;
    }

    private Object invoke(String method) {
        return this.framework.invoke(this.opt, method);
    }

    public String Name() {
        return ((RubyString) this.invoke("name")).asJavaString();
    }

    public String Type() {
        return ((RubyString) this.invoke("type")).asJavaString();
    }

    public boolean Advanced() {
        return ((RubyBoolean) this.invoke("advanced")).isTrue();
    }

    public boolean Evasion() {
        return ((RubyBoolean) this.invoke("evasion")).isTrue();
    }

    public boolean Required() {
        return ((RubyBoolean) this.invoke("required")).isTrue();
    }

    public String Description() {
        return ((RubyString) this.invoke("desc")).asJavaString();
    }

    public Object Default() {
        return this.invoke("default");
    }

    public Object Enums() {
        return null;
    }

}
