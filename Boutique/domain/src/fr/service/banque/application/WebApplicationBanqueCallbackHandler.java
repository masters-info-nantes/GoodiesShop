
/**
 * WebApplicationBanqueCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v14  Built on : Jul 25, 2015 (11:19:54 IST)
 */

    package fr.service.banque.application;

    /**
     *  WebApplicationBanqueCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class WebApplicationBanqueCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public WebApplicationBanqueCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public WebApplicationBanqueCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for payer method
            * override this method for handling normal response from payer operation
            */
           public void receiveResultpayer(
                    fr.service.banque.application.WebApplicationBanqueStub.PayerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from payer operation
           */
            public void receiveErrorpayer(java.lang.Exception e) {
            }
                


    }
    