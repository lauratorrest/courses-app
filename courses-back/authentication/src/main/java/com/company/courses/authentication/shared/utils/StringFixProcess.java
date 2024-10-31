package com.company.courses.authentication.shared.utils;

import org.springframework.stereotype.Service;

@Service
public class StringFixProcess {

    public String removeSpaces(String sentence) {
        sentence = sentence.trim(); //Remove spaces before and after letters
        sentence = sentence.replaceAll(" ", ""); //Replace more than one straight spaces for only one
        return sentence;
    }
}
