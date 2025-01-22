CREATE TABLE clientes (
    cliente_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(20) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE cuentas (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  numero_cuenta VARCHAR(50) NOT NULL UNIQUE,
  tipo_cuenta VARCHAR(50) NOT NULL,
  saldo_inicial DECIMAL(10, 2) NOT NULL,
  estado BOOLEAN NOT NULL,
  cliente_id BIGINT NOT NULL,
  FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id)
);

CREATE TABLE movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL,
    cuenta_id BIGINT NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);
