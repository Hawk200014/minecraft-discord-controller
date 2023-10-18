package de.hawk200014.ServerList;

import java.io.Serializable;

public class ServerModel implements Serializable {

    public int hash = -1;

    public String ip = "";
    public String port = "";

    public ServerModel(String ip, String port){
        this.ip = ip;
        this.port = port;
        hash = ((String) ip + port).hashCode();
    }
}
