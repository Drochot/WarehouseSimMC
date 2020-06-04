package com.nhlstenden.amazonsimulatie.models;

import java.util.*;

/* De SortingCart plaatst aangeleverde blokken op de juiste plank in het magazijn. */

class SortingCart extends MagazineCart {

    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private NodeWeighted destination = null;

    private NodeWeighted start = null;

    private int cartId = 0;

    static int deliveryBlockType = 1;
    static int shippingBlockType = 2;

    private boolean visible = true;

    private boolean cartFull = false;

    private double step = 0.125;

    private static boolean sortingCartReady = false;

    private static String deliveryType = new String();


    ArrayList<IntPair> nodes = new ArrayList<IntPair>();
    static String lastDeliveryType = new String();
    ArrayList<IntPair> newPath = new ArrayList<IntPair>();
    static List<Block> blocks = new ArrayList<Block>();

    public SortingCart(int id, double x, double y, double z) {
        this.cartId = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.uuid = UUID.randomUUID();

        robots.add(this);
    }

    @Override
    public boolean update() {

        blocks = Block.getBlocks();
        cartFull = false;
        goCart();

        return true;
    }

    @Override
    public void goCart() {
        if (cartId == 2) {

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
            /* Als de cart een blok heeft en geen nodes in de lisjt heeft wordt het pad bepaald naar de juiste shelf om het blok op te slaan.
            Dit gebeurt aan de hand van deliveryType, wat wordt doorgegeven door de DeliveryCart.  */
            for (Block block : blocks) {
                if (block.getCarrierId() == 2) {
                    if (nodes.isEmpty()) {
                        sortingCartReady = false;
                        magazinePath = new MagazinePath();
                        deliveryType = DeliveryCart.getDeliveryType();
                        lastDeliveryType = deliveryType;
                        destination = getShelfNode(deliveryType, false);
                        nodes = magazinePath.graphWeighted.DijkstraShortestPath(magazinePath.x, destination);
                    }
                    /* Als de shelf bereikt is wordt het blok op de plank gezet. */

                    else if (!nodes.isEmpty()) {
                        if (nodes.get(nodes.size() - 1).x == x && nodes.get(nodes.size() - 1).z == z) {
                            block.Place();
                        }
                    }
                    this.cartFull = true;
                    break;
                }
            }
            /* Wanneer de cart weer leeg is wordt de weg vanaf de shelf terug naar de ingang bepaald. Wanneer de cart aan komt geeft
            deze het signaal dat een blok in ontvangst genomen kan worden. */

            if (nodes.isEmpty()) {
                if (this.cartFull == false) {
                    if (this.x != magazinePath.x.coords.x || this.z != magazinePath.x.coords.z) {
                        magazinePath = new MagazinePath();
                        newPath.clear();
                        start = getShelfNode(lastDeliveryType, true);
                        newPath = magazinePath.graphWeighted.DijkstraShortestPath(start, magazinePath.x);
                        nodes.addAll(newPath);
                    } else if (this.x == magazinePath.x.coords.x && this.z == magazinePath.x.coords.z) {
                        sortingCartReady = true;
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

    public static boolean getSortingCartReady() {
        return sortingCartReady;
    }

    public List<Robot> getRobots() {
        return robots;
    }

}
