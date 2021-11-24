use SDA

create Table Cells
(
	[Save] int not null,
	[Row] int not null,
	[Column] int not null
)

create Table Save_Info
(
	[Save] int Primary Key,
	[Name] varchar(30),
	[Date] date,
	[Time] time
)

select *
from Cells

select *
from Save_Info

insert into Save_Info values(1, 'Save_1', null , null);

create trigger T
on Save_Info
after Insert
as
begin
	update dbo.Save_Info
	set [Date] = CAST( GETDATE() AS Date) , [Time] = Cast (GetDate() as Time)
	where [Date] is NULL
end





truncate table Cells
truncate table Save_Info