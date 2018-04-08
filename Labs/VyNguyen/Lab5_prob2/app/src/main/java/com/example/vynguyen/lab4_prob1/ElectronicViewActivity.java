package com.example.vynguyen.lab4_prob1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ElectronicViewActivity extends Activity {
    ListView lv;

    // Init data
    List<Product> lstProduct = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_view);

        //
        lstProduct.add(new Product("iPad Mini 16GB WiFi + 4G LTE AT&T", 139,
                "White", R.drawable.ipad_pro, "#01", "Using the iPad feels like second nature, even if it's your first time. " +
                "Simple, gentle taps and swipes control this window to the world. " +
                "Fine attention to detail makes the iPad mini a top notch tablet."));
        lstProduct.add(new Product("Inspiron 15 3000", 279.99, "Black",
                R.drawable.delllaptop, "#02", "Stay powered up with this lightweight, 15” " +
                "laptop featuring Intel® processors, excellent battery life and sound by Waves MaxxAudio® Pro."));
        lstProduct.add(new Product("Iphone 8", 699.99,"Silver", R.drawable.iphone8,
                "#03", "Phone 8 introduces an all-new glass design. " +
                "The world's most popular camera, now even better. " +
                "The smartest, most powerful chip ever in a smartphone. " +
                "Wireless charging that's truly effortless. " +
                "And augmented reality experiences never before possible. iPhone 8. " +
                "A new generation of iPhone. Verizon is your destination for the hottest phones " +
                "like the Apple iPhone 8, all on the nation's most reliable 4G LTE network."));
        lstProduct.add(new Product("55 inches Class J6201 Full HD LED TV", 499.99, "Black",
                R.drawable.samsungledtv, "#04", "Enjoy a viewing experience that is 2X the clarity of standard HD TVs." +
                "See every image as the director intended with enriched colors – even with older, non-HD content." +
                "Access your favorite program choices, live TV, video on demand, apps, " +
                "and social media in one easy-to-browse navigation experience." +
                "Easily browse the web right on your TV – enjoy everything from online shopping " +
                "and social media to entertainment news and the latest YouTube hits."));
        lstProduct.add(new Product("Gold Phantom", 2990, "Gold", R.drawable.phantom, "#05",
                "The best sound in the world. Feel the physical impact of ultra-dense sound. " +
                        "Experience your music with the most extreme power, " +
                        "clarity and precision ever achieved. Breathtaking."));

        //
        lv = (ListView)findViewById(R.id.listViewElectronic);
        lv.setAdapter(new ElectronicAdapter(this, lstProduct));
    }
}
