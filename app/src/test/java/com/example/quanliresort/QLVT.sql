CREATE DATABASE QLVT
GO
USE QLVT
GO
--BANG LOAI VAT TU
CREATE TABLE LoaiVatTu(
    MaLoai CHAR(10),
    TenLoai NVARCHAR(100),
    PRIMARY KEY (MaLoai)
)
GO
--BANG VAT TU
CREATE TABLE VatTu(
    MaHang CHAR(10),
    TenHang NVARCHAR(100),
    DonViTinh NVARCHAR(20) DEFAULT 'Cái',
    SoLuong INT CHECK(SoLuong>0),
    DonGia Money CHECK (DonGia > 0),
    MaLoai CHAR(10),
    CHECK (DonViTinh like N'%Thùng' OR DonViTinh like N'%Bịch' 
    OR DonViTinh like N'%Khối' OR DonViTinh like N'%Cái'),
    PRIMARY KEY (MaHang,MaLoai),
    CONSTRAINT fk_VT FOREIGN KEY (MaLoai) REFERENCES LoaiVatTu (MaLoai)
)
GO
--BANG KHACH HANG
CREATE TABLE KhachHang(
    MaSoKH CHAR(10),
    TenKH NVARCHAR(100),
    DienThoai CHAR(15),
    PRIMARY KEY (MaSoKH)
)
GO
--BANG HOA DO
CREATE TABLE HoaDon(
    SoHoaDon CHAR(10),
    NgayLap DATE,
    MaSoKH CHAR(10),
    PRIMARY KEY (SoHoaDon),
    CONSTRAINT fk_HD FOREIGN KEY (MaSoKH) REFERENCES KhachHang (MaSoKH)
)
GO
--BANG CHI TIET HOA DON
CREATE TABLE ChiTietHoaDon(
    SoHoaDon CHAR(10) NOT NULL,
    MaHang CHAR(10),
    SoLuong INT CHECK (SoLuong > 0),
    DonGia Money CHECK (DonGia > 0),
)
GO
-- THAY DOI KIEU DU LIEU 
ALTER TABLE ChiTietHoaDon ADD FOREIGN KEY (SoHoaDon) REFERENCES HoaDon(SoHoaDon)
GO
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT PK_ChiTietHoaDon PRIMARY KEY (SoHoaDon)
GO
ALTER TABLE ChiTietHoaDon ADD FOREIGN KEY (MaHang) REFERENCES VatTu(MaHang)
GO
ALTER TABLE KhachHang ALTER COLUMN TenKH NVARCHAR(50)
GO
--THEM DU LIEU
USE QLVT
GO
--BANG LOAI VAT TU
INSERT INTO LoaiVatTu VALUES ('G01',N'Gạch'),
                            ('D01',N'Đá'),
                            ('N01',N'Ngói'),
                            ('C01',N'Cát'),
                            ('T01',N'Thép')
GO
--BANG VAT TU
INSERT INTO VatTu VALUES ('G001',N'Gạch Nung',N'Cái',100000,200000,'G01'),
                        ('D001',N'Đá Ong',N'Khối',100,300000,'D01'),
                        ('N001',N'Ngói Đỏ',N'Cái',1000,400000,'N01'),
                        ('C001',N'Cát Vàng',N'Khối',200,500000,'C01'),
                        ('T001',N'Thép Không Gỉ',N'Cái',500,700000,'T01')
GO
--BANG KHACH HANG
INSERT INTO KhachHang VALUES ('K01',N'Nguyễn Tiến Dũng','0375158965'),
                             ('K02',N'Lê Văn Mạnh','0337410560'),
                             ('K03',N'Nguyễn Trần Việt','0326614464')  ,
                             ('K04',N'Ngô Tấn Đạt','0965149358'),
                             ('K05',N'Phan Khánh Huyền','0378101321')
GO
--BANG HOA DON
SET DATEFORMAT DMY
INSERT INTO HoaDon VALUES ('01','13/02/2020','K01'),
                          ('03','15/03/2020','K01'),
                          ('02','20/03/2020','K02'),
                          ('04','22/05/2020','K04'),
                          ('05','23/06/2020','K05'),
						  ('06','11/05/2020','K03'),
						  ('07','27/06/2020','K05'),
						  ('08','23/06/2020','K04'),
						  ('09','23/06/2020','K05'),
						  ('10','23/06/2020','K05')

GO
--BANG HOA DON
INSERT INTO  ChiTietHoaDon VALUES ('01','G001',10000,200000) ,
                                 ('02','N001',20000,300000),
                                 ('03','D001',1000,400000),
                                 ('04','T001',2000,700000) ,
                                 ('05','C001',3000,100000),
                                 ('06','G001',10000,200000),
                                 ('07','N001',30000,400000),
                                 ('08','D001',2000,800000),
                                 ('09','T001',3000,300000),
                                 ('10','G001',20000,400000)
GO

--TRUY VAN DU LIEU
USE QLVT
GO
--a
SELECT * FROM VatTu WHERE SoLuong >= 10 AND DonViTinh = 'Thùng'
GO
--b
SELECT HoaDon.MaSoKH,KhachHang.TenKH, count(HoaDon.MaSoKH) AS N'Số lượt mua hàng'
FROM HoaDon,KhachHang WHERE HoaDon.MaSoKH = KhachHang.MaSoKH
GROUP BY HoaDon.MaSoKH,KhachHang.TenKH
GO
--c
SELECT TOP 1 HoaDon.MaSoKH,KhachHang.TenKH
FROM HoaDon,KhachHang
WHERE HoaDon.MaSoKH = KhachHang.MaSoKH GROUP BY HoaDon.MaSoKH,KhachHang.TenKH
ORDER BY count(HoaDon.MaSoKH) DESC
GO
--d
SELECT TOP 1 ChiTietHoaDon.SoHoaDon,SUM(ChiTietHoaDon.SoLuong*ChiTietHoaDon.DonGia) AS 'Tổng giá trị'
FROM ChiTietHoaDon,HoaDon
WHERE HoaDon.SoHoaDon = ChiTietHoaDon.SoHoaDon
GROUP BY ChiTietHoaDon.SoHoaDon
ORDER BY SUM(ChiTietHoaDon.SoLuong*ChiTietHoaDon.DonGia) DESC
GO

--TAO FUNCTION
USE QLVT
GO
--a
CREATE FUNCTION QLVT_A()
RETURNS TABLE
AS 
    RETURN SELECT * FROM dbo.VatTu
GO
SELECT * FROM QLVT_A()
GO
--b
CREATE FUNCTION QLVT_B()
RETURNS TABLE
AS 
    RETURN SELECT * FROM dbo.KhachHang WHERE count(HoaDon.MaSoKH) > 10
GO
SELECT * FROM QLVT_B()
GO

--TAO PROCEDURE
USE QLVT
GO
--a
CREATE PROCEDURE VT1(@MaHang CHAR(10),@TenHang NVARCHAR(100),@DonViTinh NVARCHAR(20),@SoLuong INT,@DonGia MONEY,@MaLoai CHAR(10))
AS
BEGIN
    IF EXISTS(SELECT   MaHang FROM VatTu WHERE @MaHang = MaHang)
        PRINT N'Không hợp lệ'
    ELSE IF NOT EXISTS(SELECT MaLoai FROM LoaiVatTu WHERE @MaLoai = MaLoai)
        PRINT N'Không đúng khóa ngoại'
    ELSE IF @SoLuong < 0 and @DonGia <0
        PRINT N'Số lượng và đơn giá phải lớn hơn 0'
	ELSE
		INSERT INTO VatTu VALUES (@MaHang,@TenHang,@DonViTinh,@SoLuong,@DonGia,@MaLoai)
    
END
GO
--Thực thi
INSERT INTO LoaiVatTu VALUES ('N02',N'Gạch')
GO
EXEC VT1 'N003',N'Ngói Trắng',N'Thùng',2000,800000,'N02'
GO
--b
CREATE PROCEDURE HD1(@SoHoaDon CHAR(10),@NgayLap DATE,@MaSoKH CHAR(10))
AS
BEGIN
    IF EXISTS (SELECT SoHoaDon FROM HoaDon WHERE @SoHoaDon = SoHoaDon)
        PRINT N'không hợp lệ'
    ELSE IF NOT EXISTS (SELECT MaSoKH FROM KhachHang WHERE @MaSoKH = MaSoKH)
        PRINT N'Không hợp lệ'
    ELSE
        INSERT INTO HoaDon VALUES (@SoHoaDon,@NgayLap,@MaSoKH)
END
GO
--thực thi
INSERT INTO KhachHang VALUES ('K06',N'Trần Chiêu Hưng','0374125768')
GO
SET DATEFORMAT DMY
EXEC HD1 '11','23/03/2020','K06'

--TAO TRIGGER
USE QLVT
GO
--b
CREATE TRIGGER KH2
ON KhachHang
FOR INSERT, UPDATE
AS
BEGIN
   IF NOT EXISTS(SELECT INSERTED.MaSoKH FROM INSERTED,DELETED WHERE INSERTED.MaSoKH = DELETED.MaSoKH)
      BEGIN
         PRINT N'Mã khách hàng không thể sửa'
         ROLLBACK
      END
   ELSE
      BEGIN
         DECLARE @MaSoKH CHAR(10),@TenKH NVARCHAR(50),@DienThoai CHAR(15)
         SELECT @MaSoKH = MaSoKH,@TenKH = TenKH,@DienThoai=DienThoai FROM INSERTED
         UPDATE  KhachHang SET  @TenKH = TenKH,@DienThoai=DienThoai WHERE @MaSoKH = MaSoKH 
      END
END
GO
INSERT INTO KhachHang VALUES ('K07',N'Nguyễn Lê Hồng Huy','0378964842')
GO
--a
CREATE TRIGGER KH1
ON KhachHang
FOR INSERT
AS 
BEGIN 
     IF  EXISTS(SELECT 1 FROM KhachHang a,inserted b WHERE a.MaSoKH = b.MaSoKH AND a.DienThoai = b.DienThoai)
        BEGIN
			PRINT N'không hợp lệ'
			ROLLBACK
        END 
END
GO
INSERT INTO KhachHang VALUES ('K07',N'Nguyễn Lê Hồng Huy','0378964842')
GO