package ro.iacobai.redstoneclock.models;

import java.util.UUID;

public class Id {
    private int ID;
    private String OwnerUUID;
    private String name;

    public Id(int ID, String ownerUUID, String name) {
        this.ID = ID;
        this.OwnerUUID = ownerUUID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOwnerUUID() {
        return OwnerUUID;
    }

    public void setOwnerUUID(String ownerUUID) {
        OwnerUUID = ownerUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
