package com.nhlstenden.amazonsimulatie.models;

import java.util.*;

/* De ShippingCart is de "vrachtwagen" die de blokken het magazijn uit vervoert. */

class ShippingCart implements Robot{

    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private int cartId = 0;

    static int deliveryBlockType = 1;
    static int shippingBlockType = 2;

    private boolean visible = true;

    private boolean cartFull = false;

    private double step = 0.125;

    private static boolean shippingCartReady = true;

    ArrayList<IntPair> nodes = new ArrayList<IntPair>();
    ShippingPath shippingPath = new ShippingPath();
    static List<Block> blocks = new ArrayList<Block>();



    public ShippingCart(int id, double x, double y, double z) {
        this.cartId = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.uuid = UUID.randomUUID();

        robots.add(this);
    }

    @Override
    public boolean update(){

        blocks = Block.getBlocks();
        cartFull = false;
        goCart();


        return true;
    }

    @Override
    public void goCart() {
        if (cartId == 3) {

            /*  Als de nodes list van de cart nodes bevat beweegt de cart naar de volgende node is nde lijst.
            Wanneer een node bereikt is wordt die verwijderd uit de lijst en gaat de cart verder naar de volgende node tot de lijst leeg is.  */

            if (!nodes.isEmpty()) {
                if (x < nodes.get(0).getX()) {
                    this.x += step;
                    this.rotationY = 0;
                } else if (x > nodes.get(0).getX()) {
                    this.x -= step;
                    this.rotationY = 0;
                } else if (z < nodes.get(0).getZ()) {
                    this.z += step;
                    this.rotationY = Math.PI / 2;
                } else if (z > nodes.get(0).getZ()) {
                    this.z -= step;
                    this.rotationY = Math.PI / 2;
                } else {
                    this.x += 0;
                    this.z += 0;

                    nodes.remove(0);
                }

            }

            /* Wanneer de ShippingCart een blok heeft bepaalt deze het pad naar het eindpunt buiten het magazijn. Eenmaal daar aangekomen
            wordt het blok "weggestuurd" (maar eigenlijk gaat het gewoon weer de mijn in). */
            for (Block block : blocks) {
                if (block.getCarrierId() == 3) {
                    if (nodes.isEmpty()) {
                        shippingPath = new ShippingPath();
                        nodes = shippingPath.getNodesED();
                        shippingCartReady = false;
                    } else {
                        if (nodes.get(nodes.size() - 1).x == x && nodes.get(nodes.size() - 1).z == z) {
                            block.cartToMine();
                        }

                    }

                    this.cartFull = true;
                    break;
                }

            }
            /* Zodra de ShippingCart geen blok meer heeft rijdt deze terug naar de startpositie en geeft, eenmaal aangekomen, het signaal dat hij klaar
            staat om het volgende blok van de FetchingCart in ontvangst te nemen.*/

            if (nodes.isEmpty()) {
                if (this.cartFull == false) {
                    if (this.x != shippingPath.getEntrance().coords.x || this.z != shippingPath.getEntrance().coords.z) {
                        shippingPath = new ShippingPath();
                        nodes = shippingPath.getNodesDE();
                    } else {
                        shippingCartReady = true;
                    }
                }
                
            }
        }
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

@Override
    public String getType() {
        /*
         * Dit onderdeel wordt gebruikt om het type van dit object als stringwaarde
         * terug te kunnen geven. Het moet een stringwaarde zijn omdat deze informatie
         * nodig is op de client, en die verstuurd moet kunnen worden naar de browser.
         * In de javascript code wordt dit dan weer verder afgehandeld.
         */
        return Robot.class.getSimpleName().toLowerCase();
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

    public int getCartId() {
        return this.cartId;
    }

    public boolean getVisible() {
        return this.visible;
    }


    public List<Robot> getRobots() {
        return robots;
    }

    public static boolean getShippingCartReady(){
        return shippingCartReady;
    }

}

