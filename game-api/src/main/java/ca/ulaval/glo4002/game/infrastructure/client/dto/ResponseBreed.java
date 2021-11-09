package ca.ulaval.glo4002.game.infrastructure.client.dto;

public class ResponseBreed {
    private String offspring;
    private String gender;

    public ResponseBreed(String offspring, String gender) {
        this.offspring = offspring;
        this.gender = gender;
    }

    public ResponseBreed() {}

    public String getOffspring() {
        return offspring;
    }

    public String getGender() {
        return gender;
    }
}
