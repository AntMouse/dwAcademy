package java_test;

import java.util.ArrayList;
import java.util.List;

class LibraryItem {
    private String title;
    private String UPC;
    private String subject;

    public LibraryItem(String title, String UPC, String subject) {
        this.title = title;
        this.UPC = UPC;
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String locate() {
        return "Location not specified";
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nUPC: " + UPC + "\nSubject: " + subject;
    }
}

class Catalog {
    private List<LibraryItem> items;

    public Catalog() {
        this.items = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void listItems() {
        for (LibraryItem item : items) {
            System.out.println(item);
            System.out.println("Location: " + item.locate());
            System.out.println("==============================");
        }
    }
}

class Book extends LibraryItem {
    private String ISBN;
    private String DDSNumber;
    private ContributorWithType contributorWithType; // ContributorWithType 객체 추가

    public Book(String title, String UPC, String subject, String ISBN, String DDSNumber, ContributorWithType contributorWithType) {
        super(title, UPC, subject);
        this.ISBN = ISBN;
        this.DDSNumber = DDSNumber;
        this.contributorWithType = contributorWithType; // ContributorWithType 객체 초기화
    }

    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDDSNumber() {
        return DDSNumber;
    }
    
    public void setDDSNumber(String DDSNumber) {
        this.DDSNumber = DDSNumber;
    }

    public ContributorWithType getContributorWithType() { // 추가: ContributorWithType 객체 반환
        return contributorWithType;
    }
    
    public void setContributorWithType(ContributorWithType contributorWithType) { // 추가: ContributorWithType 객체 설정
        this.contributorWithType = contributorWithType;
    }

    @Override
    public String locate() {
        return "DDS Number: " + DDSNumber + ", " + contributorWithType; // ContributorWithType 정보 반환 추가
    }

    @Override
    public String toString() {
        return super.toString() + "\nISBN: " + ISBN + "\nDDS Number: " + DDSNumber + "\n" + contributorWithType; // ContributorWithType 정보 출력 추가
    }
}

class CD extends LibraryItem {
    private ContributorWithType artist; // 아티스트 정보를 ContributorWithType 객체로 변경

    public CD(String title, String UPC, String subject, ContributorWithType artist) {
        super(title, UPC, subject);
        this.artist = artist; // 아티스트 정보 초기화
    }

    public ContributorWithType getArtist() {
        return artist;
    }

    public void setArtist(ContributorWithType artist) {
        this.artist = artist;
    }

    @Override
    public String locate() {
        return "Artist: " + artist;
    }

    @Override
    public String toString() {
        return super.toString() + "\nArtist: " + artist;
    }
}

class DVD extends LibraryItem {	
    // Genre enum 선언
    public enum Genre {
        ROMANCE, HORROR, SF, ACTION, DOCUMENTARY
    }
    
    private Genre genre; // Genre enum을 멤버 변수로 사용
    private List<ContributorWithType> contributors; // ContributorWithType 객체를 리스트로 관리

    public DVD(String title, String UPC, String subject, Genre genre, List<ContributorWithType> contributors) {
        super(title, UPC, subject);
        this.genre = genre;
        this.contributors = contributors; // ContributorWithType 객체 초기화
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<ContributorWithType> getContributors() {
        return contributors;
    }

    public void setContributors(List<ContributorWithType> contributors) {
        this.contributors = contributors;
    }

    @Override
    public String locate() {
        StringBuilder builder = new StringBuilder();
        builder.append("Genre: ").append(genre).append("\n");
        for (ContributorWithType contributorWithType : contributors) {
            builder.append(contributorWithType).append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("\nGenre: ").append(genre).append("\n");
        for (ContributorWithType contributorWithType : contributors) {
            builder.append(contributorWithType).append("\n");
        }
        return builder.toString();
    }
}

class Magazine extends LibraryItem {
    private int volume;
    private int issue;
    private List<ContributorWithType> contributors; // ContributorWithType 객체를 리스트로 관리

    public Magazine(String title, String UPC, String subject, int volume, int issue, List<ContributorWithType> contributors) {
        super(title, UPC, subject);
        this.volume = volume;
        this.issue = issue;
        this.contributors = contributors; // ContributorWithType 객체 초기화
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public List<ContributorWithType> getContributors() {
        return contributors;
    }

    public void setContributors(List<ContributorWithType> contributors) {
        this.contributors = contributors;
    }

    @Override
    public String locate() {
        StringBuilder builder = new StringBuilder();
        builder.append("Volume: ").append(volume).append(", Issue: ").append(issue).append("\n");
        for (ContributorWithType contributorWithType : contributors) {
            builder.append(contributorWithType).append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("\nVolume: ").append(volume).append("\nIssue: ").append(issue).append("\n");
        for (ContributorWithType contributorWithType : contributors) {
            builder.append(contributorWithType).append("\n");
        }
        return builder.toString();
    }
}

enum Contbtr {
    AUTHOR, ACTOR, DIRECTOR, ARTIST
}

// Contributor 클래스를 enum 타입인 Contbtr로 변경
class Contributor {
    private Contbtr contbtr;

    public Contributor(Contbtr contbtr) {
        this.contbtr = contbtr;
    }

    public Contbtr getContbtr() {
        return contbtr;
    }

    public void setContbtr(Contbtr contbtr) {
        this.contbtr = contbtr;
    }

    @Override
    public String toString() {
        return "참여자: " + contbtr;
    }
}

class ContributorWithType {
    private Contributor contributor;
    private String contType;

    public ContributorWithType(Contributor contributor, String contType) {
        this.contributor = contributor;
        this.contType = contType;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public String getContType() {
        return contType;
    }

    public void setContType(String contType) {
        this.contType = contType;
    }

    @Override
    public String toString() {
        return "참여자: " + contType + "(" + contributor.getContbtr() + ")";
    }
}

public class LibraryCatalogSystem {
    public static void main(String[] args) {
    }
}
