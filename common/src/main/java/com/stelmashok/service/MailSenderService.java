package com.stelmashok.service;

import com.stelmashok.domain.User;

public interface MailSenderService {
    void sendActivateCode(User user);

}
