package com.example.p3bpianotiles;

public interface FragmentListener {
    void changePage(int page);
    void changeVolume(int vol);
    void changeBackground(int id);
    void setDefault();
    void setLevel(int level);
    void setScore(int score, int level);
    void restart(int)
}
