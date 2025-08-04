
package com.example;

import org.apache.commons.collections.list.GrowthList; 

public class VulnerableApp {
    public static void main(String[] args) {
        System.out.println("Hello from My Vulnerable App!");
        
        GrowthList list = new GrowthList();
        list.add("Test Item");
        System.out.println("List created with GrowthList (from vulnerable dependency). Size: " + list.size());
    }
}
