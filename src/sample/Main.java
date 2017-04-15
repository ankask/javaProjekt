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

//Valdav osa muude märgeteta koodist sellel lehel saadud lehelt http://i200.itcollege.ee/#Kasutajaliides ja modifitseeritud minu poolt projektile sobivaks


public class Main extends Application {

/*Rida saadud Hardi Tiituselt ja Marko Esna tööst **/
    @Override

/*Rida saadud Marko Esna tööst **/
    public void start(Stage primaryStage) throws Exception{

        Funktsioonid func = new Funktsioonid(); //funktsioonide listi loomine
        Pane paan = new Pane();   //Loon uue paani
        Scene stseen = new Scene(paan, 375, 425); //akna kõrgus ja laius
        primaryStage.setScene(stseen);
        primaryStage.show();
        primaryStage.setTitle("Palgaarvestus"); //Akna pealkiri
//Abi saadud:  lehelt http://i200.itcollege.ee/#Kasutajaliides ja Külli Ristilt

        VBox vbox = new VBox(2); //2-pikslise vahega kastid üksteise alla
        vbox.setAlignment(Pos.BOTTOM_CENTER); //ei tea täpselt, mida see teeb, sest ei näe seda veel
        vbox.setTranslateY(100); //nihutas kastid ja teksti ülemisest äärest eemale
        vbox.setTranslateX(100); //nihutas kastid ja teksti vasakust äärest eemale


        //Lahtrid teksti ja numbrite sisestuseks
/*Esimene rida saadud Külli Ristilt **/
        Label nimiVali = new Label("Sisesta töötaja nimi:"); //rida enne sisestuskastikest
        TextField nimi = new TextField();      //küsib tekstilisi andmeid
        nimi.setPromptText("sisesta nimi");   //see on salajane tekst



        Label netoVali = new Label("Sisesta töötaja netopalk:"); //rida enne sisestuskastikest
        TextField neto = new TextField();          //küsib tekstilisi andmeid
        neto.setPromptText("sisesta netopalk");  //see on salajane tekst

        Label brutoVali = new Label("Sisesta töötaja brutopalk:"); //rida enne sisestuskastikest
        TextField bruto = new TextField();      //küsib tekstilisi andmeid
        bruto.setPromptText("sisesta brutopalk");   //see on salajane tekst

        //nupud sisestuse, arvutamise ja puhastamise tarbeks
/*Funktsiooni kasutamise ülesehitus saadud Marko Esna tööst ja ümber tehtud minu poolt **/
        Button sisesta = new Button("Sisesta"); //1. nupp, sisestamiseks
        sisesta.setOnAction(event -> {
            func.lisaListi( nimi.getText(), parsiSisend(neto.getText()), parsiSisend(bruto.getText())); //andmete sisestus
            // Double-iks tegemise errori ja 0 sisestamise vajaduse eemaldamiseks kasutan ka parsiSisend meetodid netol ja brutol
            nimi.clear(); neto.clear(); bruto.clear(); //välja tühjendamine
        });

        Button arvuta = new Button("Arvuta tulemus"); //2. nupp töötlemiseks
        arvuta.setOnAction(event -> {
            func.fondiArvutused(); // funktsiooni tulemuse arvutamine ja esitamine
            nimi.clear(); neto.clear(); bruto.clear(); // arvutusmälu tühjendamine
        });

        Button lopeta = new Button("Lõpeta arvutused"); //3. nupp väärtuste tühjendamiseks mälust ja lõpptulemuse esitamine
        lopeta.setOnAction(event -> {func.lopp();
            nimi.clear(); neto.clear(); bruto.clear(); //mälu tühjendamine
        });

        // kõigi valitud nuppude ja väljade näitamine
        vbox.getChildren().addAll(nimiVali, nimi, netoVali, neto, brutoVali, bruto, sisesta, arvuta, lopeta);
        // kõigi valitud nuppude ja väljade näitamine
        paan.getChildren().add(vbox);
    }
/*@Author Kristel Meikas**/
    private Double parsiSisend(String sisend) { //funktsioon/meetod, et katsetada, kas on puhas number või ei
        //private - saab kasutada ainult sama faili sees, public - saab kasutada ka mujalt
        try { //try-catch katsetus
            if (!sisend.isEmpty()) { //kui ei ole tühi
                return Double.parseDouble(sisend); //siis võib parsida Double'iks
            }
        } catch (NumberFormatException e) { //muul juhul annaks errorid, kuid eemaldasime ülearuse rea
            System.out.println("Sisendväärtuse formaat ei sobi, sisend = " + sisend); //errori asemel tekst ja väljund
            //e.printStackTrace(); //errorite väljastamise rida, mille eemaldasime
        }
        return 0.0; //muul juhul annab 0.0 ehk double vormingus 0
    }
}