package com.thpa.a9019.fitnessmore;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


@RequiresApi(api = Build.VERSION_CODES.N)
public class Main2Activity extends AppCompatActivity implements MainFragment.SendDate {
    public DBHelper dbHelper;
    public TabLayout tabLayout;
    public ViewPager viewPager;
    private static int count = 0;


    private int[] tabIcons = {
            R.drawable.calenicon,
            R.drawable.muscleicon,
            R.drawable.mapicon,
            R.drawable.graphicon

    };
    private boolean doubleBackToExitPressedOnce;

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new DBHelper(this);
        if(dbHelper.getNumberofData() == 0) {

            getDatabase();

        }

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            int iconId = -1;
            switch (i) {
                case 0:
                    iconId = tabIcons[0];
                    break;
                case 1:
                    iconId = tabIcons[1];
                    break;
                case 2:
                    iconId = tabIcons[2];
                    break;
                case 3:
                    iconId = tabIcons[3];
                    break;

            }
            tabLayout.getTabAt(i).setIcon(iconId);
        }


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                if (tab.getPosition() == 3) {
//                    Intent intent = new Intent(Main2Activity.this, Statperday.class);
//                    startActivity(intent);
//                }

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

      /*  tabLayout.setSelectedTabIndicatorColor(colorId); // For the line
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                tabLayout.getTabAt(i).getIcon().setTint(getResources().getColor(R.color.gray));
            }
            tabLayout.getTabAt(selectedTabPosition).getIcon().setTint(colorId);
        }*/


    }

    public boolean getDatabase() {

        if (!dbHelper.InsertTrain("Alternate Dumbbell Curl",
                "alternate_dumbbell_curl1",
                "Bicep", "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการเตรียมดัมเบลหนึ่งคู่ หยิบดัมเบลขึ้นมา ยืนตัว ตรงปล่อยแขนลงจนสุดตามธรรมชาติโดยให้ข้อศอก ติดกับลำตัว หันฝ่ามือทั้งสองข้างเข้าหาลำตัวเป็นท่าเตรียม ฝึกท่า Alternate Dumbbell Curl" +
                        "\n\n" +
                        "1.สูดลมหายใจเข้าจนสุด จากนั้นเริ่มออกแรงเกร็งที่กล้าม เนื้อหน้าแขนข้างที่ต้องการจะฝึกเพื่อยกดัมเบลขึ้นมาจน สุดพิสัยในขณะที่กำลังยกขึ้นนั้น ให้บิดนิ้วก้อยเข้าหา ลำตัว จะสังเกตได้ว่า เมื่อยกดัมเบลขึ้นจนสุด หลังมือจะหันออก ไปทางด้านหน้าลำตัวโดย ในการขยับนั้นจะให้คลื่อนที่ เฉพาะแขนท่อนปลายต้นแขนนั้นจะไม่ขยับพร้อมกับปล่อย ลมหายใจออกจนสุด" +
                        "\n\n" +
                        "2.จากนั้นค่อยๆคลายกล้ามเนื้อหน้าแขนออกลดดัมเบลลง จนสุดเพื่อกลับสู่ท่าเตรียมพร้อมกับสูดลมหายใจเข้าจนสุด จากนั้นสลับไปทำอีกข้าง นับเป็น 1 ครั้ง",
                "a")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Barbell Shrugs",
                "barbellshrugs1",
                "Trapezius",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการยืนตัวตรง เท้าทั้งสองข้างวางห่างกันประ มาณหัวไหล่คว่ำมือทั้งสองข้างลงจับบาร์เบลด้วยความ กว้างที่กว้างไหล่เล็กน้อย เป็นท่าเตรียมฝึกท่า Barbell  Shrugs" +
                        "\n\n" +
                        "1.เริ่มต้นจากการ ออกแรงเกร็งกล้ามเนื้อหนอกเพื่อดึงบาร์ เบลขึ้นตรงๆจนสุดพิสัย พร้อมกับสูดลมหายใจเข้าจนสุด" +
                        "\n\n" +
                        "2.ค่อยๆคลายกล้ามเนื้อหนอกออก ลดบาร์เบลลงเพื่อ กลับสู่ท่าเตรียม พร้อมกับปล่อยลมหายใจออกจนสุด นับ เป็น 1 ครั้ง",
                "a")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Cable Shrug",
                "cable_shrug1",
                "Trapezius",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการ ปรับรอกให้อยู่ในตำแหน่งที่ต่ำที่สุด คล้อง บาร์ตรงเอาไว้ จากนั้น หันหน้าเข้าหาเคเบิ้ลคว่ำมือทั้งสอง ข้างลง จับบาร์ ด้วยความกว้างที่แคบกว่าหัวไหล่เล็กน้อย แล้วยืนตัวตรงเท้าทั้งสองข้างวางห่างกันประมาณหัวไหล่ ปล่อยแขนลงมาทางด้านหน้าของลำตัวตาธรรมชาติเป็นท่า เตรียมฝึกท่า Cable Shrug (Straight Bar)" +
                        "\n\n" +
                        "1.เริ่มต้นจากการสูดลมหายใจเข้าจนสุด จากนั้นออกแรง เกร็งกล้ามเนื้อหนอก บีบสะบัก เพื่อดึงบาร์ขึ้นในแนวตรง จนสุดพิสัยพร้อมกับปล่อยลมหายใจออกจนสุด" +
                        "\n\n" +
                        "2.ค่อยๆคลายกล้ามเนื้อหนอกออกลดบาร์ลงเพื่อกลับสู่ ท่าเตรียมพร้อมกับสูดลมหายใจเข้าจนสุด นับเป็น 1 ครั้ง",
                "a")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Cable Upright Row",
                "cableup_right2",
                "Trapezius",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการ ปรับรอกให้อยู่ในตำแหน่งที่ต่ำที่สุดคล้อง บาร์ตรงเอาไว้ จากนั้น หันหน้าเข้าหาเคเบิ้ลคว่ำมือทั้งสอง ข้างลงจับบาร์ ด้วยความกว้างที่แคบกว่าหัวไหล่เล็กน้อย แล้วยืนตัวตรงเท้าทั้งสองข้างวางห่างกันประมาณหัวไหล่ ปล่อยแขนลงมาทางด้านหน้าของลำตัวตามธรรมชาติเป็น ท่าเตรียมฝึกท่า Cable Upright Row(Straight Bar)" +
                        "\n\n" +
                        "1.เริ่มต้นจากการสูดลมหายใจเข้าจนสุด จากนั้นออกแรง เกร็งกล้ามเนื้อหน้าอก เพื่อยกบาร์ขึ้นจนสุดพิสัยโดย พยายามดึงบาร์ขึ้นในแนวเส้นตรง รักษาระดับของข้อศอก ให้กางออกไปทางด้านข้างของลำตัวและอยู่สูงกว่าระดับ ของข้อมือเสมอ พร้อมกับปล่อยลมหายใจออกจนสุด" +
                        "\n\n" +
                        "2.ค่อยๆคลายกล้ามเนื้อหนอกออก ลดบาร์ลงเพื่อกลับสู่ท่า เตรียม พร้อมกับสูดลมหายใจเข้าจนสุด นับเป็น 1 ครั้ง",
                "a")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Concentration Curl",
                "concentration_curl1",
                "Bicep",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการเตรียมดัมเบลหนึ่งอัน นั่งลงบนเบาะจากนั้น เอนตัวลงมาทางด้านหน้า ให้แขนที่ต้องการฝึกพิงกับต้นขา เอาไว้โดยให้ข้อศอกเลยออกมา ใช้มือจับดัมเบลขึ้นมาให้ ลอยอยู่เหนือพื้น เป็นท่าเตรียมฝึกท่า Concentration Curl" +
                        "\n\n" +
                        "1.สูดลมหายใจเข้าจนสุด จากนั้นเริ่มออกแรงเกร็งที่กล้าม เนื้อหน้าแขนเพื่อยกดัมเบลขึ้นมาจนสุดพิสัย พร้อมกับ ปล่อยลมหายใจออก" +
                        "\n\n" +
                        "2.จากนั้นค่อยๆคลายกล้ามเนื้อหน้าแขนออก ลดดัมเบลลง เพื่อกลับสู่ท่าเตรียม พร้อมกับสูดลมหายใจเข้าจนสุด นับเป็น 1 ครั้ง ทำจนครบจำนวนครั้งแล้วสลับไปทำอีก ข้างหนึ่ง นับเป็น 1 เซท",
                "a")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Dumbbell Shrugs",
                "dumbbell_shrugs1",
                "Trapezius",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการ ยืนตัวตรง เท้าทั้งสองข้างวางห่างกันประ มาณหัวไหล่ คว่ำมือทั้งสองข้างลง จับดัมเบลปล่อยแขนลง ตามธรรมชาติ ไว้ทางด้านข้างของลำตัว ในลักษณะที่หันฝ่า มือเข้าหาลำตัว เป็นท่าเตรียมฝึกท่า Dumbbell Shrugs" +
                        "\n\n" +
                        "1.เริ่มต้นจากการ ออกแรงเกร็งกล้ามเนื้อหนอก เพื่อดึงดัม เบลขึ้นตรงๆจนสุดพิสัย พร้อมกับสูดลมหายใจเข้าจนสุด" +
                        "\n\n" +
                        "2.ค่อยๆคลายกล้ามเนื้อหนอกออก ลดดัมเบลลงเพื่อกลับ สู่ท่าเตรียม พร้อมกับปล่อยลมหายใจออกจนสุด นับเป็น 1 ครั้ง",
                "b")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Incline Dumbbell Curls",
                "incline_dumbbell_curls1",
                "Bicep",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการเตรียมดัมเบลหนึ่งคู่ และเบาะนอนปรับ เอียงที่ 60 องศา จากนั้นนั่งลงไปบนเบาะแล้วหยิบดัมเบล ขึ้นมา ปล่อยแขนลงจนสุดตามธรรมชาติโดยให้ข้อศอกติด กับลำตัว หันฝ่ามือทั้งสองข้างออกจากลำตัวให้มากที่สุด เท่าที่จะทำได้เป็นท่าเตรียมฝึกท่า Incline Inner Bicep Curl" +
                        "\n\n" +
                        "1.สูดลมหายใจเข้าจนสุด จากนั้นเริ่มออกแรงเกร็งที่กล้าม เนื้อหน้าแขน เพื่อยกดัมเบลทั้งสองข้างขึ้นมาจนสุดพิสัย โดยในการขยับนั้นจะให้เคลื่อนที่เฉพาะแขนท่อนปลาย ต้นแขนนั้นจะไม่ขยับ พร้อมกับปล่อยลมหายใจออกจนสุด" +
                        "\n\n" +
                        "2.จากนั้นค่อยๆคลายกล้ามเนื้อหน้าแขนออก ลดดัมเบลลง จนสุด เพื่อกลับสู่ท่าเตรียม พร้อมทั้งสูดลมหายใจเข้าจนสุด นับเป็น 1 ครั้ง ตลอดการฝึกด้วยท่า Incline Inner Bicep Curl พยายาม ให้หัวติดเบาะตลอดการฝึก เพื่อให้แนวกระดูกสันหลังเป็น ไปตามธรรมชาติ", "b")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Seated Dumbbell Hammer Curls",
                "seated_dumbbell_hammer_curls1",
                "Bicep",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการเตรียมดัมเบลหนึ่งคู่ นั่งพิงหลังลงบนเบาะ ที่ทำมุม 90 องศากับพื้นโลก หยิบดัมเบลขึ้นมาแล้ว เท้า ทั้งสองข้างวางห่างกันประมาณหัวไหล่ ปล่อยแขนลงจน สุดตามธรรมชาติโดยให้ข้อศอกติดกับลำตัว หันฝ่ามือทั้งสอง ข้างเข้าหาลำตัวในลักษณะเหมือนว่ากำลังจับค้อนอยู่ เป็น ท่าเตรียมฝึกท่า Seated Dumbbell Hammer Curls" +
                        "\n\n" +
                        "1.สูดลมหายใจเข้าจนสุด จากนั้นเริ่มออกแรงเกร็งที่กล้าม เนื้อหน้าแขน เพื่อยกดัมเบลขึ้นมาจนสุดพิสัยในลักษณะที่ ฝ่ามือทั้งสองข้างยังคงหันเข้าหาลำตัว โดยในการขยับนั้น จะให้เคลื่อนที่เฉพาะแขนท่อนปลาย ต้นแขนนั้นจะไม่ขยับ พร้อมกับปล่อยลมหายใจออกจนสุด" +
                        "\n\n" +
                        "2.จากนั้นค่อยๆคลายกล้ามเนื้อหน้าแขนออก ลดดัมเบลลง จนสุด เพื่อกลับสู่ท่าเตรียม พร้อมทั้งสูดลมหายใจเข้าจนสุด นับเป็น 1 ครั้ง", "b")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Standing Reverse Grip Dumbbell Curl",
                "standing_reverse_grip_dumbbell_cur1",
                "Bicep",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการเตรียมดัมเบลหนึ่งคู่ ยืนตรง วางเท้าทั้งสองข้างห่างกันด้วยความกว้างประมาณหัวไหล่ คว่ำมือทั้งสองข้างลง หยิบดัมเบลขึ้นมา ปล่อยแขนลงจนสุดตามธรรมชาติ เป็นท่าเตรียมฝึกท่า Standing Reverse Grip Dumbbell Curl" +
                        "\n\n" +
                        "1.สูดลมหายใจเข้าจนสุด จากนั้นเริ่มออกแรงเกร็งที่กล้ามเนื้อหน้าแขนเพื่อยกดัมเบลทั้งสองข้างขึ้นมาจนสุดพิสัย โดยในการขยับนั้นจะให้เคลื่อนที่เฉพาะแขนท่อนปลาย ต้นแขนนั้นจะไม่ขยับ พร้อมกับปล่อยลมหายใจออกจนสุด" +
                        "\n\n" +
                        "2.จากนั้นค่อยๆคลายกล้ามเนื้อหน้าแขนออก ลดดัมเบลลงจนสุด เพื่อกลับสู่ท่าเตรียม พร้อมทั้งสูดลมหายใจเข้าจนสุด นับเป็น 1 ครั้ง", "b")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Floor Back Extension",
                "floorbackextension1",     //do not put picture
                "Back",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการนอนคว่ำลงบนพื้น เป็นท่าเตรียมฝึกท่า Floor Back Extension " +
                        "\n\n" +
                        "1.เริ่มต้นจากการสูดลมหายใจเข้าจนสุด จากนั้นออกแรงเกร็งกล้ามเนื้อหลังล่าง เพื่อยกลำตัวช่วงบนและปลายเท้าขึ้นจนสุดพิสัย พร้อมกับปล่อยลมหายใจออกจนสุด โดยอาจจะใช้การกางแขนออกเพื่อให้ฝึกได้ง่ายขึ้น" +
                        "\n\n" +
                        "2.ค่อยๆคลายกล้ามเนื้อหลังล่างออก ลดลำตัวและปลายเท้าลง เพื่อกลับสู่ท่าเตรียม นับเป็น 1 ครั้ง", "b")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Incline Bench Dumbbell Row",
                "inclinebenchdumbbellrow1",     //do not put picture
                "Back",
                "inclinpronedumbbellbentoverrow1",
                "\t" +
                        "เริ่มต้นจากการนอนคว่ำตัวลงบนเบาะนอนเอียงขึ้น 30 องศา ชันเข่าลงบนเบาะ มือทั้งสองข้างจับดัมเบลขึ้นมา ในลักษณะหันฝ่ามือทั้งสองข้างเข้าหากัน ปล่อยแขนลงมาตามธรรมชาติ ให้กล้ามเนื้อหลังถูกเหยียดตัวจนสุด เป็นท่าเตรียมฝึกท่า Incline Bench Dumbbell Row " +
                        "\n\n" +
                        "1.เริ่มต้นจากการสูดลมหายใจเข้าจนสุด จากนั้นออกแรงเกร็งกล้ามเนื้อหลัง เพื่อดึงดัมเบลขึ้นจนสุดพิสัย โดยพยายามบีบมุมของข้อศอกเข้าหาลำตัวขณะเคลื่อนที่ พร้อมกับปล่อยลมหายใจออกจนสุด" +
                        "\n\n" +
                        "2.จากนั้นค่อยๆ คลายกล้ามเนื้อหลังออก ลดดัมเบลลง เพื่อกลับสู่ท่าเตรียม พร้อมกับสุดลมหายใจเข้าจนสุด นับเป็น 1 ครั้ง ", "b")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Wide-Grip Lat Pulldown",
                "widegriplatpulldown1",     //do not put picture
                "Back",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการคว่ำมือทั้งสองข้างลง จับบาร์ด้วยความกว้างที่กว้างกว่าหัวไหล่ประมาณครึ่งศอก จากนั้นนั่งลงบนเบาะของเครื่อง Cable Pulldown พยายามล็อกขากับเบาะรองไว้ให้แน่น ปล่อยให้กล้ามเนื้อหลังถูกเหยียดออกจนสุด ไปตามแรงของเคเบิ้ล เป็นท่าเตรียมฝึกท่า Wide-Grip Lat Pulldown" +
                        "\n\n" +
                        "1.เริ่มต้นจากการสูดลมหายใจเข้าจนสุด จากนั้น แอ่นอก ออกแรงเกร็งกล้ามเนื้อหลัง เพื่อดึงด้ามจับลงมาจนสัมผัสกับหน้าอก โดยในขณะที่ออกแรงนั้นพยายามปิดมุมข้อศอก เข้าหาลำตัว พร้อมกับปล่อยลมหายใจออกจนสุด" +
                        "\n\n" +
                        "2.ค่อยๆคลายกล้ามเนื้อหลังออก ปล่อยด้ามจับกลับขึ้นไป เพื่อกลับสู่ท่าเตรียม พร้อมกับสูดลมหายใจเข้าจนสุด นับเป็น 1 ครั้ง", "b")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Russian Twist",
                "russiantwist1",     //do not put picture
                "Abdominal",
                "ic_listtype",
                "\t" +
                        "นั่งงอขา ยกเท้าให้ลอยจากพื้น เอนตัวไปข้างหลังเล็กน้อย เกร้งที่บริเวณหน้าท้อง มือประสานไว้หน้าลำตัว บิดตัวเอาศอกข้างซ้าย ลงไปใกล้พื้นที่สุด บิดกลับไปทางขวา ทำสลับไปจนครบจำนวนที่กำหนด"
                        , "b")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }
        if (!dbHelper.InsertTrain("Suitcase Crunch",
                "suitcasecrunch1",     //do not put picture
                "Abdominal",
                "ic_listtype",
                "\t" +
                        "เริ่มต้นจากการนอนหงายหน้าราบลงบนพื้น ยกแขนขึ้นเหนือหัวเหยียดแขนตรงให้ขนานและลอยขึ้นจากพื้นเล็กน้อย  เหยียดขาตรงวางไปตามพื้น เป็นท่าเตรียมฝึกท่า Suitcase Crunch" +
                        "\n\n" +
                        "1.เริ่มต้นด้วยการสูดลมหายใจเข้าจนสุด จากนั้นออกแรงเกร็งกล้ามเนื้อหน้าท้องเพื่อยกลำตัวส่วนบนและส่วนล่างขึ้นพร้อมกัน โดยให้ปลายนิ้วไปแตะกับปลายเท้าที่ตำแหน่งสูงสุด พร้อมปล่อยลมหายใจออกจนสุด" +
                        "\n\n" +
                        "2.ค่อยๆ คลายกล้ามเนื้อหน้าท้องออก ลดลำตัวส่วนบนและส่วนล่างลง พร้อมกับสูดลมหายใจเข้าจนสุด นับเป็น 1 ครั้ง", "b")) {
            Toast.makeText(getApplicationContext(), "Error Inserted", Toast.LENGTH_SHORT).show();
        }


        return true;
    }


    @Override
    public void sendData(int day, int month, int year) {
        String tag = "android:switcher:" + R.id.viewpager + ":" + 3;
        RecFragment f = (RecFragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedDate(day, month, year);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MainFragment();
                case 1:
                    return new ContainerFragment();
                case 2:
                    return new MapFragment();
                case 3:
                    return new RecFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }
    }
}
