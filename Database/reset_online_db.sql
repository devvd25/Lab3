USE dbtest2026;
GO

IF OBJECT_ID('dbo.students', 'U') IS NOT NULL
    DROP TABLE dbo.students;
GO

CREATE TABLE students (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NULL,
    email NVARCHAR(100) NULL,
    age INT NULL,
    gender NVARCHAR(50) NULL
);
GO

SET IDENTITY_INSERT students ON;

INSERT students (id, name, email, age) VALUES (1, N'Nguyễn Văn A', N'nguyenvana@gmail.com', 20);
INSERT students (id, name, email, age) VALUES (2, N'Trần Thị B', N'tranthib@gmail.com', 21);
INSERT students (id, name, email, age) VALUES (3, N'Lê Văn C', N'levanc@gmail.com', 19);
INSERT students (id, name, email, age) VALUES (4, N'Phạm Thị D', N'phamthid@gmail.com', 22);
INSERT students (id, name, email, age) VALUES (5, N'Hoàng Văn E', N'hoangvane@gmail.com', 20);

SET IDENTITY_INSERT students OFF;
GO
