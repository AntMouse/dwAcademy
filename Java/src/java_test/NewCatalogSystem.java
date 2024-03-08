package java_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewCatalogSystem {
	 Scanner scanner = new Scanner(System.in);
	 LibraryItem[] items = new LibraryItem[8];
	 Catalog catalog = new Catalog();
	 
	 public NewCatalogSystem() {
		 // 1. 주제: "모험", 제목: "Moby Dick", 저자: "Herman Melville", UPC: "111-111111", ISBN: "1234-1234-1234", DDS: "100"
		 Contributor author1 = new Contributor(Contbtr.AUTHOR);
		 ContributorWithType authorWithType1 = new ContributorWithType(author1, "Herman Melville");
		 items[0] = new Book("Moby Dick", "111-111111", "모험", "1234-1234-1234", "100", authorWithType1);
		 
		 // 2. 주제: "교훈", 제목: "이솝 우화", 아티스트(성우): "이진우", UPC: "111-111-222"
		 Contributor artist1 = new Contributor(Contbtr.ARTIST);
		 ContributorWithType artistWithType1 = new ContributorWithType(artist1, "이진우");
		 items[1] = new CD("이솝 우화", "111-111-222", "교훈", artistWithType1);
		 
		 // 3. 주제: "역경 극복", 제목: "Gone with the wind", 배우: "Vivian Leigh", "Clark Gable", 감독: "Victor Flemming", 장르: "Romance", UPC: "111-111-333"
		 Contributor actor1 = new Contributor(Contbtr.ACTOR);
		 ContributorWithType actorWithType1 = new ContributorWithType(actor1, "Vivian Leigh");
		 Contributor actor2 = new Contributor(Contbtr.ACTOR);
		 ContributorWithType actorWithType2 = new ContributorWithType(actor2, "Clark Gable");
		 Contributor director1 = new Contributor(Contbtr.DIRECTOR);
		 ContributorWithType directorWithType1 = new ContributorWithType(director1, "Victor Flemming");
		 List<ContributorWithType> contributors2 = new ArrayList<>();
		 contributors2.add(actorWithType1);
		 contributors2.add(actorWithType2);
		 contributors2.add(directorWithType1);
		 DVD.Genre genre2 = DVD.Genre.ROMANCE;
		 items[2] = new DVD("Gone with the wind", "111-111-333", "역경 극복", genre2, contributors2);
		 
		 // 4. 주제: "신소재", 제목: "New materials", UPC: "111-111-444", 편집자: "Mark Bodlwin", "Peter Fenn", 저자: "이수웅", 볼륨: 2022, 이슈: 10
		 Contributor editor1 = new Contributor(Contbtr.DIRECTOR); // 편집자는 DIRECTOR에 해당합니다.
		 ContributorWithType editorWithType1 = new ContributorWithType(editor1, "Mark Bodlwin");
		 Contributor editor2 = new Contributor(Contbtr.DIRECTOR);
		 ContributorWithType editorWithType2 = new ContributorWithType(editor2, "Peter Fenn");

		 List<ContributorWithType> contributors3 = new ArrayList<>();
		 contributors3.add(editorWithType1);
		 contributors3.add(editorWithType2);

		 Contributor author3 = new Contributor(Contbtr.AUTHOR); // 저자는 AUTHOR에 해당합니다.
		 ContributorWithType authorWithType3 = new ContributorWithType(author3, "이수웅");
		 contributors3.add(authorWithType3);

		 items[3] = new Magazine("New materials", "111-111-444", "신소재", 2022, 10, contributors3);
		 
		 // 5. 주제: "역사", 제목: "도전과 응전", 저자: "Arnold Toynbee", UPC: "111-111555", ISBN: "1234-1234-1235", DDS: "100"
		 Contributor author4 = new Contributor(Contbtr.AUTHOR);
		 ContributorWithType authorWithType4 = new ContributorWithType(author4, "Arnold Toynbee");
		 items[4] = new Book("도전과 응전", "111-111555", "역사", "1234-1234-1235", "100", authorWithType4);

		 // 6. 주제: "모험", 제목: "오디세이", 아티스트(성우): "양정모", UPC: "111-111-666"
		 Contributor artist5 = new Contributor(Contbtr.ARTIST);
		 ContributorWithType artistWithType5 = new ContributorWithType(artist5, "양정모");
		 items[5] = new CD("오디세이", "111-111-666", "모험", artistWithType5);

		 // 7. 주제: "정의", 제목: "Taxi Driver", 배우: "Robert Deniro", 감독: "Martin Scorsage", 장르: "Action", UPC: "111-111-777"
		 Contributor actor6 = new Contributor(Contbtr.ACTOR);
		 ContributorWithType actorWithType6 = new ContributorWithType(actor6, "Robert Deniro");
		 Contributor director6 = new Contributor(Contbtr.DIRECTOR);
		 ContributorWithType directorWithType6 = new ContributorWithType(director6, "Martin Scorsage");
		 List<ContributorWithType> contributors6 = new ArrayList<>();
		 contributors6.add(actorWithType6);
		 contributors6.add(directorWithType6);
		 DVD.Genre genre7 = DVD.Genre.ACTION;
		 items[6] = new DVD("Taxi Driver", "111-111-777", "정의", genre7, contributors6);

		 // 8. 주제: "유전자", 제목: "Genetic Engineering", UPC: "111-111-888", 편집자: "Alex Patrick", "Collmaan Fitztet", 저자: "Roy Halem", 볼륨: 2023, 이슈: 11
		 Contributor editor7 = new Contributor(Contbtr.DIRECTOR); // 편집자는 DIRECTOR로 설정합니다.
		 ContributorWithType editorWithType7a = new ContributorWithType(editor7, "Alex Patrick");
		 ContributorWithType editorWithType7b = new ContributorWithType(editor7, "Collmaan Fitztet");
		 List<ContributorWithType> contributors7 = new ArrayList<>();
		 contributors7.add(editorWithType7a);
		 contributors7.add(editorWithType7b);
		 Contributor author7 = new Contributor(Contbtr.AUTHOR);
		 ContributorWithType authorWithType7 = new ContributorWithType(author7, "Roy Halem");
		 contributors7.add(authorWithType7);
		 items[7] = new Magazine("Genetic Engineering", "111-111-888", "유전자", 2023, 11, contributors7);
	 }
	 
	 public static void main(String[] args) {
		 NewCatalogSystem catalog = new NewCatalogSystem();
		 catalog.start();
	 }

	 public void start() {
		 while (true) {
		 displayMenu();
		 int choice = scanner.nextInt();
		 handleMenuChoice(choice);
		 }
	 }
	 
	 public void displayMenu() {
		 System.out.println("Welcome to Mars Library!");
		 System.out.println("1. Search Item");
		 System.out.println("2. List Items");
		 System.out.println("3. Exit");
		 System.out.print("Enter your choice: ");
	 }

	 public void handleMenuChoice(int choice) {
		 switch (choice) {
		 case 1:
		 searchItem();
		 break; 
		 case 2:
		 listItems();
		 break;	 
		 case 3:
			 System.out.println("Exiting...");
			 System.exit(0);
		 }
	 }
	    
	 public void searchItem() {
		    System.out.println("Enter search keyword: ");
		    scanner.nextLine(); // Consume newline character
		    String keyword = scanner.nextLine().toLowerCase();

		    boolean found = false;	    
		    System.out.println("");

		    for (LibraryItem item : items) {
		        List<String> locationInfo = getLocationInfo(item);

		        if (locationInfo != null) {
		            if (item.getTitle().toLowerCase().contains(keyword) || item.getSubject().toLowerCase().contains(keyword)) {
		                found = true;
		                System.out.println(item);
		                System.out.println("==============================");
		            } else if (item instanceof Book) {
		                Book book = (Book) item;
		                if (book.getContributorWithType().getContributor().toString().toLowerCase().contains(keyword)
		                        || book.getContributorWithType().getContType().toLowerCase().contains(keyword)) { // 기여자(이름) 검색 추가
		                    found = true;
		                    System.out.println(item);
		                    System.out.println("==============================");
		                }
		            } else if (item instanceof CD) {
		                CD cd = (CD) item;
		                if (cd.getArtist().getContributor().toString().toLowerCase().contains(keyword)
		                        || cd.getArtist().getContType().toLowerCase().contains(keyword)) { // 기여자(이름) 검색 추가
		                    found = true;
		                    System.out.println(item);
		                    System.out.println("==============================");
		                }
		            } else if (item instanceof DVD) {
		                DVD dvd = (DVD) item;
		                for (ContributorWithType contributorWithType : dvd.getContributors()) {
		                    if (contributorWithType.getContributor().toString().toLowerCase().contains(keyword)
		                            || contributorWithType.getContType().toLowerCase().contains(keyword)) { // 기여자(이름) 검색 추가
		                        found = true;
		                        System.out.println(item);
		                        System.out.println("==============================");
		                        break; // Once found, break the loop
		                    }
		                }
		            } else if (item instanceof Magazine) {
		                Magazine magazine = (Magazine) item;
		                for (ContributorWithType contributorWithType : magazine.getContributors()) {
		                    if (contributorWithType.getContributor().toString().toLowerCase().contains(keyword)
		                            || contributorWithType.getContType().toLowerCase().contains(keyword)) { // 기여자(이름) 검색 추가
		                        found = true;
		                        System.out.println(item);
		                        System.out.println("==============================");
		                        break; // Once found, break the loop
		                    }
		                }
		            }
		        }
		    }
		    if (!found) {
		        System.out.println("No items found matching the keyword.");
		    }
		}
	 public void listItems() {
		 System.out.println("");
		    for (LibraryItem item : items) {
		        List<String> locationInfo = getLocationInfo(item);
		        if (locationInfo != null) {
		            System.out.println(item.toString().trim());
		            System.out.println("==============================");
		        }
		    }
		}

		private List<String> getLocationInfo(LibraryItem item) {
		    List<String> locationInfo = new ArrayList<>();
		    if (item instanceof Book) {
		        Book book = (Book) item;
		        locationInfo.add("DDS Number: " + book.getDDSNumber());
		    } else if (item instanceof CD) {
		        CD cd = (CD) item;
		        locationInfo.add("Artist: " + cd.getArtist().getContributor().toString());
		    } else if (item instanceof DVD) {
		        DVD dvd = (DVD) item;
		        locationInfo.add("Genre: " + dvd.getGenre());
		        locationInfo.add("Title: " + dvd.getTitle());
		    } else if (item instanceof Magazine) {
		        Magazine magazine = (Magazine) item;
		        locationInfo.add("Volume: " + magazine.getVolume());
		        locationInfo.add("Issue: " + magazine.getIssue());
		    }
		    return locationInfo.isEmpty() ? null : locationInfo;
		}
}
