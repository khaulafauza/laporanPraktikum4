import java.util.Scanner;

public class Swalayan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Pelanggan[] pelangganList = {
            new Pelanggan("Abun", "3800000001", 2000000, "1234"),
            new Pelanggan("Riki", "5600000002", 3000000, "4321"),
            new Pelanggan("Chenle", "7400000003", 4000000, "5678")
        };

        while (true) {
            System.out.print("\nMasukkan nomor pelanggan kmuh: ");
            String nomor = input.next();
            Pelanggan pelanggan = null;

            for (Pelanggan p : pelangganList) {
                if (p.getNomorPelanggan().equals(nomor)) {
                    pelanggan = p;
                    break;
                }
            }

            if (pelanggan == null) {
                System.out.println("OMG nomor pelanggan tidak ditemukanT-T");
                continue;
            }

            if (pelanggan.isDiblokir()) {
                System.out.println("oow mff akun ini telah diblokir:(");
                continue;
            }

            System.out.println("Haloow, " + pelanggan.getNama() + ":D");

            String pin="";
            boolean autentifikasiBerhasil = false;

            for (int i = 0; i < 3; i++) {
                System.out.print("Masukin PIN nya yhh: ");
                pin = input.next();

                if (pelanggan.isDiblokir()) {
                    System.out.println("oopss.. mff akun ini telah diblokir krn km salah PIN 3x hikss:'");
                    autentifikasiBerhasil = false;
                    break;
                }

                if (pelanggan.validasiPIN(pin)) {
                    autentifikasiBerhasil = true;
                    break;
                }                
            }

            if (!autentifikasiBerhasil) continue;

            System.out.println("Saldo kamu sekarang: Rp"+ pelanggan.getSaldo());
            System.out.println("\nMau apnihh:");
            System.out.println("1. Pembelian");
            System.out.println("2. Top up (bkn epep)");
            System.out.println("3. Keluar ajlah");

            System.out.print("Pilih menu (1-3 aj jgn maruk): ");
            int menu = input.nextInt();

            if (menu == 1) {
                System.out.print("Masukkan jumlah (Rp) pembelian: ");
                double jumlah = input.nextDouble();
                pelanggan.transaksi(jumlah);
            } else if (menu == 2) {
                System.out.print("Masukkan jumlah (Rp) top up: ");
                double jumlah = input.nextDouble();
                pelanggan.topUp(jumlah);
            } else if (menu == 3) {
                System.out.println("YEAYY terimakasii suda berbelanja^-^!");
                break;
            } else {
                System.out.println("hdehh menu tdks valid bang.");
            }
        }
    }
}