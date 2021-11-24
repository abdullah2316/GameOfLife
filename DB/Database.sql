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

truncate table Cells
