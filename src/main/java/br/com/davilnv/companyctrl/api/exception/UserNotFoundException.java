package br.com.davilnv.companyctrl.api.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String msgError) {
        super(msgError);
    }

}
