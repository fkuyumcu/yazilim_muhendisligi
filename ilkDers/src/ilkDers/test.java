package ilkDers;
import java.util.*;

class Bus {
    int id;
    String kalkis;
    String varis;
    String tarih;
    int musait;

    public Bus(int id, String kalkis, String varis, String tarih, int musait) {
        this.id = id;
        this.kalkis = kalkis;
        this.varis = varis;
        this.tarih = tarih;
        this.musait = musait;
    }

    public void bookSeat() {
        if (musait > 0) {
            musait--;
            System.out.println("Bilet satın alındı. Kalan koltuk: " + musait);
        } else {
            System.out.println("Bu seferde boş koltuk bulunmamaktadır.");
        }
    }
}

class Admin {
    List<Bus> buses;
    List<User> users;
    String adminUsername = "admin"; 
    String adminPassword = "1234";

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
                return;
            }
        }
        System.out.println("Kullanıcı bulunamadı.");
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

        System.out.println("Otobüs rezervasyon sistemine hoş geldiniz!");
        System.out.println("Giriş yapmak için: 1 - Admin  2 - Kullanıcı");

        int loginChoice = scanner.nextInt();
        scanner.nextLine(); // Buffer temizlem

        if (loginChoice == 1) {
        	
            System.out.print("Admin Kullanıcı Adı: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Admin Şifre: ");
            String inputPassword = scanner.nextLine();

            if (inputUsername.equals(admin.adminUsername) && inputPassword.equals(admin.adminPassword)) {
                System.out.println("Admin olarak giriş yapıldı!");
                while (true) {
                    System.out.println("Admin Paneli:");
                    System.out.println("1- Seferleri Görüntüle, 2- Sefer Ekle, 3- Sefer Sil, 4- Kullanıcı Onayla, 5- Çıkış");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            for (Bus bus : admin.buses) {
                                System.out.println("Sefer ID: " + bus.id + ", " + bus.kalkis + " -> " + bus.varis + " Tarih: " + bus.tarih + " Koltuk: " + bus.musait);
                            }
                            break;
                        case 2:
                            System.out.print("Sefer ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine(); // Buffer temizleme
                            System.out.print("Kalkış Şehri: ");
                            String kalkis = scanner.nextLine();
                            System.out.print("Varış Şehri: ");
                            String varis = scanner.nextLine();
                            System.out.print("Tarih: ");
                            String tarih = scanner.nextLine();
                            System.out.print("Müsait Koltuk Sayısı: ");
                            int musait = scanner.nextInt();
                            admin.addBus(id, kalkis, varis, tarih, musait);
                            break;
                        case 3:
                            System.out.print("Silmek istediğiniz sefer ID: ");
                            int removeId = scanner.nextInt();
                            admin.removeBus(removeId);
                            break;
                        case 4:
                            scanner.nextLine(); // Buffer temizleme
                            System.out.print("Onaylamak istediğiniz kullanıcı adı: ");
                            String username = scanner.nextLine();
                            admin.approveUser(username);
                            break;
                        case 5:
                            System.out.println("Admin çıkış yaptı.");
                            return;
                        default:
                            System.out.println("Geçersiz seçim.");
                    }
                }
            } else {
                System.out.println("Hatalı admin bilgileri!");
                return;
            }
        } else if (loginChoice == 2) {
            // Kullanıcı girişi
            scanner.nextLine(); // Buffer temizleme
            System.out.print("Kullanıcı Adınızı Girin: ");
            String username = scanner.nextLine();
            User user = new User(username, false);
            admin.users.add(user);

            while (true) {
                System.out.println("Kullanıcı Paneli:");
                System.out.println("1- Seferleri Görüntüle, 2- Bilet Satın Al, 3- Çıkış");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        for (Bus bus : admin.buses) {
                            System.out.println("Sefer ID: " + bus.id + ", " + bus.kalkis + " -> " + bus.varis + " Tarih: " + bus.tarih + " Koltuk: " + bus.musait);
                        }
                        break;
                    case 2:
                        if (!user.isMember) {
                            System.out.println("Bilet satın almak için admin onayı gerekiyor.");
                            break;
                        }
                        System.out.print("Satın almak istediğiniz seferin ID'sini girin: ");
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
                        return;
                    default:
                        System.out.println("Geçersiz seçim.");
                }
            }
        } else {
            System.out.println("Geçersiz seçim.");
        }

        scanner.close();
    }
}
