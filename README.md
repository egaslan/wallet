# Para Takas Uygulaması

Bu proje, kullanıcıların farklı para birimlerindeki hesaplar arasında para takas işlemleri gerçekleştirebildiği bir uygulamayı içerir. Ayrıca, kullanıcılar para birimi dönüşüm oranlarını belirli bir API'den alarak bakiyelerini farklı para birimlerine dönüştürebilirler.

## Özellikler

- Kullanıcılar USD ve TRY para birimlerinde 2 farklı hesaba sahip olabilir.
- Kullanıcılar, mevcut bakiyeyi talep edilen para biriminde çekebilir/yatırabilir/alabilir.
- Para birimi dönüştürme oranı [ExchangeRate-API](https://www.exchangerate-api.com/) adresinden alınır ve 1 dakika için geçerlidir.
- Kullanıcı bakiyesi sıfırdan düşük olamaz.
- Para çekme ve para yatırma talepleri eşzamansız olarak ele alınır.


## Kullanılan Teknolojiler

- Java 17 veya 21
- Spring Boot 3.0.0+
- Kafka
- Redis

## Projeyi Çalıştırma

### Gereksinimler

- Java JDK 17
- Maven
- Docker
- Docker Compose

### Adımlar

1. **Projeyi Derleme:**
    ```sh
    mvn clean package
    ```

2. **Docker İmajını Oluşturma:**
    ```sh
    docker build --tag wallet:1.0.0 .
    ```

3. **Docker Compose ile Projeyi Başlatma:**
    ```sh
    docker-compose up
    ```

4. **Swagger Belgelerine Erişim:**
   Tarayıcınızda [Swagger UI](http://localhost:8080/webjars/swagger-ui/index.html) adresine giderek Swagger belgelerine erişebilirsiniz. Swagger search'ine /api-docs yazmanız yeterli.

## Docker İmajını Docker Hub'a Yükleme

Docker imajınızı Docker Hub'a yüklemek için:

1. Docker Hub'da oturum açın.
2. Docker imajınızı etiketleyin:
    ```sh
    docker tag wallet:1.0.0 egas/wallet:1.0.0
    ```
3. Docker imajınızı Docker Hub'a gönderin:
    ```sh
    docker push egas/wallet:1.0.0
    ```
4. Docker imajı ortamınıza çekin:
    ```sh
    docker pull egas/wallet:1.0.0
    ```
4. Docker imajın containerda çalıştırın:
    ```sh
    docker run --name wallet_project wallet:1.0.0
    ```

## Docker-Compose'un ayaklandırılması

1.  Docker-Compose'un ayaklandırılması:
    ```sh
     docker-compose up -d 
    ```


Bu README.md dosyası, projenin temel işleyişini açıklar ve projeyi çalıştırmak için gerekli adımları adım adım açıklar. Eğer herhangi bir sorunuz veya ek talimatınız varsa, lütfen bana bildirin.
