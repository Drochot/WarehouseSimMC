package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Diamond extends Block implements Object3D, Updatable {

    private static List<Diamond> diamonds = new ArrayList<>();

    public Diamond(int id, double x, double y, double z) {
        this.Id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.uuid = UUID.randomUUID();
        blocks.add(this);

        /* Er worden standaard 5 diamonds op de planken gezet. */
        if (this.Id < 6) {
            this.Place();
        }
    }

    ArrayList<IntPair> spots = Shelf.getDiamondSpots();

    /*
     * Met deze methode wordt een blok vanaf de plank in de aangegeven cart
     * geplaatst. Ook wordt de counter voor opgeslagen diamond blokken geupdate.
     */

    public void shelfToCart(int cartId) {
        this.shelfPlace = 0;
        this.onShelf = false;
        this.inCart = true;
        this.carrierId = cartId;
        Shelf.decrementDiamond();
    }

    /*
     * Deze methode plaatst een diamond op de plank. Vanuit de Shelf class worden
     * het aantal opgeslagen diamonds en de coordinaten van de plekken op de plank
     * opgehaald, en wordt de diamond zo op de juiste plek gezet.
     */

    public void Place() {
        this.inCart = false;
        int stored = Shelf.getStoredDiamond();
        ArrayList<IntPair> shelfSpots = Shelf.getDiamondSpots();
        this.x = shelfSpots.get(stored).x + 0.5;
        this.z = shelfSpots.get(stored).z + 0.5;
        this.y = 5;
        this.shelfPlace = stored + 1;
        this.onShelf = true;
        this.carrierId = 0;
        Shelf.incrementDiamond();
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
        return Diamond.class.getSimpleName().toLowerCase();
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

    public static List<Diamond> getDiamonds() {
        return diamonds;
    }

    @Override
    public int getCarrierId() {
        return this.carrierId;
    }
}