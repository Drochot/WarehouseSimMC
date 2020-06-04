package com.nhlstenden.amazonsimulatie.models;

import java.util.*;

/* De DeliveryCart is de "vrachtwagen" die nieuwe blokken naar het magazijn brengt zodat dezen opgeslagen kunnen worden. */

class DeliveryCart implements Robot {

    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private int cartId = 0;

    private static int deliveryBlockType = 1;

    private boolean visible = true;

    private boolean cartFull = false;

    private double step = 0.125;

    ArrayList<IntPair> nodes = new ArrayList<IntPair>();
    CargoPath cargoPath = new CargoPath();

    static String deliveryType = new String();
    static List<Block> blocks = new ArrayList<Block>();

    public DeliveryCart(int id, double x, double y, double z) {
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
        if (cartId == 1) {
            /*
             * Als de nodes list van de cart nodes bevat beweegt de cart naar de volgende
             * node is nde lijst. Wanneer een node bereikt is wordt die verwijderd uit de
             * lijst en gaat de cart verder naar de volgende node tot de lijst leeg is.
             */

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
            
            /*
             * Als er in block in de DeliveryCart zit en de DeliveryCart is niet bij de
             * entrance (waar het block overgegeven moet worden aan de SortingCart) wordt
             * het pad naar de entrance aan de nodes lijst toegevoegd.
             */

            for (Block block : blocks) {
                if (block.getCarrierId() == 1) {
                    if (nodes.isEmpty()) {
                        if (this.x != cargoPath.getEntrance().coords.x && this.z != cargoPath.getEntrance().coords.z) {
                            cargoPath = new CargoPath();
                            nodes = cargoPath.getNodesME();
                        }
                        /*
                         * Wanneer de DeliveryCart de entrance heeft bereikt wacht deze totdat de
                         * SortingCart het signaal geeft dat deze klaar is om een blok te ontvangen
                         */
                        else {
                            if (this.x == cargoPath.getEntrance().coords.x
                                    && this.z == cargoPath.getEntrance().coords.z)
                                if (SortingCart.getSortingCartReady()) {
                                    block.cartToCart(2);
                                }
                        }
                    }

                    this.cartFull = true;
                    break;
                }

            }
            /*
             * Als de nodes list leeg is en de cart heeft geen blok zal deze naar de
             * mijningang rijden. Daar haalt deze een blok op. De soorten blokken die worden
             * opgehaald roteren in de volgorder diamond -> emerald -> gold -> diamond.
             * deliveryType wordt geupdate zodat de OrtingCart weet wat voor soort blok het
             * is en waar deze naar toe moet.
             */
            if (nodes.isEmpty()) {
                if (this.cartFull == false) {
                    if (this.x != cargoPath.getMine().coords.x || this.z != cargoPath.getMine().coords.z) {
                        cargoPath = new CargoPath();
                        nodes = cargoPath.getNodesEM();
                    } else if (this.x == cargoPath.getMine().coords.x && this.z == cargoPath.getMine().coords.z) {

                        for (Block block : blocks) {
                            if (block.mineToCart(1, deliveryBlockType)) {
                                if (deliveryBlockType == 1) {
                                    deliveryType = "diamond";
                                }
                                if (deliveryBlockType == 2) {
                                    deliveryType = "emerald";
                                }
                                if (deliveryBlockType == 3) {
                                    deliveryType = "gold";
                                }
                                break;
                            }
                        }
                        deliveryBlockType++;
                        if (deliveryBlockType == 4) {
                            deliveryBlockType = 1;
                        }

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

    public static String getDeliveryType() {
        return deliveryType;
    }

}
