package com.nghiepnguyen.lession5;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ElectronicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic);
        ActionBar aBar = getSupportActionBar();
        aBar.setTitle("Electronic Category");

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
        productList.add(new Product("Dell - XPS Tower - Intel Core i7 - 8GB Memory - 1TB 7200 RPM HD - Intel HD Graphics", 935.99, "Black", R.drawable.product5, "565627377", "Dell - XPS 8920, 7th Generation Intel Core i7-7700 Processor, 8GB 2400MHz DDR4, 1TB 7200 RPM, Intel HD Graphics, DVD-RW Drive, Bluetooth 4.2, 1x HDMI, 7 X USB 3.0, 2 x USB 2.0, 2 x USB 3.1; Front: 1x Combo headphone/mic Jack , SD-Card Reader, 10/100/1000 Gigabit Ethernet, Height: 15.1 X Width: 7.0X Depth 13.3 , 29.0 lbs, Dell Wired Keyboard BLK, W10H-HE 64 ENG, Integrated 5.1 with WAVE MAXX, XPS 8920, Black Chassis , Microsoft Office 30 Day Trial , McAfee LiveSafe 12 Month Subscription"));
        productList.add(new Product("Refurbished HP Dreamy Teal 20-c013w All-in-One Desktop PC with Intel Celeron J3060 Processor, 4GB Memory, 19.5\" Display, 500GB Hard Drive and Windows 10", 199, "White", R.drawable.product6, "556322894", "Congratulations, your search for the perfect home PC is over. The HP All-in-One 20-c013w Desktop PC has everything you need in a home PC. The Intel Celeron J3060 processor packs enough power for your everyday needs, and the 4GB of RAM give you plenty of space to run programs and browse the Internet. The beautiful 19.5-inch HD integrated monitor, backed by the Intel HD Graphics adapter, provides stunning image quality, and the 500GB hard drive offers more than enough space to store all your files. "));
        ListView clothingListview = findViewById(R.id.electronic_listview);
        ElectronicAdapter adapter = new ElectronicAdapter(this, productList);
        clothingListview.setAdapter(adapter);
    }
}
