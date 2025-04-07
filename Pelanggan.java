// Class ini merepresentasikan objek pelanggan swalayan
public class Pelanggan {
    // Ini data-data yang disimpan buat tiap pelanggan.
    // Pakai "private" biar gabisa diubah sembarangan dr luar class, jadi lebih aman.
    private String nama; // Nama pelanggan
    private String nomorPelanggan; // Nomor unik pelanggan (2 digit awal = jenis tingkatan akun)
    private double saldo; // Saldo akun pelanggan
    private String pin; // PIN untuk autentikasi transaksi
    private boolean diblokir; // Status akun (apakah diblokir atau tidak)
    private int percobaanGagal; // buat nyatet berapa kali user salah masukin PIN

    // Ini constructornya dipanggil pas bikin objek Pelanggan baru.
    // Langsung isi nama, no pelanggan, saldo awal, dan PIN.
    public Pelanggan(String nama, String nomorPelanggan, double saldo, String pin) {
        this.nama = nama;
        this.nomorPelanggan = nomorPelanggan;
        this.saldo = saldo;
        this.pin = pin;
        this.diblokir = false; // Saat akun baru dibuat, belum diblokir
        this.percobaanGagal = 0; // Jumlah salah PIN masih nol
    }

    // Getter buat nomor pelanggan
    public String getNomorPelanggan() {
        return nomorPelanggan;
    }

    // Getter buat nama pelanggan
    public String getNama() {
        return nama;
    }

    // Getter buat saldo pelanggan
    public double getSaldo() {
        return saldo;
    }

    // Cek status blokir akun
    public boolean isDiblokir() {
        return diblokir;
    }

    // Buat ngecek apakah PIN yang dimasukin user itu bener
    public boolean validasiPIN(String inputPIN) {
        if (diblokir) { // kalo akun udah keburu diblokir, langsung tolak aja
            System.out.println("Akun diblokir.");
            return false;
        }

        // Bandingin PIN input sama yang disimpen
        if (this.pin.equals(inputPIN)) {
            percobaanGagal = 0; // Reset percobaan jika PIN benar
            return true;
        } else {
            percobaanGagal++; // Tambah jumlah gagal jika salah
            System.out.println("Oy PIN salah! Percobaan ke-" + percobaanGagal);
            if (percobaanGagal >= 3) {
                diblokir = true; // kalo udah 3x salah, akun diblokir otomatis
                System.out.println("oopss.. mff akun ini telah diblokir krn km salah PIN 3x hikss:'");
            }
            return false;
        }
    }

    // Ini buat proses pembelian/transaksi
    public boolean transaksi(double jumlah) {
        double cashback = hitungCashback(jumlah); // hitung cashback berdasar jumlah dan tipe pelanggan
        double saldoBaru = saldo - jumlah + cashback; // saldo akhir = saldo lama dikurang belanja, ditambah cashback

        if (saldoBaru < 10000) {
            // aturan: saldo minimal setelah transaksi harus 10rb
            System.out.println("Eitss transaksi gagal: saldo minimal Rp10.000.");
            return false;
        }

        saldo = saldoBaru; // update saldo pelanggan
        System.out.println("Yeyy transaksi berhasil! Cashback km: Rp" + cashback + " yaaw");
        System.out.println("Saldo akhir km: Rp" + saldo);
        return true;
    }

    // Fungsi buat nambahin saldo (top up)
    public void topUp(double jumlah) {
        saldo += jumlah;
        System.out.println("Top up berhasil. Saldo km sekarang: Rp" + saldo + ":D");
    }

    // Logika buat ngitung cashback sesuai tipe pelanggan
    private double hitungCashback(double jumlah) {
        String kode = nomorPelanggan.substring(0, 2); // ambil 2 digit awal dari nomor pelanggan

        // Pakai switch case karena cuma ada 3 jenis pelanggan dengan ketentuan masing-masing
        switch (kode) {
            case "38": // Silver
                // Silver cuma dapet cashback kalo belanja di atas 1 juta
                return (jumlah > 1000000) ? jumlah * 0.05 : 0;
            case "56": // Gold
                // Gold dapet cashback 7% kalo belanja > 1 juta
                // Kalau di bawah itu tetep dapet 2%
                return (jumlah > 1000000) ? jumlah * 0.07 : jumlah * 0.02;
            case "74": // Platinum
                // Platinum dapet 10% cashback kalau belanja > 1 juta, 5% kalau <= 1 juta
                return (jumlah > 1000000) ? jumlah * 0.10 : jumlah * 0.05;
            default:
                // Kalau tipe g dikenali, g dpt cashback
                return 0;
        }
    }
}
