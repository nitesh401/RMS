package com.rms.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GlobalProperties {
    @Autowired
    private MessageSource messageSource;

    public GlobalProperties() {
//		do nothing

    }

    public String get(String property) {
        return messageSource.getMessage(property, null, LocaleContextHolder.getLocale());
    }
}
