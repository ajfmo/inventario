DROP TABLE IF EXISTS `deposito`;
CREATE TABLE `deposito` (
  `idDeposito` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion_deposito` varchar(45) NOT NULL,
  PRIMARY KEY (`idDeposito`),
  UNIQUE KEY `descripcion_deposito_UNIQUE` (`descripcion_deposito`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `idProductos` varchar(45) NOT NULL,
  `descripcion_producto` varchar(45) NOT NULL,
  `costo_producto` double DEFAULT NULL,
  `utilidad_producto` double DEFAULT NULL,
  `precio_producto` double DEFAULT NULL,
  `deposito_producto` varchar(45) NOT NULL,
  `existencia_producto` double DEFAULT NULL,
  PRIMARY KEY (`idProductos`),
  UNIQUE KEY `descripcion_producto_UNIQUE` (`descripcion_producto`),
  UNIQUE KEY `idProductos_UNIQUE` (`idProductos`),
  KEY `fk_deposito_producto_idx` (`deposito_producto`),
  CONSTRAINT `fk_deposito_producto` FOREIGN KEY (`deposito_producto`) REFERENCES `deposito` (`descripcion_deposito`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='				';

INSERT INTO `deposito` VALUES (1,'Altos de Jalisco'),(8,'Cuatricentenario'),(6,'El Mojan'),(7,'La Victoria'),(4,'Manzanillo'),(5,'Merida'),(3,'Milagro Norte'),(2,'Sabaneta');


INSERT INTO `productos` VALUES ('001-PP','Producto de Prueba 001',1850000,30,2650000,'Altos de Jalisco',10),('002-PP','Producto de Prueba 002',1000000,30,1430000,'Altos de Jalisco',10),('003-PP','Producto de Prueba 003',1500000,30,2143000,'Altos de Jalisco',10);
