package sample;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Funktsioonid extends Main {


    //Muutujad: stringid ja double-id (et ka komadega arvestaks)

    String nimi;
    Double neto = 0.0; //Algväärtus 0. Kui pole teada, siis saab sisestada 0 või tühjaks jätta. Muul juhul number.
    Double bruto = 0.0; //Algväärtus 0. Kui pole teada, siis saab sisestada 0 või tühjaks jätta. Muul juhul number.
    Double fondid = 0.0; //Igaühel eraldi palgafond
    Double koguFond = 0.0; //Summaarne palgafond ettevõtjale

    //Listid muutujate jaoks

/**@Author Marko Esna - näidis*/
    ArrayList<String> nimed = new ArrayList<>();
    ArrayList<Double> netod = new ArrayList<>();
    ArrayList<Double> brutod = new ArrayList<>();
    ArrayList<Double> palgafondid = new ArrayList<>();


    /**
     * Väärtuste listi lisamine.
     * @param nimi String
     * @param neto Double
     * @param bruto Double
     */

    public void lisaListi(String nimi, Double neto, Double bruto){

/**@Author Marko Esna - näidis*/
        nimed.add(nimi); //Lisab uue inimese nime listi järgmisele kohale
        netod.add(neto); //Lisab uue inimese netopalga listi järgmisele kohale
        brutod.add(bruto); //Lisab uue inimese brutopalga listi järgmisele kohale
        palgafondid.add(fondid); //Lisab uue inimese palgafondi listi järgmisele kohale
    }


    //Muudab nimed Camel Case'ile vastavaks (esimene täht igas sõnas suurtäht), nagu eesti keele reeglitele kohane.

    /** @Author Java With Us
    * http://www.javawithus.com/programs/converting-string-to-different-cases-upper-lower-toggle-camel-sentence-case
    * @param inputString String
    * @return toCamelCase String
    **/

    public static String toCamelCase(String inputString) {
        String result = ""; //Loob muutuja
        if (inputString.length() == 0) { //Kui sisendi pikkus on 0, siis jääb nii, nagu on
            return result; //Väljastab tulemuse juhul, kui sisend on tühi
        }
        char firstChar = inputString.charAt(0); //Loob muutuja, mis võtab sisendi esimese märgi
        char firstCharToUpperCase = Character.toUpperCase(firstChar); //Muudab esimese tähe suurtäheks
        result = result + firstCharToUpperCase; //Lisab esitähe tulemusele
        for (int i = 1; i < inputString.length(); i++) { //For tsükkel märkide 1 kuni i läbikäimiseks
            char currentChar = inputString.charAt(i);  // Määrab ära, milline märk on hetkel käsil
            char previousChar = inputString.charAt(i - 1); //Määrab ära eelmise märgi
            if (previousChar == ' ') {  //If lause, "kui eelmine märk on tühik", siis ...
                char currentCharToUpperCase = Character.toUpperCase(currentChar); //Praeguse märgi muudab suureks täheks
                result = result + currentCharToUpperCase; //Tulemusele liidab suure märgi
            } else {
                char currentCharToLowerCase = Character.toLowerCase(currentChar); //Muul juhul muudab väikeseks märgiks
                result = result + currentCharToLowerCase; //Liidetakse tähe tulemusele
            }
        }
        return result; //Väljastab tulemuse
    }


    /**
     * Netode, brutode ja palgafondide leidmine ning info väljaprintimine.
     */

    public void fondiArvutused(){
        DecimalFormat f = new DecimalFormat("##.00"); //Muudab 0,xxxxxxx... numbrid 0,xx-ks.


         for (int i = 0; i < nimed.size(); i++) {
             //For tsükli abil teeb kõik funktsiooni astmed läbi kõigi massiivi liikmetega lähtudes nimede massiivi liikmetest



             String susi=nimed.get(i).replaceAll("[0-9\\+*/_<>=?!#%:\\.;,\\)\\(\\´\\{\\}\"¤&`@£$€§½]+", "");
             //Eemaldab veidrad märgid nimedest, kui midagi on sinna sattunud
             //Märkide []\| eemaldamisega on probleeme

             nimed.set(i, toCamelCase(susi)); //Muudab nimed Camel Case tekstiks toCamelCase meetodi abil

             if (brutod.get(i) > 0 && netod.get(i)==0) { //Kui netot ei tea, aga bruto on olemas, siis ...
                fondid = brutod.get(i)*(1.384); //Arvutab brutost palgafondi
                neto = brutod.get(i)/1.2; //Arvutab brutost neto
                //33%=sotsmaksud, 1,8% töötuskindlustusmaks ettevõttele, 1,6% töötuskindlustusmaks töötajale, 2% 2. pensionisammas
                //33+1,8+1,6+2=38,4% -> 1,384
                netod.set(i, neto); //Viib uue neto õigele kohale netode massiivi
                palgafondid.set(i, fondid); //Viib saadud palgafondi õigesse kohta palgafondi massiivi
                System.out.println( nimed.get(i) + " neto: " + f.format(netod.get(i)) + " bruto: " + f.format(brutod.get(i))+ ", palgafond: " + f.format(palgafondid.get(i)));
                //Trükib kõik andmed välja ühes reas üksteise järel täpsusega kaks kohta pärast koma.
            }
            else if (brutod.get(i) == 0  && netod.get(i) > 0){ //Kui brutot ei tea, aga neto on olemas, siis ...
                fondid = netod.get(i)*1.2*(1.384); //Arvutab netost palgafondi
                //33%=sotsmaksud, 1,8% töötuskindlustusmaks ettevõttele, 1,6% töötuskindlustusmaks töötajale, 2% 2. pensionisammas, 20% tulumaksu
                bruto = netod.get(i)*1.2; //Arvutab netost bruto
                brutod.set(i, bruto); //Viib uue bruto õigele kohale brutode massiivi
                palgafondid.set(i, fondid); //Viib saadud palgafondi õigesse kohta palgafondi massiivi
                System.out.println( nimed.get(i) + " neto: " + f.format(netod.get(i)) + " bruto: " + f.format(brutod.get(i))+ ", palgafond: " + f.format(palgafondid.get(i)));
                 //Trükib kõik andmed välja ühes reas üksteise järel täpsusega kaks kohta pärast koma.
             }
            else {System.out.println("Vigane rida: neto = " + netod.get(i) + ", bruto = "+ brutod.get(i) + ".");}
            //Kui ei teata ei netot ega brutot või on sisestatud arv väiksem nullist, siis trükib selle rea täpsusega kaks kohta pärast koma

            if (brutod.get(i) >=0 && (netod.get(i)) >=0 ){ //Kui vaadeldavad brutod ja netod on nullist suuremad, siis ...
                System.out.println("Palgafond kasutajal " + nimed.get(i)+  " on " + f.format(palgafondid.get(i)) + " eurot.");
                //Trükib sellise teksti täpsusega kaks kohta pärast koma
                koguFond = koguFond + palgafondid.get(i); //Liidab saadud palgafondi kogufondile juurde
                System.out.println("Aastane palgafond kasutajal " + nimed.get(i)+  " on " + f.format(palgafondid.get(i)*12) + " eurot.");
                //Leiab aastafondi ja trükib välja
            }
            else continue; //Muul juhul jätab rea vahele ja liigub järgmise juurde

        }
    }

    /**
     * Numbrite ümardamine ja tekstide välja trükkimine.
     */

    public void lopp() {
        DecimalFormat f = new DecimalFormat("##0.00"); //Muudab 0,xxxxxxx... kujul numbrid 0,xx-ks.
        System.out.println("Kogu palgafond kuus on " + f.format(koguFond) + " eurot.");
        //Trükib ettevõtte kogu ühe kuu palgafondi
        System.out.println("[Nimi, neto, bruto, palgafond]"); //Trükib tabeli esimese rea
        for (int i = 0; i < nimed.size(); i++){ //For tsükli abil trükib ekraanile andmed nimede massiivi järjekorra alusel
            System.out.println("[" + nimed.get(i) + "; " + f.format(netod.get(i)) + "; " + f.format(brutod.get(i)) + "; "+ f.format(palgafondid.get(i)) + " ]");
            //Trükib igast massiivist sama järjekorranumbriga andmed üksteise all tabelisse
        }
    }


}
