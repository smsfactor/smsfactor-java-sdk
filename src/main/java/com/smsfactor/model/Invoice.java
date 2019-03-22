package com.smsfactor.model;

import java.io.IOException;
import java.io.InputStream;

import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.net.ApiResource;
import com.smsfactor.response.InvoiceGetReponse;

public abstract class Invoice extends ApiResource {
    /**
     * Get all your facture information.
     * 
     * @return
     * @throws SMSFactorException
     * @throws IOException
     */
    public static InvoiceGetReponse get() throws SMSFactorException {
        String url = "/account/invoice";
        InvoiceGetReponse response = staticRequest(RequestMethod.GET, url, InvoiceGetReponse.class);

        return response;
    }

    /**
     * Get facture.
     * 
     * @param id
     * @return
     * @throws SMSFactorException
     * @throws IOException
     */
    public static InvoiceGetReponse get(Integer id) throws SMSFactorException {
        String url = "/account/invoice/" + id;
        InvoiceGetReponse response = staticRequest(RequestMethod.GET, url, InvoiceGetReponse.class);

        return response;
    }

    /**
     * Download facture.
     * 
     * @param id
     * @return
     * @throws SMSFactorException
     * @throws IOException
     */
    public static InputStream download(Integer id) throws SMSFactorException {
        String url = "/account/invoice/"+id+"/download";
        InputStream response = staticRequestDownload(RequestMethod.GET, url);

        return response;
    }

}