package sample;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Funktsioonid extends Main {


    //muutujad stringid ja double-id (et ka komadega arvestaks)
    String nimi;

    Double neto = 0.0; //Algväärtus 0. Kui pole teada, siis saab sisestada 0 või tühjaks jätta. Muul juhul number.
    Double bruto = 0.0; //Algväärtus 0. Kui pole teada, siis saab sisestada 0 või tühjaks jätta. Muul juhul number.
    Double fondid = 0.0; //igaühel eraldi palgafond
    Double koguFond = 0.0; //summaarne palgafond ettevõtjale

    //listid muutujate jaoks
/*Näidis saadud Marko Esna tööst**/
    ArrayList<String> nimed = new ArrayList<>();
    ArrayList<Double> netod = new ArrayList<>();
    ArrayList<Double> brutod = new ArrayList<>();
    ArrayList<Double> palgafondid = new ArrayList<>();


    //Funktsioon väärtuste listi lisamiseks
    public void lisaListi(String nimi, Double neto, Double bruto){


/*Näidis saadud Marko Esna tööst**/
        nimed.add(nimi); //uue inimese nime listi järgmisele kohale lisamine
        netod.add(neto); //uue inimese netopalga listi järgmisele kohale lisamine
        brutod.add(bruto); //uue inimese brutopalga listi järgmisele kohale lisamine
        palgafondid.add(fondid); //uue inimese palgafondi listi järgmisele kohale lisamine
    }


    //Muudab nimed Camel Case'ile vastavaks (esimene täht igas sõnas suurtäht), nagu eesti keele reeglitele kohane.
/*@Author http://www.javawithus.com/programs/converting-string-to-different-cases-upper-lower-toggle-camel-sentence-case **/
    public static String toCamelCase(String inputString) {
        String result = ""; //loon muutuja
        if (inputString.length() == 0) { //kui sisendi pikkus on 0, siis jääb nii, nagu on
            return result; //väljastatakse tulemus juhul, kui sisend on tühi
        }
        char firstChar = inputString.charAt(0); //loon muutuja, mis võtab sisendi esimese märgi
        char firstCharToUpperCase = Character.toUpperCase(firstChar);
        result = result + firstCharToUpperCase;
        for (int i = 1; i < inputString.length(); i++) { //for tsükkel märkide 1 kuni i läbikäimiseks
            char currentChar = inputString.charAt(i);  // määrab ära, milline märk on hetkel käsil
            char previousChar = inputString.charAt(i - 1); //määrab ära eelmise märgi
            if (previousChar == ' ') {  //if lause, "kui eelmine märk on tühik"
                char currentCharToUpperCase = Character.toUpperCase(currentChar); //siis praegune muudetakse suureks täheks
                result = result + currentCharToUpperCase; //tulemusele liidetakse suur märk
            } else {
                char currentCharToLowerCase = Character.toLowerCase(currentChar); //muul juhul muudetakse väikeseks märgiks
                result = result + currentCharToLowerCase; //ja liidetakse tulemusele
            }
        }
        return result; //väljastatakse tulemus
    }


    //Funktsioon netode, brutode ja palgafondide leidmiseks ning info väljaprintimiseks.
    public void fondiArvutused(){
        DecimalFormat f = new DecimalFormat("##.00"); //muudab 0,xxxxxxx... numbrid 0,xx-ks.

        //for tsükli abil teeg kõik funktsiooni astmed läbi kõigi massiivi liikmetega lähtudes nimede massiivi liikmetest
         for (int i = 0; i < nimed.size(); i++) {

             //eemaldan veidrad märgid nimedest, kui midagi on sinna sattunud
             String susi=nimed.get(i).replaceAll("[0-9\\+*/_<>=?!#%:\\.;,\\)\\(\\´\\{\\}\"¤&`@£$€§½]+", "");
             //nende märkite eemaldamisega on probleeme: []\|

             nimed.set(i, toCamelCase(susi)); //muudan nimed Camel Case tekstiks

             if (brutod.get(i) > 0 && netod.get(i)==0) { //kui netot ei tea, aga bruto on olemas
                fondid = brutod.get(i)*(1.384); //arvutab brutost palgafondi
                neto = brutod.get(i)/1.2; //arvutab brutost neto
                //33%=sotsmaksud, 1,8% töötuskindlustusmaks ettevõttele, 1,6% töötuskindlustusmaks töötajale, 2% 2. pensionisammas
                //33+1,8+1,6+2=38,4%
                netod.set(i, neto); //viib uue neto õigele kohale netode massiivi
                palgafondid.set(i, fondid); //viib saadud palgafondi õigesse kohta palgafondi massiivi
                System.out.println( nimed.get(i) + " neto: " + f.format(netod.get(i)) + " bruto: " + f.format(brutod.get(i))+ ", palgafond: " + f.format(palgafondid.get(i)));
            }
            else if (brutod.get(i) == 0  && netod.get(i) > 0){ //kui brutot ei tea, aga neto on olemas
                fondid = netod.get(i)*1.2*(1.384); //arvutab netost palgafondi
                //33%=sotsmaksud, 1,8% töötuskindlustusmaks ettevõttele, 1,6% töötuskindlustusmaks töötajale, 2% 2. pensionisammas, 20% tulumaksu
                bruto = netod.get(i)*1.2; //arvutab netost bruto
                brutod.set(i, bruto); //viib uue bruto õigele kohale brutode massiivi
                palgafondid.set(i, fondid); //viib saadud palgafondi õigesse kohta palgafondi massiivi
                System.out.println( nimed.get(i) + " neto: " + f.format(netod.get(i)) + " bruto: " + f.format(brutod.get(i))+ ", palgafond: " + f.format(palgafondid.get(i)));
            }
            else {System.out.println("Vigane rida: neto = " + netod.get(i) + ", bruto = "+ brutod.get(i) + ".");}
            //kui ei teata ei netot ega brutot või on sisestatud arv väiksem nullist

            if (brutod.get(i) >=0 && (netod.get(i)) >=0 ){ //kui vaadeldavad brutod ja netod on nullist suuremad, siis
                System.out.println("Palgafond kasutajal " + nimed.get(i)+  " on " + f.format(palgafondid.get(i)) + " eurot."); //trükitakse selline teks
                koguFond = koguFond + palgafondid.get(i);//ja liidetakse palgafond kogufondile juurde
                System.out.println("Aasta palgafond kasutajal " + nimed.get(i)+  " on " + f.format(palgafondid.get(i)*12) + " eurot."); //leitakse aastafond ja trükitakse välja
            }
            else continue; //muidu jäetakse rida vahele

        }
    }


    public void lopp() {
        DecimalFormat f = new DecimalFormat("##0.00"); //muudab 0,xxxxxxx... numbrid 0,xx-ks.
        System.out.println("Kogu palgafond kuus on " + f.format(koguFond) + " eurot.");
        System.out.println("[Nimi, neto, bruto, palgafond]");
        for (int i = 0; i < nimed.size(); i++){ //for tsükli abil trükib ekraanile andmed maatriksi kujul
            System.out.println("[" + nimed.get(i) + "; " + f.format(netod.get(i)) + "; " + f.format(brutod.get(i)) + "; "+ f.format(palgafondid.get(i)) + " ]");
        }
    }


}
