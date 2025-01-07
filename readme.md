# Panduan Setup Project Diservis

Repository ini berisi aplikasi Spring Boot yang dikonfigurasi untuk bekerja dengan XAMPP dalam pengembangan lokal. Aplikasi akan berjalan di `localhost:81`.

## Prasyarat

Sebelum memulai, pastikan Anda telah menginstal:
- Java JDK 17 atau versi lebih baru
- Gradle 7.0 atau versi lebih baru
- XAMPP (untuk database MySQL)
- IDE pilihan Anda (IntelliJ IDEA, Eclipse, atau VS Code) disarankan menggunakan VS Code dengan ekstensi Spring Boot Dashboard

## Setup Database

1. Buka XAMPP Control Panel
2. Jalankan layanan Apache dan MySQL
3. Buka phpMyAdmin (`http://localhost/phpmyadmin`)
4. Buat database baru:
   ```sql
   CREATE DATABASE tubesdpbo;
   ```

## Menjalankan Aplikasi

### Menggunakan Terminal

1. Jalankan XAMPP dan pastikan layanan MySQL sudah aktif
2. Buka terminal di direktori project
3. Build project:
   Untuk Bash:
   ```bash
   ./gradlew clean build
   ```
   Untuk Windows:
   ```cmd
   gradlew.bat clean build
   ```
4. Jalankan aplikasi:
   Untuk Bash:
   ```bash
   ./gradlew bootRun
   ```
   Untuk Windows:
   ```cmd
   gradlew.bat bootRun
   ```
   Atau jalankan melalui IDE Anda

Aplikasi akan tersedia di `http://localhost:81`

### Menggunakan Ekstensi Spring Boot Dashboard VS Code (Disarankan)

1. Jalankan XAMPP dan pastikan layanan MySQL sudah aktif
2. Buka direktori project pada VS Code (Pastikan ekstansi yang dibutuhkan sudah terpasang)
3. Build dan Run project:
   
   Build dan Run project dengan pergi ke panel Spring Boot Dashboard dengan memilih menu berlogo tombol power pada sisi kanan VS Code,
   lalu jalankan dengan menekan tombol berlogo play pada bagian atas pada panel Spring Boot Dashboard.
   
Aplikasi akan tersedia di `http://localhost:81`

## Masalah Umum dan Solusi

1. **Port 81 sudah digunakan**
   - Periksa apakah ada layanan lain yang menggunakan port 81
   - Ubah port di `application.properties` jika diperlukan

2. **Koneksi Database Gagal**
   - Pastikan layanan MySQL XAMPP sedang berjalan
   - Periksa kredensial database di `application.properties`
   - Pastikan database sudah dibuat

3. **MySQL XAMPP Tidak Mau Start**
   - Periksa log MySQL di XAMPP control panel
   - Pastikan tidak ada instance MySQL lain yang sedang berjalan

4. **Masalah Gradle**
   - Jika Gradle wrapper tidak ada, jalankan:
     ```bash
     gradle wrapper
     ```
   - Untuk memperbarui Gradle wrapper:
     ```bash
     ./gradlew wrapper --gradle-version=8.5
     ```
   - Hapus folder `.gradle` jika terjadi masalah cache

## Lisensi

Project ini dilisensikan di bawah Lisensi MIT - lihat file [LICENSE.md](LICENSE.md) untuk detail lebih lanjut.