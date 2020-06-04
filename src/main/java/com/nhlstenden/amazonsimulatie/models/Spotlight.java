package com.nhlstenden.amazonsimulatie.models;

import java.util.UUID;

class Spotlight implements Object3D, Updatable  {

    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private double intensity = 0;

    private boolean visible = true;

    private int spotlightId = 0;
    
    public Spotlight(int id, double x, double y, double z){
        this.spotlightId = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.uuid = UUID.randomUUID();

    }

    @Override
    public boolean update() {
        
        return false;
    }


    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {        
        return Spotlight.class.getSimpleName().toLowerCase();
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public double getRotationX() {
        return this.rotationX;
    }

    @Override
    public double getRotationY() {
        return this.rotationY;
    }

    @Override
    public double getRotationZ() {
        return this.rotationZ;
    }


    public double getIntensity(){
        return this.intensity;
    }

     public int getSpotlightId(){
         return this.spotlightId;
     }

     public boolean getVisible() {
        return this.visible;
    }

}