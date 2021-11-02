package ca.ulaval.glo4002.game.controllers.baby.dtos;

public class ExternalApiBabyListDto {
    private String offspring;
    private String gender;

    public ExternalApiBabyListDto(String offspring, String gender) {
        this.offspring = offspring;
        this.gender = gender;
    }

    public String getOffspring() {
        return offspring;
    }

    public String getGender() {
        return gender;
    }
}
