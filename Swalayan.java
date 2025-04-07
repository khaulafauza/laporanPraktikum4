import java.util.Scanner; // buat input dari keyboard

// Main class, tempat aplikasi dijalankan
public class Swalayan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Scanner buat baca input dari user

        // Bikin array yang isinya daftar beberapa pelanggan
        Pelanggan[] pelangganList = {
            new Pelanggan("Abun", "3800000001", 2000000, "1234"), // Silver
            new Pelanggan("Riki", "5600000002", 3000000, "4321"), // Gold
            new Pelanggan("Chenle", "7400000003", 4000000, "5678") // Platinum
        };

        while (true) { // ini loop utama, bakal jalan terus sampe user pilih menu keluar
            System.out.print("\nMasukkan nomor pelanggan kmuh: ");
            String nomor = input.next(); //minta input nomor pelanggan
            Pelanggan pelanggan = null;

            // Cari pelanggan dari list, berdasarkan nomor yang dimasukkan
            for (Pelanggan p : pelangganList) {
                if (p.getNomorPelanggan().equals(nomor)) {
                    pelanggan = p;
                    break; // kalo ketemu langsung keluar loop
                }
            }

            if (pelanggan == null) {
                // Kalo nomornya g ktmu
                System.out.println("OMG nomor pelanggan tidak ditemukanT-T");
                continue; // balik ke awal loop
            }

            if (pelanggan.isDiblokir()) {
                // Kalo akun udah diblokir, gabisa lanjut
                System.out.println("oow mff akun ini telah diblokir:(");
                continue;
            }

            // Sapaan untuk pelanggan
            System.out.println("Haloow, " + pelanggan.getNama() + ":D");

            String pin = "";
            boolean autentifikasiBerhasil = false;

            // Coba masukin PIN maksimal 3x
            for (int i = 0; i < 3; i++) {
                System.out.print("Masukin PIN nya yhh: ");
                pin = input.next();

                // Cek apakah akun ternyata udh diblokir
                if (pelanggan.isDiblokir()) {
                    autentifikasiBerhasil = false;
                    break;
                }

                if (pelanggan.validasiPIN(pin)) {
                    autentifikasiBerhasil = true;
                    break; // keluar dari loop kalo PIN bener
                }
            }

            if (!autentifikasiBerhasil) continue; // skip ke awal kalau gagal masuk

            // Tampilkan saldo setelah berhasil login
            System.out.println("Saldo kamu sekarang: Rp" + pelanggan.getSaldo());

            // Tampilkan menu
            System.out.println("\nMau apnihh:");
            System.out.println("1. Pembelian");
            System.out.println("2. Top up (bkn epep)");
            System.out.println("3. Keluar ajlah");

            System.out.print("Pilih menu (1-3 aj jgn maruk): ");
            int menu = input.nextInt(); // ambil input pilihan

            if (menu == 1) {
                // Menu pembelian
                System.out.print("Masukkan jumlah (Rp) pembelian: ");
                double jumlah = input.nextDouble();
                pelanggan.transaksi(jumlah); // proses belanja, akan cek saldo & cashback
            } else if (menu == 2) {
                // Menu top up
                System.out.print("Masukkan jumlah (Rp) top up: ");
                double jumlah = input.nextDouble();
                pelanggan.topUp(jumlah); // tinggal tambah saldo
            } else if (menu == 3) {
                // Keluar dari program
                System.out.println("YEAYY terimakasii suda berbelanja^-^!");
                break;
            } else {
                // Input menu ga valid
                System.out.println("hdehh menu tdks valid bang.");
            }
        }
    }
}
