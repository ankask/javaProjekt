package sample;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

/*
*@author Annika Kask
*@since 1.8
**/

/*Valdav osa muude märgeteta koodist sellel lehel saadud lehelt
* http://i200.itcollege.ee/#Kasutajaliides
* ja modifitseeritud minu poolt projektile sobivaks
**/

public class Main extends Application {



/**@Author Hardi Tiitus ja Marko Esna - 1 rida*/
    @Override

    public void start(Stage primaryStage) throws Exception{

/**@Author Marko Esna - 1 rida */
        Funktsioonid func = new Funktsioonid(); //Loob objekti
        Pane paan = new Pane();   //Loob uue paani
        Scene stseen = new Scene(paan, 375, 425); //Loob uue stseeni, määrab selle kõrguse ja laiuse
        primaryStage.setScene(stseen);
        primaryStage.show();
        primaryStage.setTitle("Palgaarvestus"); //Määrab aknale pealkirja

//Abi saadud:  lehelt http://i200.itcollege.ee/#Kasutajaliides ja Külli Ristilt

        VBox vbox = new VBox(2); //Loob 2-pikslise vahega kastid üksteise alla
        vbox.setAlignment(Pos.BOTTOM_CENTER); //Joondab kastide kohal oleva teksti kastide keskele
        vbox.setTranslateY(100); //Nihutab kastid ja teksti ülemisest äärest eemale
        vbox.setTranslateX(100); //Nihutab kastid ja teksti vasakust äärest eemale


        //Loob lahtrid teksti ja numbrite sisestuseks
/**@Author Külli Ristilt  - 1 rida */
        Label nimiVali = new Label("Sisesta töötaja nimi:"); //Loob ja sisestab rea enne sisestuskastikest
        TextField nimi = new TextField();      //Loob tekstikasti
        nimi.setPromptText("sisesta nimi");   //loob informatiivse teksti tekstikasti



        Label netoVali = new Label("Sisesta töötaja netopalk:"); //Loob ja sisestab rea enne sisestuskastikest
        TextField neto = new TextField();          //Loob tekstikasti
        neto.setPromptText("sisesta netopalk");  //loob informatiivse teksti tekstikasti

        Label brutoVali = new Label("Sisesta töötaja brutopalk:"); //Loob ja sisestab rea enne sisestuskastikest
        TextField bruto = new TextField();      //Loob tekstikasti
        bruto.setPromptText("sisesta brutopalk");   //loob informatiivse teksti tekstikasti

        //Loob nupud sisestuse, arvutamise ja puhastamise tarbeks koos lisatud funktsioonidega

/**@Author Marko Esna - funktsiooni kasutamise ülesehitus, mis minu poolt ümber tehtud */
        Button sisesta = new Button("Sisesta"); //Loob "Sisesta" nupu teksti sisestamise kinnitamiseks
        sisesta.setOnAction(event -> { //Nupuga kaasnevad funktsioonid
            func.lisaListi( nimi.getText(), parsiSisend(neto.getText()), parsiSisend(bruto.getText())); //Sisestab andmed
            // Double-iks tegemise errori ja 0 sisestamise vajaduse eemaldamiseks kasutab parsiSisend meetodit netol ja brutol
            nimi.clear(); neto.clear(); bruto.clear(); //Tühjendab väljad
        });

        Button arvuta = new Button("Arvuta tulemus"); //Loob "Arvuta tulemus" nupu teksti sisestamise kinnitamiseks
        arvuta.setOnAction(event -> { //Nupuga kaasnevad funktsioonid
            func.fondiArvutused(); //Arvutab ja esitab funktsiooni tulemused
            nimi.clear(); neto.clear(); bruto.clear(); //Tühjendab arvutusmälu
        });

        Button lopeta = new Button("Lõpeta arvutused"); //Loob "Lõpeta arvutused" nupu lõpptulemuste saamiseks
        lopeta.setOnAction(event -> {  //Nupuga kaasnevad funktsioonid
            func.lopp(); //Teen lõpparvutused ja väljastab koondandmed
            nimi.clear(); neto.clear(); bruto.clear(); //Tühjendab arvutusmälu
        });

        vbox.getChildren().addAll(nimiVali, nimi, netoVali, neto, brutoVali, bruto, sisesta, arvuta, lopeta);
        // Valib kõiki nupud ja väljad
        paan.getChildren().add(vbox);
        // Näitab kõiki valitud nuppe ja välju
    }


    /**Neto ja bruto numbrivormingu kontrollimine ja vajadusel asendamine.
     * @Author Kristel Meikas
     * @param sisend String
     * @return parsiSisend Double
     * */

    private Double parsiSisend(String sisend) {
        //Private - saab kasutada ainult sama faili sees, public - saab kasutada ka mujalt
        try { //Try-catch katsetus
            if (!sisend.isEmpty()) { //Kui ei ole tühi, siis ...
                return Double.parseDouble(sisend); //Parsib Double'iks
            }
        } catch (NumberFormatException e) { //Muul juhul püüab vead kinni
            System.out.println("Sisendväärtuse formaat ei sobi, sisend = " + sisend); //Väljastab tekst ja sisendi
            //e.printStackTrace(); //Vigade väljastamise rida, mille eemaldasin, et puhtamana hoida
        }
        return 0.0; //Muul juhul annab 0.0 ehk double vormingus 0
    }


}