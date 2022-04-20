package br.com.dh.ctd.ecommerce.service.exceptions;

public class BDExcecao extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BDExcecao(String msg) {
        super(msg);
    }

}
