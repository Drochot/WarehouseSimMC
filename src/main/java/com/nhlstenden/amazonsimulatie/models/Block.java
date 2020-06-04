package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* Hier worden universele eigenschappen en functies van de blokken aangemaakt. */

public abstract class Block {
    double x = 0;
    double y = 0;
    double z = 0;

    UUID uuid;

    double rotationX = 0;
    double rotationY = 0;
    double rotationZ = 0;

    boolean visible = true;

    public static List<Block> blocks = new ArrayList<>();
    public int Id = 0;
    public int shelfPlace = 0;
    boolean onShelf = false;
    boolean inCart = false;
    int carrierId = 0;

    List<Robot> robots = Robot.robots;

    public abstract void shelfToCart(int cartId);

    public abstract void Place();

    public abstract int getCarrierId();

    /*
     * Met deze methode wordt het juiste type blok uit de mijn gehaald en in een
     * cart geplaatst en worden de bijbehorende waardes aangepast.
     */

    public boolean mineToCart(int cartId, int type) {
        if (type == 1) {
            if (this.Id < 11 && this.onShelf == false && this.inCart == false) {
                this.inCart = true;
                this.carrierId = cartId;
                DeliveryCart.deliveryType = "diamond";
                return true;
            }
        } else if (type == 2) {
            if (this.Id < 21 && this.Id > 10 && this.onShelf == false && this.inCart == false) {
                this.inCart = true;
                this.carrierId = cartId;
                DeliveryCart.deliveryType = "emerald";
                return true;

            }
        } else if (type == 3) {
            if (this.Id > 20 && this.onShelf == false && this.inCart == false) {
                this.inCart = true;
                this.carrierId = cartId;
                DeliveryCart.deliveryType = "gold";
                return true;

            }
        }
        return false;
    }

    /*
     * Deze methode verwijdert een blok uit een cart en plaatst deze terug in de
     * mijn.
     */

    public void cartToMine() {
        this.inCart = false;
        this.carrierId = 0;
        this.x = 8.5;
        this.y = 3;
        this.z = 11.5;
    }

    public void cartToCart(int cartId) {
        this.inCart = true;
        this.carrierId = cartId;
    }

    public static List<Block> getBlocks() {
        return blocks;
    }

    public int getId() {
        return Id;
    }
}