package com.tp.store.util;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleHelper {

    private final MessageSource messageSource;

    public LocaleHelper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }
}
