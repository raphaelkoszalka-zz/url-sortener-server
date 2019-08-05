package com.koszalka.shortener.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerShuffle {
    

    public UrlShortenerShuffle() {}

    public String shuffle(String hash) {
        List<Character> characters = new ArrayList<Character>();
        for(char c:hash.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(hash.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}