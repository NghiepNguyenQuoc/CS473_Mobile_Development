package nghiepnguyen.com.phrasebook.model;

public class Phrase {
    private int id;
    private int idCategory;
    private String phrase;
    private String description1;
    private String description2;
    private String description3;
    private String pronunciation;
    private String description4;
    private String mean;
    private String description5;
    private String sound;
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getDescription4() {
        return description4;
    }

    public void setDescription4(String description4) {
        this.description4 = description4;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getDescription5() {
        return description5;
    }

    public void setDescription5(String description5) {
        this.description5 = description5;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Phrase() {
    }

    public Phrase(int id, int idCategory, String phrase, String description1,
                  String description2, String description3, String pronunciation,
                  String description4, String mean, String description5,
                  String sound, int number) {
        super();
        this.id = id;
        this.idCategory = idCategory;
        this.phrase = phrase;
        this.description1 = description1;
        this.description2 = description2;
        this.description3 = description3;
        this.pronunciation = pronunciation;
        this.description4 = description4;
        this.mean = mean;
        this.description5 = description5;
        this.sound = sound;
        this.number = number;
    }

}
