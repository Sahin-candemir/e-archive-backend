# E-Archive Backend

## Genel Bakış
Kullanıcıların dosya yükleme, klasör yönetimi, dosya paylaşımı ve kullanıcı doğrulama işlemlerini RESTful API olarak sunar.  

JWT entegrasyonu sayesinde kullanıcı yetkilendirilir.

---

## Teknolojiler

- Java 17  
- Spring Boot
- Spring Web
- Spring Security (JWT)  
- Spring Data JPA  
- PostgreSQL
- Maven
- Map Struct
---
## Özellikler
* Kullanıcı Kayıt ve Giriş işlemleri haricinde istek atılırken Authorization headers içerisinde ``Bearer <JWT-TOKEN>`` eklenmelidir.
* JWT Token kullanıcı giriş yaptıktan sonra cevap olarak dönmektedir.
* Kullanıcı kayıt (``POST /auth/register``)
* Kullanıcı giriş (``POST /auth/login``)
---
* Yeni dosya ekleme (``POST /files/upload/{folderId}``)
* Birden fazla yeni dosya ekleme (``POST /files/upload-multiple/{folderId}``)
* İstenilen kalsör id, kaçıncı sayfa, kaç dosya ve aranılan dosya adına göre kayıtları getir (``GET /files/getAll?page={page}&size={size}&search={search}&folderId={folderId}``)
* Dosya indirme (``GET /files/download/{name}``)
* Dosya güncelleme (``PUT /files``)
* Dosya silme (``DELETE /files/{name}``)
---
* Yeni klasör oluşturma (``POST /folders``)
* Bir klasör altındaki alt klasörleri getir (``GET /folders/subfolders?parentId={parentId}``)
* Parent klasörleri getir (``GET /folders/parentfolders``)
* Klasör içerisindeki alt klasör ve dosyaları getir (``GET /folders//subfolders/{folderId}``)
* Parent Klasör bilgisi null olanları getir (``GET /folders/tree``)
* Klasörü sil (``DELETE /folders/{id}``)
---
* Dosya Paylaşma Özellikleri henüz eklenmedi.
---
## Veritabanı ER diagramı
![e-archiv_ERD.png](e-archiv_ERD.png)
