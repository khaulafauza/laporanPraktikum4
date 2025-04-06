public class Pelanggan {
    private String nama;
    private String nomorPelanggan;
    private double saldo;
    private String pin;
    private boolean diblokir;
    private int percobaanGagal;

    public Pelanggan(String nama, String nomorPelanggan, double saldo, String pin) {
        this.nama = nama;
        this.nomorPelanggan = nomorPelanggan;
        this.saldo = saldo;
        this.pin = pin;
        this.diblokir = false;
        this.percobaanGagal = 0;
    }

    public String getNomorPelanggan() {
        return nomorPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isDiblokir() {
        return diblokir;
    }

    // Method ini public agar bisa digunakan oleh sistem (misalnya Swalayan.java) 
    // untuk validasi PIN, tanpa mengubah data sensitif pelanggan secara langsung.
    public boolean validasiPIN(String inputPIN) {
        if (diblokir) {
            System.out.println("Akun diblokir.");
            return false;
        }

        if (this.pin.equals(inputPIN)) {
            percobaanGagal = 0;
            return true;
        } else {
            percobaanGagal++;
            System.out.println("Oy PIN salah! Percobaan ke-" + percobaanGagal);
            if (percobaanGagal >= 3) {
                diblokir = true;
                System.out.println("oopss.. mff akun ini telah diblokir krn km salah PIN 3x hikss:'");
            }
            return false;
        }
    }

    public boolean transaksi(double jumlah) {
        double cashback = hitungCashback(jumlah);
        double saldoBaru = saldo - jumlah + cashback;
    
        if (saldoBaru < 10000) {
            System.out.println("Eitss transaksi gagal: saldo minimal Rp10.000.");
            return false;
        }
    
        saldo = saldoBaru;
        System.out.println("Yeyy transaksi berhasil! Cashback km: Rp" + cashback + " yaaw");
        System.out.println("Saldo akhir km: Rp" + saldo);
        return true;
    }
    
    public void topUp(double jumlah) {
        saldo += jumlah;
        System.out.println("Top up berhasil. Saldo km sekarang: Rp" + saldo + ":D");
    }
    

    private double hitungCashback(double jumlah) {
        String kode = nomorPelanggan.substring(0, 2);
    
        switch (kode) {
            case "38": // Silver
                return (jumlah > 1000000) ? jumlah * 0.05 : 0;
            case "56": // Gold
                return (jumlah > 1000000) ? jumlah * 0.07 : jumlah * 0.02;
            case "74": // Platinum
                return (jumlah > 1000000) ? jumlah * 0.10 : jumlah * 0.05;
            default:
                return 0;
        }
    }
}