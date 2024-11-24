import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Menu> daftarMenu = new ArrayList<>();
    private static ArrayList<Menu> pesanan = new ArrayList<>();

    public static void main(String[] args) {
        // Data menu awal
        daftarMenu.add(new Menu("Nasi Goreng", 20000, "Makanan"));
        daftarMenu.add(new Menu("Ayam Bakar", 25000, "Makanan"));
        daftarMenu.add(new Menu("Es Teh", 5000, "Minuman"));
        daftarMenu.add(new Menu("Es Jeruk", 7000, "Minuman"));

        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== Aplikasi Restoran ===");
            System.out.println("1. Lihat Menu");
            System.out.println("2. Pemesanan");
            System.out.println("3. Manajemen Menu");
            System.out.println("4. Keluar");
            System.out.print("Pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tampilkanMenu();
                    break;
                case 2:
                    pemesanan(scanner);
                    break;
                case 3:
                    manajemenMenu(scanner);
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 4);

        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n=== Daftar Menu Restoran ===");
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu menu = daftarMenu.get(i);
            System.out.printf("%d. %s - Rp %.2f (%s)\n", i + 1, menu.getNama(), menu.getHarga(), menu.getKategori());
        }
    }

    private static void pemesanan(Scanner scanner) {
        String pilihan;
        do {
            tampilkanMenu();
            System.out.print("Masukkan nomor menu yang ingin dipesan (ketik 'selesai' untuk selesai): ");
            pilihan = scanner.next();

            if (pilihan.equalsIgnoreCase("selesai")) {
                break;
            }

            try {
                int nomor = Integer.parseInt(pilihan); // Konversi input ke nomor
                if (nomor >= 1 && nomor <= daftarMenu.size()) {
                    Menu menuDipilih = daftarMenu.get(nomor - 1);
                    pesanan.add(menuDipilih);
                    System.out.println(menuDipilih.getNama() + " berhasil ditambahkan ke pesanan.");
                } else {
                    System.out.println("Nomor menu tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Silakan masukkan nomor menu.");
            }
        } while (true);

        hitungTotal();
    }

    private static void hitungTotal() {
        double total = 0;
        System.out.println("\n=== Struk Pesanan ===");
        for (Menu menu : pesanan) {
            System.out.println(menu);
            total += menu.getHarga();
        }

        double pajak = total * 0.10;
        double biayaPelayanan = 20000;
        double diskon = 0;

        if (total > 100000) {
            diskon = total * 0.10;
        }

        double totalAkhir = total + pajak + biayaPelayanan - diskon;

        System.out.printf("Total: Rp %.2f\n", total);
        System.out.printf("Pajak (10%%): Rp %.2f\n", pajak);
        System.out.printf("Biaya Pelayanan: Rp %.2f\n", biayaPelayanan);
        if (diskon > 0) {
            System.out.printf("Diskon: Rp %.2f\n", diskon);
        }
        System.out.printf("Total Akhir: Rp %.2f\n", totalAkhir);
    }

    private static void manajemenMenu(Scanner scanner) {
        int pilihan;
        do {
            System.out.println("\n=== Manajemen Menu ===");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahMenu(scanner);
                    break;
                case 2:
                    ubahHarga(scanner);
                    break;
                case 3:
                    hapusMenu(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 4);
    }

    private static void tambahMenu(Scanner scanner) {
        System.out.print("Masukkan nama menu: ");
        String nama = scanner.next();
        System.out.print("Masukkan harga: ");
        double harga = scanner.nextDouble();
        System.out.print("Masukkan kategori (Makanan/Minuman): ");
        String kategori = scanner.next();

        daftarMenu.add(new Menu(nama, harga, kategori));
        System.out.println("Menu berhasil ditambahkan!");
    }

    private static void ubahHarga(Scanner scanner) {
        tampilkanMenu();
        System.out.print("Masukkan nama menu yang ingin diubah: ");
        String nama = scanner.next();

        for (Menu menu : daftarMenu) {
            if (menu.getNama().equalsIgnoreCase(nama)) {
                System.out.print("Masukkan harga baru: ");
                double hargaBaru = scanner.nextDouble();
                menu.setHarga(hargaBaru);
                System.out.println("Harga berhasil diubah!");
                return;
            }
        }

        System.out.println("Menu tidak ditemukan!");
    }

    private static void hapusMenu(Scanner scanner) {
        tampilkanMenu();
        System.out.print("Masukkan nama menu yang ingin dihapus: ");
        String nama = scanner.next();

        for (Menu menu : daftarMenu) {
            if (menu.getNama().equalsIgnoreCase(nama)) {
                daftarMenu.remove(menu);
                System.out.println("Menu berhasil dihapus!");
                return;
            }
        }

        System.out.println("Menu tidak ditemukan!");
    }
}
