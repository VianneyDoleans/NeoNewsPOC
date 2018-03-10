package neonews.neonews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;


    ArrayList<SectionDataModel> allSampleData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        allSampleData = new ArrayList<>();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("G PlayStore");
        }
        createDummyData();
        createDummyData2();
        createDummyData3();
        RecyclerView my_recycler_view = findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);


    }

    public void createDummyData() {
        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle("Prise d’otages en Californie : le suspect et trois femmes retrouvés morts");
        ArrayList<SingleItemModel> singleItem = new ArrayList<>();
        singleItem.add(new SingleItemModel("Le monde", "https://cdn.discordapp.com/attachments/323196049055285248/422071541375696897/6a00e5548e5acb88340120a621c2a0970b-800wi.png"));
        singleItem.add(new SingleItemModel("Le figaro", "https://cdn.discordapp.com/attachments/323196049055285248/422071730689933312/640px-Le_Figaro_logo.png"));
        singleItem.add(new SingleItemModel("Youtube", "https://cdn.discordapp.com/attachments/323196049055285248/422071983350611969/YouTube-logo.png"));
        singleItem.add(new SingleItemModel("RTL", "https://cdn.discordapp.com/attachments/323196049055285248/422072080171794432/3747226-logo-de-la-station-de-radio-rtl-950x0-2.png"));
        singleItem.add(new SingleItemModel("Kombini ", "https://cdn.discordapp.com/attachments/323196049055285248/422072168235663362/Logo_Konbini_2015.png"));
        dm.setAllItemsInSection(singleItem);
        allSampleData.add(dm);
    }

    public void createDummyData2() {
        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle("Mario Kart pilote dans Google Maps");
        ArrayList<SingleItemModel> singleItem = new ArrayList<>();
        singleItem.add(new SingleItemModel("20 minutes", "https://cdn.discordapp.com/attachments/323196049055285248/422072263479787520/500px-Logo_20_Minutes.png"));
        singleItem.add(new SingleItemModel("Canal +", "https://cdn.discordapp.com/attachments/323196049055285248/422072648118304779/canal-numerique-logo.png"));
        singleItem.add(new SingleItemModel("TMC", "https://cdn.discordapp.com/attachments/323196049055285248/422072422037061633/1024px-TMC_new.png"));
        singleItem.add(new SingleItemModel("RTL", "https://cdn.discordapp.com/attachments/323196049055285248/422072080171794432/3747226-logo-de-la-station-de-radio-rtl-950x0-2.png"));
        singleItem.add(new SingleItemModel("Kombini ", "https://cdn.discordapp.com/attachments/323196049055285248/422072168235663362/Logo_Konbini_2015.png"));
        dm.setAllItemsInSection(singleItem);
        allSampleData.add(dm);
    }

    public void createDummyData3(){
        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle("6 Nations. L’Irlande s’impose face à l’Écosse et reste en course pour le grand chelem");
        ArrayList<SingleItemModel> singleItem = new ArrayList<>();
        singleItem.add(new SingleItemModel("Le monde", "https://cdn.discordapp.com/attachments/323196049055285248/422071541375696897/6a00e5548e5acb88340120a621c2a0970b-800wi.png"));
        singleItem.add(new SingleItemModel("Le figaro", "https://cdn.discordapp.com/attachments/323196049055285248/422071730689933312/640px-Le_Figaro_logo.png"));
        singleItem.add(new SingleItemModel("Youtube", "https://cdn.discordapp.com/attachments/323196049055285248/422071983350611969/YouTube-logo.png"));
        singleItem.add(new SingleItemModel("RTL", "https://cdn.discordapp.com/attachments/323196049055285248/422072080171794432/3747226-logo-de-la-station-de-radio-rtl-950x0-2.png"));
        singleItem.add(new SingleItemModel("Kombini ", "https://cdn.discordapp.com/attachments/323196049055285248/422072168235663362/Logo_Konbini_2015.png"));
        dm.setAllItemsInSection(singleItem);
        allSampleData.add(dm);
    }
}