DECLARE @NAMES TABLE (
   id INT IDENTITY(1,1),
   name VARCHAR(100)
)

INSERT INTO @NAMES(name)
VALUES('João'), ('Pedro'), ('Lucas'), ('Carlos'), ('Paulo'),
      ('Gabriel'), ('Felipe'), ('Antônio'), ('Fernando'), ('Gustavo'),
      ('Rafael'), ('Eduardo'), ('Márcio'), ('Henrique'), ('Luis')

DECLARE @i INT = 0

WHILE @i < 50
BEGIN
   DECLARE @name VARCHAR(100) = (
      SELECT name FROM @NAMES ORDER BY NEWID() OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY
   )

   INSERT INTO [dbo].[_user] (id, name, phone, email, role)
   VALUES (
      (SELECT MAX(id) FROM [dbo].[_user]) + 1,
      @name,
      '11 9 ' + CAST(ROUND(RAND() * 100000, 0) AS VARCHAR(8)),
      REPLACE(LOWER(@name), ' ', '.') + '@gmail.com',
      'USER'
   )

   SET @i += 1
END
