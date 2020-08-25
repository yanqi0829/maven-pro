package com.mycom.mvnbook.email;

import com.mycom.mvnbook.email.exception.AccountEmailException;

public interface AccountEmailService {
    void sendEmail(String to, String subject, String htmlText) throws AccountEmailException;
}
