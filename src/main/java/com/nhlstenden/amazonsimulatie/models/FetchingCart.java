package com.nhlstenden.amazonsimulatie.models;

import java.util.*;

/* De FetchingCart haalt blokken uit de planken zodat zij weggebracht kunnen worden door de ShippingCart. */

class FetchingCart extends MagazineCart {

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

    static String shippingType = new String();

    ArrayList<IntPair> nodes = new ArrayList<IntPair>();
    ArrayList<IntPair> newPath = new ArrayList<IntPair>();
    static List<Block> blocks = new ArrayList<Block>();

    public FetchingCart(int id, double x, double y, double z) {
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
        if (cartId == 4) {

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
            /* Als de FetchingCart een blok heeft en bij de ingang staat wacht de FetchingCart totdat de ShippingCart klaar is om het blok aan te nemen.
            Wanneer de ShippingCart ready is wordt het blok over gegeven. */
            for (Block block : blocks) {
                if (block.getCarrierId() == 4) {
                    if (nodes.isEmpty()) {
                        if (ShippingCart.getShippingCartReady()) {
                            block.cartToCart(3);
                            break;
                        }
                    }
                    this.cartFull = true;
                    break;
                }
            }

            /* Als de FetchingCart geen nodes heeft en op zijn startpositie staat wordt het pad bepaald naar de shelf waar het volgende blok
            kan worden opgehaald. De blokken worden opgehaald in de volgorde emerald -> gold -> diamond -> emerald. */
            if (this.cartFull == false) {
                if (nodes.isEmpty()) {
                    if (this.x == magazinePath.w.coords.x && this.z == magazinePath.w.coords.z) {
                        magazinePath = new MagazinePath();
                        if (shippingBlockType == 1) {
                            if (Shelf.getStoredDiamond() > 0) {
                                shippingType = "diamond";
                                destination = getShelfNode(shippingType, true);
                                nodes = magazinePath.graphWeighted.DijkstraShortestPath(magazinePath.w, destination);

                            }
                            shippingBlockType++;

                        } else if (shippingBlockType == 2) {
                            if (Shelf.getStoredEmerald() > 0) {
                                shippingType = "emerald";
                                destination = getShelfNode(shippingType, true);
                                nodes = magazinePath.graphWeighted.DijkstraShortestPath(magazinePath.w, destination);
                            }
                            shippingBlockType++;

                        } else if (shippingBlockType == 3) {
                            if (Shelf.getStoredGold() > 0) {
                                shippingType = "gold";
                                destination = getShelfNode(shippingType, true);
                                nodes = magazinePath.graphWeighted.DijkstraShortestPath(magazinePath.w, destination);
                            }
                            shippingBlockType = 1;

                        }

                    }
                    /* Wanneer de shelf bereikt is wordt een blok vanaf de plank in de cart geplaatst en bepaalt de FetchingCart de weg
                    terug naar zijn startpositie. */
                } else if (!nodes.isEmpty()) {
                    if (nodes.get(nodes.size() - 1).x == x && nodes.get(nodes.size() - 1).z == z) {
                        if (this.x != magazinePath.w.coords.x || this.z != magazinePath.w.coords.z) {

                            for (Block block : blocks) {
                                magazinePath = new MagazinePath();
                                newPath.clear();

                                if (shippingType == "diamond") {
                                    if (block.shelfPlace == Shelf.getStoredDiamond()) {
                                        block.shelfToCart(4);
                                        start = getShelfNode(shippingType, false);
                                        newPath = magazinePath.graphWeighted.DijkstraShortestPath(start,
                                                magazinePath.w);
                                        nodes.addAll(newPath);
                                    }
                                }
                                if (shippingType == "emerald") {
                                    if (block.shelfPlace == Shelf.getStoredEmerald() + 10) {
                                        block.shelfToCart(4);
                                        start = getShelfNode(shippingType, false);
                                        newPath = magazinePath.graphWeighted.DijkstraShortestPath(start,
                                                magazinePath.w);
                                        nodes.addAll(newPath);
                                    }
                                }
                                if (shippingType == "gold") {
                                    if (block.shelfPlace == Shelf.getStoredGold() + 20) {
                                        block.shelfToCart(4);
                                        start = getShelfNode(shippingType, false);
                                        newPath = magazinePath.graphWeighted.DijkstraShortestPath(start,
                                                magazinePath.w);
                                        nodes.addAll(newPath);
                                    }

                                }

                            }

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

}
