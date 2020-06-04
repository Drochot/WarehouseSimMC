package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;

/* De Shelf class bevat de coordinaten van de plekken op de planken waar de blokken opgeslagen worden.
 Daarnaast houdt het ook bij hoe veel van elke soort blok er op de planken staan om te kunnen bepalen waar de volgende terecht moet komen. */

public class Shelf {

    IntPair diamondA = new IntPair(-7, -12);
    IntPair diamondB = new IntPair(-6, -12);
    IntPair diamondC = new IntPair(-4, -12);
    IntPair diamondD = new IntPair(-3, -12);
    IntPair diamondE = new IntPair(-1, -12);
    IntPair diamondF = new IntPair(0, -12);
    IntPair diamondG = new IntPair(2, -12);
    IntPair diamondH = new IntPair(3, -12);
    IntPair diamondI = new IntPair(5, -12);
    IntPair diamondJ = new IntPair(6, -12);

    IntPair goldA = new IntPair(-7, -8);
    IntPair goldB = new IntPair(-6, -8);
    IntPair goldC = new IntPair(-4, -8);
    IntPair goldD = new IntPair(-3, -8);
    IntPair goldE = new IntPair(-1, -8);
    IntPair goldF = new IntPair(0, -8);
    IntPair goldG = new IntPair(2, -8);
    IntPair goldH = new IntPair(3, -8);
    IntPair goldI = new IntPair(5, -8);
    IntPair goldJ = new IntPair(6, -8);

    IntPair emeraldA = new IntPair(-7, -16);
    IntPair emeraldB = new IntPair(-6, -16);
    IntPair emeraldC = new IntPair(-4, -16);
    IntPair emeraldD = new IntPair(-3, -16);
    IntPair emeraldE = new IntPair(-1, -16);
    IntPair emeraldF = new IntPair(0, -16);
    IntPair emeraldG = new IntPair(2, -16);
    IntPair emeraldH = new IntPair(3, -16);
    IntPair emeraldI = new IntPair(5, -16);
    IntPair emeraldJ = new IntPair(6, -16);

    private static ArrayList<IntPair> goldSpots = new ArrayList<IntPair>();
    private static ArrayList<IntPair> diamondSpots = new ArrayList<IntPair>();
    private static ArrayList<IntPair> emeraldSpots = new ArrayList<IntPair>();

    private static int storedGold = 0;
    private static int storedDiamond = 0;
    private static int storedEmerald = 0;

    Shelf() {
        goldSpots.add(goldA);
        goldSpots.add(goldB);
        goldSpots.add(goldC);
        goldSpots.add(goldD);
        goldSpots.add(goldE);
        goldSpots.add(goldF);
        goldSpots.add(goldG);
        goldSpots.add(goldH);
        goldSpots.add(goldI);
        goldSpots.add(goldJ);

        diamondSpots.add(diamondA);
        diamondSpots.add(diamondB);
        diamondSpots.add(diamondC);
        diamondSpots.add(diamondD);
        diamondSpots.add(diamondE);
        diamondSpots.add(diamondF);
        diamondSpots.add(diamondG);
        diamondSpots.add(diamondH);
        diamondSpots.add(diamondI);
        diamondSpots.add(diamondJ);

        emeraldSpots.add(emeraldA);
        emeraldSpots.add(emeraldB);
        emeraldSpots.add(emeraldC);
        emeraldSpots.add(emeraldD);
        emeraldSpots.add(emeraldE);
        emeraldSpots.add(emeraldF);
        emeraldSpots.add(emeraldG);
        emeraldSpots.add(emeraldH);
        emeraldSpots.add(emeraldI);
        emeraldSpots.add(emeraldJ);
    }

    /* Functies om de counters voor het aantal opgeslagen blokken te updaten. */

    public static void incrementGold() {
        storedGold++;
    }

    public static void incrementEmerald() {
        storedEmerald++;
    }

    public static void incrementDiamond() {
        storedDiamond++;
    }

    public static void decrementGold() {
        storedGold--;
    }

    public static void decrementEmerald() {
        storedEmerald--;
    }

    public static void decrementDiamond() {
        storedDiamond--;
    }

    

    public static ArrayList<IntPair> getGoldSpots() {
        return goldSpots;
    }

    public static ArrayList<IntPair> getDiamondSpots() {
        return diamondSpots;
    }

    public static ArrayList<IntPair> getEmeraldSpots() {
        return emeraldSpots;
    }

    public static int getStoredGold() {
        return storedGold;
    }

    public static int getStoredDiamond() {
        return storedDiamond;
    }

    public static int getStoredEmerald() {
        return storedEmerald;
    }

}