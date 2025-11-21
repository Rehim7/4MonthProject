package com.example._monthproject.myenum;

public enum Levels {
    EXCELLENT("Əla iş! Vərdişlərin 90%-dən çoxunu tamamladınız. Davam edin!"),
    GOOD("Yaxşı nəticədir! Daha çox cəhd etməklə Mükəmmələ çata bilərsiniz."),
    MEDIUM("Orta səviyyə. Potensialınız var, amma əlavə fokus lazımdır."),
    BAD("Zəif nəticə. Vərdişlərinizin yarısından azını tamamlamısınız. Motivasianı artırın!"),
    VERY_BAD("Çox Pis. Tətbiqdən daha çox istifadə etməyə çalışın.");

    private final String displayMessage;

    Levels(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }
}
