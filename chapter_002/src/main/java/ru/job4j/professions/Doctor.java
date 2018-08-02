package ru.job4j.professions;

/**
 * Created by a.makarov on 02.08.2018.
 */
public class Doctor extends Profession {
    public Diagnose cure(Pacient pacient) {
        return new Diagnose();
    }
}
