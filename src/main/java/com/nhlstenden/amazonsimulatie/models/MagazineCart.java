package com.nhlstenden.amazonsimulatie.models;


public abstract class MagazineCart implements Robot{

    public static MagazinePath magazinePath = new MagazinePath();

    //get op basis van type block de Node voor de robot waar de Shelf naast staat
    public static NodeWeighted getShelfNode(String type, boolean isReturn) {
        if (type == "diamond") {
            int shelfIndex = Shelf.getStoredDiamond();
            if (isReturn) {
                shelfIndex--;
            }

            if (shelfIndex == 0 || shelfIndex == 1) {
                return magazinePath.i;
            }
            if (shelfIndex == 2 || shelfIndex == 3) {
                return magazinePath.j;
            }
            if (shelfIndex == 4 || shelfIndex == 5) {
                return magazinePath.k;
            }
            if (shelfIndex == 6 || shelfIndex == 7) {
                return magazinePath.l;
            }
            if (shelfIndex == 8 || shelfIndex == 9) {
                return magazinePath.m;
            }
        }

        if (type == "gold") {
            int shelfIndex = Shelf.getStoredGold();
            if (isReturn) {
                shelfIndex--;
            }

            if (shelfIndex == 0 || shelfIndex == 1) {
                return magazinePath.p;
            }
            if (shelfIndex == 2 || shelfIndex == 3) {
                return magazinePath.q;
            }
            if (shelfIndex == 4 || shelfIndex == 5) {
                return magazinePath.r;
            }
            if (shelfIndex == 6 || shelfIndex == 7) {
                return magazinePath.s;
            }
            if (shelfIndex == 8 || shelfIndex == 9) {
                return magazinePath.t;
            }
        }
        if (type == "emerald") {
            int shelfIndex = Shelf.getStoredEmerald();
            if (isReturn) {
                shelfIndex--;
            }
            if (shelfIndex == 0 || shelfIndex == 1) {
                return magazinePath.b;
            }
            if (shelfIndex == 2 || shelfIndex == 3) {
                return magazinePath.c;
            }
            if (shelfIndex == 4 || shelfIndex == 5) {
                return magazinePath.d;
            }
            if (shelfIndex == 6 || shelfIndex == 7) {
                return magazinePath.e;
            }
            if (shelfIndex == 8 || shelfIndex == 9) {
                return magazinePath.f;
            }
        }
        return magazinePath.a;
    }


}