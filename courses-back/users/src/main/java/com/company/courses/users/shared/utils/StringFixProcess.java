package com.company.courses.users.shared.utils;

import org.springframework.stereotype.Service;

@Service
public class StringFixProcess {

    public String fixUserName(String name) {
        this.removeSpaces(name);
        this.capitalizeEachWord(name);
        return name;
    }

    public String fixParagraph(String paragraph) {
        this.removeSpaces(paragraph);
        this.capitalizeFirstLetter(paragraph);
        return paragraph;
    }

    private String removeSpaces(String sentence) {
        sentence = sentence.trim(); //Remove spaces before and after letters
        sentence = sentence.replaceAll("\\s+", " "); //Replace more than one straight spaces for only one
        return sentence;
    }

    private String capitalizeFirstLetter(String sentence) {
        sentence = removeSpaces(sentence);
        if (sentence.isEmpty()) {
            return sentence;
        }
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1).toLowerCase();
    }

    private String capitalizeEachWord(String sentence) {
        sentence = removeSpaces(sentence);
        String[] words = sentence.toLowerCase().split(" ");
        StringBuilder capitalizedPhrase = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedPhrase.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1)).append(" ");
            }
        }

        return capitalizedPhrase.toString().trim();
    }
}
