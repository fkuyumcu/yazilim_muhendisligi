package ilkDers;
import java.util.*;

class Bus {
    int id;
    String departure;
    String arrival;
    String date;
    int availableSeats;

    public Bus(int id, String departure, String arrival, String date, int availableSeats) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.availableSeats = availableSeats;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            System.out.println("Bilet satın alındı. Kalan koltuk: " + availableSeats);
        } else {
            System.out.println("Bu seferde boş koltuk bulunmamaktadır.");
        }
    }
}

class Admin {
    List<Bus> buses;
    List<User> users;

    public Admin() {
        this.buses = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBus(int id, String departure, String arrival, String date, int availableSeats) {
        buses.add(new Bus(id, departure, arrival, date, availableSeats));
        System.out.println("Yeni sefer eklendi.");
    }

    public void removeBus(int id) {
        buses.removeIf(bus -> bus.id == id);
        System.out.println("Sefer kaldırıldı.");
    }
    
    public void approveUser(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                user.isMember = true;
                System.out.println(username + " kullanıcısı onaylandı.");
            }
        }
    }
}





class User {
    String username;
    boolean isMember;
    
    public User(String username, boolean isMember) {
        this.username = username;
        this.isMember = isMember;
    }
}






public class test {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	        Admin admin = new Admin();

	        admin.addBus(1, "İstanbul", "Ankara", "2024-06-01", 40);
	        admin.addBus(2, "İzmir", "Antalya", "2024-06-02", 35);

	        System.out.println("Otobüs rezervasyon sistemine hoş geldiniz.");
	        System.out.println("Lütfen işlem seçin: 1- Seferleri Görüntüle, 2- Bilet Satın Al, 3- Çıkış");
	        int choice = scanner.nextInt();
	        
	        switch (choice) {
	            case 1:
	                for (Bus bus : admin.buses) {
	                    System.out.println("Sefer ID: " + bus.id + ", " + bus.departure + " -> " + bus.arrival + " Tarih: " + bus.date + " Koltuk: " + bus.availableSeats);
	                }
	                break;
	            case 2:
	                System.out.println("Satın almak istediğiniz seferin ID'sini girin: ");
	                int busId = scanner.nextInt();
	                for (Bus bus : admin.buses) {
	                    if (bus.id == busId) {
	                        bus.bookSeat();
	                        break;
	                    }
	                }
	                break;
	            case 3:
	                System.out.println("Çıkış yapılıyor...");
	                break;
	            default:
	                System.out.println("Geçersiz seçim.");
	        }

	}

}
