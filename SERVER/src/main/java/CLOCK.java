import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;

public class CLOCK extends Thread {

    @Override
    public void run() {

        while (true) {
            int cnt  = 1;
            String yy = new java.text.SimpleDateFormat("HH").format(new java.util.Date());
            String mm_ss = new java.text.SimpleDateFormat("mm:ss").format(new java.util.Date());

            if (cnt>0||(Integer.parseInt(yy) == 03 &&Integer.parseInt(mm_ss) == 0000) ) {
                try {
                    if(cnt==1)
                        cnt--;
                    Document doc = Jsoup.connect("http://www.dgsw.hs.kr/user/carte/list.do?menuCd=").get();

                    Elements el = doc.select("div.meals_today_top");

                    String html = el.toString();

                    html = html.substring(html.indexOf("<ul class=\"meals_today_list\">"), html.length());

                    DATA.food_morning = html.substring(html.indexOf("alt=\"조식\">") + 10, html.indexOf("</li>"));
                    html = html.substring(html.indexOf("</li>") + 5, html.length());

                    DATA.food_morning = DATA.food_morning.replaceAll("[.]", "");
                    DATA.food_morning = DATA.food_morning.replaceAll("[0-9]", "");
                    DATA.food_morning = DATA.food_morning.trim().replaceAll("  ", "<br>");

                    DATA.food_lunch = html.substring(html.indexOf("alt=\"중식\">") + 10, html.indexOf("</li>"));
                    html = html.substring(html.indexOf("</li>") + 5, html.length());

                    DATA.food_lunch =  DATA.food_lunch.replaceAll("[.]", "");
                    DATA.food_lunch =  DATA.food_lunch.replaceAll("[0-9]", "");
                    DATA.food_lunch =  DATA.food_lunch.trim().replaceAll("  ", "<br>");

                    DATA.food_dinner = html.substring(html.indexOf("alt=\"석식\">") + 10, html.indexOf("</li>"));
                    html = html.substring(html.indexOf("</li>") + 5, html.length());

                    DATA.food_dinner =  DATA.food_dinner.replaceAll("[.]", "");
                    DATA.food_dinner =  DATA.food_dinner.replaceAll("[0-9]", "");
                    DATA.food_dinner =  DATA.food_dinner.replaceAll("  ", "<br>");
                    DATA.food_dinner =  DATA.food_dinner.trim().replaceAll("ㆍ", "");

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

        }

    }

}


