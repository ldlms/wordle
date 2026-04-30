package com.wordle.interfaces;

import java.util.List;

public interface ApiService {

    List<String> callApi();

    List<String> callTestApi(String word);
}
