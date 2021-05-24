package onlineClassbook.service.csv;

import onlineClassbook.config.DataSetup;
import onlineClassbook.menu.Menu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        DataSetup setUpData = new DataSetup();

        setUpData.setUp();
        try {
            Menu menu = new Menu();
            menu.chooseMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
