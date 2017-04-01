package com.example.fevre.fashiondonerightv2;

import java.util.ArrayList;

/**
 * Created by mrjakob on 01-04-2017.
 */

public class ClothItem {
    private Brand brand;
    private Type type;
    private Material material;
    private Usage usage;

    public enum Type{
        Hat, Pants, Shoes, Shirt, Jacket;
    }

    public enum Material{
        OrganicWool, ConventionalWool, Cotton, Silk, Polyester, Leather, Linen;
    }

    public enum Usage{
        Often, Avage, Rare;
    }

    public static class Brand{
        private static ArrayList<Brand> brands = new ArrayList<Brand>();

        private String name;
        private int id;
        private String url;

        public static ArrayList<Brand> getBrands() {
            return brands;
        }

        public String getName() {

            return name;
        }

        public int getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }

        private Brand(String name, int id, String url){
            this.name = name;
            this.id = id;

            this.url = url;
        }

        public Brand createBrand(String name, int id, String url){
            for(Brand b: brands){
                if (b.getName().equals(name)) return b;
            }
            Brand newBrand = new Brand(name, id, url);
            return newBrand;
        }
    }

    public ClothItem(Brand brand, Type type, Material material, Usage usage) {
        this.brand = brand;
        this.type = type;
        this.material = material;
        this.usage = usage;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public Brand getBrand() {
        return brand;
    }

    public Type getType() {
        return type;
    }

    public Material getMaterial() {
        return material;
    }

    public Usage getUsage() {
        return usage;
    }
}
