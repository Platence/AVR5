package WRSOnLine;

import dateClass.OpenWrs;
import javafx.application.Platform;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import sample.Controller;

public class OpenBrowser {

    public final static String adressBr = "https://wrs.x5.ru/";
    private final static WebView browser = new WebView();
    private final static WebEngine webEngine = browser.getEngine();
    private final static double sizeX = 1255;
    private final static double sizeY = 620;
    private static boolean online = false;




    public void go(Controller cr){
        if(!SettingsOnWrs.onWebPage){return;}
        OpenWrs.wasOpened = true;
        if(online){return;}
        cr.browserPane.getChildren().clear();
        cr.browserPane.getChildren().add(retNewFlowPaneBrowser(cr));
        cr.browserPane.setMinSize(sizeX,sizeY);
        cr.browserPane.setMaxSize(sizeX,sizeY);
        online = true;
        webEngine.setJavaScriptEnabled(true);
        webEngine.getHistory().setMaxSize(0);
    }


    public void redirectFromButton(Controller cr,String adress){

        cr.browserPane.getChildren().clear();
        cr.browserPane.getChildren().add(retNewFlowPaneBrowserWithPar(cr,adress));

    }

    public FlowPane retNewFlowPaneBrowserWithPar(Controller cr,String adressX){

        if(!SettingsOnWrs.onWebPage){
            return null;
        }

        FlowPane fp = new FlowPane();
        browser.setMinSize(sizeX,sizeY);
        browser.setZoom(0.82);
        fp.getChildren().add(browser);


        webEngine.setJavaScriptEnabled(true);
        webEngine.load(adressX);
        fp.setMinSize(sizeX,sizeY);



        System.out.println(webEngine.getUserAgent());
        return fp;

    }

    public FlowPane retNewFlowPaneBrowser(Controller cr){

        if(!SettingsOnWrs.onWebPage){
            return null;
        }

        FlowPane fp = new FlowPane();
        browser.setMinSize(sizeX,sizeY);
        browser.setZoom(0.82);
        fp.getChildren().add(browser);



        webEngine.setJavaScriptEnabled(true);
        webEngine.load(adressBr);
        fp.setMinSize(sizeX,sizeY);



        System.out.println(webEngine.getUserAgent());
        return fp;

    }


}
