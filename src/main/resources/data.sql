-- Insertar clientes
INSERT INTO clientes (nombre, genero, edad, identificacion, direccion, telefono, contrasena, estado)
VALUES ('Jose Lema', 'Masculino', 35, '1234567890', 'Otavalo sn y principal', '098254785', '1234', TRUE);

-- Insertar cuentas
INSERT INTO cuentas (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES ('478758', 'Ahorros', 2000.00, TRUE, 1);

-- Insertar movimientos ejemplo
INSERT INTO movimientos (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES ('2023-01-01 10:00:00', 'Depósito', 500.00, 1500.00, 1);
