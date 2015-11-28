
/**
 * FournisseurCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v14  Built on : Jul 25, 2015 (11:19:54 IST)
 */

    package fr.services.fournisseur.domain.services;

    /**
     *  FournisseurCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class FournisseurCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public FournisseurCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public FournisseurCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for reserverProduit method
            * override this method for handling normal response from reserverProduit operation
            */
           public void receiveResultreserverProduit(
                    fr.services.fournisseur.domain.services.FournisseurStub.ReserverProduitResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from reserverProduit operation
           */
            public void receiveErrorreserverProduit(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for listerProduits method
            * override this method for handling normal response from listerProduits operation
            */
           public void receiveResultlisterProduits(
                    fr.services.fournisseur.domain.services.FournisseurStub.ListerProduitsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listerProduits operation
           */
            public void receiveErrorlisterProduits(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getReservation method
            * override this method for handling normal response from getReservation operation
            */
           public void receiveResultgetReservation(
                    fr.services.fournisseur.domain.services.FournisseurStub.GetReservationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getReservation operation
           */
            public void receiveErrorgetReservation(java.lang.Exception e) {
            }
                


    }
    