package khushboo.rohit.osmnavi;

import android.app.Application;

import java.util.Locale;

/**
 * Created by rohit on 26/4/17.
 */


public class MyApp extends Application{
    public boolean hasRefreshed;

    @Override
    public void onCreate() {
        super.onCreate();
        hasRefreshed=false;
    }

}
