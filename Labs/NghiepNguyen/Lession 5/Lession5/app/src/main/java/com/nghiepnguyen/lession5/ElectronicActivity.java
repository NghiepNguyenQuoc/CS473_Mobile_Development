package com.nghiepnguyen.lession5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ElectronicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic);


        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Sceptre 32\" Class HD (720P) LED TV (X322BV-SR)", 89.9, "Black", R.drawable.product1, "558256704", "Escape into a world of splendid color and clarity with the X322BV-SR. Clear QAM tuner is included to make cable connection as easy as possible, without an antenna. HDMI input delivers the unbeatable combination of high-definition video and clear audio. A USB port comes in handy when you want to flip through all of your stored pictures and tune into your stored music. More possibilities: with HDMI, VGA, Component and Composite inputs, we offer a convenient balance between the old and new to suit your diverse preferences. \n"));
        productList.add(new Product("Straight Talk Samsung Galaxy S9 LTE Prepaid Smartphone, Black", 699.9, "Black", R.drawable.product2, "567592505", "The Camera. Reimagined. Enjoy the new Straight Talk Samsung Galaxy S9 LTE Prepaid Smartphone. This phone's camera captures photos like a pro. The revolutionary camera adapts like the human eye-capturing stunning pictures in bright daylight, moonlight and super low light using its adjustable aperture. It captures 4X as many frames per second so you can slow down reality and enjoy every frame.\n"));
        productList.add(new Product("HP Flyer Red 15.6\" 15-f272wm Laptop PC with Intel Pentium N3540 Processor, 4GB Memory, 500GB Hard Drive and Windows 10 Home\n", 299, "Red", R.drawable.product3, "554340039", "Products that are ENERGY STAR-qualified prevent greenhouse gas emissions by meeting strict energy efficiency guidelines set by the U.S. Environmental Protection Agency and the U.S. Department of Energy. The ENERGY STAR name and marks are registered marks owned by the U.S. government, as part of their energy efficiency and environmental activities.\n"));
        productList.add(new Product("Vivitar DVR-508 HD Digital Video Camera Camcorder (Red) with 16GB Card + Case + Tripod + Kit\n", 59.9, "Red", R.drawable.product4, "551884881", "1) Vivitar DVR-508 HD Digital Video Camera Camcorder (Red) \n" +
                "2) Transcend 16GB SecureDigital (SDHC) 300x UHS-I Class 10 Memory Card \n" +
                "3) Vidpro ACT-15 Accent Hard Shell Digital Camera Case (Black) \n" +
                "4) Precision Design Flexible Tabletop Mini Tripod \n" +
                "5) Precision Design 5-Piece Camera + Lens Cleaning Kit \n" +
                "6) Precision Design Universal LCD Screen Protectors \n" +
                "7) ImageRecall Digital Image Recovery Software "));


        ListView clothingListview = findViewById(R.id.electronic_listview);
        ElectronicAdapter adapter = new ElectronicAdapter(this, productList);
        clothingListview.setAdapter(adapter);
    }
}
