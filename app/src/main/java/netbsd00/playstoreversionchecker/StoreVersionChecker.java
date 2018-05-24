package netbsd00.playstoreversionchecker;

import org.jsoup.Jsoup;

import java.io.IOException;

public class StoreVersionChecker {
    private final static String BASE_URL = "https://play.google.com/store/apps/details?id=";

    public static String getStoreVersion(String packageName) {
        try {
            return Jsoup.connect(BASE_URL + packageName)
                    .timeout(5000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div:contains(Current Version)").last().parent()
                    .select("span").last()
                    .ownText();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
