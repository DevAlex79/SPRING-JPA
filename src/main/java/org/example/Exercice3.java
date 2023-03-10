package org.example;

import org.example.entities.Produit;
import org.example.services.ProduitService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Exercice3 {

    public static void main(String[] args) throws Exception {
        System.out.println("############# Exercice 3 ##############");

        //Création de produits
        ProduitService ps = new ProduitService();
        ps.begin();
        ps.create(new Produit("FENDER","zzza123",new Date("2015/01/08"),6000,12));
        ps.create(new Produit("GIBSON","zEEE163",new Date("2016/02/09"),2000,34));
        ps.create(new Produit("RICKENBECKER","AQYUD",new Date("2015/09/23"),6000,56));
        ps.create(new Produit("MARTIN","AZERTY",new Date("2016/02/12"),6000,72));
        ps.create(new Produit("LAG","qsdert",new Date("2015/02/02"),6000,90));
        ps.envoi();

        System.out.println("Question 1 : ");
        ps.begin();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Merci de saisir la date 1 format (dd/MM/yyyy) : ");
        String s1=scanner.nextLine();
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(s1);

        System.out.println("Merci de saisir la date 2 format (dd/MM/yyyy) : ");
        String s2=scanner.nextLine();
        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(s2);

        List<Produit> produits = null;
        try{
            produits = ps.filterByDate(date1, date2);
            for(Produit pr : produits){
                System.out.println(pr.getId()+" , "+pr.getMarque()+" , "+pr.getReference()+" , "+pr.getDateAchat()+" , "+pr.getPrix()+" , ");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ps.envoi();

        System.out.println("Question 2 : ");
        System.out.println("Merci de saisir la marque : ");
        String marque = scanner.nextLine();

        try {
            Double total = ps.valeurStockParMarque(marque);
            System.out.println("Total pour la marque"+marque+" : "+total+" €");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

