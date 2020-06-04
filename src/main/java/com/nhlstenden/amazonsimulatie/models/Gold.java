package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Gold extends Block implements Object3D, Updatable {

    private static List<Gold> golds = new ArrayList<>();

    public Gold(int id, double x, double y, double z) {
        this.Id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.uuid = UUID.randomUUID();
        blocks.add(this);

        /* Er worden standaard 5 golds op de planken gezet. */

        if (this.Id > 20 && this.Id < 26) {
            this.Place();
        }
    }

    ArrayList<IntPair> spots = Shelf.getGoldSpots();

    /*
     * Met deze methode wordt een blok vanaf de plank in de aangegeven cart
     * geplaatst. Ook wordt de counter voor opgeslagen gold blokken geupdate.
     */

    public void shelfToCart(int cartId) {
        this.shelfPlace = 0;
        this.onShelf = false;
        this.inCart = true;
        this.carrierId = cartId;
        Shelf.decrementGold();
    }

    /*
     * Deze methode plaatst een gold op de plank. Vanuit de Shelf class worden het
     * aantal opgeslagen golds en de coordinaten van de plekken op de plank
     * opgehaald, en wordt de gold zo op de juiste plek gezet.
     */

    public void Place() {
        this.inCart = false;
        int stored = Shelf.getStoredGold();
        ArrayList<IntPair> shelfSpots = Shelf.getGoldSpots();
        this.x = shelfSpots.get(stored).x + 0.5;
        this.z = shelfSpots.get(stored).z + 0.5;
        this.y = 5;
        this.shelfPlace = stored + 21;
        this.onShelf = true;
        this.carrierId = 0;
        Shelf.incrementGold();
    }

    public Shelf shelf = new Shelf();

    @Override
    public boolean update() {

        /*
         * als inCart true is wordt de locatie van het blok gelijk gesteld aan die van
         * de cart waaraan het blok gekoppeld is.
         */

        if (this.inCart) {
            for (Robot robot : robots) {
                if (robot.getCartId() == carrierId) {
                    this.x = robot.getX();
                    this.y = robot.getY() + 1.2;
                    this.z = robot.getZ();
                    this.rotationY = robot.getRotationY();
                }
            }

        }
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Gold.class.getSimpleName().toLowerCase();
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

    public boolean getVisible() {
        return this.visible;
    }

    @Override
    public double getRotationZ() {
        return this.rotationZ;
    }

    @Override
    public int getId() {
        return this.Id;
    }

    public static List<Gold> getgolds() {
        return golds;
    }

    @Override
    public int getCarrierId() {
        return carrierId;
    }

}