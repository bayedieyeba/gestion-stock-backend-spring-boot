package com.baye.gestiondestock.utils;

public interface Constants {

    String APP_ROOT = "gestiondestock/v1";

    String CMMANDE_FOURNISSEUR_ENDPOINT = APP_ROOT+ "/commandesfournisseurs";
    String CREATE_COMMANDE_FOURNISSEUR_ENDPOINT = CMMANDE_FOURNISSEUR_ENDPOINT+ "/create";
    String FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT = CMMANDE_FOURNISSEUR_ENDPOINT+ "/{idCommandeFourniseur}";
    String FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT = CMMANDE_FOURNISSEUR_ENDPOINT+ "/{codeCommandeFourniseur}";
    String FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT = CMMANDE_FOURNISSEUR_ENDPOINT+ "/all";
    String DELETE_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT = CMMANDE_FOURNISSEUR_ENDPOINT+ "/delete/{idCommandeFourniseur}";


    String  ENTREPRISE_ENDPOINT = APP_ROOT+"/entreprises";

    String FOURNISSEUR_ENDPOINT = APP_ROOT +"/fournisseurs";

    String UTILISATEUR_ENDPOINT = APP_ROOT+"/utilisateurs";

    String VENTES_ENDPOINT = APP_ROOT+"/ventes";
}
