# User Controller API Automation Testing

Project automation testing untuk menguji API user contnroller dari dokumentasi https://dummyapi.io/docs/user. Project ini dikembangkan dengan bahasa Java untuk kode program dan pembuatan script test dan Maven sebagai build tools.

## Build With
Automation Testing pada project ini menggunakan dua buah library.
 <ul>
    <li>JUnit</li>
    <li>REST Assured</li>
 </ul>
 
# Getting Started
## Pre-requisites
Sebelum menjalankan project ini, perlu disiapkan environment yang sesuai.
<ul>
 <li>Java 17+</li>
 <li>Apache Maven 3.8.0+</li>
</ul>

## Struture Project Test
Berikut ini merupakan struktur kode beserta penjelasannya
```
{nama_proyek}
 src
   main
   test
     java
       model
         Location.java
         User.java
       request
         EndPoint.java
         {namaAPI}Endpoint.java
       testapi
         {namaAPI}Test.java
     resources
       user-schema.json
```
<ul>
 <li>package model berisi class yang digunakan sebagai obejct dari struktur data yang digunakan pada pengujian</li>
 <li>package request berisi pendefinisian url dan mengatur apa saja yang dikirimkan saat melakukan request</li>
 <li>package testapi berisi script test untuk eksekusi test case yang telah dibuat</li>
</ul>

## Run Automation Test
1. Clone repository dengan git
   ```
   git clone https://github.com/salsabilamp3/user-controller-api-automation-testing.git
   ```
2. Jalankan perintah berikut untuk menginstall artifak yang didefinisikan
   ```
   mvn clean install
   ```
3. Jalankan perintah berikut pada terminal untuk menjalankan semua test
   ```
   mvn test
   ```
   

## Test Cases
Pembuatan test case meliputi test positif dan negatif untuk setiap endpoint.

#### CREATE
```
1. Pemeriksaan penambahan user dengan mengatur app-id yang salah pada header request.
2. Pemeriksaan penambahan user hanya dengan mengisikan required field, jumlah karakter firstName pada batas bawah(2) dan lastName dalam range dan email valid yang belum terdaftar di sistem.
3. Pemeriksaan penambahan user hanya dengan mengisikan required field, jumlah karakter firstName dalam range dan lastName pada batas atas(50) dan email valid yang belum terdaftar di sistem.
4. Pemeriksaan penambahan user dengan mengisikan semua field bukan hanya field required dengan semua field valid.
5. Pemeriksaan penambahan user dengan mengisikan semua field benar, dan field location.street diisi 101 karakter.
```
#### GET
```
1.
2.
3.
4.
5.
```
#### UPDATE
```
1. Pemeriksaan update user dengan mengisikan semua field dalam range dan data yang valid tanpa email, dengan id yang valid dan terdaftar.
2. Pemeriksaan update user hanya mengisikan title diisi dengan "mr", dengan id yang valid dan terdaftar.
3. Pemeriksaan update user hanya mengisikan gender diisi dengan "", dengan id yang valid dan terdaftar.
4. Pemeriksaan update user dengan mengisikan email yang benar dengan id yang valid dan terdaftar
5. Pemeriksaan update user dengan mengisikan semua field dengan benar, dengan id yang tidak terdaftar
```
#### DELETE
```
1.
2.
3.
4.
5.
```

## Author
[Amelia Dewi Agustiani](https://github.com/ameliadewi19) (211524002)

[Salsabila Maharani Putri](https://github.com/salsabilamp3) (211524026)

[Raka Mahardika Maulana](https://github.com/rakamhrdka10) (211524056)

_Mahasiswa D4 Teknik Informatika Politeknik Negeri Bandung_
