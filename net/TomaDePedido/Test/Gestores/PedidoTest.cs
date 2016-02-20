namespace Test
{
    using Moq;
    using Microsoft.VisualStudio.TestTools.UnitTesting;
    using TomaDePedido.Models;
    using TomaDePedido.Interfaces;
    using TomaDePedido.Enums;
    using TomaDePedido.Gestores;
    using System.Collections.Generic;
    [TestClass]
    public class PedidoTest
    {
        GestorPedido gestorPedido;
        Mock<IGestorFacturacion> gestorFacturacion;
        Mock<IGestorCocina> gestorCocina;
        Mock<IGestorStock> gestorStock;

        [TestInitialize]
        public void TestInitialize()
        {
            this.gestorFacturacion = new Mock<IGestorFacturacion>();
            this.gestorCocina = new Mock<IGestorCocina>();
            this.gestorStock = new Mock<IGestorStock>();
        }

        [TestMethod]
        public void DebeRetornarUnPedido()
        {
            var codigoPlato = 12;
            var codigoCerveza = 22;
            var codigoMesa = 14;
            var pedido = new Pedido(codigoMesa) { EntregarTodoJunto = false };
            pedido.AgregarCerveza(codigoCerveza, 4);
            pedido.AgregarPlato(codigoPlato, 1, "Sin Aceitunas");
            pedido.AsignarCodigo(19);

            var pedidos = new List<IPedido>() { pedido };
            gestorFacturacion.Setup(f => f.EnviarPedido(pedido));
            gestorFacturacion.Setup(f => f.ObtenerPedidos(codigoMesa)).Returns(pedidos);

            this.gestorPedido = new GestorPedido(gestorCocina.Object, gestorFacturacion.Object, gestorStock.Object);

            gestorPedido.EnviarPedido(pedido);
            Assert.AreEqual(this.gestorPedido.ObtenerPedidos(codigoMesa).Count, pedidos.Count);
        }      
    }
}
