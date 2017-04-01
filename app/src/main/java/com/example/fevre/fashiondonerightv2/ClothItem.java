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
        public static ArrayList<Material> getMaterialList(){
            ArrayList<Material> materials = new ArrayList<>();
            materials.add(OrganicWool);
            materials.add(ConventionalWool);
            materials.add(Cotton);
            materials.add(Silk);
            materials.add(Polyester);
            materials.add(Leather);
            materials.add(Linen);
            return materials;
        }
    }



    public enum Usage{
        Often, Avage, Rare;
        public static ArrayList<Usage> getUsageList(){
            ArrayList<Usage> usages = new ArrayList<>();
            usages.add(Often);
            usages.add(Avage);
            usages.add(Rare);
            return usages;
        }
    }



    public static class Brand{
        private static ArrayList<Brand> brands = new ArrayList<Brand>();

        private String name;
        private int id;
        private String url;
        private String score;

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

        public String getScore() {
            return score;
        }

        private Brand(String name, int id, String url, String score){
            this.name = name;
            this.id = id;
            this.url = url;
            this.score = score;
        }

        public static Brand createBrand(String name, int id, String url, String score){
            for(Brand b: brands){
                if (b.getName().equals(name)) return b;
            }
            Brand newBrand = new Brand(name, id, url,score);
            brands.add(newBrand);
            return newBrand;
        }
    }

    public ClothItem() {
        this.brand = null;
        this.type = null;
        this.material = null;
        this.usage = null;
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
