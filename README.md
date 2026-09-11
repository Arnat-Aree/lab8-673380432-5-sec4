# 🛍️ Lab 8: Table Relationships — Product Shop

**วิชา:** CP353002 Principles of Software Design
**ผู้จัดทำ:** อาณัฐ อารีย์
**รหัสนักศึกษา:** 673380432-5
**Section:** 4

---

## 📋 คำอธิบายโปรเจกต์

เว็บแอปพลิเคชันจัดการข้อมูลสินค้า (Product Shop) พัฒนาด้วย Spring Boot + JPA + PostgreSQL
รองรับ CRUD ครบทั้ง 4 ฟังก์ชัน (Create, Read, Update, Delete) พร้อมความสัมพันธ์ระหว่างตาราง 2 แบบ:

- **1:1** — `Product` ↔ `ProductDetail` (ข้อมูลเสริมสินค้า)
- **1:N** — `Product` → `Review` (รีวิวสินค้า)

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA / Hibernate
- PostgreSQL
- Thymeleaf
- Maven

## 📐 หลักการออกแบบที่ใช้

- **SOLID Principles** (SRP, OCP, LSP, ISP, DIP) — แยกหน้าที่ของแต่ละ Entity/Layer ชัดเจน
- **Layered Architecture** — Controller → Service → Repository → Database
- **Strategy Pattern** — คำนวณส่วนลดราคาสินค้า (`NoDiscountStrategy`, `MemberDiscountStrategy`, `SeasonalSaleStrategy`)
- **Constructor Injection (DI)** — ใช้ทุก Layer แทนการ `new` object เอง

## 📂 โครงสร้างโปรเจกต์
```
src/main/java/com/example/demo/
├── DemoApplication.java
├── model/ → Product, ProductDetail, Review
├── repository/ → ProductRepository, ProductDetailRepository, ReviewRepository
├── strategy/ → DiscountStrategy, NoDiscountStrategy, MemberDiscountStrategy, SeasonalSaleStrategy, DiscountContext
├── service/ → ProductService
└── controller/ → ProductController

src/main/resources/
├── application.properties
├── static/css/style.css
└── templates/products/ (list, add, edit, delete)
```


## ⚙️ วิธีรันโปรเจกต์

1. สร้าง Database ชื่อ `lab8shop` ใน PostgreSQL
2. ตั้งค่า `src/main/resources/application.properties` ให้ตรงกับ username/password ของเครื่องตัวเอง
3. รัน `DemoApplication.java` (หรือ `mvn spring-boot:run`)
4. เปิด browser ไปที่ `http://localhost:8080/products`

## 🔄 URL Mappings

| Method | URL | หน้าที่ |
|---|---|---|
| GET | `/products` | รายการสินค้า |
| GET | `/products/add` | ฟอร์มเพิ่มสินค้า |
| POST | `/products/save` | บันทึกสินค้า |
| GET | `/products/edit/{id}` | ฟอร์มแก้ไข |
| POST | `/products/update/{id}` | อัปเดต |
| GET | `/products/delete/{id}` | ยืนยันลบ |
| POST | `/products/delete/{id}` | ลบสินค้า |

## 📄 รายงาน

ดูรายละเอียดคำอธิบาย SOLID, Strategy Pattern, Execution Flow และภาพหน้าจอเพิ่มเติมได้ที่ไฟล์ [`Report_Lab8.pdf`](./Report_Lab8.pdf)