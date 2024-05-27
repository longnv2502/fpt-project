create database HostelManager
use HostelManager
drop database HostelManager

create table [Type] (
	ID int identity(1,1) primary key not null,
	Name varchar(50) not null,
	UnitPrice money not null,
)

create table Room (
	ID int identity(1,1) primary key not null,
	Name varchar(10) not null,
	TypeID int references [Type](ID) not null,	
	[Status] bit not null, -- 1: available, 0: unavailable
)

create table Customer (
	ID int identity(1,1) primary key not null,
	Name varchar(50) not null,
	Phone varchar(12) not null,
	Birthdate datetime not null,
	CardID varchar(16) not null,
	[Role] varchar(50) not null,
	Gender bit not null, -- 1: Male, 0: Female
	Avatar varchar(max),
	[Username] varchar(50) not null,
	[Password] varchar(50) not null,
	[Address] varchar(20) not null,
)

create table Contract(
	ID int identity(1,1) primary key not null,
	CustomerID int references Customer(ID) not null,
	RoomID int references Room(ID) not null,
	StartDate datetime not null,
	EndDate datetime,
	Status bit not null, 
)

create table [Service] (
	ServiceID int identity(1,1) primary key not null,
	ServiceName varchar(50) not null,
	UnitPrice money not null,
	ServiceStatus bit not null,
) 

create table Bill (
	ID int identity(1,1) primary key not null,
	ContractID int references Contract(ID) not null,
	StartDate datetime not null,
	EndDate datetime,
	TotalPrice money,
	Status bit not null, -- 1: Paid, 0: Unpaid
)

create table BillService (
	BillID int references Bill(ID) not null,
	ServiceID int references [Service](ServiceID) not null,
	Unit int not null,	
	Price money not null,
	primary key(BillID, ServiceID)
)

insert into [Type] values('Single Room', 1500000)
insert into [Type] values('Double Room', 2000000)
insert into [Type] values('Triple Room', 2400000)
insert into [Type] values('Quad Room', 2800000)

insert into Room values ('R001', 1, 1)
insert into Room values ('R002', 4, 0)
insert into Room values ('R003', 3, 1)
insert into Room values ('R004', 1, 1)
insert into Room values ('R005', 4, 0)
insert into Room values ('R006', 2, 1)
insert into Room values ('R007', 4, 0)
insert into Room values ('R008', 2, 1)
insert into Room values ('R009', 3, 0)
insert into Room values ('R010', 1, 1)
insert into Room values ('R011', 2, 1)
insert into Room values ('R012', 3, 1)
insert into Room values ('R013', 4, 1)
insert into Room values ('R014', 4, 1)
insert into Room values ('R015', 1, 0)

insert into Customer values ('Admin', '0999261874', '1978-01-29', '642455288367', 'adminstrator', 1, '\Resources\image\customer\admin.jpg', 'admin', 'admin', 'Hanoi')

insert into Customer values ('Mabel Acosta', '0923436132', '1984-10-17', '489972054140', 'customer', 1, '\Resources\image\customer\3bf52bf2-a769-4b79-8860-93b0f5978e70.jpg', 'customer01', '123456', 'Hai Phong')
insert into Customer values ('Katy Harrison', '0942911936', '1987-08-14', '888352417761', 'customer', 0, '\Resources\image\customer\b3a94204-aa1f-44d7-b329-20b84c0b31f0.jpg', 'customer02', '123456', 'Quang Ninh')
insert into Customer values ('Madina Kelly', '0992611956', '1988-04-26', '156515717274', 'customer', 0, '\Resources\image\customer\c70c419a-ed3f-419c-b634-1fb032df5ea5.jpg', 'customer03', '123456', 'Nghe An')
insert into Customer values ('Jamie Duggan', '0900376816', '1999-08-31', '546549787468', 'customer', 1, '\Resources\image\customer\3f165e7d-45ca-4a8c-b4e7-8d23158a6867.jpg', 'customer04', '123456', 'Hung Yen')
insert into Customer values ('Malia Pritchard', '0935321501', '1993-07-07', '801878699410', 'customer', 0, '\Resources\image\customer\d6f1a3b0-9de7-466e-93af-090442c76541.jpg', 'customer05', '123456', 'Ha Noi')
insert into Customer values ('Nellie Croft', '0937047167', '2000-10-23', '769501958501', 'customer', 1, '\Resources\image\customer\17e8963c-0b89-4f5e-894d-531846017f4a.jpg', 'customer06', '123456', 'Bac Ninh')
insert into Customer values ('Ainsley Montes', '0902285680', '2002-01-30', '824576008968', 'customer', 0, '\Resources\image\customer\09990114-cf50-4d32-85b7-ba844797e714.jpg', 'customer07', '123456', 'Cao Bang')
insert into Customer values ('Christiana Mason', '0995331978', '2003-12-01', '333287220818', 'customer', 0, '\Resources\image\customer\e1620b02-f5dc-43a2-8df6-89f4c11ed98c.jpg', 'customer08', '123456', 'Hai Duong')
insert into Customer values ('Latisha Beard', '0929137903', '2001-12-19', '260639016026', 'customer', 0, '\Resources\image\customer\740b7e50-c188-45d8-877d-7ac3bb5a7dad.jpg', 'customer09', '123456', 'TP HCM')
insert into Customer values ('Codey Snider', '0911808445', '1997-04-28', '175125007343', 'customer', 1, '\Resources\image\customer\031d8a8f-2889-4901-90e7-74bf536818cc.jpg', 'customer10', '123456', 'Nam Dinh')


-- CustomerID, RoomID, StartDate, EndDate, Status 2 5 7 9
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (2, 1, CAST(N'2022-01-01T00:00:00.000' AS DateTime), CAST(N'2022-07-01T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (3, 3, CAST(N'2022-01-01T00:00:00.000' AS DateTime), CAST(N'2022-07-01T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (4, 4, CAST(N'2022-02-08T00:00:00.000' AS DateTime), CAST(N'2023-02-08T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (5, 6, CAST(N'2022-05-23T00:00:00.000' AS DateTime), CAST(N'2022-08-23T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (6, 8, CAST(N'2022-03-15T00:00:00.000' AS DateTime), CAST(N'2022-10-15T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (7, 10, CAST(N'2022-03-22T00:00:00.000' AS DateTime), CAST(N'2022-04-22T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (8, 11, CAST(N'2021-03-08T00:00:00.000' AS DateTime), CAST(N'2024-03-08T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (9, 12, CAST(N'2020-07-23T00:00:00.000' AS DateTime), CAST(N'2022-07-23T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (10, 13, CAST(N'2022-02-15T00:00:00.000' AS DateTime), CAST(N'2022-05-15T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Contract] ([CustomerID], [RoomID], [StartDate], [EndDate], [Status]) VALUES (11, 14, CAST(N'2022-06-01T00:00:00.000' AS DateTime), CAST(N'2023-06-01T00:00:00.000' AS DateTime), 1)

insert into [Service] values ('Electricity', 3500, 0)
insert into [Service] values ('Water', 4000, 0)
insert into [Service] values ('Cleaning', 50000, 0)

-- ContractID, StartDate, EndDate, TotalPrice, Status
insert into Bill values (1, '2022-06-01', '2022-07-01', 2195000.00, 0)
insert into Bill values (2, '2022-06-01', '2022-07-01', 2950000.00, 0)
insert into Bill values (3, '2022-06-01', '2022-07-01', 1965000.00, 0)
insert into Bill values (4, '2022-06-01', '2022-07-01', 2607500.00, 0)
insert into Bill values (5, '2022-06-01', '2022-07-01', 2407500.00, 0)
insert into Bill values (6, '2022-06-01', '2022-07-01', 1907000.00, 0)
insert into Bill values (7, '2022-06-01', '2022-07-01', 2460000.00, 0)
insert into Bill values (8, '2022-06-01', '2022-07-01', 2849000.00, 0)
insert into Bill values (9, '2022-06-01', '2022-07-01', 3401000.00, 0)
insert into Bill values (10, '2022-06-01', '2022-07-01', 3218000.00, 0)

-- cai nay de cho vao payment history
insert into Bill values (1, '2022-05-01', '2022-06-01', 2584000.00, 1)
insert into Bill values (2, '2022-05-01', '2022-06-01', 2160000.00, 1)
insert into Bill values (3, '2022-05-01', '2022-06-01', 3154000.00, 1)
insert into Bill values (4, '2022-05-01', '2022-06-01', 3518000.00, 1)
insert into Bill values (5, '2022-05-01', '2022-06-01', 3196400.00, 1)
insert into Bill values (6, '2022-05-01', '2022-06-01', 1918000.00, 1)
insert into Bill values (7, '2022-05-01', '2022-06-01', 1879000.00, 1)
insert into Bill values (8, '2022-05-01', '2022-06-01', 2560000.00, 1)
insert into Bill values (9, '2022-05-01', '2022-06-01', 2990000.00, 1)
insert into Bill values (10, '2022-05-01', '2022-06-01', 2990000.00, 1)

insert into Bill values (1, '2022-04-01', '2022-05-01', 2806000.00, 1)
insert into Bill values (2, '2022-04-01', '2022-05-01', 1940000.00, 1)
insert into Bill values (3, '2022-04-01', '2022-05-01', 3376000.00, 1)
insert into Bill values (4, '2022-04-01', '2022-05-01', 3207000.00, 1)
insert into Bill values (5, '2022-04-01', '2022-05-01', 3296400.00, 1)
insert into Bill values (6, '2022-04-01', '2022-05-01', 1796000.00, 1)
insert into Bill values (7, '2022-04-01', '2022-05-01', 2070000.00, 1)
insert into Bill values (8, '2022-04-01', '2022-05-01', 2338000.00, 1)
insert into Bill values (9, '2022-04-01', '2022-05-01', 3112000.00, 1)
insert into Bill values (10, '2022-04-01', '2022-05-01', 3112000.00, 1)

-- tinh cua 01/06/2022 den 01/07/2022
-- BillID, ServiceID, Unit, Price
insert into BillService values (1, 1, 150, 525000.00)
insert into BillService values (1, 2, 30, 120000.00)
insert into BillService values (1, 3, 1, 50000.00)
insert into BillService values (2, 1, 120, 420000.00)
insert into BillService values (2, 2, 20, 80000.00)
insert into BillService values (2, 3, 1, 50000.00)
insert into BillService values (3, 1, 90, 315000.00)
insert into BillService values (3, 2, 25, 100000.00)
insert into BillService values (3, 3, 1, 50000.00)
insert into BillService values (4, 1, 125, 437500.00)
insert into BillService values (4, 2, 30, 120000.00)
insert into BillService values (4, 3, 1, 50000.00)
insert into BillService values (5, 1, 85, 297500.00)
insert into BillService values (5, 2, 15, 60000.00)
insert into BillService values (5, 3, 1, 50000.00)
insert into BillService values (6, 1, 78, 273000.00)
insert into BillService values (6, 2, 21, 84000.00)
insert into BillService values (6, 3, 1, 50000.00)
insert into BillService values (7, 1, 100, 350000.00)
insert into BillService values (7, 2, 15, 60000.00)
insert into BillService values (7, 3, 1, 50000.00)
insert into BillService values (8, 1, 98, 343000.00)
insert into BillService values (8, 2, 14, 56000.00)
insert into BillService values (8, 3, 1, 50000.00)
insert into BillService values (9, 1, 130, 455000.00)
insert into BillService values (9, 2, 24, 96000.00)
insert into BillService values (9, 3, 1, 50000.00)
insert into BillService values (10, 1, 88, 308000.00)
insert into BillService values (10, 2, 15, 60000.00)
insert into BillService values (10, 3, 1, 50000.00)




