package com.example.ecogreen.api;

import com.example.ecogreen.User;

public interface APIListener {
    void onLogin(User user);
    void onRegister(User user);
    void onAdditem(User user);
    void onAddshop(User user);
    void onAddidea(User user);
    void onAddcentre(User user);
    void onResetpassword(User user);
    void onBuyitem(User user);
    void onPostfeedback(User user);
    void onGetshop(User user);
    void onPostreply(User user);
    void onPostquiz(User user);

}
