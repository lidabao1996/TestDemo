package spring_version2.impl;

import spring_version2.service.MessageService;

public class MessageServiceImpl implements MessageService {
    @Override
    public String getMessage() {
        return "hello world";
    }
}
