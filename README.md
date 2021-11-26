# Dorayaki Supplier

## How to Run
```
mvn clean install
mvn tomcat7:run
```
Make sure to change database username and password in DatabaseService.js


## Deskripsi singkat web service
Web service dibuat dengan Java memanfaatkan Maven dan JAX-WS. Web service digunakan sebagai perantara antara store dan factory. Store dapat meminta varian dorayaki melalui web service dan melakukan request stok. Pada web service terdapat rate limiter terhadap request dari store.

## Skema Basis Data yang Digunakan
| requests    |
| ----------- |
| id          |
| id_dorayaki |
| stok_added  |
| status      |
| createdAt   |
| updatedAt   |

| dorayakis     |
| ------------- |
| id            |
| dorayaki_name |
| createdAt     |
| updatedAt     |


## Pembagian Tugas

SOAP
- GET Request Status: 13519144
- POST Request : 13519114
- GET Dorayaki : 13519081