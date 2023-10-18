package de.hawk200014.ServerList;

import de.hawk200014.ErrorHandler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerList {

    private ArrayList<ServerModel> serverList = new ArrayList<>();
    private String filepath;

    public ServerList(String filepath){
        this.filepath = filepath;
        load();
    }

    public void save(){
        try {
            FileOutputStream fos = new FileOutputStream(this.filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(serverList);
            oos.close();
        }
        catch (Exception e){
            ErrorHandler.logError("Error while saving the server list", e);
        }
    }

    public void load(){
        try {
            FileInputStream fis = new FileInputStream("t.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.serverList = (ArrayList<ServerModel>) ois.readObject();
            ois.close();
        }
        catch (Exception e){
            ErrorHandler.logError("Error while saving the server list", e);
        }
    }

    public boolean containsServer(int hash){
        for (ServerModel serverModel : serverList) {
            if(serverModel.hash == hash){
                return true;
            }
        }
        return false;
    }

    public void addServer(ServerModel model){
        if(containsServer(model.hash)) return;
        serverList.add(model);
    }
}
