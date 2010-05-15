/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.metasploit.framework.types;

import java.util.Date;

/**
 *
 * @author hughneale
 */
public class Service {

    private Host host_id;
    private Date created_at;
    private long port;
    private String proto;
    private String state;
    private String name;
    private Date updated_at;
    private String info;

    public Service() {

    }

    public void setHost(Host h) {
        this.host_id = h;
    }

    public void setCreated(Date c) {
        this.created_at = c;
    }

    public void setPort(long p) {
        this.port = p;
    }

    public void setProto(String p) {
        this.proto = p;
    }

    public void setState(String s) {
        this.state = s;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setUpdated(Date u) {
        this.updated_at = u;
    }

    public void setInfo(String i) {
        this.info = i;
    }

    public Host getHost() {
        return this.host_id;
    }

    public Date getCreated() {
        return this.created_at;
    }

    public long getPort() {
        return this.port;
    }

    public String getProto() {
        return this.proto;
    }

    public String getState() {
        return this.state;
    }

    public String getName() {
        return this.name;
    }

    public Date getUpdated() {
        return this.updated_at;
    }

    public String getInfo() {
        return this.info;
    }

}
