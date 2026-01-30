-- Script để thêm cột age vào bảng students
USE [dbtest2026]
GO

-- Thêm cột age
ALTER TABLE [dbo].[students]
ADD [age] INT NULL;
GO

-- Cập nhật một số giá trị mẫu cho age (tuổi ngẫu nhiên từ 18-25)
UPDATE [dbo].[students] SET [age] = 20 WHERE [id] = 1;
UPDATE [dbo].[students] SET [age] = 21 WHERE [id] = 2;
UPDATE [dbo].[students] SET [age] = 19 WHERE [id] = 3;
UPDATE [dbo].[students] SET [age] = 22 WHERE [id] = 4;
UPDATE [dbo].[students] SET [age] = 23 WHERE [id] = 6;
UPDATE [dbo].[students] SET [age] = 20 WHERE [id] = 7;
UPDATE [dbo].[students] SET [age] = 21 WHERE [id] = 8;
GO
