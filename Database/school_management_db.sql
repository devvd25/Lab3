USE master;
GO

IF EXISTS (SELECT name FROM sys.databases WHERE name = N'school_management_db')
BEGIN
    ALTER DATABASE school_management_db SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE school_management_db;
END
GO

CREATE DATABASE school_management_db;
GO

USE school_management_db;
GO

CREATE TABLE students (
    id NVARCHAR(50) PRIMARY KEY,
    name NVARCHAR(100) NULL,
    email NVARCHAR(100) NULL,
    age INT NULL,
    gender NVARCHAR(20) NULL
);
GO

INSERT students (id, name, email, age, gender) VALUES (N'S001', N'Nguyễn Văn A', N'nguyenvana@gmail.com', 20, N'Nam');
INSERT students (id, name, email, age, gender) VALUES (N'S002', N'Trần Thị B', N'tranthib@gmail.com', 21, N'Nu');
INSERT students (id, name, email, age, gender) VALUES (N'S003', N'Lê Văn C', N'levanc@gmail.com', 19, N'Nam');
INSERT students (id, name, email, age, gender) VALUES (N'S004', N'Phạm Thị D', N'phamthid@gmail.com', 22, N'Nu');
INSERT students (id, name, email, age, gender) VALUES (N'S005', N'Hoàng Văn E', N'hoangvane@gmail.com', 20, N'Nam');
GO
