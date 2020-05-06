package com.smsfactor.model;

import java.io.IOException;
import java.io.InputStream;

import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.net.ApiResource;
import com.smsfactor.response.InvoiceGetResponse;

public abstract class Invoice extends ApiResource {
    /**
     * Get all your facture information.
     *
     * @return the invoices
     * @throws SMSFactorException
     * @throws IOException
     */
    public static InvoiceGetResponse get() throws SMSFactorException {
        String url = "/account/invoice";
        InvoiceGetResponse response = staticRequest(RequestMethod.GET, url, InvoiceGetResponse.class);

        return response;
    }

    /**
     * Get facture.
     *
     * @param id the invoice id
     * @return the requested invoice information
     * @throws SMSFactorException
     * @throws IOException
     */
    public static InvoiceGetResponse get(Integer id) throws SMSFactorException {
        String url = "/account/invoice/" + id;
        InvoiceGetResponse response = staticRequest(RequestMethod.GET, url, InvoiceGetResponse.class);

        return response;
    }

    /**
     * Download facture.
     *
     * @param id the invoice id
     * @return the invoice file
     * @throws SMSFactorException
     * @throws IOException
     */
    public static InputStream download(Integer id) throws SMSFactorException {
        String url = "/account/invoice/"+id+"/download";
        InputStream response = staticRequestDownload(RequestMethod.GET, url);

        return response;
    }

}
