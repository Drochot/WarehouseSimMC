package com.nhlstenden.amazonsimulatie.models;

import java.util.*;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
public interface Robot extends Object3D, Updatable {

    public double x = 0;
    public double y = 0;
    public double z = 0;

    public double rotationX = 0;
    public double rotationY = 0;
    public double rotationZ = 0;

    public NodeWeighted destination = null;

    public NodeWeighted start = null;

    public int cartId = 0;

    static int deliveryBlockType = 1;
    static int shippingBlockType = 2;

    public boolean visible = true;

    public boolean cartFull = false;

    public double step = 0.125;

    public static boolean cartTwoReady = false;
    public static boolean cartThreeReady = true;

    public static List<Robot> robots = new ArrayList<>();

    public abstract void goCart();

    public abstract boolean update();

    /*
     * Deze update methode wordt door de World aangeroepen wanneer de World zelf
     * geupdate wordt. Dit betekent dat elk object, ook deze robot, in de 3D wereld
     * steeds een beetje tijd krijgt om een update uit te voeren. In de
     * updatemethode hieronder schrijf je dus de code die de robot steeds uitvoert
     * (bijvoorbeeld positieveranderingen). Wanneer de methode true teruggeeft
     * (zoals in het voorbeeld), betekent dit dat er inderdaad iets veranderd is en
     * dat deze nieuwe informatie naar de views moet worden gestuurd. Wordt false
     * teruggegeven, dan betekent dit dat er niks is veranderd, en de informatie
     * hoeft dus niet naar de views te worden gestuurd. (Omdat de informatie niet
     * veranderd is, is deze dus ook nog steeds hetzelfde als in de view)
     */

    static List<Block> blocks = new ArrayList<Block>();

    static String deliveryType = new String();
    static String shippingType = new String();

    public MagazinePath magazinePath = new MagazinePath();

    ArrayList<IntPair> nodes = new ArrayList<IntPair>();

    ArrayList<IntPair> newPath = new ArrayList<IntPair>();

    // only results of 1/2^n where n is positive whole number work.

    @Override
    public String getUUID();

    @Override
    public String getType();
    /*
     * Dit onderdeel wordt gebruikt om het type van dit object als stringwaarde
     * terug te kunnen geven. Het moet een stringwaarde zijn omdat deze informatie
     * nodig is op de client, en die verstuurd moet kunnen worden naar de browser.
     * In de javascript code wordt dit dan weer verder afgehandeld.
     */

    @Override
    public double getX();

    @Override
    public double getY();

    @Override
    public double getZ();

    @Override
    public double getRotationX();

    @Override
    public double getRotationY();

    @Override
    public double getRotationZ();

    public int getCartId();

    public boolean getVisible();

    public List<Robot> getRobots();

}