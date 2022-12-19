package org.example;

import org.example.entities.Produit;

import org.example.services.ProduitService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("#############   Exercice 1    #############");

        //Création de produits
        ProduitService ps = new ProduitService();
        ps.begin();
        ps.create(new Produit("FENDER","zzza123",new Date("2015/01/08"),6000,12));
        ps.create(new Produit("GIBSON","zEEE163",new Date("2016/02/09"),2000,34));
        ps.create(new Produit("RICKENBECKER","AQYUD",new Date("2015/09/23"),6000,56));
        ps.create(new Produit("MARTIN","AZERTY",new Date("2016/02/12"),6000,72));
        ps.create(new Produit("LAG","qsdert",new Date("2015/02/02"),6000,90));
        ps.envoi();

        // Information produit id=2
        ps.begin();
        Produit p = ps.findById(2);
        System.out.println(p.getId()+" , "+p.getMarque()+" , "+p.getReference()+" , "+p.getDateAchat()+" , "+p.getPrix());
        ps.envoi();

        //Suppression produit id=3
        ps.begin();
        ps.delete(ps.findById(3));
        ps.envoi();

        //Modification des informations du produit  dont id =1
        ps.begin();
        p = ps.findById(1);
        if(p != null){
            p.setMarque("FENDER");
            p.setReference("MMMMMPPPP");
            p.setDateAchat(new Date("2015/09/08"));
            p.setPrix(5000);
            ps.update(p);
        }
        ps.envoi();

        // Exercice Pile

        Pile<String> produitPile = new Pile<String>(String[].class, 1);

        Pile<Produit> produitPile2 = new Pile<Produit>(Produit[].class, 1);

        System.out.println("#############   Exercice 2    #############");
        System.out.println("Tous les produits");
        ps.begin();
        for(Produit produit : ps.findAll()){
            System.out.println(produit.getId()+" , "+produit.getMarque()+" , "+produit.getReference()+" , "+produit.getDateAchat()+" , "+produit.getPrix());
        }
        ps.envoi();

        System.out.println("Tous les produits au-dessus de 2100 euros : ");
        ps.begin();
        for (Produit produitpascher : ps.filterByPrice(2100)) {
            System.out.println(produitpascher.getId()+" , "+produitpascher.getMarque()+" , "+produitpascher.getReference()+" , "+produitpascher.getDateAchat()+" , "+produitpascher.getPrix());
        }

        ps.envoi();
        System.out.println("Tous les produits achetés entre le 01/01/2016 et le 30/12/2016");
        ps.begin();;
        String madate1 ="01/01/2016";
        Date date1= new SimpleDateFormat("dd/MM/yyyy").parse(madate1);
        // Date date1 = new Date("2016/01/01");

        String madate2 = "31/12/2016";
        Date date2= new SimpleDateFormat("dd/MM/yyyy").parse(madate2);
        // Date date2=new Date("2016/30/12");

        List<Produit> produitsDate = ps.filterByDate(date1, date2);
        for(Produit pr : produitsDate) {
            System.out.println(pr.getId() + " , " + pr.getMarque()+" , "+pr.getDateAchat()+" , "+ pr.getPrix());
        }

        ps.envoi();


        ps.close();


    }
}
