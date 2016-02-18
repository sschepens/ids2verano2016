namespace Test
{
    using Moq;
    using Microsoft.VisualStudio.TestTools.UnitTesting;
    using TomaDePedido.Models;
    using TomaDePedido.Interfaces;
    using TomaDePedido.Enums;
    using TomaDePedido.Gestores;
    [TestClass]
    public class MesaTest
    {
        [TestMethod]
        public void ObtenerMontoAPagarTest()
        {
            var gestorPedido = new Mock<IGestorPedido>();            
            var codigoMesa = 72;

            gestorPedido.Setup(p => p.ObtenerSaldoMesa(codigoMesa)).Returns(145.50);                          
            Assert.AreEqual(gestorPedido.Object.ObtenerSaldoMesa(codigoMesa), 11.20);                        
        }

        [TestMethod]
        public void AbrirMesaTest()
        {
            var gestorPedido = new Mock<IGestorPedido>();
            var codigoMesa = 72;
            gestorPedido.Setup(h => h.ObtenerEstadoMesa(codigoMesa)).Returns(Enums.EstadoMesa.Ocupada);          
            Assert.AreEqual(gestorPedido.Object.ObtenerEstadoMesa(codigoMesa), Enums.EstadoMesa.Ocupada);
        }
    }
}
